/**
* 跟登陆状态、权限有关的代码
* --Mondooo
*/
export default ($localStorage, $sessionStorage) => {
  'ngInject';
  
    var TOKEN_KEY  = 'x-auth-token',
        LOGIN_USER = 'login-user',
        USERNAME   = 'username',
        PASSWORD   = 'password',
        AUTOLOGIN     = 'ioc-kpi-autologin';

    return {
        // 保存登陆用户的信息
        saveLoginInfo: (login_user, token_key) => {
            $sessionStorage[TOKEN_KEY] = token_key;
            $sessionStorage[LOGIN_USER] = login_user;
        },

        // 获取登陆用户的信息
        getLoginInfo: () => {
            return $sessionStorage[LOGIN_USER];
        },

        // 更新登陆用户的信息
        updateLoginInfo: (updated_user) => {
            $sessionStorage[LOGIN_USER] = updated_user;
        },

        // 删除登陆用户的信息
        delLoginInfo: () => {
            delete $sessionStorage[LOGIN_USER];
            delete $sessionStorage[TOKEN_KEY];

            delete $localStorage[USERNAME];
            delete $localStorage[PASSWORD];

            $localStorage[AUTOLOGIN] = false;
        },

        // 检查用户是否处于登陆状态
        checkLoginState: () => {
            return $sessionStorage[LOGIN_USER] && $sessionStorage[TOKEN_KEY];
        },

        // 保存自动登录用户的信息
        saveAutoLoginInfo: (username, password) => {
            $localStorage[USERNAME] = username;
            $localStorage[PASSWORD] = password;
            $localStorage[AUTOLOGIN] = true;
        }
    }
}
