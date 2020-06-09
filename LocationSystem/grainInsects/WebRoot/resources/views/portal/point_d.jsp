
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>

 <head>
     <script type = "text/javascript" src = "jquery.js"></script>
    </head>
  <body>
    <div id="canvasesdiv" style="position:relative; width:4381px; height:2228px">
      <canvas id="layer1"
        style="z-index: 1;
        position:absolute;
        left:0px;
        top:0px;
        " height="2228px" width="4381">
      </canvas>
      <canvas id="layer2"
        style="z-index: 2;
        position:absolute;
        left:0px;
        top:0px;
        " height="2228px" width="4381">
      </canvas>
    </div>

    <script type="text/javascript">
      var layer1;
      var layer2;
      var ctx1;
      var ctx2;
      //var x = 2000;
      //var y = 1000;
      var WIDTH = 4381;
      var HEIGHT = 2228;
      var platform = new Image();
	  var realtime ="realtime";
	  var all      ="all";
	  var imgScale=1;
	  var imgX=0;
	  var imgY=0;
      function init() {
		platform.src ="platform.jpg";
	    layer1 = document.getElementById("layer1");
	    ctx1 = layer1.getContext("2d");
	    layer2 = document.getElementById("layer2");
	    ctx2 = layer2.getContext("2d");
		//画布在init里面重绘就好
		
	    setInterval(getXYandDraw, 1000);
	  }

	  function Ondraw() {
		drawpoint();
	  }

	  function drawbottom() {
		ctx1.clearRect(0, 0, WIDTH, HEIGHT);
		ctx1.drawImage(platform,0,0,platform.width,platform.height,imgX,imgY,platform.width*imgScale,platform.height*imgScale);
	  }

	  function drawpoint(x,y,name) { //draw point and word
		ctx2.beginPath();
		ctx2.arc(x,y,10,0,2*Math.PI);
		ctx2.fillStyle = "red";
		ctx2.fill();
		ctx2.stroke();
		ctx2.font = 'bold 55px arial';
        	ctx2.fillStyle = 'blue';
		var xx=Number(x)+Number(10);
		var yy=Number(y)+Number(10);
        	ctx2.fillText(name,xx,yy);
	  }
	  //ajax 先向后台发送一段json,得到了数据以后，我们就把canvas上面的点重新绘制，绘制前要清一下画布，实现动态的显示
	  function getXYandDraw(){
		drawbottom();
         $.ajax({
             type: "post",
             url: 'http://localhost:8080/grainInsects/depoter/common/allPrisoner',
             data: {},
             dataType: 'json',
             success: function(re){
			 console.log(re);
			 ctx2.clearRect(0,0,layer2.width,layer2.height);//清除画布,仅仅是重新刷上面一层的点就好，最底层的画根本不用动
			 for(var i=0;i<re.obj.length;i++){
			 var current_x=re.obj[i].x;
			 var current_y=re.obj[i].y;
			 var current_uuid=re.obj[i].UUID;
			 var current_status=re.obj[i].status;
			 current_x = Number(current_x)*0.1443; 
			 current_y = Number(current_y)*0.1443;
			 drawpoint(current_x*imgScale,current_y*imgScale,current_uuid);
			 }
			 
		  }
			 
                      
		});
	 }
	  init();
	  layer2.onmousewheel=layer2.onwheel=function(event){
		    var pos=windowToCanvas(layer2,event.clientX,event.clientY);
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
		    drawbottom();	
		}
	  function windowToCanvas(canvas,x,y){
		    var bbox = canvas.getBoundingClientRect();
		    return {
		        x:x - bbox.left - (bbox.width - canvas.width) / 2,
		        y:y - bbox.top - (bbox.height - canvas.height) / 2
		    };
		}
	</script>
  </body>
</html>