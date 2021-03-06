package icu.shishc.entity;

import java.util.Date;

/**
 * @date: 2021-5-22, 11:15
 * @author: ShiShc
 */
public class Attachment {
    private Long id;
    // 创建时间
    private Date createTime;
    // 附件名
    private String attachName;
    // 附件后缀
    private String attachSuffix;
    // 附件大小
    private Double attachSize;
    // 附件存储地址
    private String attachLocation;
    // 附件来源, 0:上传, 1:外部链接
    private Integer attachOrigin = 0;
    // 附件所属者id
    private Long userId;
}
