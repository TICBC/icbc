/*
* $resource- 物料信息相关
* --Mondooo
* 
*/
export default ($resource, BASE_URL) => {
	'ngInject';
	return {
		

		ResultsAll: (headers) => {
			return $resource(BASE_URL+'/logininfo/all', {}, {
				get: {
					method: 'GET',
					headers: headers,
				}
			});
		},
	};
};