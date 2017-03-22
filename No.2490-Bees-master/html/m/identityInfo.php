<?php 
    include_once 'header.php';
    $id=$_GET['id'];
    $uid=$_GET['uid'];

?>
<style type="text/css">
    td{width: 33%;text-align:center;padding:10px;}
    tr{min-height: 40px;margin: 10px;}
</style>
<script type="text/javascript">
        var id;
        $(document).ready(function() {
            <?php if($id){?>
                id=<?php echo $id;?>;
                $.post('interface.php?ac=user/authInfo', {id: <?php echo $id;?>}, function(data, textStatus, xhr) {
                    make_res(data.result);
                },'json');
            <?php }else{ ?>
                $.post('interface.php?ac=user/info', {uid: <?php echo $uid;?>}, function(data, textStatus, xhr) {
                    make_res(data.result); 
                },'json');
            <?php } ?>
        });
        function make_res (res) {
            $('#frontPic').attr('src','http://www.beeslm.com/'+res.frontPic);
            $('#backPic').attr('src','http://www.beeslm.com/'+res.backPic);
            $('#handPic').attr('src','http://www.beeslm.com/'+res.handPic);
            $('#platPic').attr('src','http://www.beeslm.com/'+res.platPic);
            $('#mobile').html(res.mobile);
            $('#truename').html(res.truename);
            $('#idCard').html(res.idCard);
            $('#plat').html(res.plat);
            $('#liveRoom').html(res.liveRoom);
            if(res.authStatus==0){
                $('#show_pass').show();
            }else{
                $('#show_pass').hide();
            }
        }
        function local_sub (type) {
            var reson=null;
            if(type==1){
                status=1;                
            }else{
                status=2;
                reson=$('#show_reson').val();
                if(reson==""||reson==null){
                    alert("请填写理由");
                    return false;
                }
            }
            $.post('interface.php?ac=user/auth', {reson: reson,id:id,authStatus:status}, function(data, textStatus, xhr) {
                alert("操作成功");
                window.location.href="certificationList.php";
            });
        }
    </script>
<div class="box">

    <div class="box-title"> <i class="fa fa-signal"></i>
        <h3>认证信息查看</h3>

    </div>
    <div class="box-body">

        <div class="box-body"></div>
        <div style="clear:both;"></div>

    </div>
    <!-- /.box -->
    <!-- 列表部分 -->
    <div class="row">

        <div class="col-lg-12">

            <div id="printArea" class="box"></div>
            <div class="box-body">
            <table>
                <tr>
                    <td colspan="3">
                <div style ='margin-left:10px;margin-bottom:30px;widh:1500px;'>
                    身份证正面：
                    <img src ='' style ='width:250px;' id='frontPic'>
                    身份证反面：
                    <img src ='' style ='width:250px;' id='backPic'>
                    手持身份证
                    <img src ='' style ='width:250px;'  id='handPic'></div>
                </td>
            </tr>
            <tr>
                <td>
                    
                    手机号：<span id="mobile"></span>
                </td>
                <td>
                    姓名：<span id="truename"></span>
                </td>
                <td>
                    身份证号:<span id="idCard"></span>
                </td>
            </tr>
            <tr>
                <td>
                    所属平台 :<span id="plat"></span>
                </td>
                <td>
                    房间信息 :<span id="liveRoom"></span>
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td colspan="3" style="text-align:left">
                    平台截图 :<img src ='' id='platPic' style ='width:250px;'>
                </td>
            </tr>
            <?php if($id){ ?>
            <tr id="show_pass">
                <td colspan="3">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="hidden" name="prices" id="prices">
                        <input type="hidden" name="id" value="<?php echo $_GET['id'];?>">
                      <a href="javascript:void(0)" onclick="local_sub(1)"class="btn btn-success">通过</a>
                      <a href="javascript:void(0)" onclick="local_sub(0)"class="btn btn-success">不通过</a>
                      <input type="text" id="show_reson">
                    </div>
                
                </td>
            </tr>
            <?php }?>
           </table>
            <!-- printArea --> </div>
        <!-- col-lg-12 --> </div>
    <!-- row -->
</div>
<!-- /.content -->
</div>
<!-- /.rightside -->
</div>
<!-- /.wrapper -->

</body>
<?php include_once 'footer.php';?></html>