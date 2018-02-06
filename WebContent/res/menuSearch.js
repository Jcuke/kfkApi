/**
 * Created by wangjw on 2017/9/25 0025.
 */
//还原zTree的初始数据
function InitialZtree() {
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
}

///根据文本框的关键词输入情况自动匹配树内节点 进行模糊查找
function AutoMatch(txtObj) {
    if (txtObj.value.length > 0) {
        InitialZtree();
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var nodeList = [];
        if(/^[\u3220-\uFA29]+$/.test(txtObj.value)){
            nodeList = zTree.getNodesByParamFuzzy("name", txtObj.value);
        }else{
            nodeList = zTree.getNodesByParamFuzzy("code", txtObj.value);
        }
        $.fn.zTree.init($("#treeDemo"), setting, nodeList);
    } else {
        InitialZtree();
    }
}

//点击某个节点 然后将该节点的名称赋值值文本框
function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    //获得选中的节点
    var nodes = zTree.getSelectedNodes(),
        v = "";
    //根据id排序
    nodes.sort(function compare(a, b) { return a.id - b.id; });
    for (var i = 0, l = nodes.length; i < l; i++) {
        v += nodes[i].name + ",";
    }
    //将选中节点的名称显示在文本框内
    if (v.length > 0) v = v.substring(0, v.length - 1);
    var cityObj = $("#citySel");
    cityObj.attr("value", v);
    return false;
}