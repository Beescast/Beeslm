var serverUrl="interface.php?";
function submits () {
    $.ajax({
        cache: true,
        type: "POST",
        url:serverUrl+ajaxUrl,
        data:$('#myform').serialize(),
        async: false,
        dataType: "json", 
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            success(data);
        }
    });
    return false;
}
function searchs () {
    try{
        tables.api().destroy();
    }catch(e){
            
    }
    create_table();
}

(function($) {
    $.extend({
        myTime: {
            CurTime: function(){
                return Date.parse(new Date())/1000;
            },
            DateToUnix: function(string) {
                var f = string.split(' ', 2);
                var d = (f[0] ? f[0] : '').split('-', 3);
                var t = (f[1] ? f[1] : '').split(':', 3);
                return (new Date(
                        parseInt(d[0], 10) || null,
                        (parseInt(d[1], 10) || 1) - 1,
                        parseInt(d[2], 10) || null,
                        parseInt(t[0], 10) || null,
                        parseInt(t[1], 10) || null,
                        parseInt(t[2], 10) || null
                        )).getTime() / 1000;

            },

            /**               

             * 时间戳转换日期               

             * @param <int> unixTime    待时间戳(秒)               

             * @param <bool> isFull    返回完整时间(Y-m-d 或者 Y-m-d H:i:s)               

             * @param <int>  timeZone   时区               

             */

            UnixToDate: function(unixTime, isFull, timeZone) {
                unixTime = parseInt(unixTime) + parseInt(8) * 60 * 60;


                var time = new Date(unixTime * 1000);

                var ymdhis = "";

                ymdhis += time.getUTCFullYear() + "-";

                ymdhis += (time.getUTCMonth()+1) + "-";

                ymdhis += time.getUTCDate();

                if (isFull !== false)

                {

                    ymdhis += " " + time.getUTCHours() + ":";

                    ymdhis += time.getUTCMinutes() + ":";

                    ymdhis += time.getUTCSeconds();

                }

                return ymdhis;

            }

        }

    });

})(jQuery); 

var check=false;
function all_check(){
    if(check==false){
        check=true;
        $(".check").attr("checked","checked");
    }else{
        check=false;
        $(".check").removeAttr("checked");
    }
}
function check_ids(){
    var str="";
    $(".check").each(function(i){
        if($(this).prop("checked")==true){
            str+=$(this).val()+",";
        }
    });
    return str;
}