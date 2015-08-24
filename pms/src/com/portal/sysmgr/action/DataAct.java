/*     */ package com.portal.sysmgr.action;
/*     */ 
/*     */ /*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.sql.SQLException;
/*     */ import java.util.List;

/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;

import com.javapms.basic.plugin.springmvc.RealPathResolver;
/*     */ import com.javapms.basic.utils.DateUtils;
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.sysmgr.service.DataBackService;
/*     */ import com.portal.sysmgr.service.ResourceService;
/*     */ 
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/data"})
/*     */ public class DataAct
/*     */ {
/*  34 */   private static String BR = "\r\n";
/*     */   private static String backup_table;
/*     */   private static String percent;
/*     */ 
/*     */   @Autowired
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   @Autowired
/*     */   private DataBackService dataBackService;
/*     */ 
/*     */   @Autowired
/*     */   private ResourceService resourceService;
/*     */ 
/*     */   @RequestMapping({"/v_revert.do"})
/*     */   public String listDataBases(ModelMap model, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*     */     try
/*     */     {
/*  42 */       String defaultCatalog = this.dataBackService.getDefaultCatalog();
/*  43 */       model.addAttribute("defaultCatalog", defaultCatalog);
/*     */     } catch (SQLException e) {
/*  45 */       System.out.println("");
/*     */     }
/*  47 */     model.addAttribute("dbfiles", 
/*  48 */       this.resourceService.listFile("backup", "", false));
/*  49 */     return "dataCenter/dbback/revert";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/o_revert.do"})
/*     */   public void revert(String filename, ModelMap model, HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException, JSONException, SQLException
/*     */   {
/*  56 */     JSONObject json = new JSONObject();
/*  57 */     String backpath = this.realPathResolver.get("backup");
/*  58 */     String backFilePath = backpath + "/" + filename;
/*  59 */     String sql = readFile(backFilePath);
/*  60 */     this.dataBackService.executeSQL("use " + this.dataBackService.getDefaultCatalog() + 
/*  61 */       ";" + BR);
/*  62 */     this.dataBackService.executeSQL(sql);
/*  63 */     json.put("status", 1);
/*  64 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/v_backup.do "})
/*     */   public String backup(String[] tableNames, ModelMap model, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*  70 */     return "dataCenter/dbback/backup";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/o_backup.do"})
/*     */   public void backupsubmit(ModelMap model, HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException, InterruptedException, JSONException
/*     */   {
/*  77 */     JSONObject json = new JSONObject();
/*  78 */     String backpath = this.realPathResolver.get("backup");
/*  79 */     File backDirectory = new File(backpath);
/*  80 */     if (!backDirectory.exists())
/*  81 */       backDirectory.mkdir();
/*     */     try
/*     */     {
			/* 84 */List<String> tables = this.dataBackService.getTables();
/*  85 */       String backFilePath = backpath + "/" + 
/*  86 */         DateUtils.getNowToString() + ".sql";
/*  87 */       File file = new File(backFilePath);
/*  88 */       Thread thread = new DateBackupTableThread(file, tables);
/*  89 */       thread.start();
/*     */     } catch (SQLException e) {
/*  91 */       e.printStackTrace();
/*     */     }
/*  93 */     json.put("status", 1);
/*  94 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/o_backup_progress.do"})
/*     */   public void getBackupProgress(HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 100 */     JSONObject json = new JSONObject();
/* 101 */     json.put("tablename", backup_table);
/* 102 */     json.put("percent", percent);
/* 103 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   private String readFile(String filename) throws IOException {
/* 107 */     if (StringUtils.isBlank(filename)) {
/* 108 */       throw new NullPointerException("文件名为空");
/*     */     }
/* 110 */     StringBuffer sb = new StringBuffer();
/* 111 */     FileInputStream file = new FileInputStream(filename);
/* 112 */     InputStreamReader isr = new InputStreamReader(file, "utf8");
/* 113 */     BufferedReader br = new BufferedReader(isr);
/* 114 */     String line = null;
/* 115 */     while ((line = br.readLine()) != null) {
/* 116 */       sb.append(line);
/* 117 */       sb.append(BR);
/*     */     }
/* 119 */     file.close();
/* 120 */     isr.close();
/* 121 */     br.close();
/* 122 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private class DateBackupTableThread extends Thread {
/*     */     private File file;
/*     */     private List<String> tablenames;
/*     */ 
		/*     */public DateBackupTableThread(File file, List<String> tablenames) {
/* 131 */       this.file = file;
/* 132 */       this.tablenames = tablenames;
/*     */     }
/*     */ 
/*     */     @Override
public void run()
/*     */     {
/* 137 */       OutputStreamWriter writer = null;
/*     */       try {
/* 139 */         FileOutputStream out = new FileOutputStream(this.file);
/* 140 */         writer = new OutputStreamWriter(out, "utf8");
/* 141 */         writer.write("SET FOREIGN_KEY_CHECKS = 0");
/* 142 */         writer.write(";--end" + DataAct.BR);
/* 143 */         for (int i = 0; i < this.tablenames.size(); i++) {
/* 144 */           DataAct.backup_table = this.tablenames.get(i);
/* 145 */           DataAct.percent = i * 100 / this.tablenames.size() + "%";
/* 146 */           backupTable(writer, this.tablenames.get(i));
/*     */         }
/* 148 */         for (int i = 0; i < this.tablenames.size(); i++) {
/*     */           try {
/* 150 */             String sf = DataAct.this.dataBackService
/* 151 */               .getAllExportedKeys(this.tablenames.get(i));
/* 152 */             if (StringUtils.isNotBlank(sf))
/* 153 */               writer.write(sf);
/*     */           }
/*     */           catch (SQLException e) {
/* 156 */             e.printStackTrace();
/*     */           }
/* 158 */           writer.flush();
/*     */         }
/* 160 */         writer.write("SET FOREIGN_KEY_CHECKS = 1");
/* 161 */         writer.write(";--end" + DataAct.BR);
/* 162 */         DataAct.backup_table = "";
/* 163 */         writer.close();
/* 164 */         out.close();
/*     */       } catch (IOException e) {
/* 166 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/*     */     private String backupTable(OutputStreamWriter writer, String tablename) throws IOException
/*     */     {
/* 172 */       writer.write(DataAct.this.dataBackService.getWholeTableSql(tablename));
/* 173 */       writer.flush();
/* 174 */       return tablename;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.DataAct
 * JD-Core Version:    0.6.1
 */