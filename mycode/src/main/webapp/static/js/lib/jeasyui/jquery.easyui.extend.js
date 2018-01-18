/**
 * 拓展easyui
 * Created by zhuyd on 2017/6/22.
 */
/**
 * 拓展输入框验证规则
 */
$.extend($.fn.validatebox.defaults.rules, {
    range : {
        validator : function (value,param) {
            if(isNaN(value)){
                return false;
            }
            if(!/^-?\d+$/.test(value)){
                return false;
            }

            if( value >= param[0] && value <= param[1]){
                return true;
            }else {
                return false;
            }

        },
        message : '请输入{0}-{1}之间的整数！'
    }

});