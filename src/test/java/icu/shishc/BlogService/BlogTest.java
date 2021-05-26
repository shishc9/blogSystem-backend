//package icu.shishc.BlogService;
//
//import icu.shishc.entity.Blog;
//import icu.shishc.exception.CustomException;
//import icu.shishc.mapper.BlogMapper;
//import icu.shishc.service.BlogService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @date: 2021-5-17, 20:03
// * @author: ShiShc
// */
//
//
//@SpringBootTest
//public class BlogTest {
//
//    @Autowired
//    BlogService blogService;
//    @Autowired
//    BlogMapper blogMapper;
//
//
//    @Test
//    public void test01() throws CustomException {
//        System.out.println(blogMapper.delete(Long.parseLong("1")));
//        System.out.println(blogMapper.delete(Long.parseLong("30005")));
//    }
//
//
//    @Test
//    public void test02() {
//        ArrayList<Long> list = new ArrayList<>();
//        list.add(30000L);
//        list.add(30002L);
//        list.add(30004L);
//        list.add(30006L);
//
//        List<Blog> result = blogMapper.getBlogByList(list);
//        for(Blog blog : result) {
//            System.out.println(blog);
//        }
//    }
//
//
//
//}
