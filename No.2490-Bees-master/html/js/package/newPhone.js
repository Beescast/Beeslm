/**
 * Created by jiqiang on 2016/8/19.
 */

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
            "mobile" : { required : true, isMobile : true },
            "mobileCode" : { required : true }
        },
        messages : {
            "phone" : { required : "请输入手机号", isMobile : "请输入正确手机格式" },
            "newMobile" : { required : "请输入手机号", isMobile : "请输入正确手机格式" },
            "mobileCode" : { required : "请输入验证码" }
        },
        submitHandler:function(form){

                $(form).ajaxSubmit({

                    url:serverUrl+"/user/changeMobile",
                    type:'get',
                    success:function(data){
                        
                        if(data.code ==200){
                            alert("修改成功");

                        }else{

                            alert(data.messages.message);
                            return false;
                        }
                    }
                });
            }
    });

});

/* function changeMobile(id,newMobile,sessionKey){
    $.ajax({
        type:"post",
        url:serverUrl+"/user/changeMobile",
        dataType:"json",
        data:{id:id,newMobile:newMobile,sessionKey:sessionKey},
        success:function(data){
           if(data.code ==200){
               alert("修改成功");
                window.location.href="account.html";
            }else{

                alert("请填写正确的验证码");
                return false
            }
        }
    });
 }*/

function getToken(){
    var mobile=document.getElementById("inputMobile").value;
    if(mobile==''){
        alert('请填写手机号');
        return false;
    }
    $.ajax({
        async:false,
        type:"POST",
        url:serverUrl+"/auth/getMobileCode?action=register&mobile="+mobile,
        dataType:"json",
        success:function(data){
           if(data.code==200){
                alert('发送成功');
                send();
           }else{
             alert(data.messages.message)
           }
        }
    });
}

    var second = 60;
    function send(){
        var btn=$("#getTokenBtn");
        btn.text(second + "秒后重新发送");
        //btn.attr("class", "btn btn-success disabled");
        second--;
        if(second >= 0){
            btn.attr("onclick", "");
            t = setTimeout("send()", 1000);
        }else{
            btn.text("重新获取验证码");
            btn.attr("onclick", "getToken()");
            //btn.attr("class", "btn btn-primary");
            second = 30;
        }
    }