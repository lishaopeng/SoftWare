/*    */ package com.portal.usermgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.usermgr.dao.RoleDao;
/*    */ import com.portal.usermgr.entity.Role;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class RoleDaoImpl extends QueryDaoImpl<Role, Integer>
/*    */   implements RoleDao
/*    */ {
/*    */   public Page<Role> getPage(String name, Integer siteId, String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 20 */     Criteria crit = createCriteria();
/* 21 */     if (!StringUtils.isBlank(name)) {
/* 22 */       crit.add(Restrictions.like("name", "%" + name + "%"));
/*    */     }
/* 24 */     if (siteId != null) {
/* 25 */       crit.add(Restrictions.eq("site.id", siteId));
/*    */     }
/* 27 */     if (!StringUtils.isBlank(sortname)) {
/* 28 */       if ("asc".equals(sortorder))
/* 29 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 31 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 34 */       crit.addOrder(Order.asc("priority"));
/*    */     }
/* 36 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<Role> getListBySite(Integer siteId) {
/* 40 */     Criteria crit = createCriteria();
/* 41 */     crit.add(Restrictions.eq("site.id", siteId));
/* 42 */     crit.addOrder(Order.asc("priority"));
/* 43 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 47 */     String hql = "delete from Role bean where bean.site.id=?";
/* 48 */     return executeQuery(hql, new Object[] { siteId });
/*    */   }
/*    */ 
/*    */   protected Class<Role> getEntityClass()
/*    */   {
/* 53 */     return Role.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.impl.RoleDaoImpl
 * JD-Core Version:    0.6.1
 */