package icu.shishc.entity;

import lombok.Data;

/**
 * @date:2021-4-14, 21:24
 * @author:ShiShc
 */

@Data
public class Permission {
    private Long pid;
    private String permissionName;
    private String permissionUrl;
    private String method;
    private String description;
}
