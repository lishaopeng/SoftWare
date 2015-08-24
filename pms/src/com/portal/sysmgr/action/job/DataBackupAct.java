/*    */ package com.portal.sysmgr.action.job;
/*    */ 
/*    */ import com.javapms.basic.plugin.springmvc.RealPathResolver;
/*    */ import com.javapms.basic.utils.DateUtils;
/*    */ import com.portal.sysmgr.entity.DatabaseConfig;
/*    */ import com.portal.sysmgr.service.DataBackService;
/*    */ import com.portal.sysmgr.service.DatabaseConfigService;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class DataBackupAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private DatabaseConfigService configService;
/*    */ 
/*    */   @Autowired
/*    */   private DataBackService dataBackService;
/*    */ 
/*    */   @Autowired
/*    */   private RealPathResolver realPathResolver;
/*    */ 
/*    */   public void dataBackupJob()
/*    */   {
/* 24 */     DatabaseConfig config = this.configService.findUnique();
/* 25 */     if (config != null)
/*    */     {
/* 27 */       if ((DateUtils.daysBetween(new Date(), config.getPreTime()) > config
/* 27 */         .getInter().intValue()) && 
/* 28 */         (DateUtils.getSameHourTime(config.getBackupTime()).booleanValue()))
/*    */         try {
/* 30 */           backupdata();
/*    */         } catch (SQLException e) {
/* 32 */           e.printStackTrace();
/*    */         }
/*    */     }
/*    */   }
/*    */ 
/*    */   private void backupdata()
/*    */     throws SQLException
/*    */   {
/* 40 */     String backpath = this.realPathResolver.get("backup");
/* 41 */     File backDirectory = new File(backpath);
/* 42 */     if (!backDirectory.exists()) {
/* 43 */       backDirectory.mkdir();
/*    */     }
/* 45 */     List tables = this.dataBackService.getTables();
/* 46 */     String backFilePath = backpath + "/" + 
/* 47 */       DateUtils.getNowToString() + ".sql";
/* 48 */     File file = new File(backFilePath);
/*    */ 
/* 50 */     OutputStreamWriter writer = null;
/*    */     try {
/* 52 */       FileOutputStream out = new FileOutputStream(file);
/* 53 */       writer = new OutputStreamWriter(out, "utf8");
/* 54 */       writer.write("SET FOREIGN_KEY_CHECKS = 0");
/* 55 */       writer.write(";--end\r\n");
/* 56 */       for (int i = 0; i < tables.size(); i++) {
/* 57 */         backupTable(writer, (String)tables.get(i));
/*    */       }
/* 59 */       for (int i = 0; i < tables.size(); i++) {
/*    */         try {
/* 61 */           String sf = this.dataBackService.getAllExportedKeys(
/* 62 */             (String)tables
/* 62 */             .get(i));
/* 63 */           if (StringUtils.isNotBlank(sf))
/* 64 */             writer.write(sf);
/*    */         }
/*    */         catch (SQLException e) {
/* 67 */           e.printStackTrace();
/*    */         }
/* 69 */         writer.flush();
/*    */       }
/* 71 */       writer.write("SET FOREIGN_KEY_CHECKS = 1");
/* 72 */       writer.write(";--end\r\n");
/* 73 */       writer.close();
/* 74 */       out.close();
/*    */     } catch (IOException e) {
/* 76 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   private String backupTable(OutputStreamWriter writer, String tablename) throws IOException
/*    */   {
/* 82 */     writer.write(this.dataBackService.getWholeTableSql(tablename));
/* 83 */     writer.flush();
/* 84 */     return tablename;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.job.DataBackupAct
 * JD-Core Version:    0.6.1
 */