<?php

$menus = array(
	'home'		=> array(
		'name'		=> '订单查询',
		'icon'		=> 'fa fa-home',
		'links'		=> .'/system/order.php',
	),
	'user'		=> array(
		'name'		=> '用户信息查询',
		'icon'		=> 'fa fa-codepen',
		'links'		=> .'/system/userInfo.php',
	),
	'check'	=> array(
		'name'		=> '注册认证审核',
		'icon'		=> 'fa fa-ge',
		'links'		=> .'/system/certificationList.php',
	),

	'imgConfig'	=> array(
		'name'		=> '主要图片配置',
		'icon'		=> 'fa fa-joomla',
		'links'		=> .'/system/userInfo.php',
	),
	'combModify'	=> array(
		'name'		=> '套餐内容修改',
		'icon'		=> 'fa fa-trophy',
		'links'		=> .'/system/userInfo.php',
	),
	'notice'	    => array(
		'name'		=> '公告查询',
		'icon'		=> 'fa fa-glass',
		'links'		=> array(
				'商户列表' => .'/merchant/merchant.php?action=list',
				'公告发布和修改' => .'/merchant/brand.php?action=list',
		),
	),
	/* 积生活 */
	'marchent' => array(
		'name'		=> '商户管理',
		'icon'		=> 'fa fa-codepen',
		'links'		=> array(
			'商户列表' => .'/merchant/merchant.php?action=list',
			'品牌管理' => .'/merchant/brand.php?action=list',
			//'合作商城' => .'/merchant/merchant.php?action=list&industry_id=129',
			'交通出行' => .'/merchant/merchant.php?action=list&industry_id=132',
			'出租车审核' => .'/merchant/taxi_examine.php?action=list&industry_id=132',
		),	
		
	),
);

?>