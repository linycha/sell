package com.sell.modules.sys.controller;

import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.FTPUtil;
import com.sell.common.utils.PropertiesUtil;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.entity.Feedback;
import com.sell.modules.store.service.OrderService;
import com.sell.modules.store.service.OrderStatusService;
import com.sell.modules.sys.dto.PasswordDTO;
import com.sell.modules.sys.entity.User;
import com.sell.modules.sys.security.WebSocket;
import com.sell.modules.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author linyuc
 * @date 2019/12/18 15:10
 */
@Slf4j
@RestController
@CrossOrigin(origins="*",maxAge=3600)
@RequestMapping("user")
@Api(tags = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private WebSocket webSocket;
    @Autowired
    private FTPUtil ftpUtil;

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
    @ApiOperation("获取用户个人信息")
    public Res<User> info(){
        User user = userService.selectByUsername(null,UserUtils.getUserId());
        if(user == null){
            return Res.errorMsg("找不到当前用户");
        }
        return Res.success(user);
    }
    @PutMapping("update_head")
    @ApiOperation("修改个人头像")
    public Res<String> updateHead(MultipartFile file, HttpServletRequest request){
        if(file == null){
            return Res.errorMsg("上传头像为空");
        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        User user = new User();
        user.setId(UserUtils.getUserId());
        try {
            String fileName = ftpUtil.uploadFile(file,path,Const.FTP_PATH_USER);
            if(fileName == null){
                return Res.errorMsg("ftp上传图片出现失败");
            }
            String url = PropertiesUtil.getProperty("ftp.prefix")+Const.FTP_PATH_USER+"/"+fileName;
            user.setHeadImg(url);
        }catch (Exception e){
            log.info(e.getMessage());
            return Res.errorMsg("ftp上传图片出现异常");
        }
        int result = userService.update(user);
        if(result == 0){
            return Res.errorMsg("修改头像失败");
        }
        return Res.successMsg("修改头像成功");
    }
    @PutMapping("update_mobile")
    @ApiOperation("修改手机号")
    public Res<String> updateMobile(String mobile){
        User user = new User();
        user.setId(UserUtils.getUser().getId());
        user.setMobile(mobile);
        return userService.updateMobile(user);
    }

    @PutMapping("update_name")
    @ApiOperation("修改用户名")
    public Res<String> updateName(@RequestBody String username){
        if(StringUtils.isBlank(username)){
            return Res.errorMsg("用户名不能为空");
        }
        return userService.updateUsername(User.builder()
                .id(UserUtils.getUserId()).username(username).build());
    }

    @PutMapping("updatePwd")
    @ApiOperation("修改用户个人密码")
    public Res<String> updatePassword(@RequestBody PasswordDTO dto){
        return userService.updatePassword(dto);
    }

    /**
     * 未登录状态下忘记密码的重置密码
     * */
    @PostMapping("rest_password")
    @ResponseBody
    public Res<String> restPassword(String username,String passwordNew,String forgetToken){
        return userService.restPassword(username,passwordNew,forgetToken);
    }
    @PostMapping("feedback")
    @ApiOperation("用户端-发送意见反馈")
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
    @ApiOperation("用户确认收到餐操作")
    @PutMapping("sure")
    public Res<String> fulfill(Long orderNo){
        if(orderNo == null){
            return Res.errorMsg("订单号参数错误");
        }
        int result = orderService.updateStatusByOrderNo(orderNo, Const.OrderStatus.ACCOMPLISH);
        if(result == 0){
            return Res.errorMsg("确认送达失败");
        }
        boolean b2 = orderStatusService.saveStatus(orderNo,Const.OrderStatus.ACCOMPLISH);
        if(!b2){
            return Res.errorMsg("确认送达失败");
        }
/*        String shopId = orderService.getShopId(orderNo);
        webSocket.sendOneMessage(userId,"您有一条订单已送达，祝您用餐愉快");
        webSocket.sendOneMessage(shopId,"您有一条订单已被骑手送达");*/
        return Res.successMsg("确认送达成功");
    }
}
