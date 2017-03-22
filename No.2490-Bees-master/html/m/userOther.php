<?php 
    include_once 'header.php';
    $id=$_GET['id'];
    if($id){        
       
        $user=json_decode(get_data(array('uid'=>
$id),'user/info'),true);
        $info=$user['result'];        
    }
?>
<div class="box">

    <!-- 列表部分 -->
    <div class="row">

        <div class="col-lg-12">

            <div id="printArea" class="box">

                <div class="box-title">
                    <h3>用户其它信息</h3>

                </div>
                <div class="box-body">
                <br>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">手机号</label>
                        <div class="col-sm-6">
                            <?php echo $info['mobile'];?></div>
                        <div class="col-sm-2" style="padding-top:7px;" ><p class="help-block"></p>
                        </div>
                    </div><br><br>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-6">
                            <?php echo $info['truename'];?></div>
                        <div class="col-sm-2" style="padding-top:7px;" ><p class="help-block"></p>
                        </div>
                    </div><br><br>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">身份证</label>
                        <div class="col-sm-6">
                            <?php echo $info['idCard'];?></div>
                        <div class="col-sm-2" style="padding-top:7px;" ><p class="help-block"></p>
                        </div>
                    </div><br><br>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-6">
                            <?php echo $info['email'];?></div>
                        <div class="col-sm-2" style="padding-top:7px;" ><p class="help-block"></p>
                        </div>
                    </div><br><br>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系电话</label>
                        <div class="col-sm-6">
                            <?php echo $info['contactMobile'];?></div>
                        <div class="col-sm-2" style="padding-top:7px;" ><p class="help-block"></p>
                        </div>
                    </div><br><br>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系地址</label>
                        <div class="col-sm-6">
                            <?php echo $info['contactAddress'];?></div>
                        <div class="col-sm-2" style="padding-top:7px;" ><p class="help-block"></p>
                        </div>
                    </div><br><br>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">经纪公司名称</label>
                        <div class="col-sm-6">
                            <?php echo $info['agencyName'];?></div>
                        <div class="col-sm-2" style="padding-top:7px;" ><p class="help-block"></p>
                        </div>
                    </div><br><br>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">经纪人电话</label>
                        <div class="col-sm-6">
                            <?php echo $info['agencyMobile'];?></div>
                        <div class="col-sm-2" style="padding-top:7px;" ><p class="help-block"></p>
                        </div>
                    </div><br><br>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">备注</label>
                        <div class="col-sm-6">
                            <?php echo $info['description'];?></div>
                        <div class="col-sm-2" style="padding-top:7px;" ><p class="help-block"></p>
                        </div>
                    </div><br><br>

                </div>
            </div>
        </div>
    </div>