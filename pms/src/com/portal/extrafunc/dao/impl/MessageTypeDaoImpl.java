/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.MessageTypeDao;
/*    */ import com.portal.extrafunc.entity.MessageType;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class MessageTypeDaoImpl extends QueryDaoImpl<MessageType, Integer>
/*    */   implements MessageTypeDao
/*    */ {
/*    */   public Page<MessageType> getPage(int pageNo, int pageSize)
/*    */   {
/* 20 */     Criteria crit = createCriteria();
/* 21 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<MessageType> getList(Integer siteId, String sortname, String sortorder)
/*    */   {
/* 26 */     Criteria crit = createCriteria();
/* 27 */     crit.add(Restrictions.eq("site.id", siteId));
/* 28 */     if (!StringUtils.isBlank(sortname)) {
/* 29 */       if ("asc".equals(sortorder))
/* 30 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 32 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 35 */       crit.addOrder(Order.asc("priority"));
/*    */     }
/* 37 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public MessageType getUniqueType(Integer siteId) {
/* 41 */     Criteria crit = createCriteria();
/* 42 */     crit.add(Restrictions.eq("site.id", siteId));
/* 43 */     crit.addOrder(Order.asc("priority"));
/* 44 */     return (MessageType)findUnique(crit);
/*    */   }
/*    */ 
/*    */   protected Class<MessageType> getEntityClass()
/*    */   {
/* 49 */     return MessageType.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.MessageTypeDaoImpl
 * JD-Core Version:    0.6.1
 */