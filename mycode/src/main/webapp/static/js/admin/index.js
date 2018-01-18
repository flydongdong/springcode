(function($, document, window) {
    var pub = {};

    window.index = pub;

    pub.init = function () {
        initView();
    }

    //初始化页面
    function initView() {
        //初始化左侧树
        $("#treeNav").tree({
            "fit" : true,
            "url": "/static/json/index_tree.json",
            "method": 'get',
            "onClick": function (node) {
                // alert(node.id);
                // console.log($("#tab").tabs("exists",1));
                if(!$("#treeNav").tree("isLeaf",node.target)){
                    return ;
                }
                if(!$("#tab").tabs("exists",node.text)){
                    $("#tab").tabs('add',{
                        "closable" : true,
                        "title" : node.text,
                        // "index" : node.id,
                        "href" : node.url
                    });
                }else{
                    $('#tab').tabs("select",node.text);
                    var tab = $("#tab").tabs("getTab",node.text);
                    $("#tab").tabs("update",{
                        "tab" : tab,
                        "options" : {
                            "closable" : true,
                            "title" : node.text,
                            "href" : node.url
                        }
                    });
                }

            }
        })
        //初始化选项卡
        $("#tab").tabs({
            "fit" : true
        });
    }


})(jQuery, document, window);