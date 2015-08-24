/*    */ package com.portal.usermgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.usermgr.dao.GroupPermDao;
/*    */ import com.portal.usermgr.entity.GroupPerm;
/*    */ import org.hibernate.Criteria;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class GroupPermDaoImpl extends QueryDaoImpl<GroupPerm, Integer>
/*    */   implements GroupPermDao
/*    */ {
/*    */   public Page<GroupPerm> getPage(int pageNo, int pageSize)
/*    */   {
/* 14 */     Criteria crit = createCriteria();
/* 15 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   protected Class<GroupPerm> getEntityClass()
/*    */   {
/* 20 */     return GroupPerm.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.impl.GroupPermDaoImpl
 * JD-Core Version:    0.6.1
 */