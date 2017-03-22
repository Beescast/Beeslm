/**
 * Created by chen on 2016/8/1.
 */
var _iDialog, imageTarget, _promptDialog, _warnDialog;
var serverUrl= "http://bees.beeslm.com";
//关闭上传图片弹框
function hideCutImageDialog(canvas, serviceImageUrl){
    if('undefined' !== typeof _iDialog) _iDialog.hideDialog();
    if('undefined' !== typeof imageTarget){
        imageTarget.html(canvas);
        imageTarget.parent().find('input[type=text][data-toggle="uploadImage"]').val(serviceImageUrl).trigger('blur');
    }
}

function showEditImageDialog(){
    _iDialog = $('#uploadImage').iDialog({
        cancelFunc : function(){
            if('undefined' !== typeof document.getElementById('uploadImageFrame')) document.getElementById('uploadImageFrame').contentWindow.location.reload(true);
        }
    });

    $(document).on('click', '#btn-changeHead', function(){
        imageTarget = $(this).parents('dl.hd').find('dt');
    });
}

//加载上传图片弹框弹框
function showCutImageDialog(){
    _iDialog = $('#uploadImage').iDialog({
        cancelFunc : function(){
            if('undefined' !== typeof document.getElementById('uploadImageFrame')) document.getElementById('uploadImageFrame').contentWindow.location.reload(true);
        }
    });

    //上传图片
    $('input[type=text][data-toggle="uploadImage"]').each(function(){
        //var _id = $.trim($(this).attr('id'));
        $(this).parents('[data-toggle=modal][data-target="#uploadImage"]').on('click', function(){
            imageTarget = $(this).find('dt');
        });
    });
}

//完成任务 上传截图
function finishDutiesBtn(){
    var _btn = $('#btn-finishDuties');
    var _selectImage = $('#selectImage-finishDuties');
    var _imgBoxHtml = '<dl data-toggle="modal" data-target="#uploadImage"><dd><input type="text" name="pics[]" value="" data-toggle="uploadImage" /></dd><dt></dt></dl>';
    var _index = -1;
    var _emptyImg = null;
    _btn.on('click', function(){
        //当有空时则 弹出空位 选择框
        _selectImage.find('dl[data-toggle=modal][data-target="#uploadImage"] input').each(function(){
            if($.trim($(this).val()) == '') _emptyImg = $(this).parents('dl[data-toggle=modal][data-target="#uploadImage"]');
        });
        if(null != _emptyImg){
            _emptyImg.trigger('click');
            _emptyImg = null;
            return;
        }
        //添加新截图区域
        _selectImage.append($(_imgBoxHtml).on('click', function(){
            imageTarget = $(this).find('dt');
        }));
        _index++;
        _selectImage.find('dl[data-toggle=modal][data-target="#uploadImage"]').eq(_index).trigger('click');         //弹框上传图片弹框
        //处理至少上传三线截图 校验
        _selectImage.find('dl[data-toggle=modal][data-target="#uploadImage"]').eq(_index).find('input').on('blur', function(){
            if('' != $.trim($(this).val())){
                _selectImage.find('input[type=number]').val(_index).trigger('blur');
            }
        });
    });
}

//提示窗口
function loadHint(){
    $('body').append('<div class="prompt-box" id="promptDialog" title="提示"></div>').append('<div class="prompt-box" id="warnDialog" title="订单评价"></div>');
    _promptDialog = $('#promptDialog').iDialog({width : 300});
    _warnDialog = $('#warnDialog').iDialog({width : 300});
}

//提示信息
function setPromptInfo(value){
    _promptDialog.setContent('', value).showDialog();
}

//警告提示
function setWarnInfo(value){
    _warnDialog.setContent('', value).showDialog();
}

//修正高度
function fixHeight(obj){
    if('object' === typeof obj && 'undefined' !== typeof obj[0])
        obj.css({
            'min-height' : 'undefined' != typeof $('div.personal-box .box-cell')[0] ? $('div.personal-box .box-cell').outerHeight(true) - $('div.personal-box .pb-title').outerHeight(true) : 0
        });
}

$(function(){
    //placeholder
    $('textarea, input').placeholder();
    //加载提示窗口
    loadHint();
});


