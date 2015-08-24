/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.ForumExtDao;
/*    */ import com.portal.extrafunc.entity.ForumExt;
/*    */ import org.hibernate.Criteria;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ForumExtDaoImpl extends QueryDaoImpl<ForumExt, Integer>
/*    */   implements ForumExtDao
/*    */ {
/*    */   public Page<ForumExt> getPage(int pageNo, int pageSize)
/*    */   {
/* 15 */     Criteria crit = createCriteria();
/* 16 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public int deleteByCategoryId(Integer categoryId) {
/* 20 */     String hql = "delete from ForumExt bean where bean.id in (select f.id from Forum f inner join f.category c where c.id=?)";
/*    */ 
/* 22 */     return executeQuery(hql, new Object[] { categoryId });
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 26 */     String hql = "delete from ForumExt bean where bean.id in (select f.id from Forum f inner join f.site s where s.id=?)";
/*    */ 
/* 28 */     return executeQuery(hql, new Object[] { siteId });
/*    */   }
/*    */ 
/*    */   protected Class<ForumExt> getEntityClass()
/*    */   {
/* 33 */     return ForumExt.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.ForumExtDaoImpl
 * JD-Core Version:    0.6.1
 */