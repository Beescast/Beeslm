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
            "ajax":{"url":"interface.php?ac=wallet/adminList","type": "POST","data":function(d) {
                d.id=$('#search_id').val();
                d.startTime=$('#search_startTime').val();
                d.endTime=$('#search_endTime').val();
                d.payType=$('#search_payType').val();
                d.uid=$('#search_uid').val();
                d.mobile=$('#search_mobile').val();
                d.businessNo=$('#search_businessNo').val();
                d.businessType=$('#search_businessType').val();
                //d.=$('#search_').val();
            }}, 
            "columns":[
                {"data":"id"},                
                {"data":"uid","orderable":false,},
                {"data":"mobile","orderable":false},
                {"data":"nickname","orderable":false},
                {"data":"businessType","orderable":false},
                {"data":"payType","orderable":false},
                {"data":"money","orderable":false},
                {"data":"currentBalance","orderable":false},
                {"data":function(data){
                    if(data.addTime){
                        return $.myTime.UnixToDate(data.addTime/1000);
                    }else{
                        return '';
                    }
                },"orderable":false},
                {"data":"businessNo","orderable":false},
            ]            
        });
    }
</script>
    <div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>钱包流水查询</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">

         <div class="form-group">
        <label>流水号</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_id" autocomplete="off">
        </div>

          <div class="form-group">
        <label>收支类型</label>
            <?php create_optiones($balance_payments,'payType');?>
        </div>

         <div class="form-group">
        <label>手机号</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_mobile" autocomplete="off">
        </div>
         <div class="form-group">
        <label>UID</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_uid" autocomplete="off">
        </div>

        <div class="form-group">
        <label>发生时间</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_startTime" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>
        <div class="form-group">
        <label>至</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_endTime" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>
        <div style="height:10px"></div>
         

        <div class="form-group">
        <label>业务说明</label>
            <?php create_optiones($wallet_business_type,'businessType');?>
        </div>


         <div class="form-group">
        <label>业务编号</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_businessNo" autocomplete="off">
        </div>
       
        <a href="#" class="btn btn-info" onclick="searchs()">查询</a>

        

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
                        <th>UID</th>
                        <th>手机号</th>
                        <th>昵称</th>
                        <th>业务说明</th>
                        <th>收支类型</th>

                        <th>金额</th>
                        <th>实时余额</th>
                        <th>发生时间</th>
                        <th>业务编号</th>
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