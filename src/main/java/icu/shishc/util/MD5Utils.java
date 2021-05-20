package icu.shishc.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @date: 2021-5-20, 17:36
 * @author: ShiShc
 */
public class MD5Utils {

    /**
     * Shiro的md5加密
     * @param pwd 密码
     * @param salt 盐值
     * @param i 加密次数
     * @return string
     */
    public static String toMd5(String pwd, String salt, int i) {
        Md5Hash toMd5 = new Md5Hash(pwd, salt, i);
        return toMd5.toString();
    }

}
