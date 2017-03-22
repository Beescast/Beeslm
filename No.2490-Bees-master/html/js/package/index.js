/**
 * Created by chen on 2016/8/9.
 */
//banner信息

//var serverUrl ="http://123.56.239.203:8080/bees"
// http://123.56.239.203:8080/bees/banner/list


function loadingBanner(){
    $.ajax({
        type:"get",
        url:serverUrl+"/banner/list?page=index&start=0&limit=10",
        dataType:"json",
        success:function(data){
            var _html = template('tpl-index-banner', data.data.response);
            var _indexBanner = $('#carousel-index-banner');
            _indexBanner.html(_html);

            //手动调取轮播图
            _indexBanner.carousel();
        }
    });
}

//主播评论信息
function loadingLordInfo(){
    $.ajax({
        type: "get",
        url: serverUrl+"/comment/list?start=0&limit=3",
        dataType: "json",
        success: function (data) {
            var _html = template('tpl-lord-index-banner', data.data.response);
            var _lordBanner = $('#lord-index-banner');
            _lordBanner.html(_html);

            _lordBanner.carousel();
        }
    });
}

$(function(){
    //加载banner信息
    loadingBanner();
    //加载首页主播评论信息
    loadingLordInfo();
});