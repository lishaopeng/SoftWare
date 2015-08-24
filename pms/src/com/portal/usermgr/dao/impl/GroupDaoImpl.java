/*    */ package com.portal.usermgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.usermgr.dao.GroupDao;
/*    */ import com.portal.usermgr.entity.Group;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class GroupDaoImpl extends QueryDaoImpl<Group, Integer>
/*    */   implements GroupDao
/*    */ {
/*    */   public List<Group> getList(Integer siteId, String sortname, String sortorder)
/*    */   {
/* 19 */     Criteria crit = createCriteria();
/* 20 */     crit.add(Restrictions.eq("site.id", siteId));
/* 21 */     if (!StringUtils.isBlank(sortname)) {
/* 22 */       if ("asc".equals(sortorder))
/* 23 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 25 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 28 */       crit.addOrder(Order.asc("priority"));
/* 29 */       crit.addOrder(Order.asc("id"));
/*    */     }
/* 31 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public Group findById(Integer id) {
/* 35 */     Group entity = (Group)get(id);
/* 36 */     return entity;
/*    */   }
/*    */ 
/*    */   public Group save(Group bean) {
/* 40 */     getSession().save(bean);
/* 41 */     return bean;
/*    */   }
/*    */ 
/*    */   public Group deleteById(Integer id) {
/* 45 */     Group entity = (Group)super.get(id);
/* 46 */     if (entity != null) {
/* 47 */       getSession().delete(entity);
/*    */     }
/* 49 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<Group> getEntityClass()
/*    */   {
/* 54 */     return Group.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.impl.GroupDaoImpl
 * JD-Core Version:    0.6.1
 */