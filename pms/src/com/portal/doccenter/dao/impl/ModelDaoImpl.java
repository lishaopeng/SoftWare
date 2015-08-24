/*    */ package com.portal.doccenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.doccenter.dao.ModelDao;
/*    */ import com.portal.doccenter.entity.Model;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ModelDaoImpl extends QueryDaoImpl<Model, Integer>
/*    */   implements ModelDao
/*    */ {
/*    */   public List<Model> getList(boolean containDisabled, String sortname, String sortorder)
/*    */   {
/* 21 */     Criteria crit = createCriteria();
/* 22 */     if (!containDisabled) {
/* 23 */       crit.add(Restrictions.eq("disabled", Boolean.valueOf(false)));
/*    */     }
/* 25 */     if (!StringUtils.isBlank(sortname)) {
/* 26 */       if ("asc".equals(sortorder))
/* 27 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 29 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 32 */       crit.addOrder(Order.asc("priority"));
/* 33 */       crit.addOrder(Order.asc("id"));
/*    */     }
/* 35 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public Model getDefModel() {
/* 39 */     Criteria crit = createCriteria();
/* 40 */     crit.add(Restrictions.eq("def", Boolean.valueOf(true)));
/* 41 */     return (Model)findUnique(crit);
/*    */   }
/*    */ 
/*    */   protected Class<Model> getEntityClass()
/*    */   {
/* 46 */     return Model.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.impl.ModelDaoImpl
 * JD-Core Version:    0.6.1
 */