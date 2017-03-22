<?php 
    include_once 'header.php';
    $id=$_GET['id'];
    if($id){        
        $info=json_decode(get_data(array('id'=>$id),'wallet/adminCashList'),true);
        $info=$info['data'][0];
        $user=json_decode(get_data(array('uid'=>$info['uid']),'user/info'),true);
        $user=$user['result'];
    }else{
        header("Location: walletCashList.php");
    }
?>
<script type="text/javascript">
    var ajaxUrl='ac=wallet/cashOp';
    
    function local_submits() {
        if($('#reseaon').val()==""){
            alert('请填写理由');
            return false;
        }
        submits();
    }

    function success (data) {
        if(data.code==200){
            alert('操作成功');
            window.location.href='walletCashList.php';
        }else{
            alert(data.messages.message);
        }
    }
</script>
<div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>提现不通过</h3>
        
    </div>
    
    <div class="box-body">
    
        <div class="row">

            <div class="col-lg-12">

            <div id="printArea" class="box">
            </div><div class="box-body"><form class="form-horizontal" role="form" action="" enctype="multipart/form-data" method="post" id="myform" >
 
            <div class="form-group" id="" >

                <label for="name" class="col-sm-2 control-label">用户姓名：</label>
                <div class="col-sm-2" style="padding-top:7px;"><?php echo $user['truename'];?>
                </div>
                 
             </div>
         <div class="form-group" id="" >
                <label for="name" class="col-sm-2 control-label">提现金额</label>

                <div class="col-sm-2" style="padding-top:7px;"><?php echo $info['money'];?>
                </div>
                 
        </div>

        <div class="form-group" id="" >
            <label for="name" class="col-sm-2 control-label">银行名称：</label>

                <div class="col-sm-2" style="padding-top:7px;"><?php echo $info['bankName'].$info['subBankName'];?>
                </div>
        </div>
         <div class="form-group" id="" >
            <label for="name" class="col-sm-2 control-label">不通过理由：</label>

                <div class="col-sm-2" style="padding-top:7px;">
                    <input class="form-control" type="text" style="width:380px;" name="reseaon" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="reseaon" autocomplete="off">
                </div>
        </div>
        <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input type="hidden" name="id" value="<?php echo $id;?>"> 
            <input type="hidden" name="status" value="2">
        <a class="btn btn-success" href="javascript:void(0)" onclick="local_submits()">确认</a>
        <a class="btn btn-primary" href="walletCashList.php">取消</a>
        </div>
        </div>
    </form>

          