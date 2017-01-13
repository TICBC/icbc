export default ($scope, $rootScope, qService, trustRes, ToasterTool, resultsRes, BASE_URL) => {
	

	const isNull = (value) => {
    	return typeof(value) == undefined || value == null;
    };

	

	$scope.getSome = () => {
		qService.httpPost(trustRes.trustAll, {},{}, {"user1":$scope.user1,
            "user2":$scope.user2,"time":$scope.time,"money":$scope.money}).then((data) => {
	        if (data.success) {
	           $scope.data = data.data;
               console.log($scope.data);
	        } else {
	        	
	        }
	    }, (err) => {
	    	ToasterTool.error("网络错误");
	    	$scope.items = null;
	    }).finally(() => {
	        $rootScope.loading = false;
	    });
	}

	// //////  画图
	// $(function(){
		
	// 	var dom = document.getElementById("container");

 //        var myChart1 = echarts.init(dom);

 //        var app = {};

 //        var option = null;
	
 //        myChart1.showLoading();

 //        $.get('data/data.gexf', function (xml) {
			
 //            myChart1.hideLoading();

 //            var graph = echarts.dataTool.gexf.parse(xml);
			
 //            var categories = [];
 //            for (var i = 0; i < 9; i++) {
 //                categories[i] = {
 //                    name: '用户' + i
 //                };
 //            }
 //            graph.nodes.forEach(function (node) {
 //                node.itemStyle = null;
 //                node.value = node.symbolSize;
 //                node.label = {
 //                    normal: {
 //                        show: node.symbolSize > 30
 //                    }
 //                };
 //                node.category = node.attributes.modularity_class;
 //            });
	// 		console.log(graph);
 //            option = {
 //                title: {
 //                    text: '当前用户风险关系信息',
 //                    subtext: 'Default layout',
 //                    top: 'bottom',
 //                    left: 'center'
 //                },
 //                tooltip: {},
 //                legend: [{
 //                    // selectedMode: 'single',
 //                    data: categories.map(function (a) {
 //                        return a.name;
 //                    })
 //                }],
 //                animationDuration: 1500,
 //                animationEasingUpdate: 'quinticInOut',
 //                series : [
 //                    {
 //                        name: '当前用户风险关系信息',
 //                        type: 'graph',
 //                        layout: 'none',
 //                        data: graph.nodes,
 //                        links: graph.links,
 //                        categories: categories,
 //                        roam: true,
 //                        label: {
 //                            normal: {
 //                                position: 'right',
 //                                formatter: '{b}'
 //                            }
 //                        },
 //                        lineStyle: {
 //                            normal: {
 //                                curveness: 0.4
 //                            }
 //                        }
 //                    }
 //                ]
 //            };

 //            myChart1.setOption(option);
 //        }, 'xml');
	// });
	
};