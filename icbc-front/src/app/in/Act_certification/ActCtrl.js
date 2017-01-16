export default ($scope, $rootScope, qService, TransactionRes, ToasterTool, BASE_URL,$interval) => {
	'ngInject';
//	$state.go("in.result");
	$scope.getAll = () => {
		// if (isNull($scope.params.value)) {
		// 	ToasterTool.warning("输入不能为空");
		// 	return;
		// }
		$rootScope.loading = false;
		
		qService.httpGet(TransactionRes.ResultsAll, {}, {}).then((data) => {
	    if (data.success) {
	        	//console.log("hehe");
	        if (data.data == null) {
	            ToasterTool.error("无结果");
	            $scope.items = null;
	        } else {
	            ToasterTool.success("查找成功");
	            $scope.items = data.data;
	            // ToasterTool.success(data.data);
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
	$scope.getAll();
};