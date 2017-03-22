/**
 * Created by chen on 2016/8/20.
 */
var sessionKey = getCookieValue("sessionKey");

var serverUrl ="http://123.56.239.203:8080/bees";
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
            "oldPassword" : { required : true  },
            "newPassword" : { required : true  , minlength : 6, maxlength : 18,isPass:true},
            "aPwd" : { required : true, equalTo : "#newPassword" }
        },
        messages : {
            "oldPassword" : { required : "请输入旧密码" },
            "newPassword" : { required : "请输入新密码" , minlength : "请输入6-18密码", maxlength : "请输入6-18位密码",isPass:"请输入6-18位密码字母数字组合密码"},
            "aPwd" : { required : "请再次输入新密码", equalTo : "两次密码不一致" }
        },
        submitHandler : function(form){


            $(form).ajaxSubmit({

                url:serverUrl+"/user/setPassword",
                type:'post',
                success:function(data){
                  
                    if(data.code ==200){
                        alert("修改成功");
                        //return false;
                        //history.go(0)
                        window.location.href = "loginOut.html";
                    }else{
                        alert("修改失败");
                        return false
                    }
                }
            });
        }
    });

});