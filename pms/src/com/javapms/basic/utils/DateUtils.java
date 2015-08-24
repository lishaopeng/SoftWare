/*     */ package com.javapms.basic.utils;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class DateUtils
/*     */ {
/*     */   public static Boolean getSameHourTime(Integer hour)
/*     */   {
/*   9 */     if (hour == null) {
/*  10 */       return Boolean.valueOf(false);
/*     */     }
/*  12 */     Calendar cal = Calendar.getInstance();
/*  13 */     int h = cal.get(11);
/*  14 */     if (hour.intValue() == h) {
/*  15 */       return Boolean.valueOf(true);
/*     */     }
/*  17 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   public static String getNowToString() {
/*  21 */     Calendar cal = Calendar.getInstance();
/*  22 */     int year = cal.get(1);
/*  23 */     int month = cal.get(2) + 1;
/*  24 */     int day = cal.get(5);
/*  25 */     StringBuilder sb = new StringBuilder();
/*  26 */     sb.append(year);
/*  27 */     if (month < 10) {
/*  28 */       sb.append("0");
/*     */     }
/*  30 */     sb.append(month);
/*  31 */     if (day < 10) {
/*  32 */       sb.append("0");
/*     */     }
/*  34 */     sb.append(day);
/*  35 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static Date getToday()
/*     */   {
/*  42 */     Calendar cld = Calendar.getInstance();
/*  43 */     cld.set(11, 0);
/*  44 */     cld.set(12, 0);
/*  45 */     cld.set(13, 0);
/*  46 */     cld.set(14, 0);
/*  47 */     return cld.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getEndOfDay()
/*     */   {
/*  54 */     Calendar cld = Calendar.getInstance();
/*  55 */     cld.set(11, 23);
/*  56 */     cld.set(12, 59);
/*  57 */     cld.set(13, 59);
/*  58 */     cld.set(14, 999);
/*  59 */     return cld.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getStartOfDay(int interval)
/*     */   {
/*  71 */     Calendar cld = Calendar.getInstance();
/*  72 */     cld.setTimeInMillis(cld.getTimeInMillis() + interval * 24 * 60 * 60 * 
/*  73 */       1000L);
/*  74 */     cld.set(11, 0);
/*  75 */     cld.set(12, 0);
/*  76 */     cld.set(13, 0);
/*  77 */     cld.set(14, 0);
/*  78 */     return cld.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getStartOfDay(Date date)
/*     */   {
/*  89 */     Calendar cld = Calendar.getInstance();
/*  90 */     cld.setTime(date);
/*  91 */     cld.set(11, 0);
/*  92 */     cld.set(12, 0);
/*  93 */     cld.set(13, 0);
/*  94 */     cld.set(14, 0);
/*  95 */     return cld.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getEndOfDay(int interval)
/*     */   {
/* 106 */     Calendar cld = Calendar.getInstance();
/* 107 */     cld.setTimeInMillis(cld.getTimeInMillis() + interval * 24 * 60 * 60 * 
/* 108 */       1000L);
/* 109 */     cld.set(11, 23);
/* 110 */     cld.set(12, 59);
/* 111 */     cld.set(13, 59);
/* 112 */     cld.set(14, 999);
/* 113 */     return cld.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getEndOfDay(Date date)
/*     */   {
/* 124 */     Calendar cld = Calendar.getInstance();
/* 125 */     cld.setTime(date);
/* 126 */     cld.set(11, 23);
/* 127 */     cld.set(12, 59);
/* 128 */     cld.set(13, 59);
/* 129 */     cld.set(14, 999);
/* 130 */     return cld.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getStartOfWeek(int interval)
/*     */   {
/* 141 */     Calendar cld = Calendar.getInstance();
/* 142 */     cld.setTimeInMillis(cld.getTimeInMillis() + interval * 7 * 24 * 60 * 60 * 
/* 143 */       1000L);
/* 144 */     cld.set(7, 1);
/* 145 */     return getStartOfDay(cld.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getStartOfWeek(int year, int week)
/*     */   {
/* 158 */     Calendar cld = Calendar.getInstance();
/* 159 */     cld.set(1, year);
/* 160 */     cld.set(3, week);
/* 161 */     cld.set(7, 1);
/* 162 */     return getStartOfDay(cld.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getStartOfWeek(Date date, int interval)
/*     */   {
/* 175 */     Calendar cld = Calendar.getInstance();
/* 176 */     cld.setTime(date);
/* 177 */     cld.setTimeInMillis(cld.getTimeInMillis() + interval * 7 * 24 * 60 * 60 * 
/* 178 */       1000L);
/* 179 */     cld.set(7, 1);
/* 180 */     return getStartOfDay(cld.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getStartOfWeek(Date date)
/*     */   {
/* 191 */     Calendar cld = Calendar.getInstance();
/* 192 */     cld.setTime(date);
/* 193 */     cld.set(7, 1);
/* 194 */     return getStartOfDay(cld.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getEndOfWeek(int interval)
/*     */   {
/* 205 */     Calendar cld = Calendar.getInstance();
/* 206 */     cld.setTime(getStartOfWeek(interval + 1));
/* 207 */     return new Date(cld.getTimeInMillis() - 1L);
/*     */   }
/*     */ 
/*     */   public static Date getEndOfWeek(int year, int week)
/*     */   {
/* 220 */     Calendar cld = Calendar.getInstance();
/* 221 */     cld.setTime(getStartOfWeek(year, week + 1));
/* 222 */     return new Date(cld.getTimeInMillis() - 1L);
/*     */   }
/*     */ 
/*     */   public static Date getEndOfWeek(Date date, int interval)
/*     */   {
/* 235 */     Calendar cld = Calendar.getInstance();
/* 236 */     cld.setTime(date);
/* 237 */     cld.setTime(getStartOfWeek(date, interval + 1));
/* 238 */     return new Date(cld.getTimeInMillis() - 1L);
/*     */   }
/*     */ 
/*     */   public static Date getEndOfWeek(Date date)
/*     */   {
/* 249 */     Calendar cld = Calendar.getInstance();
/* 250 */     cld.setTime(date);
/* 251 */     cld.set(7, 7);
/* 252 */     return getEndOfDay(cld.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getStartOfMonth(Date date)
/*     */   {
/* 263 */     Calendar cld = Calendar.getInstance();
/* 264 */     cld.setTime(date);
/* 265 */     cld.set(5, 1);
/* 266 */     return getStartOfDay(cld.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getStartOfMonth(int year, int month)
/*     */   {
/* 279 */     Calendar cld = Calendar.getInstance();
/* 280 */     cld.set(1, year);
/* 281 */     cld.set(2, month);
/* 282 */     cld.set(5, 1);
/* 283 */     return getStartOfDay(cld.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getStartOfMonth(int interval)
/*     */   {
/* 294 */     Calendar cld = Calendar.getInstance();
/* 295 */     int currentMonth = cld.get(2);
/* 296 */     cld.add(1, (interval + currentMonth) / 12);
/* 297 */     cld.set(2, (interval + currentMonth) % 12);
/* 298 */     cld.set(5, 1);
/* 299 */     return getStartOfDay(cld.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getEndOfMonth(Date date)
/*     */   {
/* 310 */     Calendar cld = Calendar.getInstance();
/* 311 */     cld.setTime(date);
/* 312 */     int maxDay = cld.getActualMaximum(5);
/* 313 */     cld.set(5, maxDay);
/* 314 */     return getEndOfDay(cld.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getEndOfMonth(int interval)
/*     */   {
/* 325 */     return new Date(getStartOfMonth(interval + 1).getTime() - 1L);
/*     */   }
/*     */ 
/*     */   public static Date getEndOfMonth(int year, int month)
/*     */   {
/* 338 */     return new Date(getStartOfMonth(year, month + 1).getTime() - 1L);
/*     */   }
/*     */ 
/*     */   public static int monthsBetween(Date startDate, Date endDate)
/*     */   {
/* 349 */     Calendar cld = Calendar.getInstance();
/* 350 */     cld.setTime(startDate);
/* 351 */     int startMonth = cld.get(2);
/* 352 */     int startYear = cld.get(1);
/* 353 */     cld.setTime(endDate);
/* 354 */     int endMonth = cld.get(2);
/* 355 */     int endYear = cld.get(1);
/* 356 */     return (endYear - startYear) * 12 + (endMonth - startMonth);
/*     */   }
/*     */ 
/*     */   public static int daysBetween(Date startDate, Date endDate)
/*     */   {
/* 367 */     Calendar c1 = Calendar.getInstance();
/* 368 */     Calendar c2 = Calendar.getInstance();
/* 369 */     c1.setTime(startDate);
/* 370 */     c2.setTime(endDate);
/* 371 */     return daysBetween(c1, c2);
/*     */   }
/*     */ 
/*     */   public static int daysBetween(Calendar early, Calendar late) {
/* 375 */     return (int)(toJulian(late) - toJulian(early));
/*     */   }
/*     */ 
/*     */   public static final float toJulian(Calendar c) {
/* 379 */     int Y = c.get(1);
/* 380 */     int M = c.get(2);
/* 381 */     int D = c.get(5);
/* 382 */     int A = Y / 100;
/* 383 */     int B = A / 4;
/* 384 */     int C = 2 - A + B;
/* 385 */     float E = (int)(365.25F * Y + 4716);
/* 386 */     float F = (int)(30.6001F * M + 1);
/* 387 */     float JD = C + D + E + F - 1524.5F;
/* 388 */     return JD;
/*     */   }
/*     */ 
/*     */   public static int getAge(Date birthday)
/*     */   {
/* 398 */     Calendar c1 = Calendar.getInstance();
/* 399 */     c1.setTime(birthday);
/* 400 */     Calendar c2 = Calendar.getInstance();
/* 401 */     c2.setTime(new Date());
/* 402 */     int age = c2.get(1) - c1.get(1);
/* 403 */     return age < 0 ? 0 : age;
/*     */   }
/*     */ 
/*     */   public static Date addDate(Date date, int day)
/*     */   {
/* 414 */     Calendar cld = Calendar.getInstance();
/* 415 */     cld.setTime(date);
/* 416 */     cld.setTimeInMillis(cld.getTimeInMillis() + day * 24L * 3600L * 
/* 417 */       1000L);
/* 418 */     return cld.getTime();
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.utils.DateUtils
 * JD-Core Version:    0.6.1
 */