function addCookie(name,value,days,path){   /**添加设置cookie**/  
    var name = escape(name);  
    var value = escape(value);  
    var expires = new Date();  
    expires.setTime(expires.getTime() + days * 3600000 * 24);  
    //path=/，表示cookie能在整个网站下使用，path=/temp，表示cookie只能在temp目录下使用  
    path = path == "" ? "" : ";path=" + path;  
    //GMT(Greenwich Mean Time)是格林尼治平时，现在的标准时间，协调世界时是UTC  
    //参数days只能是数字型  
    var _expires = (typeof days) == "string" ? "" : ";expires=" + expires.toUTCString();  
    document.cookie = name + "=" + value + _expires + path;  
}  
function getCookieValue(name){  /**获取cookie的值，根据cookie的键获取值**/  
    //用处理字符串的方式查找到key对应value  
    var name = escape(name);  
    //读cookie属性，这将返回文档的所有cookie  
    var allcookies = document.cookie;         
    //查找名为name的cookie的开始位置  
    name += "=";  
    var pos = allcookies.indexOf(name);      
    //如果找到了具有该名字的cookie，那么提取并使用它的值  
    if (pos != -1){                                             //如果pos值为-1则说明搜索"version="失败  
        var start = pos + name.length;                  //cookie值开始的位置  
        var end = allcookies.indexOf(";",start);        //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置  
        if (end == -1) end = allcookies.length;        //如果end值为-1说明cookie列表里只有一个cookie  
        var value = allcookies.substring(start,end); //提取cookie的值  
        return (value);                           //对它解码        
    }else{  //搜索失败，返回空字符串  
        return "";  
    }  
}  
function deleteCookie(name,path){   /**根据cookie的键，删除cookie，其实就是设置其失效**/  
    var name = escape(name);  
    var expires = new Date(0);  
    path = path == "" ? "" : ";path=" + path;  
    document.cookie = name + "="+ ";expires=" + expires.toUTCString() + path;  
}  

var sessionKey = getCookieValue("sessionKey");
            //document.getElementById("txtUserName").value = userNameValue;  
if(sessionKey==''){

   var nav = "<li id='index'><a href='index.html'>首页</a><i></i></li><li id='about'><a href='about.html'>关于bees</a><i></i></li>";
   var navfr = "<a href='login.html' class='btn-lr btn-login hover'>登录</a><a href='register.html' class='btn-lr btn-reg'>注册</a>"
   document.getElementById("navfr").innerHTML += navfr
}else{
    var nav = "<li id='index'><a href='index.html'>首页</a><i></i></li><li id='buyMeal'><a href='buyMeal.html'>套餐购买</a><i></i></li><li id='personal'><a href='personal.html'>个人页</a><i></i></li><li id='store'><a href='store.html'>小商城</a><i></i></li><li id='offer'><a href='offer.html'>悬赏任务</a><i></i></li><li id='about'><a href='about.html'>关于bees</a><i></i></li>";
    var navfr = "<a href='account.html' class='top-head' id='userAvart'></a><div class='top-user-name' id='userName'></div><a href='loginOut.html' class='top-exit'>退出</a>"
   document.getElementById("navfr").innerHTML += navfr

     var userName = document.getElementById("userName");

        var userAvart = document.getElementById("userAvart");

        $.ajax({
        async:false,
        type:"POST",
        url:serverUrl+"/user/info?sessionKey="+sessionKey,
        dataType:"json",
        success:function(data){
            if(data.code==200){
               //introduce.innerHTML=data.data.response.result.content;
               userName.innerHTML=data.data.response.result.nickname;

                var Avart ='<img src="'+data.data.response.result.avatar+'" /><span></span>';
                userAvart.innerHTML=Avart;

           }else{
            alert(data.code)
           }
        }
    

    });
}


document.getElementById("nav").innerHTML += nav

/**实现功能，保存用户的登录信息到cookie中。当登录页面被打开时，就查询cookie**/  
/*window.onload = function(){  
    var userNameValue = getCookieValue("userName");  
    document.getElementById("txtUserName").value = userNameValue;  
    var userPassValue = getCookieValue("userPass");  
    document.getElementById("txtUserPass").value = userPassValue;  
}*/  
