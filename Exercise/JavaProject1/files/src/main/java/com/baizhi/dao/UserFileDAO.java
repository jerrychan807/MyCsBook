package com.baizhi.dao;

import com.baizhi.entity.UserFile;

import java.util.List;

public interface UserFileDAO {

    // 根据登录用户id获取用户的文件列表
    List<UserFile> findByUserId(Integer id);

    // 保存用户的文件记录
    void save(UserFile userFile);
}
