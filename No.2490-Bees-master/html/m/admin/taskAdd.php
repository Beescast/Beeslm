<?php 
include_once '/header.php';
$task_category=array(1=>'视屏流广告',2=>'植入广告');
$task_type=array(0=>'普通任务',1=>'高级任务');

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
                        <h3>赏金任务发布</h3>
                        

                    </div>
                    <div class="box-body">
                    <form class="form-horizontal" role="form" action="" enctype="multipart/form-data" method="post" name="" >

            <div class="form-group" >
            <label for="role" class="col-sm-2 control-label">任务分类</label>
            <div class="col-sm-6">
           <?php create_optiones($task_category,'name');?>

            </div>

            </div>
             <div class="form-group" >
            <label for="role" class="col-sm-2 control-label">任务类型</label>
            <div class="col-sm-6">
            <?php create_optiones($task_type,'name');?>
            </div>
            </div>

            <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">任务标题</label>
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="mobile" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>

             <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">总赏金</label>
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="mobile" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>

              <div class="form-group" id="" >
            <label for="mobile" class="col-sm-2 control-label">所需人数</label>
            <div class="col-sm-6"><input class="form-control" autocomplete='off' type="text" name="mobile" 
            data-inputmask="'mask': ''" placeholder="" 
            size="35" value="" >
            <p class="help-block"></p>
            </div>
            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>
              <div class="form-group" >
            <label class="col-sm-2 control-label">任务说明</label>
            <div class="col-sm-10">
                <textarea class="ckeditor" type="text" name="contend" cols="80" rows="10"> </textarea>
            </div>

            <div class="col-sm-4" style="padding-top:7px;"></div>
            </div>
            <div class="form-group">
            <label class="col-sm-2 control-label" for="status">
            消息推送
            </label>

            <div id="de" class="col-sm-6" style="width: 80%;">
            <th class="fc-day-header fc-mon fc-widget-header" style="width: ;"> 平台公告</th>
            <th class="fc-day-header fc-mon fc-widget-header" style="text-align:center;">
            <input id="turnout1_1" type="checkbox" name="use_day[]" value="">
            </th>
            <th class="fc-day-header fc-mon fc-widget-header" style="width: ;">主播信息</th>
            <th class="fc-day-header fc-mon fc-widget-header" style="text-align:center;">
            <input id="turnout1_1" type="checkbox" name="use_day[]" value="">
            </th>
            </div>
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