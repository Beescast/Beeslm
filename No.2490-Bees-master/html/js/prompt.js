/**
 * Created by chen on 2016/8/14.
 */
$(function(){
    //------------------------------------- 订单评价 -------------------------------------
    $("#orderInfo").iDialog();

    $('form.order-say').validate({
        errorLabelContainer: $('form.order-say').find("div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler : function(form){

        },
        rules : {
            orderSayMessage : { required : true, maxlength : 200, minlength : 2 }
        },
        messages : {
            orderSayMessage : { required : '请输入评语', maxlength : '最多可输入{0}个字符', minlength : '至少输入{0}个字符' }
        }
    });

    //------------------------------------- 注册及认证信息已经提交 -------------------------------------
    $('#identification').iDialog().lastTimeDialog({
        _time : 3,
        _lastTimeBox : $('#identification').find('.lastTime'),
        _lastCallBack : function(){
            window.location = 'index.html';
        },
    });

    //------------------------------------- 钱包支付 -------------------------------------
    $('#packagePay').iDialog();

    $('form.packagePay').validate({
        errorLabelContainer: $('form.packagePay').find("div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler : function(form){

        },
        rules : {
            payPassword : { required : true }
        },
        messages : {
            payPassword : { required : '请输入支付密码' }
        }
    });


    //------------------------------------- 赏金任务 -------------------------------------
    /*$('#moneyReward').iDialog();*/

    //------------------------------------- 取消定单 -------------------------------------
    $('#cancelOrder').iDialog();

    //------------------------------------- 修改密码成功 -------------------------------------
    $('#eidtPwdSuccess').iDialog().lastTimeDialog({
        _time : 3,
        _lastTimeBox : $('#eidtPwdSuccess').find('.lastTime'),
        _lastCallBack : function(obj, lastTime){
            obj.hideDialog(function(){
                obj.find('.lastTime').text(3);
            });
        }
    });
    //------------------------------------- 删除银行卡提示 -------------------------------------
    $('#delBankInfo').iDialog();

    //图片剪裁
    showCutImageDialog();

    //------------------------------------- 推广信息分析必须先购买套餐 -------------------------------------
    $('#buyMealDialog').iDialog().lastTimeDialog({
        _time : 3,
        _lastTimeBox : $('#buyMealDialog').find('.lastTime'),
        _lastCallBack : function(){
            window.location = 'buyMeal.html';
        }
    });

    //------------------------------------- 信息已经保存成功 -------------------------------------
    $('#saveSuccess').iDialog().lastTimeDialog({
        _time : 3,
        _lastTimeBox : $('#saveSuccess').find('.lastTime'),
        _lastCallBack : function(){
            window.location = 'personal.html';
        }
    });

    //------------------------------------- 提现申请已经提交 -------------------------------------
    $('#applySuccess').iDialog().lastTimeDialog({
        _time : 3,
        _lastTimeBox : $('#applySuccess').find('.lastTime'),
        _lastCallBack : function(){
            window.location = 'personal.html';
        }
    });

    //------------------------------------- 意见反馈 -------------------------------------
    $('#feedback').iDialog();

    $('form.feedback').validate({
        errorLabelContainer: $('form.feedback').find("div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler : function(form){

        },
        rules : {
            feedbackMessage : { required : true, maxlength : 500, minlength : 2 }
        },
        messages : {
            feedbackMessage : { required : '请填写内容', maxlength : '最多可输入{0}个字符', minlength : '至少输入{0}个字符'}
        }
    });

});