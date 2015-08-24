/*    */ package com.javapms.basic.plugin.springmvc;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.springframework.web.bind.WebDataBinder;
/*    */ import org.springframework.web.bind.support.WebBindingInitializer;
/*    */ import org.springframework.web.context.request.WebRequest;
/*    */ 
/*    */ public class BindingInitializer
/*    */   implements WebBindingInitializer
/*    */ {
/*    */   public void initBinder(WebDataBinder binder, WebRequest request)
/*    */   {
/* 20 */     binder.registerCustomEditor(Date.class, new DateTypeEditor());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.plugin.springmvc.BindingInitializer
 * JD-Core Version:    0.6.1
 */