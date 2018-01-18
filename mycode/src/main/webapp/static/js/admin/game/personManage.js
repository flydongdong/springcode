(function($, document, window) {
    var pub = {};

    window.personManage = pub;

    pub.init = function () {
        initView();
        bindEvent();
    }

    //绑定事件
    function bindEvent() {
        $("#addBtn").on("click",alertAddWindow);//弹出新增窗口
    }

    //弹出新增窗口
    function alertAddWindow() {
        $("#addWindow").dialog({
            title : "新增普通人物",
            modal : true,
            width : 400,
            height : 300,
            href : "/admin/game/person/editBasePerson",
            loadingMessage : "冬冬正在努力加载中..."
        });
        // $("#addWindow").center();
    }


    //初始化页面
    function initView() {
        $("#tb_person").datagrid({
            "url" : "/admin/game/person/queryBasePersonJson",
            "fitColumns" : true,
            "singleSelect" : true ,//只允许选中一行
            "columns" : [[
                {field : 'id',title : 'id',width : 50},
                {field : 'name',title : '姓名',width : 100},
                {field : 'age',title : '年龄',width : 50,sortable : true},
                {field : 'maxPower',title : '最大力量',width : 100},
                {field : 'maxIt',title : '最大智力',width : 100},
                {field : 'maxHP',title : '最大HP',width : 100},
                {field : 'maxMP',title : '最大MP',width : 100},
                {field : 'exp',title : '经验',width : 100},{
                    field : '_operate',
                    title : '操作',
                    width : 80,
                    halign : 'center',
                    formatter : function(value,row,index){
                        if(row.id){
                            return "<a href='javascript:;' class='easyui-linkbutton' data-options='iconCls:" + '"icon-edit"' + "' onclick='window.personManage.editBasePerson(" +row.id +")' >修改</a>";
                        }else{
                            return "";
                        }
                    }
                }
            ]],
            "loadMsg" : "<span style='color: #d00d09;font-size: 4+;'>冬冬正在努力加载中...</span>",
            "pagination" : true,
            "pagePosition" : 'both',//定义分页工具栏的位置。可用的值有：'top','bottom','both'。
            "pageNumber" : 1,
            "pageSize" : 10,
            "pageList" : [5,10,20,50],
            "queryParams" : { //测试数据用
                "testQueryParams" : "冬冬"
            },
            //"sortName" : 'age,maxPower',//定义哪些列可以进行排序。注意：并不是数组类型，还是string
            "sortOrder" : "asc", //定义列的排序顺序，只能是'asc'或'desc'。
            "onLoadSuccess" : function () {
                $.parser.parse();//渲染样式
            },
            "rowStyler":function(){
                return 'height: 36px';
            }
            // "beforePageText" : "跳到",
            // "afterPageText" : "/{pages}",
            // "displayMsg" : "显示 {from} 至 {to} 的记录，总共 {total} 条记录！"
        });
    }

    /**
     * 编辑数据
     * @param id
     */
    pub.editBasePerson = function(id) {
        if(isNaN(id)){
            return;
        }
        $("#addWindow").dialog({
            title : "新增普通人物",
            modal : true,
            width : 400,
            height : 300,
            href : "/admin/game/person/editBasePerson",
            loadingMessage : "冬冬正在努力加载中...",
            queryParams:{
                "id" : id
            }
        });
        $("#addWindow").center();
    }


})(jQuery, document, window);