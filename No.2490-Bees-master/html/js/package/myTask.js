/**
 * Created by chen on 2016/8/17.
 */
var tempOffset = 0;
/**
 * ajax数据处理
 * @param element   分页对象
 * @param data      ajax url参数集
 * @param url       ajax 请求地址
 * @private
 */

function _ajaxLoadTaskData(element, data, url){
    tempOffset = data.offset;
    tempRows = data.rows;
    data.limit= data.rows;
    data.start = data.offset*data.rows;
    $.ajax({
        url : url,
        type : 'GET',
        data : $.param(data),
        dataType : 'JSON',
        success : function(data){
            data.offset = tempOffset;           //临时记录当前页， 后续后台传送offset存储data中
            data.rows =tempRows;
            var _html = template('tpl-task-list', data.data.response);
            $('#task-list').html(_html).fnPageList();

            //重新设置分页
            element.setOptions({
                _total : data.data.response.total,               //设置总数据条数
                _rows : data.rows,                  //一页显示行数
                _offset : data.offset               //当前所在页, 从0(第1页)开始
            });
        }
    });
}

//加载任务信息
function loadingTaskList(){
     var sessionKey = getCookieValue("sessionKey");
    $('#order-list').myPaginate({
        _parent : $('#task-list').parents('.personal-list'),
        _ajaxUrl : serverUrl+'/task/list?sessionKey='+sessionKey+"&sign=1&bidStatus=1",      //数据接口
        _ajaxData : {                           //附加ajax传递参数
            _templateId : 'testId'
        },
        _ajax : _ajaxLoadTaskData
    });
}
function finishDuties () {
     $('#finishDuties').iDialog();
     finishDutiesBtn();
     $(document).on('click', 'a[data-target=#finishDuties]', function(){

        $('#taskTypes').text($(this).data('title'));
        $("#title").text($(this).data('name'));
        $('#taskId').val($(this).data('id'));
        $("#").val(getCookieValue("sessionKey"))
    });
      

    $('form.finishDuties').validate({
        errorLabelContainer: $('form.finishDuties').find("div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler : function(form){
             $(form).ajaxSubmit({
                url:serverUrl+"/task/finishTask",
                type:'post',
                success:function(data){
                    
                    if(data.code ==200){
                        window.location.href="myTask.html";
                    }else{
                        alert(data.messages.message);
                        return false
                    }
                }
            });

        },
        rules : {
            littleNumber : { min : 2 }
        },
        messages : {
            littleNumber : { min : '最少三张截图' }
        }
    });
}

$(function(){
    finishDuties();
    //加载数据
    loadingTaskList();
});