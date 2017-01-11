/* 
* please keep the code elegant
*/

// config
import config from './config/config';
import httpConfig from './config/http';
import routerConfig from './config/route';
import i18nConfig from './i18n/config';

// service
import qService from './services/q-service';
import toolService from './services/tool-service';
import AuthTool  from './services/auth-tool';
import ToasterTool  from './services/toaster-tool';

// resource
import accountRes from './resources/account-res';
import materialsRes from './resources/materials-res';
import resultsRes from './resources/results-res';
import deviceRes from './resources/device-res';
import TransactionRes from './resources/transaction-res';

import customerInfoRes from './resources/customerInfo-res';

// directive
import headerDirect from './common/header/headerDirect';

// filter

// controllers
// 登陆及首页Controller
import portalCtrl from './auth/portal/portalCtrl';
import inCtrl from './in/inCtrl';
//import homeCtrl from './in/home/homeCtrl';
//import resultCtrl from './in/result/resultCtrl';
//四部分内容
import ActCtrl from './in/Act_certification/ActCtrl';
import DevCtrl from './in/Device_fingerprinting/DevCtrl';
import RisCtrl from './in/Risk_monitoring/RisCtrl';
import TruCtrl from './in/Trust_relationships/TruCtrl';

//import Transaction from 'in/';
angular.module('soraka',
  ['ngAnimate', 'ngCookies', 'ngSanitize', 'ui.router', 'ngResource', 'ngStorage', 'toaster'])

  // 配置全局常量
  .constant('lcConfig', config)
  .constant('moment', window.moment)
  .constant('BASE_URL', 'http://localhost:8080/api') // 测试
  // .constant('BASE_URL', '/api') // 发布

  // 基础配置
  .config(httpConfig)
  .config(routerConfig)
  
  // 自动执行
  .run(i18nConfig)

  // services 初始化
  .service('qService', qService)
  .service('toolService', toolService)
  .service('AuthTool',AuthTool)
  .service('ToasterTool',ToasterTool)

  // factory 初始化
  .factory('accountRes', accountRes)
  .factory('materialsRes', materialsRes)
  .factory('resultsRes', resultsRes)

  .factory('customerInfoRes', customerInfoRes)

  .factory('TransactionRes',TransactionRes)
  .factory('deviceRes', deviceRes)


  // directive 初始化
  .directive('lcHeader', headerDirect)
  
  // filter 初始化

  // controllers 初始化
  .controller('portalCtrl', portalCtrl)
  .controller('inCtrl', inCtrl)
  //.controller('homeCtrl', homeCtrl)
  //.controller('resultCtrl', resultCtrl)

  .controller('ActCtrl',ActCtrl)
  .controller('DevCtrl',DevCtrl)
  .controller('RisCtrl',RisCtrl)
  .controller('TruCtrl',TruCtrl)
  ;