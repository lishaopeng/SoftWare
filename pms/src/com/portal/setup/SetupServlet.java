/*    */ package com.portal.setup;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.RequestDispatcher;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class SetupServlet extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*    */     throws ServletException, IOException
/*    */   {
/*    */   }
/*    */ 
/*    */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
/*    */     throws ServletException, IOException
/*    */   {
/* 40 */     String dbHost = request.getParameter("dbHost");
/* 41 */     String dbPort = request.getParameter("dbPort");
/* 42 */     String dbUser = request.getParameter("dbUser");
/* 43 */     String dbPassword = request.getParameter("dbPassword");
/*    */ 
/* 45 */     String domain = request.getParameter("domain");
/* 46 */     String cxtPath = request.getParameter("cxtPath");
/* 47 */     String port = request.getParameter("port");
/*    */ 
/* 49 */     String dbXmlFileName = "/WEB-INF/config/jdbc/jdbc.properties";
/* 50 */     String webXmlFrom = "/setup/config/web.xml";
/* 51 */     String webXmlTo = "/WEB-INF/web.xml";
/*    */     try
/*    */     {
/* 54 */       SetUp.createDb(dbHost, dbPort, dbUser, dbPassword);
/*    */ 
/* 56 */       String sqlPath = getServletContext().getRealPath(
/* 57 */         "/setup/db/javapms.sql");
/* 58 */       String sqls = SetUp.readSql(sqlPath);
/* 59 */       SetUp.createTable(dbHost, dbPort, dbUser, dbPassword, sqls);
/* 60 */       SetUp.updateConfig(dbHost, dbPort, dbUser, dbPassword, domain, 
/* 61 */         cxtPath, port);
/*    */ 
/* 63 */       String dbXmlPath = getServletContext().getRealPath(dbXmlFileName);
/* 64 */       SetUp.dbXml(dbXmlPath, dbHost, dbPort, dbUser, dbPassword);
/*    */ 
/* 66 */       String webXmlFromPath = getServletContext().getRealPath(webXmlFrom);
/* 67 */       String webXmlToPath = getServletContext().getRealPath(webXmlTo);
/* 68 */       SetUp.webXml(webXmlFromPath, webXmlToPath);
/*    */     } catch (Exception e) {
/* 70 */       throw new ServletException("setup failed!", e);
/*    */     }
/* 72 */     RequestDispatcher dispatcher = request
/* 73 */       .getRequestDispatcher("/setup/step3.jsp");
/* 74 */     dispatcher.forward(request, response);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.setup.SetupServlet
 * JD-Core Version:    0.6.1
 */