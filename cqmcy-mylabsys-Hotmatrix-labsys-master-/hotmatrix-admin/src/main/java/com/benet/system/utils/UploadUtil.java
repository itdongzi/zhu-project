package com.benet.system.utils;

import com.benet.common.utils.file.FileUtils;
import com.benet.common.utils.string.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件上传工具类
 *
 * @author yong
 * @date 2017-03-02 16:33:24
 */
public class UploadUtil {

    // 最大文件大小
    private long maxSize = 10 * 1024 * 1024;

    // 文件保存目录url
    private String saveUrl;

    private static String image = "gif,jpg,jpeg,png,bmp";

    private static String media = "swf,flv,mp3,mp4,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb";

    private static String file = "doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,zip,rar,gz,bz2";

    public UploadUtil() {

    }

    /**
     * 获取文件类型
     *
     * @param fileFormat
     */
    public static String getType(String fileFormat) {
        if (StringUtils.contains(image, fileFormat)) {
            return "image";
        } else if (StringUtils.contains(media, fileFormat)) {
            return "media";
        } else if (StringUtils.contains(file, fileFormat)) {
            return "file";
        } else {
            return "other";
        }
    }

    /**
     * @param file     //文件对象
     * @param filePath //上传路径
     * @param fileName //文件名
     * @return 文件名
     */
    public static String fileUp(MultipartFile file, String filePath, String fileName) {
        System.out.println("filePath - " + filePath);
        System.out.println("fileName - " + fileName);
        String extName = ""; // 扩展名格式：
        try {
            if (file.getOriginalFilename().lastIndexOf(".") >= 0) {
                extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            }
            if (StringUtils.isBlank(extName)) {
                extName = ".png";
            }
            copyFile(file.getInputStream(), filePath, fileName + extName).replaceAll("-", "");
        } catch (IOException e) {
            System.out.println("IOException - " + e.getMessage());
        }
        return fileName + extName;
    }

    /**
     * 写文件到当前目录的upload目录中
     *
     * @param in
     * @param
     * @throws IOException
     */
    public static String copyFile(InputStream in, String dir, String realName)
            throws IOException {
        File file = new File(dir, realName);

        if (file.exists()) {
            FileUtils.deleteFile(dir + realName);
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        //FileUtils.(in, file);
        return realName;
    }

}
