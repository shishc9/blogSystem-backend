package icu.shishc.mapper;

import icu.shishc.entity.Blog;
import icu.shishc.enumeration.BlogStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
    Integer insert(@Param("username") String username,
                   @Param("title") String title,
                   @Param("content") String content,
                   @Param("status") int status
    );

    Integer delete(@Param("id") Long bid);

    Integer update(@Param("title") String title,
                   @Param("content") String content,
                   @Param("status") BlogStatus status
    );

    List<Blog> getAllBlog();

    Blog getBlogByTitle(@Param("title") String title);

    Blog getBlogByBID(@Param("bid") Long bid);

    List<Blog> getBlogByStatus(@Param("blogStatus") BlogStatus blogStatus);

    Integer getAllLike();

    Integer getAllReadNum();
}
