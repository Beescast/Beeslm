<?php 
    include_once 'header.php';
    $task_category=get_task_cat();
?>
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
            "ajax":{"url":"interface.php?ac=task/adminList","type": "POST","data":function(d) {
                d.id=$('#search_id').val();
                d.title=$('#search_title').val();
                d.status=$('#search_status').val();
                d.catId=$('#search_catId').val();
                d.type=$('#search_type').val();
                d.startTime=$('#search_startTime').val();
                d.endTime=$('#search_endTime').val();
            }},
            "columns":[
                {"data":"id"},                
                {"data":"catId","orderable":false,},
                {"data":"type","orderable":false},
                {"data":"title","orderable":false},
                {"data":"price","orderable":false},
                {"data":"status","orderable":false},
                {"data":"num","orderable":false},
                {"data":"bidNum","orderable":false},
                {"data":"signNum","orderable":false},

                {"data":function(data){
                    
                    if(data.status!="招募完成"){
                        if(data.type=='普通任务'){
                            str='<span><a href="taskCommonSign.php?id='+data.id+'" class="btn btn-primary btn-sm" >指派</a></span>';
                        }else{
                            str='<span><a href="taskSeniorSign.php?id='+data.id+'" class="btn btn-primary btn-sm" >指派</a></span>';
                        }
                        str+='&nbsp;<a href="javascript:void(0)" onclick="finish('+data.id+')" class="btn btn-primary btn-sm" >结束</a>';
                        str+='&nbsp;<a href="javascript:void(0)" onclick="del('+data.id+')" class="btn btn-primary btn-sm" >删除</a>';
                    }else{
                        str='';
                    }
                    return str;
                },"orderable":false},
                {"data":function(data){
                    if(data.addTime){
                        return $.myTime.UnixToDate(data.addTime/1000,false);
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
        <h3>赏金任务审核</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">
        <div class="form-group">
        <label>任务标题</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_title" autocomplete="off">
        </div>


        <div class="form-group">
        <label>状态</label>
            <?php create_optiones($task_status,'status');?>
        </div>

         <div class="form-group">
        <label>分类</label>
            <?php create_optiones($task_category,'catId');?>
        </div>

         <div class="form-group">
        <label>类型</label>
            <?php create_optiones($task_type,'type');?>
        </div>

        <div class="form-group">
        <label>创建时间</label>
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
                        <h3>任务审核列表</h3>
                        

                       </div>
                        <div class="box-body">

                        <table class="table table-hover table-striped" id="table">
                        <thead>
                        <tr>
                        <th>任务ID</th>
                        <th>分类</th>
                        <th>类型</th>
                        <th>任务标题</th>
                        <th>总赏金</th>
                        <th>状态</th>
                        <th>所需人数</th>
                        <th>已中标人数</th>
                        <th>报名人数</th>
                        <th>操作</th>
                         <th>任务创建时间</th>
                        </tr>
                        </table>
                    </div><!-- box-body -->
                </div><!-- printArea -->
            </div><!-- col-lg-12 -->
         </div><!-- row -->
        </div><!-- /.content -->
        </div><!-- /.rightside -->
    </div><!-- /.wrapper -->
    <script type="text/javascript">
        function finish (id) {
            if(confirm('是否完成此任务')){
                $.post('interface.php?ac=task/finish', {id: id}, function(data) {
                    alert('修改成功');
                    window.location.reload();
                });
            }
        }
        function del (id) {
        if(confirm('是否删除')){
            $.post('interface.php?ac=task/del', {id: id}, function(data, textStatus, xhr) {
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
</body>
<?php include_once 'footer.php';?>
</html>