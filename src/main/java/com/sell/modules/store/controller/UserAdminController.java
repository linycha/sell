package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.sell.common.Res;
import com.sell.modules.store.dto.QueryDTO;
import com.sell.modules.sys.entity.User;
import com.sell.modules.sys.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员端，用户管理相关接口
 * @author linyuc
 * @Description TODO
 * @date 2022/2/28 23:02
 */
@RestController
@RequestMapping("userAdmin")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    @ApiOperation("查找普通用户列表")
    public Res<PageInfo<User>> getUserList(QueryDTO dto){
        return Res.success(userService.getUserList(dto));
    }

    @PostMapping("update")
    @ApiOperation("修改普通用户")
    public Res<String> update(@RequestBody User user){
        int result = userService.update(user);
        if(result < 1){
            return Res.errorMsg("修改用户失败");
        }
        return Res.successMsg("修改用户成功");
    }

    @DeleteMapping("delete")
    @ApiOperation("批量删除普通用户")
    public Res<String> deleteUser(String ids){
        if(StringUtils.isBlank(ids)){
            return Res.errorMsg("传递的id参数不能为空");
        }
        int result = userService.deleteBatch(ids);
        if(result == 0){
            return Res.errorMsg("删除失败");
        }
        return Res.successMsg("删除成功");
    }
}
