/**
 * Created by chen on 2016/8/1.
 */

window.onload = function(){ 
    var sessionKey = getCookieValue("sessionKey");
    document.getElementById("sessionKey").value=sessionKey;

    var serverUrl="http://123.56.239.203:8080/bees"; 
    var userName = document.getElementById("userName");
    var name = document.getElementById("viewUserName");
    var account = document.getElementById("account");
    var mobile = document.getElementById("mobile");
    var balance = document.getElementById("balance");
    
     var userAvart = document.getElementById("userAvart");
     var headAvart = document.getElementById("headAvart");
    $.ajax({
    async:false,
    type:"POST",
    url:serverUrl+"/user/info?sessionKey="+sessionKey,
    dataType:"json",
    success:function(data){
        if(data.code==200){
           //introduce.innerHTML=data.data.response.result.content;
           name.innerHTML=data.data.response.result.nickname;
           userName.innerHTML=data.data.response.result.nickname;
           account.innerHTML=data.data.response.result.id;
            
           var balan= "钱包余额：￥"+data.data.response.result.balance;
            balance.innerHTML=balan;
            var Avart ='<img src="'+data.data.response.result.avatar+'" /><span></span>';
            userAvart.innerHTML=Avart;
            headAvart.innerHTML=Avart;     
       }else{
        alert(data.code)
       }
    }
    });
}