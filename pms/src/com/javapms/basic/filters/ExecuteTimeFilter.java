/*    */ package com.javapms.basic.filters;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class ExecuteTimeFilter
/*    */   implements Filter
/*    */ {
/* 24 */   protected final Logger log = LoggerFactory.getLogger(ExecuteTimeFilter.class);
/*    */   public static final String START_TIME = "_start_time";
/*    */ 
/*    */   public void destroy()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
/*    */     throws IOException, ServletException
/*    */   {
/* 35 */     HttpServletRequest request = (HttpServletRequest)req;
/* 36 */     long time = System.currentTimeMillis();
/* 37 */     request.setAttribute("_start_time", Long.valueOf(time));
/* 38 */     chain.doFilter(request, response);
/* 39 */     time = System.currentTimeMillis() - time;
/*    */ 
/* 42 */     this.log.debug("process in {} ms: {}", Long.valueOf(time), request.getRequestURI());
/*    */   }
/*    */ 
/*    */   public void init(FilterConfig arg0)
/*    */     throws ServletException
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.filters.ExecuteTimeFilter
 * JD-Core Version:    0.6.1
 */