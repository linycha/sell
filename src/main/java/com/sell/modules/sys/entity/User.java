package com.sell.modules.sys.entity;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author linyc
 * @date 2019/12/12 12:43
 */
public class User implements Serializable {
    private String id;

    private String openId;

    private String username;

    private String password;

    private String sex;

    private String phone;

    private String headImg;

    private String question;

    private String answer;

    private Integer role;

    private Date createTime;

    private Date updateTime;

    private List<Role> roles = new ArrayList<>();
    private  String roleListStr;


    public User(String id, String openId, String username, String password, String sex, String phone, String headImg, String question, String answer, Integer role, Date createTime, Date updateTime) {
        this.id = id;
        this.openId = openId;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.headImg = headImg;
        this.question = question;
        this.answer = answer;
        this.role = role;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    /*public String getRoleNames() {
        return Collections3.extractToString(roleList, "name", ",");
    }*/

    public User() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getRoleIdListStr(List<Role> roleList1) {
        List<String> idList= Lists.newArrayList();
        for (Role role : roleList1) {
            idList.add(role.getId());
        }

        if(idList!=null && idList.size()>0){
            return StringUtils.join(idList,",");
        }
        return "";

    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", openId='" + openId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", headImg='" + headImg + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", role=" + role +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", roles=" + roles +
                '}';
    }
}