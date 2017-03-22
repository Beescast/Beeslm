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
    
    function create_table(){
        tables =$('#table').dataTable({
            dom: 'Brtip',
            buttons: ['excel'],
            <?php include_once 'table.php';?>
            "ajax":{"url":"interface.php?ac=user/adminList","type": "POST","data":function(d) {
                d.id=$('#search_id').val();
                d.mobile=$('#search_mobile').val();
            }},     
            "columns":[
                {"data":function(data){
                    return '<input autocomplete="off" type="checkbox" class="check" name="select_cat" value="'+data.id+'">'                
                },"orderable":false},
                {"data":"id","orderable":false},
                {"data":"nickname","orderable":false},
                {"data":"truename","orderable":false},
                {"data":"mobile","orderable":false},
                {"data":"plat","orderable":false},
                {"data":"liveRoom","orderable":false},
                           
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
        <h3>赏金任务指派-高级</h3>
        
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
    
    </form>
    
        <form>
        <div class="form-group">
        <label>UID</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_id" autocomplete="off">
        </div>

        <div class="form-group">
        <label>手机号</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_mobile" autocomplete="off">
        </div>

        <a href="#" class="btn btn-info" onclick="searchs()">查询</a> <a href="#" class="btn btn-info" onclick="assign()">指派</a>
        </form>

    </div>

    <div class="box-body">
    </div><div style="clear:both;"></div>

    </div><!-- /.box -->
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