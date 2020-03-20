package com.supwisdom.datashow.system.service;

import com.supwisdom.datashow.system.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserAndRoleService {

    public User getUserByIds(String ids);

    public List<Role> getAllRole();

    public List<MenuZtreeNodes> getAllMenuTreeList();

    public Role getRoleByIds(String ids);

    public List<Menu> getMenuByRoleIds(String ids);

    public List<Menu> getMenuBy(String uids,String menuName);

    public List<Menu>  getMenuByRoleIds_ext(String ids);

    public List<UserAndRole> getUsersWithPage(String loginName, String userName, String status);

    public Role getRoleByUserId(String ids);

    public User getUserByLoginName(String loginName,String ids);

    public void updatePassword(String newPassword,String ids,String now);

    public void updateUserToDelete(User user);

    public void saveUser(User user);

    public List<Role> getRolesWithPage(String roleName);

    public Role getRoleByName(String roleName);

    public void saveRole(Role role);

    public void saveRoleMenu(String roleId,String MenuId);

    public void updateRole(Role role);

    public void deleteRoleMenuByRoleId(String roleId);

    public void deleteRoleByRoleId(String roleId);

    public void saveUserRole(String userId,String roleId);

    public void updateUser(User user);

    public String getUserPassword(String ids);
}
