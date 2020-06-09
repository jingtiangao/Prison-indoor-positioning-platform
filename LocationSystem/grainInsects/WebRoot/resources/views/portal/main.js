var canvas,context,zoomIn,zoomOut,revert;
var all_Prisoner = new Array("prisoner_name", "current_x", "current_y","prisoner_code", "head_url", "state"); 
var img,//图片对象
    imgIsLoaded,//图片是否加载完成;
    imgX=-650,
    imgY=0,
    imgScale=0.5;
	var layer1;
    var layer2;
    var ctx1;
    var ctx2;
      //var x = 2000;
      //var y = 1000;
    
    //对于超出组内最大间距报警音频的处理
    var audioElement;//audio 音频控件
    var isplay;//布尔值，是否有人超出组内最大间距报警
    var warningicon;//超出最大间距的报警图标
	  function Ondraw() {
		drawImage();
		//drawpoint(1000,500,"张三");
	  }

	  function drawpoint(x,y,name,head_url,color) { //draw point and word
			
		  	 
		  
		  	var real_x=imgX+Math.round(imgScale*x);
			var real_y=imgY+Math.round(imgScale*y);
			var radius=10*imgScale;
			ctx2.beginPath();
			ctx2.arc(real_x,real_y,radius,0,2*Math.PI);//把画的点按照调节因子放大缩小
			ctx2.fillStyle = ""+color;
			ctx2.fill();
			ctx2.stroke();
			var font_size = Math.round(imgScale*30);
			ctx2.font = 'bold '+font_size+'px arial';
	        ctx2.fillStyle = 'blue';
			var xx=Number(real_x)-Number(30*imgScale);
			var yy=Number(real_y)-Number(10*imgScale); //把文本的位置也按照调节因子放大缩小
	        	ctx2.fillText(name,xx,yy);

      //画图像
      var head_img= new Image();
      head_img.src= head_url;
      ctx2.drawImage(head_img,0,0,head_img.width,head_img.height,real_x-Number(20*imgScale),real_y+Number(10*imgScale),head_img.width*imgScale,head_img.height*imgScale);

		  }

	  
    function getDataAndDraw(){
        //按照参数的不同选择不同的接口并调用
    	drawImage();
        if (dataArray.flag=='1' ){//所有
            getPrisonDataAndDraw();
        }else if(dataArray.flag=='2'){//组
            getGroupDataAndDraw();      
        }else if( dataArray.flag=='3'){//犯人编号
            getPrison_codeDataAndDraw();
        }
        //重新加载缺勤树
        var node = $('#tt').tree('getSelected'); 
        
        $('#tt').tree('reload', node);

    }
    function getPrisonDataAndDraw(){ //对应接口4获取犯人信息接口
    		             //dataArray存在Seach.js里面，存下将要查询定位的数据
            $.ajax({
                         type: "post",
                         //url: 'http://10.108.164.132:8080/grainInsects/depoter/common/allPrisoner',
                         url: 'http://localhost:8080/grainInsects/depoter/common/allPrisoner',
                        //  url:'http://localhost:8080/grainInsects/depoter/common//allprisoner_count',
                         data: {},
                         dataType: 'json',
                         success: function(re){
                         console.log(re);
                        // ctx2.clearRect(0,0,layer2.width,layer2.height);//
                         isplay =false;
                         for(var i=0;i<re.obj.length;i++){
	                            var prisoner_name=re.obj[i].prisoner_name;
	                            var current_x=re.obj[i].x;
	                            var current_y=re.obj[i].y;
	                            var prisoner_code=re.obj[i].prisoner_code;
	                            var head_url=re.obj[i].head_url;
	                            var state=re.obj[i].state;
	                            //is_success=re.obj.is_success;
	                            //error_msg=re.obj.error_msg;
	                            current_x = Number(current_x)*0.1443; 
	                            current_y = Number(current_y)*0.1443;
	                            //
	                            if(state==1){
	                            drawpoint(current_x,current_y,prisoner_name+prisoner_code,head_url,"green");
	                            }else if(state==4){
	                            drawpoint(current_x,current_y,prisoner_name+prisoner_code,head_url,"red");
	                            isplay=true;
	                            }
                         }
                         //出现了超出最大间距的人就播放报警音乐，反之停止播放
                         if(isplay){
                             audioElement.play();
                             warningicon[0].style.color="red";
                          }else{
                             audioElement.pause();
                             warningicon[0].style.color="green";
                          }
                         
                         
                      }
             
                      
                });
    }
    function getPrison_codeDataAndDraw(){ //对应接口5获取犯人编号信息接口
                          //dataArray存在Seach.js里面，存下将要查询定位的数据
            $.ajax({
                         type: "post",
                         //url: 'http://10.108.164.132:8080/grainInsects/depoter/common/find/prisoner_code',
                         url: 'http://localhost:8080/grainInsects/depoter/common/find/prisoner_code',

                         data: {prisoner_code:dataArray.prisoner_code_list},
                         dataType: 'json',
                         success: function(re){
                         console.log(re);
                        // ctx2.clearRect(0,0,layer2.width,layer2.height);//
                         isplay =false;
                         for(var i=0;i<re.obj.length;i++){
                            var prisoner_name=re.obj[i].prisoner_name;
                            var current_x=re.obj[i].x;
                            var current_y=re.obj[i].y;
                            var prisoner_code=re.obj[i].prisoner_code;
                            var head_url=re.obj[i].head_url;
                            var state=re.obj[i].state;
                            //var state=re.obj.prisoner_info_list[i].state;
                            //is_success=re.obj.is_success;
                            //error_msg=re.obj.error_msg;
                            current_x = Number(current_x)*0.1443; 
                            current_y = Number(current_y)*0.1443;
                            
                            if(state==1){
	                           drawpoint(current_x,current_y,prisoner_name+prisoner_code,head_url,"green");
	                         }else if(state==4){
	                           drawpoint(current_x,current_y,prisoner_name+prisoner_code,head_url,"red");
	                          isplay=true;
	                         }
                         }
                         //出现了超出最大间距的人就播放报警音乐，反之停止播放
                         if(isplay){
                             audioElement.play();
                             warningicon[0].style.color="red";
                          }else{
                             audioElement.pause();
                             warningicon[0].style.color="green";
                          }	
                        
                         
                      }
             
                      
                });
    }
    function getGroupDataAndDraw(){//对应接口6获取犯人按照组信息接口
             
             $.ajax({
                             type: "post",
                            // url: 'http://10.108.164.132:8080/grainInsects/depoter/common/find/group_name',
                             url: 'http://localhost:8080/grainInsects/depoter/common/find/group_name',

                             data: {group_name:dataArray.group_name},
                             dataType: 'json',
                             success: function(re){
                             console.log(re);
                           //  ctx2.clearRect(0,0,layer2.width,layer2.height);//
                             isplay =false;
                             for(var i=0;i<re.obj.length;i++){
                                var prisoner_name=re.obj[i].prisoner_name;
                                var current_x=re.obj[i].x;
                                var current_y=re.obj[i].y;
                                var prisoner_code=re.obj[i].prisoner_code;
                                var head_url=re.obj[i].head_url;
                                //var state=re.obj.prisoner_info_list[i].state;
                                //is_success=re.obj.is_success;
                                //error_msg=re.obj.error_msg;
                                var state=re.obj[i].state;
                                current_x = Number(current_x)*0.1443; 
                                current_y = Number(current_y)*0.1443;
                                
                                if(state==1){
     	                           drawpoint(current_x,current_y,prisoner_name+prisoner_code,head_url,"green");
     	                         }else if(state==4){
     	                           drawpoint(current_x,current_y,prisoner_name+prisoner_code,head_url,"red");
     	                          isplay=true;
     	                         }
                             }
                             //出现了超出最大间距的人就播放报警音乐，反之停止播放
                             if(isplay){
                                 audioElement.play();
                                 warningicon[0].style.color="red";
                              }else{
                                 audioElement.pause();
                                 warningicon[0].style.color="green";
                              }	
                          }                   
                    });//按照组来获取犯人信息
        }
    	 
