/*    */ package com.javapms.basic.plugin.springmvc;
/*    */ 
/*    */ import javax.servlet.ServletContext;
/*    */ import org.springframework.stereotype.Component;
/*    */ import org.springframework.web.context.ServletContextAware;
/*    */ 
/*    */ @Component
/*    */ public class ServletContextRealPathResolver
/*    */   implements RealPathResolver, ServletContextAware
/*    */ {
/*    */   private ServletContext context;
/*    */ 
/*    */   public String get(String path)
/*    */   {
/* 12 */     return this.context.getRealPath(path);
/*    */   }
/*    */ 
/*    */   public void setServletContext(ServletContext servletContext) {
/* 16 */     this.context = servletContext;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.plugin.springmvc.ServletContextRealPathResolver
 * JD-Core Version:    0.6.1
 */