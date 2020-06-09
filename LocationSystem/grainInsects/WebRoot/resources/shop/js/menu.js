


//主方法，返回生成后的HTML节点
//参数container:菜单所处的HTML节点的Id;
//参数xmlFilePath:要读取的XML文档的路径；
function Menu(container,xmlFilePath)
{
if(container!=null)
   this.Container=container;
if(xmlFilePath!=null)
   this.XmlFilePath=xmlFilePath;
//如果菜单处在的容器id不为空，xml文档路径也为空，则开始生成
if(this.Container!=null&&this.XmlFilePath!=null)
   this.Builder(this.Container,this.XmlFilePath);
}
//全局变量
//子菜单项的默认宽度
Menu.prototype.ItemWidth=120;
//鼠标是否离开下拉菜单
Menu.prototype.MenuRelease=false;
//处于html页面中的容器的id
Menu.prototype.Container=null;
//XML的文件路径
Menu.prototype.XmlFilePath=null;
//生成菜单的主方法
Menu.prototype.Builder=function(container,xmlFilePath)
{
if(container!=null)
   this.Container=container;
if(xmlFilePath!=null)
   this.XmlFilePath=xmlFilePath;
if(this.Container!=null&&this.XmlFilePath!=null)
{
   var topMenu=document.getElementById(this.Container);
   var xml=new XmlHelper(this.XmlFilePath);
   var rootNode=xml.getRootNode();
   var MenuNode=this.BuildMenu(rootNode);
   topMenu.appendChild(MenuNode);
}
}
//返回主菜单,参数依次为：节点名称，层深，序号
Menu.prototype.BuildMenu=function(nodeName,level)
{ 
level=level==null ? 0 : level;
//菜单项的父节点，即菜单面板
var divBox=document.createElement("div");
this.BuildItemPanel(divBox,level);
//
var nodes=nodeName.childNodes;
for(var i=0;i<nodes.length;i++)
{ 
   var node=new Node(nodes[i]);
   if(!node.Visible)continue;
   var subBox=document.createElement("div");  
   //如果节点返回为空，则跳过；否则，生成菜单项
   var newNode=this.BuildItem(node,subBox,level);
   if(newNode==null)continue;
   divBox.appendChild(newNode);
   //如果没有事件，则跳过，否则添加事件
   var eventNode=this.BuildItemEvent(node,newNode,level);
   if(eventNode!=null)
   {
    divBox.replaceChild(eventNode,newNode);
   }
   //如果还有子菜单，递归调用
   if(nodes[i].childNodes.length>0)
    divBox.appendChild(this.BuildMenu(nodes[i],level+1));    
}
return divBox;
}
//生成菜单面板的样式
//divBox:菜单面板
//level:菜单深度，用于判断是第几级菜单
Menu.prototype.BuildItemPanel=function(divBox,level)
{
divBox.setAttribute("type","ItemPanel");
divBox.setAttribute("level",level);
if(level==0)
{
   divBox.className="main";   
}else
{
   divBox.className="sub"; 
   divBox.style.position="absolute";
   divBox.style.display="none";
   divBox.style.zIndex=level+1;
}
divBox.onmouseout=function()
{
   Menu.prototype.MenuRelease=false;
}
divBox.onmouseover=function()
{
   Menu.prototype.MenuRelease=true;
}
}
//生成主菜单项目
//obj,xml节点
//element,HTML元素节点
//level,深度，用于判断是否为主菜单
Menu.prototype.BuildItem=function(obj,element,level)
{
//如果菜单项为空，则跳过
if(obj.Name==null||obj.Name=="")return null;
//如果为主菜单
if(level==0)
{
   if(obj.Type=="line")return null;
   //该菜单项不显示
   if(!obj.Visible)element.style.display="none";
   element.innerHTML=this.BuildMainBox(obj);
   //给HTML元素赋属性值
   element.setAttribute("name",obj.Name);
   element.setAttribute("url",obj.Url);
   element.setAttribute("enable",obj.Enable);
   element.className="mainItem";  
   element.setAttribute("type","Item");
}
//如果为子菜单
if(level>0)
{
   //如果为分隔符
   if(obj.Type=="line")
   {
    var hr=document.createElement("hr");
    hr.className="line"; 
    //hr.innerHTML="&nbsp;";  
    return hr;
   }
   //菜单项的值
   element.innerHTML=this.BuildSubBox(obj);  
   //给HTML元素赋属性值
   element.setAttribute("name",obj.Name);
   element.setAttribute("url",obj.Url);
   element.setAttribute("enable",obj.Enable);
   element.className= "subItem";  
   element.setAttribute("type","Item");
}
if(!obj.Enable)element.style.color="#999999";
return element;
}
//菜单项的宽度,当菜单项的字数增长时，菜单项也相应变宽，该方法求出其最大宽度；
Menu.prototype.setItemWidth=function(element)
{
var maxWidth=0;
//遍历当前节点下的节点，即菜单项；
var childsItem=element.childNodes;
for(var i=0;i<childsItem.length;i++)
{
   if(childsItem[i].getAttribute("type")!="Item")continue;
   var wh=0;
   var valueWh=0;
   //遍历菜单下的各项，求其总宽
   var childs=childsItem[i].childNodes;  
   for(var j=0;j<childs.length;j++)
   {   
    wh+=childs[j].offsetWidth;
   }  
   if(wh>maxWidth)maxWidth=wh;
   if(maxWidth<this.ItemWidth)maxWidth=this.ItemWidth;
   wh=0;
} 
for(var i=0;i<childsItem.length;i++)
{
   childsItem[i].style.width=maxWidth;
}
element.style.width=maxWidth;
}
//菜单面板的实际高度
Menu.prototype.setItemHeight=function(element)
{
var wh=0;
//遍历当前节点下的节点，即菜单项；
var childsItem=element.childNodes;
for(var i=0;i<childsItem.length;i++)
{  
   wh+=childsItem[i].offsetHeight;
}
element.style.height=wh;
}
//生成主菜单项
Menu.prototype.BuildMainBox=function(obj)
{
var name=obj.Name;
if(obj.Type=="link")
{
   if(obj.Enable)
    name="<a href='"+obj.Url+"' target='"+obj.Target+"'>"+obj.Name+"</a>";
}
return name;
}
//生成子菜单项
Menu.prototype.BuildSubBox=function(obj)
{
var isChilds="";
var name=obj.Name;
if(obj.IsChilds)
{
   isChilds=imgPath+"isChilds_1.gif";
  
} 
if(obj.Type=="link")
{
   if(obj.Enable)
   {
    name="<a href='"+obj.Url+"' target='"+obj.Target+"'>"+obj.Name+"</a>";
   }  
}
name=Menu.prototype.subBoxTable(obj.Ico,name,isChilds);
//alert(name);
return name;
}
//生成菜单的完整结构；
Menu.prototype.subBoxTable=function(ico,name,gif)
{
if(ico!="")
{
   ico="<img width='15px' src='"+imgPath+ico+"'/>";
}else
{
   ico="";
} 
if(gif!="")
{
   gif="<img class='gif' src='"+gif+"'/>";
}
ico="<div class='ico'>"+ico+"</div>";
name="<div class='value'>"+name+"</div>";
gif="<div class='isChilds'>"+gif+"</div>"; 
var temp=ico+name+gif;
return temp;
}
//菜单项的事件
Menu.prototype.BuildItemEvent=function(obj,element,level)
{ 
if(obj.Type=="item")
{
   if(obj.Enable)
   {
    try
    {
     element.onclick=eval(obj.Target);
     //alert(obj.Name+":"+element.onclick);
     //element.onclick=function(){alert(obj.Target);};
    }catch(e){}
   }  
} 
element.onmouseover=function()
{    
   if(this.getAttribute("type")=="Item")
   {   
    //清理当前节点的兄弟节点样式
    Menu.prototype.itemStyleOut(this);
    //具体的鼠标事件
    Menu.prototype.Level_over(this);
    //设定自身为选中状态
    Menu.prototype.itemStyleOver(this);   
   }
} 
return element;
}
//主菜单鼠标事件,当鼠标移到主菜单时，显示下拉菜单
Menu.prototype.Level_over=function(obj)
{
//隐藏所有的菜单面板
Menu.prototype.heddenPanel(obj.parentNode);
//该菜单是否启用
var enable=obj.getAttribute("enable");
enable=enable!=null ? enable : true;
if(!enable)
{
   return;
}
//当前节点的下一节点；
var Sibling=obj.nextSibling;
if(Sibling==null)return;
//下个节点如果不是菜单面板，则退出
if(Sibling.getAttribute("type")!="ItemPanel")return; 
//显示子级菜单的面板
Sibling.style.display="block";
//****以下为计算，并确立显示子级菜单面板的位置
//当前节点的在页面上的绝对位置
var left=getLeft(obj);
var top=getTop(obj)+obj.offsetHeight+1;
//浏览器窗口宽度
var winWidth=document.documentElement.clientWidth-20;
var bool=false;
//如果弹出菜单大于窗口宽度
if((left+obj.offsetWidth+Sibling.offsetWidth)>winWidth)bool=true;
//如果不是第一级的下拉
if(Number(Sibling.getAttribute("Level"))>1)
{  
   var top=obj.offsetTop+5;
   //如果子菜单面板太靠右了，则左侧显示
   if(bool)
    left=obj.offsetLeft-obj.offsetWidth+5;
   else
    left=obj.offsetLeft+obj.offsetWidth-5;
}//
Sibling.style.left=left;
Sibling.style.top=top;
Sibling.className="sub";
//设定宽度、高度
this.setItemWidth(Sibling);
this.setItemHeight(Sibling);
}
//隐藏当前菜单的子菜单
Menu.prototype.heddenPanel=function(obj)
{
//当前节点父级的所有子节点
var Childs=obj.childNodes;
for(var i=0;i<Childs.length;i++)
{
   //必须是菜单面板
   if(Childs[i].getAttribute("type")=="ItemPanel")
   { 
    //如果还有下级菜单
    if(Childs[i].childNodes.length>0)
    {
     Menu.prototype.heddenPanel(Childs[i]);
    }
    //不能是主菜单面板
    if(Childs[i].getAttribute("level")!="0")
    {
     Childs[i].style.display="none";
     Childs[i].className="";    
    }
    Menu.prototype.substyleout(Childs[i]);     
   }
}
}
Menu.prototype.itemStyleOut=function(obj)
{
var parent=obj.parentNode;
Menu.prototype.substyleout(parent);
}
//将某个节点下的子节样式还原
Menu.prototype.substyleout=function(obj)
{
//当前节点不是菜单面板，则退出
if(obj.getAttribute("type")!="ItemPanel")return;
//菜单面板层深
var level=Number(obj.getAttribute("level"));
var items=obj.childNodes;
for(var i=0;i<items.length;i++)
{
   var type=items[i].getAttribute("type");
   if(type=="Item")
   {
    var css=level==0 ? "mainItem" : "subItem";   
    items[i].className=css;
   }
}
}
//将自己设为选中状态
Menu.prototype.itemStyleOver=function(obj)
{
//当前html标签的上级节点，如果不是菜单面板则退出
var parent=obj.parentNode;
if(parent.getAttribute("type")!="ItemPanel")return;
//菜单项层深
var level=Number(parent.getAttribute("level"));
var type=obj.getAttribute("type");
if(type=="Item")
{
   var css="";
   css= level==0 ? "mainItem over" : "subItem over"; 
   obj.className=css;
}
}
//菜单滑出效果
Menu.prototype.panelGlide=function(obj)
{
}
//++++++++++++++++
//子菜单全部隐藏
Menu.prototype.allHidden=function()
{
if(this.Container!=null&&this.XmlFilePath!=null)
{  
   var topMenu=document.getElementById(this.Container);
   Menu.prototype.heddenPanel(topMenu);
}
}
//还原菜单
Menu.prototype.revertMenu=function()
{ 
if(this.MenuRelease==false)
   this.allHidden();
}

