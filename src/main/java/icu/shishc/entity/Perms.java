package icu.shishc.entity;

import icu.shishc.enumeration.UserIdentity;
import lombok.Data;

/**
 * @date: 2021-5-19, 17:04
 * @author: ShiShc
 * @DESC: 权限表
 */
@Data
public class Perms {
    UserIdentity userIdentity;
    String entity;
    String perm;
}
