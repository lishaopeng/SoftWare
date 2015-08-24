/*     */ package com.portal.extrafunc.dao.impl;
/*     */ 
/*     */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*     */ import com.portal.extrafunc.dao.ThemeDao;
/*     */ import com.portal.extrafunc.entity.Theme;
/*     */ import java.util.List;
/*     */ import org.hibernate.CacheMode;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.ScrollMode;
/*     */ import org.hibernate.ScrollableResults;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class ThemeDaoImpl extends QueryDaoImpl<Theme, Integer>
/*     */   implements ThemeDao
/*     */ {
/*     */   public Page<Theme> getPage(int pageNo, int pageSize)
/*     */   {
/*  23 */     Criteria crit = createCriteria();
/*  24 */     return findByCriteria(crit, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public Page<Theme> getThemePageForTag(Integer forumId, Integer status, Integer createrId, Integer replyId, int orderBy, int pageNo, int pageSize)
/*     */   {
/*  30 */     Criteria crit = createCriteria();
/*  31 */     if (forumId != null) {
/*  32 */       crit.add(Restrictions.eq("forum.id", forumId));
/*     */     }
/*  34 */     if (status != null) {
/*  35 */       crit.add(Restrictions.ge("status", status));
/*     */     }
/*  37 */     if (createrId != null) {
/*  38 */       crit.add(Restrictions.eq("creater.id", createrId));
/*     */     }
/*  40 */     if (replyId != null) {
/*  41 */       crit.createAlias("txt", "t");
/*  42 */       crit.add(Restrictions.like("t.content", "%," + replyId + ",%"));
/*     */     }
/*  44 */     appendOrder(crit, orderBy);
/*  45 */     return findByCriteria(crit, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public List<Theme> getThemeByTop() {
/*  49 */     Criteria crit = createCriteria();
/*  50 */     crit.add(Restrictions.gt("status", Integer.valueOf(0)));
/*  51 */     crit.addOrder(Order.desc("status"));
/*  52 */     crit.addOrder(Order.desc("lastReplyTime"));
/*  53 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public List<Theme> getThemeByLight() {
/*  57 */     Criteria crit = createCriteria();
/*  58 */     crit.add(Restrictions.isNotNull("color"));
/*  59 */     crit.addOrder(Order.desc("status"));
/*  60 */     crit.addOrder(Order.desc("lastReplyTime"));
/*  61 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public List<Theme> getThemeByLock() {
/*  65 */     Criteria crit = createCriteria();
/*  66 */     crit.add(Restrictions.eq("lock", Boolean.valueOf(true)));
/*  67 */     crit.addOrder(Order.desc("status"));
/*  68 */     crit.addOrder(Order.desc("lastReplyTime"));
/*  69 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public List<Theme> getThemeAll() {
/*  73 */     Criteria crit = createCriteria();
/*  74 */     crit.addOrder(Order.desc("status"));
/*  75 */     crit.addOrder(Order.desc("lastReplyTime"));
/*  76 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public int deleteByForumId(Integer forumId) {
/*  80 */     String hql = "delete from Theme bean where bean.forum.id=?";
/*  81 */     return executeQuery(hql, new Object[] { forumId });
/*     */   }
/*     */ 
/*     */   public int deleteByUserId(Integer userId) {
/*  85 */     String hql = "delete from Theme bean where bean.creater.id=?";
/*  86 */     return executeQuery(hql, new Object[] { userId });
/*     */   }
/*     */ 
/*     */   public int deleteBySiteId(Integer siteId) {
/*  90 */     String hql = "delete from Theme bean where bean.site.id=?";
/*  91 */     return executeQuery(hql, new Object[] { siteId });
/*     */   }
/*     */ 
/*     */   public int deleteByCategoryId(Integer categoryId) {
/*  95 */     Criteria crit = createCriteria();
/*  96 */     crit.createAlias("forum", "f");
/*  97 */     crit.add(Restrictions.eq("f.category.id", categoryId));
/*  98 */     Session session = getSession();
/*  99 */     ScrollableResults themes = crit.setCacheMode(CacheMode.IGNORE).scroll(
/* 100 */       ScrollMode.FORWARD_ONLY);
/*     */ 
/* 102 */     int count = 0;
/* 103 */     while (themes.next()) {
/* 104 */       Theme theme = (Theme)themes.get(0);
/* 105 */       session.delete(theme);
/* 106 */       count++; if (count % 20 == 0) {
/* 107 */         session.clear();
/*     */       }
/*     */     }
/* 110 */     return count;
/*     */   }
/*     */ 
/*     */   private void appendOrder(Criteria crit, int orderBy) {
/* 114 */     switch (orderBy) {
/*     */     case 1:
/* 116 */       crit.addOrder(Order.desc("status"));
/* 117 */       crit.addOrder(Order.desc("replyCount"));
/* 118 */       break;
/*     */     case 2:
/* 120 */       crit.addOrder(Order.desc("status"));
/* 121 */       crit.addOrder(Order.desc("viewsCount"));
/* 122 */       break;
/*     */     case 3:
/* 124 */       crit.addOrder(Order.desc("replyCount"));
/* 125 */       break;
/*     */     case 4:
/* 127 */       crit.addOrder(Order.desc("viewsCount"));
/* 128 */       break;
/*     */     default:
/* 130 */       crit.addOrder(Order.desc("status"));
/* 131 */       crit.addOrder(Order.desc("lastReplyTime"));
/*     */     }
/*     */   }
/*     */ 
/*     */   protected Class<Theme> getEntityClass()
/*     */   {
/* 137 */     return Theme.class;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.ThemeDaoImpl
 * JD-Core Version:    0.6.1
 */