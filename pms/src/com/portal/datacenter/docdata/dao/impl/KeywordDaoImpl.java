/*    */ package com.portal.datacenter.docdata.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.datacenter.docdata.dao.KeywordDao;
/*    */ import com.portal.datacenter.docdata.entity.Keyword;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class KeywordDaoImpl extends QueryDaoImpl<Keyword, Integer>
/*    */   implements KeywordDao
/*    */ {
/*    */   public List<Keyword> getList(Integer siteId, boolean enable, boolean cacheable, String sortname, String sortorder)
/*    */   {
/* 20 */     Criteria crit = createCriteria();
/* 21 */     if (siteId != null) {
/* 22 */       crit.add(Restrictions.eq("site.id", siteId));
/*    */     }
/* 24 */     if (enable) {
/* 25 */       crit.add(Restrictions.eq("enable", Boolean.valueOf(true)));
/*    */     }
/* 27 */     if (!StringUtils.isBlank(sortname)) {
/* 28 */       if ("asc".equals(sortorder))
/* 29 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 31 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 34 */       crit.addOrder(Order.desc("id"));
/*    */     }
/* 36 */     crit.setCacheable(cacheable);
/* 37 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   protected Class<Keyword> getEntityClass()
/*    */   {
/* 42 */     return Keyword.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.dao.impl.KeywordDaoImpl
 * JD-Core Version:    0.6.1
 */