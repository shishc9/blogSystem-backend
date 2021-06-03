package icu.shishc.service.serviceImpl;

import icu.shishc.entity.Blog;
import icu.shishc.exception.CustomException;
import icu.shishc.entity.Perms;
import icu.shishc.entity.User;
import icu.shishc.enumeration.UserIdentity;
import icu.shishc.mapper.*;
import icu.shishc.service.BlogService;
import icu.shishc.service.CommentService;
import icu.shishc.service.LikeService;
import icu.shishc.service.UserService;
import icu.shishc.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName:icu.shishc.service.serviceImpl
 * @Date:2021/3/15, 9:40
 * @Auther:ShiShc
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final static String USER_DEFAULT_AVATAR = "https://i.loli.net/2021/05/25/vowpYGb6ND1I3ET.jpg";

    @Autowired
    UserMapper userMapper;
    @Autowired
    PermsMapper permsMapper;
    @Autowired
    BlogService blogService;
    @Autowired
    CollectionMapper collectionMapper;
    @Autowired
    AttentionMapper attentionMapper;
    @Autowired
    LikeService likeService;
    @Autowired
    CommentService commentService;


    @Override
    public List<User> getAllUsers() {
        List<User> list = userMapper.getAllUsers();
        log.info("【UserService】getAllUsers");
        return list;
    }


    @Override
    public void updateLoginTime(String username) {
        if(regexMatch(username)) {
            userMapper.updateLoginTimeEmail(username);
            return ;
        }
        userMapper.updateLoginTimeUsername(username);
    }

    @Override
    public User getUserById(Long userId) throws CustomException {
        if(!checkUserId(userId)) {
            log.warn("【UserService】getUserById::bad userid, userid = {}", userId);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        log.info("【UserService】getUserById::return user");
        return userMapper.getUserById(userId);
    }


    @Override
    public User getUserByEmail(String email) {
        log.info("【UserService】gerUserByEmail::email = {}", email);
        return userMapper.findUserByEmail(email.trim());
    }


    @Override
    public User getUserByName(String username) throws CustomException {
        if("".equals(username.trim())) {
            log.warn("【UserService】getUserByName::bad username, username = {}", username);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        log.info("【UserService】getUserByName::return user");
        return userMapper.getUserByName(username);
    }


    @Override
    public User insert(User user) throws CustomException {
        if(!userCheck(user)) {
            log.warn("【UserService】insert::bad user entity");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        String username = user.getUsername().trim();
        User user1 = userMapper.getUserByName(username);
        String email = user.getEmail().trim();
        if(user1 != null) {
            log.warn("【UserService】insert::the user has exist, username = {}", username);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        if(userMapper.emailCheck(email) == 1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "email has been used.");
        }
        String pwd = MD5Utils.toMd5(user.getPassword().trim(), "shishc", 10);
        user.setPassword(pwd);
        userMapper.insert(username, pwd, USER_DEFAULT_AVATAR, email);
        User user2 = userMapper.getUserByName(username);
        log.info("【UserService】insert::add user successfully! userId = {}", user2.getUserId());
        return user2;
    }


    @Override
    public Integer delete(Long userId) throws CustomException {
        if(!checkUserId(userId)) {
            log.warn("【UserService】delete::bad userid, userid = {}", userId);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
//        log.info("【UserService】delete::delete user, userId = {}", userId);
//        deleteUserData(userId);
        log.info("【UserService】delete::delete user's all data successfully!");
        return userMapper.delete(userId);
    }


    @Override
    public void deleteUserData(Long uid) throws CustomException {
//        // 删除用户的所有关注
//        List<Long> attentionList = attentionMapper.getAttentions(uid);
//        if(attentionList.size() > 0) {
//            for(Long uided : attentionList) {
//                attentionMapper.cancelAttention(uid, uided);
//                User user = userMapper.getUserById(uided);
//                userMapper.updateNum(uided, user.getPostCount(), user.getLikeCount(), user.getCollectionCount(), user.getFollowing(), user.getFollowed() -1);
//            }
//        }
//        // 更新关注这个用户的所有用户
//        List<Long> attentionedlist = attentionMapper.countAttentions(uid);
//        if(attentionedlist.size() > 0) {
//            for(Long uided : attentionedlist) {
//                attentionMapper.cancelAttention(uided, uid);
//                User user = userMapper.getUserById(uided);
//                userMapper.updateNum(uided, user.getPostCount(), user.getLikeCount(), user.getCollectionCount(), user.getFollowing() - 1, user.getFollowed());
//            }
//        }
//        // 删除一个该用户的所有博客
//        List<Blog> list = blogService.getBlogByUserId(uid);
//        if(list.size() > 0) {
//            for(Blog blog : list) {
//                blogService.delete(blog.getBlogId());
//            }
//        }
//        // 删除用户的所有收藏
//        List<Long> collectionList = collectionMapper.getCollections(uid);
//        if(collectionList.size() > 0) {
//            for(Long id : collectionList) {
//                collectionMapper.userCancelBlogCollection(uid, id);
//                Blog blog = blogService.getBlogByBID(id);
//                blogService.updateBlogNum(id, blog.getCommentNum(), blog.getCollectionNum() - 1);
//            }
//        }
//        // 删除用户参与的所有评论

    }

    @Override
    public Long getUserId(String username) {
        if(regexMatch(username)) {
            return userMapper.findUserByEmail(username).getUserId();
        }
        return userMapper.getUserId(username);
    }


    @Override
    public User update(User user) throws CustomException {
        String email = user.getEmail().trim();
        if(!regexMatch(email)) {
            log.warn("【UserService】update::bad user entity");
            throw new CustomException(HttpStatus.BAD_REQUEST, "bad email");
        }
        Long userId = user.getUserId();
        User user1 = userMapper.getUserById(userId);
        if(null == user1) {
            log.warn("【UserService】:update:: the user doesn't exist! userId = {}", userId);
            throw new CustomException(HttpStatus.BAD_REQUEST, "bad userid");
        }
        if(userMapper.emailCheck(email) == 1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "email has been used.");
        }
        userMapper.update(userId, user.getUserSite(), user.getAvatar(), email);
        log.info("【Service】UserService::update: update successfully! userId = {}", userId);
        return userMapper.getUserById(userId);
    }


    @Override
    public boolean updatePassword(String oldPassword, Long userId, String newPassword) throws CustomException{
        log.info("【UserService】updatePwd::update");
        String dbPwd = userMapper.getPasswordByUid(userId);
        String oldP = MD5Utils.toMd5(oldPassword, "shishc", 10);
        String newP = MD5Utils.toMd5(newPassword, "shishc", 10);
        if(!oldP.equals(dbPwd)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "PWD_ERROR");
        }
        userMapper.updatePassword(newP, userId);
        log.info("【UserService】updatePassword::pwd change successfully!");
        return true;
    }


    @Override
    public void updateUserNum(Long uid, Integer postCount, Integer likeCount, Integer collectionNum, Integer following, Integer followed) {
        userMapper.updateNum(uid, postCount, likeCount, collectionNum, following, followed);
    }


    @Override
    public boolean userCheck(User user) {
        String password = user.getPassword().trim();
        String username = user.getUsername().trim();
        String email = user.getEmail();
        if("".equals(username) || "".equals(password) || !regexMatch(email)) {
            log.warn("【UserService】userCheck::bad user entity");
            return false;
        }
        return true;
    }


    @Override
    public boolean regexMatch(String email) {
        String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        return email.matches(pattern);
    }


    @Override
    public Boolean checkUserId(Long userId) {
        if(userId < 0) {
            log.warn("【UserService】checkUserId::userId <= 0, userId = {}", userId);
            return false;
        }
        return true;
    }


    @Override
    public List<Perms> getUserPerms(UserIdentity identity) {
        return permsMapper.getUserPerms(identity.getKey());
    }


}
