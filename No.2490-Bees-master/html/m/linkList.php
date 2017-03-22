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
            "ajax":{"url":"interface.php?ac=link/list","type": "POST","data":function(d) {
                //d.page=$('#search_page').val();
                //d.position=$('#search_position').val();
            }},     
            "columns":[
                {"data":"id"},                
                {"data":"title","orderable":false,},
                {"data":"href","orderable":false},
                {"data":"orders","orderable":false},
                
                {"data":function(data){
                        if(data.addTime){
                            return $.myTime.UnixToDate(data.addTime/1000);
                        }else{
                            return '';
                        }                        
                    },"orderable":false},
                {"data":function(data){
                    return '<a onclick="del('+data.id+')" class="btn btn-danger btn-sm" href="javascript:void(0)">删除</a>';                 
                },"orderable":false},                
            ]            
        });
    }
</script>
    <div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>友情链接设置</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">

         <div class="form-group">
        <label>链接名称</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_title" autocomplete="off">
        </div>

        <div class="form-group">
        <label>显示次序</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_orders" autocomplete="off">
        </div>

         <div class="form-group">
        <label>链接地址</label>
        <input class="form-control" type="text" style="width:380px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_href" autocomplete="off">
        </div>


        <a href="#" class="btn btn-info" onclick="add()">新增</a>

        

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
                            
                        <th>ID</th>
                        <th>链接名称</th>
                        <th>链接地址</th>
                        <th>次序</th>
                        <th>创建时间</th>
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
<script type="text/javascript">
    function add () {
        $.post('interface.php?ac=link/add', {title:$('#search_title').val(),orders:$('#search_orders').val(),href:$('#search_href').val()}, function(data, textStatus, xhr) {
            alert('新增成功');
            window.location.reload();
        });
    }
    function del (ids) {
        if(confirm('该操作不可恢复，确认要执行吗?')){
            $.post('interface.php?ac=link/del', {id: ids}, function(data, textStatus, xhr) {
                alert('删除成功');
                window.location.reload();
            });
        } else {return false;}
    }
</script>
<?php include_once 'footer.php';?>
</html>