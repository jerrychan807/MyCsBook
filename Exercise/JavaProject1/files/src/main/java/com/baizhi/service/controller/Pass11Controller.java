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

@Controller
@RequestMapping("pass11")
public class Pass11Controller {
    //
    // 双写绕过
    //

    @PostMapping("upload")
    public String upload(Model model, MultipartFile aaa, HttpServletRequest request) throws IOException {

        String[] denyExt = {"php", "php5", "php4", "php3", "php2", "html", "htm", "phtml", "pht", "jsp", "jspa", "jspx", "jsw", "jsv", "jspf", "jtml", "asp", "aspx", "asa", "asax", "ascx", "ashx", "asmx", "cer", "swf", "htaccess"};

        // 获取文件原始名称
        String oldFileName = aaa.getOriginalFilename();


        for (String ext : denyExt) { // 将含黑名单字符串置为空
            oldFileName = oldFileName.replace(ext, "");
        }

        System.out.println(oldFileName);

        // 根据日期生成目录
        String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static/files";
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dateDirPath = realPath + "/" + dateFormat;

        File dateDir = new File(dateDirPath);
        if (!dateDir.exists()) {
            dateDir.mkdirs();
        }
        // 处理文件上传
        try {
            aaa.transferTo(new File(dateDir, oldFileName));
            model.addAttribute("result", "success");
        } catch (Exception e) {
            model.addAttribute("result", "fail");
        }

        model.addAttribute("title", "Pass11结果");
        return "passresult";
    }
}
