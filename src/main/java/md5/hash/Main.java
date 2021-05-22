package md5.hash;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Main {
    public static void main(String[] args) {
        Md5Hash md5 = new Md5Hash(args[0], args[1], Integer.parseInt(args[2]));
        System.out.println(md5);
    }

    /* public static void m(String pwd, String salt, int i) {
        Md5Hash toMd5 = new Md5Hash(pwd, salt, i);
    } */
}
