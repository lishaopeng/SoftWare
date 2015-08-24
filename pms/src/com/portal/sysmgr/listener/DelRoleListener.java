/*    */ package com.portal.sysmgr.listener;
/*    */ 
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.event.DelSiteEvent;
/*    */ import com.portal.usermgr.service.RoleService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ import org.springframework.context.ApplicationListener;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class DelRoleListener
/*    */   implements ApplicationListener
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private RoleService roleService;
/*    */ 
/*    */   public void onApplicationEvent(ApplicationEvent baseEvent)
/*    */   {
/* 20 */     if ((baseEvent instanceof DelSiteEvent)) {
/* 21 */       DelSiteEvent event = (DelSiteEvent)baseEvent;
/* 22 */       Site site = event.getSite();
/* 23 */       this.roleService.deleteBySiteId(site.getId());
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.listener.DelRoleListener
 * JD-Core Version:    0.6.1
 */