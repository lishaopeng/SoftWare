/*    */ package com.portal.datacenter.commdata.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.datacenter.commdata.dao.SpecialtyDao;
/*    */ import com.portal.datacenter.commdata.entity.Specialty;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class SpecialtyDaoImpl extends QueryDaoImpl<Specialty, Integer>
/*    */   implements SpecialtyDao
/*    */ {
/*    */   public Page<Specialty> getPage(String key, int pageNo, int pageSize)
/*    */   {
/* 20 */     Criteria crit = createCriteria();
/* 21 */     if (!StringUtils.isBlank(key)) {
/* 22 */       crit.add(Restrictions.or(
/* 23 */         Restrictions.like("code", "%" + key + "%"), 
/* 24 */         Restrictions.like("name", "%" + key + "%")));
/*    */     }
/* 26 */     crit.addOrder(Order.asc("code"));
/* 27 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<Specialty> getSpecialtyList(Integer id) {
/* 31 */     Criteria crit = createCriteria();
/* 32 */     crit.add(Restrictions.isNull("parent"));
/* 33 */     if (id != null) {
/* 34 */       crit.add(Restrictions.ne("id", id));
/*    */     }
/* 36 */     crit.addOrder(Order.asc("code"));
/* 37 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public List<Specialty> getSpecialtyChild(Integer id) {
/* 41 */     Criteria crit = createCriteria();
/* 42 */     crit.add(Restrictions.eq("parent.id", id));
/* 43 */     crit.addOrder(Order.asc("code"));
/* 44 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public Specialty findByCode(String code) {
/* 48 */     Criteria crit = createCriteria();
/* 49 */     crit.add(Restrictions.eq("code", code));
/* 50 */     return (Specialty)findUnique(crit);
/*    */   }
/*    */ 
/*    */   protected Class<Specialty> getEntityClass()
/*    */   {
/* 55 */     return Specialty.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.dao.impl.SpecialtyDaoImpl
 * JD-Core Version:    0.6.1
 */