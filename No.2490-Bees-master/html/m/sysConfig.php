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
            "ajax":{"url":"interface.php?ac=config/list","type": "POST","data":function(d) {
                //d.page=$('#search_page').val();
                //d.position=$('#search_position').val();
            }},     
            "columns":[
                {"data":"id"},                
                {"data":"type","orderable":false,},
                {"data":"value","orderable":false},
               
                {"data":function(data){
                        if(data.addTime){
                            return $.myTime.UnixToDate(data.addTime/1000);
                        }else{
                            return '';
                        }                        
                    },"orderable":false},
                {"data":function(data){
                    return '<a onclick="edit(\''+data.id+'\',\''+data.type+'\',\''+data.value+'\')" class="btn btn-danger btn-sm" href="javascript:void(0)">编辑</a>';                 
                },"orderable":false},                
            ]            
        });
    }
</script>
    <div class="box">

    <div class="box-title">
        <i class="fa fa-signal"></i>
        <h3>系统设置</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">

         <div class="form-group">
        <label>参数ID</label>
            <span id="search_id"></span>
        </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

        <div class="form-group">
        <label>参数名称</label>
        <span id="search_type"></span>
        </div><br><br>

         <div class="form-group">
        <label>参数值</label>
        <input class="form-control" type="text" style="width:380px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_value" autocomplete="off">
        </div>


        <a href="#" class="btn btn-info" onclick="save()">保存</a>

        

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
                        <th>参数名称</th>
                        <th>参数值</th>
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
    function save () {
        if($('#search_id').val()!=null){
            $.post('interface.php?ac=config/edit', {id:$('#search_id').html(),value:$('#search_value').val()}, function(data, textStatus, xhr) {
                alert('保存成功');
                window.location.reload();
            });
        }
        
    }
    function edit (ids,type,value) {
        $('#search_id').html(ids);
        $('#search_type').html(type);
        $('#search_value').val(value);
    }
</script>
<?php include_once 'footer.php';?>
</html>