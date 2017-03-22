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
            "ajax":{"url":"interface.php?ac=user/authList","type": "POST","data":function(d) {
                d.nickName=$('#search_uid').val();
                d.mobile=$('#search_mobile').val();
                d.id=$('#search_id').val();
                d.authStatus=$('#search_authStatus').val();
                d.plat=$('#search_plat').val();
                d.liveRoom=$('#search_liveRoom').val();
                d.startTime=$('#search_startTime').val();
                d.endTime=$('#search_endTime').val();
            }},
            "columns":[
                {"data":"id"},                
                {"data":"mobile","orderable":false,},
                {"data":"truename","orderable":false},
                {"data":"plat","orderable":false},
                {"data":"liveRoom","orderable":false},
                {"data":function(data){
                    if(data.authStatus==0){
                        return '待审核';
                    }else if(data.authStatus==1){
                        return '已通过';
                    }else if(data.authStatus==2){
                        return '未通过';
                    }                        
                },"orderable":false}, 
                {"data":function(data){
                        return '<a class="btn btn-primary btn-sm" href="identityInfo.php?id='+data.id+'">认证信息</a>';                    
                    },"orderable":false},  
                {"data":function(data){
                    if(data.regTime){
                        return $.myTime.UnixToDate(data.regTime/1000);
                    }else{
                        return '';
                    }                        
                },"orderable":false},                
            ]            
        });
    }
</script>
    <div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>认证列表</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">
        <div class="form-group">
        <label>手机号</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_mobile" autocomplete="off">
        </div>&nbsp;&nbsp;&nbsp;&nbsp;
       <div class="form-group">
        <label>认证状态</label>
            <?php create_optiones($user_auth_status,'authStatus');?>
        </div>&nbsp;&nbsp;&nbsp;&nbsp;

         <div class="form-group">
        <label>平台信息</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_plat" autocomplete="off">
        </div>&nbsp;&nbsp;&nbsp;&nbsp;
         <div class="form-group">
        <label>直播房间</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_liveRoom" autocomplete="off">
        </div>&nbsp;&nbsp;&nbsp;&nbsp;<br><br>

        <div class="form-group">
        <label>时间</label>
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
                        <th>流水号</th>
                        <th>手机号</th>
                        <th>姓名</th>

                        <th>平台</th>
                        <th>直播房间</th>
                        <th>认证状态</th>
                        <th>认证信息</th>
                        <th>注册时间</th>
                      
                  
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