/*     */ package com.javapms.basic.utils;
/*     */ 
/*     */ import java.beans.BeanInfo;
/*     */ import java.beans.IntrospectionException;
/*     */ import java.beans.Introspector;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public final class ResponseUtils
/*     */ {
/*  23 */   public static final Logger log = LoggerFactory.getLogger(ResponseUtils.class);
/*     */ 
/*     */   public static void renderText(HttpServletResponse response, String text)
/*     */   {
/*  34 */     render(response, "text/plain;charset=UTF-8", text);
/*     */   }
/*     */ 
/*     */   public static void renderJson(HttpServletResponse response, String text)
/*     */   {
/*  46 */     render(response, "application/json;charset=UTF-8", text);
/*     */   }
/*     */ 
/*     */   public static void renderXml(HttpServletResponse response, String text)
/*     */   {
/*  58 */     render(response, "text/xml;charset=UTF-8", text);
/*     */   }
/*     */ 
/*     */   public static void render(HttpServletResponse response, String contentType, String text)
/*     */   {
/*  70 */     response.setContentType(contentType);
/*  71 */     response.setHeader("Pragma", "No-cache");
/*  72 */     response.setHeader("Cache-Control", "no-cache");
/*  73 */     response.setDateHeader("Expires", 0L);
/*     */     try {
/*  75 */       response.getWriter().write(text);
/*     */     } catch (IOException e) {
/*  77 */       log.error(e.getMessage(), e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static String beanToJson(Object bean, String arg) {
/*  82 */     if (bean == null) {
/*  83 */       return "";
/*     */     }
/*  85 */     PropertyDescriptor[] props = (PropertyDescriptor[])null;
/*     */     try {
/*  87 */       props = Introspector.getBeanInfo(bean.getClass(), Object.class)
/*  88 */         .getPropertyDescriptors();
/*     */     } catch (IntrospectionException localIntrospectionException) {
/*     */     }
/*  91 */     if (props != null) {
/*  92 */       for (int i = 0; i < props.length; i++) {
/*     */         try {
/*  94 */           String name = props[i].getName();
/*  95 */           if (arg.indexOf(".") > -1) {
/*  96 */             String temparg = arg.substring(0, arg.indexOf("."));
/*  97 */             if (temparg.equals(name)) {
/*  98 */               return beanToJson(
/*  99 */                 props[i].getReadMethod().invoke(bean, new Object[0]), 
/* 100 */                 arg.substring(arg.indexOf(".") + 1, 
/* 101 */                 arg.length()));
/*     */             }
/*     */           }
/* 104 */           else if (name.equals(arg)) {
/* 105 */             return props[i].getReadMethod()
/* 106 */               .invoke(bean, new Object[0]).toString();
/*     */           }
/*     */         }
/*     */         catch (Exception localException)
/*     */         {
/*     */         }
/*     */       }
/*     */     }
/* 114 */     return "";
/*     */   }
/*     */ 
/*     */   public static String beanToJson(Object bean, String[] args) {
/* 118 */     StringBuffer json = new StringBuffer();
/* 119 */     json.append("{");
/* 120 */     PropertyDescriptor[] props = (PropertyDescriptor[])null;
/*     */     try {
/* 122 */       props = Introspector.getBeanInfo(bean.getClass(), Object.class)
/* 123 */         .getPropertyDescriptors();
/*     */     } catch (IntrospectionException localIntrospectionException) {
/*     */     }
/* 126 */     if (props != null) {
/* 127 */       for (int i = 0; i < props.length; i++)
/*     */         try {
/* 129 */           String name = props[i].getName();
/* 130 */           for (String arg : args) {
/* 131 */             if (arg.indexOf(".") > -1) {
/* 132 */               String[] showname = arg.split(":");
/* 133 */               String temparg = showname[0].substring(0, 
/* 134 */                 showname[0].indexOf("."));
/* 135 */               if (temparg.equals(name)) {
/* 136 */                 String tvalue = beanToJson(props[i]
/* 137 */                   .getReadMethod().invoke(bean, new Object[0]), 
/* 138 */                   showname[0].substring(
/* 139 */                   showname[0].indexOf(".") + 1, 
/* 140 */                   showname[0].length()));
/* 141 */                 json.append(showname[1]);
/* 142 */                 json.append(":");
/* 143 */                 json.append('"');
/* 144 */                 json.append(StringBeanUtils.txt2htm(tvalue));
/* 145 */                 json.append('"');
/* 146 */                 json.append(",");
/*     */               }
/*     */             }
/*     */           }
/* 150 */           String value = props[i].getReadMethod().invoke(bean, new Object[0])
/* 151 */             .toString();
/* 152 */           json.append(name);
/* 153 */           json.append(":");
/* 154 */           json.append('"');
/* 155 */           json.append(StringBeanUtils.txt2htm(value));
/* 156 */           json.append('"');
/* 157 */           json.append(",");
/*     */         }
/*     */         catch (Exception localException) {
/*     */         }
/* 161 */       json.setCharAt(json.length() - 1, '}');
/*     */     } else {
/* 163 */       json.append("}");
/*     */     }
/* 165 */     return json.toString();
/*     */   }
/*     */ 
/*     */   public static String listToJson(List list) {
/* 169 */     StringBuffer json = new StringBuffer();
/* 170 */     json.append("{c:[");
/* 171 */     if ((list != null) && (list.size() > 0)) {
/* 172 */       for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
/* 173 */         json.append(beanToJson(o, new String[0]));
/* 174 */         json.append(",");
/*     */       }
/* 176 */       json.setCharAt(json.length() - 1, ']');
/* 177 */       json.append("}");
/*     */     } else {
/* 179 */       json.append("]}");
/*     */     }
/* 181 */     return json.toString();
/*     */   }
/*     */ 
/*     */   public static String listToJsonadmin(List list, Integer total, String[] args)
/*     */   {
/* 187 */     StringBuffer json = new StringBuffer();
/* 188 */     json.append("{Rows:[");
/* 189 */     if ((list != null) && (list.size() > 0)) {
/* 190 */       for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
/* 191 */         json.append(beanToJson(o, args));
/* 192 */         json.append(",");
/*     */       }
/* 194 */       json.setCharAt(json.length() - 1, ']');
/* 195 */       if (total != null) {
/* 196 */         json.append(",Total:");
/* 197 */         json.append(total);
/*     */       }
/* 199 */       json.append("}");
/*     */     } else {
/* 201 */       json.append("]}");
/*     */     }
/* 203 */     return json.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.utils.ResponseUtils
 * JD-Core Version:    0.6.1
 */