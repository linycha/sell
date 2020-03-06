package com.sell.modules.sys.controller;

import com.sell.config.WechatMpConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author linyuc
 * @date 2020/2/28 11:01
 */
@RestController
@RequestMapping("/wechat")
public class WeChatController {
    @Autowired
    private WechatMpConfig wechatMpConfig;
    @RequestMapping("test")
    public String test(){
        return "this is test";
    }
    @RequestMapping("check")
    public String check(HttpServletRequest req){
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        System.out.println(wechatMpConfig.wxMpService().getWxMpConfigStorage().getToken());
        System.out.println(wechatMpConfig.wxMpService().getWxMpConfigStorage().getToken());
        //校验成功将随机字符串返回
        if(wechatMpConfig.wxMpService().checkSignature(timestamp,nonce, signature)) {
            System.out.println("接入成功");
            return echostr;
        }
        return "接入失败";
    }
    /*@GetMapping("/authorize")
    public String authorize(String returnUrl){
        //配置，调用方法
        String url = "https://liny.mynatapp.cc/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO,returnUrl);
        System.out.println(redirectUrl);
        System.out.println(redirectUrl);
        return "redirect:"+ redirectUrl;
    }
    @GetMapping("userInfo")
    public String userInfo(String code,String state){
        WxMpOAuth2AccessToken accessToken = new WxMpOAuth2AccessToken();
        try {
            accessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            System.out.println("微信网页授权异常");
            e.printStackTrace();
        }
        String openId = accessToken.getOpenId();
        System.out.println(openId);
        return "redirect:" + state + "?openid="+openId;
    }*/
}
