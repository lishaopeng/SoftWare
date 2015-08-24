/*     */ package com.javapms.basic.utils;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class NumBeanUtils
/*     */ {
/*  13 */   public static final char[] N62_CHARS = { '0', '1', '2', '3', '4', '5', '6', 
/*  14 */     '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
/*  15 */     'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 
/*  16 */     'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
/*  17 */     'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 
/*  18 */     'x', 'y', 'z' };
/*     */ 
/*  22 */   public static final char[] N36_CHARS = { '0', '1', '2', '3', '4', '5', '6', 
/*  23 */     '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
/*  24 */     'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 
/*  25 */     'x', 'y', 'z' };
/*     */   public static final int LONG_N36_LEN = 13;
/*     */   public static final int LONG_N62_LEN = 11;
/*     */ 
/*     */   private static StringBuilder longToNBuf(long l, char[] chars)
/*     */   {
/*  43 */     int upgrade = chars.length;
/*  44 */     StringBuilder result = new StringBuilder();
/*     */ 
/*  46 */     while (l > 0L) {
/*  47 */       int last = (int)(l % upgrade);
/*  48 */       result.append(chars[last]);
/*  49 */       l /= upgrade;
/*     */     }
/*  51 */     return result;
/*     */   }
/*     */ 
/*     */   public static String longToN62(long l)
/*     */   {
/*  61 */     return longToNBuf(l, N62_CHARS).reverse().toString();
/*     */   }
/*     */ 
/*     */   public static String longToN36(long l)
/*     */   {
/*  71 */     return longToNBuf(l, N36_CHARS).reverse().toString();
/*     */   }
/*     */ 
/*     */   public static String longToN62(long l, int length)
/*     */   {
/*  83 */     StringBuilder sb = longToNBuf(l, N62_CHARS);
/*  84 */     for (int i = sb.length(); i < length; i++) {
/*  85 */       sb.append('0');
/*     */     }
/*  87 */     return sb.reverse().toString();
/*     */   }
/*     */ 
/*     */   public static String longToN36(long l, int length)
/*     */   {
/*  99 */     StringBuilder sb = longToNBuf(l, N36_CHARS);
/* 100 */     for (int i = sb.length(); i < length; i++) {
/* 101 */       sb.append('0');
/*     */     }
/* 103 */     return sb.reverse().toString();
/*     */   }
/*     */ 
/*     */   public static long n62ToLong(String n62)
/*     */   {
/* 113 */     return nToLong(n62, N62_CHARS);
/*     */   }
/*     */ 
/*     */   public static long n36ToLong(String n36)
/*     */   {
/* 123 */     return nToLong(n36, N36_CHARS);
/*     */   }
/*     */ 
/*     */   private static long nToLong(String s, char[] chars) {
/* 127 */     char[] nc = s.toCharArray();
/* 128 */     long result = 0L;
/* 129 */     long pow = 1L;
/* 130 */     for (int i = nc.length - 1; i >= 0; pow *= chars.length) {
/* 131 */       int n = findNIndex(nc[i], chars);
/* 132 */       result += n * pow;
/*     */ 
/* 130 */       i--;
/*     */     }
/*     */ 
/* 134 */     return result;
/*     */   }
/*     */ 
/*     */   private static int findNIndex(char c, char[] chars) {
/* 138 */     for (int i = 0; i < chars.length; i++) {
/* 139 */       if (c == chars[i]) {
/* 140 */         return i;
/*     */       }
/*     */     }
/* 143 */     throw new RuntimeException("N62(N36)非法字符：" + c);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 147 */     System.out.println(longToN62(9223372036854775807L));
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.utils.NumBeanUtils
 * JD-Core Version:    0.6.1
 */