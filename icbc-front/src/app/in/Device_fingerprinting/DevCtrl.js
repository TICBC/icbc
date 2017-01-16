export default($scope, $rootScope, AuthTool, $state, qService, deviceRes, ToasterTool) => {
	'ngInject';
	$scope.getAll = () => {
		$rootScope.loading = true;
		qService.httpGetWithToken(deviceRes.DeviceAll, {}, {}).then((data) => {
	        if (data.success) {
	        	ToasterTool.success("查找成功");
	            $scope.items = data.data;
	        } else {
	        	$scope.items = null;
	        }
	    }, (err) => {
	    	ToasterTool.error("网络错误");
	    	$scope.items = null;
	    }).finally(() => {
	        $rootScope.loading = false;
	    });
	};
};