export default (authSer, commonSer, $rootScope, $state) => {
  'ngInject';
  return {
    restrict: 'E',
    templateUrl: 'app/common/header/header.html',
    scope: true,
    replace: true,
    link: ($scope) => {

    }
  };
};
