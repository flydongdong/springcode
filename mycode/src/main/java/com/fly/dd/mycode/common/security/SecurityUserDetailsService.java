package com.fly.dd.mycode.common.security;

import com.fly.dd.mycode.common.dao.security.ShiroDao;
import com.fly.dd.mycode.common.entity.security.Permission;
import com.fly.dd.mycode.common.entity.security.Role;
import com.fly.dd.mycode.common.entity.security.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 向Security提供获取用户信息service
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年12月2日 下午4:16:26 $
 */
// @Service("securityUserDetailsService")
public class SecurityUserDetailsService implements UserDetailsService {

    // @Autowired
    private ShiroDao shiroDao;

//    @Autowired
//    HttpServletRequest request;
//    @Autowired
//    HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 使用自带的，如下一个最简单的：
        // List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        // SecurityUser securityUser = new SecurityUser(username, "", authorities);
        // GrantedAuthority权限自定义实现
        User user = shiroDao.getUserByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名未发现");
        }
        List<SecurityGrantedAuthority> authorities = new ArrayList<SecurityGrantedAuthority>();
        List<Permission> permissions = getAllPermissionByUserId(user.getId());
        StringBuilder sb = new StringBuilder("用户名：" + username + "权限为-->");
        for (Permission permission : permissions) {
            authorities.add(new SecurityGrantedAuthority("ROLE_" + permission.getPermission()));
            sb.append(permission.getPermission()).append("、");
        }
        System.out.println(sb.toString());
//        request.setAttribute("longUserName",user.getUsername());
        SecurityUser su = new SecurityUser(username, user.getPassword(), authorities);
        su.setRealname(user.getRealname());
        return su;
    }

    /**
     * 根据用户名获取所有权限
     *
     * @param username
     * @return
     */
    @SuppressWarnings("unused")
    private List<Permission> getAllPermissionByUsername(String username) {
        User user = shiroDao.getUserByLoginName(username);
        return getAllPermissionByUserId(user.getId());
    }

    /**
     * 获取用户所有权限
     *
     * @param userid
     * @return
     */
    private List<Permission> getAllPermissionByUserId(Long userid) {
        List<Role> roles = shiroDao.getUserRoles(userid);
        Set<Long> roleIds = new HashSet<>();// 获取所有的角色ID
        for (Role temp : roles) {
            roleIds.add(temp.getId());
        }
        List<Permission> permissions = shiroDao.getUserPermissionByRoleIds(roleIds.toArray(new Long[] {}));// 获取所有权限

        return permissions;
    }

    public void setShiroDao(ShiroDao shiroDao) {
        this.shiroDao = shiroDao;
    }

//    public void setRequest(HttpServletRequest request) {
//        this.request = request;
//    }
}
