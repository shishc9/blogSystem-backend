//package icu.shishc.controller;
//
//import icu.shishc.dto.MyDTO;
//import icu.shishc.util.AttachUtils;
//import io.github.bluemiaomiao.service.FastdfsClientService;
//import lombok.extern.slf4j.Slf4j;
//import org.csource.common.FastdfsException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.UnsupportedEncodingException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Objects;
//
///**
// * @date: 2021-5-25, 15:56
// * @author: ShiShc
// */
//
//@RestController
//@Slf4j
//@RequestMapping("/attachments")
//public class AttachController {
//
//    @Autowired
//    FastdfsClientService remoteService;
//
//    @RequestMapping(value = "/attachment", method = RequestMethod.POST)
//    public MyDTO upload(@RequestParam MultipartFile file) {
//        String[] remoteInfo;
//        try {
//            remoteInfo = remoteService.autoUpload(file.getBytes(), Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1));
//            log.info("【AttachController】upload:group - {}", remoteInfo[0]);
//            log.info("【AttachController】upload:id - {}", remoteInfo[1]);
//        } catch (Exception e) {
//            log.warn("【AttachController】upload:error");
//            return MyDTO.wrongDTO(HttpStatus.INTERNAL_SERVER_ERROR, "UPLOAD_ERROR");
//        }
//
//        return MyDTO.successDTO(remoteInfo);
//    }
//
//
//    @RequestMapping(value = "/attachment", method = RequestMethod.GET)
//    public MyDTO download(@RequestParam String group, @RequestParam String fileId) throws FastdfsException, UnsupportedEncodingException, NoSuchAlgorithmException {
//        log.info("【AttachController】download: group = {}, id = {}", group, fileId);
//        return MyDTO.successDTO(AttachUtils.getSourceUrl(group, fileId));
//    }
//
//}
