<?php
	ini_set('date.timezone','Asia/Shanghai');
	//error_reporting(E_ERROR);
	require_once '../config.php';
	$order_id=$_GET['id'];
	$total_fee=$_GET['totalFee'];
	$type=$_GET['type'];
	$session_key=$_GET['sessionKey'];
	if($type=="package"){
		$packages=get_data(array('id'=>$order_id,'sessionKey'=>$session_key),'order/info');
		$packages=json_decode($packages,true);
		$packages=$packages['result'];
		$total_fee=$packages['price'];
		$subject=$packages['packageName'];
		$product_id=$packages['packageId'];
	}elseif($type=="wallet"){
		$subject="充值";
		$product_id=-1;
		$order_id='WXPAY_'.date('YmdHis').rand(1000,9999);
	}
	require_once "lib/WxPay.Api.php";
	require_once "WxPay.NativePay.php";
	require_once 'log.php';

	//模式二
	/**
	 * 流程：
	 * 1、调用统一下单，取得code_url，生成二维码
	 * 2、用户扫描二维码，进行支付
	 * 3、支付完成之后，微信服务器会通知支付成功
	 * 4、在支付成功通知中需要查单确认是否真正支付成功（见：notify.php）
	 */
	$notify = new NativePay();
	$input = new WxPayUnifiedOrder();
	$input->SetBody($subject);
	$input->SetAttach("test");
	$input->SetOut_trade_no($order_id);
	$input->SetTotal_fee($total_fee*100);
	$input->SetTime_start(date("YmdHis"));
	$input->SetTime_expire(date("YmdHis", time() + 600));
	$input->SetGoods_tag($subject);
	$input->SetNotify_url(BACKEND_URL);
	$input->SetTrade_type("NATIVE");
	$input->SetProduct_id($product_id);
	$result = $notify->GetPayUrl($input);
	$url2 = $result["code_url"];
	require_once 'phpqrcode/phpqrcode.php';
	
	QRcode::png(urldecode($url2 ));
?>