<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>管理后台</title>
        <!-- Maniac stylesheets -->
        <link rel="stylesheet" href="../maniac/css/bootstrap.min.css" />
        <link rel="stylesheet" href="../maniac/css/font-awesome.min.css" />
        <link rel="stylesheet" href="../maniac/css/animate/animate.min.css" />
        <link rel="stylesheet" href="../maniac/css/fullcalendar/fullcalendar.css" />
        <link rel="stylesheet" href="../maniac/css/style.css" />
        <link rel="stylesheet" href="../maniac/mr/css/extra.css" />
        <link rel="stylesheet" href="../maniac/mr/css/ie.css" />

        <!-- Javascript -->
        <!--script type="text/javascript" src="../maniac/mr/js/jquery-1.8.31.min.js"></script--> 
    <!-- cr001 -->
        <script src="../maniac/js/plugins/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="../maniac/js/plugins/jquery-ui/jquery-ui-1.10.4.min.js" type="text/javascript"></script>
        
        <script src="../maniac/mr/js/iealert.js" type="text/javascript"></script>
        
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        
        
        <script type="text/javascript">
            $(document).ready(function() {
                var isIE=!!window.ActiveXObject;
                var isIE6=isIE&&!window.XMLHttpRequest;
                var isIE8=isIE&&!!document.documentMode;
                var isIE7=isIE&&!isIE6&&!isIE8;
                if (isIE){
                    if (isIE6){
                        alert("ie6");
                    }else if (isIE8){
                        alert("ie8");
                    }else if (isIE7){
                        alert("ie7");
                    }
                }
                $("body").iealert();
            });
        </script>
        <style>
            .file-input-wrapper { overflow: hidden; position: relative; cursor: pointer; z-index: 1; }.file-input-wrapper input[type=file], .file-input-wrapper input[type=file]:focus, .file-input-wrapper input[type=file]:hover { position: absolute; top: 0; left: 0; cursor: pointer; opacity: 0; filter: alpha(opacity=0); z-index: 99; outline: 0; }.file-input-name { margin-left: 8px; }
            .timeline-centered .timeline-entry { margin-bottom: 15px; }
        </style>
    </head>
    <body class="fixed">
        <iframe src="" name="ajax_iframe" style="display:none"></iframe>
        <!-- Header -->
        <header>
            <a href="" class="logo"><i class="fa fa-joomla"></i> <span>BEES</span></a>
            <nav class="navbar navbar-static-top">
                <a href="#" class="navbar-btn sidebar-toggle">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-header">
                    <form role="search" class="navbar-form" method="post" action="<?php $_SERVER['PHP_SELF'] ?>" name="search">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="搜索..."  name="name"/>
                            <span class="input-group-btn">
                                <button type="submit" id="search-btn" class="btn"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
                </div>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">                  
                        <li class="dropdown widget-user">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img src="" class="pull-left" alt="image" />
                                <span> <i class="fa fa-caret-down"></i></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href=""><i class="fa fa-user"></i>修改密码</a>
                                </li>
                                <li class="footer">
                                    <a href=""><i class="fa fa-power-off"></i>退出系统</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <!-- /.header -->
