package icu.shishc.service.serviceImpl;

import icu.shishc.entity.Blog;
import icu.shishc.entity.User;
import icu.shishc.exception.CustomException;
import icu.shishc.mapper.BlogMapper;
import icu.shishc.mapper.CollectionMapper;
import icu.shishc.service.BlogService;
import icu.shishc.service.CollectionService;
import icu.shishc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date: 2021-5-26, 10:26
 * @author: ShiShc
 */

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    CollectionMapper collectionMapper;
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;

    @Override
    public Integer deleteBlogCollection(Long bid) {
        return collectionMapper.deleteBlogCollection(bid);
    }

    @Override
    public int addCollection(Long uid, Long bid) throws CustomException {
        User user = userService.getUserById(uid);
        Blog blog = blogService.getBlogByBID(bid);
        userService.updateUserNum(uid, user.getPostCount(), user.getLikeCount(), user.getCollectionCount() + 1, user.getFollowing(), user.getFollowed());
        blogService.updateBlogNum(bid, blog.getCommentNum(), blog.getCollectionNum() + 1);
        return collectionMapper.userAddBlogCollection(uid, bid);
    }

    @Override
    public int cancelCollection(Long uid, Long bid) throws CustomException {
        User user = userService.getUserById(uid);
        Blog blog = blogService.getBlogByBID(bid);
        userService.updateUserNum(uid, user.getPostCount(), user.getLikeCount(), user.getCollectionCount() - 1, user.getFollowing(), user.getFollowed());
        blogService.updateBlogNum(bid, blog.getCommentNum(), blog.getCollectionNum() - 1);
        return collectionMapper.userCancelBlogCollection(uid, bid);
    }

    @Override
    public List<Blog> collectionList(Long uid) {
        List<Long> list = collectionMapper.getCollections(uid);
        return blogService.getBlogByList(list);
    }

    @Override
    public int collectionOrNot(Long uid, Long bid) {
        return collectionMapper.collectionOrNot(uid, bid);
    }
}
