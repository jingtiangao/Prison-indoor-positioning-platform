//扩展验证方法  
$.extend($.fn.validatebox.defaults.rules, {  
    check_bm:{//检查编号 
        validator: function (value,param) {
            var checkbm=$.ajax({  
                async : false,    
                cache : false,  
                type: "post",
                url: 'check_bm',
                data : {
                    "bm" : value
                }
            }).responseText;    
            return checkbm==="true";   
        },  
        message: '该编号已被占用'  
    },
    
    check_mc:{//检查中文名 
        validator: function (value,param) {
        	console.log("进入验证");
        	console.log(value);
            var checkmc=$.ajax({  
                async : false,    
                cache : false,  
                type: "post",
                url: 'check_mc',
                data : {
                    "mc" : value
                }
            }).responseText;
            console.log(checkmc);
            return checkmc==="true";   
        },  
        message: '该中文名已被占用'  
    },
    
    check_ywm:{//检查英文名 
        validator: function (value,param) {
        	console.log("进入验证");
        	console.log(value);
            var checkywm=$.ajax({  
                async : false,    
                cache : false,  
                type: "post",
                url: 'check_ywm',
                data : {
                    "ywm" : value
                }
            }).responseText;    
            return checkywm==="true";   
        },  
        message: '该英文名已被占用'  
    }
      
});   