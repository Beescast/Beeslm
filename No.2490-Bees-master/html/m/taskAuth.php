<?php 
    include_once 'header.php';
    $id=$_GET['id'];
    if($id){        
        $infos=json_decode(get_data(array('id'=>$id),'task/getSignList'),true);
        //var_dump($infos);die();
        $sign=$infos['data'][0];
        //var_dump($sign);die();
        $infos=json_decode(get_data(array('id'=>$sign['taskId']),'task/info'),true);
        $info=$infos['result'];
        //var_dump($info);die();
        //$prices=$infos['prices'];

        $pics=json_decode(get_data(array('taskId'=>$sign['taskId'],'uid'=>$sign['uid']),'task/getSignPicList'),true);
        $pics=$pics['data'];
       
    }
?>

    <div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>赏金任务审核</h3>
        
    </div>
    <div class="box-body">



    <div style="clear:both;"></div>

    </div><!-- /.box -->
        <!-- 列表部分 -->  
        <div class="row">

            <div class="col-lg-12">

            <div id="printArea" class="box">
            </div><div class="box-body"><form class="form-horizontal" role="form" action="" enctype="multipart/form-data" method="post" name="" >
              <table class="table table-bordered">
            <tbody>
            <tr class="firstalt">
            <td width="15%">任务id</td>
            <td width="10%" style="text-align:left"><?php echo $id;?></td>
            <td width="10%">任务类型</td>
            <td width="15%" style="text-align:left"><?php echo $sign['catId'];?></td>
            <td width="25%">任务赏金</td>
            <td width="10%" style="text-align:left"><?php echo $info['price'];?></td>
            </tr>
 
            <tr class="secondalt">
            <td width="15%">所需人数</td>
            <td width="10%" style="text-align:left"><?php echo $info['num'];?></td>
            <td width="10%">单份赏金</td>
            <td width="15%" style="text-align:left"><?php echo $sign['singlePrice'];?></td>
            <td width="15%">主播姓名</td>
            <td width="20crons%" style="text-align:left"><?php echo $sign['truename'];?></td>
            </tr>
            
            <tr class="firstalt">
            <td width="15%">主播账号</td>
            <td width="10%" style="text-align:left"><?php echo $sign['mobile'];?></td>
            <td width="" style="text-align:center"><a href="javascript:void(0)" onclick="auth(1)" class="btn btn-primary btn-sm" >通过并支付赏金</a>
            <td width="" style="text-align:right" ><a href="javascript:void(0)"  onclick="auth(0)" class="btn btn-primary btn-sm" >不通过</a></td>

            <td width="15%" > <input class="form-control" autocomplete='off' type="text" id="reason"
            data-inputmask="'mask': ''" placeholder="填写不通过的理由，并且短信通知用户" 
            size="35" value=""   ></td>
            <td width="20crons%" style="text-align:left"></td>
            </tr>
            </tbody>

            </table>

            <div class="box-body">
            </div>

            <div style ='margin-left:10px;margin-bottom:30px;widh:1500px;'>截图：
                <?php foreach ($pics as $key => $pic) {?>
                    <img src ='<?php echo IMAGE_URL.$pic['picUrl'];?>' onclick="javascript:showimage('<?php echo IMAGE_URL.$pic['picUrl'];?>');"  style ='width:250px;'>
                <?php }?>
            </div>

                </div><!-- printArea -->
            </div><!-- col-lg-12 -->
         </div><!-- row -->
        </div><!-- /.content -->
        </div><!-- /.rightside -->
    </div><!-- /.wrapper -->
    <script type="text/javascript">
        function auth (type) {
            if(confirm('是否执行此操作')){
                var reason="";
                if(type!=1){
                    reason=$('#reason').val();
                    if(reason==""){
                        alert("请填写不通过理由");
                        return false;
                    }
                }
                $.post('interface.php?ac=task/auth', {id: '<?php echo $id;?>',status:type,reson:reason}, function(data, textStatus, xhr) {
                    data=$.parseJSON(data);
                    if(data.code==200){
                        alert('操作成功');
                        window.location.href="taskAuthList.php";
                    }
                });
            }
            
        }
    </script>
</body>

<?php include_once 'footer.php';?>
</html>