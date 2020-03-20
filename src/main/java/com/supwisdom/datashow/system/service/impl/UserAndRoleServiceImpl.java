package com.supwisdom.datashow.system.service.impl;

import com.supwisdom.datashow.system.domain.*;
import com.supwisdom.datashow.system.mapper.UserAndRoleMapper;
import com.supwisdom.datashow.system.service.UserAndRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAndRoleServiceImpl implements UserAndRoleService {
    @Autowired
    private UserAndRoleMapper userAndRoleMapper;

    @Override
    public User getUserByIds(String ids) {
        return userAndRoleMapper.getUserByIds(ids);
    }

    @Override
    public List<Role> getAllRole() {
        return userAndRoleMapper.getAllRole();
    }

    @Override
    public List<MenuZtreeNodes> getAllMenuTreeList() {
        List<MenuZtreeNodes> menuZtreeNodesList = new ArrayList<>();
        List<Menu> menuList = userAndRoleMapper.getAllMenu();
        for (Menu mn:menuList){
            MenuZtreeNodes childNode = new MenuZtreeNodes();
            childNode.setId(Integer.parseInt(mn.getMenuId()));
            childNode.setpId(Integer.parseInt(mn.getpMenuId()));
            childNode.setName(mn.getMenuName());
            childNode.setParent(false);
            menuZtreeNodesList.add(childNode);
        }
        return menuZtreeNodesList;
    }

    @Override
    public Role getRoleByIds(String ids) {
        return userAndRoleMapper.getRoleByIds(ids);
    }

    @Override
    public List<Menu> getMenuByRoleIds(String ids) {
        return userAndRoleMapper.getMenuByRoleIds(ids);
    }

    @Override
    public List<Menu> getMenuByRoleIds_ext(String ids) {
        return userAndRoleMapper.getMenuByRoleIds_ext(ids);
    }

    @Override
    public List<UserAndRole> getUsersWithPage(String loginName, String userName, String status) {
        return userAndRoleMapper.getUsersWithPage(loginName, userName, status);
    }

    @Override
    public Role getRoleByUserId(String ids) {
        return userAndRoleMapper.getRoleByUserId(ids);
    }

    @Override
    public User getUserByLoginName(String loginName,String ids) {
        return userAndRoleMapper.getUserByLoginName(loginName,ids);
    }

    @Override
    public void updatePassword(String newPassword, String ids,String now) {
        userAndRoleMapper.updatePassword(newPassword,ids,now);
    }

    @Override
    public void updateUserToDelete(User user) {
        userAndRoleMapper.updateUserToDelete(user);
    }

    @Override
    public void saveUser(User user) {
        userAndRoleMapper.saveUser(user);
    }

    @Override
    public List<Role> getRolesWithPage(String roleName) {
        return userAndRoleMapper.getRolesWithPage(roleName);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return userAndRoleMapper.getRoleByName(roleName);
    }

    @Override
    public void saveRole(Role role) {
        userAndRoleMapper.saveRole(role);
    }

    @Override
    public void saveRoleMenu(String roleId, String MenuId) {
        userAndRoleMapper.saveRoleMenu(roleId,MenuId);
    }

    @Override
    public void updateRole(Role role) {
        userAndRoleMapper.updateRole(role);
    }

    @Override
    public void deleteRoleMenuByRoleId(String roleId) {
        userAndRoleMapper.deleteRoleMenuByRoleId(roleId);
    }

    @Override
    public void deleteRoleByRoleId(String roleId) {
        userAndRoleMapper.deleteRoleByRoleId(roleId);
    }

    @Override
    public void saveUserRole(String userId, String roleId) {
        userAndRoleMapper.saveUserRole(userId,roleId);
    }

    @Override
    public void updateUser(User user) {
        userAndRoleMapper.updateUser(user);
    }

    @Override
    public String getUserPassword(String ids) {
        return userAndRoleMapper.getUserPassword(ids);
    }

    @Override
    public List<Menu> getMenuBy(String uids, String menuName) {
        return userAndRoleMapper.getMenuBy(uids,menuName);
    }
}
