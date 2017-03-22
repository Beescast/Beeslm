//时间格式化
template.helper("dateFormat", function (time, style) {
    if(!time || time==null ||time=='' || time <= 0){
        return '';
    }
    var d = new Date(parseInt(time)*1000);
    return formatDate(d,style);
});
function formatDate(now,style) {
    var year=now.getFullYear();
    var month=now.getMonth()+1;
    var date=now.getDate();
    var hour=now.getHours();
    var minute=now.getMinutes();
    var second=now.getSeconds();
    if(month < 10){
        month = '0' + month;
    }
    if(date < 10){
        date = '0' + date;
    }
    if(hour < 10){
        hour = '0' + hour;
    }
    if(minute < 10){
        minute = '0' + minute;
    }
    if(second < 10){
        second = '0' + second;
    }
    if(style == 1 ){//格式:2014-08-15 00:00:00
        return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
    }else if(style == 2){//格式:2014-08-15 00:00
        return year+"-"+month+"-"+date+" "+hour+":"+minute;
    }else if(style == 3){//格式:2014/08/15
        return year+"/"+month+"/"+date;
    }else if(style == 4){//格式:2014-08-15
        return year+"-"+month+"-"+date;
    }
}

//招聘状态
template.helper("recruitStatusFun", function (value) {
    if(value === 1){
        return '招聘中';
    }else if(value === 0){
        return '招聘结束';
    }
});


//1合格  2不合格 3复检'
template.helper("fruitFun",function(value){
    if(value === 1){
        return '合格';
    }else if(value === 2){
        return '不合格';
    }else if(value==3) {
        return '复检';
    }
})

//文本截取
template.helper("hideText", function (value,num) {
    if(value.length > num){
        return value.substr(0,num)+'…';
    }else{
        return value;
    }
});


//待办事宜名称（申请）
template.helper("mattersTypeFun", function (value) {
    if(value === 1){
        return '在职证明申请';
    }else if(value === 2){
        return '收入证明申请';
    }
});

//待办事宜状态（申请）
template.helper("mattersStatusFun", function (value) {
    if(value ==0){
        return '未审核'
    }else if(value === 1){
        return '审核通过';
    }else if(value === 2){
        return '审核不通过';
    }
});

//薪资分类类型
template.helper("salaryItemItemTypeFun", function (value) {
    if(value == 1){
        return '输入项'
    }else if(value === 2){
        return '计算项';
    }
});

//薪资分类类型
template.helper("salaryItemSalaryTypeFun", function (value) {
    if(value == 1){
        return '收入项目'
    }else if(value === 2){
        return '扣款项目';
    }else if(value === 3){
        return '其他项目';
    }
});

//薪资项目是否计税
template.helper("salaryItemIsTaxFun", function (value) {
    if(value == 1){
        return '是'
    }else if(value === 2){
        return '否';
    }
});

//薪资项目状态
template.helper("salaryItemStatusFun", function (value) {
    if(value == 1){
        return '启用'
    }else if(value === 2){
        return '禁用';
    }
});
//性别
template.helper("sexFun", function (value) {
    if(value == 1){
        return '男'
    }else if(value == 2){
        return '女';
    }else{
        return value;
    }
});
//户口性质
template.helper("accountPropertiesFun", function (value) {
    if(value == 1){
        return '农业'
    }else if(value == 2){
        return '非农业';
    }else{
        return value;
    }
});
//政治面貌
template.helper("politicalStatusFun", function (value) {
    if(value == 1){
        return '群众'
    }else if(value == 2){
        return '团员';
    }else if(value == 3){
        return '党员';
    }else{
        return value;
    }
});
//婚姻状态
template.helper("maritalStatusFun", function (value) {
    if(value == 1){
        return '已婚'
    }else if(value == 2){
        return '未婚';
    }else if(value == 3){
        return '离异';
    }else{
        return value;
    }
});
//文化程度
template.helper("educationDegreeFun", function (value) {
    if(value == 1){
        return '初中'
    }else if(value == 2){
        return '高中';
    }else if(value == 3){
        return '中专';
    }else if(value == 4){
        return '大专';
    }else if(value == 5){
        return '本科';
    }else if(value == 6){
        return '硕士';
    }else if(value == 7){
        return '博士';
    }else if(value == 8){
        return '博士以上';
    }else{
        return value;
    }
});
//工作性质
template.helper("jobProperty", function (value) {
    if(value == 1){
        return '全职'
    }else if(value == 2){
        return '兼职';
    }else if(value == 3){
        return "实习";
    }else if(value == 4){
        return "外包";
    }else if(value == 5){
        return "派遣";
    }else if(value == 6){
        return "正式";
    }else{
        return value;
    }
});
//员工状态   1：在职 2：待岗 3：长期休假 4：借调 5：离职 6：退休 7：死亡
template.helper("employeeStatus", function (value) {
    if(value == 1){
        return '在职'
    }else if(value == 2){
        return '待岗';
    }else if(value == 3){
        return "长期休假";
    }else if(value == 4){
        return "借调";
    }else if(value == 5){
        return "离职";
    }else if(value == 6){
        return "退休";
    }else if(value == 7){
        return "死亡";
    }else{
        return value;
    }
});

