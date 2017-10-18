//(function($){
//  var labelLength = 1;
//  var data = {
//    labels: _.range(1,labelLength),
//    datasets: [
//        {
//            label: "My First dataset",
//            fillColor: "rgba(255,69,0,0.2)",
//            strokeColor: "rgba(255,69,0,1)",
//            pointColor: "rgba(255,69,0,1)",
//            pointStrokeColor: "#fff",
//            pointHighlightFill: "#fff",
//            pointHighlightStroke: "rgba(220,220,220,1)",
//            data: _.range(1,labelLength)
//        }
//    ]
//  };
//  $(function(){
//    console.log("onload");
//    var ctx = document.getElementById("myChart").getContext("2d");
//    var myNewChart = new Chart(ctx).Line(data,{datasetFill: false});
//        var dataset0 = myNewChart.datasets[0];
//    var timer = setInterval(function(){
//            for(i=0; i < dataset0.points.length; i+=1){
//              dataset0.points[i].value = (i+1 != dataset0.points.length) ?
//                    dataset0.points[i+1].value:_.random(0,1);;
//            }
//        myNewChart.update();
//    },60000);
//  })
//
//})(jQuery);
var	datapos = 10;
var	onoff=1;
var lineChartData = {
	labels : ["1番目","2番目","3番目","4番目","5番目","6番目","7番目","8番目","9番目","10番目"],
	bezierCurve: false,
	datasets : [
		{
			label: "1本目",
			fillColor : "rgba(255,255,255,0.2)",
			strokeColor : "red",
			pointColor : "red",
			data : []
		},
		{
			label: "2本目",
			fillColor : "rgba(255,255,255,0.2)",
			strokeColor : "blue",
			pointColor : "blue",
			data : []
		}
	]
};



window.onload = function(){

	//	初期データセット
	for(i=0 ; i < lineChartData.datasets.length ; i ++)
	{
		for(j=0;j< datapos;j++)
		{
			lineChartData.datasets[i].data[j] =  Math.round(Math.random()*100);
		}
	}

	//	チャート生成
	var ctx = document.getElementById("canvas").getContext("2d");
	window.myChart = new Chart(ctx).Line(lineChartData, {
		responsive : true
	});

	//	１秒ごとに繰り返し
	setTimeout("mycheck()",1000);

}

function update_temp()
{
	for(i=0 ; i < lineChartData.datasets.length ; i ++)
	{
		for(j=0;j< datapos;j++)
		{
			lineChartData.datasets[i].data[j] =  Math.round(Math.random()*100);
		}
	}

	//	チャート生成
	var ctx = document.getElementById("canvas").getContext("2d");
	window.myChart = new Chart(ctx).Line(lineChartData, {
		responsive : true
	});
	setTimeout("mytemp()",60000);
}
function update_temp_bus(){
    	for(i=0 ; i < lineChartData.datasets.length ; i ++)
    	{
    		for(j=0;j< datapos;j++)
    		{
    			lineChartData.datasets[i].data[j] =  Math.round(Math.random()*100);
    		}
    	}

    	//	チャート生成
    	var ctx = document.getElementById("canvas").getContext("2d");
    	window.myChart = new Chart(ctx).Line(lineChartData, {
    		responsive : true
    	});
    	setTimeout("mytemp_bus()",60000);
}
function update_hs_value_bus(){
	for(i=0 ; i < lineChartData.datasets.length ; i ++)
	{
		for(j=0;j< datapos;j++)
		{
			lineChartData.datasets[i].data[j] =  Math.round(Math.random()*100);
		}
	}

	//	チャート生成
	var ctx = document.getElementById("canvas").getContext("2d");
	window.myChart = new Chart(ctx).Line(lineChartData, {
		responsive : true
	});
	setTimeout("my_hs_value_bus()",60000);
}
function update_hs_value(){
	for(i=0 ; i < lineChartData.datasets.length ; i ++)
	{
		for(j=0;j< datapos;j++)
		{
			lineChartData.datasets[i].data[j] =  Math.round(Math.random()*100);
		}
	}

	//	チャート生成
	var ctx = document.getElementById("canvas").getContext("2d");
	window.myChart = new Chart(ctx).Line(lineChartData, {
		responsive : true
	});
	setTimeout("my_hs_value()",60000);
}



function mytemp()
{
    myaddData =[];
    for(i=0;i<lineChartData.datasets.length;i++)
    	{
    		myaddData[i]= Math.round(Math.random()*100);
    	}
    	window.myChart.addData(myaddData,(datapos+1)+"番目");
    	datapos++;

    		setTimeout("mytemp()",60000);
}


function mytemp_bus(){
    myaddData =[];
    for(i=0;i<lineChartData.datasets.length;i++)
    	{
    		myaddData[i]= Math.round(Math.random()*100);
    	}
    	window.myChart.addData(myaddData,(datapos+1)+"番目");
    	datapos++;

    	setTimeout("mytemp_bus()",60000);
}

function my_hs_value(){
    myaddData =[];
    for(i=0;i<lineChartData.datasets.length;i++)
    	{
    		myaddData[i]= Math.round(Math.random()*100);
    	}
    	window.myChart.addData(myaddData,(datapos+1)+"番目");
    	datapos++;

    	etTimeout("my_hs_value()",60000);
}
function my_hs_value_bus(){
    myaddData =[];
    for(i=0;i<lineChartData.datasets.length;i++)
    	{
    		myaddData[i]= Math.round(Math.random()*100);
    	}
    	window.myChart.addData(myaddData,(datapos+1)+"番目");
    	datapos++;

    	setTimeout("my_hs_value_bus()",60000);
}

function stopsw()
 {
 	if ( onoff == 1 )
 	{
 		onoff = 0;
 	}
 	else
 	{
 		onoff = 1;
 		setTimeout("mycheck()",1000);
 	}
 }

