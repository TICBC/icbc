/*
* $
* --Mondooo
* 
*/
export default ($resource, BASE_URL) => {
	'ngInject';
	return {
		
		trustAll: (headers) => {
			return $resource(BASE_URL+'/socialnet/all', {}, {
				post: {
					method: 'POST',
					headers: headers,
				}
			});
		},
	};
};