<?php include_once 'header.php';?>
<script type="text/javascript">
    var ajaxUrl="ac=admin/setPassword";
    function save () {
        if($('#repeatPass').val()==$('#newPassword').val()){
            submits();
        }else{
            alert('再次输入密码不一致');
        }
    }
    function success (data) {
        if(data.code==200&&data.result==true){
            alert('修改成功');
            //window.location.href='changePass.php';
        }else{
            alert('修改失败');
            //alert(data.messages.message);
        }
    }
</script>
    <div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>密码设置</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form" id="myform">

         <div class="form-group">
        <label>原密码</label>
        <input class="form-control" type="password" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" name="oldPassword" id="oldPassword" autocomplete="off">
        </div><br><br>

        <div class="form-group">
        <label>新密码</label>
        <input class="form-control" type="password" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" name="newPassword" id="newPassword" autocomplete="off">
        </div><br><br>

         <div class="form-group">
        <label>再次输入新密码</label>
        <input class="form-control" type="password" style="width:380px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="repeatPass" autocomplete="off">
        </div><br><br>


        <a href="#" class="btn btn-info" onclick="save()">修改</a>

        

    </div>
    </form>

<?php include_once 'footer.php';?>
</html>