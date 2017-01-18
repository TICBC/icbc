export default ($scope, $rootScope, qService, TransactionRes,customerInfoRes,deviceRes,loginRes,$interval, ToasterTool, BASE_URL) => {
	'ngInject';
	const isNull = (value) => {
    	return typeof(value) == undefined || value == null;
	};
	

	//交易信息 查看全部
	$scope.getTransactionAll = () => {
		$rootScope.loading = true;
		qService.httpGet(TransactionRes.ResultsAll, {}, {}).then((data) => {
	        if (data.success) {
	        	
	            $scope.transactionItems = data.data;

				var dataArray = data.data;
	            var total = dataArray.length;
				var equ=0,act=0,tru=0;
				var flag_equ=0,flag_act=0,flag_tru=0;
				
				for(var t in dataArray){
					
					if(dataArray[t].actSign == 0){
						act++;
						flag_act = 1;
					}
					if(dataArray[t].equSign == 0){
						equ++;
						flag_equ = 1;
					}
					if(dataArray[t].truSign == 0){
						tru++;
						flag_tru = 1;
					}
					
					flag_act = 0;
					flag_equ = 0;
					flag_tru = 0;
				}
				
				// dataArray.forEach();
				
				//画统计图
					var option = {
							title:{
								text:'分类拦截交易数据',
								left: 'center',
								textStyle: {
									color: '#3398DB'
								}
							},
							color: ['#3398DB'],
							tooltip : {
								trigger: 'axis',
								axisPointer : {            // 坐标轴指示器，坐标轴触发有效
									type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
								}
							},
							grid: {
								left: '3%',
								right: '4%',
								bottom: '3%',
								containLabel: true
							},
							xAxis : [
								{
									type : 'category',
									data : ['设备指纹', '行为认证', '信任关系'],
									axisTick: {
										alignWithLabel: true
									}
								}
							],
							yAxis : [
								{
									type : 'value'
								}
							],
							series : [
								{
									name:'直接访问',
									type:'bar',
									barWidth: '60%',
									data:[equ, act, tru]
								}
							]
						};
						var picture1 = echarts.init(document.getElementById('pic1'));
						// picture1.setOption(option);
				//////////
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
	 
	 
	 
	
	
	// 定时查询ById   -- start
	// var ids = [1,2,3,4,5,6,7,8,9,10,11,12];
	var id_cur,i=0,n=0,total=0;
	var equ=0,act=0,tru=0;
	var arrayItem = new Array();
	$scope.passArray = new Array;
	$scope.rejectArray = new Array;
	var timer = $interval(function () {
		// i = n%5;
		i++;
		n = i%120;

		getByIdLoop(n);
		
		
	}, 3000);
	function getByIdLoop(id) {
		// if (isNull($scope.params.value)) {
		// 	ToasterTool.warning("输入不能为空");
		// 	return;
		// }
		const params = {
			"id": id,
			//"value": $scope.params.value,
		}
		qService.httpGet(TransactionRes.TransactionInfo, params, {}).then((data) => {
	    if (data.success) {
	        	//console.log("hehe");
	        if (data.data == null) {
	            ToasterTool.error("无结果");
	            // $scope.items = null;
	        } else {
				// [data.data];
	            arrayItem.push(data.data);
				$scope.items= angular.copy(arrayItem);

				total++;
				
				//画统计图
					//1-过，0-不过
					var flag_equ=0,flag_act=0,flag_tru=0;
					if(data.data.actSign == 0){
						act++;
						flag_act = 1;
					}
					if(data.data.equSign == 0){
						equ++;
						flag_equ = 1;
					}
					if(data.data.truSign == 0){
						tru++;
						flag_tru = 1;
					}
					if(flag_act==1 && flag_equ==1 && flag_tru==1){
						$scope.rejectArray.push(data.data);
					}else{
						$scope.passArray.push(data.data);
					}
					var option = {
							color: ['#3398DB'],
							tooltip : {
								trigger: 'axis',
								axisPointer : {            // 坐标轴指示器，坐标轴触发有效
									type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
								}
							},
							grid: {
								left: '3%',
								right: '4%',
								bottom: '3%',
								containLabel: true
							},
							xAxis : [
								{
									type : 'category',
									data : ['设备指纹', '行为认证', '信任关系'],
									axisTick: {
										alignWithLabel: true
									}
								}
							],
							yAxis : [
								{
									type : 'value'
								}
							],
							series : [
								{
									name:'直接访问',
									type:'bar',
									barWidth: '60%',
									data:[equ, act, tru]
								}
							]
						};
						var picture1 = echarts.init(document.getElementById('pic1'));
						picture1.setOption(option);

	           }
	    } else {
	    	ToasterTool.error("无结果");

	        // $scope.items = null;
	    }
	    }, (err) => {
	    	ToasterTool.error("网络错误");
	    	// $scope.items = null;
	    }).finally(() => {
	        $rootScope.loading = false;
	    });
	};
	
	$scope.$on('$destroy',function(){
		$interval.cancel(timer);
	})
	

	/////////// 定时查询ById  --end
	
	
}