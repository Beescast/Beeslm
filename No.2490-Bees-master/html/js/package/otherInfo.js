/**
 * Created by jiqiang on 2016/8/20.
 */
var serverUrl ="http://123.56.239.203:8080/bees";

$(function(){
    //高度修正
    fixHeight($('form.update'));

    //表单校验
    $('form.update').validate({
        errorLabelContainer: $("div.error-info"),
        wrapper: "p",
        ignore: ".ignore",
       /* submitHandler : function(form){
        
        },*/
       rules : {
            "email" : { required : false , email : true },
            "contactMobile" : { required : false, isMobile : true  },
            "contactAddress" : { required : false },
            "agencyName" : { required : false },
            "agencyMobile" : { required : false , isPhone : true},
            "description" : { required : false }
        },
        messages : {
            "email" : { required : "请输入电子邮箱", email : '邮箱格式不正确'},
            "contactMobile" : { required : "请输入联系电话", isMobile : "请输入正确手机格式" },
            "contactAddress" : { required : "请输入联系地址" },
            "agencyName" : { required : "请输入经纪人公司名称" },
            "agencyMobile" : { required : "请输入经纪人联系电话" , isPhone : "请输入正确格式联系电话"},
            "description" : { required : "请输入备注信息" }
        },
        submitHandler:function(form){
            var sessionKey = getCookieValue("sessionKey");


            $(form).ajaxSubmit({
                url:serverUrl+"/user/edit",
                type:'post',
                success:function(data){
                    if(data.code ==200){
                        alert("设置成功");
                        history.go(0)
                    }else{
                        alert("提交失败");
                        return false
                    }
                }
            });
        }
      
    });

});

// 电话号码验证
jQuery.validator.addMethod("isPhone", function(value, element) {
    var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
    return this.optional(element) || (tel.test(value));
}, "电话号码格式错误!");

function isPhone(phone){
    if(!/^(\d{3,4}-?)?\d{7,9}$/.test(phone))
        return false;
}