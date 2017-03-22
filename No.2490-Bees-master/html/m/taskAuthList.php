<?php 
    include_once 'header.php';
    $task_category=get_task_cat();
?>
<link rel="stylesheet" href="maniac/css/datatables/dataTables.bootstrap.css" />
<script src="maniac/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
 <script type="text/javascript">
    var tables;
    var ajaxUrl="ac=task/unsign";
    $(document).ready(function(){
        create_table();
    })
    function create_table(){
        tables =$('#table').dataTable({
            dom: 'Brtip',
            buttons: ['excel'],
            <?php include_once 'table.php';?>
            "ajax":{"url":"interface.php?ac=task/getSignList","type": "POST","data":function(d) {
                d.bidStatus=1;
                d.taskId=$('#search_id').val();
                d.title=$('#search_title').val();
                d.payStatus=$('#search_payStatus').val();
                d.catId=$('#search_catId').val();
                d.type=$('#search_type').val();
                d.startTime=$('#search_startTime').val();
                d.endTime=$('#search_endTime').val();
                d.timeType=$('#search_time_type').val();
            }},
            "columns":[
                {"data":"taskId"},                
                {"data":"catId","orderable":false,},
                {"data":"type","orderable":false},
                {"data":"title","orderable":false},
                {"data":"singlePrice","orderable":false},
                {"data":"uid","orderable":false},
                {"data":"nickname","orderable":false},
                {"data":"payStatus","orderable":false},

                {"data":function(data){
                    str=""
                    if(data.payStatus=="待完成"||data.payStatus=="待付款"){
                        if(data.payStatus=="待付款"){
                            str+='<a href="taskAuth.php?id='+data.id+'" class="btn btn-primary btn-sm" >审核</a>';
                        }
                        str+='&nbsp;<a href="javascript:void(0)" onclick="cancel_bid('+data.id+')" class="btn btn-primary btn-sm" >取消中标</a>';
                    }
                    return str;
                },"orderable":false},
                {"data":function(data){
                    if(data.bidTime){
                        return $.myTime.UnixToDate(data.bidTime/1000);
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
                {"data":"opName","orderable":false},
                {"data":function(data){
                    if(data.opTime){
                        return $.myTime.UnixToDate(data.opTime/1000);
                    }else{
                        return '';
                    }
                },"orderable":false},
                
            ]    
        });
    }
    function cancel_bid (id) {
        if(confirm('是否取消中标')){
            $('#cancel_id').val(id);
            submits();
        }        
    }
    function success (data) {
        if(data.code==200){
            alert('修改成功');
            window.location.reload();
        }else{
            alert(data.messages.message);
        }
    }
</script>
    <div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>赏金任务审核</h3>
        
    </div>
    <div class="box-body">
    <form id="myform">
        <input type="hidden" name="id" id="cancel_id">
    </form>
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">
        <div class="form-group">
        <label>任务标题</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_title" autocomplete="off">
        </div>


        <div class="form-group">
        <label>状态</label>
            <?php create_optiones($task_pay_status,'payStatus');?>
        </div>

         <div class="form-group">
        <label>分类</label>
            <?php create_optiones($task_category,'catId');?>
        </div>


        <div class="form-group">
        <label>
            <select class="form-control" id="search_time_type">
                <option value="bid">中标时间</option>
                <option value="pay">交付时间</option>
                <option value="op">审核时间</option>
            </select>
        </label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_startTime" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>
        <div class="form-group">
        <label>至</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''"  id="search_endTime" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>

        <div class="form-group">
        <label>任务ID</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_id" autocomplete="off">
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
                        <h3>赏金任务审核</h3>
                        

                       </div>
                        <div class="box-body">

                        <table class="table table-hover table-striped" id="table">
                        <thead>
                        <tr>
                        <th>任务ID</th>
                        <th>分类</th>
                        <th>类型</th>
                        <th>任务标题</th>
                        <th>赏金</th>                        
                        <th>中标者账号</th>
                        <th>中标者昵称</th>
                        <th>状态</th>
                        <th>操作</th>
                        <th>中标时间</th>
                         <th>交付时间</th>
                         <th>完成审核人</th>
                         <th>完成审核时间</th>
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