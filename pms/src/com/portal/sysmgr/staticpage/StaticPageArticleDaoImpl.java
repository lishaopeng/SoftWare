/*    */ package com.portal.sysmgr.staticpage;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.datacenter.docdata.service.KeywordService;
/*    */ import com.portal.doccenter.entity.Article;
/*    */ import freemarker.template.Configuration;
/*    */ import freemarker.template.TemplateException;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletContext;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.CacheMode;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.ScrollMode;
/*    */ import org.hibernate.ScrollableResults;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class StaticPageArticleDaoImpl extends QueryDaoImpl<Article, Integer>
/*    */   implements StaticPageArticleDao
/*    */ {
/*    */   public String staticArticlePage(String treeNumber, Configuration config, ServletContext ctx, KeywordService keywordService)
/*    */     throws IOException, TemplateException
/*    */   {
/* 37 */     Criteria crit = createCriteria();
/* 38 */     if (!StringUtils.isBlank(treeNumber)) {
/* 39 */       crit.createAlias("channel", "c");
/* 40 */       crit.add(Restrictions.like("c.number", treeNumber + "%"));
/*    */     }
/* 42 */     crit.add(Restrictions.eq("status", Byte.valueOf((byte)2)));
/* 43 */     Session session = getSession();
/* 44 */     ScrollableResults articles = crit.setCacheMode(CacheMode.IGNORE)
/* 45 */       .scroll(ScrollMode.FORWARD_ONLY);
/*    */ 
/* 47 */     int count = 0; int counts = 0; int countn = 0; int countf = 0;
/* 48 */     while (articles.next()) {
/* 49 */       Article article = (Article)articles.get(0);
/* 50 */       Integer i = StaticArticle.staticArticle(article, config, ctx, 
/* 51 */         keywordService);
/* 52 */       if (i == null)
/* 53 */         counts++;
/* 54 */       else if (i.intValue() == 0)
/* 55 */         countn++;
/*    */       else {
/* 57 */         countf++;
/*    */       }
/* 59 */       count++; if (count % 20 == 0) {
/* 60 */         session.clear();
/*    */       }
/*    */     }
/* 63 */     StringBuffer sb = new StringBuffer();
/* 64 */     sb.append("成功生成静态页信息");
/* 65 */     sb.append(counts).append("篇,");
/* 66 */     sb.append("生成失败").append(countf).append("篇,");
/* 67 */     sb.append("未生成静态页").append(countn).append("篇!");
/* 68 */     return sb.toString();
/*    */   }
/*    */ 
/*    */   protected Class<Article> getEntityClass() {
/* 72 */     return Article.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.staticpage.StaticPageArticleDaoImpl
 * JD-Core Version:    0.6.1
 */