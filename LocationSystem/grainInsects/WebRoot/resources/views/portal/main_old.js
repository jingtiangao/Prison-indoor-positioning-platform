var canvas,context,zoomIn,zoomOut,revert;
var img,//图片对象
    imgIsLoaded,//图片是否加载完成;
    imgX=0,
    imgY=0,
    imgScale=0.5;
	var layer1;
    var layer2;
    var ctx1;
    var ctx2;
      //var x = 2000;
      //var y = 1000;
      var WIDTH = 4381;
      var HEIGHT = 2228;
	  function init() {
	    layer1 = document.getElementById("layer1");
	    ctx1 = layer1.getContext("2d");
	    layer2 = document.getElementById("layer2");
	    ctx2 = layer2.getContext("2d");
		//画布在init里面重绘就好
		
	    setInterval(getXYandDraw, 1000);
	  }

	  function Ondraw() {
		drawImage();
		drawpoint(4381,2228,"张三","http://localhost:8080/grainInsects/upload/image/user4.png");
	  }

	  function drawbottom() {
		ctx1.clearRect(0, 0, WIDTH, HEIGHT);
		ctx1.drawImage(platform, 0, 0);
	  }

	  function drawpoint(x,y,name,head_url) { //draw point and word
			var real_x = imgX+Math.round(imgScale*x);
			var real_y = imgY+Math.round(imgScale*y);
			var radius=10*imgScale;
			ctx2.beginPath();
			ctx2.arc(real_x,real_y,radius,0,2*Math.PI);
			ctx2.fillStyle = "red";
			ctx2.fill();
			ctx2.stroke();
			var font_size = Math.round(imgScale*30);
			ctx2.font = 'bold '+font_size+'px arial';
	        	ctx2.fillStyle = 'blue';
			var xx=Number(real_x)-Number(30*imgScale);
			var yy=Number(real_y)-Number(10*imgScale);
	        	ctx2.fillText(name,xx,yy);
	        
	        head_img = new Image();
	        head_img.src = head_url;
	        context.drawImage(head_img,0,0,head_img.width,head_img.height,real_x,real_y,head_img.width*imgScale,head_img.height*imgScale);
		  }

	 
(function int(){
    canvas=document.getElementById('canvas');
	zoomIn=document.getElementById('zoom-in');
	zoomOut=document.getElementById('zoom-out');
	revert=document.getElementById('revert');
    context=canvas.getContext('2d');
	 layer2 = document.getElementById("canvas");
	 ctx2 = layer2.getContext("2d");
    loadImg();
	   
		//画布在init里面重绘就好
		
//	    setInterval(Ondraw, 100);
    	setInterval(getXYandDraw, 1000);
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
	imgX=0,
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

var total_num;
var absence_num;
var absence_prisoner_list;

function count_init(){
	total_num = 0;
	absence_num = 0;
	absence_prisoner_list = Array(10);
}

function count_state(prisoner_name, state){
	total_num++;
	if(state==2) {
		absence_prisoner_list[absence_num] = prisoner_name;
		absence_num++;
	}
}

function print_prisoners_state(){
	var _easyui_tree_1 = document.getElementById('_easyui_tree_1');
	var tmp = _easyui_tree_1.lastChild;
	tmp.innerHTML = "总人数 :"+total_num;
	
	var _easyui_tree_1 = document.getElementById('_easyui_tree_2');
	var tmp = _easyui_tree_1.lastChild;
	tmp.innerHTML = "已到人数:"+(total_num-absence_num);
	
	var _easyui_tree_1 = document.getElementById('_easyui_tree_3');
	var tmp = _easyui_tree_1.lastChild;
	tmp.innerHTML = "缺勤人数:"+absence_num;
	
	var ul = $('#tt').tree('getSelected');

//	ul.innerHTML = ""; 
//	for(var i=0;i<3;i++){
////		var prison_name = absence_prisoner_list[i];
////		prison_name = "absence";
////		var li = document.createElement('li');
////		var span = document.createElement('span');
////		span.innerHTML = prison_name;
////		li.appendChild(span);
////		ul.appendChild(li);
//		// 追加若干个节点并选中他们
//		$('#tt1').tree('append', {
//			parent: ul.target,
//			data: [{
//				id: 23,
//				text: 'node23'}]
//		});
//	}
}


function getXYandDraw(){
	drawImage();
     $.ajax({
         type: "post",
         url: 'http://localhost:8080/grainInsects/depoter/common/allPrisoner',
         data: {},
         dataType: 'json',
         success: function(re){
        	 console.log(re);
        	 count_init();
        	 for(var i=0;i<re.obj.length;i++){
        		 var prisoner_name=re.obj[i].prisoner_name;
        		 var current_x=re.obj[i].x;
        		 var current_y=re.obj[i].y;
        		 // var prisoner_code=re.obj.prisoner_info_list[i].prisoner_code;
        		 var head_url=re.obj[i].head_url;
        		 var state=re.obj[i].state;
        		 //is_success=re.obj.is_success;
        		 //error_msg=re.obj.error_msg;
        		 current_x = Number(current_x)*0.1443; 
        		 current_y = Number(current_y)*0.1443;
        		 console.log(current_x+' '+current_y+' '+prisoner_name+' '+head_url)
        		 drawpoint(current_x,current_y,prisoner_name,head_url);
        		 count_state(prisoner_name, state);
        	}
//        	 Ondraw();
        	 print_prisoners_state();
		 } 
	 });
  }


