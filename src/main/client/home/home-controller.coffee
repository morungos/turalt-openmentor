angular
  .module 'openmentor.home'


  .controller 'HeaderController', Array '$scope', '$location', ($scope, $location) ->
    'use strict'

    $scope.isActive = (viewLocation) ->
      viewLocation == $location.path()


  .controller 'HomeController', () ->
    'use strict'
