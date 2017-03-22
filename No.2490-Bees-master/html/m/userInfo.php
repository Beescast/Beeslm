<?php include_once 'header.php';?>
<link rel="stylesheet" href="maniac/css/datatables/dataTables.bootstrap.css" />
<script src="maniac/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
 <script type="text/javascript">
    var tables;
    $(document).ready(function(){
        create_table();
    })
    function create_table(){
        tables =$('#table').dataTable({
            dom: 'Brtip',
            buttons: ['excel'],
            <?php include_once 'table.php';?>
            "ajax":{"url":"interface.php?ac=user/adminList","type": "POST","data":function(d) {
                d.nickName=$('#search_nickname').val();
                d.mobile=$('#search_mobile').val();
                d.id=$('#search_uid').val();
                d.plat=$('#search_plat').val();
                d.liveRoom=$('#search_liveRoom').val();
                d.balance=$('#search_balance').val();
                d.startTime=$('#search_startTime').val();
                d.endTime=$('#search_endTime').val();
            }},
            
            "columns":[
                {"data":"id","orderable":false,},                
                {"data":"mobile","orderable":false,},
                {"data":"nickname","orderable":false},
                {"data":"plat","orderable":false},
                {"data":"liveRoom","orderable":false},
                {"data":"truename","orderable":false},
                {"data":"balance","orderable":false},
                {"data":function(data){
                        return '<a class="btn btn-primary btn-sm" href="identityInfo.php?uid='+data.id+'">认证信息</a>';
                    },"orderable":false},
                {"data":function(data){
                        return '<a class="btn btn-primary btn-sm" href="userOther.php?id='+data.id+'">其它信息</a>';
                    },"orderable":false},
               
                {"data":function(data){
                    if(data.regTime){
                        return $.myTime.UnixToDate(data.regTime/1000);
                    }else{
                        return '';
                    }                        
                },"orderable":false},
                {"data":"catNum","orderable":false},
                {"data":"signNum","orderable":false},
                {"data":"finishNum","orderable":false},
                {"data":"balanceNum","orderable":false},              
            ]            
        });
    }
</script>
    <div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>用户列表</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">
        <div class="form-group">
        <label>手机号</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_mobile" autocomplete="off">
        </div>
        <div class="form-group">
        <label>昵称</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_nickname" autocomplete="off">
        </div>

        <div class="form-group">
        <label>UID</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_uid" autocomplete="off">
        </div>

      

         <div class="form-group">
        <label>平台信息</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_plat" autocomplete="off">
        </div>
         <div class="form-group">
        <label>直播房间</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_liveRoom" autocomplete="off">
        </div>

        <div class="form-group">
        <label>钱包</label>
            <?php create_optiones($user_balance,'balance');?>
        </div>
        

        <div class="form-group">
        <label>时间</label>
       
        <div class="form-group">
        <label></label>
        <input class="form-control" type="text" style="width:180px;" value="" id="search_startTime" size="35" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>
        <div class="form-group">
        <label>至</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" id="search_endTime" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
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
                        <h3>列表</h3>
                        

                       </div>
                        <div class="box-body">

                        <table class="table table-hover table-striped" id="table">
                        <thead>
                        <tr>
                        <th>UID</th>
                        <th>手机号</th>
                        <th>昵称</th>

                        <th>平台</th>
                        <th>直播房间</th>
                        <th>姓名</th>
                        <th>钱包余额</th>
                        <th>认证信息</th>
                        <th>其他信息</th>
                        <th>注册时间</th>
                        <th>关注总数量</th>
                        <th>中标任务总数</th>
                        <th>完成任务总数</th>
                        <th>任务总金额</th>
                        
                     
                  
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