/*    */ package com.javapms.basic.plugin.springmvc;
/*    */ 
/*    */ import java.beans.PropertyEditorSupport;
/*    */ import java.sql.Timestamp;
/*    */ import java.text.DateFormat;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import org.springframework.util.StringUtils;
/*    */ 
/*    */ public class DateTypeEditor extends PropertyEditorSupport
/*    */ {
/* 17 */   public static final DateFormat DF_LONG = new SimpleDateFormat(
/* 18 */     "yyyy-MM-dd HH:mm:ss");
/*    */ 
/* 19 */   public static final DateFormat DF_SHORT = new SimpleDateFormat("yyyy-MM-dd");
/*    */   public static final int SHORT_DATE = 10;
/*    */ 
/*    */   public void setAsText(String text)
/*    */     throws IllegalArgumentException
/*    */   {
/* 26 */     text = text.trim();
/* 27 */     if (!StringUtils.hasText(text)) {
/* 28 */       setValue(null);
/* 29 */       return;
/*    */     }
/*    */     try {
/* 32 */       if (text.length() <= 10)
/* 33 */         setValue(new java.sql.Date(DF_SHORT.parse(text).getTime()));
/*    */       else
/* 35 */         setValue(new Timestamp(DF_LONG.parse(text).getTime()));
/*    */     }
/*    */     catch (ParseException ex) {
/* 38 */       IllegalArgumentException iae = new IllegalArgumentException(
/* 39 */         "Could not parse date: " + ex.getMessage());
/* 40 */       iae.initCause(ex);
/* 41 */       throw iae;
/*    */     }
/*    */   }
/*    */ 
/*    */   public String getAsText()
/*    */   {
/* 49 */     java.util.Date value = (java.util.Date)getValue();
/* 50 */     return value != null ? DF_LONG.format(value) : "";
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.plugin.springmvc.DateTypeEditor
 * JD-Core Version:    0.6.1
 */