export default ($resource, BASE_URL) => {
	'ngInject';
	return {
		
		DeviceAll: (headers) => {
			return $resource(BASE_URL+'/TransactionInfo/all', {}, {
				get: {
					method: 'GET',
					headers: headers,
				}
			});
		},

		DeviceAllEqu: (headers) => {
			return $resource(BASE_URL+'/device/allequ', {}, {
				get: {
					method: 'GET',
					headers: headers,
				}
			});
		},

		DevicePeriod: (headers) => {
			return $resource(BASE_URL+'/TransactionInfo/period', {}, {
				get: {
					method: 'GET',
					headers: headers,
				}
			});
		},

		DeviceOutCard: (headers) => {
			return $resource(BASE_URL+'/TransactionInfo/outcardnum', {}, {
				get: {
					method: 'GET',
					headers: headers,
				}
			});
		},
		// Device: (headers) => {
		// 	return $resource(BASE_URL+'/materials', {}, {
		// 		post: {
		// 			method: 'POST',
		// 			headers: headers, 
		// 		},
		// 		get: {
		// 			method: 'GET',
		// 			headers: headers,
		// 		},
		// 		put: {
		// 			method: 'PUT',
		// 			headers: headers,
		// 		}
		// 	});
		// },
	};
};