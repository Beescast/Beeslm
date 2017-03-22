/**
 * Created by chen on 2016/8/1.
 */
$.validator.addMethod("isMobile", function(value, element) {
    var tel = /^1\d{10}$/;
    return this.optional(element) || (tel.test(value));
}, "请输入正确手机号");

$.validator.addMethod("email", function(value, element) {
    var tel = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    return this.optional(element) || (tel.test(value));
}, "请输入正确邮箱");

$.validator.addMethod("isIdCardNo", function(value, element) {
return this.optional(element) || isIdCardNo(value);
}, "请正确输入您的身份证号码,支持大写X哦");

$.validator.addMethod("string", function(value, element){
return this.optional(element) ||/^[a-zA-Z0-9-\u0391-\uFFE5]+$/.test(value);
}, "不允许包含特殊符号!");

$.validator.addMethod("isChinaName", function(value, element){
return this.optional(element) ||/^[\u4E00-\u9FA5]{1,6}$/.test(value);
}, "请填写正确姓名!");
$.validator.addMethod("isPass", function(value, element){
return this.optional(element) || /[0-9]+[a-zA-Z]+[0-9a-zA-Z]*|[a-zA-Z]+[0-9]+[0-9a-zA-Z]/.test(value);
}, "请填写正确密码6-18位字母+数字组合!");
 $.validator.addMethod("isPass", function(value, element){
return this.optional(element) || /[0-9]+[a-zA-Z]+[0-9a-zA-Z]*|[a-zA-Z]+[0-9]+[0-9a-zA-Z]/.test(value);
}, "请填写正确密码6-18位字母+数字组合!");

 $.validator.addMethod("money", function(value, element){
return this.optional(element) || /^((\d*[1-9])|(0?\.\d{2}))$/.test(value);
}, "请输入大于0的金额!");

//增加身份证验证
function isIdCardNo(num) {
    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2","x");
    var varArray = new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;
    // initialize
    if ((intStrLen != 15) && (intStrLen != 18)) {
        return false;
    }
    // check and set value
    for (i = 0; i < intStrLen; i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i] * factorArr[i];
        }
    }

    if (intStrLen == 18) {
        //check date
        var date8 = idNumber.substring(6, 14);
        if (isDate8(date8) == false) {
            return false;
        }
        // calculate the sum of the products
        for (i = 0; i < 17; i++) {
            lngProduct = lngProduct + varArray[i];
        }
        // calculate the check digit
        intCheckDigit = parityBit[lngProduct % 11];
        // check last digit
        if (varArray[17] != intCheckDigit) {
            return false;
        }
    }
    else {        //length is 15
        //check date
        var date6 = idNumber.substring(6, 12);
        if (isDate6(date6) == false) {
            return false;
        }
    }
    return true;
}
function isDate6(sDate) {
    if (!/^[0-9]{6}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    if (year < 1700 || year > 2500) return false
    if (month < 1 || month > 12) return false
    return true
}

function isDate8(sDate) {
    if (!/^[0-9]{8}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    day = sDate.substring(6, 8);
    var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if (year < 1700 || year > 2500) return false
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
    if (month < 1 || month > 12) return false
    if (day < 1 || day > iaMonthDays[month - 1]) return false
    return true
}