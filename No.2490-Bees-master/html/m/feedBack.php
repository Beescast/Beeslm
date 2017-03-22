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
            "ajax":{"url":"interface.php?ac=msg/list","type": "POST","data":function(d) {
                d.uid=$('#search_uid').val();
                d.name=$('#search_name').val();
                d.content=$('#search_content').val();
            }},     
            "columns":[
                {"data":"id"},                
                {"data":"name","orderable":false},
                {"data":"content","orderable":false},
                
                {"data":function(data){
                    if(data.addTime){
                        return $.myTime.UnixToDate(data.addTime/1000);
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
        <h3>意见反馈</h3>
        
    </div>
    <div class="box-body">
    <form class="form-inline" name="" method="get" enctype="multipart/form-data" action="" role="form">


         <div class="form-group">
        <label>UID</label>
        <input class="form-control" type="text" style="width:380px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_uid" autocomplete="off">
        </div>

         <div class="form-group">
        <label>用户姓名</label>
        <input class="form-control" type="text" style="width:380px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_name" autocomplete="off">
        </div>

         <div class="form-group">
        <label>意见内容</label>
        <input class="form-control" type="text" style="width:380px;" value="" size="35" placeholder="" data-inputmask="'mask': ''" id="search_content" autocomplete="off">
        </div>

        <a href="#" class="btn btn-info" onclick="searchs()">查询</a>
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
                        <th>用户姓名</th>
                        <th>反馈内容</th>
                        <th>反馈时间</th>
             
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