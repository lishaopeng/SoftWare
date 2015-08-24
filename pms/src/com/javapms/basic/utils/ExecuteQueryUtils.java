/*    */ package com.javapms.basic.utils;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ExecuteQueryUtils
/*    */ {
/*    */   private StringBuilder hqlb;
/*    */   private Map<String, Object> maps;
/*    */ 
/*    */   private ExecuteQueryUtils(String hql)
/*    */   {
/*  9 */     this.hqlb = new StringBuilder(hql);
/*    */   }
/*    */ 
/*    */   public static ExecuteQueryUtils create(String hql) {
/* 13 */     return new ExecuteQueryUtils(hql);
/*    */   }
/*    */ 
/*    */   public ExecuteQueryUtils append(String hql) {
/* 17 */     this.hqlb.append(hql);
/* 18 */     return this;
/*    */   }
/*    */ 
/*    */   public ExecuteQueryUtils setParameter(String name, Object value) {
/* 22 */     if (this.maps == null) {
/* 23 */       this.maps = new HashMap();
/*    */     }
/* 25 */     this.maps.put(name, value);
/* 26 */     return this;
/*    */   }
/*    */ 
/*    */   public String getHql() {
/* 30 */     return this.hqlb.toString();
/*    */   }
/*    */ 
/*    */   public Map<String, Object> getMaps() {
/* 34 */     return this.maps;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.utils.ExecuteQueryUtils
 * JD-Core Version:    0.6.1
 */