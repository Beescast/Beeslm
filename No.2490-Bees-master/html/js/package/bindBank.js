/**
 * Created by chen on 2016/8/20.
 */

$(function(){
    //高度修正
    fixHeight($('form.update'));

    //
    $('select#selectBank').on('change', function(){
        var _val = $(this).val();
        if('其他银行' == _val){
            $('#inputBankName').slideDown();
            $('#BankName').val('');
        }else{
            $('#inputBankName').hide();
            $('#BankName').val(_val);
        }
    });

    //表单校验
    $('form.update').validate({
        errorLabelContainer: $("div.error-info"),
        wrapper: "p",
        ignore: ".ignore",
       /* submitHandler : function(form){
            //form.submit();
        },*/
        rules : {
            "selectBank" : { required : true },
            "subBranch" : { required : true },
            "accountName" : { required : true },
            "bankNo" : { required : true },
            "aBankNo" : { required : true, equalTo : "#bankNo" },
            "bankName":{required:true}
        },
        messages : {
            "selectBank" : { required : "请选择银行" },
            "subBranch" : { required : "请输入支行名称" },
            "accountName" : { required : "请输入开户人姓名"  },
            "bankNo" : { required : "请输入银行账号" },
            "aBankNo" : { required : "请再次输入银行账号", equalTo : "两次账号不一致" },
            "bankName":{required:'请填写其他银行名称'}
        },
        submitHandler:function(form){
            var sessionKey = getCookieValue("sessionKey");
            var bankName = $("select[name='selectBank']").val();
            if(bankName=='其他银行'){
                bankName =  $("input[name='bankName']").val();
            }
            var subBankName = $("input[name='subBranch']").val();

            var bankCard = $("input[name='bankNo']").val();
            var accountName = $("input[name='accountName']").val();
            $.ajax({
                type:"get",
                url:serverUrl+"/user/edit?bankName="+bankName+"&subBankName="+subBankName+"&bankCard="+bankCard+"&accountName="+accountName+"&sessionKey="+sessionKey,
                dataType:"json",

                success:function(data){
                   if(data.code ==200){
                        alert("绑定成功");
                       
                        window.location.href="viewBank.html";
                    }else{
                        alert("绑定失败");
                        return false
                    }
                }
            });
        }

    });

});