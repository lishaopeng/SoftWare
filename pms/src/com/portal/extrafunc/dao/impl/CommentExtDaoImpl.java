/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.CommentExtDao;
/*    */ import com.portal.extrafunc.entity.CommentExt;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CommentExtDaoImpl extends QueryDaoImpl<CommentExt, Integer>
/*    */   implements CommentExtDao
/*    */ {
/*    */   public int deleteByParentId(Integer parentId)
/*    */   {
/* 14 */     String hql = "delete from CommentExt bean where bean.id in (select c.id from Comment c where c.parent.id=?)";
/*    */ 
/* 16 */     return executeQuery(hql, new Object[] { parentId });
/*    */   }
/*    */ 
/*    */   public int deleteByDocId(Integer docId) {
/* 20 */     String hql = "delete from CommentExt bean where bean.id in (select c.id from Comment c where c.doc.id=?)";
/*    */ 
/* 22 */     return executeQuery(hql, new Object[] { docId });
/*    */   }
/*    */ 
/*    */   public int deleteByTreeNumber(String treeNumber) {
/* 26 */     String hql = "delete from CommentExt bean where bean.id in (select c.id from Comment c inner join c.doc a where a.channel.number like ?)";
/*    */ 
/* 28 */     return executeQuery(hql, new Object[] { treeNumber + "%" });
/*    */   }
/*    */ 
/*    */   public int deleteByDocUserId(Integer userId) {
/* 32 */     String hql = "delete from CommentExt bean where bean.id in (select c.id from Comment c inner join c.doc a where a.user.id=?)";
/*    */ 
/* 34 */     return executeQuery(hql, new Object[] { userId });
/*    */   }
/*    */ 
/*    */   public int deleteByUserId(Integer userId) {
/* 38 */     String hql = "delete from CommentExt bean where bean.id in (select c.id from Comment c inner join c.user u where u.id=?)";
/*    */ 
/* 40 */     return executeQuery(hql, new Object[] { userId });
/*    */   }
/*    */ 
/*    */   public int deleteByUserIdAndParent(Integer userId) {
/* 44 */     String hql = "delete from CommentExt bean where bean.id in (select c.id from Comment c inner join c.parent p where p.id in (select c.id from Comment c inner join c.user u where u.id=?))";
/*    */ 
/* 47 */     return executeQuery(hql, new Object[] { userId });
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 51 */     String hql = "delete from CommentExt bean where bean.id in (select c.id from Comment c inner join c.site s where s.id=?)";
/*    */ 
/* 53 */     return executeQuery(hql, new Object[] { siteId });
/*    */   }
/*    */ 
/*    */   protected Class<CommentExt> getEntityClass()
/*    */   {
/* 58 */     return CommentExt.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.CommentExtDaoImpl
 * JD-Core Version:    0.6.1
 */