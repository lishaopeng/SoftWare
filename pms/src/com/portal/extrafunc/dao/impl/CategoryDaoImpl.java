/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.CategoryDao;
/*    */ import com.portal.extrafunc.entity.Category;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CategoryDaoImpl extends QueryDaoImpl<Category, Integer>
/*    */   implements CategoryDao
/*    */ {
/*    */   public Page<Category> getPage(String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 21 */     Criteria crit = createCriteria();
/* 22 */     if (!StringUtils.isBlank(sortname)) {
/* 23 */       if ("asc".equals(sortorder))
/* 24 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 26 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 29 */       crit.addOrder(Order.asc("priority"));
/*    */     }
/* 31 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<Category> getList(Integer siteId, String sortname, String sortorder)
/*    */   {
/* 36 */     Criteria crit = createCriteria();
/* 37 */     crit.add(Restrictions.eq("site.id", siteId));
/* 38 */     if (!StringUtils.isBlank(sortname)) {
/* 39 */       if ("asc".equals(sortorder))
/* 40 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 42 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 45 */       crit.addOrder(Order.asc("priority"));
/*    */     }
/* 47 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 51 */     String hql = "delete from Category bean where bean.site.id=?";
/* 52 */     return executeQuery(hql, new Object[] { siteId });
/*    */   }
/*    */ 
/*    */   protected Class<Category> getEntityClass()
/*    */   {
/* 57 */     return Category.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.CategoryDaoImpl
 * JD-Core Version:    0.6.1
 */