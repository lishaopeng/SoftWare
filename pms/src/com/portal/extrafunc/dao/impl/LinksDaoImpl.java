/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.LinksDao;
/*    */ import com.portal.extrafunc.entity.Links;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class LinksDaoImpl extends QueryDaoImpl<Links, Integer>
/*    */   implements LinksDao
/*    */ {
/*    */   public Page<Links> getPage(Integer siteId, Integer typeId, String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 22 */     Criteria crit = createCriteria();
/* 23 */     crit.add(Restrictions.eq("site.id", siteId));
/* 24 */     if (typeId != null) {
/* 25 */       crit.add(Restrictions.eq("type.id", typeId));
/*    */     }
/* 27 */     if (!StringUtils.isBlank(sortname)) {
/* 28 */       if ("asc".equals(sortorder))
/* 29 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 31 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 34 */       crit.addOrder(Order.asc("priority"));
/*    */     }
/* 36 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<Links> getListByTag(Integer siteId, Integer typeId, Integer count)
/*    */   {
/* 41 */     Criteria crit = createCriteria();
/* 42 */     crit.add(Restrictions.eq("site.id", siteId));
/* 43 */     if (typeId != null) {
/* 44 */       crit.add(Restrictions.eq("type.id", typeId));
/*    */     }
/* 46 */     crit.add(Restrictions.eq("show", Boolean.valueOf(true)));
/* 47 */     crit.addOrder(Order.asc("priority"));
/* 48 */     if (count != null) {
/* 49 */       crit.setMaxResults(count.intValue());
/*    */     }
/* 51 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   protected Class<Links> getEntityClass()
/*    */   {
/* 56 */     return Links.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.LinksDaoImpl
 * JD-Core Version:    0.6.1
 */