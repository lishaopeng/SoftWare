/*    */ package com.portal.datacenter.lucene;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.doccenter.entity.Article;
/*    */ import java.io.IOException;
/*    */ import java.util.Date;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.apache.lucene.index.CorruptIndexException;
/*    */ import org.hibernate.CacheMode;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.ScrollMode;
/*    */ import org.hibernate.ScrollableResults;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class LuceneDocDaoImpl extends QueryDaoImpl<Article, Integer>
/*    */   implements LuceneDocDao
/*    */ {
/*    */   public Integer index(NRTManagerService service, Integer siteId, String number, Date startDate, Date endDate, Integer startId, Integer max)
/*    */     throws CorruptIndexException, IOException
/*    */   {
/* 27 */     Criteria crit = createCriteria();
/* 28 */     crit.createAlias("channel", "c");
/* 29 */     if (!StringUtils.isBlank(number))
/* 30 */       crit.add(Restrictions.like("c.number", number + "%"));
/* 31 */     else if (siteId != null) {
/* 32 */       crit.add(Restrictions.eq("site.id", siteId));
/*    */     }
/* 34 */     if (startId != null) {
/* 35 */       crit.add(Restrictions.gt("id", startId));
/*    */     }
/* 37 */     if (startDate != null) {
/* 38 */       crit.add(Restrictions.ge("releaseDate", startDate));
/*    */     }
/* 40 */     if (endDate != null) {
/* 41 */       crit.add(Restrictions.le("releaseDate", endDate));
/*    */     }
/* 43 */     crit.add(Restrictions.eq("status", Byte.valueOf((byte)2)));
/* 44 */     crit.addOrder(Order.asc("id"));
/* 45 */     if (max != null) {
/* 46 */       crit.setMaxResults(max.intValue());
/*    */     }
/* 48 */     Session session = getSession();
/* 49 */     ScrollableResults articles = crit.setCacheMode(CacheMode.IGNORE)
/* 50 */       .scroll(ScrollMode.FORWARD_ONLY);
/* 51 */     int count = 0;
/* 52 */     Article doc = null;
/* 53 */     while (articles.next()) {
/* 54 */       doc = (Article)articles.get(0);
/* 55 */       service.addDoc(LuceneCommon.createDoc(doc));
/* 56 */       count++; if (count % 20 == 0) {
/* 57 */         session.clear();
/*    */       }
/*    */     }
/* 60 */     if ((count < max.intValue()) || (doc == null)) {
/* 61 */       return null;
/*    */     }
/* 63 */     return doc.getId();
/*    */   }
/*    */ 
/*    */   protected Class<Article> getEntityClass()
/*    */   {
/* 68 */     return Article.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.lucene.LuceneDocDaoImpl
 * JD-Core Version:    0.6.1
 */