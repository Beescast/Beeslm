/**
 * Created by jiqiang on 2016/8/19.
 */
 function pay(){
    //选择支付方式
    $('form.recharge button.payType').click(function(){
         var money= $.trim($("#money").val());
		 var sessionKey = getCookieValue("sessionKey");
		if($('form.recharge').valid()){
			if($(this).data('value')=='微信'){
			   
			}else{
				window.location.href=serverUrl+"/pay/alipay.html?totalFee="+money+"+&type=wallet&sessionKey="+sessionKey;
			}
			$('input#payType').val($(this).data('value'));
		}
    });
 }

 function WXPay(){   
    $(document).on('click', 'button#pay-wx', function(){
        var money= $.trim($("#money").val());
		if($('form.recharge').valid()){
			$('#WXPay').showDialog();
			var sessionKey = getCookieValue("sessionKey");
			$('#viewPayPrice').text(money);   
			$('#identifyImage').attr("src","http://www.beeslm.com:8070/pay/pay.php?totalFee="+money+"&type=wallet&sessionKey="+sessionKey);
		}
    });
    
}

$(function(){
	fixHeight($('form.recharge'));

	$('#WXPay').iDialog();

    pay();
    //修正高度

	//表单校验
    $('form.recharge').validate({
        errorLabelContainer: $("div.error-info"),
		submitHandler : function(){
			
		},
        wrapper: "p",
        ignore: ".ignore",
        rules : {
            "price" : { required : true, number:true, min:0.1 }
        },
        messages : {
            "price" : { required : "请输入充值金额", number:"必须输入合法的数值或小数", min : "输入金额不能小于0.1元"}
        }
    });
    
});

