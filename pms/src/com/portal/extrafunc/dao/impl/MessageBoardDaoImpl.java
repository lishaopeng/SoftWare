/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.MessageBoardDao;
/*    */ import com.portal.extrafunc.entity.MessageBoard;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Projections;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class MessageBoardDaoImpl extends QueryDaoImpl<MessageBoard, Integer>
/*    */   implements MessageBoardDao
/*    */ {
/*    */   public Page<MessageBoard> getPage(String name, Integer siteId, Boolean show, String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 21 */     Criteria crit = createCriteria();
/* 22 */     if (!StringUtils.isBlank(name)) {
/* 23 */       crit.add(Restrictions.like("title", "%" + name + "%"));
/*    */     }
/* 25 */     if (show != null) {
/* 26 */       crit.add(Restrictions.eq("show", show));
/*    */     }
/* 28 */     crit.add(Restrictions.eq("site.id", siteId));
/* 29 */     if (!StringUtils.isBlank(sortname)) {
/* 30 */       if ("asc".equals(sortorder))
/* 31 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 33 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 36 */       crit.addOrder(Order.desc("createTime"));
/*    */     }
/* 38 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public Integer getAllMessageCount(Integer siteId) {
/* 42 */     Criteria crit = createCriteria();
/* 43 */     crit.add(Restrictions.eq("site.id", siteId));
/* 44 */     crit.setProjection(Projections.count("id"));
/* 45 */     return Integer.valueOf(((Number)crit.uniqueResult()).intValue());
/*    */   }
/*    */ 
/*    */   public Integer getNoRepMessageCount(Integer siteId) {
/* 49 */     Criteria crit = createCriteria();
/* 50 */     crit.createAlias("ext", "ext");
/* 51 */     crit.add(Restrictions.eq("site.id", siteId));
/* 52 */     crit.add(Restrictions.isNull("ext.reply"));
/* 53 */     crit.setProjection(Projections.count("id"));
/* 54 */     return Integer.valueOf(((Number)crit.uniqueResult()).intValue());
/*    */   }
/*    */ 
/*    */   protected Class<MessageBoard> getEntityClass()
/*    */   {
/* 59 */     return MessageBoard.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.MessageBoardDaoImpl
 * JD-Core Version:    0.6.1
 */