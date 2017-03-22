<?php include_once 'header.php';?>
<script type="text/javascript">
    var ajaxUrl='ac=admin/setPassword';
    function local_sub () {
        if($('#newPassword').val()!=$('#rnewPassword').val()){
            alert('新密码不一致');
            return false;
        }
        submits();
    }
    function success (data) {
        if(data.code==200){
            alert('修改成功');
            window.location.href='login.php';
        }else{
            alert(data.messages.message);
        }
    }
</script>
<div class="box"> 
    <div class="row">
        <div class="col-lg-12">
            <div id="printArea" class="box">
                <div class="box-title">
                <h3>修改密码</h3>
                </div>
                <div class="box-body">
            <form class="form-horizontal" role="form" action="" enctype="multipart/form-data" method="post" id="myform" >

                <div class="form-group" id="" >
                <label for="password" class="col-sm-2 control-label">原密码</label>
                
                <div class="col-sm-6"><input class="form-control" autocomplete='off' type="password" name="oldPassword"
                data-inputmask="'mask': ''" placeholder="" 
                size="35" value="" >
                <p class="help-block"></p>
                </div>
                
                <div class="col-sm-4" style="padding-top:7px;"></div>
                </div>

                <div class="form-group" id="" >
                <label for="new_password" class="col-sm-2 control-label">新密码</label>
                
                <div class="col-sm-6"><input class="form-control" autocomplete='off' type="password" id="newPassword" 
                data-inputmask="'mask': ''" name="newPassword" placeholder="" 
                size="35" value="" >
                <p class="help-block"></p>
                </div>

                
                <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>
            <div class="form-group" id="" >
            <label for="renew_password" class="col-sm-2 control-label">确认密码</label>
            
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="password" id="rnewPassword" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="" >
            <p class="help-block"></p>
            </div>

            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>

        <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <a href="#" onclick="local_sub()" class="btn btn-success">提交</a> 

        </div>
      </div>
  </form>

                </div><!-- box-body -->
            </div><!-- printArea -->
        </div><!-- col-lg-12 -->
     </div><!-- row -->
    </div><!-- /.content -->
    </div><!-- /.rightside -->
</div><!-- /.wrapper -->
</body>
<?php include_once 'footer.php';?>
</html>