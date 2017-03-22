<?php 
    include_once 'header.php';
    $id=$_GET['id'];
    if(!$id){
        header("Location: taskList.php");
    }
    $info=json_decode(get_data(array('id'=>$id),'task/info'),true);
    $info=$info['result'];
    $task_cats=get_task_cat();
?>
<link rel="stylesheet" href="maniac/css/datatables/dataTables.bootstrap.css" />
<script src="maniac/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
 <script type="text/javascript">
    var tables;
    var ajaxUrl="ac=task/sign";
    $(document).ready(function(){
        create_table();
    })
    function create_table(){
        tables =$('#table').dataTable({
            dom: 'Brtip',
            buttons: ['excel'],
            <?php include_once 'table.php';?>
            "ajax":{"url":"interface.php?ac=task/getSignList","type": "POST","data":function(d) {
                d.taskId=<?php echo $id;?>;
            }},
            "columns":[
                {"data":function(data){
                    if(data.bidStatus=="中标"){
                        return '';
                    }else{
                        return '<input autocomplete="off" type="checkbox" class="check" name="select_cat" value="'+data.uid+'">'                            
                    }
                    
                },"orderable":false},
                {"data":"uid","orderable":false},
                {"data":"nickname","orderable":false},
                {"data":"truename","orderable":false},
                {"data":"mobile","orderable":false},
                {"data":"plat","orderable":false},
                {"data":"liveRoom","orderable":false},
                {"data":function(data){
                    if(data.addTime){
                        return $.myTime.UnixToDate(data.addTime/1000,false);
                    }else{
                        return '';
                    }                         
                },"orderable":false},
                {"data":"bidStatus","orderable":false},
                {"data":"singlePrice","orderable":false},
                {"data":function(data){
                    if(data.bidTime){
                        return $.myTime.UnixToDate(data.bidTime/1000,false);
                    }else{
                        return '';
                    }                         
                },"orderable":false},
                               
            ]            
        });
    }

    function assign () {
        ids=check_ids();
        if(ids!=""){
            $('#uids').val(ids);
            
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
        <h3>赏金任务指派-普通</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" id="myform" method="get" enctype="multipart/form-data" action="" role="form">
        <input type="hidden" name="taskId" value="<?php echo $id;?>">
        <input type="hidden" name="uids" id="uids" value="">
        <div class="form-group">
        <label>任务ID：</label>
        <?php echo $id;?>
        </div>

        <div class="form-group" style="margin-left:30px">
        <label>任务类型：</label>
        <?php echo $task_cats[$info['catId']];?>
        </div>

        <div class="form-group" style="margin-left:30px">
        <label>任务价格：</label>
        <?php echo $info['price'];?>
        </div>
        <div class="form-group" style="margin-left:30px">
        <label>中标人数/所需人数：</label>
        <?php echo $info['bidNum'].'/'.$info['num'];?>
        </div>
        <div class="form-group" style="margin-left:30px">
        <label>单份赏金：</label>
        <?php echo round($info['price']/$info['num'],2);?>
        </div>
    </div>
    </form>


    <div class="box-body">
    </div><div style="clear:both;"></div>

    </div><!-- /.box -->
    <div class="form-group">
        <a href="javascript:void(0)" onclick="assign()" class="btn btn-primary btn-sm">指派</a>
    </div>
        <!-- 列表部分 -->  
        <div class="row">

            <div class="col-lg-12">

                <div id="printArea" class="box">

                     <div class="box-title">
                        <h3>指派人员</h3>
                        

                       </div>
                        <div class="box-body">

                        <table class="table table-hover table-striped" id="table">
                        <thead>
                        <tr>
                        <th>选择</th>
                        <th>UID</th>
                        <th>昵称</th>
                        <th>真实姓名</th>
                        <th>手机号</th>
                        <th>平台</th>
                        <th>直播房间</th>
                        <th>报名时间</th>
                        <th>中标状态</th>
                        <th>任务赏金</th>
                        <th>中标时间</th>
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