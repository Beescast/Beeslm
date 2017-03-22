<?php 
    include_once 'header.php';
    $id=$_GET['id'];
    if($id){        
        $info=json_decode(get_data(array('id'=>$id),'banner/info'),true);
        $info=$info['result'];

    }
?>
<link rel="stylesheet" href="../maniac/js/plugins/uploadify.css" />
<script src="../maniac/js/plugins/jquery.uploadify.min.js" type="text/javascript"></script>
<script type="text/javascript">
    <?php 
        if($id){
            echo 'var ajaxUrl="ac=banner/edit";';
        }else{
            echo 'var ajaxUrl="ac=banner/add";';
        }
    ?>
    <?php $timestamp = time();?>
    $(function() {
        $('#file_upload').uploadify({
            'formData'     : {
                'timestamp' : '<?php echo $timestamp;?>',
                'token'     : '<?php echo md5('unique_salt' . $timestamp);?>'
            },
            'dataType':'json',
            'swf'      : 'maniac/js/plugins/uploadify.swf',
            'uploader' : '<?php echo INTERFACE_URL."file/uploadFiles";?>',
            'onUploadSuccess' : function(file, data, response) {
                var datas=$.parseJSON(data);
                $("#picUrl").val(datas.data.response.urls)

            }
        });
    });
    function success (data) {
        if(data.code==200){
            alert('修改成功');
            window.location.href='mainPic.php';
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
                        <h3>列表</h3>
                        

                    </div>
                    <div class="box-body">
                    <form class="form-horizontal" role="form" action="" enctype="multipart/form-data" method="post" id="myform" >

            <div class="form-group" >
            <label for="role" class="col-sm-2 control-label">页面名称</label>
            <div class="col-sm-6">
           <?php create_optiones($pic_name,'page',null,"所有",$info['page']);?>

            </div>

            </div>
             <div class="form-group" >
            <label for="role" class="col-sm-2 control-label">图片位置</label>
            <div class="col-sm-6">
            <?php create_optiones($pic_position,'position',null,"所有",$info['position']);?>
            </div>
            </div>

            <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">显示顺序</label>
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="picOrder" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="<?php echo $info['picOrder'];?>" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>

             <div class="form-group">
            <label for="avatar" class="col-sm-2 control-label">图片(1920*840)</label>
            <div class="col-sm-6">
            <?php 
                if($info['picUrl']){
                    echo '<img src='.IMAGE_URL.$info['picUrl'].' width="200">';
                }
            ?>
            <input id="file_upload" multiple type="file" title="选择文件" >
            <input type="hidden" value="<?php echo $info['picUrl'];?>" name="picUrl" id="picUrl">
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>

              <div class="form-group" id="" >
            <label for="email" class="col-sm-2 control-label">链接地址</label>
            
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="picHref" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="<?php echo $info['picHref'];?>" >
            <p class="help-block"></p>
            </div>
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