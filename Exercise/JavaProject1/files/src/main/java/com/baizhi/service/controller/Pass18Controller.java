package com.baizhi.service.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("pass18")
public class Pass18Controller {

    @PostMapping("upload")
    public String upload(Model model, MultipartFile aaa, HttpServletRequest request) throws IOException {

        String[] whiteExt = {".jpg", ".png", ".gif"};

        // 获取文件原始名称
        String oldFileName = aaa.getOriginalFilename();

        // 删除文件名末尾的点
        while ((oldFileName.substring(oldFileName.length() - 1)).equals(".")) {
            oldFileName = oldFileName.substring(0, oldFileName.length() - 1);
        }

        // 获取文件后缀
        String extension = "." + FilenameUtils.getExtension(oldFileName);
        extension = extension.replace("::$DATA", ""); //去除字符串::$DATA
        extension = extension.toLowerCase(); //转换为小写
        System.out.println(extension);

        // 根据日期生成目录
        String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static/files";
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dateDirPath = realPath + "/" + dateFormat;
        // System.out.println(dateDirPath);
        File dateDir = new File(dateDirPath);
        if (!dateDir.exists()) {
            dateDir.mkdirs();
        }
        // 处理文件上传
        aaa.transferTo(new File(dateDir, oldFileName));

        String filePath = dateDir + "\\" + oldFileName;
        File file = new File(filePath);

        if (Arrays.asList(whiteExt).contains(extension)) {
            // 生成新的文件名称
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "") + extension;
            String newfilePath = dateDir + "\\" + newFileName;
            System.out.println(newfilePath);
            File newfile = new File(newfilePath);
            // 重命名
            file.renameTo(newfile);
            model.addAttribute("result", "success");
        } else {
            // delete
            if (file.exists()) {
                file.delete();
            }
            model.addAttribute("result", "fail");
        }

        model.addAttribute("title", "Pass18结果");
        return "passresult";
    }
}
