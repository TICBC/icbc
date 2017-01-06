/*
* 功能: 异步方式封装http调用
* --Mondooo
*/
export default ($q, $state, $sessionStorage, ToasterTool) => {
	'ngInject';

	let TOKEN_KEY = 'x-auth-token';
	// $sessionStorage[TOKEN_KEY] = "6d60749d793e43cc8599aa1a1f8aa227";

	const CodeHandler = (value) => {
		if (value.code === "502") {
			$state.go("portal");
			ToasterTool.warning("本系统需要登录之后使用！");
		}
	}
	
	return {
		httpGet: (resource, parameters, headers) => {
			return $q((resolve, reject) => {
				resource(headers).get(parameters,
				(value, responseHeaders) => {
					value.headers = responseHeaders ? responseHeaders() : "";
					resolve(value);
				}, 
				(httpResponse) => {
					reject(httpResponse);
				});
			});
		},
		httpGetWithToken: (resource, parameters, headers) => {
			return $q((resolve, reject) => {
				headers['X-Auth-Token'] = $sessionStorage[TOKEN_KEY];
				resource(headers).get(parameters,
				(value, responseHeaders) => {
					CodeHandler(value);
					value.headers = responseHeaders ? responseHeaders() : "";
					resolve(value);
				}, 
				(httpResponse) => {
					reject(httpResponse);
				});
			});
		},
		httpPost: (resource, parameters, headers, body) => {
			return $q((resolve, reject) => {
				resource(headers).post(parameters,body,
				(value, responseHeaders) => {
					value.headers = responseHeaders ? responseHeaders() : "";
					resolve(value);
				}, 
				(httpResponse) => {
					reject(httpResponse);
				});
			});
		},
		httpPostWithToken: (resource, parameters, headers, body) => {
			return $q((resolve, reject) => {
				headers['X-Auth-Token'] = $sessionStorage[TOKEN_KEY];
				resource(headers).post(parameters,body,
				(value, responseHeaders) => {
					CodeHandler(value);
					value.headers = responseHeaders ? responseHeaders() : "";
					resolve(value);
				}, 
				(httpResponse) => {
					reject(httpResponse);
				});
			});
		},
		httpPut: (resource, parameters, headers, body) => {
			return $q((resolve, reject) => {
				resource(headers).put(parameters,body,
				(value, responseHeaders) => {
					value.headers = responseHeaders ? responseHeaders() : "";
					resolve(value);
				}, 
				(httpResponse) => {
					reject(httpResponse);
				});
			});
		},
		httpPutWithToken: (resource, parameters, headers, body) => {
			return $q((resolve, reject) => {
				headers['X-Auth-Token'] = $sessionStorage[TOKEN_KEY];
				resource(headers).put(parameters,body,
				(value, responseHeaders) => {
					CodeHandler(value);
					value.headers = responseHeaders ? responseHeaders() : "";
					resolve(value);
				}, 
				(httpResponse) => {
					reject(httpResponse);
				});
			});
		},
		httpDelete: (resource, parameters, headers, body) => {
			return $q((resolve, reject) => {
				resource(headers).delete(parameters,body,
				(value, responseHeaders) => {
					value.headers = responseHeaders ? responseHeaders() : "";
					resolve(value);
				}, 
				(httpResponse) => {
					reject(httpResponse);
				});
			});
		},
		httpDeleteWithToken: (resource, parameters, headers, body) => {
			return $q((resolve, reject) => {
				headers['X-Auth-Token'] = $sessionStorage[TOKEN_KEY];
				resource(headers).delete(parameters,body,
				(value, responseHeaders) => {
					CodeHandler(value);
					value.headers = responseHeaders ? responseHeaders() : "";
					resolve(value);
				}, 
				(httpResponse) => {
					reject(httpResponse);
				});
			});
		},
	};
};