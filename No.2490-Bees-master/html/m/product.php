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
            "ajax":{"url":"interface.php?ac=product/list","type": "POST","data":function(d) {
            }},     
            "columns":[
                {"data":"id"},                
                {"data":"name","orderable":false,},
                {"data":"href","orderable":false},
                {"data":function(data){
                    return '<a class="btn btn-primary btn-sm" href="productAdd.php?id='+data.id+'">修改</a>&nbsp;&nbsp;<a onclick="del('+data.id+')" class="btn btn-danger btn-sm" href="javascript:void(0)">删除</a>';                 
                },"orderable":false},                
            ]            
        });
    }
    function del (ids) {
        if(confirm('该操作不可恢复，确认要执行吗?')){
            $.post('interface.php?ac=product/del', {id: ids}, function(data, textStatus, xhr) {
                alert('删除成功');
                window.location.reload();
            });
        } else {return false;}
    }
</script>
    <div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>商品配置</h3>
        
    </div>
    <div class="box-body">
        <a href="productAdd.php" class="btn btn-primary btn-sm" >新增商品</a> 
    </div>

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
                        <th>ID</th>
                        <th>名称</th>
                        <th>链接</th>
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