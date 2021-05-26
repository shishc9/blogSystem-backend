package icu.shishc.service;


public interface CollectionService {

    /**
     * 删除收藏该博客的记录
     * @param bid bid
     * @return ,
     */
    Integer deleteBlogCollection(Long bid);

}
