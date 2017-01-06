export default (toaster) => {
	'ngInject';
	return {
		success: (title, body) => {
			toaster.pop({
				type: 'success',
				title: title || '',
				body: body || '',
				showCloseButton: false,
				timeout: 2000
			});
    	},
    	info: (title, body) => {
			toaster.pop({
				type: 'info',
				title: title || '',
				body: body || '',
				showCloseButton: false,
				timeout: 2000
			});
		},
		warning: (title, body) => {
			toaster.pop({
				type: 'warning',
				title: title || '',
				body: body || '',
				showCloseButton: false,
				timeout: 2000
			});
		},
		error: (title, body) => {
			toaster.pop({
				type: 'error',
				title: title || '',
				body: body || '',
				showCloseButton: false,
				timeout: 2000
			});
		}
	}
}