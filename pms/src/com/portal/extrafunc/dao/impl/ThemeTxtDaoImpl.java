/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.ThemeTxtDao;
/*    */ import com.portal.extrafunc.entity.ThemeTxt;
/*    */ import org.hibernate.Criteria;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ThemeTxtDaoImpl extends QueryDaoImpl<ThemeTxt, Integer>
/*    */   implements ThemeTxtDao
/*    */ {
/*    */   public Page<ThemeTxt> getPage(int pageNo, int pageSize)
/*    */   {
/* 15 */     Criteria crit = createCriteria();
/* 16 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public int deleteByForumId(Integer forumId) {
/* 20 */     String hql = "delete from ThemeTxt bean where bean.id in (select t.id from Theme t inner join t.forum f where f.id=?)";
/*    */ 
/* 22 */     return executeQuery(hql, new Object[] { forumId });
/*    */   }
/*    */ 
/*    */   public int deleteByUserId(Integer userId) {
/* 26 */     String hql = "delete from ThemeTxt bean where bean.id in (select t.id from Theme t inner join t.creater c where c.id=?)";
/*    */ 
/* 28 */     return executeQuery(hql, new Object[] { userId });
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 32 */     String hql = "delete from Theme bean where bean.site.id=? (select t.id from Theme t inner join t.site s where s.id=?)";
/*    */ 
/* 34 */     return executeQuery(hql, new Object[] { siteId });
/*    */   }
/*    */ 
/*    */   protected Class<ThemeTxt> getEntityClass()
/*    */   {
/* 39 */     return ThemeTxt.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.ThemeTxtDaoImpl
 * JD-Core Version:    0.6.1
 */