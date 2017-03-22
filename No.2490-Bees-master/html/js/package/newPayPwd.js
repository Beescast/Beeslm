/**
 * Created by chen on 2016/8/20.
 */
$(function(){
    //高度修正
    fixHeight($('form.update'));

    //表单校验
    $('form.update').validate({
        errorLabelContainer: $("div.error-info"),
        wrapper: "p",
        ignore: ".ignore",
        /*submitHandler : function(form){
            form.submit();
        },*/
        rules : {
            "nPwd" : { required : true , minlength : 6, maxlength : 16,isPass:true},
            "newPass" : { required : true, equalTo : "#nPwd" }
        },
        messages : {
            "nPwd" : { required : "请输入支付密码" , minlength : "请输入6-16密码", maxlength : "请输入6-16位密码",isPass:"数字字母组合密码"},
            "newPass" : { required : "请再次输入支付密码", equalTo : "两次密码不一致" }
        },
        submitHandler : function(form){
            $(form).ajaxSubmit({
                url:serverUrl+"/user/setPayPassword",
                type:'post',
                success:function(data){
                    if(data.code ==200){
                        alert("设置成功");
                        history.go(-2)
                    }else{
                        alert("提交失败");
                        return false
                    }
                }
            });
        }
    });

});