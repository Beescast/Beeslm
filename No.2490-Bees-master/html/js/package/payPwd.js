/**
 * Created by jiqiang on 2016/8/20.
 */
//var serverUrl ="http://123.56.239.203:8080/bees"; 
$(function(){
    //高度修正
    fixHeight($('form.update'));

    //表单校验
    $('form.update').validate({
        errorLabelContainer: $("div.error-info"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler : function(form){
            form.submit();
        },
        rules : {
            "phone" : { required : true, isMobile : true  },
            "verifyCode" : { required : true }
        },
        messages : {
            "phone" : { required : "请输入手机号", isMobile : "请输入正确手机格式" },
            "verifyCode" : { required : "请输入短信验证码" }
        },
        submitHandler : function(form){
            var mobile = $("input[name='phone']").val();
            var mobileCode = $("input[name='verifyCode']").val();
            //验证手机验证码是否正确
            $.ajax({
                type:"get",
                url:serverUrl+"/auth/authMobileCode?mobile="+mobile+"&mobileCode="+mobileCode,
                dataType:"json",
                success:function(data){
                  if(data.code == 200){
                    var id =data.data.response.id;
                    window.location.href = "newPayPwd.html?id="+id;
                  }else{
                    alert(data.messages.message);
                    return;
                  }
                }
            });

        }
    });

});
