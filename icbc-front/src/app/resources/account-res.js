/*
* $resource- 账户相关 
* --Mondooo
* 
*/
export default ($resource, BASE_URL) => {
	'ngInject';
	return {
		/**
		* 登录api
		* @Request Headers: X-Username; X-Password
		* @Request Params: null
		*/
		account: (headers) => {
			return $resource(BASE_URL+'/account/authentication', {}, {
				post: {
					method: 'POST',
					headers: headers, 
				}
			});
		}
	};
};