package com.sell;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.sell.common.Const;
import com.sell.common.utils.CheckUtil;
import com.sell.common.utils.FTPUtil;
import com.sell.common.utils.RedisUtil;
import com.sell.modules.store.dao.*;
import com.sell.modules.store.entity.Delivery;
import com.sell.modules.store.entity.Shipping;
import com.sell.modules.store.entity.Shop;
import com.sell.modules.store.entity.ShopCategory;
import com.sell.modules.store.service.DeliveryService;
import com.sell.modules.store.service.OrderService;
import com.sell.modules.store.service.RedisService;
import com.sell.modules.store.service.ShopCategoryService;
import com.sell.modules.store.vo.Cart;
import com.sell.modules.store.vo.NewOrderVo;
import com.sell.modules.store.vo.ShopVo;
import com.sell.modules.store.vo.UserOrderVo;
import com.sell.modules.sys.dao.UserMapper;
import com.sell.modules.sys.entity.User;
import com.sun.media.sound.SoftTuning;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author linyuc
 * @date 2019/12/12 12:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//����junit spring�����ļ���λ��
public class Test {
    /*public static void main(String[] args) {
        String expressionString = "100*10-(200+300)";

        JexlEngine jexlEngine = new JexlBuilder().create();
        JexlExpression jexlExpression = jexlEngine.createExpression(expressionString);
        Object evaluate = jexlExpression.evaluate(null);
        System.out.println(evaluate);
        *//*Scanner sc = new Scanner(System.in);
        String regex = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        //����������ʽ
        Pattern pattern= Pattern.compile(regex);
        System.out.println("���������֤���룺");
        while(true){
            String id = sc.next();
            //��������ʽƥ��
            Matcher matcher=pattern.matcher(id);
            if(matcher.matches()){
                int year = Integer.parseInt(id.substring(6,10));
                int age = 2019 - year;
                System.out.println("��ʽ��ȷ�������ǣ�"+age);
                break;
            }else{
                System.out.println("���֤�����ʽ���������룺");
            }
        }*//*
    }*/
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ShopCategoryMapper shopCategoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisUtil.redisList redisList;
    @Autowired
    private RedisUtil.redisString redisString;
    @Autowired
    private FTPUtil ftpUtil;
    @Autowired
    private ShippingMapper shippingMapper;
    @org.junit.Test
    public void test1()throws Exception{
        /*User user = new User();
        user.setId("aaaacl");
        user.setUsername("����");
        getUser(user);
        System.out.println(user.toString());*/
        String srcString = "�������й���";
        System.out.println(srcString);
        //String utf2GbkString = new String(srcString.getBytes("UTF-8"),"GBK");
        String utf2GbkString = new String(srcString.getBytes("GBK"),"UTF-8");
        System.out.println("UTF-8ת����GBK��"+utf2GbkString);
        //String utf2Gbk2UtfString = new String(utf2GbkString.getBytes("GBK"),"UTF-8");
        //System.out.println("UTF-8ת����GBK��ת��UTF-8��"+utf2Gbk2UtfString);
        //printByReader();
    }
    public User getUser(User user){
        user.setUsername("����");
        return user;
    }

    public static void main(String[] args)throws Exception {
        String s = "Ľ��ABC";
        byte[] bytes1 = s.getBytes("gbk");//ת�����ֽ������õ���GBK�ı���
        //gbk��������ռ��2���ֽڣ�Ӣ��ռ��1���ֽ�
        for(byte b : bytes1){
            //���ֽڣ�ת������int����16���Ƶķ�ʽ��ʾ,0xff��ǰ���24��0ȥ��
            System.out.println(Integer.toHexString(b & 0xff)+" ");
        }
    }
    static void printByReader()throws IOException {
        File file = new File("E:\\javaio\\22.txt");
        FileInputStream in = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(in, "gbk");
        char[] buf = new char[8 * 1024];
        int c = 0;
        while((c = isr.read(buf,0,buf.length)) != -1){
            String s = new String(buf,0,c);
            System.out.println(s);
        }
    }
    @org.junit.Test
    public void test2(){
        //List<ShopCategory> list2 = shopCategoryService.getSiblingCategory("0");
        redisString.set("aaa", "2345",1800);
        String value = (String)redisString.get("aaa");
        String value2 = (String)redisString.get("aaaacl");
        System.out.println(value);
        System.out.println(value2);
        //Object s = new SimpleHash("md5","123456",null,2);
        //System.out.println(s);
    }
    @org.junit.Test
    public void test(){
        String categoryId = "3";
        List<String> categoryIds = new ArrayList<>();
        if(!StringUtils.isBlank(categoryId)){
            ShopCategory shopCategory = shopCategoryMapper.selectByPrimaryKey(categoryId);
            if(shopCategory.getParentId().equals(Const.CATEGORY_PARENT_ID)){
                categoryIds = shopCategoryMapper.selectCategoryList(categoryId);
                if(categoryIds.size() == 0){
                    System.out.println("������");
                    categoryIds = null;
                }
            }else{
                categoryIds.add(categoryId);
            }
        }else{
            categoryIds = null;
        }
        System.out.println("categoryIds="+categoryIds);
        List<ShopVo> shopList = shopMapper.selectShopList("", categoryIds, Const.ShopList.ORDER_BY.get(0));
        if(shopList.size() == 0){
            System.out.println("δ�ҵ�");
        }else{
            System.out.println(shopList);
        }

    }
    @org.junit.Test
    public void userTest(){

        List<ShopCategory> shopCategoryList = shopCategoryMapper.selectSiblingCategory("0");
        System.out.println(shopCategoryList);
    }

}
