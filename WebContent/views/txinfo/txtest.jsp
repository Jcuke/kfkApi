<%@ page language="java" contentType="text/html; charset=GBK"
         pageEncoding="GBK"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String	clsName=request.getParameter("cls");
    String	method=request.getParameter("method");
    //java.util.Map dftVals=(java.util.Map)session.getAttribute("defaultValues");
    String ctx=request.getContextPath();
    Map<String,String> dfv=(Map<String,String>)session.getAttribute("defaultValues");
    if(dfv==null) dfv=new HashMap<String,String>();

    dfv.put("appcode", "102");
    dfv.put("appversion", "1.0.0");
    dfv.put("ostype", "Windows");
    dfv.put("osversion", "Windows");
    dfv.put("phonebrand", "xiaomi");
    dfv.put("phonemodel", "");
    dfv.put("imei", "123400000000");
    dfv.put("browser", "50");
    dfv.put("browserversion", "50");
    dfv.put("extinfo", "no");
    dfv.put("token", "1");
    dfv.put("ip", "172.23.2.62");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="<%=ctx%>/res/jquery.js" type="text/javascript"></script>
    <script src="<%=ctx%>/res/jquery.json.js" type="text/javascript"></script>
    <script src="<%=ctx%>/res/jquery.fix.clone.js" type="text/javascript"></script>
    <script src="<%=ctx%>/res/formatjson.js" type="text/javascript"></script>


    <title>交易测试</title>
    <style>
        h3{
            font-size:13px;
            color:white;
            margin:0;
            padding-left:5px;
            line-height:24px;
            background:#333;
        }
        .hh{
            padding-left:3px;
            width:100%;
            background:#ccc;
        }
    </style>
</head>
<body style="margin:0">
<h3><%=method%>测试
</h3>
<form method="post" action="txcall.do">
    <table cellpadding=0 cellspacing=0>
        <tr>
            <td valign="top"  >
                <div class="hh">输入参数HEAD</div>
                <table>
                    <tr>
                        <td width="400">
                            <table>
                                <%
                                    java.util.List<String> heads = (java.util.List<String>) request.getAttribute("heads");
                                    for (int i = 0; i < heads.size(); i++) {
                                        String s = heads.get(i);
                                        String val = dfv.get(s);
                                        if (val == null)
                                            val = "";
                                        //数组
                                        if (s.startsWith("[]"))
                                            continue;

                                %>
                                <tr>
                                    <td><%=s%></td>
                                    <td><input type="textfield" name="<%=s%>" value="<%=val%>" size=40   id="head" />
                                </tr>
                                <%
                                    }
                                %>
                            </table>

                        </td>
                    </tr>
                </table>

            </td>
            <td valign="top" width="900" rowspan="3">
                <div class="hh">返回参数</div> <span></span> <textarea id="result"
                                                                   style="width:100%;height:300px;"></textarea>
            </td>
        </tr>
        <tr>
            <td valign="top"  >
                <div class="hh">输入参数BODY</div>
                <table>
                    <tr>
                        <td width="400">
                            <table>
                                <%
                                    java.util.List<String> inputs = (java.util.List<String>) request.getAttribute("inputs");
                                    for (int i = 0; i < inputs.size(); i++) {
                                        String s = inputs.get(i);
                                        String val = dfv.get(s);
                                        if (val == null)
                                            val = "";
                                        //数组
                                        if (s.startsWith("[]"))
                                            continue;

                                %>
                                <tr>
                                    <td><%=s%></td>
                                    <td><input type="textfield" name="<%=s%>" value="<%=val%>" size=40    id="body"  />
                                </tr>
                                <%
                                    }
                                %>
                                <tr>
                                    <td colspan="2" align="center"><input type="submit"
                                                                          value="提交" /></td>
                                </tr>
                            </table>

                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
<script>
    function successFn(data, status, jqxhr)
    {
        $("#result").val(formatJson(data));
    }

    function errorFn(xhr,status,error)
    {
        if(xhr!=null)
            alert("error:"+xhr.responseText);
    }

    function	parseName(name){
        var ppos=name.indexOf(".");
        var bpos=name.indexOf("[");
        var apos=name.indexOf("]");
        var n1=name.substr(0,bpos);
        var n2=name.substr(ppos+1);
        var s=name.substr(bpos+1,apos-bpos-1);

        var idx=parseInt(s);
        return {n1:n1,n2:n2,idx:idx};
    }

    $("input[type=submit]").on("click",function(){
        var postData={};
        var postData2={};
        var head={};
        var body={};
        $("input[type=textfield]").each(function(){
            var name=$(this).attr("name");
            var id = $(this).attr("id");
            var val=$(this).val();
            if(val!=null){
                if(name.indexOf("[")<0){
                    if(id=="head"){
                        head[name]=val;
                    }else{
                        body[name]=val;
                    }
                }
                else{

                }
            }
        });

        postData["head"]=head;
        postData["body"]=body;
        postData2["h"]=head;
        postData2["p"]=body;

        try{
            $.ajax({
                type: 'post',
                url: "<%=ctx%>/request.do?v=<%=dfv.get("appversion")%>&r=<%=clsName%>-<%=method%>&p=" + JSON.stringify(postData2),
                data: {},
                cache: false,
                dataType:'json',
                contentType:'application/json;charset=UTF-8',
                success: successFn,
                error: errorFn
            });
        }catch(e){
            alert(e);
        }
        return false;
    });
</script>

<script>
    function	doAdd(name){
        var tr=$(".subfield_header")[0];
        var fields=[];
        $(".subfield_header_f",$(tr)).each(function(){
            fields.push($(this).html());
        });
        var table=$(".subfield_table");
        var rows=$("tr",table);
        var cnt=rows.length-1;
        var html="";
        html+="<tr>";
        html+="<td>";
        html+="<input type='checkbox'>";
        html+="</td>";
        for(var i=0;i<fields.length;i++){
            html+="<td>";
            html+="<input type='textfield' size='10' name='"+name+"["+cnt+"]."+fields[i]+"'>";
            html+="</td>";
        }
        html+="</tr>";
        $(".subfield_table").append(html);
    }

    var reg = /\[[^\]]*\]/g;


    function	doRm(name){
        var table=$(".subfield_table");
        var rows=$("tr",table);
        for(var i=1;i<rows.length;i++){
            var row=$(rows[i]);
            var checkbox=$("input[type=checkbox]",row);
            if(checkbox.is(':checked')){
                row.remove();
            }
        }

        var table=$(".subfield_table");
        var rows=$("tr",table);
        var temp="";
        for(var i=1;i<rows.length;i++){
            var row=$(rows[i]);
            var idx=i-1;
            $("input",row).each(function(){
                var input=$(this);
                var name=input.attr("name");
                if(name!=null)
                {
                    //reg = /\[em(.+?)\]/g
                    var nidx="["+idx+"]";
                    ss =  name.replace(reg,nidx);
                    input.attr("name",ss);
                    temp+=ss;
                    temp+=";";
                }
            });
            temp+="\n";
        }
    }

    $(".subfield_btn_add").each(function(){
        var $el=$(this);
        $el.on("click",function(){
            var name=$el.attr("name");
            doAdd(name.substr(2));
        });
    });

    $(".subfield_btn_rm").each(function(){
        var $el=$(this);
        $el.on("click",function(){
            var name=$el.attr("name");
            doRm(name.substr(2));
        });
    });


</script>