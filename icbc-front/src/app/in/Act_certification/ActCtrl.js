export default ($scope, $rootScope, qService, TransactionRes, ToasterTool,$interval) => {
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
	            // ToasterTool.success("查找成功");
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
	
	var option = {
		title:{
								text:'拦截次数',
								left: 'center',
								textStyle: {
									color: '#000000'
								}
							},
	color: ['#922929'],
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
			data : ['2015-1-4', '2015-1-7', '2015-1-10'],
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
			data:[3,2, 2]
		}
	]
};
var picture1 = echarts.init(document.getElementById('chart'));
picture1.setOption(option);
};