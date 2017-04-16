export default ($scope, $rootScope, qService, TransactionRes, ToasterTool,$interval) => {
	'ngInject';
	//	$state.go("in.result");
	//交易数字统计 start
    var total_money=0;
    var total_times = 0;
    var max_n = 0;
    var id_cur,i=0,n=0;

    qService.httpGet(TransactionRes.CountAll, '', {}).then((data) => {
    if (data.success) {
            //console.log("hehe");
        if (data.data == null) {
            ToasterTool.error("无结果");
            // $scope.items = null;
        } else {
            //获取当前时间
            console.log(data.data);
            total_money = data.data.txAmtCount;
            total_times = data.data.total;
            n = data.data.lastId;

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

    //交易数字统计 end
	

    // 定时查询ById   -- start
    // var ids = [1,2,3,4,5,6,7,8,9,10,11,12];
    var value;
    
    var equ=0,act=0,tru=0;
    var arrayItem = new Array();
    var arrayLength = 8;
    var timer = $interval(function () {
        // i = n%5;
        // i++;
        // n = i%120;

        getByIdLoop(n);
        
        
    }, 1000);
    function getByIdLoop(id) {
        // if (isNull($scope.params.value)) {
        //  ToasterTool.warning("输入不能为空");
        //  return;
        // }
        const params = {
            "id": id,
            //"value": $scope.params.value,
        }
        qService.httpGet(TransactionRes.TransactionInfo, params, {}).then((data) => {
        if (data.success) {
                //console.log("hehe");
            if (data.data == null) {
                // ToasterTool.error("无结果");
                // $scope.items = null;
            } else {
                // [data.data];
                n++;
                total_times++;
                total_money = total_money + data.data['txAmt'];
                if(data.data['txAmt'] > max_n){
                    max_n = data.data['txAmt'];
                }
                if(arrayItem.length < arrayLength){
                    arrayItem.push(data.data);
                }else{
                    for (var i = 0 ; i < arrayLength - 1; i++) {
                        arrayItem[i] = arrayItem[i+1];
                    }
                    arrayItem[arrayLength-1] = data.data;
                    value = data.data.txAmt
                    console.log(value)
                }
                

                $scope.items= angular.copy(arrayItem);
                $scope.Tmoney= angular.copy(total_money);
                $scope.Ttimes= angular.copy(total_times);
                $scope.MaxN= angular.copy(max_n);
            }
        } else {
            // ToasterTool.error("无结果");

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
	// $scope.new1_hist();
	
	var myChart = echarts.init(document.getElementById('main'));
	var xData = function() {
		var data = [];
		for (var i = 0; i < 25; i++) {
			data.push(i + "时");
		}
		return data;
	}();

	var option = {
		backgroundColor: "#ffffff",
		"title": {
			"text": "拦截量/放行量",
			
			x: "4%",

			textStyle: {
				color: '#000000',
				fontSize: '20'

			},
			
		},
		"tooltip": {
			"trigger": "axis",
			"axisPointer": {
				"type": "shadow",
				textStyle: {
					color: "#fff"
				}

			},
		},
		"grid": {
			"borderWidth": 0,
			"top": 130,
			"bottom": 95,
			textStyle: {
				color: "#fff"
			}
		},
		"legend": {
			x: '4%',
			top: '8%',
			textStyle: {
				color: '#90979c',
			},
			"data": ['放行量', '拦截量', '平均']
		},
		

		"calculable": true,
		"xAxis": [{
			"type": "category",
			"axisLine": {
				lineStyle: {
					color: '#90979c'
				}
			},
			"splitLine": {
				"show": false
			},
			"axisTick": {
				"show": false
			},
			"splitArea": {
				"show": false
			},
			"axisLabel": {
				"interval": 0,

			},
			"data": xData,
		}],
		"yAxis": [{
			"type": "value",
			"splitLine": {
				"show": false
			},
			"axisLine": {
				lineStyle: {
					color: '#90979c'
				}
			},
			"axisTick": {
				"show": false
			},
			"axisLabel": {
				"interval": 0,

			},
			"splitArea": {
				"show": false
			},

		}],
		"dataZoom": [{
			"show": true,
			"height": 30,
			"xAxisIndex": [
			0
			],
			bottom: 30,
			"start": 10,
			"end": 80,
			handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
			handleSize: '110%',
			handleStyle:{
				color:"#d3dee5",
				
			},
			textStyle:{
				color:"#fff"},
				borderColor:"#90979c"
				
				
			}, {
				"type": "inside",
				"show": true,
				"height": 15,
				"start": 1,
				"end": 35
			}],
			"series": [{
				"name": "放行量",
				"type": "bar",
				"stack": "总量",
				"barMaxWidth": 35,
				"barGap": "10%",
				"itemStyle": {
					"normal": {
						"color": "rgba(255,144,128,1)",
						"label": {
							"show": true,
							"textStyle": {
								"color": "#fff"
							},
							"position": "insideBottom",
							formatter: function(p) {
								return p.value > 0 ? (p.value) : '';
							}
						}
					}
				},
				"data": [
				709,
				1917,
				2455,
				2610,
				1719,
				1433,
				1544,
				3285,
				5208,
				3372,
				2484,
				4078,
                709,
                1917,
                2455,
                2610,
                1719,
                1433,
                1544,
                3285,
                5208,
                3372,
                2484,
                4078
				],
			},

			{
				"name": "拦截量",
				"type": "bar",
				"stack": "总量",
				"itemStyle": {
					"normal": {
						"color": "rgba(0,191,183,1)",
						"barBorderRadius": 0,
						"label": {
							"show": true,
							"position": "top",
							formatter: function(p) {
								return p.value > 0 ? (p.value) : '';
							}
						}
					}
				},
				"data": [
				327,
				1776,
				507,
				1200,
				800,
				482,
				204,
				1390,
				1001,
				951,
				381,
				220,
                327,
                1776,
                507,
                1200,
                800,
                482,
                204,
                1390,
                1001,
                951,
                381,
                220
				]
			}, 
			]
		}
		// 为echarts对象加载数据 
		myChart.setOption(option);

		var myChart2 = echarts.init(document.getElementById('pie'));
        var app2=[];
        // 指定图表的配置项和数据
        var option2 = {
            title: {
                "text": '交易放行率',
                "x": '50%',
                "y": '45%',
                textAlign: "center",
                "textStyle": {
                    "fontWeight": 'normal',
                    "fontSize": 24
                },
                "subtextStyle": {
                    "fontWeight": 'bold',
                    "fontSize": 32,
                    "color": '#34f1a4'
                }
            },
            series: [
                {
                    "name": ' ',
                    "type": 'pie',
                    "radius": ['50%', '70%'],
                    "avoidLabelOverlap": false,
                    "startAngle": 225,
                    "color": ["#34f1a4", "transparent"],
                    "hoverAnimation": false,
                    "legendHoverLink": false,
                    "label": {
                        "normal": {
                            "show": false,
                            "position": 'center'
                        },
                        "emphasis": {
                            "show": true,
                            "textStyle": {
                                "fontSize": '30',
                                "fontWeight": 'bold'
                            }
                        }
                    },
                    "labelLine": {
                        "normal": {
                            "show": false
                        }
                    },
                    "data": [{
                        "value": 75,
                        "name": '1'
                    }, {
                        "value": 25,
                        "name": '2'
                    }]
                },
                {
                    "name": '',
                    "type": 'pie',
                    "radius": ['52%', '68%'],
                    "avoidLabelOverlap": false,
                    "startAngle": 317,
                    "color": ["#fff", "transparent"],
                    "hoverAnimation": false,
                    "legendHoverLink": false,
                    "clockwise": false,
                    "itemStyle":{
                        "normal":{
                            "borderColor":"transparent",
                            "borderWidth":"20"
                        },
                        "emphasis":{
                            "borderColor":"transparent",
                            "borderWidth":"20"
                        }
                    }
                    ,
                    "z":10,
                    "label": {
                        "normal": {
                            "show": false,
                            "position": 'center'
                        },
                        "emphasis": {
                            "show": true,
                            "textStyle": {
                                "fontSize": '30',
                                "fontWeight": 'bold'
                            }
                        }
                    },
                    "labelLine": {
                        "normal": {
                            "show": false
                        }
                    },
                    "data": [{
                        // "value": (100 - value1) * 266 / 360,
                        "name": ''
                    }, {
                        // "value": 100 - (100 - value1) * 266 / 360,
                        "name": ''
                    }
                    ]
                }

            ]
        };

        // 使用刚指定的配置项和数据显示图表。
//        myChart.setOption(option);
        app2.timeTicket = setInterval(function() {
            var value = parseInt(Math.random() * 55) + 30,
                    value_ = (100 - value) * 266 / 360;
            option2.title.subtext = value + "%";
            option2.series[1].data[0].value = value_;
            option2.series[1].data[1].value = 100 - value_;
            myChart2.setOption(option2, true);
        }, 1000);

        var myChart3 = echarts.init(document.getElementById('pie2'));
        var app3=[];
        // 指定图表的配置项和数据
        var option3 = {
            title: {
                "text": '信任关系拦截率',
                "x": '50%',
                "y": '45%',
                textAlign: "center",
                "textStyle": {
                    "fontWeight": 'normal',
                    "fontSize": 16
                },
                "subtextStyle": {
                    "fontWeight": 'bold',
                    "fontSize": 20,
                    "color": '#e42a0a'
                }
            },
            series: [
                {
                    "name": ' ',
                    "type": 'pie',
                    "radius": ['50%', '70%'],
                    "avoidLabelOverlap": false,
                    "startAngle": 225,
                    "color": ["#e42a0a", "transparent"],
                    "hoverAnimation": false,
                    "legendHoverLink": false,
                    "label": {
                        "normal": {
                            "show": false,
                            "position": 'center'
                        },
                        "emphasis": {
                            "show": true,
                            "textStyle": {
                                "fontSize": '30',
                                "fontWeight": 'bold'
                            }
                        }
                    },
                    "labelLine": {
                        "normal": {
                            "show": false
                        }
                    },
                    "data": [{
                        "value": 75,
                        "name": '1'
                    }, {
                        "value": 25,
                        "name": '2'
                    }]
                },
                {
                    "name": '',
                    "type": 'pie',
                    "radius": ['52%', '68%'],
                    "avoidLabelOverlap": false,
                    "startAngle": 317,
                    "color": ["#fff", "transparent"],
                    "hoverAnimation": false,
                    "legendHoverLink": false,
                    "clockwise": false,
                    "itemStyle":{
                        "normal":{
                            "borderColor":"transparent",
                            "borderWidth":"20"
                        },
                        "emphasis":{
                            "borderColor":"transparent",
                            "borderWidth":"20"
                        }
                    }
                    ,
                    "z":10,
                    "label": {
                        "normal": {
                            "show": false,
                            "position": 'center'
                        },
                        "emphasis": {
                            "show": true,
                            "textStyle": {
                                "fontSize": '30',
                                "fontWeight": 'bold'
                            }
                        }
                    },
                    "labelLine": {
                        "normal": {
                            "show": false
                        }
                    },
                    "data": [{
                        // "value": (100 - value1) * 266 / 360,
                        "name": ''
                    }, {
                        // "value": 100 - (100 - value1) * 266 / 360,
                        "name": ''
                    }
                    ]
                }

            ]
        };

        // 使用刚指定的配置项和数据显示图表。
//        myChart.setOption(option);
        app3.timeTicket = setInterval(function() {
            var value = parseInt(Math.random() * 55) + 30,
                    value_ = (100 - value) * 266 / 360;
            option3.title.subtext = value + "%";
            option3.series[1].data[0].value = value_;
            option3.series[1].data[1].value = 100 - value_;
            myChart3.setOption(option3, true);
        }, 1000);

		var myChart4 = echarts.init(document.getElementById('pie3'));
        var app4=[];
        // 指定图表的配置项和数据
        var option4 = {
            title: {
                "text": '行为认证拦截率',
                "x": '50%',
                "y": '45%',
                textAlign: "center",
                "textStyle": {
                    "fontWeight": 'normal',
                    "fontSize": 16
                },
                "subtextStyle": {
                    "fontWeight": 'bold',
                    "fontSize": 20,
                    "color": '#e42a0a'
                }
            },
            series: [
                {
                    "name": ' ',
                    "type": 'pie',
                    "radius": ['50%', '70%'],
                    "avoidLabelOverlap": false,
                    "startAngle": 225,
                    "color": ["#e42a0a", "transparent"],
                    "hoverAnimation": false,
                    "legendHoverLink": false,
                    "label": {
                        "normal": {
                            "show": false,
                            "position": 'center'
                        },
                        "emphasis": {
                            "show": true,
                            "textStyle": {
                                "fontSize": '30',
                                "fontWeight": 'bold'
                            }
                        }
                    },
                    "labelLine": {
                        "normal": {
                            "show": false
                        }
                    },
                    "data": [{
                        "value": 75,
                        "name": '1'
                    }, {
                        "value": 25,
                        "name": '2'
                    }]
                },
                {
                    "name": '',
                    "type": 'pie',
                    "radius": ['52%', '68%'],
                    "avoidLabelOverlap": false,
                    "startAngle": 317,
                    "color": ["#fff", "transparent"],
                    "hoverAnimation": false,
                    "legendHoverLink": false,
                    "clockwise": false,
                    "itemStyle":{
                        "normal":{
                            "borderColor":"transparent",
                            "borderWidth":"20"
                        },
                        "emphasis":{
                            "borderColor":"transparent",
                            "borderWidth":"20"
                        }
                    }
                    ,
                    "z":10,
                    "label": {
                        "normal": {
                            "show": false,
                            "position": 'center'
                        },
                        "emphasis": {
                            "show": true,
                            "textStyle": {
                                "fontSize": '30',
                                "fontWeight": 'bold'
                            }
                        }
                    },
                    "labelLine": {
                        "normal": {
                            "show": false
                        }
                    },
                    "data": [{
                        // "value": (100 - value1) * 266 / 360,
                        "name": ''
                    }, {
                        // "value": 100 - (100 - value1) * 266 / 360,
                        "name": ''
                    }
                    ]
                }

            ]
        };

        // 使用刚指定的配置项和数据显示图表。
//        myChart.setOption(option);
        app4.timeTicket = setInterval(function() {
            var value = parseInt(Math.random() * 55) + 30,
                    value_ = (100 - value) * 266 / 360;
            option4.title.subtext = value + "%";
            option4.series[1].data[0].value = value_;
            option4.series[1].data[1].value = 100 - value_;
            myChart4.setOption(option4, true);
        }, 1000);

        var myChart5 = echarts.init(document.getElementById('pie4'));
        var app5=[];
        // 指定图表的配置项和数据
        var option5 = {
            title: {
                "text": '设备指纹拦截率',
                "x": '50%',
                "y": '45%',
                textAlign: "center",
                "textStyle": {
                    "fontWeight": 'normal',
                    "fontSize": 16
                },
                "subtextStyle": {
                    "fontWeight": 'bold',
                    "fontSize": 20,
                    "color": '#e42a0a'
                }
            },
            series: [
                {
                    "name": ' ',
                    "type": 'pie',
                    "radius": ['50%', '70%'],
                    "avoidLabelOverlap": false,
                    "startAngle": 225,
                    "color": ["#e42a0a", "transparent"],
                    "hoverAnimation": false,
                    "legendHoverLink": false,
                    "label": {
                        "normal": {
                            "show": false,
                            "position": 'center'
                        },
                        "emphasis": {
                            "show": true,
                            "textStyle": {
                                "fontSize": '30',
                                "fontWeight": 'bold'
                            }
                        }
                    },
                    "labelLine": {
                        "normal": {
                            "show": false
                        }
                    },
                    "data": [{
                        "value": 75,
                        "name": '1'
                    }, {
                        "value": 25,
                        "name": '2'
                    }]
                },
                {
                    "name": '',
                    "type": 'pie',
                    "radius": ['52%', '68%'],
                    "avoidLabelOverlap": false,
                    "startAngle": 317,
                    "color": ["#fff", "transparent"],
                    "hoverAnimation": false,
                    "legendHoverLink": false,
                    "clockwise": false,
                    "itemStyle":{
                        "normal":{
                            "borderColor":"transparent",
                            "borderWidth":"20"
                        },
                        "emphasis":{
                            "borderColor":"transparent",
                            "borderWidth":"20"
                        }
                    }
                    ,
                    "z":10,
                    "label": {
                        "normal": {
                            "show": false,
                            "position": 'center'
                        },
                        "emphasis": {
                            "show": true,
                            "textStyle": {
                                "fontSize": '30',
                                "fontWeight": 'bold'
                            }
                        }
                    },
                    "labelLine": {
                        "normal": {
                            "show": false
                        }
                    },
                    "data": [{
                        // "value": (100 - value1) * 266 / 360,
                        "name": ''
                    }, {
                        // "value": 100 - (100 - value1) * 266 / 360,
                        "name": ''
                    }
                    ]
                }

            ]
        };

        // 使用刚指定的配置项和数据显示图表。
//        myChart.setOption(option);
        app5.timeTicket = setInterval(function() {
            var value = parseInt(Math.random() * 55) + 30,
                    value_ = (100 - value) * 266 / 360;
            option5.title.subtext = value + "%";
            option5.series[1].data[0].value = value_;
            option5.series[1].data[1].value = 100 - value_;
            myChart5.setOption(option5, true);
        }, 1000);
	};