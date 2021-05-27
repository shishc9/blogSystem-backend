package icu.shishc.service;

import icu.shishc.exception.CustomException;
import icu.shishc.entity.Blog;
import java.util.List;

/**
 * @author ShiShc
 */
public interface BlogService{


    /**
     * 博客广场，默认博客页面的展示内容
     * @return list
     */
    List<Blog> getBlogSquare();


    /**
     * 返回某个用户的所有博客
     * @param userId 查询条件：userid
     * @return list
     * @throws CustomException .
     */
    List<Blog> getBlogByUserId(Long userId) throws CustomException;


    /**
     * 根据标题查找博客，规定 整个系统中的博客名称必须唯一
     * @param title 标题
     * @return blog实体
     * @throws CustomException .
     */
    Blog getBlogByTitle(String title) throws CustomException;


    List<Blog> getBlogByList(List<Long> list);


    /**
     * 根据博客的bid查找博客
     * @param bid 博客id
     * @return blog实体
     * @throws CustomException .
     */
    Blog getBlogByBID(Long bid) throws CustomException;


    /**
     * 某个用户新增一篇博客
     * @param blog 博客实体
     * @return 插入成功的博客实体
     * @throws CustomException .
     */
    Blog insert(Blog blog) throws CustomException;


    /**
     * 删除一篇博客
     * @param id 删除博客的博客id
     * @return 1 or 0
     * @throws CustomException .
     */
    Integer delete(Long id) throws CustomException;


    /**
     * 某个用户更新一篇自己博客
     * @param blog 博客实体
     * @return 更新后的博客实体
     * @throws CustomException .
     */
    Blog update(Blog blog) throws CustomException;


    /**
     * 某一用户的上一篇博客
     * @param bid
     * @param userId
     * @return
     * @throws CustomException
     */
    Blog getPrevious(Long bid, Long userId) throws CustomException;


    /**
     * 某一用户的下一篇博客
     * @param bid
     * @param userId
     * @return
     * @throws CustomException
     */
    Blog getNext(Long bid, Long userId) throws CustomException;


    /**
     * 博客实体的完整性检查
     * @param blog 博客实体
     * @return T F
     * @throws CustomException .
     */
    Boolean checkBlog(Blog blog) throws CustomException;


    /**
     * bid检查方法
     * @return
     */
    Boolean checkBid(Long bid);



    void updateBlogNum(Long bid, Integer commentNum, Integer collectionNum);

}
