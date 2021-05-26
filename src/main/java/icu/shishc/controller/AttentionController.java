package icu.shishc.controller;

import icu.shishc.dto.MyDTO;
import icu.shishc.entity.User;
import icu.shishc.service.AttentionService;
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


    @RequestMapping(value = "/attention", method = RequestMethod.POST)
    public MyDTO addAttention(@RequestParam Long uid, @RequestParam Long uided) {
        int i = attentionService.addAttention(uid, uided);
        return MyDTO.successDTO(i);
    }

    @RequestMapping(value = "/attention", method = RequestMethod.DELETE)
    public MyDTO cancelAttention(@RequestParam Long uid, @RequestParam Long uided) {
        int i = attentionService.cancelAttention(uid, uided);
        return MyDTO.successDTO(i);
    }

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
    public MyDTO getAttentions(@RequestParam Long uid) {
        List<User> list = attentionService.getAttention(uid);
        return MyDTO.successDTO(list);
    }

    /**
     * 获取关注他的
     * @param uid
     * @return
     */
    @RequestMapping(value = "/u/attentioned", method = RequestMethod.GET)
    public MyDTO getAttentioned(@RequestParam Long uid) {
        List<User> list = attentionService.getAttentioned(uid);
        return MyDTO.successDTO(list);
    }


}
