package cn.gdut.system.controller;

import cn.gdut.common.constant.CommonConstant;
import cn.gdut.common.controller.BaseController;
import cn.gdut.common.utils.AddressUtil;
import cn.gdut.common.utils.HttpContextUtil;
import cn.gdut.common.utils.IpUtil;
import cn.gdut.common.utils.R;
import cn.gdut.system.entity.SysLoginLog;
import cn.gdut.system.entity.SysUser;
import cn.gdut.system.service.LoginLogService;
import cn.gdut.system.service.UserService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class LoginController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    LoginLogService loginLogService;

    @PostMapping("/login")
    public R login(@RequestParam(value = "username",required = false) String username,
                   @RequestParam(value = "password",required = false) String password){
        Subject subject = getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
//            int a = 1/0;
            //获取IP和device等信息
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
            String ipAddr = IpUtil.getIpAddr(request);
            // 记录登录信息
            SysLoginLog sysLoginLog = new SysLoginLog();
            sysLoginLog.setIp(ipAddr);
            sysLoginLog.setLocation(AddressUtil.getAddress(ipAddr));
            sysLoginLog.setCreateTime(new Date());
            // 获取device
            String header = request.getHeader(CommonConstant.USER_AGENT);
            UserAgent userAgent = UserAgent.parseUserAgentString(header);
            // 获取操作系统
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            // 获取浏览器信息
            Browser browser = userAgent.getBrowser();
            sysLoginLog.setDevice(browser + "--" +operatingSystem);
            SysUser sysUser = (SysUser) subject.getPrincipal();
            sysLoginLog.setUserName(sysUser.getUsername());
            loginLogService.saveLog(sysLoginLog);

            return new R<>(this.getToken());

        }catch (Exception e){
            e.printStackTrace();
            return new  R<>(e);
        }
    }

    @GetMapping("/logout")
    public R logout(){
        Subject subject = getSubject();
        subject.logout();
        return new R<>();
    }

}
