package icu.shishc.entity;

import lombok.Data;

import java.util.List;

/**
 * @date:2021-4-17, 10:09
 * @author:ShiShc
 */


@Data
public class Pager<T> {
    /**
     * 分页起始页
     */
    private int page;
    /**
     * 每页记录数
     */
    private int size;
    /**
     * 返回的记录集合
     */
    private List<T> rows;
    /**
     * 总记录条数
     */
    private long total;
}
