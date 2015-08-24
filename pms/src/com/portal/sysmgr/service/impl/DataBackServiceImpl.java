/*     */ package com.portal.sysmgr.service.impl;
/*     */ 
/*     */ import com.javapms.basic.utils.StringBeanUtils;
/*     */ import com.portal.sysmgr.dao.DataBackDao;
/*     */ import com.portal.sysmgr.service.DataBackService;
/*     */ import java.sql.DatabaseMetaData;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class DataBackServiceImpl
/*     */   implements DataBackService
/*     */ {
/*     */   private DataBackDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Object[]> getCreateTableData(String tablename)
/*     */   {
/*  26 */     return this.dao.getCreateTableData(tablename);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public String getDefaultCatalog() throws SQLException {
/*  31 */     return this.dao.getDefaultCatalog();
/*     */   }
/*     */ 
/*     */   public String getWholeTableSql(String tablename) {
/*  35 */     StringBuffer sb = new StringBuffer();
/*     */     try
/*     */     {
/*  38 */       sb.append(getCreateSQL(tablename));
/*     */     } catch (SQLException e) {
/*  40 */       e.printStackTrace();
/*     */     }
/*  42 */     List results = getCreateTableData(tablename);
/*  43 */     for (int i = 0; i < results.size(); i++) {
/*  44 */       Object[] oneResult = (Object[])results.get(i);
/*  45 */       sb.append(getInsertSql(oneResult, tablename));
/*     */     }
/*  47 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public List<String> getTables()
/*     */     throws SQLException
/*     */   {
/*  57 */     DatabaseMetaData d = this.dao.getMetaData();
/*  58 */     String c = this.dao.getDefaultCatalog();
/*  59 */     ResultSet rs = d.getTables(c, d.getUserName(), "tq_%", 
/*  60 */       new String[] { "TABLE" });
/*  61 */     List tables = new ArrayList();
/*     */     try {
/*  63 */       while (rs.next())
/*  64 */         tables.add(rs.getString("TABLE_NAME"));
/*     */     }
/*     */     catch (SQLException e) {
/*  67 */       e.printStackTrace();
/*     */     } finally {
/*  69 */       rs.close();
/*     */     }
/*  71 */     return tables;
/*     */   }
/*     */ 
/*     */   public String getCreateSQL(String table) throws SQLException {
/*  75 */     DatabaseMetaData d = this.dao.getMetaData();
/*  76 */     String c = this.dao.getDefaultCatalog();
/*  77 */     ResultSet rs = d.getColumns(c, d.getUserName(), table, null);
/*  78 */     StringBuffer sb = new StringBuffer("");
/*  79 */     sb.append("DROP TABLE IF EXISTS ");
/*  80 */     sb.append(table);
/*  81 */     sb.append(";--end");
/*  82 */     sb.append("\r\n");
/*  83 */     sb.append("CREATE TABLE ");
/*  84 */     sb.append(table);
/*  85 */     sb.append("\r\n");
/*  86 */     sb.append("(");
/*  87 */     sb.append("\r\n");
/*     */     try {
/*  89 */       label332: while (rs.next()) {
/*  90 */         sb.append("   ");
/*  91 */         sb.append(rs.getString("COLUMN_NAME"));
/*  92 */         sb.append("           ");
/*  93 */         sb.append(rs.getString("TYPE_NAME"));
/*  94 */         if ((rs.getString("TYPE_NAME").indexOf("DATE") == -1) && 
/*  95 */           (rs.getString("TYPE_NAME").indexOf("TIME") == -1) && 
/*  96 */           (rs.getString("TYPE_NAME").indexOf("TEXT") == -1) && 
/*  97 */           (StringUtils.isNotBlank(rs.getString("COLUMN_SIZE")))) {
/*  98 */           sb.append("(");
/*  99 */           if (StringUtils.isNotBlank(rs
/* 100 */             .getString("DECIMAL_DIGITS")))
/* 101 */             if (!rs.getString("DECIMAL_DIGITS").equals("0")) {
/* 102 */               sb.append(rs.getString("COLUMN_SIZE"));
/* 103 */               sb.append(",");
/* 104 */               sb.append(rs.getString("DECIMAL_DIGITS")); break label332;
/*     */             }
/* 106 */           sb.append(rs.getString("COLUMN_SIZE"));
/*     */ 
/* 108 */           sb.append(")");
/*     */         }
/*     */ 
/* 111 */         if (rs.getString("NULLABLE").equals("0")) {
/* 112 */           sb.append(" NOT NULL");
/*     */         }
/* 114 */         if (StringUtils.isNotBlank(rs.getString("COLUMN_DEF"))) {
/* 115 */           sb.append(" DEFAULT ");
/* 116 */           if (rs.getString("TYPE_NAME").indexOf("BIT") == -1) {
/* 117 */             sb.append("'");
/*     */           }
/* 119 */           sb.append(rs.getString("COLUMN_DEF"));
/* 120 */           if (rs.getString("TYPE_NAME").indexOf("BIT") == -1) {
/* 121 */             sb.append("'");
/*     */           }
/*     */         }
/* 124 */         if (rs.getString("IS_AUTOINCREMENT").equals("YES")) {
/* 125 */           sb.append(" AUTO_INCREMENT");
/*     */         }
/* 127 */         if (StringUtils.isNotBlank(rs.getString("REMARKS"))) {
/* 128 */           sb.append(" COMMENT ");
/* 129 */           sb.append("'");
/* 130 */           sb.append(rs.getString("REMARKS"));
/* 131 */           sb.append("'");
/*     */         }
/* 133 */         if (!rs.isLast()) {
/* 134 */           sb.append(",");
/* 135 */           sb.append("\r\n");
/*     */         }
/*     */       }
/* 138 */       String sp = getPrimaryKeys(table, d, c);
/* 139 */       if (StringUtils.isNotBlank(sp)) {
/* 140 */         sb.append(",");
/* 141 */         sb.append("\r\n");
/* 142 */         sb.append(sp);
/*     */       } else {
/* 144 */         sb.append("\r\n");
/*     */       }
/* 146 */       sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8");
/* 147 */       sb.append(";--end");
/* 148 */       sb.append("\r\n");
/*     */     } catch (SQLException localSQLException) {
/*     */     } finally {
/* 151 */       rs.close();
/*     */     }
/* 153 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private String getPrimaryKeys(String tablename, DatabaseMetaData d, String c)
/*     */     throws SQLException
/*     */   {
/* 167 */     ResultSet rs = d.getPrimaryKeys(c, d.getUserName(), tablename);
/* 168 */     StringBuffer sb = new StringBuffer("");
/* 169 */     boolean b = false;
/*     */     try {
/* 171 */       sb.append("   PRIMARY KEY");
/* 172 */       sb.append("(");
/* 173 */       while (rs.next()) {
/* 174 */         sb.append(rs.getString("COLUMN_NAME"));
/* 175 */         sb.append(",");
/* 176 */         b = true;
/*     */       }
/* 178 */       if (!b) {
/* 179 */         rs.close();
/* 180 */         return "";
/*     */       }
/* 182 */       sb.deleteCharAt(sb.lastIndexOf(","));
/* 183 */       sb.append(")");
/* 184 */       sb.append("\r\n");
/*     */     } catch (SQLException localSQLException) {
/*     */     } finally {
/* 187 */       rs.close();
/*     */     }
/* 189 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public String getAllExportedKeys(String tableName)
/*     */     throws SQLException
/*     */   {
/* 198 */     DatabaseMetaData d = this.dao.getMetaData();
/* 199 */     String c = this.dao.getDefaultCatalog();
/* 200 */     ResultSet rs = d.getImportedKeys(c, d.getUserName(), tableName);
/* 201 */     StringBuffer sb = new StringBuffer("");
/*     */     try {
/* 203 */       while (rs.next()) {
/* 204 */         sb.append("ALTER TABLE ");
/* 205 */         sb.append(rs.getString("FKTABLE_NAME"));
/* 206 */         sb.append(" ADD CONSTRAINT ");
/* 207 */         sb.append(rs.getString("FK_NAME"));
/* 208 */         sb.append(" FOREIGN KEY ");
/* 209 */         sb.append("(");
/* 210 */         sb.append(rs.getString("FKCOLUMN_NAME"));
/* 211 */         sb.append(")");
/* 212 */         sb.append(" REFERENCES ");
/* 213 */         sb.append(rs.getString("PKTABLE_NAME"));
/* 214 */         sb.append(" (");
/* 215 */         sb.append(rs.getString("PKCOLUMN_NAME"));
/* 216 */         sb.append(")");
/* 217 */         sb.append(";--end");
/* 218 */         sb.append("\r\n");
/*     */       }
/*     */     } catch (SQLException e) {
/* 221 */       e.printStackTrace();
/*     */     } finally {
/* 223 */       rs.close();
/*     */     }
/* 225 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public String getInsertSql(Object[] o, String tablename) {
/* 229 */     StringBuffer sb = new StringBuffer();
/* 230 */     sb.append(" INSERT INTO ");
/* 231 */     sb.append(tablename);
/* 232 */     sb.append(" VALUES(");
/* 233 */     for (int i = 0; i < o.length; i++) {
/* 234 */       if (o[i] != null) {
/* 235 */         if ((o[i] instanceof Date))
/* 236 */           sb.append("'" + o[i] + "'");
/* 237 */         else if ((o[i] instanceof String))
/* 238 */           sb.append("'" + 
/* 239 */             StringBeanUtils.replaceKeyString((String)o[i]) + 
/* 240 */             "'");
/* 241 */         else if ((o[i] instanceof Boolean)) {
/* 242 */           if (((Boolean)o[i]).booleanValue())
/* 243 */             sb.append(1);
/*     */           else
/* 245 */             sb.append(0);
/*     */         }
/*     */         else
/* 248 */           sb.append(o[i]);
/*     */       }
/*     */       else {
/* 251 */         sb.append(o[i]);
/*     */       }
/* 253 */       sb.append(",");
/*     */     }
/* 255 */     sb = sb.deleteCharAt(sb.lastIndexOf(","));
/* 256 */     sb.append(")");
/* 257 */     sb.append(";--end");
/* 258 */     sb.append("\r\n");
/* 259 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public Boolean executeSQL(String sql) {
/* 263 */     return this.dao.executeSQL(sql);
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(DataBackDao dao)
/*     */   {
/* 270 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.service.impl.DataBackServiceImpl
 * JD-Core Version:    0.6.1
 */