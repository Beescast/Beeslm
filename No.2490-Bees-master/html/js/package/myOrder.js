/**
 * Created by jiqiang on 2016/8/17.
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
function _ajaxLoadOrderData(element, data, url){
     tempOffset = data.offset;

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
               
                v['submitTime'] = getLocalTime(v['submitTime']);
            })
            var _html = template('tpl-order-list', data.data.response);
            $('#order-list').html(_html).fnPageList();

            //重新设置分页
            element.setOptions({
                _total : data.data.response.total ,               //设置总数据条数
                _rows : data.rows,                  //一页显示行数
                _offset : data.offset               //当前所在页, 从0(第1页)开始
            });
        }
    });
}

//加载订单信息
function loadingOrderList(){
    var sessionKey = getCookieValue("sessionKey");
    $('#order-list').myPaginate({
        _parent : $('#order-list').parents('.personal-list'),
        _ajaxUrl : serverUrl+'/order/list?sessionKey='+sessionKey,      //数据接口
        _ajaxData : {                           //附加ajax传递参数
            _templateId : 'testId'
        },
        _ajax : _ajaxLoadOrderData
    });
}

//去评价
function commentOrder(){
    $('#orderInfo').iDialog();
    $(document).on('click', 'a[data-target=#orderInfo]', function(){

        $('#dialog_orderText').text($(this).data('id'));
        $('#dialog_orderId').val($(this).data('id'));
        $("#paname").text($(this).data('name'))
    });

    $('form.order-say').validate({
        errorLabelContainer: $('form.order-say').find("div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler : function(form){

             $(form).ajaxSubmit({
                url:serverUrl+"/comment/addComent",
                type:'post',
                success:function(data){
                    
                    if(data.code ==200){
                        
                        window.location.href="personal.html";
                    }else{
                        alert(data.messages.message);
                        return false
                    }
                }
            });

        },
        rules : {
            orderSayMessage : { required : true, maxlength : 200, minlength : 2 }
        },
        messages : {
            orderSayMessage : { required : '请输入评语', maxlength : '最多可输入200个字符', minlength : '至少输入2个字符' }
        }
    });
}

//去支付
function packagePay(){
    $('#packagePay').iDialog();

    $(document).on('click', 'a[data-target="#packagePay"]', function(){
       
        $('#viewPayPrices').text($(this).data('price'));                 //订单价格
        //$('#lastPackagePrice').text();                                //钱包余额, ajax获取

        $('#payPriceInput').val($(this).data('price'));             //隐藏域，订单价格
        $('#payPriceId').val($(this).data('id'));                   //隐藏域，订单编号
    });

    $('form.packagePay').validate({
        errorLabelContainer: $('form.packagePay').find("div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler : function(form){
              $(form).ajaxSubmit({
                url:serverUrl+"/pay/wallet",
                type:'post',
                success:function(data){
                    
                    if(data.code ==200){
                        window.location.href="personal.html";
                    }else{
                        alert(data.messages.message);
                        return false
                    }
                }
            });
        },
        rules : {
            payPassword : { required : true }
        },
        messages : {
            payPassword : { required : '请输入支付密码' }
        }
    });
}
//去支付
function WXPay(){
    $('#WXPay').iDialog();

    $(document).on('click', 'a[data-target="#WXPay"]', function(){
         $('#viewPayPrice').text($(this).data('price')); 
         var sessionKey = getCookieValue("sessionKey");
         $('#identifyImage').attr("src","http://www.beeslm.com:8070/pay/pay.php?id="+$(this).data('id')+"&type=package&sessionKey="+sessionKey);
                          //隐藏域，订单编号
    });

    $('form.WXPay').validate({
        errorLabelContainer: $('form.WXPay').find("div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler : function(form){
            //ajax获取支付密码

            //密码一致，执行以下功能函数，不一致则提示密码错误
           // form.submit();
        },
        rules : {
            payPassword : { required : true }
        },
        messages : {
            payPassword : { required : '请输入支付密码' }
        }
    });
}
//取消订单
function cancelOrder(){
    $('#cancelOrder').iDialog();

    $(document).on('click', 'a[data-target="#cancelOrder"]', function(){
            
        $('#cancelOrderId').text("订单号："+ $(this).data('id'));  
        $('button#btn-cancelOrder').data('orderId', $(this).data('id'));                 //订单编号
    });

    //确定取消订单
    $(document).on('click', 'button#btn-cancelOrder', function(){
        var _orderId = $(this).data('orderId');
        $.ajax({
        async:false,
        type:"POST",
        url:serverUrl+"/order/del?sessionKey="+sessionKey+"&id="+_orderId,
        dataType:"json",
        success:function(data){
            if(data.code==200){
               alert("取消成功")
                window.location.href="personal.html";
           }else{
            alert(data.code)
           }
        }
    });
        console.log(_orderId);
        //ajax do something....
    })
}

function loginOut(){

if (confirm("您确定要退出吗？"))
    top.location = "login.html";
    DelCookie('sessionKey')
    window.location.replace("login.html");
     return false;
}

function DelCookie(name) {
  var exp = new Date();
  exp.setTime(exp.getTime() + (-1 * 24 * 60 * 60 * 1000));
  var cval = GetCookieValue(name);
  document.cookie = name + "=" + cval + "; expires=" + exp.toGMTString();
}

$(function(){
    loadingOrderList();     //加载数据
    commentOrder();         //去评价
    packagePay();           //钱包支付
    cancelOrder(); 
    WXPay();         //取消订单
});