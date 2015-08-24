/*     */ package com.portal.extrafunc.dao.impl;
/*     */ 
/*     */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*     */ import com.portal.extrafunc.dao.CommentDao;
/*     */ import com.portal.extrafunc.entity.Comment;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Projections;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class CommentDaoImpl extends QueryDaoImpl<Comment, Integer>
/*     */   implements CommentDao
/*     */ {
/*     */   public Page<Comment> getPage(Integer siteId, Integer docId, Integer parentId, Boolean checked, Boolean noparent, int orderBy, String sortname, String sortorder, boolean cacheable, int pageNo, int pageSize)
/*     */   {
/*  22 */     Criteria crit = createCriteria();
/*  23 */     if ((noparent != null) && (noparent.booleanValue())) {
/*  24 */       if (docId != null)
/*  25 */         crit.add(Restrictions.eq("doc.id", docId));
/*  26 */       else if (siteId != null) {
/*  27 */         crit.add(Restrictions.eq("site.id", siteId));
/*     */       }
/*  29 */       crit.add(Restrictions.isNull("parent"));
/*     */     }
/*  31 */     else if (parentId != null) {
/*  32 */       crit.add(Restrictions.eq("parent.id", parentId));
/*  33 */     } else if (docId != null) {
/*  34 */       crit.add(Restrictions.eq("doc.id", docId));
/*  35 */     } else if (siteId != null) {
/*  36 */       crit.add(Restrictions.eq("site.id", siteId));
/*     */     }
/*     */ 
/*  39 */     if (checked != null) {
/*  40 */       crit.add(Restrictions.eq("checked", checked));
/*     */     }
/*  42 */     if (orderBy == 1)
/*  43 */       crit.addOrder(Order.desc("ups"));
/*  44 */     else if (orderBy == 2)
/*  45 */       crit.addOrder(Order.desc("lastTime"));
/*  46 */     else if (orderBy == 3) {
/*  47 */       crit.addOrder(Order.desc("createTime"));
/*     */     }
/*  49 */     else if (!StringUtils.isBlank(sortname)) {
/*  50 */       if ("asc".equals(sortorder))
/*  51 */         crit.addOrder(Order.asc(sortname));
/*     */       else
/*  53 */         crit.addOrder(Order.desc(sortname));
/*     */     }
/*     */     else {
/*  56 */       crit.addOrder(Order.desc("createTime"));
/*     */     }
/*     */ 
/*  59 */     crit.setCacheable(cacheable);
/*  60 */     return findByCriteria(crit, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public Integer getAllCommentCount(Integer siteId) {
/*  64 */     Criteria crit = createCriteria();
/*  65 */     crit.add(Restrictions.eq("site.id", siteId));
/*  66 */     crit.setProjection(Projections.count("id"));
/*  67 */     return Integer.valueOf(((Number)crit.uniqueResult()).intValue());
/*     */   }
/*     */ 
/*     */   public Integer getFloorByComment(Integer commentId) {
/*  71 */     Criteria crit = createCriteria();
/*  72 */     crit.add(Restrictions.eq("parent.id", commentId));
/*  73 */     crit.setProjection(Projections.max("floor"));
/*  74 */     if (crit.uniqueResult() == null) {
/*  75 */       return Integer.valueOf(2);
/*     */     }
/*  77 */     return Integer.valueOf(((Number)crit.uniqueResult()).intValue());
/*     */   }
/*     */ 
/*     */   public int deleteByParentId(Integer parentId) {
/*  81 */     String hql = "delete from Comment bean where bean.parent.id=?";
/*  82 */     return executeQuery(hql, new Object[] { parentId });
/*     */   }
/*     */ 
/*     */   public int deleteByDocId(Integer docId) {
/*  86 */     String hql = "delete from Comment bean where bean.doc.id=?";
/*  87 */     return executeQuery(hql, new Object[] { docId });
/*     */   }
/*     */ 
/*     */   public int deleteByTreeNumber(String treeNumber) {
/*  91 */     String hql = "delete from Comment bean where bean.doc.id in (select a.id from Article a where a.channel.number like ?)";
/*     */ 
/*  93 */     return executeQuery(hql, new Object[] { treeNumber + "%" });
/*     */   }
/*     */ 
/*     */   public int deleteByDocUserId(Integer userId) {
/*  97 */     String hql = "delete from Comment bean where bean.doc.id in (select a.id from Article a where a.user.id=?)";
/*     */ 
/*  99 */     return executeQuery(hql, new Object[] { userId });
/*     */   }
/*     */ 
/*     */   public int deleteByUserId(Integer userId) {
/* 103 */     String hql = "delete from Comment bean where bean.user.id=?";
/* 104 */     return executeQuery(hql, new Object[] { userId });
/*     */   }
/*     */ 
/*     */   public int deleteByUserIdAndParent(Integer userId) {
/* 108 */     String hql = "delete from Comment bean where bean.parent.id in (select c.id from Comment c where c.user.id=?)";
/*     */ 
/* 110 */     return executeQuery(hql, new Object[] { userId });
/*     */   }
/*     */ 
/*     */   public int deleteBySiteId(Integer siteId) {
/* 114 */     String hql = "delete from Comment bean where bean.site.id=?";
/* 115 */     return executeQuery(hql, new Object[] { siteId });
/*     */   }
/*     */ 
/*     */   protected Class<Comment> getEntityClass()
/*     */   {
/* 120 */     return Comment.class;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.CommentDaoImpl
 * JD-Core Version:    0.6.1
 */