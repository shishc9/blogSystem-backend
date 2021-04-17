package icu.shishc.util;

import com.github.pagehelper.PageInfo;
import icu.shishc.entity.Pager;

/**
 * @date:2021-4-17, 16:04
 * @author:ShiShc
 */

/**
 * 处理分页结果
 * 需要 当前页码，当前页大小，总页数，总数据量和总数据量的包装集合
 */
public class PagerUtils {
    public static Pager getPager(PageInfo<?> pageInfo) {
        Pager pager = new Pager();
        pager.setPageNum(pageInfo.getPageNum());
        pager.setPageSize(pageInfo.getPageSize());
        pager.setTotalSize(pageInfo.getTotal());
        pager.setContent(pageInfo.getList());
        pager.setTotalPages(pageInfo.getPages());
        return pager;
    }
}
