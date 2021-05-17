package icu.shishc.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @date:2021-4-17, 10:09
 * @author:ShiShc
 */

@ApiModel("分页实体")
@Data
public class Pager {
    /**
     * 分页起始页
     */
    private int pageNum;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 返回的记录集合
     */
    private List<?> content;
    /**
     * 总记录条数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private long totalPages;
}
