/*     */ package com.portal.sysmgr.utils;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLDecoder;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;

/*     */ import javax.servlet.http.HttpServletRequest;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ 
/*     */ public class URLTools
/*     */ {
/*     */   public static String getUrl(HttpServletRequest request)
/*     */   {
/*  22 */     String uri = request.getRequestURI();
/*  23 */     String queryString = request.getQueryString();
/*  24 */     if (StringUtils.isNotBlank(queryString)) {
/*  25 */       uri = uri + "?" + queryString;
/*     */     }
/*  27 */     return uri;
/*     */   }
/*     */ 
/*     */   public static Integer getPageNo(HttpServletRequest request)
/*     */   {
/*  32 */     String uri = request.getRequestURI();
/*  33 */     int i = uri.lastIndexOf(".");
/*  34 */     int j = uri.lastIndexOf("_");
/*  35 */     if ((i > -1) && (j > -1) && (i > j)) {
/*  36 */       String pageStr = uri.substring(j + 1, i);
/*  37 */       return Integer.valueOf(pageStr);
/*     */     }
/*  39 */     return Integer.valueOf(1);
/*     */   }
/*     */ 
/*     */   public static String getUrlFromParamter(HttpServletRequest request)
/*     */     throws UnsupportedEncodingException
/*     */   {
		/* 45 */Map<String, Object> map = request.getParameterMap();
/*  46 */     StringBuilder sb = new StringBuilder("-");
/*  47 */     for (Map.Entry entry : map.entrySet()) {
/*  48 */       sb.append((String)entry.getKey());
/*  49 */       sb.append("-");
/*  50 */       String value = URLEncoder.encode(((String[])entry.getValue())[0], 
/*  51 */         "UTF-8");
/*  52 */       value = URLEncoder.encode(value, "UTF-8");
/*  53 */       sb.append(value);
/*  54 */       sb.append("-");
/*     */     }
/*  56 */     return sb.substring(0, sb.length() - 1);
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getAllParamter(HttpServletRequest request)
/*     */     throws UnsupportedEncodingException
/*     */   {
/*  62 */     String uri = request.getRequestURI();
/*  63 */     int i = uri.indexOf("-");
/*  64 */     int j = uri.lastIndexOf("_");
/*  65 */     if ((j == -1) || (j < i)) {
/*  66 */       j = uri.lastIndexOf(".");
/*     */     }
/*  68 */     Map map = new HashMap();
/*  69 */     if ((i > -1) && (j > -1) && (j > i)) {
/*  70 */       String[] paramters = uri.substring(i + 1, j).split("-");
/*     */ 
/*  72 */       for (int l = 0; l < paramters.length - 1; l++) {
/*  73 */         if ((l % 2 == 0) && 
/*  74 */           (StringUtils.isNotBlank(paramters[l]))) {
/*  75 */           String value = URLDecoder.decode(paramters[(l + 1)], 
/*  76 */             "UTF-8");
/*  77 */           value = URLDecoder.decode(value, "UTF-8");
/*  78 */           map.put(paramters[l], value);
/*     */         }
/*     */       }
/*     */ 
/*  82 */       return map;
/*     */     }
/*  84 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getUrlChanged(HttpServletRequest request, boolean encode)
/*     */   {
/*  90 */     String uri = request.getRequestURI();
/*  91 */     String url = getUrl(request);
/*  92 */     if (encode) {
/*  93 */       uri = uri.replace(".jsp", ".html");
/*  94 */       uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
/*     */       try {
/*  96 */         uri = URLEncoder.encode(uri, "UTF-8");
/*     */       } catch (UnsupportedEncodingException e) {
/*  98 */         e.printStackTrace();
/*     */       }
/* 100 */       url = url.substring(0, url.lastIndexOf("/") + 1);
/* 101 */       url = url + uri;
/*     */     } else {
/* 103 */       url = url.replace(".jsp", ".html");
/*     */     }
/* 105 */     return url;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.utils.URLTools
 * JD-Core Version:    0.6.1
 */