<?php 
    include_once 'header.php';

    $id=$_GET['id'];
    if($id){        
        $info=json_decode(get_data(array('id'=>$id),'order/info'),true);
        
        $info=$info['result'];
    }
    //$task_category=get_task_cat();
?>
<script type="text/javascript">
    var ajaxUrl="ac=order/startPackage";
   
    function success (data) {
        if(data.code==200){
            alert('修改成功');
            window.location.href='index.php';
        }else{
            alert(data.messages.message);
        }
    }
</script>
    <div class="box">

        <!-- 列表部分 -->  
        <div class="row">

            <div class="col-lg-12">

                <div id="printArea" class="box">

                     <div class="box-title">
                        <h3>启动套餐订单</h3>
                        

                    </div>
                    <div class="box-body">
                    <form class="form-horizontal" role="form" action="" enctype="multipart/form-data" method="post" id="myform" >

            <div class="form-group" >
	            <label for="role" class="col-sm-2 control-label">订单号</label>
	            <div class="col-sm-6">
	            	<?php echo $info['id'];?>
	            </div>
            </div>

             <div class="form-group" >
	            <label for="role" class="col-sm-2 control-label">商品名称</label>
	            <div class="col-sm-6">
	            <?php echo $info['packageName'];?>
	            </div>
            </div>

            <div class="form-group" id="" >
	            <label for="mobile" class="col-sm-2 control-label">商品价格</label>
	            <div class="col-sm-6">
	            <?php echo $info['price'];?>
	            </div>
            </div>

             <div class="form-group" id="" >
	            <label for="mobile" class="col-sm-2 control-label">结束时间</label>
	            <div class="col-sm-6">
	           	<input class="form-control" type="text" style="width:180px;" value="" id="endDate" size="35" placeholder="" data-inputmask="'mask': ''" name="endDate" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate: new Date() })" >
	           	</div>
            </div>

            <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <input type="hidden" name="id" value="<?php echo $id;?>">
              <a href="javascript:void(0)" onclick="submits()"class="btn btn-success">提交</a>
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