(function int(){
    canvas=document.getElementById('canvas');
	zoomIn=document.getElementById('zoom-in');
	zoomOut=document.getElementById('zoom-out');
	revert=document.getElementById('revert');
	
    context=canvas.getContext('2d');//context是2D画布
    
	 layer2 = document.getElementById("canvas");//这个就是主画布
	 ctx2 = layer2.getContext("2d");
	 
	 bottom = document.getElementById("layer2");//这个就是主画布
	 botm = bottom.getContext("2d");
    loadImg();
	   
		//画布在init里面重绘就好

		//选择那一个列表，并且初始化
          $('#tt').tree({    
        //  url: 'http://10.108.164.132:8080/grainInsects/depoter/common//allprisoner_count',   // 这里放置缺勤人数的那个接口
          url: 'http://localhost:8080/grainInsects/depoter/common//allprisoner_count',   // 这里放置缺勤人数的那个接口

          loadFilter: function(data){    
                
        	  	data.obj[0].text="总人数："+data.obj[0].text;
        	  	data.obj[1].text="已到人数："+data.obj[1].text;
        	  	data.obj[2].text="缺勤人数："+data.obj[2].text;
        	  	data.obj[3].text="请假人数："+data.obj[3].text;
                  return data.obj;            
          }    
          });   


          //读入warning图片
          warningicon= document.getElementsByClassName("fa-warning");
         //读入音频
           audioElement = document.createElement('audio');
          audioElement.setAttribute('src', 'wav/baojing.wav');
          						
          audioElement.autoplay=false;
          audioElement.loop=true;
          //audioElement.load()
          //audioElement.play();

      
          
	  //  //设定的刷新时间
          
      setInterval(getDataAndDraw, 1000);//设定的刷新时间 这个是加入搜索空间以后...
      //setInterval(Ondraw, 1);
})();



