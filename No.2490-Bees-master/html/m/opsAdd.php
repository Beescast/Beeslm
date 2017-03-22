<?php 
    include_once 'header.php';
    $id=$_GET['id'];
    if($id){        
       
        $user=json_decode(get_data(array('id'=>$id),'admin/info'),true);
        $user=$user['result'];
    }
?>
<script type="text/javascript">
    <?php 
        if($id){
            echo 'var ajaxUrl="ac=admin/edit";';
        }else{
            echo 'var ajaxUrl="ac=admin/add";';
        }
    ?>
    function edit () {
        ids=check_ids();
        if(ids!=""){
            $('#ops').val(ids);
            submits();
        }
    }
    function success (data) {
        if(data.code==200){
            alert('修改成功');
            window.location.href="ops.php";
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
                        <h3>后台管理用户设置</h3>
                        

                    </div>
                    <div class="box-body">
            <form class="form-horizontal" id="myform" action="" enctype="multipart/form-data" method="post" name="" >


            <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">登录账号</label>
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="name" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="<?php echo $user['name'];?>" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>

             <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">登录密码</label>
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="password" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>

            <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">
            分配权限：
            </label>
            <table align="center" width="80%" border="0" cellpadding="0" cellspacing="0" >
            <?php 
                $menu_count=count($menus);
                $this_ops=explode(",", $user['ops']);
                for ($i=0; $i <4; $i++) { 
                    for ($j=0; $j < 6; $j++) { 
                        $k=$i*6+$j;
                        if($k>=$menu_count)break;
                        if($menus[$k]['ops']===0)continue;
                        if($j==0){
                            echo '<tr>';
                        }
                        if($user&&in_array($menus[$k]['ops'], $this_ops)){
                            $checked="checked";
                        }else{
                            $checked="";
                        }
                        ?>
                        <td >
                            <input <?php echo $checked;?> type="checkbox" class="check" value="<?php echo $menus[$k]['ops'];?>">
                        </td>
                        <td><?php echo $menus[$k]['ops'].'.'.$menus[$k]['name'];?></td>
                        <?php
                        if($j==5){
                            echo '</tr>';
                        }
                    }
                    
                }

            ?>
        </table>
            <div class="col-sm-4" style="padding-top:7px;"></div>
           


            <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="id" value="<?php echo $id;?>">
                <input type="hidden" name="ops" id="ops" value="<?php echo $user['ops'];?>">
              <a href="javascript:void(0)" onclick="edit()" class="btn btn-success">保存</a>
            </div>
          </div>
      </form>

        </div><!-- box-body -->
    </div><!-- printArea -->
</div><!-- col-lg-12 -->
</div><!-- row -->