package icu.shishc.util;

import org.csource.common.FastdfsException;
import org.csource.fastdfs.ProtoCommon;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @date: 2021-5-25, 15:57
 * @author: ShiShc
 */

public class AttachUtils {
    public static String getSourceUrl(String group, String remoteFilename) throws FastdfsException, UnsupportedEncodingException, NoSuchAlgorithmException {
        final String host = "http://123.56.42.105:8888";
        final String secretKey = "369999";
        int lts = (int)(System.currentTimeMillis() / 1000);
        String token = ProtoCommon.getToken(remoteFilename, lts, secretKey);
        return host + "/" + group + "/" + remoteFilename + "?token=" + token + "&ts=" + lts;
    }
}
