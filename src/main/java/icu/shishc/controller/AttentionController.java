package icu.shishc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import icu.shishc.dto.MyDTO;
import icu.shishc.entity.Pager;
import icu.shishc.entity.User;
import icu.shishc.service.AttentionService;
import icu.shishc.util.PagerUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @date: 2021-5-26, 22:55
 * @author: ShiShc
 */

@RestController
@RequestMapping("/attentions")
public class AttentionController {

    private final AttentionService attentionService;


    public AttentionController(AttentionService attentionService) {
        this.attentionService = attentionService;
    }


    /**
     * 新增关注
     * @param uid
     * @param uided
     * @return
     */
    @RequestMapping(value = "/attention", method = RequestMethod.POST)
    public MyDTO addAttention(@RequestParam Long uid, @RequestParam Long uided) {
        int i = attentionService.addAttention(uid, uided);
        return MyDTO.successDTO(i);
    }

    /**
     * 取消关注
     * @param uid
     * @param uided
     * @return
     */
    @RequestMapping(value = "/attention", method = RequestMethod.DELETE)
    public MyDTO cancelAttention(@RequestParam Long uid, @RequestParam Long uided) {
        int i = attentionService.cancelAttention(uid, uided);
        return MyDTO.successDTO(i);
    }

    /**
     * 是否关注
     * @param uid
     * @param uided
     * @return
     */
    @RequestMapping(value = "/attention", method = RequestMethod.GET)
    public MyDTO attentionOrNot(@RequestParam Long uid, @RequestParam Long uided) {
        int i = attentionService.attentionOrNot(uid, uided);
        return MyDTO.successDTO(i);
    }

    /**
     * 获取他关注的
     * @param uid
     * @return
     */
    @RequestMapping(value = "/u/attention", method = RequestMethod.GET)
    public MyDTO getAttentions(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam Long uid) {
        PageHelper.startPage(page, size);
        List<User> list = attentionService.getAttention(uid);
        Pager pager = PagerUtils.getPager(new PageInfo<>(list));
        return MyDTO.successDTO(pager);
    }

    /**
     * 获取关注他的
     * @param uid
     * @return
     */
    @RequestMapping(value = "/u/attentioned", method = RequestMethod.GET)
    public MyDTO getAttentioned(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam Long uid) {
        PageHelper.startPage(page, size);
        List<User> list = attentionService.getAttentioned(uid);
        Pager pager = PagerUtils.getPager(new PageInfo<>(list));
        return MyDTO.successDTO(pager);
    }


}
