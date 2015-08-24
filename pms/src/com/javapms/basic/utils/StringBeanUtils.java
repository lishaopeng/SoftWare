/*     */ package com.javapms.basic.utils;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.htmlparser.Node;
/*     */ import org.htmlparser.lexer.Lexer;
/*     */ import org.htmlparser.nodes.TextNode;
/*     */ import org.htmlparser.util.ParserException;
/*     */ 
/*     */ public class StringBeanUtils
/*     */ {
/*     */   public static String handelUrl(String url)
/*     */   {
/*  33 */     if (url == null) {
/*  34 */       return null;
/*     */     }
/*  36 */     url = url.trim();
/*  37 */     if ((url.equals("")) || (url.startsWith("http://")) || 
/*  38 */       (url.startsWith("https://"))) {
/*  39 */       return url;
/*     */     }
/*  41 */     return "http://" + url.trim();
/*     */   }
/*     */ 
/*     */   public static String[] splitAndTrim(String str, String sep, String sep2)
/*     */   {
/*  57 */     if (StringUtils.isBlank(str)) {
/*  58 */       return null;
/*     */     }
/*  60 */     if (!StringUtils.isBlank(sep2)) {
/*  61 */       str = StringUtils.replace(str, sep2, sep);
/*     */     }
/*  63 */     String[] arr = StringUtils.split(str, sep);
/*     */ 
/*  65 */     int i = 0; for (int len = arr.length; i < len; i++) {
/*  66 */       arr[i] = arr[i].trim();
/*     */     }
/*  68 */     return arr;
/*     */   }
/*     */ 
/*     */   public static boolean hasHtml(String txt) {
/*  72 */     if (StringUtils.isBlank(txt)) {
/*  73 */       return false;
/*     */     }
/*     */ 
/*  76 */     boolean doub = false;
/*  77 */     int i = 0; if (i < txt.length()) {
/*  78 */       char c = txt.charAt(i);
/*  79 */       if (c == ' ') {
/*  80 */         if (doub) {
/*  81 */           return true;
/*     */         }
/*  83 */         return true;
/*     */       }
/*     */ 
/*  86 */       switch (c) {
/*     */       case '&':
/*  88 */         return true;
/*     */       case '<':
/*  90 */         return true;
/*     */       case '>':
/*  92 */         return true;
/*     */       case '"':
/*  94 */         return true;
/*     */       case '\n':
/*  96 */         return true;
/*     */       }
/*  98 */       return false;
/*     */     }
/*     */ 
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */   public static String txt2htm(String txt)
/*     */   {
/* 112 */     if (StringUtils.isBlank(txt)) {
/* 113 */       return txt;
/*     */     }
/* 115 */     StringBuilder sb = new StringBuilder((int)(txt.length() * 1.2D));
/*     */ 
/* 117 */     boolean doub = false;
/* 118 */     for (int i = 0; i < txt.length(); i++) {
/* 119 */       char c = txt.charAt(i);
/* 120 */       if (c == ' ') {
/* 121 */         if (doub) {
/* 122 */           sb.append(' ');
/* 123 */           doub = false;
/*     */         } else {
/* 125 */           sb.append("&nbsp;");
/* 126 */           doub = true;
/*     */         }
/*     */       } else {
/* 129 */         doub = false;
/* 130 */         switch (c) {
/*     */         case '&':
/* 132 */           sb.append("&amp;");
/* 133 */           break;
/*     */         case '<':
/* 135 */           sb.append("&lt;");
/* 136 */           break;
/*     */         case '>':
/* 138 */           sb.append("&gt;");
/* 139 */           break;
/*     */         case '"':
/* 141 */           sb.append("&quot;");
/* 142 */           break;
/*     */         case '\n':
/* 144 */           sb.append("<br/>");
/* 145 */           break;
/*     */         default:
/* 147 */           sb.append(c);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 152 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String textCut(String s, int len, String append)
/*     */   {
/* 165 */     if (s == null) {
/* 166 */       return null;
/*     */     }
/* 168 */     int slen = s.length();
/* 169 */     if (slen <= len) {
/* 170 */       return s;
/*     */     }
/*     */ 
/* 173 */     int maxCount = len * 2;
/* 174 */     int count = 0;
		int i;
		/* 175 */for (i = 0;
/* 176 */       (count < maxCount) && (i < slen); i++) {
/* 177 */       if (s.codePointAt(i) < 256)
/* 178 */         count++;
/*     */       else {
/* 180 */         count += 2;
/*     */       }
/*     */     }
/* 183 */     if (i < slen) {
/* 184 */       if (count > maxCount) {
/* 185 */         i--;
/*     */       }
/* 187 */       if (!StringUtils.isBlank(append)) {
/* 188 */         if (s.codePointAt(i - 1) < 256)
/* 189 */           i -= 2;
/*     */         else {
/* 191 */           i--;
/*     */         }
/* 193 */         return s.substring(0, i) + append;
/*     */       }
/* 195 */       return s.substring(0, i);
/*     */     }
/*     */ 
/* 198 */     return s;
/*     */   }
/*     */ 
/*     */   public static String htmlCut(String s, int len, String append)
/*     */   {
/* 203 */     String text = html2Text(s, len * 2);
/* 204 */     return textCut(text, len, append);
/*     */   }
/*     */ 
/*     */   public static String html2Text(String html, int len) {
/*     */     try {
/* 209 */       Lexer lexer = new Lexer(html);
/*     */ 
/* 211 */       StringBuilder sb = new StringBuilder(html.length());
/*     */       Node node;
/* 212 */       while ((node = lexer.nextNode()) != null)
/*     */       {
/* 213 */         if ((node instanceof TextNode)) {
/* 214 */           sb.append(node.toHtml());
/*     */         }
/* 216 */         if (sb.length() > len) {
/*     */           break;
/*     */         }
/*     */       }
/* 220 */       return sb.toString();
/*     */     } catch (ParserException e) {
/* 222 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static StringBuilder replace(StringBuilder sb, String what, String with)
/*     */   {
/* 236 */     int pos = sb.indexOf(what);
/* 237 */     while (pos > -1) {
/* 238 */       sb.replace(pos, pos + what.length(), with);
/* 239 */       pos = sb.indexOf(what);
/*     */     }
/* 241 */     return sb;
/*     */   }
/*     */ 
/*     */   public static String replaceKeyString(String str) {
/* 245 */     if (containsKeyString(str)) {
/* 246 */       return str.replace("'", "\\'").replace("\"", "\\\"")
/* 247 */         .replace("\r", "\\r").replace("\n", "\\n")
/* 248 */         .replace("\t", "\\t").replace("\b", "\\b")
/* 249 */         .replace("\f", "\\f");
/*     */     }
/* 251 */     return str;
/*     */   }
/*     */ 
/*     */   public static boolean containsKeyString(String str)
/*     */   {
/* 256 */     if (StringUtils.isBlank(str)) {
/* 257 */       return false;
/*     */     }
/* 259 */     if ((str.contains("'")) || (str.contains("\"")) || (str.contains("\r")) || 
/* 260 */       (str.contains("\n")) || (str.contains("\t")) || 
/* 261 */       (str.contains("\b")) || (str.contains("\f"))) {
/* 262 */       return true;
/*     */     }
/* 264 */     return false;
/*     */   }
/*     */ 
/*     */   public static String replace(String s, String what, String with)
/*     */   {
/* 276 */     return replace(new StringBuilder(s), what, with).toString();
/*     */   }
/*     */ 
/*     */   public static boolean contains(String str, String[] strs) {
/* 280 */     if ((strs == null) || (strs.length == 0)) {
/* 281 */       return false;
/*     */     }
/* 283 */     for (String s : strs) {
/* 284 */       if (s.equals(str)) {
/* 285 */         return true;
/*     */       }
/*     */     }
/* 288 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getChangeChart(String name) {
/* 292 */     String newname = "";
/*     */     try {
/* 294 */       if (!StringUtils.isBlank(name))
/* 295 */         newname = new String(name.getBytes("iso-8859-1"), "utf-8");
/*     */     }
/*     */     catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*     */     }
/* 299 */     return newname;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.utils.StringBeanUtils
 * JD-Core Version:    0.6.1
 */