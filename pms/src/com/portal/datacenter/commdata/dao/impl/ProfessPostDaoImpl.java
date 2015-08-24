/*    */ package com.portal.datacenter.commdata.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.datacenter.commdata.dao.ProfessPostDao;
/*    */ import com.portal.datacenter.commdata.entity.ProfessPost;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ProfessPostDaoImpl extends QueryDaoImpl<ProfessPost, Integer>
/*    */   implements ProfessPostDao
/*    */ {
/*    */   public Page<ProfessPost> getPage(int pageNo, int pageSize)
/*    */   {
/* 19 */     Criteria crit = createCriteria();
/* 20 */     crit.addOrder(Order.asc("code"));
/* 21 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<ProfessPost> getProfessPostList(Integer id) {
/* 25 */     Criteria crit = createCriteria();
/* 26 */     if (id != null) {
/* 27 */       crit.add(Restrictions.ne("id", id));
/*    */     }
/* 29 */     crit.addOrder(Order.asc("code"));
/* 30 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   protected Class<ProfessPost> getEntityClass()
/*    */   {
/* 35 */     return ProfessPost.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.dao.impl.ProfessPostDaoImpl
 * JD-Core Version:    0.6.1
 */