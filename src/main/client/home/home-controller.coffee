angular
  .module 'openmentor.home'


  .controller 'HeaderController', Array '$scope', '$location', ($scope, $location) ->
    'use strict'

    $scope.isActive = (viewLocation) ->
      viewLocation == $location.path()


  .controller 'HomeController', () ->
    'use strict'


  .controller 'CoursesController', Array '$scope', '$http', ($scope, $http) ->
    'use strict'

    $scope.data = undefined

    $http
      .get('/api/courses')
      .success (response) ->
        $scope.data = response
      .error (response) ->
        console.log "Error", response
