/*
* $resource- 物料结果相关
* --Mondooo
* 
*/
export default ($resource, BASE_URL) => {
	'ngInject';
	return {
		
		resultsAll: (headers) => {
			return $resource(BASE_URL+'/results/all', {}, {
				get: {
					method: 'GET',
					headers: headers,
				},
				delete: {
					method: 'DELETE',
					headers: headers,
				}
			});
		},
		
		results: (headers) => {
			return $resource(BASE_URL+'/results', {}, {
				post: {
					method: 'POST',
					headers: headers, 
				},
				delete: {
					method: 'DELETE',
					headers: headers,
				}
			});
		},
	};
};