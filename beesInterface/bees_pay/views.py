#coding:utf-8
from django.shortcuts import render
from django.http import HttpResponse
from bees_order.orderService import OrderService
import random
from django.views.decorators.csrf import csrf_exempt

@csrf_exempt
def alipay(request):
	orderId = request.POST.get('orderId')
	type = request.POST.get('type')
	totalFee = request.POST.get('totalFee')
	response = HttpResponse()
	return HttpResponse('未实现')
	subject = ""
	if type == "package":
		packageOrder = OrderService.getInfoById(orderId)
		subject = packageOrder.package_name
		totalFee =packageOrder.price
	elif type == "wallet":
		dateString = DateUtils.toDateFormat("yyyyMMdd") #时间格式有问题
		rnd = random
		randString =str(rnd.random(89999) + 10000)
		orderId = dateString + randString
		subject = "充值"
	sParaTemp = {}
	sParaTemp['out_trade_no'] = orderId
	sParaTemp['subject'] = subject
	sParaTemp['total_fee'] = totalFee
	sParaTemp['extra_common_param'] = type
	#其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
	#如sParaTemp.put("参数名","参数值")
	#建立请求
	sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认")
	# model.addAttribute("htmls", sHtmlText) #没有model类 ，此代码无效
	return #"alipay"

@csrf_exempt
def aliPayRes(request):
	return HttpResponse('未实现')

@csrf_exempt
def aliPayResNotify(request):
	return HttpResponse('未实现')

@csrf_exempt
def weixinPayRes(request):
	return HttpResponse('未实现')

@csrf_exempt
def wallet(request):
	return HttpResponse('未实现')




