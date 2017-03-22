/**
 * Created by chen on 2016/8/19.
 */
/*$(function(){
    //高度修正
    fixHeight($('form.update'));

    //表单校验
    $('form.update').validate({
        errorLabelContainer: $("div.error-info"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler : function(form){
            // form.submit();
        },
        rules : {
            "price" : { required : true, number : true  },
            "payPwd" : { required : true}
        },
        messages : {
            "price" : { required : "请输入提现金额", number : "请输入正确数字格式" },
            "payPwd" : { required : "请输入支付密码" }
        },
    });

});*/

function cashApply(){
   $('.update').validate({
        errorLabelContainer: $("div.error-info"),
        wrapper: "p",
        ignore: ".ignore",
        rules : {
            "money" : { required : true ,money:true},
            "password" : { required : true}
        },
        messages : {
            "money" : { required : "请输入提现金额",money:"请输入大于0的金额"},
            "password" : { required : "请输入支付密码" }
        },
       // 
        submitHandler:function(form){
            $(form).ajaxSubmit({
                url:serverUrl+"/wallet/cash",
                type:'post',
                success:function(data){
                    if(data.code ==200){
                        alert("提交成功");
                        history.go(0)
                    }else{
                        alert(data.messages.message);
                        return false
                    }
                }
            });
        }

    });
}