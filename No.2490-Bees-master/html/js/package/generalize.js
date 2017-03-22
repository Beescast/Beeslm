/**
 * Created by chen on 2016/8/24.
 */
function loadingHCharts(dataTitle, dataTime, data){
    $('#container').highcharts({
        title: {
            text: '',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            type: 'datetime'
        },
        yAxis: {
            title: {
                text: ''
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            xDateFormat: '%Y-%m-%d',
            shared: true
        },
        plotOptions: {
            series: {
                pointStart: Date.UTC(dataTime.getFullYear(), dataTime.getMonth(), dataTime.getDate()),
                pointInterval: 24 * 3600 * 1000
            }
        },
        legend: {
            layout: 'vertical',
            borderWidth: 0
        },
        series: [{
            name: dataTitle,
            data: data
        }
        ]
    });

    //隐藏不必要内容
    var _container = $('#container');
    _container.find('text:contains("Highcharts.com")').hide();
    _container.find('text:contains("' + dataTitle + '")').parent().hide();

    //替换成中文
    _container.find('g.highcharts-xaxis-labels tspan:contains("Jan")').each(function(){ replaceMonth($(this),"Jan");});
    _container.find('g.highcharts-xaxis-labels tspan:contains("Feb")').each(function(){ replaceMonth($(this),"Feb");});
    _container.find('g.highcharts-xaxis-labels tspan:contains("Mar")').each(function(){ replaceMonth($(this),"Mar");});
    _container.find('g.highcharts-xaxis-labels tspan:contains("Apr")').each(function(){ replaceMonth($(this),"Apr");});
    _container.find('g.highcharts-xaxis-labels tspan:contains("May")').each(function(){ replaceMonth($(this),"May");});
    _container.find('g.highcharts-xaxis-labels tspan:contains("Jun")').each(function(){ replaceMonth($(this),"Jun");});
    _container.find('g.highcharts-xaxis-labels tspan:contains("Jul")').each(function(){ replaceMonth($(this),"Jul");});
    _container.find('g.highcharts-xaxis-labels tspan:contains("Aug")').each(function(){ replaceMonth($(this),"Aug");});
    _container.find('g.highcharts-xaxis-labels tspan:contains("Sep")').each(function(){ replaceMonth($(this),"Sep");});
    _container.find('g.highcharts-xaxis-labels tspan:contains("Oct")').each(function(){ replaceMonth($(this),"Oct");});
    _container.find('g.highcharts-xaxis-labels tspan:contains("Nov")').each(function(){ replaceMonth($(this),"Nov");});
    _container.find('g.highcharts-xaxis-labels tspan:contains("Dec")').each(function(){ replaceMonth($(this),"Dec");});
}

function replaceMonth(obj, value){
    switch (value){
        case "Jan" :  obj.text(obj.text().replace(value, "一月")); break;
        case "Feb" : obj.text(obj.text().replace(value, "二月")); break;
        case "Mar" : obj.text(obj.text().replace(value, "三月")); break;
        case "Apr" : obj.text(obj.text().replace(value, "四月")); break;
        case "May" : obj.text(obj.text().replace(value, "五月")); break;
        case "Jun" : obj.text(obj.text().replace(value, "六月")); break;
        case "Jul" : obj.text(obj.text().replace(value, "七月")); break;
        case "Aug" : obj.text(obj.text().replace(value, "八月")); break;
        case "Sep" : obj.text(obj.text().replace(value, "九月")); break;
        case "Oct" : obj.text(obj.text().replace(value, "十月")); break;
        case "Nov" : obj.text(obj.text().replace(value, "十一月")); break;
        case "Dec" : obj.text(obj.text().replace(value, "十二月")); break;
    }
}

//ajax加载数据
function ajaxLoadingData(dataTitle, dataTime){
    //
    $.ajax({
        url : "json/generalize.json",
        type : 'GET',
        data : $.param({ 'title' : dataTitle, 'dataTime' : dataTime }),
        dataType : 'JSON',
        success : function(data){
            loadingHCharts(dataTitle, new Date(dataTime), data.resultList);
        }
    });
}

$(function(){
    //修正高度
    fixHeight($('div.liveData-list'));

    var _dataTime = $('#dataTime');         //时间控件DOM对象
    //初始化数据
    ajaxLoadingData($('#dataTitle').val(), new Date());

    _dataTime.datepicker();                //加载日期控件

    //标题选择
    $(document).on('click', '#title-list a', function(){
        $('#title-list a').removeClass('act');
        $(this).addClass('act');

        $('#dataTitle').val($(this).data('value'));
        $('div.container-title h2').text($(this).data('value'));

        //加载数据
        ajaxLoadingData($(this).data('value'), _dataTime.val());
    });

    //选择日期事件
    _dataTime.change(function(){
        ajaxLoadingData($('#dataTitle').val(), $(this).val());
    });
});