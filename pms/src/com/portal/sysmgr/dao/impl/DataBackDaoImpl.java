/*    */ package com.portal.sysmgr.dao.impl;
/*    */ 
/*    */ import com.portal.sysmgr.dao.DataBackDao;
/*    */ import java.sql.Connection;
/*    */ import java.sql.DatabaseMetaData;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.sql.DataSource;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ import org.springframework.jdbc.core.support.JdbcDaoSupport;
/*    */ import org.springframework.jdbc.support.rowset.SqlRowSet;
/*    */ 
/*    */ public class DataBackDaoImpl extends JdbcDaoSupport
/*    */   implements DataBackDao
/*    */ {
/*    */   public List<Object[]> getCreateTableData(String tablename)
/*    */   {
/* 19 */     int filedNum = getFieldNum(tablename);
/* 20 */     List results = new ArrayList();
/* 21 */     String sql = "select * from " + tablename;
/* 22 */     SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
/* 23 */     while (set.next()) {
/* 24 */       Object[] oneResult = new Object[filedNum];
/* 25 */       for (int i = 1; i <= filedNum; i++) {
/* 26 */         oneResult[(i - 1)] = set.getObject(i);
/*    */       }
/* 28 */       results.add(oneResult);
/*    */     }
/* 30 */     return results;
/*    */   }
/*    */ 
/*    */   public DatabaseMetaData getMetaData() throws SQLException {
/* 34 */     return getJdbcTemplate().getDataSource().getConnection().getMetaData();
/*    */   }
/*    */ 
/*    */   public String getDefaultCatalog() throws SQLException {
/* 38 */     return getJdbcTemplate().getDataSource().getConnection().getCatalog();
/*    */   }
/*    */ 
/*    */   public Boolean executeSQL(String sqls) {
/*    */     try {
/* 43 */       String[] s = sqls.split(";--end");
/* 44 */       for (String sql : s)
/* 45 */         if (StringUtils.isNotBlank(sql))
/* 46 */           getJdbcTemplate().execute(sql.trim() + ";");
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 50 */       e.printStackTrace();
/* 51 */       return Boolean.valueOf(false);
/*    */     }
/* 53 */     return Boolean.valueOf(true);
/*    */   }
/*    */ 
/*    */   private int getFieldNum(String tablename) {
/* 57 */     int num = 0;
/*    */     try {
/* 59 */       ResultSet rs = getMetaData().getColumns(getDefaultCatalog(), 
/* 60 */         getMetaData().getUserName(), tablename, null);
/* 61 */       while (rs.next()) {
/* 62 */         num++;
/*    */       }
/* 64 */       rs.close();
/*    */     } catch (SQLException e) {
/* 66 */       e.printStackTrace();
/*    */     }
/* 68 */     return num;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.dao.impl.DataBackDaoImpl
 * JD-Core Version:    0.6.1
 */