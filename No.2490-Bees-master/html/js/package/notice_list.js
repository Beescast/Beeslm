/**
 * Created by jiqiang on 2016/8/15.
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


function _ajaxLoadNoticeData(element, data, url){
    tempOffset = data.offset;
    data.rows=15
    tempRows = data.rows;

    data.limit= data.rows;
    data.start = data.offset*data.rows;
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
            var _html = template('tpl-notice-list', data.data.response);
            $('#notice-list').html(_html).fnPageList();
            console.log(data);
            //重新设置分页
            element.setOptions({
                _total : data.data.response.total ,               //设置总数据条数
                _rows : data.rows,                  //一页显示行数
                _offset : data.offset               //当前所在页, 从0(第1页)开始
            });
        }
    });
}

//加载公告信息
function loadingNoticeList(){
    $('#notice-list').myPaginate({
        _parent : $('#modePage'),
        _showFirst : false,
        _showLast : false,
        _ajaxUrl : serverUrl+'/notice/list',      //数据接口
        _ajaxData : {                           //附加ajax传递参数
            _templateId : 'testId'
        },
        _ajax : _ajaxLoadNoticeData
    });
}



$(function(){
    //加载列表数据
    loadingNoticeList();
});