/*    */ package com.portal.doccenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.doccenter.dao.ArticleTypeDao;
/*    */ import com.portal.doccenter.entity.ArticleType;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ArticleTypeDaoImpl extends QueryDaoImpl<ArticleType, Integer>
/*    */   implements ArticleTypeDao
/*    */ {
/*    */   public List<ArticleType> getList(boolean containDisabled, String sortname, String sortorder)
/*    */   {
/* 20 */     Criteria crit = createCriteria();
/* 21 */     if (!containDisabled) {
/* 22 */       crit.add(Restrictions.eq("disabled", Boolean.valueOf(false)));
/*    */     }
/* 24 */     if (!StringUtils.isBlank(sortname)) {
/* 25 */       if ("asc".equals(sortorder))
/* 26 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 28 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 31 */       crit.addOrder(Order.asc("id"));
/*    */     }
/* 33 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public ArticleType getDef() {
/* 37 */     Criteria crit = createCriteria();
/* 38 */     crit.add(Restrictions.eq("disabled", Boolean.valueOf(false)));
/* 39 */     crit.addOrder(Order.asc("id"));
/* 40 */     return (ArticleType)findUnique(crit);
/*    */   }
/*    */ 
/*    */   protected Class<ArticleType> getEntityClass()
/*    */   {
/* 45 */     return ArticleType.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.impl.ArticleTypeDaoImpl
 * JD-Core Version:    0.6.1
 */