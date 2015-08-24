/*     */ package com.portal.usermgr.dao.impl;
/*     */ 
/*     */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*     */ import com.portal.usermgr.dao.DepartDao;
/*     */ import com.portal.usermgr.entity.Depart;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class DepartDaoImpl extends QueryDaoImpl<Depart, Integer>
/*     */   implements DepartDao
/*     */ {
/*     */   public Page<Depart> getPage(String key, Integer siteId, String sortname, String sortorder, int pageNo, int pageSize)
/*     */   {
/*  21 */     Criteria crit = createCriteria();
/*  22 */     crit.add(Restrictions.eq("site.id", siteId));
/*  23 */     if (!StringUtils.isBlank(key)) {
/*  24 */       crit.add(Restrictions.like("name", "%" + key + "%"));
/*     */     }
/*  26 */     if (!StringUtils.isBlank(sortname)) {
/*  27 */       if ("asc".equals(sortorder))
/*  28 */         crit.addOrder(Order.asc(sortname));
/*     */       else
/*  30 */         crit.addOrder(Order.desc(sortname));
/*     */     }
/*     */     else {
/*  33 */       crit.addOrder(Order.asc("priority"));
/*  34 */       crit.addOrder(Order.asc("id"));
/*     */     }
/*  36 */     return findByCriteria(crit, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public List<Depart> getListByTag(Integer siteId, Integer parentId, Integer orderBy)
/*     */   {
/*  41 */     Criteria crit = createCriteria();
/*  42 */     crit.add(Restrictions.eq("site.id", siteId));
/*  43 */     crit.add(Restrictions.eq("show", Boolean.valueOf(true)));
/*  44 */     if (parentId != null) {
/*  45 */       crit.add(Restrictions.eq("parent.id", parentId));
/*     */     }
/*  47 */     if (orderBy != null) {
/*  48 */       if (orderBy.intValue() == 1) {
/*  49 */         crit.addOrder(Order.desc("signCount"));
/*  50 */         crit.addOrder(Order.asc("priority"));
/*     */       } else {
/*  52 */         crit.addOrder(Order.desc("useCount"));
/*  53 */         crit.addOrder(Order.asc("priority"));
/*     */       }
/*     */     } else {
/*  56 */       crit.addOrder(Order.asc("priority"));
/*  57 */       crit.addOrder(Order.asc("id"));
/*     */     }
/*  59 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public List<Depart> getAllList(Integer siteId) {
/*  63 */     Criteria crit = createCriteria();
/*  64 */     crit.add(Restrictions.eq("site.id", siteId));
/*  65 */     crit.addOrder(Order.asc("priority"));
/*  66 */     crit.addOrder(Order.asc("id"));
/*  67 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public List<Depart> getListNoParent(Integer siteId) {
/*  71 */     Criteria crit = createCriteria();
/*  72 */     crit.add(Restrictions.eq("site.id", siteId));
/*  73 */     crit.add(Restrictions.isNull("parent"));
/*  74 */     crit.addOrder(Order.asc("priority"));
/*  75 */     crit.addOrder(Order.asc("id"));
/*  76 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public List<Depart> getListByParent(Integer parentId) {
/*  80 */     Criteria crit = createCriteria();
/*  81 */     crit.add(Restrictions.eq("parent.id", parentId));
/*  82 */     crit.addOrder(Order.asc("priority"));
/*  83 */     crit.addOrder(Order.asc("id"));
/*  84 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public Depart getDepartByPath(String path, Integer siteId) {
/*  88 */     Criteria crit = createCriteria();
/*  89 */     crit.add(Restrictions.eq("site.id", siteId));
/*  90 */     crit.add(Restrictions.eq("visitPath", path));
/*  91 */     return (Depart)findUnique(crit);
/*     */   }
/*     */ 
/*     */   public Depart getDepartByName(String name, Integer siteId) {
/*  95 */     Criteria crit = createCriteria();
/*  96 */     crit.add(Restrictions.eq("site.id", siteId));
/*  97 */     crit.add(Restrictions.eq("name", name));
/*  98 */     return (Depart)findUnique(crit);
/*     */   }
/*     */ 
/*     */   public int deleteBySiteId(Integer siteId) {
/* 102 */     String hql = "delete from Depart bean where bean.site.id=?";
/* 103 */     return executeQuery(hql, new Object[] { siteId });
/*     */   }
/*     */ 
/*     */   protected Class<Depart> getEntityClass()
/*     */   {
/* 108 */     return Depart.class;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.impl.DepartDaoImpl
 * JD-Core Version:    0.6.1
 */