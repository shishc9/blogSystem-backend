package icu.shishc.service;


import icu.shishc.Exception.CustomException;
import icu.shishc.entity.User;

public interface LoginService {

    /**
     * 登录表单提交
     * @param username
     * @param password
     * @return
     */
    boolean authLogin(String username, String password) throws CustomException;


    /**
     * 查询当前登录用户权限信息
     * @return
     */
    String getInfo();


    /**
     * 退出登录
     * @return
     */
    boolean logout() throws CustomException;


    /**
     * 用户注册
     * @param user
     * @return
     * @throws CustomException
     */
    User register(User user) throws CustomException;

}
