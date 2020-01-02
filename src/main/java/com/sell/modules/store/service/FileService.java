package com.sell.modules.store.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author linyuc
 * @date 2019/12/20 11:49
 */
public interface FileService {
    String upload(MultipartFile file, String path);
}
