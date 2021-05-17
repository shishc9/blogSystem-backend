package icu.shishc.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShiShc
 * @PackageName:icu.shishc.enumeration
 * @Date:2021/3/15, 9:20
 */

public enum UserIdentity {
    BLOGGER(0),
    ADMIN(1);

    private final int key;

    private static Map<Integer, UserIdentity> map = new HashMap<>();
    static {
        for(UserIdentity userIdentity : UserIdentity.values()) {
            map.put(userIdentity.key, userIdentity);
        }
    }

    UserIdentity(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static UserIdentity ValueOf(int v) { return (UserIdentity) map.get(v);}
}
