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
            "ajax":{"url":"interface.php?ac=wallet/adminCashList","type": "POST","data":function(d) {
                d.id=$('#search_id').val();
                d.startTime=$('#search_startTime').val();
                d.endTime=$('#search_endTime').val();
                d.status=$('#search_status').val();
                d.accountName=$('#search_accountName').val();
                d.mobile=$('#search_mobile').val();
                d.nickname=$('#search_nickname').val();
                d.uid=$('#search_uid').val();
                d.startMoney=$('#search_startMoney').val();
                d.endMoney=$('#search_endMoney').val();
            }},       
            "columns":[
                {"data":"id"},                
                {"data":"uid","orderable":false,},
                {"data":"mobile","orderable":false},
                {"data":"nickname","orderable":false},
                {"data":"money","orderable":false},
                {"data":"accountName","orderable":false},
                {"data":"bankName","orderable":false},
                {"data":"subBankName","orderable":false},
                {"data":"status","orderable":false},
                {"data":function(data){
                    if(data.applyTime){
                        return $.myTime.UnixToDate(data.applyTime/1000);
                    }else{
                        return '';
                    }
                },"orderable":false},
                {"data":"opName","orderable":false},
                {"data":function(data){
                    if(data.opTime){
                        return $.myTime.UnixToDate(data.opTime/1000);
                    }else{
                        return '';
                    }
                },"orderable":false},
                {"data":function(data){
                    if(data.status=='待处理'){
                        return '<a href="walletCashPass.php?id='+data.id+'" class="btn btn-primary btn-sm" >已通过</a><span>  </span><a href="walletCashNotPass.php?id='+data.id+'" class="btn btn-primary btn-sm" >不通过</a>';
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
        <h3>用户提现处理</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">

         <div class="form-group">
        <label>流水号</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_id" autocomplete="off">
        </div>

          <div class="form-group">
        <label>状态</label>
            <?php create_optiones($wallet_cash_status,'status');?>
        </div>

        <div class="form-group">
        <label>开户人姓名</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_accountName" autocomplete="off">
        </div>
         <div class="form-group">
        <label>手机号</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_mobile" autocomplete="off">
        </div>
         <div class="form-group">
        <label>昵称</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_nickname" autocomplete="off">
        </div>

        <div class="form-group">
        <label>申请时间</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_startTime" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>
        <div class="form-group">
        <label>至</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_endTime" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>
        <div style="height:10px"></div>
         <div class="form-group">
        <label>UID</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_uid" autocomplete="off">
        </div>

        <div class="form-group">
        <label>金额区间</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_startMoney" autocomplete="off" >
        </div>
        <div class="form-group">
        <label>至</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_endMoney" autocomplete="off">
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
                        <th>提现金额</th>
                        <th>开户人名称</th>

                        <th>银行名称</th>
                        <th>支行名称</th>
                        <th>处理状态</th>
                        <th>申请时间</th>
                        <th>处理人</th>
                        <th>处理时间</th>
                        <th>操作</th>
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