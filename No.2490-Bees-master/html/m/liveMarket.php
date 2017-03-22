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
            "ajax":{"url":"interface.php?ac=live/market","type": "POST","data":function(d) {
                d.id=$('#search_id').val();
                d.uid=$('#search_uid').val();
                d.name=$('#search_name').val();
                d.startTime=$('#search_startTime').val();
                d.endTime=$('#search_endTime').val();
                d.startDay=$('#search_startDay').val();
                d.endDay=$('#search_endDay').val();
            }},     
            "columns":[
                {"data":"id"},                
                {"data":"uid","orderable":false,},
                {"data":"name","orderable":false},
                {"data":function(data){
                    if(data.dates){
                        return $.myTime.UnixToDate(data.dates/1000,false);
                    }else{
                        return '';
                    }                         
                },"orderable":false},
                {"data":"times","orderable":false},
                {"data":"shows","orderable":false},
                {"data":"click","orderable":false},
                {"data":"visitArea","orderable":false},
                {"data":"newVisit","orderable":false},                
                {"data":"oldVisit","orderable":false}
            ]            
        });
    }
</script>
    <div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>推广信息查询</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">

         <div class="form-group">
        <label>姓名</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_name" autocomplete="off">
        </div>

         <div class="form-group">
        <label>UID</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_uid" autocomplete="off">
        </div>

        <div class="form-group">
        <label>年月区间</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" id="search_startDay" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>
        <div class="form-group">
        <label>至</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" id="search_endDay" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div> 

        <div class="form-group">
        <label>时间区间</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" id="search_startTime" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'H'})" style="height:auto;width:120px;">
        </div>
        <div class="form-group">
        <label>至</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" id="search_endTime" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'H'})" style="height:auto;width:120px;">
        </div> 
    
         <div class="form-group">
        <label>流水号</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_id" autocomplete="off">
        </div>
        <a href="#" class="btn btn-info" onclick="searchs()">查询</a>
        <a href="livePeople.php" class="btn btn-info" >人群价值查询</a>

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
                        <th>主播姓名</th>
                        <th>年月</th>
                        <th>时间</th>
                        <th>展现率</th>

                        <th>点击率</th>
                        <th>访问地区</th>
                        <th>新访客</th>
                        <th>老访客</th>
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