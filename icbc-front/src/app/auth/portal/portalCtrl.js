/**
* 登陆控制器
* --Mondooo
*/
export default ($scope, $rootScope, $localStorage, $timeout, $state, $q, qService, accountRes, AuthTool, ToasterTool) => {
    'ngInject';

    let TOKEN_KEY  = 'x-auth-token',
        USERNAME   = 'username',
        PASSWORD   = 'password',
        AUTOLOGIN  = 'ioc-kpi-autologin';

    // 密码加密函数
    const encryptPassword = (password, username, sbin) => {
        let code = sbin === undefined ? '1234' : sbin;
        console.log(md5(password));
        return md5(md5(md5(password) + username) + code.toUpperCase());
    }
    // 自动登录
    const autoLogin = () => {
        if ($localStorage[AUTOLOGIN] && $localStorage[USERNAME] && $localStorage[PASSWORD]) {
            let encryptedPsw = encryptPassword($localStorage[PASSWORD], $localStorage[USERNAME]);
            let info = {
                'X-Username': $localStorage[USERNAME],
                'X-Password': encryptedPsw
            };
            qService.httpPost(accountRes.account, {}, info, {}).then((data) => {
                if (data.success) {
                    console.log("自动登录, 用户信息验证成功");
                    // 存储登陆用户data和token
                    AuthTool.saveLoginInfo(data.data, data.headers[TOKEN_KEY]);
                    ToasterTool.success("自动登陆成功，欢迎回来！");
                    $state.go('in.home');
                } else {
                    ToasterTool.error(data.message);
                }
            }, (err) => {
                ToasterTool.error("出错了, 请重试");
            });
        }
    }();

    $scope.isAutoLogin = false;
    $scope.login = () => {
    	if (isNull($scope.loginAccount)) {
    		ToasterTool.warning("账号不能为空！");
    		$q((resolve, reject) => {
        		$timeout(() => {
        			$scope.errMessage = "";
        			resolve();
        		}, 1500);
            });
    		return;
    	};
        let encryptedPsw = encryptPassword($scope.loginPassword, $scope.loginAccount);
    	let info = {
    		'X-Username': $scope.loginAccount,
            'X-Password': encryptedPsw
    	};
    	qService.httpPost(accountRes.account, {}, info, {}).then((data) => {
    		if (data.success) {
                // 如果用户选择自动登录, 则将其用户名和密码存到本地
                if ($scope.isAutoLogin) {
                    AuthTool.saveAutoLoginInfo($scope.loginAccount, $scope.loginPassword);
                    console.log("用户信息已保存, 启用自动登录");
                }
                // 存储登陆用户data和token
                AuthTool.saveLoginInfo(data.data, data.headers[TOKEN_KEY]);
                ToasterTool.success("登陆成功，欢迎回来！");
    			$state.go('in.home');
    		} else {
                ToasterTool.error(data.message);
            }
    	}, (err) => {
            ToasterTool.error("出错了, 请重试");
    	});
    };

    let isNull = (value) => {
    	return typeof(value) == undefined || value == null;
    };
  
};
