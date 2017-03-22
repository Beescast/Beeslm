<?php 
    include_once 'header.php';
    $id=$_GET['id'];
    if($id){        
        $infos=json_decode(get_data(array('id'=>$id),'package/info'),true);
        $info=$infos['result'];        
        $prices=$infos['prices'];
    }
?>
<link rel="stylesheet" href="../maniac/js/plugins/uploadify.css" />
<script src="../maniac/js/plugins/jquery.uploadify.min.js" type="text/javascript"></script>
<script type="text/javascript">
    var ajaxUrl='ac=package/edit';
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
                $("#pic").val(datas.data.response.urls)

            }
        });
    });
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

            <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">套餐名称</label>
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="name" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="<?php echo $info['name'];?>" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>
            <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">副标题一</label>
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="titleOne" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="<?php echo $info['titleOne'];?>" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>

            <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">副标题二</label>
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="titleTwo" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="<?php echo $info['titleTwo'];?>" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>

            <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">副标题三</label>
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="titleThree" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="<?php echo $info['titleThree'];?>" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>


             <div class="form-group">
            <label for="avatar" class="col-sm-2 control-label">主图</label>
            <div class="col-sm-6">
            <?php 
                if($info['pic']){
                    echo '<img src='.IMAGE_URL.$info['pic'].' width="200">';
                }
            ?>
            <input id="file_upload" multiple type="file" title="选择文件" >
            <input type="hidden" name="pic" id="pic" value="<?php echo $info['pic'];?>">
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>
            
            
            <div id="div_prices">
                <div class="form-group">
                    <label for="avatar" class="col-sm-2 control-label"></label>
                    <div class="col-sm-6">
                    <a href="javascript:void(0)" class="btn btn-success" onclick="add_type()">增加种类</a>
                    </div>
                </div>
                <?php
                    foreach ($prices as $key => $price) {
                ?>
                <div class="form-group type_price" id="type_price_<?php echo $price['price'];?>">
                    <label for="mobile" class="col-sm-2 control-label">种类与价格</label>
                    <input type="hidden" value="<?php echo $price['id'];?>" class="price_id">
                    <div class="col-sm-3">
                        <input class="form-control price_name" autocomplete='off' type="text" data-inputmask="'mask': ''" placeholder="" size="20" value="<?php echo $price['name'];?>" >
                    </div>
                    <div class="col-sm-3">
                        <input class="form-control price_price" autocomplete='off' type="text" data-inputmask="'mask': ''" placeholder="" onkeyup="this.value=this.value.replace(/\D/g,'')" size="20" value="<?php echo $price['price'];?>" >
                    </div>
                    <a href="javascript:void(0)" class="btn btn-success" onclick="del_type('<?php echo $price['price'];?>',<?php echo $price['id'];?>,null)">删除种类</a>
                </div>
                <?php        
                    }
                ?>
            </div>
            
            

            <div class="form-group" >
            <label class="col-sm-2 control-label">详细内容</label>
            <div class="col-sm-10">
                <textarea class="ckeditor" type="text" name="content" cols="80" rows="10" value="<?php echo $info['content'];?>"> <?php echo $info['content'];?></textarea>
            </div>
         
            </div>

            <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="prices" id="prices">
                <input type="hidden" name="id" value="<?php echo $_GET['id'];?>">
              <a href="javascript:void(0)" onclick="local_sub()"class="btn btn-success">提交</a> 
              
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
    <script type="text/javascript">
        $("input[name='fangwenyudinhuishu']").keyup(function(){       
            var tmptxt=$(this).val();       
            $(this).val(tmptxt.replace(/\D|^0/g,''));       
        }).bind("paste",function(){       
            var tmptxt=$(this).val();
            $(this).val(tmptxt.replace(/\D|^0/g,''));       
        }).css("ime-mode", "disabled");      
        var type_ids=1;
        function add_type() {
            var str='<div class="form-group type_price" id="type_ids_'+type_ids+'"><label for="mobile" class="col-sm-2 control-label">种类与价格</label><div class="col-sm-3"><input class="form-control price_name" type="text"  size="20"></div><div class="col-sm-3"><input class="form-control price_price" onkeyup="this.value=this.value.replace(/\\D/g,\'\')" type="text"  size="20" ></div><a href="javascript:void(0)" class="btn btn-success" onclick="del_type(null,null,'+type_ids+')">删除种类</a></div>';
            $('#div_prices').append(str);
            type_ids++;
        }
        function local_sub () {

            var a = new Array(); 
            $('.type_price').each(function(index, el) {
                var ob={};
                $(el).find("input").each(function(i, inp) {
                    
                    if($(inp).hasClass('price_id')){
                        ob.id=$(inp).val();
                    }else if($(inp).hasClass('price_name')){
                        ob.name=$(inp).val();
                    }else if($(inp).hasClass('price_price')){
                        ob.price=$(inp).val();
                    }
                });
                a[index]=ob;
                
            });
            $('#prices').val(JSON.stringify(a));
            for ( instance in CKEDITOR.instances )CKEDITOR.instances[instance].updateElement(); 
            submits();
        }
        function success (data) {
        if(data.code==200){
            alert('修改成功');
            window.location.href='package.php?id=<?php echo $_GET['id'];?>';
        }else{
            alert(data.messages.message);
        }
    }
    function del_type (price,price_id,type_id) {
        if(confirm('是否删除')){
            if(price!=null){
                $('#type_price_'+price).remove();
                $.post('interface.php?ac=package/delPrice', {id: price_id}, function(data, textStatus, xhr) {
                    
                });
                //ajaxUrl='ac=package/edit';
            }else if(type_id!=null){
                $('#type_ids_'+type_id).remove();
            }
        }
        
    }
    </script>
</body>
<?php include_once 'footer.php';?>
</html>