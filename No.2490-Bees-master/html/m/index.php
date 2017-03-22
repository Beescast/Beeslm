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
            "ajax":{"url":"interface.php?ac=order/list","type": "POST","data":function(d) {
                d.uid=$('#search_uid').val();
                d.mobile=$('#search_mobile').val();
                d.id=$('#search_id').val();
                d.payType=$('#search_payType').val();
                d.status=$('#search_status').val();
                d.startTime=$('#search_startTime').val();
                d.endTime=$('#search_endTime').val();
            }},
            "columns":[
                {"data":"id","orderable":false,},
                {"data":"uid","orderable":false,},
                {"data":"mobile","orderable":false,},
                {"data":"name","orderable":false},
                {"data":"packageName","orderable":false},
                {"data":"price","orderable":false},
                {"data":"statusString","orderable":false},
                {"data":function(data){
                        if(data.status==1){
                            return '<a class="btn palette-silver btn-color btn-sm"  href="packageStart.php?id='+data.id+'">启动</a>';
                        }else{
                            return '';
                        }                        
                    },"orderable":false},
                {"data":"payTypeString","orderable":false},
                {"data":function(data){
                        if(data.submitTime){
                            return $.myTime.UnixToDate(data.submitTime/1000);
                        }else{
                            return '';
                        }                        
                    },"orderable":false},
                {"data":function(data){
                        if(data.payTime){
                            return $.myTime.UnixToDate(data.payTime/1000);
                        }else{
                            return '';
                        }                        
                    },"orderable":false},
                {"data":function(data){
                        if(data.startTime){
                            return $.myTime.UnixToDate(data.startTime/1000);
                        }else{
                            return '';
                        }                        
                    },"orderable":false},
                {"data":function(data){
                    if(data.endTime){
                        return $.myTime.UnixToDate(data.endTime/1000);
                    }else{
                        return '';
                    }
                },"orderable":false},
                {"data":function(data){
                    if(data.statusString=='待付款'){
                        return '<a class="btn palette-silver btn-color btn-sm" onclick="del(\''+data.id+'\')">删除</a>'
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
        <h3>订单列表</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">

        <div class="form-group">
        <label>UID</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_uid" autocomplete="off">
        </div>&nbsp;&nbsp;&nbsp;&nbsp;

        <div class="form-group">
        <label>手机号</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_mobile" autocomplete="off">
        </div>&nbsp;&nbsp;&nbsp;&nbsp;

         <div class="form-group">
        <label>订单号</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_id" autocomplete="off">
        </div>&nbsp;&nbsp;&nbsp;&nbsp;

        <div class="form-group">
        <label>支付方式</label>
            <?php create_optiones($order_pay_status,'payType');?>
        </div>
        <div class="form-group">
        <label>状态</label>
        <?php create_optiones($order_status,'status');?>
        </div>&nbsp;&nbsp;&nbsp;&nbsp;<br><br>

        <div class="form-group">
        <label>时间</label>
       
        <div class="form-group">
        <label></label>
        <input class="form-control" type="text" style="width:180px;" value="" id="search_startTime" size="35" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>
        <div class="form-group">
        &nbsp;&nbsp;&nbsp;&nbsp;<label>至</label>&nbsp;&nbsp;&nbsp;&nbsp;
        <input class="form-control" type="text" style="width:180px;" value="" size="35" id="search_endTime" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        
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
                        
                        <th>订单号</th>
                        <th>UID</th>
                        <th>手机号</th>
                        <th>昵称</th>
                        <th>套餐名称</th>
                        <th>价格</th>
                        <th>状态</th>
                        <th>操作</th>
                        <th>支付方式</th>
                        <th>下单时间</th>
                        <th>支付时间</th>
                        <th>启动时间</th>
                        <th>结束时间</th>
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
<script type="text/javascript">
    function del (id) {
        if(confirm('是否删除')){
            $.post('interface.php?ac=order/realDel', {id: id}, function(data, textStatus, xhr) {
                //if(data.code==200){
                    alert('删除成功');
                    window.location.reload();
                /*}else{
                    alert('删除失败');
                }*/
            });
        }
    }

</script>
</html>