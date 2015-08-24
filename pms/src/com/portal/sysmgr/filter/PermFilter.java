/*    */ package com.portal.sysmgr.filter;
/*    */ 
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import org.apache.shiro.authc.AuthenticationToken;
/*    */ import org.apache.shiro.subject.Subject;
/*    */ import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
/*    */ 
/*    */ public class PermFilter extends FormAuthenticationFilter
/*    */ {
/*    */   protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
/*    */   {
/* 21 */     boolean isAllowed = super.isAccessAllowed(request, response, 
/* 22 */       mappedValue);
/* 23 */     if ((isAllowed) && (isLoginRequest(request, response))) {
/*    */       try {
/* 25 */         issueSuccessRedirect(request, response);
/*    */       } catch (Exception localException) {
/*    */       }
/* 28 */       return false;
/*    */     }
/* 30 */     return isAllowed;
/*    */   }
/*    */ 
/*    */   protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response)
/*    */     throws Exception
/*    */   {
/* 37 */     return super.onLoginSuccess(token, subject, request, response);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.filter.PermFilter
 * JD-Core Version:    0.6.1
 */