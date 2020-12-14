package com.sell.modules.sys.controller;

import com.sell.common.Const;
import com.sell.common.ResponseCode;
import com.sell.common.Res;
import com.sell.common.utils.PropertiesUtil;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.entity.Feedback;
import com.sell.modules.store.service.FileService;
import com.sell.modules.sys.entity.User;
import com.sell.modules.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author linyuc
 * @date 2019/12/18 15:10
 */
@RestController
@CrossOrigin(origins="*",maxAge=3600)
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;

    @GetMapping("aaa")
    public Res<String> aaa(){
        Object object = SecurityUtils.getSubject().getPrincipal();
        System.out.println(object.toString());
        return Res.success("aaaacl");
    }
    /**
     * 获取用户个人信息
     */
    @GetMapping("info")
    public Res<User> info(){
        User user = userService.selectById(UserUtils.getUserId());
        System.out.println(user);
        if(user == null){
            return Res.errorMsg("找不到当前用户");
        }
        return Res.success(user);
    }

    @PutMapping("update_head")
    public Res<String> updateHead(String id, MultipartFile file, HttpServletRequest request){
        if(file == null){
            return Res.errorMsg("上传头像为空");
        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = fileService.upload(file,path,Const.FTPPATH_USER);
        if(targetFileName == null){
            return Res.errorMsg("上传头像失败");
        }
        String url = PropertiesUtil.getProperty("ftp.prefix")+Const.FTPPATH_USER+"/"+targetFileName;
        User user = new User();
        user.setId(id);
        user.setHeadImg(url);
        int result = userService.update(user);
        if(result == 0){
            return Res.errorMsg("修改头像失败");
        }
        return Res.successMsg("修改头像成功");
    }
    @PutMapping("update_mobile")
    public Res<String> updateMobile(String mobile){
        User user = new User();
        user.setId(UserUtils.getUserId());
        user.setMobile(mobile);
        return userService.updateMobile(user);
    }
    @PutMapping("update_password")
    public Res<String> updatePassword(String oldPwd,String newPwd){
        User user = new User();
        user.setId(UserUtils.getUserId());
        user.setPassword(oldPwd);
        return userService.updatePassword(newPwd,user);
    }

    /**
     * 未登录状态下忘记密码的重置密码
     * */
    @RequestMapping(value = "/rest_password",method = RequestMethod.POST)
    @ResponseBody
    public Res<String> restPassword(String username,String passwordNew,String forgetToken){
        return userService.restPassword(username,passwordNew,forgetToken);
    }
    @PostMapping("feedback")
    public Res<String> feedback(String content){
        Feedback feedback = new Feedback();
        feedback.setContent(content);
        feedback.setUserId(UserUtils.getUserId());
        int result = userService.saveFeedback(feedback);
        if(result == 0){
            return Res.errorMsg("意见反馈失败");
        }
        return Res.successMsg("意见反馈成功");
    }
}
