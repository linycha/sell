package com.sell.modules.store.controller;

import com.sell.common.Res;
import com.sell.modules.store.dao.TestMapper;
import com.sell.modules.store.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/13 17:35
 */
@RestController
@RequestMapping("testt")
public class Test1Controller {
    @Autowired
    private TestMapper testMapper;
    @RequestMapping("listt")
    public Res<List<Test>> lists(){
        return Res.success(testMapper.seleteTestList());
    }
}
