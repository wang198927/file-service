package com.daoming.fileservice.core.service;

import java.util.List;

public interface IFolderOperateService {
    public void createRootDir(String dirName);
    public List<String> listRootDirName();
}
