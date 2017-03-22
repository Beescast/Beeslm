/**
 * Created by chen on 2016/8/10.
 */

 var serverUrl ="http://bees.beeslm.com"

$(function(){
    //表单校验
    $('form.reg-form').validate({
        errorLabelContainer: $("div.error-info"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler : function(form){

        },
        rules : {
            "truename" : { required : true ,isChinaName: true,minlength:2,maxlength:13},
            "idCard" : { required : true ,isIdCardNo:true},
            "frontPic" : { required : true },
            "backPic" : { required : true },
            "handPic" : { required : true },
            "plat" : { required : true ,minlength:2,maxlength:12,string: true},
            "liveRoom" : { required : true ,minlength:4,maxlength:16,string: true},
            "platPic" : { required : true },
            "mobile" : { required : true ,minlength:1,isMobile : true},
            "mobileCode" : { required : true },
            "ckd_protocol":{required : true}
        },
        messages : {
            "truename" : { required : "真实姓名必填",isChinaName:"请填写正确姓名",minlength:"请输入大于于2位的姓名",maxlength:"请输入小于13位的姓名" },
            "idCard" : { required : "身份证号码必填" },
            "frontPic" : { required : "请上传身份证正面" },
            "backPic" : { required : "请上传身份证反面" },
            "handPic" : { required : "请上传手持身份证照" },
            "plat" : { required : "平台信息必填" ,minlength:"请输入大于于2位的平台信息",maxlength:"请输入小于12位的平台信息",string:"平台信息不能有特殊字符"},
            "liveRoom" : { required : "直播房间必填" ,minlength:"请输入大于4位的房间号",maxlength:"请输入小于16位的房间号",string:"房间号不能有特殊字符"},
            "platPic" : { required : "请上传平台认证照片" },
            "mobile" : { required : "手机号必填" ,minlength:"请输入大于于11位的手机号",isMobile:"请输正确的手机号"},
            "mobileCode" : { required : "请填写短信验证码" },
            "ckd_protocol":{ required : "同意bees协议才可注册" }
        },
         submitHandler:function(form){

            $(form).ajaxSubmit({

                url:serverUrl+"/auth/reg",
                type:'post',
                success:function(data){
                    
                    if(data.code ==200){
            
                        alert('等待审核')
                        window.location.href="index.html";
                    }else{
                        alert(data.messages.message);
                        return false
                    }
                }
            });
        }


    });

    //添加上传图片控件
    showCutImageDialog();

    $('#identifier').on('shown.bs.modal', function () {
        //完成可见效果 时触发 执行一些动作...
    })

    $('#identifier').on('hide.bs.modal', function () {
        //隐藏时间触发 执行一些动作...
    })
});
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