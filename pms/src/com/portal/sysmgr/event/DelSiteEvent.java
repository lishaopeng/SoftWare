/*    */ package com.portal.sysmgr.event;
/*    */ 
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class DelSiteEvent extends ApplicationEvent
/*    */ {
/*    */   public DelSiteEvent(Site site)
/*    */   {
/* 18 */     super(site);
/*    */   }
/*    */ 
/*    */   public Site getSite() {
/* 22 */     return (Site)super.getSource();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.event.DelSiteEvent
 * JD-Core Version:    0.6.1
 */