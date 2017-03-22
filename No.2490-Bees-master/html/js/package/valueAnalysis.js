/**
 * Created by jiqiang on 2016/8/24.
 */
var analysisStartTime, analysisEndTime;

var tempOffset = 0;
/**
 * ajax数据处理
 * @param element   分页对象
 * @param data      ajax url参数集
 * @param url       ajax 请求地址
 * @private
 */
function _ajaxLoadAnalysisData(element, data, url){
    tempOffset = data.offset;
    $.ajax({
        url : url,
        type : 'GET',
        data : $.param(data),
        dataType : 'JSON',
        success : function(data){
            data.offset = tempOffset;           //临时记录当前页， 后续后台传送offset存储data中

            var _html = template('tpl-analysis', data);
            $('#analysisWrap').html(_html).fnPageList();

            //重新设置分页
            element.setOptions({
                _total : data.total ,               //设置总数据条数
                _rows : data.rows,                  //一页显示行数
                _offset : data.offset               //当前所在页, 从0(第1页)开始
            });
        }
    });
}

//加载分析数据
function loadingAnalysisList(){
    if('undefined' === typeof analysisStartTime.get(0) || 'undefined' === typeof analysisEndTime.get(0)) return;
    $('#analysisWrap').myPaginate({
        _parent : $('#analysisWrap').parent().parent(),
        _ajaxUrl : 'json/valueAnalysis.json',      //数据接口
        _ajaxData : {                           //附加ajax传递参数
            startTime : analysisStartTime.val(),
            endTime : analysisEndTime.val()
        },
        _ajax : _ajaxLoadAnalysisData
    });
}

$(function(){
    //修正高度
    fixHeight($('div.liveData-list'));

    analysisStartTime = $('#analysisStartTime').datepicker({
        onClose : function(selectDate){
            analysisEndTime.datepicker( "option", "minDate", selectDate );
            loadingAnalysisList();
        }
    });                //加载日期控件
    analysisEndTime = $('#analysisEndTime').datepicker({
        onClose : function(selectDate){
            analysisStartTime.datepicker( "option", "maxDate", selectDate );
            loadingAnalysisList();
        }
    });

    loadingAnalysisList();      //获取数据
});