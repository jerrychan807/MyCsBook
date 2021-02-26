package com.baizhi.service.controller;

import com.baizhi.entity.User;
import com.baizhi.entity.UserFile;
import com.baizhi.service.UserFileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("pass01")
public class Pass01Controller {

    @PostMapping("upload")
    public String upload(Model model, MultipartFile aaa) throws IOException {
        // 获取文件原始名称
        String oldFileName = aaa.getOriginalFilename();

        // 获取文件后缀
        String extension = "." + FilenameUtils.getExtension(aaa.getOriginalFilename());

        // 生成新的文件名称
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "") + extension;

        // 文件大小
        Long size = aaa.getSize();

        // 文件类型
        String type = aaa.getContentType();

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
        aaa.transferTo(new File(dateDir, newFileName));

        model.addAttribute("var1", "value1");

        return "pass01result";
    }
}
