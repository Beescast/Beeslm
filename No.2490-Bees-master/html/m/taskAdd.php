<?php 
    include_once 'header.php';

    $id=$_GET['id'];
    if($id){        
        $info=json_decode(get_data(array('id'=>$id),'task/info'),true);
        $info=$info['result'];
    }
    $task_category=get_task_cat();
?>
<script type="text/javascript">
    <?php 
        if($id){
            echo 'var ajaxUrl="ac=task/edit";';
        }else{
            echo 'var ajaxUrl="ac=task/add";';
        }
    ?>
   
    function success (data) {
        if(data.code==200){
            alert('修改成功');
            window.location.href='taskList.php';
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
                        <h3>赏金任务发布</h3>
                        

                    </div>
                    <div class="box-body">
                    <form class="form-horizontal" role="form" action="" enctype="multipart/form-data" method="post" id="myform" >

            <div class="form-group" >
            <label for="role" class="col-sm-2 control-label">任务分类</label>
            <div class="col-sm-6">
           <?php create_optiones($task_category,'catId',$info['catId']);?>

            </div>

            </div>
             <div class="form-group" >
            <label for="role" class="col-sm-2 control-label">任务类型</label>
            <div class="col-sm-6">
            <?php create_optiones($task_type,'type',$info['type']);?>
            </div>
            </div>

            <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">任务标题</label>
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="title" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="<?php echo $info['title'];?>" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>

             <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">总赏金</label>
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="price" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="<?php echo $info['price'];?>" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>

              <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">所需人数</label>
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="num" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="<?php echo $info['num'];?>" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>
              <div class="form-group" >
            <label class="col-sm-2 control-label">任务说明</label>
            <div class="col-sm-10">
                <textarea type="text" name="illustration" cols="80" rows="10"><?php echo $info['illustration'];?></textarea>
            </div>

            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>
            <?php 
                if(!$id){
                    ?>
                
            <div class="form-group">
            <label class="col-sm-2 control-label" for="status">
            消息推送
            </label>

            <div id="de" class="col-sm-6" style="width: 80%;">
            <th class="fc-day-header fc-mon fc-widget-header" style="width: ;"> 平台公告</th>
            <th class="fc-day-header fc-mon fc-widget-header" style="text-align:center;">
            <input id="turnout1_1" type="checkbox" name="notice" value="1">
            </th>
            <th class="fc-day-header fc-mon fc-widget-header" style="width: ;">主播信息</th>
            <th class="fc-day-header fc-mon fc-widget-header" style="text-align:center;">
            <input id="turnout1_1" type="checkbox" name="msg" value="1">
            </th>
            </div>
            </div>
            <?php } ?>
            <div class="col-sm-4" style="padding-top:7px;"></div>
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