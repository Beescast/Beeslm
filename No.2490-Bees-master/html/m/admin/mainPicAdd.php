<?php 
include_once '/header.php';
$pic_name=array(1=>'小商城',2=>'公告页');
$pic_position=array(0=>'通栏轮播',1=>'中间图片',2=>'右侧图片');

    function create_optiones($array,$name,$value=null,$key='所有'){
        $str='<select class="form-control" name="'.$name.'" id="search_'.$name.'">
                <option class="" selected="" value="'.$value.'">'.$key.'</option>';
        foreach ($array as $key => $ar) {
            $str.='<option value="'.$key.'">'.$ar.'</option>';
        }
        $str.='</select>';
        echo $str;
    }

?>
<link rel="stylesheet" href="maniac/css/datatables/dataTables.bootstrap.css" />
<script src="maniac/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
 <!-- wrapper -->
        <div class="wrapper">
            <div class="leftside">
                <div class="sidebar">
                    <div class="user-box">
                        <div class="avatar">
                            <img src="../img/avatar.jpg" alt="" />
                        </div>
                        <div class="details">
                            <p>陈</p>
                            <span class="position">Superadmin</span>
                        </div>
                        <div class="button">
                            <a href="login.html"><i class="fa fa-power-off"></i></a>
                        </div>
                    </div>
                    <ul class="sidebar-menu">
                        
                        <li class="active sub-nav">

                            <a href="index.html">
                                <i class="fa fa-home"></i> <span>订单查询</span>
                            </a>
                        </li>

                        <li>
                            <a href="userInfo.html">
                                <i class="fa fa-envelope"></i> <span>用户信息查询</span>
                               
                            </a>
                        </li>

                         <li>
                            <a href="certification.html">
                                <i class="fa fa-envelope"></i> <span>注册认证审核</span>
                               
                            </a>
                        </li>

                        <li>
                            <a href="mainPic.html">
                                <i class="fa fa-envelope"></i> <span>主要图片配置</span>
                               
                            </a>
                        </li>
                    </ul>
                 </div>
            </div>

            <div class="rightside">

            <div class="content">
    <div class="box">

        <!-- 列表部分 -->  
        <div class="row">

            <div class="col-lg-12">

                <div id="printArea" class="box">

                     <div class="box-title">
                        <h3>列表</h3>
                        

                    </div>
                    <div class="box-body">
                    <form class="form-horizontal" role="form" action="" enctype="multipart/form-data" method="post" name="" >

            <div class="form-group" >
            <label for="role" class="col-sm-2 control-label">页面名称</label>
            <div class="col-sm-6">
           <?php create_optiones($pic_name,'name');?>

            </div>

            </div>
             <div class="form-group" >
            <label for="role" class="col-sm-2 control-label">图片位置</label>
            <div class="col-sm-6">
            <?php create_optiones($pic_position,'name');?>
            </div>
            </div>

            <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">显示顺序</label>
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="mobile" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>

             <div class="form-group">
            <label for="avatar" class="col-sm-2 control-label">图片</label>
            <div class="col-sm-6">
            <a class="file-input-wrapper btn btn-default btn btn-info file-inputs"><span>选择文件</span><input class="btn btn-info file-inputs" multiple type="file" name="avatar" title="选择文件" ></a>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>

              <div class="form-group" id="" >
            <label for="email" class="col-sm-2 control-label">链接地址</label>
            
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="link" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>

            <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn btn-success">提交</button> 
              id
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