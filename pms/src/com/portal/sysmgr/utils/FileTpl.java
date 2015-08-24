/*    */ package com.portal.sysmgr.utils;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.sql.Timestamp;
/*    */ import java.util.Date;
/*    */ import org.apache.commons.io.FileUtils;
/*    */ 
/*    */ public class FileTpl
/*    */   implements Tpl
/*    */ {
/*    */   private File file;
/*    */   private String root;
/*    */ 
/*    */   public FileTpl(File file, String root)
/*    */   {
/* 16 */     this.file = file;
/* 17 */     this.root = root;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 21 */     String ap = this.file.getAbsolutePath().substring(this.root.length());
/* 22 */     ap = ap.replace(File.separatorChar, '/');
/*    */ 
/* 24 */     if (!ap.startsWith("/")) {
/* 25 */       ap = "/" + ap;
/*    */     }
/* 27 */     return ap;
/*    */   }
/*    */ 
/*    */   public String getPath() {
/* 31 */     String name = getName();
/* 32 */     return name.substring(0, name.lastIndexOf('/'));
/*    */   }
/*    */ 
/*    */   public String getFilename() {
/* 36 */     return this.file.getName();
/*    */   }
/*    */ 
/*    */   public String getSource() {
/* 40 */     if (this.file.isDirectory())
/* 41 */       return null;
/*    */     try
/*    */     {
/* 44 */       return FileUtils.readFileToString(this.file, "UTF-8");
/*    */     } catch (IOException e) {
/* 46 */       throw new RuntimeException(e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public long getLastModified() {
/* 51 */     return this.file.lastModified();
/*    */   }
/*    */ 
/*    */   public Date getLastModifiedDate() {
/* 55 */     return new Timestamp(getLastModified());
/*    */   }
/*    */ 
/*    */   public long getLength() {
/* 59 */     return this.file.length();
/*    */   }
/*    */ 
/*    */   public int getSize() {
/* 63 */     return (int)(getLength() / 1024L) + 1;
/*    */   }
/*    */ 
/*    */   public boolean isDirectory() {
/* 67 */     return this.file.isDirectory();
/*    */   }
/*    */ 
/*    */   public boolean isLeaf() {
/* 71 */     File[] child = this.file.listFiles();
/* 72 */     if (child != null) {
/* 73 */       for (File f : child) {
/* 74 */         if (f.isDirectory()) {
/* 75 */           return false;
/*    */         }
/*    */       }
/*    */     }
/* 79 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.utils.FileTpl
 * JD-Core Version:    0.6.1
 */