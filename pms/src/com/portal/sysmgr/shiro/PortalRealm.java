/*    */ package com.portal.sysmgr.shiro;
/*    */ 
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import com.portal.usermgr.entity.Admin;
/*    */ import com.portal.usermgr.entity.User;
/*    */ import com.portal.usermgr.service.UserService;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.apache.shiro.authc.AuthenticationException;
/*    */ import org.apache.shiro.authc.AuthenticationInfo;
/*    */ import org.apache.shiro.authc.AuthenticationToken;
/*    */ import org.apache.shiro.authc.SimpleAuthenticationInfo;
/*    */ import org.apache.shiro.authc.UsernamePasswordToken;
/*    */ import org.apache.shiro.authz.AuthorizationInfo;
/*    */ import org.apache.shiro.authz.SimpleAuthorizationInfo;
/*    */ import org.apache.shiro.realm.AuthorizingRealm;
/*    */ import org.apache.shiro.subject.PrincipalCollection;
/*    */ import org.apache.shiro.util.CollectionUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class PortalRealm extends AuthorizingRealm
/*    */ {
/*    */   private UserService userService;
/*    */ 
/*    */   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
/*    */     throws AuthenticationException
/*    */   {
/* 34 */     UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
/* 35 */     User user = this.userService.findByUsername(token.getUsername());
/* 36 */     if (user != null) {
/* 37 */       return new SimpleAuthenticationInfo(user.getUsername(), 
/* 38 */         user.getPassword(), getName());
/*    */     }
/* 40 */     return null;
/*    */   }
/*    */ 
/*    */   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
/*    */   {
/* 45 */     String username = 
/* 46 */       (String)principals.fromRealm(getName()).iterator()
/* 46 */       .next();
/* 47 */     if (!StringUtils.isBlank(username)) {
/* 48 */       User user = this.userService.findByUsername(username);
/* 49 */       Site site = ContextTools.getSite();
/* 50 */       if ((user != null) && (site != null)) {
/* 51 */         SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
/* 52 */         if (user.getAdmin() != null) {
/* 53 */           Set perms = user.getAdmin().getPerms(site.getId());
/* 54 */           if (!CollectionUtils.isEmpty(perms)) {
/* 55 */             auth.setStringPermissions(perms);
/*    */           }
/* 57 */           return auth;
/*    */         }
/* 59 */         auth.setObjectPermissions(null);
/* 60 */         return auth;
/*    */       }
/*    */     }
/*    */ 
/* 64 */     return null;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setUserService(UserService userService)
/*    */   {
/* 71 */     this.userService = userService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.shiro.PortalRealm
 * JD-Core Version:    0.6.1
 */