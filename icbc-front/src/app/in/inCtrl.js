export default($scope, AuthTool, $state) => {
	'ngInject';
	$scope.logout = () => {
		swal({
			title: "注销登录?",
			text: "自动登录信息将被删除",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "注销",
			cancelButtonText: "取消"
		},
		function(){
			AuthTool.delLoginInfo();
			$state.go("portal");
			swal("已注销!", "自动登录信息已被删除", "success");
		});
	}
};