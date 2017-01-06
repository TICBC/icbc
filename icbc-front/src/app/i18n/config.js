// 多语言包
import zhCn from './zh-cn';
import en from './en';

const langPackage = {
  zhCn: zhCn,
  en: en
};

// 默认语言（其他语言缺失的部分会用默认语言代替）
const defaultLang = 'zhCn';

const config = ($rootScope, $window) => {
	'ngInject';
	$rootScope.i18n = langPackage[defaultLang];
	$rootScope.lang = defaultLang;
	$rootScope.$watch('lang', () => {
	  $window.$.extend($rootScope.i18n, langPackage[$rootScope.lang]);
	});
};

export default config;