export default ($scope, $rootScope, qService, TransactionRes, ToasterTool, BASE_URL) => {
	'ngInject';
	const isNull = (value) => {
    	return typeof(value) == undefined || value == null;
};
	$scope.getById = () => {
		// if (isNull($scope.params.value)) {
		// 	ToasterTool.warning("输入不能为空");
		// 	return;
		// }
		$rootScope.loading = true;
		const params = {
			"id": 1,
			//"value": $scope.params.value,
		}
		qService.httpGet(TransactionRes.TransactionInfo, params, {}).then((data) => {
	    if (data.success) {
	        	//console.log("hehe");
	        if (data.data == null) {
	            ToasterTool.error("无结果");
	            $scope.items = null;
	        } else {
	            ToasterTool.success("查找成功");
	            $scope.items = data.data;
	            ToasterTool.success(data.data);
	           }
	    } else {
	    	ToasterTool.error("无结果");
	        $scope.items = null;
	    }
	    }, (err) => {
	    	ToasterTool.error("网络错误");
	    	$scope.items = null;
	    }).finally(() => {
	        $rootScope.loading = false;
	    });
	}
}