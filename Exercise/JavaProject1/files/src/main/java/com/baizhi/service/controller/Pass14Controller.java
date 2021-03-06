package com.baizhi.service.controller;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping("pass14")
public class Pass14Controller {
    //
    // 双写绕过
    //
    // refs:java:根据文件头来判断文件类型 https://blog.csdn.net/ludongshun2016/article/details/53302272
    //SpringBoot文件上传控制及Java 获取和判断文件头信息 https://www.jb51.net/article/130326.htm

    @PostMapping("upload")
    public String upload(Model model, MultipartFile aaa, HttpServletRequest request) throws IOException {

        String[] fileTypeCode = {"FFD8FFDB", "89504E47", "47494638"};

        // 获取文件原始名称
        String oldFileName = aaa.getOriginalFilename();

        String fileCode = null;
        try {
            InputStream is = aaa.getInputStream();
            byte[] b = new byte[4];
            is.read(b, 0, b.length);
            fileCode = bytesToHexString(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileCode = "unknown";
        }

        System.out.println(fileCode);
        System.out.println(oldFileName);
        String uppreFileCode = fileCode.toUpperCase();

        if (Arrays.asList(fileTypeCode).contains(uppreFileCode)) {
            // 根据日期生成目录
            String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static/files";
            String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String dateDirPath = realPath + "/" + dateFormat;

            File dateDir = new File(dateDirPath);
            if (!dateDir.exists()) {
                dateDir.mkdirs();
            }
            // 处理文件上传
            aaa.transferTo(new File(dateDir, oldFileName));
            model.addAttribute("result", "success");
        } else {
            model.addAttribute("result", "fail");
        }

        model.addAttribute("title", "Pass14结果");
        return "passresult";
    }

    /**
     * 得到上传文件的文件头
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
