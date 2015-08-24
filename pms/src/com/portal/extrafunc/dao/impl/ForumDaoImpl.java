/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.ForumDao;
/*    */ import com.portal.extrafunc.entity.Forum;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ForumDaoImpl extends QueryDaoImpl<Forum, Integer>
/*    */   implements ForumDao
/*    */ {
/*    */   public Page<Forum> getPage(Integer siteId, Integer categoryId, String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 21 */     Criteria crit = createCriteria();
/* 22 */     if (siteId != null) {
/* 23 */       crit.add(Restrictions.eq("site.id", siteId));
/*    */     }
/* 25 */     if (categoryId != null) {
/* 26 */       crit.add(Restrictions.eq("category.id", categoryId));
/*    */     }
/* 28 */     if (!StringUtils.isBlank(sortname)) {
/* 29 */       if ("asc".equals(sortorder))
/* 30 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 32 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 35 */       crit.addOrder(Order.asc("priority"));
/*    */     }
/* 37 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<Forum> getList(Integer categoryId) {
/* 41 */     Criteria crit = createCriteria();
/* 42 */     if (categoryId != null) {
/* 43 */       crit.add(Restrictions.eq("category.id", categoryId));
/*    */     }
/* 45 */     crit.addOrder(Order.asc("priority"));
/* 46 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public List<Forum> getList() {
/* 50 */     Criteria crit = createCriteria();
/* 51 */     crit.addOrder(Order.asc("priority"));
/* 52 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public int deleteByCategoryId(Integer categoryId) {
/* 56 */     String hql = "delete from Forum bean where bean.category.id=?";
/* 57 */     return executeQuery(hql, new Object[] { categoryId });
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 61 */     String hql = "delete from Forum bean where bean.site.id=?";
/* 62 */     return executeQuery(hql, new Object[] { siteId });
/*    */   }
/*    */ 
/*    */   protected Class<Forum> getEntityClass()
/*    */   {
/* 67 */     return Forum.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.ForumDaoImpl
 * JD-Core Version:    0.6.1
 */