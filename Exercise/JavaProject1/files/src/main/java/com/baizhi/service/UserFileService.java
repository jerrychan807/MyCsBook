package com.baizhi.service;

import com.baizhi.entity.UserFile;

import java.util.List;

public interface UserFileService {

    List<UserFile> findByUserId(Integer id);

    void save(UserFile userFile);
}
