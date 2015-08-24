/*    */ package com.javapms.basic.utils;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Locale;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Projection;
/*    */ import org.hibernate.internal.CriteriaImpl;
/*    */ import org.hibernate.internal.CriteriaImpl.OrderEntry;
/*    */ import org.springframework.util.Assert;
/*    */ 
/*    */ public class HibernateUtils
/*    */ {
/*    */   public static Projection getProjection(Criteria criteria)
/*    */   {
/* 18 */     CriteriaImpl impl = (CriteriaImpl)criteria;
/* 19 */     return impl.getProjection();
/*    */   }
/*    */ 
/*    */   public static CriteriaImpl.OrderEntry[] getOrders(Criteria criteria)
/*    */   {
/* 24 */     CriteriaImpl impl = (CriteriaImpl)criteria;
/* 25 */     Field field = getOrderEntriesField(criteria);
/*    */     try {
/* 27 */       return 
/* 28 */         (CriteriaImpl.OrderEntry[])((List)field.get(impl))
/* 28 */         .toArray(new CriteriaImpl.OrderEntry[0]); } catch (Exception e) {
/*    */     }
/* 30 */     throw new InternalError("异常");
/*    */   }
/*    */ 
/*    */   public static Criteria removeOrders(Criteria criteria)
/*    */   {
/* 35 */     CriteriaImpl impl = (CriteriaImpl)criteria;
/*    */     try {
/* 37 */       Field field = getOrderEntriesField(criteria);
/* 38 */       field.set(impl, new ArrayList());
/* 39 */       return impl; } catch (Exception e) {
/*    */     }
/* 41 */     throw new InternalError("查询异常");
/*    */   }
/*    */ 
/*    */   public static Criteria addOrders(Criteria criteria, CriteriaImpl.OrderEntry[] orderEntries)
/*    */   {
/* 48 */     CriteriaImpl impl = (CriteriaImpl)criteria;
/*    */     try {
/* 50 */       Field field = getOrderEntriesField(criteria);
/* 51 */       for (int i = 0; i < orderEntries.length; i++) {
/* 52 */         List innerOrderEntries = (List)field
/* 53 */           .get(criteria);
/* 54 */         innerOrderEntries.add(orderEntries[i]);
/*    */       }
/* 56 */       return impl; } catch (Exception e) {
/*    */     }
/* 58 */     throw new InternalError("异常");
/*    */   }
/*    */ 
/*    */   private static Field getOrderEntriesField(Criteria criteria)
/*    */   {
/* 63 */     Assert.notNull(criteria, "criteria不存在");
/*    */     try {
/* 65 */       Field field = CriteriaImpl.class.getDeclaredField("orderEntries");
/* 66 */       field.setAccessible(true);
/* 67 */       return field; } catch (Exception e) {
/*    */     }
/* 69 */     throw new InternalError();
/*    */   }
/*    */ 
/*    */   public static Object getSimpleProperty(Object bean, String propName)
/*    */     throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
/*    */   {
/* 77 */     return bean.getClass().getMethod(getReadMethod(propName), new Class[0]).invoke(bean, new Object[0]);
/*    */   }
/*    */ 
/*    */   private static String getReadMethod(String name) {
/* 81 */     return "get" + name.substring(0, 1).toUpperCase(Locale.ENGLISH) + 
/* 82 */       name.substring(1);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.utils.HibernateUtils
 * JD-Core Version:    0.6.1
 */