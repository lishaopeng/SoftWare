/*    */ package com.portal.doccenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.doccenter.dao.ModelFieldDao;
/*    */ import com.portal.doccenter.entity.ModelField;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Projections;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ModelFieldDaoImpl extends QueryDaoImpl<ModelField, Integer>
/*    */   implements ModelFieldDao
/*    */ {
/*    */   public List<ModelField> getList(Integer modelId, boolean hasDisabled, String sortname, String sortorder)
/*    */   {
/* 22 */     Criteria crit = createCriteria();
/* 23 */     crit.add(Restrictions.eq("model.id", modelId));
/* 24 */     if (!hasDisabled) {
/* 25 */       crit.add(Restrictions.eq("show", Boolean.valueOf(true)));
/*    */     }
/* 27 */     if (!StringUtils.isBlank(sortname)) {
/* 28 */       if ("asc".equals(sortorder))
/* 29 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 31 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 34 */       crit.addOrder(Order.asc("priority"));
/* 35 */       crit.addOrder(Order.asc("id"));
/*    */     }
/* 37 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public List<ModelField> getListByPriority(Integer modelId, Integer priority, Integer priority1, boolean hasDisabled)
/*    */   {
/* 42 */     Criteria crit = createCriteria();
/* 43 */     crit.add(Restrictions.eq("model.id", modelId));
/* 44 */     crit.add(Restrictions.ge("priority", priority));
/* 45 */     crit.add(Restrictions.lt("priority", priority1));
/* 46 */     if (!hasDisabled) {
/* 47 */       crit.add(Restrictions.eq("show", Boolean.valueOf(true)));
/*    */     }
/* 49 */     crit.addOrder(Order.asc("priority"));
/* 50 */     crit.addOrder(Order.asc("id"));
/* 51 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public int getMaxPriority(Integer modelId) {
/* 55 */     Criteria crit = createCriteria();
/* 56 */     crit.add(Restrictions.eq("model.id", modelId));
/* 57 */     crit.setProjection(Projections.max("priority"));
/* 58 */     return ((Integer)crit.uniqueResult()).intValue();
/*    */   }
/*    */ 
/*    */   protected Class<ModelField> getEntityClass()
/*    */   {
/* 63 */     return ModelField.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.impl.ModelFieldDaoImpl
 * JD-Core Version:    0.6.1
 */