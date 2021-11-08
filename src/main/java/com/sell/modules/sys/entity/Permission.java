package com.sell.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限表，每个角色有哪些权限
 * @author linyuc
 * @date 2019/12/27 11:35
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 6843973116522833281L;
    private String id;
    private String name;
    private String url;
}
