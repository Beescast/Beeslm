(function($) {
	"use strict";
    //Enable sidebar toggle
	$('.sidebar-toggle').click( function() {
		$("body").toggleClass("sidebar-sm");
		$(".leftside, header .logo").toggleClass("display-inline");
		return false;
    });
	
	//Dropdown Animated
	$('.dropdown').on('show.bs.dropdown', function () {
		$(this).find('.dropdown-menu').addClass('animated flipInY');
	});
	$('.dropdown').on('hide.bs.dropdown', function () {
		$(this).find('.dropdown-menu').removeClass('animated flipInY');
	});	
	
    //Tooltip
    $("[data-toggle='tooltip']").tooltip();
	
	//Dropdown-menu Scroll
	$(".navbar .dropdown-menu ul").slimscroll({
        alwaysVisible: false,
        size: "3px",
        height: "210px"
    }).css("width", "100%");
	
    //Collapse Box
	$('.collapse-box').click(function(b){
		b.preventDefault();
		var $box = $(this).parent().parent().next('.box-body');
		if($box.is(':visible')) 
		{
		  $(this).children('i').removeClass('fa-chevron-up');
		  $(this).children('i').addClass('fa-chevron-down');
		}
		else 
		{
		  $(this).children('i').removeClass('fa-chevron-down');
		  $(this).children('i').addClass('fa-chevron-up');
		}            
		$box.slideToggle("slow");
	}); 
	
	//Remove Box
    $(".remove-box").click(function() {
        var box = $(this).parents(".box").first();
        box.slideUp();
    });

	$(".sidebar").slimscroll({
		color: "rgba(0,0,0,0.5)",
		height: ($(window).height() - $("header").height() - $(".sidebar-widget .footer").innerHeight()) + "px",
	});

	//Sidebar
    $.fn.sub = function() {
        return this.each(function() {  var btn = $(this).children("a").first(); var menu = $(this).children(".sub-menu").first();  var active = $(this).hasClass('active');
		if (active) {
            menu.show();
            btn.children(".fa-angle-right").first().removeClass("fa-angle-right").addClass("fa-angle-down");
        }
        btn.click(function(e) { e.preventDefault();
			if (active) {
				menu.slideUp(200);
				active = false;
				btn.children(".fa-angle-down").first().removeClass("fa-angle-down").addClass("fa-angle-right");
				btn.parent("li").removeClass("active");
			} else {
				menu.slideDown(200);
				active = true;
				btn.children(".fa-angle-right").first().removeClass("fa-angle-right").addClass("fa-angle-down");
				btn.parent("li").addClass("active");
			}
        });
    });
};
})(jQuery);

//Sidebar Nav
 $(".sidebar .sub-nav").sub();