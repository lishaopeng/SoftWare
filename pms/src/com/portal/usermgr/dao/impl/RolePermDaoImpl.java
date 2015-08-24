/*    */ package com.portal.usermgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.usermgr.dao.RolePermDao;
/*    */ import com.portal.usermgr.entity.RolePerm;
/*    */ import org.hibernate.Criteria;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class RolePermDaoImpl extends QueryDaoImpl<RolePerm, Integer>
/*    */   implements RolePermDao
/*    */ {
/*    */   public Page<RolePerm> getPage(int pageNo, int pageSize)
/*    */   {
/* 15 */     Criteria crit = createCriteria();
/* 16 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 20 */     String hql = "delete from RolePerm bean where bean.id in (select r.id from Role r inner join r.site s where s.id=?)";
/*    */ 
/* 22 */     return executeQuery(hql, new Object[] { siteId });
/*    */   }
/*    */ 
/*    */   protected Class<RolePerm> getEntityClass()
/*    */   {
/* 27 */     return RolePerm.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.impl.RolePermDaoImpl
 * JD-Core Version:    0.6.1
 */