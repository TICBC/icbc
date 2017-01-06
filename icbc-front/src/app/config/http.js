/*
* $http, $httpProvider
*/
export default ($httpProvider, lcConfig) => {
  'ngInject';

  const httpTimeout = lcConfig.httpTimeout;
  const apiHost = lcConfig.apiHost;

  // 跨域请求发送验证信息
  $httpProvider.defaults.withCredentials = true;
  // delete 可以携带 josn 数据。
  $httpProvider.defaults.headers.delete = {
    'Content-Type': 'application/json;charset=utf-8'
  };

  // 全局 $http 请求配置。
  $httpProvider.interceptors.push([() => {
    return {
      request: (config) => {
          config.timeout = httpTimeout;
          // // 当 url 中没有 http 或者 https 的时候，自动拼接默认的 apiHost
          // if (!/^[http|https]/.test(config.url) && !/\.html$/.test(config.url)) {
          //     config.url = apiHost + config.url;
          // }
          return config;
      },
      response: (response) => {
          if (/\.html/.test(response.config.url)) {
              return response;
          } else {
              return response; //坑爹的地方, 业界标准, RESTful返回的数据名称叫[data].
          }
      },
      responseError: function(response) {
        return Promise.reject(response);
      }
    };
  }]);
};
