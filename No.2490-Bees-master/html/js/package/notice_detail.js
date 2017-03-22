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
//var serverUrl ="http://123.56.239.203:8080/bees"

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}



function loadingNoticeInfo(){
    var notice_id = GetQueryString('notice_id');

    $.ajax({
        type: "get",
        url: serverUrl+"/notice/info?id="+notice_id,
        dataType: "json",
        success: function (data) {
            var _html = template('tpl-lord-notice', data.data.response.result);

            var _lordBanner = $('#lord-notice');
            _lordBanner.html(_html);

            _lordBanner.carousel();
        }
    });
}


$(function(){
    //加载列表数据
    
    loadingNoticeInfo();
});