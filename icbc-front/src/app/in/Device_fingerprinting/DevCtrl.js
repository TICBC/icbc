export default($scope, $rootScope, AuthTool, $state, qService, deviceRes, ToasterTool) => {
	'ngInject';
	$scope.getAll = () => {
		$rootScope.loading = true;
		qService.httpGetWithToken(deviceRes.DeviceAll, {}, {}).then((data) => {
	        if (data.success) {
	        	// ToasterTool.success("查找成功");
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

  //获取某个时间端内的拦截次数,供findPeroid函数调用
  var ValueArray=[0,0,0,0,0,0,0];
  function findByPeriod(datebegin, dateend, index){
    const params = {
      "FromDate" : datebegin,
      "ToDate" : dateend
    }
    qService.httpGet(deviceRes.DevicePeriod, params, {}).then((data) => {
      if (data.success) {
//            ToasterTool.success("查找成功");

            if (data.data!=null){
            var count = data.data.length;
            for (var i = 0, len = data.data.length; i< len; i++){
              if (data.data[i].equSign == 1)
                count--;
            }
            ValueArray[index] = count;
			}
			else
				ValueArray[index] = 0;
          } else {
            ToasterTool.error("无结果");
          }
      }, (err) => {
        ToasterTool.error("网络错误");
      }).finally(() => {
          $rootScope.loading = false;
    });
  }

  //获取某个时间段内的拦截次数,供页面中的“显示统计图”按键调用
  $scope.findPeriod = () => {
    var DateArray=[];
      var result;

      //统计图的计算x坐标,为最近几天
      var datenow = new Date('2015-01-10');
      var datebefore = new Date('2015-01-09');
      for (var i=0;i <7; i++){
        datebefore.setDate(datenow.getDate()-1);
        var dateend = datenow.getFullYear() + "-" + (datenow.getMonth() + 1) + "-" + datenow.getDate();
        var datebegin = datebefore.getFullYear() + "-" + (datebefore.getMonth() + 1) + "-" + datebefore.getDate();
        findByPeriod(datebegin, dateend, 6-i);
        DateArray.unshift(dateend);
        datenow.setDate(datenow.getDate()-1);
      }
      // 基于准备好的dom，初始化echarts实例
      var myChart = echarts.init(document.getElementById('chart'));

      // 指定图表的配置项和数据
      var option = {
        title: {
          text: '风险设备统计图(不可信登录次数)'
        },
        tooltip: {},
          xAxis: {
            data: DateArray
          },
          yAxis: {},
          series: [{
            name: '数量',
            type: 'bar',
            data: ValueArray
          }]
      };

        // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
  }

  //将交易记录中的信息绘制在土地上,调用paint()函数
	$scope.findInMap = (items) => {
    var BJData = [
    [{
      name: items[0].tranOutAcctZoneNum
    }, {
      name: items[0].tranOutAcctZoneNum,
      value: items[0].Euq_Sign
    }]
    ];

    for (var i = 1; i<items.length; i++){
      var Data = 
        [{
            name: items[i].tranOutAcctZoneNum
        }, {
            name: items[i].tranOutAcctZoneNum,
            value: 1
        }]
      BJData.push(Data);
    }

    if (BJData.length > 1)
  		paint(BJData);
    else
      paint(null);
	}

  //页面切换时自启动,获取记录
	$(function(){
		getRecords();
    paint(null);
	});

  //获取交易记录
	function getRecords(){
		$rootScope.loading = true;
		qService.httpGetWithToken(deviceRes.DeviceAll, {}, {}).then((data) => {
	        if (data.success) {
	        	// ToasterTool.success("查找成功");
	            $scope.items = data.data;
	            $scope.findInMap(data.data);
				$scope.findPeriod();
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

//绘制地图
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
      '广西壮族自治': [108.19, 22.48],
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
    var loc = document.getElementById("location");

    var BJData_init = [
    [{
      name: loc.innerHTML
    }, {
      name: loc.innerHTML,
      value: 1
    }],
    [{
      name: loc.innerHTML
    }, {
      name: loc.innerHTML,
      value: 1
    }],
    [{
      name: loc.innerHTML
    }, {
      name: loc.innerHTML,
      value: 1
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
//          return 10;
          if(val[3] == 1)
            return 10;
          else
            return 20;
        },
        itemStyle: {
          normal: {
            color: function(params) {
//              return 'green';
              var tmp = params.data.value[3]
              if (tmp == 1) {
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
