/*
* $resource- 物料信息相关
* --Mondooo
* 
*/
export default ($resource, BASE_URL) => {
	'ngInject';
	return {
		
		TransactionInfo: (headers) => {
			return $resource(BASE_URL+'/TransactionInfo', {}, {
				get: {
					method: 'GET',
					headers: headers,
				}
			});
		},
		ResultsAll: (headers) => {
			return $resource(BASE_URL+'/TransactionInfo/all', {}, {
				get: {
					method: 'GET',
					headers: headers,
				}
			});
		},
	};
};