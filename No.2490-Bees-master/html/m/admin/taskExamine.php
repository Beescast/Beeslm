<?php 
include_once '/header.php';
$task_status=array(0=>'进行中',1=>'待完成',2=>'代付款',3=>'已结束');
$task_category=array(1=>'视屏流广告',2=>'植入广告');
$task_type=array(0=>'普通任务',1=>'高级任务');
    $order_status=array(0=>'待付款',1=>'待启动',2=>'上线中',3=>'已结束',4=>'已评价',9=>'已取消');

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
                        


                        <!-- <li class="sub-nav">
                            <a href="#">
                                <i class="fa fa-briefcase"></i>
                                <span>UI Elements</span>
                                <i class="fa fa-angle-right pull-right"></i>
                            </a>
                            <ul class="sub-menu">
                                <li><a href="typography.html">Typography</a></li>
                                <li><a href="icons.html">Icons</a></li>
                                <li><a href="buttons.html">Buttons</a></li>
                                <li><a href="sliders.html">Sliders</a></li>
                            </ul>
                        </li>
                        <li class="active sub-nav">
                            <a href="#">
                                <i class="fa fa-pencil"></i> <span>Forms</span>
                                <i class="fa fa-angle-right pull-right"></i>
                            </a>
                            <ul class="sub-menu">
                                <li class="active"><a href="form-elements.html">Form Elements</a></li>
                                <li><a href="form-validation.html">Form Validation</a></li>
                            </ul>
                        </li>
                        <li class="sub-nav">
                            <a href="#">
                                <i class="fa fa-table"></i> <span>Tables</span>
                                <i class="fa fa-angle-right pull-right"></i>
                            </a>
                            <ul class="sub-menu">
                                <li><a href="basic-tables.html">Basic tables</a></li>
                                <li><a href="data-tables.html">Data tables</a></li>
                            </ul>
                        </li> -->
                    </ul>
                 </div>
            </div>

            <div class="rightside">

            <div class="content">
    <div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>赏金任务审核</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">
        <div class="form-group">
        <label>任务标题</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_name" autocomplete="off">
        </div>


        <div class="form-group">
        <label>状态</label>
            <?php create_optiones($task_status,'payType');?>
        </div>

         <div class="form-group">
        <label>分类</label>
            <?php create_optiones($task_category,'payType');?>
        </div>

         <div class="form-group">
        <label>类型</label>
            <?php create_optiones($task_type,'payType');?>
        </div>

        <div class="form-group">
        <label>创建时间</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>
        <div class="form-group">
        <label>至</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>

        <div class="form-group">
        <label>任务ID</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_name" autocomplete="off">
        </div>
        <a href="#" class="btn btn-info" onclick="searchs()">提交</a>


    </div>
    </form>

    <div class="box-body">
    </div><div style="clear:both;"></div>

    </div><!-- /.box -->
        <!-- 列表部分 -->  
        <div class="row">

            <div class="col-lg-12">

                <div id="printArea" class="box">

                     <div class="box-title">
                        <h3>公告查询</h3>
                        

                       </div>
                        <div class="box-body">

                        <table class="table table-hover table-striped" id="table">
                        <thead>
                        <tr>
                        <th>任务ID</th>
                        <th>分类</th>
                        <th>类型</th>
                        <th>任务标题</th>
                        <th>总赏金</th>
                        <th>状态</th>
                        <th>所需人数</th>
                        <th>已中标人数</th>
                        <th>报名人数</th>
                        <th>操作</th>
                         <th>任务创建时间</th>
                        </tr>
                        <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td width="" style="text-align:center"><a href="noticeAdd.php?action=modify&id=123" class="btn btn-primary btn-sm" >指派</a><span>  </span><a href="noticeAdd.php?action=modify&id=123" class="btn btn-primary btn-sm" >结束</a>
                        <td></td>   
                        </tr>
                        </thead>
                        
                        </table>
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