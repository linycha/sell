package com.sell.modules.sys.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linyuc
 * @Description TODO
 * @date 2022/1/9 3:25
 */
@Data
public class PasswordDTO implements Serializable {
    private static final long serialVersionUID = -4496599659421192593L;
    private String oldPassword;

    private String newPassword;
}
