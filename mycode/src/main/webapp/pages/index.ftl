<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎来到冬冬的世界！</title>
<#include "common/common_js.ftl" />
</head>
<body class="easyui-layout">
<div id="northDiv"  data-options="region:'north',title:'北',noheader:true,border:false,split:false,collapsible:false" style="height:60px;background-color: #00bbee;">
    <span style="font-size: 16px;font-family:Verdana,黑体,微软雅黑;float: right;padding-right: 120px;padding-top: 30px;">欢迎<strong>${loginRealname!'匿名'}</strong> <a href="/logout">安全退出</a></span>
</div>
<div data-options="region:'south',title:'南',split:false,collapsible:false" style="height:100px;">

</div>
<div data-options="region:'east',iconCls:'icon-reload',title:'东',split:true,collapsible:true" style="width:100px;">

</div>
<div data-options="region:'west',title:'西',split:false" style="width:130px;">
    <!-- 导航 -->
    <ul id="treeNav"></ul>
</div>
<div  data-options="region:'center',fit:false,noheader:true,border:false,title:'中间'" >
    <!--选项卡-->
    <div id="tab"></div>
</div>
<script type="text/javascript" src="${base!}/static/js/admin/index.js"></script>
</body>

<script>
    $(function(){
        window.index.init();
    });
</script>
</html>