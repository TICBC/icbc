export default ($stateProvider, $urlRouterProvider, $locationProvider) => {
  'ngInject';

  $stateProvider

    //登陆
    // .state('app', {
    //   url: '/',
    //   abstract: true,
    //   templateUrl: 'app/auth/portal/portal.html',
    //   controller: 'portalCtrl'
    // })
    // 首页
    .state('in', {
      // abstract: true,
      url: '/',
      templateUrl: 'app/in/in.html', 
      controller: 'inCtrl'
    })
    .state('in.home', {
      url: '^/home',
      templateUrl: 'app/in/home/home.html',
      controller: 'homeCtrl'
    })
    .state('in.result', {
      url: '^/result',
      templateUrl: 'app/in/result/result.html',
      controller: 'resultCtrl'
    })
    ;
    

  $urlRouterProvider.otherwise('/');
};
