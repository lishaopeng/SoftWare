/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.AdvertSlotDao;
/*    */ import com.portal.extrafunc.entity.AdvertSlot;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class AdvertSlotDaoImpl extends QueryDaoImpl<AdvertSlot, Integer>
/*    */   implements AdvertSlotDao
/*    */ {
/*    */   public Page<AdvertSlot> getPage(Integer siteId, String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 21 */     Criteria crit = createCriteria();
/* 22 */     crit.add(Restrictions.eq("site.id", siteId));
/* 23 */     if (!StringUtils.isBlank(sortname)) {
/* 24 */       if ("asc".equals(sortorder))
/* 25 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 27 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 30 */       crit.addOrder(Order.asc("id"));
/*    */     }
/* 32 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<AdvertSlot> getList(Integer siteId) {
/* 36 */     Criteria crit = createCriteria();
/* 37 */     crit.add(Restrictions.eq("site.id", siteId));
/* 38 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 42 */     String hql = "delete from AdvertSlot bean where bean.site.id=?";
/* 43 */     return executeQuery(hql, new Object[] { siteId });
/*    */   }
/*    */ 
/*    */   protected Class<AdvertSlot> getEntityClass()
/*    */   {
/* 48 */     return AdvertSlot.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.AdvertSlotDaoImpl
 * JD-Core Version:    0.6.1
 */