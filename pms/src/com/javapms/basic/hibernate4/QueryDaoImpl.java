/*    */ package com.javapms.basic.hibernate4;
/*    */ 
/*    */ /*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.Map;

/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.metadata.ClassMetadata;
/*    */ import org.springframework.data.domain.Page;

import com.javapms.basic.utils.ExecuteQueryUtils;
/*    */ import com.javapms.basic.utils.HibernateUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class QueryDaoImpl<T, ID extends Serializable> extends BaseQueryDaoImpl<T>
/*    */   implements QueryDao<T>
/*    */ {
/*    */   protected T get(ID id)
/*    */   {
		/* 23 */return (T) getSession().get(getEntityClass(), id);
/*    */   }
/*    */ 
/*    */   public T findById(ID id) {
/* 27 */     Object entity = get(id);
		/* 28 */return (T) entity;
/*    */   }
/*    */ 
/*    */   @Override
public T save(T bean) {
/* 32 */     getSession().save(bean);
/* 33 */     return bean;
/*    */   }
/*    */ 
/*    */   @Override
public T update(T bean)
/*    */   {
/* 38 */     ClassMetadata cm = this.sessionFactory.getClassMetadata(getEntityClass());
/* 39 */     Object po = getSession().get(getEntityClass(), 
/* 40 */       cm.getIdentifier(bean, null));
		/* 41 */updaterCopyToPersistentObject(bean, (T) po, cm);
		/* 42 */return (T) po;
/*    */   }
/*    */ 
/*    */   public T deleteById(ID id) {
/* 46 */     Object entity = get(id);
/* 47 */     if (entity != null) {
/* 48 */       getSession().delete(entity);
/*    */     }
		/* 50 */return (T) entity;
/*    */   }
/*    */ 
/*    */   @Override
public Page<T> getPage(int pageNo, int pageSize) {
/* 54 */     Criteria crit = createCriteria();
/* 55 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List executeQueryList(ExecuteQueryUtils eq) {
/* 59 */     Query query = getSession().createQuery(eq.getHql());
/* 60 */     if (eq.getMaps() != null) {
/* 61 */       for (Map.Entry entry : eq.getMaps().entrySet()) {
/* 62 */         query.setParameter((String)entry.getKey(), entry.getValue());
/*    */       }
/*    */     }
/* 65 */     return query.list();
/*    */   }
/*    */ 
/*    */   public int executeQuery(ExecuteQueryUtils eq)
/*    */   {
/* 70 */     Query query = getSession().createQuery(eq.getHql());
/* 71 */     if (eq.getMaps() != null) {
/* 72 */       for (Map.Entry entry : eq.getMaps().entrySet()) {
/* 73 */         query.setParameter((String)entry.getKey(), entry.getValue());
/*    */       }
/*    */     }
/* 76 */     return query.executeUpdate();
/*    */   }
/*    */ 
/*    */   public int executeQuery(String hql, Object[] value) {
/* 80 */     Query query = getSession().createQuery(hql);
/* 81 */     if (value != null) {
/* 82 */       for (int i = 0; i < value.length; i++) {
/* 83 */         query.setParameter(i, value[i]);
/*    */       }
/*    */     }
/* 86 */     return query.executeUpdate();
/*    */   }
/*    */ 
/*    */   protected Criteria createCriteria()
/*    */   {
/* 91 */     Criteria criteria = getSession().createCriteria(getEntityClass());
/* 92 */     return criteria;
/*    */   }
/*    */ 
/*    */   private void updaterCopyToPersistentObject(T bean, T po, ClassMetadata cm) {
/* 96 */     String[] propNames = cm.getPropertyNames();
/* 97 */     String identifierName = cm.getIdentifierPropertyName();
/*    */ 
/* 99 */     for (String propName : propNames)
/* 100 */       if (!propName.equals(identifierName))
/*    */       {
/*    */         try
/*    */         {
/* 104 */           Object value = HibernateUtils.getSimpleProperty(bean, propName);
/* 105 */           if (value != null)
/*    */           {
/* 108 */             cm.setPropertyValue(po, propName, value);
/*    */           }
/*    */         } catch (Exception e) { throw new RuntimeException("拷贝属性失败", e); }
/*    */ 
/*    */       }
/*    */   }
/*    */ 
/*    */   protected abstract Class<T> getEntityClass();
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.hibernate4.QueryDaoImpl
 * JD-Core Version:    0.6.1
 */