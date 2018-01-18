$(function() {

	// 登录窗口
	$('#loginWindow').dialog({
		title : '登录后台',
		width : 320,
		height : 220,
		modal : true,
		draggable : false,
		resizable : false,
		closable : false,
		iconCls : 'icon-login',
		buttons : '#loginBtnDiv'
	});

	// 管理员账号验证
	$('#username').validatebox({
		required : true,
		missingMessage : '请输入管理员账号',
		invalidMessage : '管理员账号不得为空',
	});

	// 加载时判断验证
	// if (!$('#username').validatebox('isValid')) {
	// $('#username').focus();
	// } else if (!$('#password').validatebox('isValid')) {
	// $('#password').focus();
	// }

	// 管理员密码验证
	$('#password').validatebox({
		required : true,
		validType : 'length[6,20]',
		missingMessage : '管理员密码不得为空!',
		invalidMessage : '请输入6-20位的管理员6密码!'
	});

	/**
	 * 绑定登录
	 */
	$("#loginBtn").on("click", function() {
		if (!$('#username').validatebox('isValid')) {
			$('#username').focus();
		} else if (!$('#password').validatebox('isValid')) {
			$('#password').focus();
		} else {
			$("#login_check_form").submit();
		}
	});
});