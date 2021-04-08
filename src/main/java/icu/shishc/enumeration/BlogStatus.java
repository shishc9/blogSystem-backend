package icu.shishc.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库中 blog.status 字段默认是0， 即博文是公开状态
 * 这里用枚举类与之对应， 只有枚举类和INT之间转换问题在Service与Entity中处理
 */
public enum BlogStatus {
    PUBLIC(0),
    PRIVATE(1);

    private final int key;

    private static Map<Integer, BlogStatus> map = new HashMap<>();
    static {
        for(BlogStatus blogStatus : BlogStatus.values()) {
            map.put(blogStatus.key, blogStatus);
        }
    }


    BlogStatus(int key) {
        this.key = key;
    }

    // 此方法将枚举类型转换成INT
    public int getKey() {
        return this.key;
    }

    // 此方法由INT返回对应枚举类型
    public static BlogStatus ValueOf(int v) { return (BlogStatus) map.get(v); }

}
