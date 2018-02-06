<%@ page pageEncoding="UTF-8"%>
<%
    String ctx=request.getContextPath();
%>
<!DOCTYPE html>
<html style="height:100%">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="<%=ctx%>/res/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
    <script src="<%=ctx%>/res/jquery.js" type="text/javascript"></script>
    <script src="<%=ctx%>/res/jquery.json.js" type="text/javascript"></script>
    <script src="<%=ctx%>/res/jquery.fix.clone.js" type="text/javascript"></script>
    <script src="<%=ctx%>/res/jquery.ztree.all-3.5.js" type="text/javascript"></script>
    <script src="<%=ctx%>/res/menuSearch.js" type="text/javascript"></script>

</head>
<style>
    ul{
        -moz-user-select:none;
        list-style: none;
        padding:0px;
        margin:0px;

    }
    ul li{
        line-height:24px;
        padding-left:2px;
    }
    ul a{
        text-decoration: none;
        text-decoration: none;
        font-weight: normal;
        color: #333333;
    }
    h3{
        font-size:13px;
        color:white;
        margin:0;
        padding-left:5px;
        line-height:24px;
        background:#333;
    }
</style>
<body style="margin:0;border-right:0px solid #333;overflow:scroll;height:100%">
<h3 style="">${menu}</h3>
<input type="hidden" id="txlist" value="${txlist}"/>
<div class="content_wrap">
    <div class="zTreeDemoBackground left">
        <ul class="list">
            <li class="title">&nbsp;&nbsp;
                <input id="citySel" type="text" value="" onkeyup="AutoMatch(this)" style="width: 180px;" />
                &nbsp;<button style="height: 20px;width:50px;" onchange="if(!$('#citySel').val('')){InitialZtree();}" onclick="$('#citySel').val('');InitialZtree();">过滤</button>
            </li>
        </ul>
    </div>
</div>
<div id="menuContent" class="menuContent" style="displayx: none; position: absolute;">
    <ul id="treeDemo" class="ztree" style="margin-top: 0; width: 110px;">
    </ul>
</div>
</body>
</html>
<script>
    var setting = {
        showLine: true,
        data: {
            simpleData: {
                enable: true,
                open: true
            },
            key: {
                title: "code"
            }
        },
        callback: {
            //zTree节点的点击事件
            onClick: onClick
        }
    };
    var zNodes = $(txlist).val();
    zNodes = eval(zNodes);

    $(document).ready(function(){
        InitialZtree();
    });
</script>