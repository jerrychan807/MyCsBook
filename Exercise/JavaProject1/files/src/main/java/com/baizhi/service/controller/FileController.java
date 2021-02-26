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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private UserFileService userFileService;


    // 上传文件处理 并保存文件信息到数据库中
    @PostMapping("upload")
    public String upload(MultipartFile aaa, HttpSession session) throws IOException {
        // 获取上传文件用户id
        User user = (User) session.getAttribute("user");

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

        // 将文件信息放入数据库中
        UserFile userFile = new UserFile();
        userFile.setOldFileName(oldFileName).setNewFileName(newFileName).setExt(extension).setSize(String.valueOf(size)).setType(type).setPath("/files/" + dateFormat).setUserId(user.getId());
        userFileService.save(userFile);

        return "redirect:/file/showAll";
    }

//    @PostMapping("upload01")
//    public String upload(Model model, MultipartFile aaa) throws IOException {
//        // 获取文件原始名称
//        String oldFileName = aaa.getOriginalFilename();
//
//        // 获取文件后缀
//        String extension = "." + FilenameUtils.getExtension(aaa.getOriginalFilename());
//
//        // 生成新的文件名称
//        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "") + extension;
//
//        // 文件大小
//        Long size = aaa.getSize();
//
//        // 文件类型
//        String type = aaa.getContentType();
//
//        // 根据日期生成目录
//        String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static/files";
//        String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        String dateDirPath = realPath + "/" + dateFormat;
//        // System.out.println(dateDirPath);
//        File dateDir = new File(dateDirPath);
//        if (!dateDir.exists()) {
//            dateDir.mkdirs();
//        }
//        // 处理文件上传
//        aaa.transferTo(new File(dateDir, newFileName));
//
//        model.addAttribute("var1", "value1");
//
//        return "pass01result";
//    }

    //    展示所有文件信息
    @GetMapping("showAll")
    public String findAll(HttpSession session, Model model) {
        // 在登录的session中获取用户的id
        User user = (User) session.getAttribute("user"); // 强制类型转换
        //根据用户id查询所有的文件信息
        List<UserFile> userFiles = userFileService.findByUserId(user.getId());
        // System.out.println("查询所有进入....");
        // 存入作用域中
        model.addAttribute("files", userFiles);

        return "showAll";
    }
}
