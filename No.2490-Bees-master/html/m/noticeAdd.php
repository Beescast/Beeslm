<?php 
    include_once 'header.php';
    $id=$_GET['id'];
    if($id){        
        $info=json_decode(get_data(array('id'=>$id),'notice/info'),true);
        $info=$info['result'];
    }
?>
<script type="text/javascript">
    var id="<?php echo $_GET['id'];?>";
    var ajaxUrl='ac=notice/edit';
    if(id==""){
        var ajaxUrl='ac=notice/add';
    }
    function success (data) {
        if(data.code==200){
            alert('修改成功');
            window.location.href='notice.php';
        }else{
            alert(data.messages.message);
        }
    }
    
    $(document).ready(function() {
        if(id!=""){
            $.post('interface.php?ac=notice/info', {id: id}, function(data, textStatus, xhr) {
                var res=data.result;
                $('#title').val(res.title);
                $('#content').text(res.content);
            },'json'
            );
        }
        
    });
</script>
    <div class="box">

        <!-- 列表部分 -->  
        <div class="row">

            <div class="col-lg-12">

                <div id="printArea" class="box">

                     <div class="box-title">
                        <h3>公告发布和修改</h3>
                        

                    </div>
                    <div class="box-body">
                    <form class="form-horizontal" role="form" action="" enctype="multipart/form-data" method="post" id="myform" >


            <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">公告标题</label>
            <div class="col-sm-6">
                <input class="form-control" autocomplete='off' type="text" name="title" id="title"
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>
            <div class="form-group" >
            <label class="col-sm-2 control-label">状态</label>
            <div class="col-sm-10">
                <input autocomplete='off' type="radio" name="status" value="1"<?php if($info['status']){echo 'checked';}?> >开启
                <input autocomplete='off' type="radio" name="status" value="0" <?php if(!$info['status']){echo 'checked';}?>>关闭
                </div>
            </div>
             
            <div class="form-group" >
            <label class="col-sm-2 control-label">详细内容</label>
            <div class="col-sm-10">
                <textarea class="ckeditor" type="text" id="content" name="content" cols="80" rows="10" ></textarea>
            </div>
            
            </div>
            <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="id" value="<?php echo $_GET['id'];?>">
              <a href="#" onclick="if(confirm('是否保存')){for ( instance in CKEDITOR.instances )CKEDITOR.instances[instance].updateElement();submits()}" class="btn btn-success">提交</a> 
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