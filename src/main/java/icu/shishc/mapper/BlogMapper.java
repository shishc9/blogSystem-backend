package icu.shishc.mapper;

import icu.shishc.entity.Blog;
import icu.shishc.enumeration.BlogStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper {
    int insert(@Param("blog") Blog blog);

    int delete(@Param("id") int id);

    int update(@Param("blog") Blog blog);

    List<Blog> getAllBlog();

    Blog getBlogByTitle(@Param("title") String title);

    Blog getBlogByBID(@Param("bid") Long bid);

    List<Blog> getBlogByStatus(@Param("blogStatus") BlogStatus blogStatus);

    Integer getAllLike();

    Integer getAllReadNum();
}
