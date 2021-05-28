package com.sell.modules.sys.entity;

import java.io.Serializable;

/**
 * 权限表，每个角色有哪些权限
 * @author linyuc
 * @date 2019/12/27 11:35
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = 6843973116522833281L;
    private String id;
    private String name;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
