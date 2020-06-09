/*
 * jQuery.ScrollUp 0.1.2
 * Copyright (c) 2012 Moocky Mark
 * Date: 2012-02-20 17:22:17 +0800
 */
(function($){
	$.fn.extend({
	  ScrollUp:function(opt,callback){
	  	opt = opt || {};
			var rollspeed = opt.rollspeed ? parseInt(opt.rollspeed) : 50;
			var rollstep  = opt.rollstep ? parseInt(opt.rollstep) : 2;
	    var _this=this;
	    this.eq(0).find("ul:first").html(this.eq(0).find("ul:first").html()+this.eq(0).find("ul:first").html());
			var timerId = setInterval(function(){
				var h = _this.eq(0).find("ul:first").height()/2;
				if($(_this).scrollTop() >= h)
					$(_this).scrollTop($(_this).scrollTop()+rollstep-h);
				else
					$(_this).scrollTop($(_this).scrollTop()+rollstep);
			},rollspeed);
	    _this.mouseover(function(){
		    clearInterval(timerId);
	    }).mouseout(function(){
	     	timerId=setInterval(function(){
					var h = _this.eq(0).find("ul:first").height()/2;
					$('#tttttt'+_this.attr('id')).html($(_this).scrollTop()+':'+h);
					if($(_this).scrollTop() >= h)
						$(_this).scrollTop($(_this).scrollTop()+rollstep-h);
					else
						$(_this).scrollTop($(_this).scrollTop()+rollstep);
				},rollspeed);
	    });
	  }
	});
})(jQuery);