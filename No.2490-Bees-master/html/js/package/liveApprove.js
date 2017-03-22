/**
 * Created by chen on 2016/8/23.
 */
var tempOffset = 0;
/**
 * ajax数据处理
 * @param element   分页对象
 * @param data      ajax url参数集
 * @param url       ajax 请求地址
 * @private
 */
  //var serverUrl ="http://123.56.239.203:8080/bees";

function _ajaxLoadLiveData(element, data, url){
    tempOffset = data.offset;
    $.ajax({
        url : url,
        type : 'GET',
        data : $.param(data),
        dataType : 'JSON',
        success : function(data){
            data.offset = tempOffset;           //临时记录当前页， 后续后台传送offset存储data中
            var arrDate = {resultList:[data.data.response.result]};
           
            var _html = template('tpl-live-list', arrDate);
            $('#live-list').html(_html).fnPageList();

            //重新设置分页
            element.setOptions({
                _total : data.total ,               //设置总数据条数
                _rows : data.rows,                  //一页显示行数
                _offset : data.offset               //当前所在页, 从0(第1页)开始
            });
        }
    });
}

//加载主播列表信息
function loadingLiveList(){
    var sessionKey = getCookieValue("sessionKey");

    $('#live-list').myPaginate({
        _parent : $('#live-list').parent().parent(),
        _ajaxUrl : serverUrl+'/user/info?sessionKey='+sessionKey,      //数据接口
        _ajaxData : {                           //附加ajax传递参数
            _templateId : 'testId'
        },
        _ajax : _ajaxLoadLiveData
    });
}
$(function(){
    //修正高度
    fixHeight($('div.live-list'));

    //加载主播列表
    loadingLiveList();
});