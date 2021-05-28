package icu.shishc.dto;

import icu.shishc.entity.User;
import icu.shishc.enumeration.UserIdentity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @date: 2021-5-9, 16:29
 * @author: ShiShc
 */

@Data
@ApiModel(value = "User数据")
public class UserDTO {

    Long userId;
    String username;
    UserIdentity userIdentity;
    String userSite;
    String avatar;
    String email;
    Integer postCount;
    Integer collectionCount;
    Integer likeCount;
    Integer following;
    Integer followed;
    Integer isDelete;


    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.userIdentity = user.getUserIdentity();
        this.userSite = user.getUserSite();
        this.avatar = user.getAvatar();
        this.email = user.getEmail();
        this.postCount = user.getPostCount();
        this.collectionCount = user.getCollectionCount();
        this.likeCount = user.getLikeCount();
        this.following = user.getFollowing();
        this.followed = user.getFollowed();
        this.isDelete = user.getIsDelete();
    }
}
