package icu.shishc.BlogService;

import icu.shishc.Exception.CustomException;
import icu.shishc.mapper.BlogMapper;
import icu.shishc.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @date: 2021-5-17, 20:03
 * @author: ShiShc
 */


@SpringBootTest
public class BlogTest {

    @Autowired
    BlogService blogService;
    @Autowired
    BlogMapper blogMapper;


    @Test
    public void test01() throws CustomException {
        System.out.println(blogMapper.delete(Long.parseLong("1")));
        System.out.println(blogMapper.delete(Long.parseLong("30005")));
    }



}
