/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.LinksTypeDao;
/*    */ import com.portal.extrafunc.entity.LinksType;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class LinksTypeDaoImpl extends QueryDaoImpl<LinksType, Integer>
/*    */   implements LinksTypeDao
/*    */ {
/*    */   public List<LinksType> getList(Integer siteId, String sortname, String sortorder)
/*    */   {
/* 21 */     Criteria crit = createCriteria();
/* 22 */     crit.add(Restrictions.eq("site.id", siteId));
/* 23 */     if (!StringUtils.isBlank(sortname)) {
/* 24 */       if ("asc".equals(sortorder))
/* 25 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 27 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 30 */       crit.addOrder(Order.asc("priority"));
/*    */     }
/* 32 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   protected Class<LinksType> getEntityClass()
/*    */   {
/* 37 */     return LinksType.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.LinksTypeDaoImpl
 * JD-Core Version:    0.6.1
 */