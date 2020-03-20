package com.supwisdom.datashow.system.mapper;

import com.supwisdom.datashow.system.domain.Menu;
import com.supwisdom.datashow.system.domain.Role;
import com.supwisdom.datashow.system.domain.User;
import com.supwisdom.datashow.system.domain.UserAndRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAndRoleMapper {

    public User getUserByIds(@Param("ids") String ids);

    public List<Role> getAllRole();

    public List<Menu> getAllMenu();

    public Role getRoleByIds(@Param("ids") String ids);

    public List<Menu> getMenuByRoleIds(@Param("ids") String ids);

    public List<Menu> getMenuBy(@Param("uids") String uids,@Param("menuName") String menuName);

    public List<Menu> getMenuByRoleIds_ext(@Param("ids") String ids);

    public List<UserAndRole> getUsersWithPage(@Param("loginName") String loginName, @Param("userName") String userName, @Param("status") String status);

    public Role getRoleByUserId(@Param("ids") String ids);

    public User getUserByLoginName(@Param("loginName") String loginName,@Param("ids") String ids);

    public void updatePassword(@Param("newPassword") String newPassword,@Param("ids") String ids,@Param("now") String now);

    public void updateUserToDelete(User user);

    public void saveUser(User user);

    public List<Role> getRolesWithPage(@Param("roleName") String roleName);

    public Role getRoleByName(@Param("roleName") String roleName);

    public void saveRole(Role role);

    public void saveRoleMenu(@Param("roleId") String roleId,@Param("menuId") String menuId);

    public void updateRole(Role role);

    public void deleteRoleMenuByRoleId(@Param("roleId") String roleId);

    public void deleteRoleByRoleId(@Param("ids") String ids);

    public void saveUserRole(@Param("userId") String userId,@Param("roleId") String roleId);

    public void updateUser(User user);

    public String getUserPassword(@Param("ids")String ids);
}
