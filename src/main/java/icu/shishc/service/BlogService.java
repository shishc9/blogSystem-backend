package icu.shishc.service;

import icu.shishc.Exception.CustomException;
import icu.shishc.entity.Blog;
import icu.shishc.enumeration.BlogStatus;
import java.util.List;

public interface BlogService{


    /**
     * 博客广场，默认博客页面的展示内容
     * @return list
     * @throws CustomException .
     */
    List<Blog> getBlogSquare() throws CustomException;


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


    /**
     * 根据博客的bid查找博客
     * @param bid 博客id
     * @return blog实体
     * @throws CustomException .
     */
    Blog getBlogByBID(Long bid) throws CustomException;


    /**
     * 根据状态查找某个用户的博客
     * @param blogStatus 博客状态
     * @param userId 用户id
     * @return list
     * @throws CustomException .
     */
    List<Blog> getBlogByStatus(BlogStatus blogStatus, Long userId) throws CustomException;


//    /**
//     * 点赞
//     * @param bid
//     * @return
//     * @throws CustomException
//     */
//    Integer addLike(Long bid) throws CustomException;
//
//
//    /**
//     * 取消点赞
//     * @param bid
//     * @return
//     * @throws CustomException
//     */
//    Integer cancelLike(Long bid) throws CustomException;


//    /**
//     * 统计所有点赞
//     * @return
//     */
//    Integer getAllLike() throws CustomException;
//
//
//    /**
//     * 统计所有阅读数
//     * @return
//     */
//    Integer getAllReadNum() throws CustomException;


    /**
     * 某个用户新增一篇博客
     * @param blog 博客实体
     * @param userId 用户ID
     * @return 插入成功的博客实体
     * @throws CustomException .
     */
    Blog insert(Blog blog, Long userId) throws CustomException;


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
     * @param userId 用户ID
     * @return 更新后的博客实体
     * @throws CustomException .
     */
    Blog update(Blog blog, Long userId) throws CustomException;


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


}
