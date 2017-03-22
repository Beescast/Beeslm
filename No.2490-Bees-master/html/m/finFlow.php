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
            "ajax":{"url":"interface.php?ac=orderLog/adminList","type": "POST","data":function(d) {
                d.uid=$('#search_uid').val();
                d.type=$('#search_type').val();
                d.incomeType=$('#search_incomeType').val();
                d.mobile=$('#search_mobile').val();
                d.nickname=$('#search_nickname').val();
                d.startTime=$('#search_startTime').val();
                d.endTime=$('#search_endTime').val();
                d.startMoney=$('#search_startMoney').val();
                d.endMoney=$('#search_endMoney').val();
                d.orderId=$('#search_orderId').val();
            }},     
            
            "columns":[
                {"data":"uid"},                
                {"data":"mobile","orderable":false,},
                {"data":"nickname","orderable":false},
                {"data":"type","orderable":false},
                {"data":"payType","orderable":false},
                {"data":"incomeType","orderable":false},
                {"data":"money","orderable":false},
                {"data":function(data){
                    if(data.addTime){
                        return $.myTime.UnixToDate(data.addTime/1000);
                    }else{
                        return '';
                    }                        
                },"orderable":false},
                {"data":"businessId","orderable":false},
                {"data":"orderId","orderable":false},
            ]            
        });
    }
</script>
    <div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>财务流水查询</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">

        <div class="form-group">
        <label>UID</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_uid" autocomplete="off">
        </div>
        
          <div class="form-group">
        <label>业务类型</label>
            <?php create_optiones($business,'type');?>
        </div>
        <div class="form-group">
        <label>收支类型 </label>
            <?php create_optiones($balance_payments,'incomeType');?>
        </div>

       
        <div class="form-group">
        <label>昵称</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_nickname" autocomplete="off">
        </div>

        <div class="form-group">
        <label>时间</label>
        <input class="form-control" type="text" style="width:180px;" value="" id="search_startTime" size="35" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>
        <div class="form-group">
        <label>至</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" id="search_endTime" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>
        <br/>
        <div style="height:10px"></div>
        <div class="form-group">
        <label>金额区间</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" id="search_startMoney">
        </div>
        <div class="form-group">
        <label>至</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" id="search_endMoney">
        </div>
        <div class="form-group">
        <label>第三方流水号</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_orderId" autocomplete="off">
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
    <div class="pull-right box-toolbar">
    <a class="btn btn-link btn-xs collapse-box" href="#">
    <i class="fa fa-chevron-up"></i>
    </a>
    <a class="btn btn-link btn-xs remove-box" href="#">
    <i class="fa fa-times"></i>
    </a>
    </div>

   </div>
    <div class="box-body">

    <table class="table table-hover table-striped" id="table">
    <thead>
    <tr>
    <th>UID</th>
    <th>手机号</th>
    <th>昵称</th>

    <th>业务类型</th>
    <th>方式</th>
    <th>收支类型</th>
    <th>金额</th>
    <th>时间</th>
    <th>业务编号</th>
    <th>第三方平台流水号</th>
                  
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