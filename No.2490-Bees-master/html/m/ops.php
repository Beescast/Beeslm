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
            "ajax":{"url":"interface.php?ac=admin/list","type": "POST","data":function(d) {
               
            }}, 
            
            "columns":[
            
                {"data":"id"},                
                {"data":"name","orderable":false,},
                {"data":"ops","orderable":false},
                
                {"data":function(data){
                    return '<a href="opsAdd.php?id='+data.id+'" class="btn btn-danger btn-sm" href="">修改</a>&nbsp;<a onclick="del('+data.id+')" class="btn btn-danger btn-sm" href="">删除</a>'
                },"orderable":false}
            ]            
        });
    }
    function del (id) {
        if(confirm('该操作不可恢复，确认要执行吗?')){
            $.post('interface.php?ac=admin/del', {id: id}, function(data, textStatus, xhr) {
                alert('修改成功');
                    window.location.reload();
            });
        }
    }
</script>
    <div class="box-body">
        <a href="opsAdd.php" class="btn btn-info" >增加</a>
    </div>
    <div class="box">
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
                        <th>ID</th>
                        <th>用户名称</th>
                        <th>已有权限</th>

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