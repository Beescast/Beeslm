/**
 * Created by jiqiang on 2016/8/14.
 */
//加载banner信息
var serverUrl ="http://bees.beeslm.com"
function loadingStoreBanner(){
    $.ajax({
        type:"get",
        url:serverUrl+"/banner/list?page=shop&start=0&limit=10&position=0",
        dataType:"json",
        success:function(data){
            var _html = template('tpl-store-banner', data.data.response);
            var _indexBanner = $('#carousel-store-banner');
            _indexBanner.html(_html);

            //手动调取轮播图
            _indexBanner.carousel();
        }
    });
}

var tempOffset = 0;
/**
 * ajax数据处理
 * @param element   分页对象
 * @param data      ajax url参数集
 * @param url       ajax 请求地址
 * @private
 */
function _ajaxLoadStoreData(element, data, url){
    tempOffset = data.offset;
          // data.rows=2
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

            var _html = template('tpl-store-list', data.data.response);
            $('#store_list').html(_html).fnPageList();

            //重新设置分页
            element.setOptions({
                _total : data.total ,               //设置总数据条数
                _rows : data.rows,                  //一页显示行数
                _offset : data.offset               //当前所在页, 从0(第1页)开始
            });
        }
    });
}

//加载商店信息
function loadingStoreList(){
    $('#store_list').myPaginate({
        _ajaxUrl : serverUrl+'/product/list',      //数据接口
        _ajaxData : {                           //附加ajax传递参数
            _templateId : 'testId'
        },
        _ajax : _ajaxLoadStoreData
    });
}

$(function(){
    //加载banner数据
    loadingStoreBanner();

    //加载商店信息
    loadingStoreList();
});