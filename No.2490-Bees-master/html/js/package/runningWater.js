/**
 * Created by Administrator on 2016/8/20.
 */
var tempOffset = 0;
/**
 * ajax数据处理
 * @param element   分页对象
 * @param data      ajax url参数集
 * @param url       ajax 请求地址
 * @private
 */


function getLocalTime(nS) { 
    return new Date(parseInt(nS/1000) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');  
} 


function _ajaxLoadWaterData(element, data, url){
    tempOffset = data.offset;
      tempRows = data.rows;

    data.limit= data.rows;
    data.start = data.offset;
    $.ajax({
        url : url,
        type : 'GET',
        data : $.param(data),
        dataType : 'JSON',
        success : function(data){
            data.offset = tempOffset;           //临时记录当前页， 后续后台传送offset存储data中
            data.rows =tempRows;
             $.each(data.data.response.resultList,function(i,v){
               
                v['addTime'] = getLocalTime(v['addTime']);
            })
            var _html = template('tpl-water-list', data.data.response);
            $('#water-list').html(_html).fnPageList();

            //重新设置分页
            element.setOptions({
                _total : data.data.response.total ,               //设置总数据条数
                _rows : data.rows,                  //一页显示行数
                _offset : data.offset               //当前所在页, 从0(第1页)开始
            });
        }
    });
}

//加载商店信息
function loadingWaterList(){
    var sessionKey = getCookieValue("sessionKey");

    $('#water-list').myPaginate({
        _parent : $('#water-list').parent().parent(),
        _ajaxUrl : serverUrl+'/wallet/list?sessionKey='+sessionKey,      //数据接口
        _ajaxData : {                           //附加ajax传递参数
            _templateId : 'testId'
        },
        _ajax : _ajaxLoadWaterData
    });
}

$(function(){
    //修正高度
    fixHeight($('div.water-list'));

    //加载数据
    loadingWaterList();
});