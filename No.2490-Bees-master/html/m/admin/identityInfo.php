<?php 
include_once '/header.php';
$order_pay_status=array(0=>'钱包',1=>'支付宝',2=>'微信');
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
        <h3>认证信息查看</h3>
        
    </div>
    <div class="box-body">


    <div class="box-body">
    </div><div style="clear:both;"></div>

    </div><!-- /.box -->
        <!-- 列表部分 -->  
        <div class="row">

            <div class="col-lg-12">

            <div id="printArea" class="box">
            </div><div class="box-body"><form class="form-horizontal" role="form" action="" enctype="multipart/form-data" method="post" name="" >
            <div style ='margin-left:10px;margin-bottom:30px;widh:1500px;'>身份证正面：
                <img src ='http://www.youhuyisheng.com/upload/avatar/2016-03-22/s.origin/sy00QQEEE77WWLLL.png' style ='width:250px;'>
                身份证反面：

                <img src ='http://www.youhuyisheng.com/upload/avatar/2016-03-22/y.origin/ymmbbb9977XXXKKz.png' style ='width:250px;'>
                手持身份证

                <img src ='http://www.youhuyisheng.com/upload/avatar/2016-03-22/y.origin/ymmbbb9977XXXKKz.png' style ='width:250px;'>
            </div>  
            <div class="form-group" id="" >

                <label for="name" class="col-sm-2 control-label">手机号</label>
                <div class="col-sm-2" style="padding-top:7px;"><p class="help-block">13300001111</p>
                </div>
                <label for="name" class="col-sm-2 control-label">姓名</label>

                <div class="col-sm-2" style="padding-top:7px;"><p class="help-block">zhang</p>
                </div>
                 <label for="name" class="col-sm-2 control-label">身份证号</label>

                <div class="col-sm-2" style="padding-top:7px;"><p class="help-block">321321799732097</p>
                </div>
             </div>

        <div class="form-group" id="" >
            <label for="ID_number" class="col-sm-2 control-label">所属平台</label>
            
            <div class="col-sm-3" style="padding-top:7px;"><p class="help-block"> 花椒直播</p></div>

            <label for="ID_number" class="col-sm-2 control-label">房间信息</label>
            
            <div class="col-sm-3" style="padding-top:7px;"><p class="help-block"> 222222</p></div>

        </div>
         <div style ='margin-left:10px;margin-bottom:30px;widh:1500px;'>平台截图：
                <img src ='http://www.youhuyisheng.com/upload/avatar/2016-03-22/s.origin/sy00QQEEE77WWLLL.png' style ='width:250px;'>
                
            </div> 

                </div><!-- printArea -->
            </div><!-- col-lg-12 -->
         </div><!-- row -->
        </div><!-- /.content -->
        </div><!-- /.rightside -->
    </div><!-- /.wrapper -->
</body>
<?php include_once 'footer.php';?>
</html>