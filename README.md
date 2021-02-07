网上订餐系统--后端部分

本系统共用三个客户端，用户端、商家端和骑手端

本系统是基于SpringBoot+Vue的前后端分离架构，使用MySQL+Redis数据库,结合Shiro安全框架，MyBatis持久层框架
前端采用axios请求，前后端采用Restful接口风格对接，使用json数据格式交互.

三个客户端之间采用Websocket通讯协议进行实时交互，页面UI上用户端和骑手端采用Vant，商家端使用ElementUI.

项目部署上采用Doker虚拟化部署在自己的阿里云上，结合Nginx、Haproxy和keepalived,从数据库到后端、前端均采用多节点负载均衡策略部署。

移动端用户端地址http://47.99.71.179:8091/#/user
本人毕设项目，还请多多指教！ :fa-check: 
互相学习联系qq:1058298031

**移动用户端主要界面：**

![用户端](https://images.gitee.com/uploads/images/2020/0306/171925_a7ceb550_5465839.png "屏幕截图.png")
![用户端](https://images.gitee.com/uploads/images/2020/0306/172026_8fdb4e68_5465839.png "屏幕截图.png")
![用户端](https://images.gitee.com/uploads/images/2020/0306/171908_0bdd2177_5465839.png "屏幕截图.png")

**移动骑手端主要界面：**

![骑手端](https://images.gitee.com/uploads/images/2020/0306/171714_d6ed403e_5465839.png "屏幕截图.png")
![骑手端](https://images.gitee.com/uploads/images/2020/0306/171747_d333a38b_5465839.png "屏幕截图.png")

**pc商家管理端主要界面：**

![商家端](https://images.gitee.com/uploads/images/2020/0306/171814_03e922d5_5465839.png "屏幕截图.png")
![商家端](https://images.gitee.com/uploads/images/2020/0306/171846_605cfba4_5465839.png "屏幕截图.png")

