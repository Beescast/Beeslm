
        <!-- Bootstrap -->
        <script src="../maniac/js/plugins/bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="../maniac/js/plugins/gritter/jquery.gritter.min.js" type="text/javascript"></script>

        <!-- Charts -->
        <script src="../maniac/js/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
        <script src="../maniac/js/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
        <script src="../maniac/js/plugins/flot/jquery.flot.pie.min.js" type="text/javascript"></script>
        <script src="../maniac/js/plugins/flot/jquery.flot.stack.min.js" type="text/javascript"></script>
        <script src="../maniac/js/plugins/flot/jquery.flot.crosshair.min.js" type="text/javascript"></script>
        <script src="../maniac/js/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
        <script src="../maniac/js/plugins/jquery-jvectormap/jquery-jvectormap-europe-merc-en.js" type="text/javascript"></script>

        <!-- Interface -->
        <script src="../maniac/js/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="../maniac/js/plugins/pace/pace.min.js" type="text/javascript"></script>
        
        <!-- Forms -->
        
        <script src="../maniac/js/plugins/datepicker/datepicker.js" type="text/javascript"></script>
        
        <script src="../maniac/js/custom.js" type="text/javascript"></script>

        
        <!-- Add -->
        <script src="../maniac/js/jquery-migrate-1.2.1.js"></script>
        <script src="../maniac/js/ckeditor/ckeditor.js" type="text/javascript" language="javascript"></script>
        <script src="../maniac/js/jquery.jqprint-0.3.js" type="text/javascript" language="javascript" ></script>
        <script src="../maniac/js/calendar/WdatePicker.js" type="text/javascript" language="javascript"></script>
        

        <script language="javascript">


        function  printArea(){
            $(".noprint").hide();
            $("#printArea").jqprint();
        }
        </script>
        

            <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>
        <script type="text/javascript">
            var map = new BMap.Map("container");
            map.centerAndZoom("南京", 12);
            map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
            map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

            map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
            map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
            map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开

            var localSearch = new BMap.LocalSearch(map);
            localSearch.enableAutoViewport(); //允许自动调节窗体大小
        function searchByStationName() {
            map.clearOverlays();//清空原来的标注
            var keyword = document.getElementById("text_").value;


            localSearch.setSearchCompleteCallback(function (searchResult) {
                var poi = searchResult.getPoi(0);
                document.getElementById("jingdu").value = poi.point.lng;
                document.getElementById("weidu").value = poi.point.lat;
              
                map.centerAndZoom(poi.point, 13);
                var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
                map.addOverlay(marker);
                var content = document.getElementById("text_").value + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
                var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
                marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
                
            });
            localSearch.search(keyword);
        } 
        </script>
    </body>
</html>