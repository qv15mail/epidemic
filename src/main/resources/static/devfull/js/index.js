$(function () {
    echart_1();
    echart_2();
    echart_3();
    echart_4();

    function echart_1() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_1'));
        option = {
            title: {
                text: '本月设备状态统计',
                top: 35,
                left: 20,
                textStyle: {
                    fontSize: 18,
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)",

            },
            legend: {
                right: 20,
                top: 35,
                data: ['故障', '正常'],
                textStyle: {
                    color: '#fff'
                }
            },
            series: [{
                name: '设备状态',
                type: 'pie',
                radius: ['0', '60%'],
                center: ['50%', '60%'],
                color: ['#e72325', '#98e002', '#2ca3fd'],
                label: {
                    normal: {
                        formatter: '{b}\n{d}%'
                    },

                },
                data: [{
                        value: 6,
                        name: '故障'
                    },
                    {
                        value: 50,
                        name: '正常',
                        selected: true
                    }
                ]
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize", function () {
            myChart.resize();
        });
    }

    function echart_2() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_2'));
        var data = {
            id: 'multipleBarsLines',
            title: '2018年前半年检测统计',
            legendBar: ['正面占比', '中立占比', '负面占比'],
            symbol: '', //数值是否带百分号        --默认为空 ''
            legendLine: ['同期对比'],
            xAxis: ['一月', '二月', '三月', '四月', '五月', '六月'],
            yAxis: [
                [8, 10, 10, 11, 4, 13]
            ],
            lines: [
                [10, 10, 9, 11, 7, 4]
            ],
            barColor: ['#3FA7DC', '#7091C4', '#5170A2'], //柱子颜色 必填参数
            lineColor: ['#D9523F'], // 折线颜色

        };
        /////////////end/////////

        var myData = (function test() {
            var yAxis = data.yAxis || [];
            var lines = data.lines || [];
            var legendBar = data.legendBar || [];
            var legendLine = data.legendLine || [];
            var symbol = data.symbol || ' ';
            var seriesArr = [];
            var legendArr = [];
            yAxis && yAxis.forEach((item, index) => {
                legendArr.push({
                    name: legendBar && legendBar.length > 0 && legendBar[index]
                });
                seriesArr.push({
                    name: legendBar && legendBar.length > 0 && legendBar[index],
                    type: 'bar',
                    barGap: '0.5px',
                    data: item,
                    barWidth: data.barWidth || 12,
                    label: {
                        normal: {
                            show: true,
                            formatter: '{c}' + symbol,
                            position: 'top',
                            textStyle: {
                                color: '#fff',
                                fontStyle: 'normal',
                                fontFamily: '微软雅黑',
                                textAlign: 'left',
                                fontSize: 11,
                            },
                        },
                    },
                    itemStyle: { //图形样式
                        normal: {
                            barBorderRadius: 4,
                            color: data.barColor[index]
                        },
                    }
                });
            });

            lines && lines.forEach((item, index) => {
                legendArr.push({
                    name: legendLine && legendLine.length > 0 && legendLine[index]
                })
                seriesArr.push({
                    name: legendLine && legendLine.length > 0 && legendLine[index],
                    type: 'line',
                    data: item,
                    itemStyle: {
                        normal: {
                            color: data.lineColor[index],
                            lineStyle: {
                                width: 3,
                                type: 'solid',
                            }
                        }
                    },
                    label: {
                        normal: {
                            show: false, //折线上方label控制显示隐藏
                            position: 'top',
                        }
                    },
                    symbol: 'circle',
                    symbolSize: 10
                });
            });

            return {
                seriesArr,
                legendArr
            };
        })();


        option = {
            title: {
                show: true,
                top: '10%',
                left: '3%',
                text: data.title,
                textStyle: {
                    fontSize: 18,
                    color: '#fff'
                },
                subtext: data.subTitle,
                link: ''
            },
            tooltip: {
                trigger: 'axis',
                formatter: function (params) {
                    var time = '';
                    var str = '';
                    for (var i of params) {
                        time = i.name.replace(/\n/g, '') + '<br/>';
                        if (i.data == 'null' || i.data == null) {
                            str += i.seriesName + '：无数据' + '<br/>'
                        } else {
                            str += i.seriesName + '：' + i.data + symbol + '%<br/>'
                        }

                    }
                    return time + str;
                },
                axisPointer: {
                    type: 'none'
                },
            },
            legend: {
                right: data.legendRight || '30%',
                top: '12%',
                right: '5%',
                itemGap: 16,
                itemWidth: 10,
                itemHeight: 10,
                data: myData.legendArr,
                textStyle: {
                    color: '#fff',
                    fontStyle: 'normal',
                    fontFamily: '微软雅黑',
                    fontSize: 12,
                }
            },
            grid: {
                x: 30,
                y: 80,
                x2: 30,
                y2: 60,
            },
            xAxis: {
                type: 'category',
                data: data.xAxis,
                axisTick: {
                    show: false,
                },

                axisLine: {
                    show: true,
                    lineStyle: {
                        color: '#1AA1FD',
                    },
                    symbol: ['none', 'arrow']
                },
                axisLabel: {
                    show: true,
                    interval: '0',
                    textStyle: {
                        lineHeight: 16,
                        padding: [2, 2, 0, 2],
                        height: 50,
                        fontSize: 12,
                    },
                    rich: {
                        Sunny: {
                            height: 50,
                            // width: 60,
                            padding: [0, 5, 0, 5],
                            align: 'center',
                        },
                    },
                    formatter: function (params, index) {
                        var newParamsName = "";
                        var splitNumber = 5;
                        var paramsNameNumber = params && params.length;
                        if (paramsNameNumber && paramsNameNumber <= 4) {
                            splitNumber = 4;
                        } else if (paramsNameNumber >= 5 && paramsNameNumber <= 7) {
                            splitNumber = 4;
                        } else if (paramsNameNumber >= 8 && paramsNameNumber <= 9) {
                            splitNumber = 5;
                        } else if (paramsNameNumber >= 10 && paramsNameNumber <= 14) {
                            splitNumber = 5;
                        } else {
                            params = params && params.slice(0, 15);
                        }

                        var provideNumber = splitNumber; //一行显示几个字
                        var rowNumber = Math.ceil(paramsNameNumber / provideNumber) || 0;
                        if (paramsNameNumber > provideNumber) {
                            for (var p = 0; p < rowNumber; p++) {
                                var tempStr = "";
                                var start = p * provideNumber;
                                var end = start + provideNumber;
                                if (p == rowNumber - 1) {
                                    tempStr = params.substring(start, paramsNameNumber);
                                } else {
                                    tempStr = params.substring(start, end) + "\n";
                                }
                                newParamsName += tempStr;
                            }

                        } else {
                            newParamsName = params;
                        }
                        params = newParamsName;
                        return '{Sunny|' + params + '}';
                    },
                    color: '#1AA1FD',
                },

            },
            yAxis: {
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: '#1AA1FD',
                    },
                    symbol: ['none', 'arrow']
                },
                type: 'value',
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: false
                },
                splitLine: {
                    show: false,
                    lineStyle: {
                        color: '#1AA1FD',
                        type: 'solid'
                    },
                }
            },
            series: myData.seriesArr
        }
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize", function () {
            myChart.resize();
        });
    }

    function echart_3() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_3'));

        function showProvince() {
            var geoCoordMap = {
                '河池': [108.085179,24.700488],
                '柳州': [109.412578,24.354875],
                '梧州': [111.323462,23.478238],
                '南宁': [108.359656,22.81328],
                '北海': [109.171374,21.477419],
                '崇左': [107.347374,22.377503]
            };
            var data = [{
                    name: '河池',
                    value: 100
                },
                {
                    name: '柳州',
                    value: 100
                },
                {
                    name: '梧州',
                    value: 100
                },
                {
                    name: '北海',
                    value: 100
                },
                {
                    name: '崇左',
                    value: 100
                }
            ];
            var max = 480,
                min = 9; // todo 
            var maxSize4Pin = 100,
                minSize4Pin = 20;
            var convertData = function (data) {
                var res = [];
                for (var i = 0; i < data.length; i++) {
                    var geoCoord = geoCoordMap[data[i].name];
                    if (geoCoord) {
                        res.push({
                            name: data[i].name,
                            value: geoCoord.concat(data[i].value)
                        });
                    }
                }
                return res;
            };

            myChart.setOption(option = {
                title: {
                    text: '设备分布蜘蛛网',
                    subtext: '',
                    x: 'center',
                    textStyle: {
                        color: '#FFF'
                    },
                    left: '6%',
                    top: '10%'
                },
				radar: {
					// shape: 'circle',
					name: {
						textStyle: {
							color: '#fff',
							backgroundColor: '#999',
							borderRadius: 3,
							padding: [3, 5]
					   }
					},
					indicator: [
					   { name: '数据库', max: 6500},
					   { name: '餐饮POS', max: 16000},
					   { name: '水控', max: 30000},
					   { name: '门禁', max: 38000},
					   { name: '应用/服务', max: 52000},
					   { name: '前置', max: 25000}
					]
				},
				series: [{
					type: 'radar',
					// areaStyle: {normal: {}},
					data : [
						 {
							value : [5000, 14000, 28000, 31000, 42000, 21000],
							name : '实际数量'
						}
					]
				}]
            });
        }
        showProvince();

        // 使用刚指定的配置项和数据显示图表。
        // myChart.setOption(option);
        window.addEventListener("resize", function () {
            myChart.resize();
        });
    }

    function echart_4() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_4'));
        var data = [70, 34, 60, 78, 69];
        var titlename = ['1号机', '2号机', '3号机', '4号机', '5号机'];
        var valdata = [702, 406, 664, 793, 505];
        var myColor = ['#1089E7', '#F57474', '#56D0E3', '#F8B448', '#8B78F6'];
        option = {
            title: {
                text: '设备使用频率',
                x: 'center',
                textStyle: {
                    color: '#FFF'
                },
                left: '6%',
                top: '10%'
            },
            //图标位置
            grid: {
                top: '20%',
                left: '32%'
            },
            xAxis: {
                show: false
            },
            yAxis: [{
                show: true,
                data: titlename,
                inverse: true,
                axisLine: {
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    color: '#fff',
                    formatter: (value, index) => {
                        return ['{lg|${index+1}}'+ '{title|' + value + '} '].join('\n')
                    },
                    rich: {
                        lg: {
                            backgroundColor: '#339911',
                            color: '#fff',
                            borderRadius: 15,
                            // padding: 5,
                            align: 'center',
                            width: 15,
                            height: 15
                        },
                    }
                },


            }, {
                show: true,
                inverse: true,
                data: valdata,
                axisLabel: {
                    textStyle: {
                        fontSize: 12,
                        color: '#fff',
                    },
                },
                axisLine: {
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },

            }],
            series: [{
                name: '条',
                type: 'bar',
                yAxisIndex: 0,
                data: data,
                barWidth: 10,
                itemStyle: {
                    normal: {
                        barBorderRadius: 20,
                        color: function(params) {
                            var num = myColor.length;
                            return myColor[params.dataIndex % num]
                        },
                    }
                },
                label: {
                    normal: {
                        show: true,
                        position: 'inside',
                        formatter: '{c}%'
                    }
                },
            }, {
                name: '框',
                type: 'bar',
                yAxisIndex: 1,
                barGap: '-100%',
                data: [100, 100, 100, 100, 100],
                barWidth: 15,
                itemStyle: {
                    normal: {
                        color: 'none',
                        borderColor: '#00c1de',
                        borderWidth: 3,
                        barBorderRadius: 15,
                    }
                }
            }, ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        // window.addEventListener("resize", function () {
        //     myChart.resize();
        // });
    }
});