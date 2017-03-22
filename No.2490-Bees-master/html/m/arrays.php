<?php
	$order_pay_status=array(0=>'钱包',1=>'支付宝',2=>'微信',3=>'银行');
	$order_status=array(0=>'待付款',1=>'待启动',2=>'上线中',3=>'已结束',4=>'已评价',9=>'已取消');
	$pic_name=array('index'=>'首页','shop'=>'小商城','notice'=>'公告页');
	$pic_position=array(0=>'通栏轮播',1=>'中间图片',2=>'右侧图片');
	$user_auth_status=array(0=>'待审核',1=>'已通过',2=>'未通过');
	$balance_payments=array(1=>'+',2=>'-');
    $user_balance=array(1=>'余额为0',2=>'余额不为0');
    $business=array(1=>'购买套餐',2=>'充值',3=>'提现');
    $notice_status=array(0=>'关闭',1=>'开启');

    $task_pay_status=array(0=>'待完成',1=>'待付款',2=>'已结束',3=>'未完成');
    $task_status=array(0=>'招募中',1=>'招募完成',);
    $task_type=array(0=>'普通任务',5=>'高级任务');
    $order_status=array(0=>'待付款',1=>'待启动',2=>'上线中',3=>'已结束',4=>'已评价',9=>'已取消');
    $task_bid_status=array(2=>'取消中标',1=>'中标');
    $wallet_cash_status=array(0=>'待处理',1=>'已通过',2=>'未通过');
    $wallet_business_type=array(1=>"充值",2=>'提现',3=>'购买套餐',4=>'赏金收益');

	function create_optiones($array,$name,$value=null,$key='所有',$select=null){
		$str='<select class="form-control" name="'.$name.'" id="search_'.$name.'">
                <option class="" value="'.$value.'">'.$key.'</option>';
        foreach ($array as $key => $ar) {
            if($select!=null&&$key==$select){
                $str.='<option selected="" value="'.$key.'">'.$ar.'</option>';
            }else{
                $str.='<option value="'.$key.'">'.$ar.'</option>';
            }
    		
        }
        $str.='</select>';
        echo $str;
	}

	function make_change(&$resultList,$keys){
		foreach ($resultList as $key => &$list) {

			foreach ($keys as $k=>$v) {
				$list[$k]=$v[$list[$k]];
			}
        }
    }

    function get_data($data,$ac){
        $data['adminSessionKey']=$_SESSION['seesionKey'];
        $ch = curl_init();
        curl_setopt($ch,CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($ch, CURLOPT_URL,INTERFACE_URL.$ac);
        curl_setopt($ch, CURLOPT_POST, 1 );    
        curl_setopt($ch, CURLOPT_HEADER, 0 ) ;    
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
        $res=curl_exec($ch);

        //echo $ip.$ac;echo $res;print_r($data);die();
        $res = json_decode($res,true);

        curl_close($ch);

        if ($res['code']==200){
            $res=$res['data']['response'];      
            if($ac=='admin/login'){
                $_SESSION['seesionKey']=$res['sessionKey'];
                $_SESSION['uid']=$res['uid'];
                $_SESSION['ops']=explode(",", $res['ops']);
                
            }
            if(isset($res['resultList'])){
                if($ac=='banner/list'){
                    global $pic_name;
                    global $pic_position;
                    make_change($res['resultList'],array('page'=>$pic_name,'position'=>$pic_position));
                    
                }elseif($ac=="orderLog/adminList"){
                    global $order_pay_status;
                    global $balance_payments;
                    global $business;
                    make_change($res['resultList'],array('payType'=>$order_pay_status,'incomeType'=>$balance_payments,'type'=>$business));
                }elseif($ac=="notice/list"){
                    global $notice_status;
                    make_change($res['resultList'],array('status'=>$notice_status));
                }elseif($ac=="task/adminList"){
                    global $task_type;
                    global $task_status;
                    make_change($res['resultList'],array('catId'=>get_task_cat(),'type'=>$task_type,'status'=>$task_status));
                }elseif($ac=='task/getSignList'){
                    global $task_bid_status;
                    global $task_type;
                    global $task_status;
                    global $task_pay_status;
                    make_change($res['resultList'],array('bidStatus'=>$task_bid_status,'catId'=>get_task_cat(),'type'=>$task_type,'status'=>$task_status,'payStatus'=>$task_pay_status));
                }elseif($ac=='wallet/adminCashList'){
                    global $wallet_cash_status;
                    make_change($res['resultList'],array('status'=>$wallet_cash_status));
                }elseif($ac=='wallet/adminList'){
                    global $wallet_business_type;
                    global $balance_payments;
                    make_change($res['resultList'],array('businessType'=>$wallet_business_type,'payType'=>$balance_payments));
                }

                $res['draw']=$data['draw'];
                $res['recordsTotal']=$res['total'];
                $res['recordsFiltered']=$res['total'];
                $res['data']=$res['resultList'];
                unset($res['resultList']);
            }

            $res['code']=200;
        }
        return json_encode($res);
    }

    function get_task_cat(){
        $task_category=json_decode(get_data(null,'task/catList'),true);

        $task_category=$task_category['data'];
        foreach ($task_category as $key => $task) {
            if($task['id']==0||$task['id']==-1)continue;
            $task_categorys[$task['id']]=$task['name'];
        }
        return $task_categorys;
    }