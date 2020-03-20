package com.supwisdom.datashow.system.controller;

import com.supwisdom.datashow.system.domain.Menu;
import com.supwisdom.datashow.system.domain.Role;
import com.supwisdom.datashow.system.domain.User;
import com.supwisdom.datashow.system.service.LoginService;
import com.supwisdom.datashow.system.service.UserAndRoleService;
import com.supwisdom.datashow.utils.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private String sessionIds;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserAndRoleService userAndRoleService;
//    @Autowired
//    private RedisClient redisClinet;
    @GetMapping("/")
    public String login(ModelMap model){
        return "login";
    }

    /**
     * 用户登陆
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/login/login")
    public String userLogin(HttpServletRequest request,
                            HttpServletResponse response,
                            ModelMap model){
        String ids="";
        String message = "";

        try{
            HttpSession session = request.getSession();
            String loginName = request.getParameter("username");
            String password = request.getParameter("password");
            //判断登陆名在缓存中是否存在，存在则延迟2个小时以后再登陆
//            String existsname = redisClinet.get(loginName);
//            if (existsname!=null && Integer.parseInt(existsname)>2){
//                message="密码错误次数过多，账号被锁定，请和管理员联系!";
//                model.addAttribute("message",message);
//                session.setAttribute("message",message);
//                return "login";
//            }

            //redisClinet.set("ffff","我的测试12345",60);

            User user = loginService.login(loginName, MD5.encodeByMD5(password));
            if (user==null){
                message="账号或密码错误!";
                model.addAttribute("message",message);
                session.setAttribute("message",message);
                //增加缓存次数，过期时间为2小时
//                redisClinet.incr(loginName,60*60*2);
                return "login";
            }else {
                session.setAttribute("ids",user.getIds());
                session.setAttribute("loginName",user.getLoginName());
                session.setAttribute("userName",user.getUserName());
                session.setAttribute("user",user);
                Role roleByUserId = userAndRoleService.getRoleByUserId(user.getIds());
                List<Menu> menuList = userAndRoleService.getMenuByRoleIds_ext(roleByUserId.getIds());
                List<Menu> pMenuList = new ArrayList<>();
                List<Menu> cMenuList = new ArrayList<>();
                for (Menu menuTemp:menuList){
                    if (menuTemp.getType()=='0'){
                        pMenuList.add(menuTemp);
                    }
                    if (menuTemp.getType()=='1'){
                        cMenuList.add(menuTemp);
                    }
                }
                session.setAttribute("menuList",menuList);
                session.setAttribute("pMenuList",pMenuList);
                session.setAttribute("cMenuList",cMenuList);
                model.addAttribute("menuList",menuList);
                model.addAttribute("pMenuList",pMenuList);
                model.addAttribute("cMenuList",cMenuList);
                model.addAttribute("ids",user.getIds());
                model.addAttribute("ip",request.getRemoteAddr());
                //登陆成功，删除缓存中的数据
//                redisClinet.del(loginName);
                return "redirect:/basedata/userPortrait";
            }
        }catch (Exception e){
            e.printStackTrace();
            message="登陆失败，请重试！";
            log.error("用户登陆失败："+ids+"，异常内容："+e.getMessage());
        }
        model.addAttribute("message",message);
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response,ModelMap model){
        try {
            HttpSession session = request.getSession();
            sessionIds = (String) session.getAttribute("ids");
            model.put("ids", sessionIds);
            model.put("ip", request.getRemoteAddr());
            model.put("result", "退出登陆成功！");
            // 清除session
            Enumeration<String> enumeration = request.getSession().getAttributeNames();
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement().toString();
                request.getSession().removeAttribute(key);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
