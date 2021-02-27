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
@RequestMapping("pass04")
public class Pass04Controller {

    @PostMapping("upload")
    public String upload(Model model, MultipartFile aaa, HttpServletRequest request) throws IOException {

        String[] denyExt = {".php", ".php5", ".php4", ".php3", ".php2", ".php1", ".html", ".htm", ".phtml", ".pht", ".pHp", ".pHp5", ".pHp4", ".pHp3", ".pHp2", ".pHp1", ".Html", ".Htm", ".pHtml", ".jsp", ".jspa", ".jspx", ".jsw", ".jsv", ".jspf", ".jtml", ".jSp", ".jSpx", ".jSpa", ".jSw", ".jSv", ".jSpf", ".jHtml", ".asp", ".aspx", ".asa", ".asax", ".ascx", ".ashx", ".asmx", ".cer", ".aSp", ".aSpx", ".aSa", ".aSax", ".aScx", ".aShx", ".aSmx", ".cEr", ".sWf", ".swf", ".ini"};

        // 获取文件原始名称
        String oldFileName = aaa.getOriginalFilename();
        // 获取文件后缀
        String extension = "." + FilenameUtils.getExtension(oldFileName);
        // TODO:删除文件名末尾的点
        extension = extension.replace("::$DATA", ""); //去除字符串::$DATA
        extension = extension.toLowerCase(); //转换为小写
        extension = extension.trim(); // 首尾去空
        System.out.println(extension);

        if (!Arrays.asList(denyExt).contains(extension)) {

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

            model.addAttribute("var1", "success");
        } else {
            model.addAttribute("var1", "fail");
        }

        return "pass04result";
    }
}
