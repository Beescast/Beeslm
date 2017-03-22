/**
 * Created by jiqiang on 2016/8/20.
 */
  /* 
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
            "offerType" : { required : true }
        },
        messages : {
            "offerType" : { required : "请选择任务类型" }
        },

         submitHandler:function(form){

            alert(form.offerType.value)
            $.ajax({
                url : serverUrl+'/user/edit',
                type : 'GET',
                dataType : 'JSON',
                success : function(data){
                
                    var _html = template('tpl-task-list', data.data.response);

                   $("#offer-top").html(_html);
                }
            });
        }

    });

});*/
function checkOffer(){
  /*  if($("input[type='checkbox']").is(':checked')) {
    alert("请选择任务类型");return false;
    }*/
    var value = "";
    var arr = [];
   
    var a = document.getElementsByName("offerType");
    for (var i=0;i<a.length;i++ ){
        if(a[i].checked){ 
            arr.push(a[i].value);
        }
    }
    var myString = arr.join(",");
    var sessionKey = getCookieValue("sessionKey");
     $.ajax({
        url : serverUrl+'/user/edit?taskCat='+myString+"&sessionKey="+sessionKey,
        type : 'GET',
        dataType : 'JSON',
        success : function(data){
            if(data.code == 200){
                alert("设置成功");
                history.go(0)
            }else{
                alert("设置失败");
            }
        }
    });
}
function loadingOfferTop(){
   
    $.ajax({
        url : serverUrl+'/task/catList?gt=1',
        type : 'GET',
        dataType : 'JSON',
        success : function(data){
            
            var _html = template('tpl-task-list', data.data.response);

           $("#offer-top").html(_html);
        }
    });
}
　function contains(arr, str) {
        var i = arr.length;
        while (i--) {
               if (arr[i] === str) {
               return true;
               }   
        }   
        return false;
    }
function loadingChecked(){
    var sessionKey = getCookieValue("sessionKey");

    var arr = new Array();
    $.ajax({
        url : serverUrl+'/user/info?sessionKey='+sessionKey,
        type : 'GET',
        dataType : 'JSON',
        success : function(data){
            arr = data.data.response.result.tasks;
            $("input[type='checkbox']").each(function(){
                var m = contains(arr,$(this).val());
                if(m){
                    $(this).attr("checked",true);
                }
            });
        }
    });
}
$(function(){
    //加载数据
    loadingOfferTop();
    loadingChecked();
});