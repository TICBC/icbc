export default ($scope, $rootScope, qService, TransactionRes,customerInfoRes,deviceRes,loginRes, ToasterTool, BASE_URL) => {
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
	};
	


	//交易信息 查看全部
	$scope.getTransactionAll = () => {
		$rootScope.loading = true;
		qService.httpGet(TransactionRes.ResultsAll, {}, {}).then((data) => {
	        if (data.success) {
	        	ToasterTool.success("查找成功");
	            $scope.transactionItems = data.data;
	            console.log(data.data);
	        } else {
	        	$scope.transactionItems = null;
	        }
	    }, (err) => {
	    	ToasterTool.error("网络错误");
	    	$scope.transactionItems = null;
	    }).finally(() => {
	        $rootScope.loading = false;
	    });
	};

	//登陆信息 查看全部
	$scope.getLoginAll = () => {
		$rootScope.loading = true;
		qService.httpGet(loginRes.ResultsAll, {}, {}).then((data) => {
	        if (data.success) {
	        	ToasterTool.success("查找成功");
	            $scope.LoginItems = data.data;
	        } else {
	        	$scope.LoginItems = null;
	        }
	    }, (err) => {
	    	ToasterTool.error("网络错误");
	    	$scope.LoginItems = null;
	    }).finally(() => {
	        $rootScope.loading = false;
	    });
	};

	//客户信息 查看全部
	$scope.getCustomerAll = () => {
		$rootScope.loading = true;
		qService.httpGet(customerInfoRes.customerinfoAll, {}, {}).then((data) => {
			// console.log(data);
	        if (data.success) {
	        	ToasterTool.success("查找成功");
	            $scope.CustomerItems = data.data;
				// console.log($scope.CustomerItems);
	        } else {
	        	$scope.CustomerItems = null;
	        }
	    }, (err) => {
	    	ToasterTool.error("网络错误");
	    	$scope.CustomerItems = null;
	    }).finally(() => {
	        $rootScope.loading = false;
	    });
	};

	//设备信息
	$scope.getDeviceAll = () => {
		$rootScope.loading = true;
		qService.httpGet(deviceRes.DeviceAllEqu, {}, {}).then((data) => {
			// console.log(data);
	        if (data.success) {
	        	ToasterTool.success("查找成功");
	        	// console.log(data);
	            $scope.Deviceitems = data.data;
	        } else {
	        	$scope.Deviceitems = null;
	        }
	    }, (err) => {
	    	ToasterTool.error("网络错误");
	    	$scope.Deviceitems = null;
	    }).finally(() => {
	        $rootScope.loading = false;
	    });
	};

	$scope.getTransactionAll();
	$scope.getLoginAll();
	$scope.getCustomerAll();
	$scope.getDeviceAll();
	function showTab() {
		$(".tab-nav li").click(function(){

			var self = $(this), target = self.data("tab");
			self.addClass("current").siblings(".current").removeClass("current");
			// window.location.hash = "#" + target.substr(3);
			// $(".tab-pane.in").removeClass("in");
			$(this).parent().next().find(".tab-pane.in").removeClass("in");
			//$(this).parent().parent().find(".tab-pane.in").removeClass("in");
			$(this).parent().next().find("." + target).addClass("in");
		});
		// }).filter("[data-tab=tab" + window.location.hash.substr(1) + "]").click();
	}
	$(function()    
	 {    
		showTab();
		
	 });
	
	
}