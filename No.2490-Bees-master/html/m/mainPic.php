<?php include_once 'header.php';?>
<link rel="stylesheet" href="maniac/css/datatables/dataTables.bootstrap.css" />
<script src="maniac/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
 <script type="text/javascript">
    var tables;
    $(document).ready(function(){
        create_table();
    })
    function del (id) {
        if(confirm('是否删除')){
            $.post('interface.php?ac=banner/del', {id: id}, function(data, textStatus, xhr) {
                //if(data.code==200){
                    alert('删除成功');
                    window.location.reload();
                /*}else{
                    alert('删除失败');
                }*/
            });
        }
    }
    function create_table(){
        tables =$('#table').dataTable({
            dom: 'Brtip',
            buttons: ['excel'],
            <?php include_once 'table.php';?>
            "ajax":{"url":"interface.php?ac=banner/list","type": "POST","data":function(d) {
                
                d.page=$('#search_page').val();
                d.position=$('#search_position').val();
            }},     
            "columns":[
                {"data":"id"},                
                {"data":"page","orderable":false,},
                {"data":"position","orderable":false},
                {"data":"picOrder","orderable":false},
                {"data":"url","orderable":false},
                {"data":function(data){
                    return '<a class="btn btn-primary btn-sm" href="mainPicAdd.php?id='+data.id+'">修改图片</a>&nbsp;<a class="btn btn-primary btn-sm" href="javascript:void(0)" onclick="del('+data.id+')">删除图片</a>';                 
                },"orderable":false},                
            ]            
        });
    }
</script>
    <div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>主要图片配置</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">

        <div class="form-group">
        <label>页面名称</label>
            <?php create_optiones($pic_name,'page');?>
        </div>

        <div class="form-group">
        <label>图片位置</label>
            <?php create_optiones($pic_position,'position');?>
        </div>
        
        <a href="#" class="btn btn-info" onclick="searchs()">查询</a>
         <a href="mainPicAdd.php" class="btn btn-primary btn-sm" >新增图片</a> 


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
                        <th>页面名称</th>
                        <th>图片位置</th>

                        <th>显示顺序</th>
                        <th>链接地址</th>
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