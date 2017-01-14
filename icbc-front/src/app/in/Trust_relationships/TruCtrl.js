export default ($scope, $rootScope, qService, customerInfoRes, ToasterTool, resultsRes, BASE_URL) => {
	

	const isNull = (value) => {
    	return typeof(value) == undefined || value == null;
    };

	

	$scope.getSome = () => {
		console.log($scope.params.value);
		
		$rootScope.loading = true;
		var params = {"id":$scope.params.value};
		qService.httpGet(customerInfoRes.customerinfo, params, {}).then((data) => {
	        if (data.success) {
	            if (data.data == null) {
	            	ToasterTool.error("无结果");
	            	$scope.items = null;
	            } else {
					console.log(data.data);
	            	ToasterTool.success("查找成功");
	            	$scope.items = data.data;
					
	            }
	        } else {
	        	$scope.items = null;
	        }
	    }, (err) => {
	    	ToasterTool.error("网络错误");
	    	$scope.items = null;
	    }).finally(() => {
	        $rootScope.loading = false;
	    });
	}

	
	
};