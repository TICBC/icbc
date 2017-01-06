/*
* $resource- 物料信息相关
* --Mondooo
* 
*/
export default ($resource, BASE_URL) => {
	'ngInject';
	return {
		
		materialsAll: (headers) => {
			return $resource(BASE_URL+'/materials/all', {}, {
				get: {
					method: 'GET',
					headers: headers,
				}
			});
		},
		
		materials: (headers) => {
			return $resource(BASE_URL+'/materials', {}, {
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