<?php require('menu.inc.php');?>
<div class="sidebar">
    <div class="user-box">
        <div class="avatar">
            <img src="" alt="" />
        </div>
        <div class="details">
            <p>管理员</p>
            <span class="position">系统管理</span>
        </div>
        <div class="button">
            <a href="logout.php"><i class="fa fa-power-off"></i></a>
        </div>
    </div>
    <ul class="sidebar-menu">
        <!--li class="title">功能导航</li-->
        <?php
        foreach ($menus as $menu) {
            /* 权限判断 */
            if (!isset($menu['allows']) || in_array( $menu['allows'])) {
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

        <!--li class="active sub-nav">
            <a href="#">
                <i class="fa fa-folder"></i> <span>Other Pages</span>
                <i class="fa fa-angle-right pull-right"></i>
            </a>
            <ul class="sub-menu">
                <li><a href="404.html">404 Error</a></li>
                <li class="active"><a href="blank.html">Blank Page</a></li>
                <li><a href="invoice.html">Invoice</a></li>
                <li><a href="login.html">Login</a></li>
                <li><a href="register.html">Register</a></li>
                <li><a href="lockscreen.html">Lockscreen</a></li>
                <li><a href="timeline.html">Timeline</a></li>
            </ul>
        </li-->
    </ul>
</div>