/**
 * Created by chenlan on 2016/8/1.
 */
 /*图形验证码获取*/
var serverUrl ="http://bees.beeslm.com"


function updateImage() {

    var mobile = document.getElementById("inputMobile").value;
    $('#otherRegist').html('<img  id="identifyImage" style="text-align:right;cursor:pointer;width:100px;height: 40px;" onclick="updateImage()"/>');
    var appname = navigator.appName.toLowerCase();
    if (appname.indexOf("netscape") != -1)
    {
        $('#identifyImage')[0].onload = function () {
            if ($('#identifyImage')[0].complete == true)
            {
                $('#identifyImage').show();
            }
        }
    }
    ra =Math.floor(Math.random()*100000000);
    $('#identifyImage').attr("src",serverUrl+"/auth/code?mobile="+mobile+"&sk="+ra);
   
     $('#sk').val(ra);

}

function noticeShow(){
    var notice = $('#notice'), _newBox = $('<div />').css({
        'position' : 'relative',
        'top' : 0,
        'left' : 0,
        'z-index' : 1
    }), _size = notice.find('a').size(), _index = 0, playTime = 3000, moveTime = 300, handle = setInterval(moveIt, playTime);
    _newBox.html(notice.html());
    notice.html(_newBox);

    notice.hover(function(){
        clearInterval(handle);
    }, function(){
        handle = setInterval(moveIt, playTime);
    });

    function moveIt(){
        _index++;
        if(_index >= _size) _index = 0;

        _newBox.stop(!0, !0).animate({
            'top' : -(18 * _index)
        }, moveTime);
    }
}

function enterShow(){
    var _enter = $('#user-enter'),_enterBox = _enter.find('div.enter-box'),  _nav = _enter.find('ul.nav li').each(function(){
        $(this).mouseover(function(){
            _enterBox.hide();
            _enter.find('div.enter-box[data-index=' + $(this).data('index') + ']').show();
        });
    });
    _enterBox.each(function(i){
        $(this).append($('<i class="icon_up" />').css({
            'left' : 138 * i + 50
        }));
    }).eq(0).show();

}

function loadingNotice(){
    $.ajax({
        async:false,
        type:"get",
        url:serverUrl+"/notice/list?start=0&limit=10&oder=12",
        dataType:"json",
        success:function(data){
           
            var _html = template('tpl-notice', data.data.response);
            $('#notice').html(_html);

            //公告
            noticeShow();
        }
    });
}

function loadingEnterInfo(){
    $.ajax({
        type:"get",
        url:serverUrl+"/user/list?start=1&limit=6&oder=12",
        dataType:"json",
        success:function(data){

            var _html = template('tpl-user-enter', data.data.response);
            $('#user-enter').html(_html);

            //入驻
            enterShow();
        }
    });
}


function userLogin(){

    /*var mobile = document.getElementById("inputMobile").value;
    var password = document.getElementById("inputPassword").value;
    var verifyCode = document.getElementById("verifyCode").value;*/
       $('.login-form').validate({
        rules : {
            "mobile" : { required : true, isMobile : true },
            "password" : {
                required:true,
                minlength: 6,
             },
           // "code" : { required : true }
        },
        messages : {
            "mobile" : { required : "请输入手机号码" },
            "password" : { required : "请输入密码",minlength:'密码不可少于6位哦' },
            //"code" : { required : "请输入验证码" }
        },

       // 
        submitHandler:function(form){
            //图片验证码验证
           /* if(authCode()!=200){
                alert('验证码不正确');
                return false;
            }*/
            $(form).ajaxSubmit({
                url:serverUrl+"/auth/login",
                type:'post',
                success:function(data){
                    
                    if(data.code ==200){
                        addCookie("mobile",data.data.response.user.UserMobile,7,"/");  
                        addCookie("UserId",data.data.response.user.UserId,7,"/"); 
                        addCookie("sessionKey",data.data.response.sessionKey,7,"/");
                        addCookie("nickname",data.data.response.nickname,7,"/");
                        addCookie("avatar",data.data.response.avatar,7,"/");
                        
                        window.location.href="account.html";
                    }else{
                        alert(data.messages.message);
                        return false
                    }
                }
            });
        }

    });
}

function GetRequest() {   
       var url = location.search; //获取url中"?"符后的字串   
    
       var theRequest = new Object();   
       if (url.indexOf("?") != -1) {   
          var str = url.substr(1);   
          strs = str.split("&");   
          for(var i = 0; i < strs.length; i ++) {   
             theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);   
          }   
       }   
       return theRequest;   
    }

$(function(){
    //加载公告信息
    loadingNotice();
     //详情数据
    var Request = new Object();
    Request = GetRequest();
    phone= Request['phone'];

    if(phone!=undefined){
           userLogin() 
    }
    
    //加载入驻信息
    loadingEnterInfo();
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

function getToken(){
    var mobile=document.getElementById("inputMobile").value;
    var code=document.getElementById("inputCode").value;

    if(mobile==''){
        alert('请填写手机号');
        return false;
    }
    
    if(code==''){
        alert('请填写图片验证码');
        return false;
    }

    
    $.ajax({
        async:false,
        type:"POST",
        url:serverUrl+"/auth/getMobileCode?action=Login&mobile="+mobile,
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

//验证图片验证码
function authCode(){
    var verifyCode = document.getElementById("verifyCode").value;
    var mobile = document.getElementById("inputMobile").value;
    $.ajax({
        type:"get",
        url:serverUrl+"/auth/authCode?code="+verifyCode+"&mobile="+mobile,
        dataType:"json",
        success:function(data){
           return data.code
        }
    })
}   
