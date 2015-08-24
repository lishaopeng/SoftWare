/*     */ package com.javapms.basic.utils;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.apache.commons.lang.RandomStringUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class UploadUtils
/*     */ {
/*  18 */   public static final DateFormat MONTH_FORMAT = new SimpleDateFormat(
/*  19 */     "/yyyyMM/ddHHmmss");
/*     */ 
/*  27 */   protected static final Pattern ILLEGAL_CURRENT_FOLDER_PATTERN = Pattern.compile("^[^/]|[^/]$|/\\.{1,2}|\\\\|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}");
/*     */ 
/*     */   public static String generateFilename(String path, String ext)
/*     */   {
/*  22 */     return path + MONTH_FORMAT.format(new Date()) + 
/*  23 */       RandomStringUtils.random(4, NumBeanUtils.N36_CHARS) + "." + ext;
/*     */   }
/*     */ 
/*     */   public static String sanitizeFileName(String filename)
/*     */   {
/*  42 */     if (StringUtils.isBlank(filename)) {
/*  43 */       return filename;
/*     */     }
/*  45 */     String name = forceSingleExtension(filename);
/*     */ 
/*  48 */     return name.replaceAll("\\\\|/|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}", "_");
/*     */   }
/*     */ 
/*     */   public static String sanitizeFolderName(String folderName)
/*     */   {
/*  63 */     if (StringUtils.isBlank(folderName)) {
/*  64 */       return folderName;
/*     */     }
/*     */ 
/*  67 */     return folderName.replaceAll(
/*  68 */       "\\.|\\\\|/|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}", "_");
/*     */   }
/*     */ 
/*     */   public static boolean isValidPath(String path)
/*     */   {
/*  81 */     if (StringUtils.isBlank(path)) {
/*  82 */       return false;
/*     */     }
/*  84 */     if (ILLEGAL_CURRENT_FOLDER_PATTERN.matcher(path).find()) {
/*  85 */       return false;
/*     */     }
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */   public static String forceSingleExtension(String filename)
/*     */   {
/*  98 */     return filename.replaceAll("\\.(?![^.]+$)", "_");
/*     */   }
/*     */ 
/*     */   public static boolean isSingleExtension(String filename)
/*     */   {
/* 110 */     return filename.matches("[^\\.]+\\.[^\\.]+");
/*     */   }
/*     */ 
/*     */   public static void checkDirAndCreate(File dir)
/*     */   {
/* 120 */     if (!dir.exists())
/* 121 */       dir.mkdirs();
/*     */   }
/*     */ 
/*     */   public static File getUniqueFile(File file)
/*     */   {
/* 135 */     if (!file.exists()) {
/* 136 */       return file;
/*     */     }
/* 138 */     File tmpFile = new File(file.getAbsolutePath());
/* 139 */     File parentDir = tmpFile.getParentFile();
/* 140 */     int count = 1;
/* 141 */     String extension = FilenameUtils.getExtension(tmpFile.getName());
/* 142 */     String baseName = FilenameUtils.getBaseName(tmpFile.getName());
/*     */     do
/* 144 */       tmpFile = new File(parentDir, baseName + "(" + count++ + ")." + 
/* 145 */         extension);
/* 143 */     while (
/* 146 */       tmpFile.exists());
/* 147 */     return tmpFile;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 151 */     System.out.println(generateFilename("/base", "gif"));
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.utils.UploadUtils
 * JD-Core Version:    0.6.1
 */