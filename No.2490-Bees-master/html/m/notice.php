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
            "ajax":{"url":"interface.php?ac=notice/list","type": "POST","data":function(d) {
                
                d.status=$('#search_status').val();
                d.title=$('#search_name').val();
                d.startTime=$('#search_startTime').val();
                d.endTime=$('#search_endTime').val();               
            }},     
            "columns":[
                {"data":"title","orderable":false},                
                {"data":"status","orderable":false,},
                {"data":function(data){
                    if(data.addTime){
                        return $.myTime.UnixToDate(data.addTime/1000,false);
                    }else{
                        return '';
                    }                         
                },"orderable":false},
                {"data":function(data){
                    return '<a href="noticeAdd.php?id='+data.id+'" class="btn btn-danger btn-sm">编辑</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="del('+data.id+')" class="btn btn-danger btn-sm">删除</a>';                 
                },"orderable":false},
            ]            
        });
    }
</script>
    <div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>公告列表</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">
        <div class="form-group">
        <label>公告标题</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_name" autocomplete="off">
        </div>


        <div class="form-group">
        <label>状态</label>
            <?php create_optiones($notice_status,'status');?>
        </div>

        <div class="form-group">
        <label>创建时间</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" id="search_startTime" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
        </div>
        <div class="form-group">
        <label>至</label>
        <input class="form-control" type="text" style="width:180px;" value="" size="35" id="search_endTime" placeholder="" data-inputmask="'mask': ''" name="shop_name" autocomplete="off" class="form-control Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="height:auto;width:120px;">
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
                        <h3>公告查询</h3>
                        

                       </div>
                        <div class="box-body">

                        <table class="table table-hover table-striped" id="table">
                        <thead>
                        <tr>
                        <th>公告标题</th>
                        <th>状态</th>
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
    function del (id) {
        if(confirm('是否删除')){
            $.post('interface.php?ac=notice/del', {id: id}, function(data, textStatus, xhr) {
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
<?php include_once 'footer.php';?>
</html>