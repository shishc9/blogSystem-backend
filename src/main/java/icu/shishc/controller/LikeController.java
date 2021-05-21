package icu.shishc.controller;

import icu.shishc.exception.CustomException;
import icu.shishc.dto.MyDTO;
import icu.shishc.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2021-5-20, 13:27
 * @author: ShiShc
 */

@Slf4j
@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public MyDTO addLike(@RequestParam Long bid, @RequestParam Long uid) throws CustomException {
        log.info("【LikeController】addLike");
        return MyDTO.successDTO(likeService.addLike(bid, uid));
    }

    @RequestMapping(value = "/like", method = RequestMethod.DELETE)
    public MyDTO cancelLike(@RequestParam Long bid, @RequestParam Long uid) throws CustomException {
        log.info("【LikeController】cancelLike");
        return MyDTO.successDTO(likeService.cancelLike(bid, uid));
    }
}
