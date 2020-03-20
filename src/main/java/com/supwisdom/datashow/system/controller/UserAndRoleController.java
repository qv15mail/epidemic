package com.supwisdom.datashow.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supwisdom.datashow.system.domain.*;
import com.supwisdom.datashow.system.service.UserAndRoleService;
import com.supwisdom.datashow.utils.DateUtil;
import com.supwisdom.datashow.utils.MD5;
import com.supwisdom.datashow.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class UserAndRoleController {
    private static final Logger log = LoggerFactory.getLogger(UserAndRoleController.class);
    private String sessionIds;
    private String sessionRole;
    //当前时间
    String currnow = DateUtil.getNow();
    @Autowired
    private UserAndRoleService userAndRoleService;
//    @Autowired
//    private RedisClient redisClinet;


    @RequestMapping("/mu_userManager")
    public String userManager (ModelMap map){
        return "system/userManager";
    }
    @RequestMapping("/mu_roleManager")
    public String roleManager (ModelMap map) {
        return "system/roleManager";
    }

    /**
     *获取此用户的用户角色信息
     * @param request
     * @param response
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/showUserForm")
    public Map showUserForm(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(value = "ids",required = false) String ids,
                                 ModelMap model){
        Map map = new HashMap();
        HttpSession session = request.getSession();
        sessionIds = (String)session.getAttribute("ids");
        sessionRole = (String)session.getAttribute("role");
        if (!StringUtil.isEmpty(ids)){
            UserRoleBean userRoleBean = new UserRoleBean();
            User user = userAndRoleService.getUserByIds(ids);
            userRoleBean.setIds(ids);
            userRoleBean.setLoginName(user.getLoginName());
            userRoleBean.setUserName(user.getUserName());
            userRoleBean.setRole(userAndRoleService.getRoleByUserId(ids).getIds());
            map.put("user",userRoleBean);
        }else {
            map.put("user",null);
        }
        List<Role> roleList = userAndRoleService.getAllRole();
        map.put("roleList",roleList);

        return map;
    }

    /**
     * 获取角色及菜单信息
     * @param request
     * @param response
     * @param ids
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/getRoleInfo")
    public Map getRoleInfo(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam(value = "roleid",required = true,defaultValue = "")String ids,
                                ModelMap model){
        Map map = new HashMap();
        try{
            List<MenuZtreeNodes> menus = userAndRoleService.getAllMenuTreeList();
            map.put("menuList",menus);
            if (!StringUtil.isEmpty(ids)){
                Role role = userAndRoleService.getRoleByIds(ids);
                map.put("role",role);
                List<Menu> roleMenus = userAndRoleService.getMenuByRoleIds(ids);
                map.put("roleMenus",roleMenus);
            }else {
                map.put("role",null);
                map.put("roleMenus",null);
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("查询角色信息失败："+e.getMessage());
        }
        return map;
    }

    /**
     * 分页获取所有用户
     * @param request
     * @param response
     * @param loginName
     * @param userName
     * @param status
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("user/getUserList")
    public Map getUserList(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "loginname",required = false)String loginName,
            @RequestParam(value = "username",required = false)String userName,
            @RequestParam(value = "status",required = false,defaultValue = "1")String status,
            @RequestParam(value = "pageNo",required = false,defaultValue = "1")int pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,ModelMap model){
            Map map = new HashMap();
            try {
                PageHelper.startPage(pageNo,pageSize);
                HttpSession session = request.getSession();
                List<UserAndRole> userAndRoleListWithPage = userAndRoleService.getUsersWithPage(loginName,userName,status);
                PageInfo<UserAndRole> pageInfo = new PageInfo<>(userAndRoleListWithPage);

                map.put("PageResult",pageInfo);
            }catch (Exception e){
                e.printStackTrace();
                log.error("查询用户失败："+e.getMessage());
            }
        return map;
    }

    /**
     * 根据登录名重置用户密码
     * @param loginName
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/resetpwd")
    public Map resetpwd(@RequestParam(value = "loginname",required = true)String loginName,
                             HttpServletRequest request,
                             ModelMap model){
        Map map = new HashMap();
        int okFlag = 0;
        String message = "";
        HttpSession session = request.getSession();
        sessionIds=(String)session.getAttribute("ids");
        map.put("ids",sessionIds);
        map.put("ip",request.getRemoteAddr());
        try{
            if (StringUtil.isEmpty(loginName)){
                message = "不合法的操作，用户认证已过期，需要刷新页面";
            }else {
                User user = userAndRoleService.getUserByLoginName(loginName, null);
                if (user == null){
                    message = "该用户不存在";
                }else{
                    String password = MD5.encodeByMD5("666666");
                    userAndRoleService.updatePassword(password,user.getIds(),currnow);
//                    redisClinet.del(loginName);
                    okFlag = 1;
                    message = "用户：" + loginName + "的密码已重置";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "重置密码过程中出现异常";
            log.error("重置密码失败:"+e.getMessage());
        }
        map.put("okFlag",okFlag);
        map.put("message",message);
        map.put("result",message);
        return map;
    }

    /**
     * 删除ids用户
     * @param ids
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/deleteUser")
    public Map deleteUser(@RequestParam(value = "ids",required = true) String ids,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               ModelMap model){
        Map map = new HashMap();
        int okFlag = 0;
        String message ="";
        HttpSession session = request.getSession();
        sessionIds = (String)session.getAttribute("ids");
        map.put("ids",sessionIds);
        map.put("ip",request.getRemoteAddr());
        if (StringUtil.isEmpty(ids)){
            message = "不合法的操作，用户认证已过期，需要刷新页面";
        }else {
            try{
                User user = userAndRoleService.getUserByIds(ids);
                if (user == null){
                    message = "该用户不存在";
                }else {
                    user.setStatus("0");
                    user.setUpdateTime(currnow);
                    userAndRoleService.updateUserToDelete(user);
                    okFlag = 1;
                }
            }catch (Exception e){
                e.printStackTrace();
                message = "删除用户出现异常";
                log.error("删除用户失败："+e.getMessage());
            }
        }
        map.put("okFlag",okFlag);
        map.put("message",message);
        map.put("result",message);
        return map;
    }

    /**
     * 保存用户信息(密码默认为666666)/编辑修改用户信息
     * @param postData
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/saveUserData")
    public Map saveUserData(@RequestBody UserRoleBean postData,HttpServletRequest request,
                                 HttpServletResponse response,ModelMap model){
        Map map = new HashMap();
        HttpSession session = request.getSession();
        sessionIds = (String)session.getAttribute("ids");
        User userByIds = userAndRoleService.getUserByIds(sessionIds);
        map.put("ids",sessionIds);
        map.put("ip",request.getRemoteAddr());
        int okFlag = 0;
        try {
            //存在则修改，否则新增
            if (StringUtil.isEmpty(postData.getIds())){
                //检测登陆名是否存在
                if (userAndRoleService.getUserByLoginName(postData.getLoginName(),null) != null){
                   map.put("okFlag",-1);
                   map.put("result","登陆名已存在");
                   return map;
                }else {
                    User user = new User();
                    user.setIds(UUID.randomUUID().toString().replaceAll("-",""));
                    user.setLoginName(postData.getLoginName());
                    user.setUserName(postData.getUserName());
                    user.setPassword(MD5.encodeByMD5("666666"));
                    user.setDeptCode("");
                    user.setStatus("1");
                    user.setCreateTime(currnow);
                    user.setUpdateTime(currnow);
                    user.setCreator(userByIds.getUserName());
                    userAndRoleService.saveUser(user);
                    userAndRoleService.saveUserRole(user.getIds(),postData.getRole());
                    map.put("result","添加用户["+postData.getLoginName()+"]成功！");
                    okFlag = 1;
                }
            }else {
                User tempUser = userAndRoleService.getUserByIds(postData.getIds());
                if (tempUser == null){
                    map.put("result","用户："+postData.getLoginName()+"，信息修改失败");
                    map.put("okFlag",okFlag);
                    return map;
                }else {
                    User newUser = new User();
                    newUser.setIds(tempUser.getIds());
                    newUser.setLoginName(postData.getLoginName());
                    newUser.setUserName(postData.getUserName());
                    newUser.setPassword(MD5.encodeByMD5("666666"));
                    newUser.setDeptCode("");
                    newUser.setStatus("1");
                    newUser.setUpdateTime(currnow);
                    newUser.setCreator("手动");
                    userAndRoleService.updateUser(newUser);
                    map.put("result", "用户：" + newUser.getLoginName() + "，信息修改完成");
                    okFlag = 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result","添加用户失败！");
            log.error("添加用户失败："+e.getMessage());
        }
        map.put("okFlag",okFlag);
        return map;
    }


    /**
     * 分页获取所有角色信息
     * @param request
     * @param response
     * @param roleName
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/getRoleList")
    public Map getRoleList(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "rolename",required = true,defaultValue = "") String roleName,
            @RequestParam(value = "pageNo",required = false,defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
            ModelMap model){
        Map map = new HashMap();
        try{
           PageHelper.startPage(pageNo,pageSize);
            List<Role> roleListWithPage = userAndRoleService.getRolesWithPage(roleName);
            PageInfo<Role> pageInfo = new PageInfo<>(roleListWithPage);
            map.put("PageResult",pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询角色失败："+e.getMessage());
        }
        return map;
    }

    /**
     * 保存角色信息/编辑修改角色信息
     * @param postData
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/saveRole")
    public Map saveRole(@RequestBody RoleMenuBean postData,
                             HttpServletRequest request,HttpServletResponse response,
                             ModelMap model){
        Map map = new HashMap();
        HttpSession session = request.getSession();
        sessionIds = (String)session.getAttribute("ids");
        map.put("ids",sessionIds);
        map.put("ip",request.getRemoteAddr());
        try{
            String userId = (String)session.getAttribute("ids");
            if (StringUtil.isEmpty(postData.getIds())){
                String roleId;
                roleId = UUID.randomUUID().toString().replaceAll("-","");
                Role roleByName = userAndRoleService.getRoleByName(postData.getRolename());
                if (roleByName==null){
                    Role role = new Role();
                    role.setIds(roleId);
                    role.setRoleName(postData.getRolename());
                    role.setRemark(postData.getRemark());
                    role.setCreateTime(currnow);
                    userAndRoleService.saveRole(role);
                    String[] menuList = postData.getMenuIds();
                    for (String str:menuList){
                        userAndRoleService.saveRoleMenu(roleId,str);
                    }
                    map.put("errStr","");
                }else {
                    map.put("errStr","角色名称已经存在，请修改！");
                    return map;
                }
            }else {
                Role roleByIds = userAndRoleService.getRoleByIds(postData.getIds());
                roleByIds.setRoleName(postData.getRolename());
                roleByIds.setRemark(postData.getRemark());
                roleByIds.setCreateTime(currnow);
                userAndRoleService.updateRole(roleByIds);
                //先把之前角色的菜单信息删除，然后重新添加菜单信息
                userAndRoleService.deleteRoleMenuByRoleId(postData.getIds());
                String[] menuList = postData.getMenuIds();
                for (String str:menuList){
                    userAndRoleService.saveRoleMenu(postData.getIds(),str);
                }
                map.put("errStr","");

            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("errStr","添加角色失败！");
            log.error("添加角色失败："+e.getMessage());
        }
        return map;
    }

    /**
     * 删除角色
     * @param roleId
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/deleteRole")
    public Map deleteRole(
            @RequestParam(value = "roleId",required = true,defaultValue = "") String roleId,
            HttpServletRequest request,HttpServletResponse response,
            ModelMap model){
        Map map = new HashMap();
        String message = "";
        HttpSession session = request.getSession();
        sessionIds =  (String)session.getAttribute("ids");
        map.put("ids",sessionIds);
        map.put("ip",request.getRemoteAddr());
        try {
            userAndRoleService.deleteRoleMenuByRoleId(roleId);
            userAndRoleService.deleteRoleByRoleId(roleId);
        }catch (Exception e){
            e.printStackTrace();
            message = "删除角色出现异常!";
            log.error("删除角色失败："+e.getMessage());
        }
        map.put("message",message);
        return map;
    }

    /**
     * 修改当前用户密码
     * @param oldPassword
     * @param newPassword
     * @param reNewPassword
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/updatePassword")
    public Map updatePassword(
            @RequestParam(value = "oldPassword",required = false,defaultValue = "")String oldPassword,
            @RequestParam(value = "newPassword",required = false,defaultValue = "")String newPassword,
            @RequestParam(value = "reNewPassword",required = false,defaultValue = "")String reNewPassword,
            HttpServletRequest request,ModelMap model){
        Map map = new HashMap();
        HttpSession session = request.getSession();
        sessionIds = (String)session.getAttribute("ids");
        map.put("ids",sessionIds);
        map.put("ip",request.getRemoteAddr());
        int okFlag = 1;
        try {
            if (sessionIds !=null && !("".equals(sessionIds))) {
                String oldPasswordByMD5 = MD5.encodeByMD5(oldPassword);
                String userPassword = userAndRoleService.getUserPassword(sessionIds);
                map.put("okFlag",okFlag);
                if (!oldPasswordByMD5.equals(userPassword)){
                    map.put("msg","当前密码错误，请填写正确的密码！");
                    return map;
                }else {
                    if (StringUtil.isEmpty(newPassword)){
                        map.put("msg","新密码不可为空，请输入新密码！");
                        return map;
                    }else {
                        if (StringUtil.isEmpty(reNewPassword)){
                            map.put("msg","确认密码不可为空，请输入确认密码！");
                            return map;
                        }else {
                            if (!newPassword.equals(reNewPassword)){
                                map.put("msg","两次输入密码不一致，请重新输入！");
                                return map;
                            }else {
                                String newPasswordByMD5 = MD5.encodeByMD5(newPassword);
                                if (newPasswordByMD5.equals(oldPasswordByMD5)){
                                    map.put("msg","新密码不能与当前密码一致，请重新输入！");
                                    return map;
                                }else {
                                    if (newPassword.length()>32){
                                        map.put("msg","新密码太长，不能超过32个字符，请重新输入！");
                                        return map;
                                    }else {
                                        userAndRoleService.updatePassword(newPasswordByMD5,sessionIds,currnow);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            map.put("result","修改密码成功！");
            okFlag = 0;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result","修改密码失败！");
            log.error("修改密码失败："+e.getMessage());
        }
        map.put("okFlag",okFlag);
        return map;
    }


}
