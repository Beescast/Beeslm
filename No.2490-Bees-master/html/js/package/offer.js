/**
 * Created by chen on 2016/8/15.
 */
//加载banner信息


function loadingStoreBanner(){
    $.ajax({
        type:"get",
        url:"json/store_banner.json",
        dataType:"json",
        success:function(data){
            var _html = template('tpl-store-banner', data);
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
function _ajaxLoadOfferData(element, data, url){
    tempOffset = data.offset;
        data.rows=10
    tempRows = data.rows;

    data.limit= data.rows;
    data.start = (data.offset)*data.rows;

    $.ajax({
        url : url,
        type : 'GET',
        data : $.param(data),
        dataType : 'JSON',
        success : function(data){
            data.offset = tempOffset;           //临时记录当前页， 后续后台传送offset存储data中
            data.rows =tempRows;
            var _html = template('tpl-offer-list', data.data.response);
            $('#offer-list').html(_html).fnPageList();

            //重新设置分页
            element.setOptions({
                _total : data.data.response.total  ,               //设置总数据条数
                _rows : data.rows,                  //一页显示行数
                _offset : data.offset               //当前所在页, 从0(第1页)开始
            });
        }
    });
}
//获取url参数


function getUrlRequest(name) {
    var url = location.search; //获取url中"?"符后的字串

    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        if (str.indexOf("&") != -1) {
      
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
            }
        } else {
            if(unescape(str.split("=")[0])==name)
               return  unescape(str.split("=")[1]);
        }
    }

}

//加载任务信息
function loadingOfferList(){
   var sign = getUrlParam("sign");
   var status = getUrlParam("status");
   var catId = getUrlParam("catId");

    $('#offer-list').myPaginate({
        _parent : $('#modePage'),
        _ajaxUrl : serverUrl+'/task/list',      //数据接口
        _ajaxData : {  
            sessionKey :getCookieValue("sessionKey"),                         //附加ajax传递参数
            sign :sign,
            status :status,
            catId : catId,
            sign : sign

        },
        _ajax : _ajaxLoadOfferData
    });
}

//加载头部信息
function loadingOfferTop(){
       if(getUrlParam("sign") != null){
       var  m  = "sign="+getUrlParam("sign");
    }else{
        var m = "1 =1";
    }
     if(getUrlParam("status") != null){
       var  n  = "status="+getUrlParam("status");
    }else{
        var n = "1 =1";
    }
  
    
    $.ajax({
        url : serverUrl+'/task/catList',
        type : 'GET',
        dataType : 'JSON',
        success : function(data){
            $.each(data.data.response.resultList,function(i,v){
               var a = "?catId="+v['id'];
                v['href'] = a+"&"+m+"&"+n;
            })
            var _html = template('tpl-offer-type', data.data.response);
            $('#offer-top').html(_html);
        }
    });
}
//去支付
function moneyReward(){
    $('#moneyReward').iDialog();

    $(document).on('click', 'a[data-target="#moneyReward"]', function(){
        $('#taskId').val($(this).data('id')); 
        var sessionKey = getCookieValue("sessionKey");
          $('#sessionKey').val(sessionKey);                //订单价格
        //$('#lastPackagePrice').text();                                //钱包余额, ajax获取
    });

    $('form.moneyReward').validate({
        errorLabelContainer: $('form.moneyReward').find("div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler : function(form){
              $(form).ajaxSubmit({
                url:serverUrl+"/task/addSign",
                type:'post',
                success:function(data){
                    
                    if(data.code ==200){
                        
                        alert('报名成功');
                        window.location.href="offer.html";
                    }else{
                        alert("报名失败");
                        return false
                    }
                }
            });
        },
    });
}

$(function(){
    //offer top
    loadingOfferTop();
    moneyReward();
    //offer list
    loadingOfferList()
});