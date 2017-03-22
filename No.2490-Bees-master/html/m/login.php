<?php session_start();unset($_SESSION['seesionKey'])?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <title>Maniac - Login</title>
        
        <!-- Maniac stylesheets -->
        <link rel="stylesheet" href="maniac/css/bootstrap.min.css" />
        <link rel="stylesheet" href="maniac/css/font-awesome.min.css" />
        <link rel="stylesheet" href="maniac/css/animate/animate.min.css" />
        <link rel="stylesheet" href="maniac/css/bootstrapValidator/bootstrapValidator.min.css" />
        <link rel="stylesheet" href="maniac/css/iCheck/all.css" />
        <link rel="stylesheet" href="maniac/css/style.css" />
        
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="login fixed">
        <div class="wrapper animated flipInY">
            <div class="logo"><a href="#"><i class="fa fa-joomla"></i> <span>BEES</span></a></div>
            <div class="box">
                <div class="header clearfix">
                    <div class="pull-left"><i class="fa fa-sign-in"></i> 登录</div>
                    <div class="pull-right"><a href="<?=BASE_URL?>"><i class="fa fa-times"></i></a></div>
                </div>
                <form id="myform" method="post">
                    <div class="alert alert-warning no-radius no-margin padding-sm"><i class="fa fa-info-circle"></i> 请输入账号和密码进行登录</div>
                    <div class="box-body padding-md">
                        <div class="form-group">
                            <input type="text" name="name" class="form-control" placeholder="账号" id="inputMobile"/>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" class="form-control" placeholder="密码" id="inputPassword"/>
                        </div>          
                        
                        <div class="box-footer">                                                               
                            <button onclick="submits()" class="btn btn-success btn-block">登录</button>  
                        </div>
                    </div>
                </form>
            </div>
            
            
            <footer>
                Copyright &copy; 2014 by BEES。
            </footer>
        </div>
        
        <!-- Javascript -->
        <script src="maniac/js/plugins/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="maniac/js/plugins/jquery-ui/jquery-ui-1.10.4.min.js" type="text/javascript"></script>
        
        <!-- Bootstrap -->
        <script src="maniac/js/plugins/bootstrap/bootstrap.min.js" type="text/javascript"></script>
        
        <!-- Interface -->
        <script src="maniac/js/plugins/pace/pace.min.js" type="text/javascript"></script>
        
        <!-- Forms -->
        <script src="maniac/js/plugins/bootstrapValidator/bootstrapValidator.min.js" type="text/javascript"></script>
        <script src="maniac/js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
        <script src="maniac/js/common.js" type="text/javascript"></script>

       
        <script type="text/javascript">
        
        $('#myform').bootstrapValidator({  
            message: '非法的值',
            fields: {
                username: {
                    message: '非法的账号',
                    validators: {
                        notEmpty: {
                            message: '账号不能为空'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        }
                    }
                }   
            },  
        });
        var ajaxUrl='ac=admin/login';
        function success (data) {
            
            if(data.code==200){
                window.location.href="index.php";
            }else{
                alert(data.messages.message);
            }
        }
        
            //iCheck
            $("input[type='checkbox'], input[type='radio']").iCheck({
                checkboxClass: 'icheckbox_minimal',
                radioClass: 'iradio_minimal'
            });
        </script>
    </body>
</html>