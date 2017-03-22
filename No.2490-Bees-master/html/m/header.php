<?php 
    include 'config.php';
    require('menu.inc.php');
    if(!$_SESSION['seesionKey']){
        header('location:login.php');
        exit;
    }
?>
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
        

        <!-- Javascript -->
        <!--script type="text/javascript" src="../maniac/mr/js/jquery-1.8.31.min.js"></script--> 
    <!-- cr001 -->
        <script src="../maniac/js/plugins/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="../maniac/js/plugins/jquery-ui/jquery-ui-1.10.4.min.js" type="text/javascript"></script>
        <script src="../maniac/js/common.js" type="text/javascript"></script>
        
        
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        
        
        <script type="text/javascript">
        function showimage(source)
             {
                 $(".modal-content").html("<image src='"+source+"' class='carousel-inner img-responsive img-rounded' />");
                 $("#show_dialog").modal();
             }
            $(document).ready(function() {
                /*var isIE=!!window.ActiveXObject;
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
                $("body").iealert();*/
            });
        </script>
        <style>
            .file-input-wrapper { overflow: hidden; position: relative; cursor: pointer; z-index: 1; }.file-input-wrapper input[type=file], .file-input-wrapper input[type=file]:focus, .file-input-wrapper input[type=file]:hover { position: absolute; top: 0; left: 0; cursor: pointer; opacity: 0; filter: alpha(opacity=0); z-index: 99; outline: 0; }.file-input-name { margin-left: 8px; }
            .timeline-centered .timeline-entry { margin-bottom: 15px; }
        </style>
    </head>
    <body class="fixed">
        <div class="modal fade bs-example-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="show_dialog" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content"></div>
          </div>
        </div>
        
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
                    
                </div>
                <div class="navbar-right">
                    <a href="changePass.php" style="padding:10px;"><i class="fa fa-user"></i>修改密码</a>
                    <a href="login.php" style="padding:10px;"><i class="fa fa-power-off"></i>退出系统</a>
                    
                </div>
            </nav>
        </header>
        <!-- /.header -->
        
        <div class="wrapper">
            <div class="leftside" style="height:100%;">
                <div class="sidebar" style="height:100%;">
                    
                    <ul class="sidebar-menu" style="height:100%;overflow-y:scroll">
                        
                        <?php
                        foreach ($menus as $menu) {
                            /* 权限判断 */
                            if (in_array($menu['ops'], $_SESSION['ops'])||$menu['ops']==0) {
                                if (is_array($menu['links'])) {
                                    if ($currentNav[0] == $menu['name']) {
                                        echo "<li class=\"active sub-nav\">";
                                    } else {
                                        echo "<li class=\"sub-nav\">";
                                    }
                                    
                                    echo "<a href=\"#\"><i class=\"{$menu['icon']}\"></i>
                                    <span>{$menu['name']}</span>
                                    <i class=\"fa fa-angle-right pull-right\"></i>
                                    </a>
                                    <ul class=\"sub-menu\">";
                                        foreach ($menu['links'] as $key => $value) {
                                            if ($currentNav[1] == $key) {
                                                echo "<li class=\"active\">";
                                            } else {
                                                echo "<li>";
                                            }

                                            if (is_array($value)) {
                                                echo "<a href=\"{$value['href']}\" target=\"{$value['target']}\">{$key}</a>";
                                            } else {
                                                echo "<a href=\"{$value}\">{$key}</a>"; 
                                            }
                                            echo "</li>";
                                        }
                                    echo "</ul>";
                                } else {
                                    if ($currentNav[0] == $menu['name']) {
                                        echo "<li class=\"active\">";
                                    } else {
                                        echo "<li>";
                                    }
                                    echo "<a href=\"{$menu['links']}\"><i class=\"{$menu['icon']}\"></i> <span>{$menu['name']}</span></a>";
                                }
                                echo "</li>";
                            }
                        }
                        ?>
                    </ul>
                 </div>
            </div>

            <div class="rightside">
        
            <div class="content">
                <div class="modal fade bs-example-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="show_dialog" aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content" id="img_show"></div>
                  </div>
                
        </div>
    </div>