function loadImg(){
    img=new Image();
    img.onload=function(){
        imgIsLoaded=true;
		imgScale=0.5;
        drawImage();
		
    }
    img.src="platform.jpg";
}

function drawImage(){
    context.clearRect(0,0,canvas.width,canvas.height);
    context.drawImage(img,0,0,img.width,img.height,imgX,imgY,img.width*imgScale,img.height*imgScale);
	
}

zoomIn.onclick=function(event){
	var pos=windowToCanvas(canvas,event.clientX,event.clientY);
	imgScale*=1.3;
	imgX+=(img.width-1.3*img.width)/2;
	imgY+=(img.height-1.3*img.height)/2;
	drawImage();
}

zoomOut.onclick=function(event){
	var pos=windowToCanvas(canvas,event.clientX,event.clientY);
	imgScale*=0.8;
	if(imgScale>0.2){
		imgX+=(img.width-0.8*img.width)/2;
		imgY+=(img.height-0.8*img.height)/2;
		drawImage();
	}
	else{
		imgScale/=0.8;
	}
}

revert.onclick=function(event){
	imgX=-650,
    imgY=0,
    imgScale=0.5;
	drawImage();
	
}
canvas.onmousedown=function(event){
    var pos=windowToCanvas(canvas,event.clientX,event.clientY);
    canvas.onmousemove=function(event){
        canvas.style.cursor="move";
        var pos1=windowToCanvas(canvas,event.clientX,event.clientY);
        var x=pos1.x-pos.x;
        var y=pos1.y-pos.y;
        pos=pos1;
        imgX+=x;
        imgY+=y;
        drawImage();
    }
    canvas.onmouseup=function(){
        canvas.onmousemove=null;
        canvas.onmouseup=null;
        canvas.style.cursor="default";
    }
}
canvas.onmousewheel=canvas.onwheel=function(event){
    var pos=windowToCanvas(canvas,event.clientX,event.clientY);
    event.wheelDelta=event.wheelDelta?event.wheelDelta:(event.deltaY*(-40));
    if(event.wheelDelta>0){
        imgScale*=2;
        imgX=imgX*2-pos.x;
        imgY=imgY*2-pos.y;
    }else{
        imgScale/=2;
        imgX=imgX*0.5+pos.x*0.5;
        imgY=imgY*0.5+pos.y*0.5;
    }
    drawImage();
}

function windowToCanvas(canvas,x,y){
    var bbox = canvas.getBoundingClientRect();
    return {
        x:x - bbox.left - (bbox.width - canvas.width) / 2,
        y:y - bbox.top - (bbox.height - canvas.height) / 2
    };
}

