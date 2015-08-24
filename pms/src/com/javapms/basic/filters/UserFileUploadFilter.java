/*    */ package com.javapms.basic.filters;
/*    */ 
/*    */ import com.ckfinder.connector.FileUploadFilter;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ 
/*    */ public class UserFileUploadFilter extends FileUploadFilter
/*    */ {
/*    */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
/*    */     throws IOException, ServletException
/*    */   {
/* 19 */     super.doFilter(request, response, chain);
/* 20 */     chain.doFilter(request, response);
/*    */   }
/*    */ 
/*    */   public void init(FilterConfig fConfig) throws ServletException {
/* 24 */     super.init(fConfig);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.filters.UserFileUploadFilter
 * JD-Core Version:    0.6.1
 */