//劳动纠纷状态
template.helper("disputeStatusFun", function (value) {
    if(value == 1){
        return '已解决'
    }else if(value == 2){
        return '未解决';
    }else{
        return value;
    }
});

//薪资方案状态
template.helper("salarySchemeStateFun", function (value,id) {
    if(value == 1){
        return '<span data-id=' + id + ' class="state open">启用</span>'
    }else if(value == 0){
        return '<span data-id=' + id + ' class="state">禁用</span>';
    }
});

//薪资方案状态
template.helper("salarySchemeItemStateFun", function (value) {
    if(value == 1){
        return '<span class="state open">是</span>'
    }else if(value == 0){
        return '<span class="state">否</span>';
    }
});

template.helper("messageType", function (value) {
    switch (value) {
        case 1:
            return "【生日提醒】"
        case 2:
            return "【合同到期】"
        case 3:
            return "【退休提醒】"
        case 4:
            return "【】"
        case 5:
            return "【】"
    }
});


//消息内容正则拼接
template.helper("messageContent", function (value) {
    switch (value) {
        case 1:
            return "的生日快到了"
        case 2:
            return "的合同快到期了"
        case 3:
            return "快到退休年龄了"
        case 4:
            return "【】"
        case 5:
            return "【】"
    }
});

//操作者正则
template.helper("operateNameFun", function (value) {
    if(value==""||value==undefined){
        return "系统管理员"
    }else{
        return value;
    }

});

//显示百分比数字
template.helper("percentNumFun", function (value) {
	if (value != 0){
		return accMul(value,100) + '%';
	}else{
		return 0;
	}
});

//除法
function accDiv(arg1,arg2) {
    var t1=0,t2=0,r1,r2;
    try{t1=arg1.toString().split(".")[1].length}catch(e){}
    try{t2=arg2.toString().split(".")[1].length}catch(e){}
    with(Math){
        r1=Number(arg1.toString().replace(".",""))
        r2=Number(arg2.toString().replace(".",""))
        return accMul((r1/r2),pow(10,t2-t1));
    }
}

//乘法
function accMul(arg1,arg2) {
    var m=0,s1=arg1.toString(),s2=arg2.toString();
    try{m+=s1.split(".")[1].length}catch(e){}
    try{m+=s2.split(".")[1].length}catch(e){}
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}

// 加法
function accAdd(arg1,arg2) {
    var r1,r2,m;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2))
    return (arg1*m+arg2*m)/m
}

//减法
function Subtr(arg1,arg2) {
    var r1,r2,m,n;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2));
    n=(r1>=r2)?r1:r2;
    return ((arg1*m-arg2*m)/m).toFixed(n);
}


//是否已经邀请面试了
template.helper("resumeFun", function (value,id) {
    if(value==0||value==undefined){
        return '<a class="opt-invite opt-addAudition" title="未邀请" data-id="'+id+'" data-status="'+value+'"></a>'
    }else if(value==1){
        return '<a class="opt-invite2"  title="已邀请" data-id="'+id+'" data-status="'+value+'"></a>';
    }

});


//是否可上移或下移
template.helper("upDownFun", function (i,size) {
    if(i==0||i==size){
        return '"display: none;'
    }else{
        return value;
    }

});

//获取url参数
function getURLParameter(name) {
	return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null;
}


