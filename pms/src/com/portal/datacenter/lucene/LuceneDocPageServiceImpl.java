/*    */ package com.portal.datacenter.lucene;
/*    */ 
/*    */ /*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;

/*    */ import org.apache.lucene.search.IndexSearcher;
/*    */ import org.apache.lucene.search.Query;
/*    */ import org.apache.lucene.search.TopDocs;
/*    */ import org.apache.lucene.search.highlight.Formatter;
/*    */ import org.apache.lucene.search.highlight.Fragmenter;
/*    */ import org.apache.lucene.search.highlight.Highlighter;
/*    */ import org.apache.lucene.search.highlight.QueryScorer;
/*    */ import org.apache.lucene.search.highlight.Scorer;
/*    */ import org.apache.lucene.search.highlight.SimpleFragmenter;
/*    */ import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.data.domain.PageImpl;
/*    */ import org.springframework.data.domain.PageRequest;
/*    */ import org.springframework.data.domain.Pageable;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;

import com.portal.doccenter.entity.Article;
/*    */ import com.portal.doccenter.service.ArticleService;
/*    */ 
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class LuceneDocPageServiceImpl
/*    */   implements LuceneDocPageService
/*    */ {
/*    */   private ArticleService articleService;
/*    */   private NRTManagerService nrtManager;
/*    */ 
/*    */   @Override
@Transactional(readOnly=true)
/*    */   public Page<Article> searchArticle(String queryKey, String attrqueryKey, Integer siteId, Integer modelId, Integer channelId, Date startDate, Date endDate, int pageNo, int pageSize)
/*    */   {
/* 37 */     IndexSearcher searcher = null;
/*    */     try {
/* 39 */       searcher = this.nrtManager.getNrtManager().acquire();
/* 40 */       Query query = LuceneCommon.createQuery(queryKey, attrqueryKey, 
/* 41 */         siteId, modelId, channelId, startDate, endDate);
/* 42 */       Scorer fragmentScore = new QueryScorer(query);
/* 43 */       Fragmenter fragmenter = new SimpleFragmenter(100);
/* 44 */       Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", 
/* 45 */         "</font>");
/* 46 */       Highlighter highlighter = new Highlighter(formatter, fragmentScore);
/* 47 */       highlighter.setTextFragmenter(fragmenter);
/* 48 */       TopDocs topdocs = searcher.search(query, pageNo * pageSize);
/*    */ 
/* 50 */       Page p = LuceneCommon.getResultPage(searcher, topdocs, 
/* 51 */         pageNo, pageSize);
/* 52 */       Pageable pa = new PageRequest(pageNo - 1, pageSize);
			/* 53 */List<Integer> ids = p.getContent();
/* 54 */       List docs = new ArrayList(ids.size());
/* 55 */       for (Integer id : ids) {
/* 56 */         docs.add(this.articleService.findById(id));
/*    */       }
/* 58 */       Page page = new PageImpl(docs, pa, 
/* 59 */         p.getTotalElements());
/* 60 */       return page;
/*    */     } catch (Exception e) {
/* 62 */       e.printStackTrace();
/*    */     } finally {
/*    */       try {
/* 65 */         this.nrtManager.getNrtManager().release(searcher);
/*    */       } catch (IOException e) {
/* 67 */         e.printStackTrace();
/*    */       }
/*    */     }
/* 70 */     return null;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setArticleService(ArticleService articleService)
/*    */   {
/* 78 */     this.articleService = articleService;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setNrtManager(NRTManagerService nrtManager) {
/* 83 */     this.nrtManager = nrtManager;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.lucene.LuceneDocPageServiceImpl
 * JD-Core Version:    0.6.1
 */