/*     */ package com.javapms.basic.developer;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class ModuleGenerator
/*     */ {
/*  32 */   private static final Logger log = LoggerFactory.getLogger(ModuleGenerator.class);
/*  33 */   public static final String SPT = File.separator;
/*     */   public static final String ENCODING = "UTF-8";
/*  37 */   private Properties prop = new Properties();
/*     */   private String packName;
/*     */   private String fileName;
/*     */   private File daoImplFile;
/*     */   private File daoFile;
/*     */   private File managerFile;
/*     */   private File managerImplFile;
/*     */   private File actionFile;
/*     */   private File pageListFile;
/*     */   private File pageEditFile;
/*     */   private File pageAddFile;
/*     */   private File daoImplTpl;
/*     */   private File daoTpl;
/*     */   private File managerTpl;
/*     */   private File managerImplTpl;
/*     */   private File actionTpl;
/*     */   private File pageListTpl;
/*     */   private File pageEditTpl;
/*     */   private File pageAddTpl;
/*     */ 
/*     */   public ModuleGenerator(String packName, String fileName)
/*     */   {
/*  60 */     this.packName = packName;
/*  61 */     this.fileName = fileName;
/*     */   }
/*     */ 
/*     */   private void loadProperties() {
/*     */     try {
/*  66 */       log.debug("packName=" + this.packName);
/*  67 */       log.debug("fileName=" + this.fileName);
/*  68 */       FileInputStream fileInput = new FileInputStream(getFilePath(
/*  69 */         this.packName, this.fileName));
/*  70 */       this.prop.load(fileInput);
/*  71 */       String entityUp = this.prop.getProperty("Entity");
/*  72 */       log.debug("entityUp:" + entityUp);
/*  73 */       if ((entityUp == null) || (entityUp.trim().equals(""))) {
/*  74 */         log.warn("Entity not specified, exit!");
/*  75 */         return;
/*     */       }
/*  77 */       String entityLow = entityUp.substring(0, 1).toLowerCase() + 
/*  78 */         entityUp.substring(1);
/*  79 */       log.debug("entityLow:" + entityLow);
/*  80 */       this.prop.put("entity", entityLow);
/*  81 */       if (log.isDebugEnabled()) {
/*  82 */         Set ps = this.prop.keySet();
/*  83 */         for (Iterator localIterator = ps.iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
/*  84 */           log.debug(o + "=" + this.prop.get(o)); }
/*     */       }
/*     */     }
/*     */     catch (FileNotFoundException e) {
/*  88 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/*  90 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void prepareFile() {
/*  95 */     String daoImplFilePath = getFilePath(this.prop.getProperty("dao_impl_p"), 
/*  96 */       this.prop.getProperty("Entity") + "DaoImpl.java");
/*  97 */     this.daoImplFile = new File(daoImplFilePath);
/*  98 */     log.debug("daoImplFile:" + this.daoImplFile.getAbsolutePath());
/*     */ 
/* 100 */     String daoFilePath = getFilePath(this.prop.getProperty("dao_p"), this.prop
/* 101 */       .getProperty("Entity") + 
/* 102 */       "Dao.java");
/* 103 */     this.daoFile = new File(daoFilePath);
/* 104 */     log.debug("daoFile:" + this.daoFile.getAbsolutePath());
/*     */ 
/* 106 */     String managerFilePath = getFilePath(this.prop.getProperty("manager_p"), 
/* 107 */       this.prop.getProperty("Entity") + "Service.java");
/* 108 */     this.managerFile = new File(managerFilePath);
/* 109 */     log.debug("managerFile:" + this.managerFile.getAbsolutePath());
/*     */ 
/* 111 */     String managerImplFilePath = getFilePath(this.prop
/* 112 */       .getProperty("manager_impl_p"), this.prop.getProperty("Entity") + 
/* 113 */       "ServiceImpl.java");
/* 114 */     this.managerImplFile = new File(managerImplFilePath);
/* 115 */     log.debug("managerImplFile:" + this.managerImplFile.getAbsolutePath());
/* 116 */     String actionFilePath = getFilePath(this.prop.getProperty("action_p"), this.prop
/* 117 */       .getProperty("Entity") + 
/* 118 */       "Act.java");
/* 119 */     this.actionFile = new File(actionFilePath);
/* 120 */     log.debug("actionFile:" + this.actionFile.getAbsolutePath());
/*     */ 
/* 122 */     String pagePath = "WebContent/WEB-INF/" + 
/* 123 */       this.prop.getProperty("config_sys") + "/" + 
/* 124 */       this.prop.getProperty("config_entity") + "/";
/* 125 */     this.pageListFile = new File(pagePath + "list.html");
/* 126 */     log.debug("pageListFile:" + this.pageListFile.getAbsolutePath());
/* 127 */     this.pageEditFile = new File(pagePath + "edit.html");
/* 128 */     log.debug("pageEditFile:" + this.pageEditFile.getAbsolutePath());
/* 129 */     this.pageAddFile = new File(pagePath + "add.html");
/* 130 */     log.debug("pageAddFile:" + this.pageAddFile.getAbsolutePath());
/*     */   }
/*     */ 
/*     */   private void prepareTemplate() {
/* 134 */     String tplPack = this.prop.getProperty("template_dir");
/* 135 */     log.debug("tplPack:" + tplPack);
/* 136 */     this.daoImplTpl = new File(getFilePath(tplPack, "dao_impl.txt"));
/* 137 */     this.daoTpl = new File(getFilePath(tplPack, "dao.txt"));
/* 138 */     this.managerImplTpl = new File(getFilePath(tplPack, "manager_impl.txt"));
/* 139 */     this.managerTpl = new File(getFilePath(tplPack, "manager.txt"));
/* 140 */     this.actionTpl = new File(getFilePath(tplPack, "action.txt"));
/* 141 */     this.pageListTpl = new File(getFilePath(tplPack, "page_list.txt"));
/* 142 */     this.pageAddTpl = new File(getFilePath(tplPack, "page_add.txt"));
/* 143 */     this.pageEditTpl = new File(getFilePath(tplPack, "page_edit.txt"));
/*     */   }
/*     */ 
/*     */   private static void stringToFile(File file, String s) throws IOException {
/* 147 */     FileUtils.writeStringToFile(file, s, "UTF-8");
/*     */   }
/*     */ 
/*     */   private void writeFile() {
/*     */     try {
/* 152 */       if ("true".equals(this.prop.getProperty("is_dao"))) {
/* 153 */         stringToFile(this.daoImplFile, readTpl(this.daoImplTpl));
/* 154 */         stringToFile(this.daoFile, readTpl(this.daoTpl));
/*     */       }
/* 156 */       if ("true".equals(this.prop.getProperty("is_manager"))) {
/* 157 */         stringToFile(this.managerImplFile, readTpl(this.managerImplTpl));
/* 158 */         stringToFile(this.managerFile, readTpl(this.managerTpl));
/*     */       }
/* 160 */       if ("true".equals(this.prop.getProperty("is_action"))) {
/* 161 */         stringToFile(this.actionFile, readTpl(this.actionTpl));
/*     */       }
/* 163 */       if ("true".equals(this.prop.getProperty("is_page"))) {
/* 164 */         stringToFile(this.pageListFile, readTpl(this.pageListTpl));
/* 165 */         stringToFile(this.pageAddFile, readTpl(this.pageAddTpl));
/* 166 */         stringToFile(this.pageEditFile, readTpl(this.pageEditTpl));
/*     */       }
/*     */     } catch (IOException e) {
/* 169 */       log.warn("write file faild! " + e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   private String readTpl(File tpl) {
/* 174 */     String content = null;
/*     */     try {
/* 176 */       content = FileUtils.readFileToString(tpl, "UTF-8");
/* 177 */       Set ps = this.prop.keySet();
/* 178 */       for (Iterator localIterator = ps.iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
/* 179 */         String key = (String)o;
/* 180 */         String value = this.prop.getProperty(key);
/* 181 */         content = content.replaceAll("\\#\\{" + key + "\\}", value); }
/*     */     }
/*     */     catch (IOException e) {
/* 184 */       log.warn("read file faild. " + e.getMessage());
/*     */     }
/* 186 */     return content;
/*     */   }
/*     */ 
/*     */   private String getFilePath(String packageName, String name)
/*     */   {
/* 191 */     log.debug("replace:" + packageName);
/* 192 */     String path = packageName.replaceAll("\\.", "/");
/* 193 */     log.debug("after relpace:" + path);
/* 194 */     return "src/" + path + "/" + name;
/*     */   }
/*     */ 
/*     */   public void generate() {
/* 198 */     loadProperties();
/* 199 */     prepareFile();
/* 200 */     prepareTemplate();
/* 201 */     writeFile();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 205 */     String packName = "com.javapms.basic.developer.template";
/* 206 */     String fileName = "template.properties";
/* 207 */     new ModuleGenerator(packName, fileName).generate();
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.developer.ModuleGenerator
 * JD-Core Version:    0.6.1
 */