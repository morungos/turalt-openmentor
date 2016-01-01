angular
  .module 'openmentor.error'

  .controller 'ErrorController', Array '$scope', '$http', '$stateParams', ($scope, $http, $stateParams) ->
    $scope.message = $stateParams.message
