/*    */ package com.javapms.basic.plugin.springmvc;
/*    */ 
/*    */ import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
/*    */ import org.springframework.web.servlet.view.AbstractUrlBasedView;
/*    */ 
/*    */ public class SimpleFreeMarkerViewResolver extends AbstractTemplateViewResolver
/*    */ {
/*    */   public SimpleFreeMarkerViewResolver()
/*    */   {
/* 19 */     setViewClass(SimpleFreeMarkerView.class);
/*    */   }
/*    */ 
/*    */   protected AbstractUrlBasedView buildView(String viewName)
/*    */     throws Exception
/*    */   {
/* 27 */     AbstractUrlBasedView view = super.buildView(viewName);
/*    */ 
/* 29 */     if (viewName.startsWith("/")) {
/* 30 */       view.setUrl(viewName + getSuffix());
/*    */     }
/* 32 */     return view;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.plugin.springmvc.SimpleFreeMarkerViewResolver
 * JD-Core Version:    0.6.1
 */