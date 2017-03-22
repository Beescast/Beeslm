/**
 * Created by Administrator on 2016/8/23.
 */
var tempOffset = 0;
/**
 * ajax数据处理
 * @param element   分页对象
 * @param data      ajax url参数集
 * @param url       ajax 请求地址
 * @private
 */

function _ajaxLoadBankData(element, data, url){
    tempOffset = data.offset;
    $.ajax({
        url : url,
        type : 'GET',
        data : $.param(data),
        dataType : 'JSON',
        success : function(data){
            data.offset = tempOffset;           //临时记录当前页， 后续后台传送offset存储data中
            var arrDate = {resultList:[data.data.response.result]};
            var _html = template('tpl-bank-list', arrDate);
            $('#view-bank-list').html(_html).fnPageList();

            //重新设置分页
            element.setOptions({
                _total : data.total ,               //设置总数据条数
                _rows : data.rows,                  //一页显示行数
                _offset : data.offset               //当前所在页, 从0(第1页)开始
            });
        }
    });
}

//加载主播列表信息
function loadingBankList(){
     var sessionKey = getCookieValue("sessionKey");
    $('#view-bank-list').myPaginate({
        
        _parent : $('#view-bank-list').parent().parent(),

        _ajaxUrl : serverUrl+'/user/info?sessionKey='+sessionKey,       //数据接口
        _ajaxData : {                           //附加ajax传递参数
            _templateId : 'testId'
        },
        _ajax : _ajaxLoadBankData
    });
}

//删除银行卡
function delBank(){
    $('#delBankInfo').iDialog();        //初始化删除银行卡 确认弹框
    $(document).on('click', 'a.delBank', function(){
        $('#delBankInfo').showDialog();     //显示弹框
        $('#delBankInfo button.btn-normal').data('id', $(this).data('id'));
    });

    //确定删除银行卡
    $(document).on('click', '#delBankInfo button.btn-normal', function(){
        var _id = $(this).data('id'); 
                 //获取当前要删除银行卡ID
        $('form.delBankInfo').validate({

        submitHandler:function(form){
            var sessionKey = getCookieValue("sessionKey");
             $.ajax({
                type:"get",
                url:serverUrl+"/user/unbindBank?sessionKey="+sessionKey,
                    dataType:"json",

                success:function(data){
                   if(data.code ==200){
                        window.location.href="bindBank.html";
                        alert("删除成功");
                       
                         
                    }else{
                        alert("删除失败");
                        return false
                    }
                }
            });
        }
    });
        
    });
}

$(function(){
    //修正高度
    fixHeight($('div.view-bank-list'));

    //加载主播列表
    loadingBankList();

    //删除银行卡
    delBank();
});