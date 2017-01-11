/*
* $resource- 物料信息相关
* --Mondooo
* 
*/
export default ($resource, BASE_URL) => {
	'ngInject';
	return {
		
		customerinfoAll: (headers) => {
			return $resource(BASE_URL+'/customerinfo/all', {}, {
				get: {
					method: 'GET',
					headers: headers,
				}
			});
		},
		
		customerinfo: (headers) => {
			return $resource(BASE_URL+'/customerinfo', {}, {
				post: {
					method: 'POST',
					headers: headers, 
				},
				get: {
					method: 'GET',
					headers: headers,
				},
				put: {
					method: 'PUT',
					headers: headers,
				}
			});
		},
	};
};