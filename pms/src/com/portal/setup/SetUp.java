/*     */ package com.portal.setup;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.Statement;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class SetUp
/*     */ {
/*  18 */   private static String TABLE_NAME = "javapmsv13";
/*     */ 
/*     */   public static void dbXml(String fileName, String dbHost, String dbPort, String dbUser, String dbPassword) throws Exception
/*     */   {
/*  22 */     String s = FileUtils.readFileToString(new File(fileName));
/*  23 */     s = StringUtils.replace(s, "DB_HOST", dbHost);
/*  24 */     s = StringUtils.replace(s, "DB_PORT", dbPort);
/*  25 */     s = StringUtils.replace(s, "DB_NAME", TABLE_NAME);
/*  26 */     s = StringUtils.replace(s, "DB_USER", dbUser);
/*  27 */     s = StringUtils.replace(s, "DB_PASSWORD", dbPassword);
/*  28 */     FileUtils.writeStringToFile(new File(fileName), s);
/*     */   }
/*     */ 
/*     */   public static Connection getConn(String dbHost, String dbPort, String dbUser, String dbPassword) throws Exception
/*     */   {
/*  33 */     Class.forName("com.mysql.jdbc.Driver");
/*  34 */     Class.forName("com.mysql.jdbc.Driver").newInstance();
/*  35 */     String connStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + 
/*  36 */       TABLE_NAME + "?user=" + dbUser + "&password=" + dbPassword + 
/*  37 */       "&characterEncoding=utf8";
/*  38 */     Connection conn = DriverManager.getConnection(connStr);
/*  39 */     return conn;
/*     */   }
/*     */ 
/*     */   public static void webXml(String fromFile, String toFile) throws Exception {
/*  43 */     FileUtils.copyFile(new File(fromFile), new File(toFile));
/*     */   }
/*     */ 
/*     */   public static void createDb(String dbHost, String dbPort, String dbUser, String dbPassword) throws Exception
/*     */   {
/*  48 */     Class.forName("com.mysql.jdbc.Driver");
/*  49 */     Class.forName("com.mysql.jdbc.Driver").newInstance();
/*  50 */     String connStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "?user=" + 
/*  51 */       dbUser + "&password=" + dbPassword + 
/*  52 */       "&characterEncoding=UTF8";
/*  53 */     Connection conn = DriverManager.getConnection(connStr);
/*  54 */     Statement stat = conn.createStatement();
/*  55 */     String sql = "drop database if exists " + TABLE_NAME;
/*  56 */     stat.execute(sql);
/*  57 */     sql = "create database " + TABLE_NAME + " CHARACTER SET UTF8";
/*  58 */     stat.execute(sql);
/*  59 */     sql = "use " + TABLE_NAME;
/*  60 */     stat.execute(sql);
/*  61 */     stat.close();
/*  62 */     conn.close();
/*     */   }
/*     */ 
/*     */   public static void createTable(String dbHost, String dbPort, String dbUser, String dbPassword, String sqls) throws Exception
/*     */   {
/*  67 */     Connection conn = getConn(dbHost, dbPort, dbUser, dbPassword);
/*  68 */     Statement stat = conn.createStatement();
/*  69 */     String[] s = sqls.split(";--end");
/*  70 */     for (String sql : s) {
/*  71 */       if (!StringUtils.isBlank(sql)) {
/*  72 */         stat.execute(sql);
/*     */       }
/*     */     }
/*  75 */     stat.close();
/*  76 */     conn.close();
/*     */   }
/*     */ 
/*     */   public static void updateConfig(String dbHost, String dbPort, String dbUser, String dbPassword, String domain, String cxtPath, String port)
/*     */     throws Exception
/*     */   {
/*  82 */     Connection conn = getConn(dbHost, dbPort, dbUser, dbPassword);
/*  83 */     Statement stat = conn.createStatement();
/*  84 */     String sql = "update tq_site set domain='" + domain + 
/*  85 */       "',context_path='" + cxtPath + "',port=" + port;
/*  86 */     stat.executeUpdate(sql);
/*  87 */     stat.close();
/*  88 */     conn.close();
/*     */   }
/*     */ 
/*     */   public static String readSql(String fileName) throws Exception {
/*  92 */     BufferedReader br = new BufferedReader(new InputStreamReader(
/*  93 */       new FileInputStream(fileName), "UTF-8"));
/*  94 */     StringBuilder sqlSb = new StringBuilder();
/*  95 */     String s = null;
/*  96 */     while ((s = br.readLine()) != null) {
/*  97 */       sqlSb.append(s);
/*  98 */       sqlSb.append("\r\n");
/*     */     }
/* 100 */     br.close();
/* 101 */     return sqlSb.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.setup.SetUp
 * JD-Core Version:    0.6.1
 */