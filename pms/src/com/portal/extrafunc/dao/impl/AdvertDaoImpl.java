/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.AdvertDao;
/*    */ import com.portal.extrafunc.entity.Advert;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class AdvertDaoImpl extends QueryDaoImpl<Advert, Integer>
/*    */   implements AdvertDao
/*    */ {
/*    */   public Page<Advert> getPage(Integer siteId, Integer slotId, String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 22 */     Criteria crit = createCriteria();
/* 23 */     crit.add(Restrictions.eq("site.id", siteId));
/* 24 */     if (slotId != null) {
/* 25 */       crit.add(Restrictions.eq("slot.id", slotId));
/*    */     }
/* 27 */     if (!StringUtils.isBlank(sortname)) {
/* 28 */       if ("asc".equals(sortorder))
/* 29 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 31 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 34 */       crit.addOrder(Order.asc("priority"));
/*    */     }
/* 36 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<Advert> getListByTag(Integer siteId, Integer slotId) {
/* 40 */     Criteria crit = createCriteria();
/* 41 */     crit.add(Restrictions.eq("site.id", siteId));
/* 42 */     if (slotId != null) {
/* 43 */       crit.add(Restrictions.eq("slot.id", slotId));
/*    */     }
/* 45 */     crit.add(Restrictions.eq("enable", Boolean.valueOf(true)));
/* 46 */     crit.add(Restrictions.le("startTime", new Date()));
/* 47 */     crit.add(Restrictions.or(Restrictions.isNull("endTime"), 
/* 48 */       Restrictions.ge("endTime", new Date())));
/* 49 */     crit.addOrder(Order.asc("priority"));
/* 50 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public int deleteBySlotId(Integer slotId) {
/* 54 */     String hql = "delete from Advert bean where bean.slot.id=?";
/* 55 */     return executeQuery(hql, new Object[] { slotId });
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 59 */     String hql = "delete from Advert bean where bean.site.id=?";
/* 60 */     return executeQuery(hql, new Object[] { siteId });
/*    */   }
/*    */ 
/*    */   protected Class<Advert> getEntityClass()
/*    */   {
/* 65 */     return Advert.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.AdvertDaoImpl
 * JD-Core Version:    0.6.1
 */