/*    */ package com.javapms.basic.comparator;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class BeanComparator
/*    */   implements Comparator<BeanInterface>, Serializable
/*    */ {
/*  8 */   public static final BeanComparator INSTANCE = new BeanComparator();
/*    */ 
/*    */   public int compare(BeanInterface o1, BeanInterface o2) {
/* 11 */     Number v1 = o1.getPriority();
/* 12 */     Number v2 = o2.getPriority();
/* 13 */     if (v1 == null)
/* 14 */       return 1;
/* 15 */     if (v2 == null)
/* 16 */       return -1;
/* 17 */     if (v1.longValue() > v2.longValue())
/* 18 */       return 1;
/* 19 */     if (v1.longValue() < v2.longValue()) {
/* 20 */       return -1;
/*    */     }
/* 22 */     return 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.comparator.BeanComparator
 * JD-Core Version:    0.6.1
 */