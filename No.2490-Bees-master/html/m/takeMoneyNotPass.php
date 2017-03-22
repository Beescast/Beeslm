<?php 
include_once '/header.php';


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

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>提现审核不通过</h3>
        
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
 
            <div class="form-group" id="" >

                <label for="name" class="col-sm-2 control-label">用户姓名：</label>
                <div class="col-sm-2" style="padding-top:7px;"><p class="help-block">张三</p>
                </div>
                 
             </div>
         <div class="form-group" id="" >
                <label for="name" class="col-sm-2 control-label">提现金额：</label>

                <div class="col-sm-2" style="padding-top:7px;"><p class="help-block">10000</p>
                </div>
                 
             </div>

        <div class="form-group" id="" >
            <label for="name" class="col-sm-2 control-label">银行名称：</label>

                <div class="col-sm-2" style="padding-top:7px;"><p class="help-block">中国工商银行</p>
                </div>


        </div>
         <div class="form-group" id="" >
            <label for="name" class="col-sm-2 control-label">不通过的理由：</label>

                <div class="col-sm-2" style="padding-top:7px;">
                    <input class="form-control" type="text" style="width:380px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_mobile" autocomplete="off">
                </div>
        </div>
        <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
        <button class="btn btn-success" type="submit">确认</button>
        <button class="btn btn-primary" type="reset">取消</button>
        </div>
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