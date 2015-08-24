/*    */ package com.javapms.basic.file;
/*    */ 
/*    */ import com.javapms.basic.utils.NumBeanUtils;
/*    */ import java.io.PrintStream;
/*    */ import java.text.DateFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.apache.commons.lang.RandomStringUtils;
/*    */ 
/*    */ public class FileNameUtils
/*    */ {
/* 21 */   public static final DateFormat pathDf = new SimpleDateFormat("yyyyMM");
/*    */ 
/* 25 */   public static final DateFormat nameDf = new SimpleDateFormat("ddHHmmss");
/*    */ 
/*    */   public static String genPathName()
/*    */   {
/* 35 */     return pathDf.format(new Date());
/*    */   }
/*    */ 
/*    */   public static String genFileName()
/*    */   {
/* 46 */     return nameDf.format(new Date()) + 
/* 47 */       RandomStringUtils.random(4, NumBeanUtils.N36_CHARS);
/*    */   }
/*    */ 
/*    */   public static String genFileName(String ext)
/*    */   {
/* 58 */     return genFileName() + "." + ext;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 62 */     System.out.println(genPathName());
/* 63 */     System.out.println(genFileName());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.file.FileNameUtils
 * JD-Core Version:    0.6.1
 */