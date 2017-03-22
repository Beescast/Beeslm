/**
 * Created by chen on 2016/8/18.
 */

function editUserName(){
    $('a#editUserName').click(function(){
        $('#viewUserName').hide();
        $('input#inputUserName').show();
    });

    $('input#inputUserName').change(function(){
         var UserName = document.getElementById("inputUserName").value;
         var sessionKey = document.getElementById("sessionKey").value;
        //修改成功更改显示内容
        $(this).hide();
        $('#viewUserName').text($(this).val()).show();

         $.ajax({
        type:"POST",
        url:serverUrl+"/user/edit?nickname="+UserName+"&sessionKey="+sessionKey,
        dataType:"json",

        success:function(data){
            deleteCookie('nickname','/')
            addCookie("nickname",UserName,7,"/");

           alert('修改成功');
           //history.go(0) 
        }
        });

    });
}



$(function(){
    //修改用户名
    editUserName();
    //添加上传图片控件
    showEditImageDialog();

    //------------------------------------- 意见反馈 -------------------------------------
    $('#feedback').iDialog();

    $('form.feedback').validate({
        errorLabelContainer: $('form.feedback').find("div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler : function(form){

        },
        rules : {
            feedbackMessage : { required : true, maxlength : 500, minlength : 5 }
        },
        messages : {
            feedbackMessage : { required : '请填写内容', maxlength : '最多可输入{0}个字符', minlength : '至少输入{0}个字符'}
        },
        submitHandler:function(form){

            var content = document.getElementById("feedbackMessage").value;
            var sessionKey = getCookieValue("sessionKey");
        
             $.ajax({
                type:"get",
                url:serverUrl+"/msg/add?content="+content+"&sessionKey="+sessionKey,
                dataType:"json",

                success:function(data){
                   if(data.code ==200){
                        alert("提交成功")
                        history.go(0)
                    }else{
                        alert("提交失败");
                        return false
                    }
                }
            });
        }
    });
    //------------------------------------- 意见反馈 -------------------------------------
});