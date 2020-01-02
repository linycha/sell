package com.sell.modules.store.controller;

import com.sell.common.Const;
import com.sell.common.ServerResponse;
import com.sell.modules.sys.entity.User;
import com.sell.modules.store.service.CategoryService;
import com.sell.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author linyuc
 * @date 2019/12/19 11:32
 */
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("add_category")
    public ServerResponse addCategory(String categoryName, @RequestParam(defaultValue = "0")Integer parentId, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.errorMsg("商家未登录");
        }
        //视频有校验是否是商家
        return categoryService.addCategory(categoryName,parentId);
    }
    @RequestMapping("set_category_name")
    public ServerResponse setCategoryName(HttpSession session,Integer categoryId,String categoryName){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.errorMsg("商家未登录");
        }
        return categoryService.updateCategoryName(categoryId,categoryName);
    }
    /**
     * 查找同级分类下的所有分类列表
     */
    @RequestMapping("get_sibling_category")
    public ServerResponse getSiblingCategory(HttpSession session,
                                                      @RequestParam(defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.errorMsg("商家未登录");
        }
        return  categoryService.getSiblingCategory(categoryId);
    }

}
