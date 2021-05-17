package icu.shishc.service;


import icu.shishc.Exception.CustomException;
import icu.shishc.entity.User;

public interface LoginService {

    /**
     * 登录表单提交
     * @param username 用户名
     * @param password 密码
     * @return 登陆是否成功
     * @throws CustomException .
     */
    boolean authLogin(String username, String password) throws CustomException;

//
//    /**
//     * 查询当前登录用户权限信息
//     * @return
//     */
//    String getInfo();


    /**
     * 退出登录
     * @return 登出状态
     * @throws CustomException .
     */
    boolean logout() throws CustomException;


    /**
     * 用户注册
     * @param user 用户实体
     * @return 注册成功的实体
     * @throws CustomException .
     */
    User register(User user) throws CustomException;

}
