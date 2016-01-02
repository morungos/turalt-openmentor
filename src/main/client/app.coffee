angular
  .module 'openmentor', [
    'ui.bootstrap'
    'ui.router'
    'openmentor.home'
    'openmentor.error'
    'openmentor.authentication'
    'openmentor-templates'
  ]


  .config Array '$stateProvider', ($stateProvider) ->
    $stateProvider
      .state 'home',
        controller: 'HomeController'
        templateUrl: '/openmentor/home/home.html'
      .state 'home.default',
        templateUrl: '/openmentor/home/overview.html',
        url: '/'
      .state 'home.courses',
        controller: 'CoursesController',
        templateUrl: '/openmentor/home/courses.html',
        url: '/courses'
      .state 'help',
        templateUrl: '/openmentor/pages/help.html'
        url: '/help'
      .state 'login',
        controller: 'LoginController'
        templateUrl: '/openmentor/authentication/login.html'
        params: { challenge : { value: "default" }, prompt : { value : "default" }}
        url: '/login'
      .state 'error',
        controller: 'ErrorController'
        templateUrl: '/openmentor/error/error.html'
        url: '/error'
        params: {
          message: "An error"
        }


  .config Array '$urlRouterProvider', ($urlRouterProvider) ->
    $urlRouterProvider.otherwise('/')


  .config Array '$locationProvider', ($locationProvider) ->
    $locationProvider.html5Mode({enabled: true, requireBase: false})
    $locationProvider.hashPrefix = "!"


  .config Array '$httpProvider', ($httpProvider) ->
    $httpProvider.interceptors.push 'httpInterceptor'
    $httpProvider.defaults.withCredentials = true
    $httpProvider.defaults.useXDomain = true


  .run Array '$rootScope', '$interval', '$document', (scope, $interval, $document) ->

    IDLE_TIMEOUT = 15 * 60
    idleSecondsTimer = null
    idleSecondsCounter = 0

    resetCounter = () ->
      idleSecondsCounter = 0

    $document.on 'click', resetCounter
    $document.on 'mousemove', resetCounter
    $document.on 'keypress', resetCounter

    checkIdleTime = () ->
      idleSecondsCounter++
      if idleSecondsCounter > IDLE_TIMEOUT
        $interval.cancel idleSecondsTimer
        scope.$broadcast 'timeout:logout'

    idleSecondsTimer = $interval checkIdleTime, 1000


  .run Array '$rootScope', '$http', '$timeout', '$state', 'authenticationService', (scope, $http, $timeout, $state, authenticationService) ->

    class User
      constructor: (user) ->
        for own key, value of user
          this[key] = value

    ## Values needed to handle the authentication
    config =
      headers: {'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'}

    scope.requests401 = []
    scope.user = undefined

    scope.login = () -> scope.$emit "event:loginRequest"
    scope.logout = () -> scope.$emit "event:logoutRequest"

    scope.$on 'event:httpError', (evt, value) ->
      $state.go 'error', value

    scope.$on 'event:loginCancelled', () ->
      $state.go('logout')

    scope.$on 'event:logoutConfirmed', () ->
      scope.user = undefined
      $state.go('logout')

    scope.$on 'event:loginConfirmed', (event, user) ->
      console.log 'event:loginConfirmed'
      scope.user = new User(user)
      scope.requests401 = []
      $state.go('home')

    scope.$on 'event:loginRequest', (evt) ->
      $state.go 'home'

    scope.$on 'event:logoutRequest', (evt) ->
      authenticationService.logout()
      $state.go 'logout'

    scope.$on 'timeout:logout', (evt) ->
      authenticationService.logout()
      $state.go 'logout'
