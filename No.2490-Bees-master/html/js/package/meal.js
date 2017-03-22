/**
 * Created by chen on 2016/8/16.
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

function _ajaxLoadMealData(element, data, url){
    tempOffset = data.offset;
     data.limit= data.rows;
    data.start = data.offset;
     tempRows = data.rows;
    $.ajax({
        url : url,
        type : 'GET',
        data : $.param(data),
        dataType : 'JSON',
        success : function(data){
             data.offset = tempOffset;           //临时记录当前页， 后续后台传送offset存储data中
             data.rows =tempRows;
            var _html = template('tpl-mealComment', data.data.response);
            $('#mealComment').html(_html).fnPageList();

            //重新设置分页
            element.setOptions({
                _total : data.data.response.total,               //设置总数据条数
                _rows : data.rows,                  //一页显示行数
                _offset : data.offset               //当前所在页, 从0(第1页)开始
            });
        }
    });
}

//加载套餐信息
function loadingMealList(){
    $('#mealComment').myPaginate({
        _ajaxUrl : serverUrl+'/comment/list',      //数据接口
        _ajaxData : {                           //附加ajax传递参数
            _templateId : 'testId'
        },
        _ajax : _ajaxLoadMealData
    });
}

//套餐详情
function loadingPriceInfo(){

    $.ajax({
        type: "get",
        url: serverUrl+"/package/price?id=1",
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
    loadingMealList();
    loadingPriceInfo();
});