<?php
    require('config.php');
	session_start();
	$ac=$_REQUEST['ac'];    
    
	$data=$_POST;
	unset($data['columns']);
	unset($data['order']);
	unset($data['search']);
	if(!in_array($ac, array('live/market','live/gift'))){
		if($data['endTime'])$data['endTime']=$data['endTime'].' 23:59:59';	
	}
	
	$data['start']=$_POST['start'];
	$data['limit']=$_POST['length'];

    echo get_data($data,$ac);

	

    die();
?>