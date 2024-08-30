package com.github.itdachen.auth.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;

/**
 * CaptchaUtils
 *
 * @author 王大宸
 * @date 2024-08-30 10:08
 */
public class CaptchaUtils {

    /***
     * 获取验证码存储 KEY
     *
     * @author 王大宸
     * @date 2024/8/30 10:32
     * @param request request
     * @return java.lang.String
     */
    public static String getCaptchaKey(HttpServletRequest request) {
        String ip = getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");
        String key = "auth:captcha:" + MD5(ip + userAgent);
        //  logger.info("验证码key:{}", key);
        return key;
    }

    /***
     * MD5加密
     *
     * @author 王大宸
     * @date 2024/8/30 9:52
     * @param data data
     * @return java.lang.String
     */
    public static String MD5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception exception) {
        }
        return null;

    }


    /***
     * 获取ip
     *
     * @author 王大宸
     * @date 2024/8/30 9:52
     * @param request request
     * @return java.lang.String
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            //    System.out.println("x-forwarded-for=" + ipAddress);
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
                //   System.out.println("Proxy-Client-IP=" + ipAddress);
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
                //  System.out.println("WL-Proxy-Client-IP=" + ipAddress);
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                //   System.out.println("getRemoteAddr=" + ipAddress);
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                    //   System.out.println("getHostAddress=" + ipAddress);
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }


}
