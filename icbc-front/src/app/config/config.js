/*
* 全局配置对象
*/
let config = {

  // http 请求超时时间
  httpTimeout: 20000,

  // Api 的请求地址
  apiHost: 'http://10.60.36.96:8080/api',

  // 运行环境的 host
  host: 'http://localhost:9000'
};

// const host = window.location.host;
// if (('http://' + host) !== config.host) {
//   config.apiHost = 'http://' + host;
// }

export default config;
