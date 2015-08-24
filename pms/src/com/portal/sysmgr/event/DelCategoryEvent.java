/*    */ package com.portal.sysmgr.event;
/*    */ 
/*    */ import com.portal.extrafunc.entity.Category;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class DelCategoryEvent extends ApplicationEvent
/*    */ {
/*    */   public DelCategoryEvent(Category c)
/*    */   {
/* 18 */     super(c);
/*    */   }
/*    */ 
/*    */   public Category getCategory() {
/* 22 */     return (Category)super.getSource();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.event.DelCategoryEvent
 * JD-Core Version:    0.6.1
 */