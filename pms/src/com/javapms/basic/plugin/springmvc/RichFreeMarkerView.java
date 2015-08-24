/*    */ package com.javapms.basic.plugin.springmvc;
/*    */ 
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
/*    */ 
/*    */ public class RichFreeMarkerView extends FreeMarkerView
/*    */ {
/*    */   public static final String CONTEXT_PATH = "base";
/*    */ 
/*    */   protected void exposeHelpers(Map model, HttpServletRequest request)
/*    */     throws Exception
/*    */   {
/* 29 */     super.exposeHelpers(model, request);
/* 30 */     model.put("base", request.getContextPath());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.plugin.springmvc.RichFreeMarkerView
 * JD-Core Version:    0.6.1
 */