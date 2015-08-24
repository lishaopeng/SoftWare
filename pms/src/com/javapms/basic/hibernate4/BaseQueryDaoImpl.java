/*    */ package com.javapms.basic.hibernate4;
/*    */ 
/*    */ /*    */ import java.util.Collections;
/*    */ import java.util.List;

/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.SessionFactory;
/*    */ import org.hibernate.criterion.CriteriaSpecification;
/*    */ import org.hibernate.criterion.Projection;
/*    */ import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.data.domain.PageImpl;
/*    */ import org.springframework.data.domain.PageRequest;
/*    */ import org.springframework.data.domain.Pageable;

import com.javapms.basic.utils.HibernateUtils;
/*    */ 
/*    */ 
/*    */ public abstract class BaseQueryDaoImpl<T>
/*    */ {
/* 29 */   protected Logger log = LoggerFactory.getLogger(getClass());
/*    */   protected SessionFactory sessionFactory;
/*    */ 
/*    */   protected T findUnique(Criteria crit)
/*    */   {
/* 33 */     crit.setMaxResults(1);
		/* 34 */return (T) crit.uniqueResult();
/*    */   }
/*    */ 
/*    */   protected List<T> findByCriteria(Criteria crit)
/*    */   {
/* 39 */     return crit.list();
/*    */   }
/*    */ 
/*    */   protected Page<T> findByCriteria(Criteria crit, int pageNo, int pageSize)
/*    */   {
/* 44 */     CriteriaImpl.OrderEntry[] orderEntries = HibernateUtils.getOrders(crit);
/* 45 */     crit = HibernateUtils.removeOrders(crit);
/* 46 */     Projection projection = HibernateUtils.getProjection(crit);
/* 47 */     int totalCount = ((Number)crit.setProjection(Projections.rowCount())
/* 48 */       .uniqueResult()).intValue();
/* 49 */     crit.setProjection(projection);
/* 50 */     if (projection == null) {
/* 51 */       crit.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
/*    */     }
/* 53 */     crit = HibernateUtils.addOrders(crit, orderEntries);
/* 54 */     Pageable p = new PageRequest(pageNo - 1, pageSize);
/*    */     List list;
/* 56 */     if (totalCount > p.getOffset()) {
/* 57 */       crit.setFirstResult(p.getOffset());
/* 58 */       crit.setMaxResults(p.getPageSize());
/* 59 */       list = crit.list();
/*    */     } else {
/* 61 */       list = Collections.emptyList();
/*    */     }
/* 63 */     Page page = new PageImpl(list, p, totalCount);
/* 64 */     return page;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setSessionFactory(SessionFactory sessionFactory)
/*    */   {
/* 71 */     this.sessionFactory = sessionFactory;
/*    */   }
/*    */ 
/*    */   protected Session getSession() {
/* 75 */     return this.sessionFactory.getCurrentSession();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.hibernate4.BaseQueryDaoImpl
 * JD-Core Version:    0.6.1
 */