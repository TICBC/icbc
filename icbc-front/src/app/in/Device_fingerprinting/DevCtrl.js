export default($scope, $rootScope, AuthTool, $state, qService, deviceRes, ToasterTool) => {
	'ngInject';
	$scope.getAll = () => {
		$rootScope.loading = true;
		qService.httpGetWithToken(deviceRes.DeviceAll, {}, {}).then((data) => {
	        if (data.success) {
	        	ToasterTool.success("查找成功");
	            $scope.items = data.data;
	            $scope.BJ
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
	$scope.findInMap = (item) => {
		// alert(item);
		//console.log(item);
		var BJData = [
			  [{
			      name: item.tranOutAcctZoneNum
			  }, {
			      name: item.tranOutAcctZoneNum,
			      value: 5
			  }],
			  [{
			      name: item.tranOutAcctZoneNum
			  }, {
			      name: item.tranInAcctZoneNum,
			      value: item.txAmt
			  }]

			];
		    console.log(BJData);
			paint(BJData);
	}

	$(function(){
		getRecords();
    paint(null);
	});

	function getRecords(){
		$rootScope.loading = true;
		qService.httpGetWithToken(deviceRes.DeviceAll, {}, {}).then((data) => {
	        if (data.success) {
	        	ToasterTool.success("查找成功");
	            $scope.items = data.data;
	            $scope.BJ
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

function paint(BJData){
    var myChart = echarts.init(document.getElementById('map'));
    var myHosName;                
    //<!-- 各省份的经纬度 -->
    var geoCoordMap = {
      '安徽省': [117.17, 31.52],
      '北京市': [116.24, 39.55],
      '重庆市': [106.54, 29.59],
      '福建省': [119.18, 26.05],
      '甘肃省': [103.51, 36.04],
      '广东省': [113.14, 23.08],
      '广西壮族自治区': [108.19, 22.48],
      '贵州省': [106.42, 26.35],
      '海南省': [110.20, 20.02],
      '河北省': [114.30, 38.02],
      '河南省': [113.40, 34.46],
      '黑龙江省': [128.36, 45.44],
      '湖北省': [112.27, 30.15],
      '湖南省': [112.59, 28.12],
      '吉林省': [125.19, 43.54],
      '江苏省': [118.46, 32.03],
      '江西省': [115.55, 28.40],
      '辽宁省': [123.25, 41.48],
      '内蒙古': [108.41, 40.48],
      '宁夏回族自治区': [106.16, 38.27],
      '青海省': [101.48, 36.38],
      '山东省': [118.00, 36.40],
      '山西省': [112.33, 37.54],
      '陕西省': [108.57, 34.17],
      '上海市': [121.29, 31.14],
      '海南': [108.77, 19.10],
      '四川省': [104.04, 30.40],
      '天津市': [117.12, 39.02],
      '西藏自治区': [91.08, 29.39],
      '新疆维吾尔自治区': [87.36, 43.45],
      '云南省': [102.42, 25.04],
      '浙江省': [120.10, 30.16],
      '澳门': [115.07, 21.33],
      '台湾省': [121.21, 23.53]
    };

    // var loc = document.getElementById("location");

    // myHosName = loc.innerHTML;
    // var BJData = [
    //   [{
    //       name: loc.innerHTML
    //   }, {
    //       name: loc.innerHTML,
    //       value: 100,
    //       ip: ip.innerHTML,
    //       time: time.innerHTML
    //   }]
   
    // ];

    var BJData_init = [
    [{
      name: '北京市'
    }, {
      name: '北京市',
      value: 5
    }],
    [{
      name: '北京市'
    }, {
      name: '上海市',
      value: 9
    }],
    [{
      name: '上海市'
    }, {
      name: '广东省',
      value: 90
    }]

    ];
    if(BJData == null){
	  BJData = BJData_init;
	}
    var convertData = function(data) {
      var res = [];
      for (var i = 0; i < data.length; i++) {
        var dataItem = data[i];
        var fromCoord = geoCoordMap[dataItem[0].name];
        var toCoord = geoCoordMap[dataItem[1].name];
        if (fromCoord && toCoord) {
            res.push({
              fromName: dataItem[0].name,
              toName: dataItem[1].name,
              coords: [fromCoord, toCoord]
            });
        }
      }
    return res;
    };

    var color = ['#a6c84c', '#ffa022', '#46bee9'];
    var mySeries = [];
    [
      [myHosName, BJData]
    ].forEach(function(item, i) {
      mySeries.push(
      { //省份圆点
        name: item[0],
        type: 'effectScatter',
        coordinateSystem: 'geo',
        zlevel: 2,
        rippleEffect: {
          brushType: 'stroke'
        },
        label: {
          normal: {
            show: true,
            position: 'right',
            formatter: '{b}'
          }
        },
        symbolSize: function(val) {
          if(val[3] <= 10)
            return 10;
          else
            return 20;
        },
        itemStyle: {
          normal: {
            color: function(params) {
              var tmp = params.data.value[3]
              if (tmp < 10) {
                return 'green';
              } else {
                return 'red'
              }
            }
          }
        },
        data: item[1].map(function(dataItem) {
          return {
            name: dataItem[1].name,
            value: geoCoordMap[dataItem[1].name].concat("</br>登陆次数").concat([dataItem[1].value])
                .concat("</br>登录IP").concat([dataItem[1].ip])
                .concat("</br>最近登录时间").concat([dataItem[1].time])
            };
        })
      });
    });

    var option = {
      backgroundColor: 'white',
      title: {
        text: '',
        subtext: '',
        left: 'center',
        textStyle: {
          color: 'blue'
        },
        subtextStyle: {
          color: 'yellow',
          fontWeight: 'bold'
        }
      },
      tooltip: {
        trigger: 'item',  
      },
      geo: {
        map: 'china',
        label: {
          emphasis: {
            show: false
          }
        },
        roam: true,
        itemStyle: {
          normal: {
            areaColor: '#cccccc',
            borderColor: '#404a59'
          },
          emphasis: {
            areaColor: '#2a333d'
          }
        }
      },
      series: mySeries
    };


    if (option && typeof option === "object") {
      myChart.setOption(option, true);
    }

    window.onresize = function() {
      myChart.resize();
    }
}