package com.sell;

import com.sell.modules.sys.dao.UserMapper;
import com.sell.modules.sys.entity.Role;
import com.sell.modules.sys.entity.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author linyuc
 * @date 2019/12/12 12:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//告诉junit spring配置文件的位置
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String regex = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        //编译正则表达式
        Pattern pattern= Pattern.compile(regex);
        System.out.println("请输入身份证号码：");
        while(true){
            String id = sc.next();
            //与正则表达式匹配
            Matcher matcher=pattern.matcher(id);
            if(matcher.matches()){
                int year = Integer.parseInt(id.substring(6,10));
                int age = 2019 - year;
                System.out.println("格式正确，年龄是："+age);
                break;
            }else{
                System.out.println("身份证号码格式，重新输入：");
            }
        }
    }
    @Autowired
    private UserMapper userMapper;
    @org.junit.Test
    public void test(){
       Object result = new SimpleHash("md5","123456", null, 2);
        System.out.println(result);
    }

}
