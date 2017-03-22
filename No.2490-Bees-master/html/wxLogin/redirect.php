<?php
error_reporting(E_ERROR);
require_once 'core.class.php';
$wx= new wx();
$code =$_GET['code'];
$state = $_GET['state'];
$nst = $wx->get_info($code,$state);

if(!empty($nst)){
	
	header("Location: http://beeslm.com/redirectLogin.html?openid=".$nst['openid']);
}


?>