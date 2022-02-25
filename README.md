 **网上订餐系统--后端部分**  
本系统共用三个客户端，用户端、商家端和骑手端，请拉取代码的老哥们点点star！！谢谢！

本系统是基于SpringBoot+Vue的前后端分离架构，使用MySQL+Redis数据库,持久层框架采用MyBatis，整合Shiro安全框架，前端项目采用VUE全家桶框架，
前端使用axios请求调取后端接口，前后端采用Restful接口风格对接，json数据格式传输，三个客户端之间采用Websocket通讯协议进行双向的实时交互,同时搭建了
文件服务器系统，项目的图片文件均存储在独立的文件服务器上，可在用户端自行注册账号修改头像测试。
   
 :hand: 用户端和骑手端采用移动端UI框架Vant： https://vant-contrib.gitee.io/vant/#/zh-CN/home   
 :hand: 商家端使用ElementUI： https://element.eleme.cn/#/zh-CN/component/drawer

项目部署上采用Doker虚拟化技术部署在自己的阿里云上，整合合Nginx、Haproxy和keepalived,从数据库到后端、前端均采用多节点负载均衡策略部署。

整个项目用的都是目前主流的技术，覆盖前后端各个技术的基础，从数据库，到后端，再到前端，再部署上云，只需这一个项目即可了解掌握整个项目的开发流程及各种细节！

 :smile:  :smile:  :smile:  :smile:  :smile:  :smile: 

 :raised_hand:  :raised_hand:  :raised_hand: 手机移动用户端访问地址（电脑端打开请按ctrl+shirt+i进入浏览器调试模式，调成手机大小模式）
 [点击进入移动用户端](http://106.14.242.100:8018/#/user) (可先注册)

 :raised_hand:  :raised_hand:  :raised_hand: 商家端访问地址：[点击进入商家端访问地址](http://106.14.242.100:8017/#/login) 体验账号：yidiandian/123456

 :raised_hand:  :raised_hand:  :raised_hand: 项目接口文档地址：[点击进入接口文档地址](http://106.14.242.100:8086/doc.html)

需要前端代码及sql，请联系qq:1058298031!!!

需要前端代码及sql，请联系qq:1058298031!!!

如有疑问需要沟通交流或技术帮助，请联系qq:1058298031!!!

如有疑问需要沟通交流或技术帮助，请联系qq:1058298031!!!

**移动用户端主要界面：**    
![输入图片说明](src/main/resources/templates/images/%E7%94%A8%E6%88%B7%E7%AB%AF.png)
![输入图片说明](src/main/resources/templates/images/%E7%94%A8%E6%88%B7%E7%AB%AF3.png)
![输入图片说明](src/main/resources/templates/images/%E7%94%A8%E6%88%B7%E7%AB%AF4.png)

**移动骑手端主要界面：**    
![输入图片说明](src/main/resources/templates/images/%E9%AA%91%E6%89%8B%E7%AB%AF.png)


**pc商家管理端主要界面：**    

![输入图片说明](src/main/resources/templates/images/%E5%95%86%E5%AE%B6%E7%AB%AF.png)
![输入图片说明](src/main/resources/templates/images/%E5%95%86%E5%AE%B6%E7%AB%AF2.jpg)
![输入图片说明](src/main/resources/templates/images/%E5%95%86%E5%AE%B6%E7%AB%AF3.png)
![输入图片说明](src/main/resources/templates/images/%E5%95%86%E5%AE%B6%E7%AB%AF7.png)
![输入图片说明](src/main/resources/templates/images/%E5%95%86%E5%AE%B6%E7%AB%AF5.png)
![输入图片说明](src/main/resources/templates/images/%E5%95%86%E5%AE%B6%E7%AB%AF6.png)
