/*对于不同内容进行定位的思路是这样的

定义了一个argument参数，默认为1，表示all，2表示按照组，3表示按照人名，表示用户到底点了哪一个选项
定义了一个dataArray键值对，单击了定位后，把现在的argment参数存入flag，把操作type存入type,把input框的内容存入剩下Key值里面

Ajax思路：定时执行getdataanddraw函数，首先对于dataArrayflag进行判断，断定需要调用的接口，然后分别调用不同接口，把点在画布上绘制出来

默认的画所有用户的信息

由于是在同一个页面引入的js文件,所以变量是共享的
*/
var elem;
var argument=1;//确定哪一个按键被按下
var test;
var inputText=document.getElementById('search');//获取文本框中的内容

var  dataArray={flag:'1',type:'all',prisoner_code_list:'',group_name:''};
(function int(){
    var searClass = document.getElementsByTagName("span");
    for(var i=0;i<searClass.length;i++){
      elem = searClass[i];
      elem.addEventListener("click",function(){
        for(var j=0;j<searClass.length;j++){
           searClass[j].style.color="#666";
        }
        this.style.color="#5af";
		argument=this.attributes[0].nodeValue;
    });
  }
})();
function locate(){
	
	//get the input content and change data array
	switch(argument){
	case '1':  //1代表所有
	dataArray.type='all';
	dataArray.prisoner_code_list='';
	dataArray.group_name='';
	dataArray.flag='1';
	//test={data:dataArray.type};
	break;
	case '3': //3代表按照编号
	dataArray.type='prisoner_code';
	dataArray.prisoner_code_list=inputText.value;//get input content
	dataArray.group_name='';
	dataArray.flag='3';
	break;
	case '2': //按照组名
	dataArray.type='group_name';
	dataArray.prisoner_code_list='';
	dataArray.group_name=inputText.value;//get the inputcontent
	dataArray.flag='2';
	break;
	default:
	console.log('do not find locate');
	}
}

/* function search(){
  xhr = new XMLHttpRequest();
  var search = document.getElementById("search").value;

  // 看接口是怎么写的
  var url = "quote1.php?symbol=" + symbol;

  xhr.onreadystatechange = function(){
  // only handle loaded requests
    if (xhr.readyState == 4&& xhr.status == 200){
                        // insert quote into DOM
                        var div = document.createElement("div");
                        var text = document.createTextNode(symbol + ": " + xhr.responseText);
                        div.appendChild(text);
                        document.getElementById("quotes").appendChild(div);
                    }
                    else
                        alert("Error with Ajax call!");
                }
            }
  xhr.open("GET", url, true);
  xhr.send(null);
        }
*/

