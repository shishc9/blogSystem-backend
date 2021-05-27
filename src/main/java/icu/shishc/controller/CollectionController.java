package icu.shishc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import icu.shishc.dto.MyDTO;
import icu.shishc.entity.Blog;
import icu.shishc.entity.Pager;
import icu.shishc.exception.CustomException;
import icu.shishc.service.CollectionService;
import icu.shishc.util.PagerUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @date: 2021-5-27, 09:25
 * @author: ShiShc
 */

@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    /**
     * 列出用户的收藏
     * @param page
     * @param size
     * @param uid
     * @return
     */
    @RequestMapping(value = "/u", method = RequestMethod.GET)
    public MyDTO listUserCollections(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam("uid") Long uid) throws CustomException {
        PageHelper.startPage(page, size);
        List<Blog> list = collectionService.collectionList(uid);
        if(list == null) {
            return MyDTO.successDTO(null);
        }
        Pager pager = PagerUtils.getPager(new PageInfo<>(list));
        return MyDTO.successDTO(pager);
    }


    @RequestMapping(value = "/collection", method = RequestMethod.POST)
    public MyDTO addCollection(@RequestParam("uid") Long uid, @RequestParam("bid") Long bid) throws CustomException {
        int i = collectionService.addCollection(uid, bid);
        return MyDTO.successDTO(i);
    }

    @RequestMapping(value = "/collection", method = RequestMethod.DELETE)
    public MyDTO cancelCollection(@RequestParam("uid") Long uid, @RequestParam("bid") Long bid) throws CustomException {
        int i = collectionService.cancelCollection(uid, bid) ;
        return MyDTO.successDTO(i);
    }

    @RequestMapping(value = "/collection", method = RequestMethod.GET)
    public MyDTO collectionOrNot(@RequestParam("uid") Long uid, @RequestParam("bid") Long bid) throws CustomException {
        int i = collectionService.collectionOrNot(uid, bid);
        return MyDTO.successDTO(i);
    }
}
