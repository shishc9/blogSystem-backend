package icu.shishc.service;

import icu.shishc.Exception.CustomException;
import icu.shishc.entity.Blog;
import icu.shishc.enumeration.BlogStatus;

import java.util.List;


public interface BlogService{


    /**
     * 返回所有博文
     * @return
     */
    List<Blog> getAllBlog() throws CustomException;


    /**
     * 根据标题查找博客
     * @param title
     * @return
     */
    Blog getBlogByTitle(String title) throws CustomException;


    /**
     * 根据BID查找博客
     * @param bid
     * @return
     */
    Blog getBlogByBID(Long bid) throws CustomException;


    /**
     * 根据状态查找博客
     * @param blogStatus
     * @return
     */
    List<Blog> getBlogByStatus(BlogStatus blogStatus) throws CustomException;


    /**
     * 点赞
     * @param bid
     * @return
     * @throws CustomException
     */
    Integer addLike(Long bid) throws CustomException;


    /**
     * 取消点赞
     * @param bid
     * @return
     * @throws CustomException
     */
    Integer cancelLike(Long bid) throws CustomException;


    /**
     * 统计所有点赞
     * @return
     */
    Integer getAllLike() throws CustomException;


    /**
     * 统计所有阅读数
     * @return
     */
    Integer getAllReadNum() throws CustomException;


    /**
     * 新增一篇博客
     * @param blog
     * @return
     */
    Blog insert(Blog blog) throws CustomException;


    /**
     * 删除一篇博客
     * @param id
     * @return
     */
    Integer delete(Long id) throws CustomException;


    /**
     * 更新一篇博客
     * @param blog
     * @return
     */
    Blog update(Blog blog) throws CustomException;


    /**
     * 获取上一篇博客
     * @param bid
     * @return
     * @throws CustomException
     */
    Blog getPrevious(Long bid) throws CustomException;


    /**
     * 获取下一篇博客
     * @param bid
     * @return
     * @throws CustomException
     */
    Blog getNext(Long bid) throws CustomException;


    /**
     * 博客完整性检查
     * @param blog
     * @return
     * @throws CustomException
     */
    Boolean checkBlog(Blog blog) throws CustomException;


}
