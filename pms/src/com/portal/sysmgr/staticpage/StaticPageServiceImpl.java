/*     */ package com.portal.sysmgr.staticpage;
/*     */ 
/*     */ import com.portal.datacenter.docdata.service.KeywordService;
/*     */ import com.portal.doccenter.entity.Article;
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import freemarker.template.Configuration;
/*     */ import freemarker.template.TemplateException;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javax.servlet.ServletContext;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class StaticPageServiceImpl
/*     */   implements StaticPageService
/*     */ {
/*     */   private FreeMarkerConfigurer freeMarkerConfigurer;
/*     */   private ServletContext context;
/*     */   private StaticPageChannelDao staticPageChannelDao;
/*     */   private StaticPageArticleDao staticPageArticleDao;
/*     */   private KeywordService keywordService;
/*     */ 
/*     */   public int staticIndex(Site site)
/*     */   {
/*  35 */     return StaticChannel.staticIndex(site, getConfig(), this.context);
/*     */   }
/*     */ 
/*     */   public int staticIndexCheck(Site site) {
/*  39 */     String filename = this.context.getRealPath(site.getStaticRealPath());
/*  40 */     File file = new File(filename);
/*  41 */     if (!file.exists()) {
/*  42 */       return StaticChannel.staticIndex(site, getConfig(), this.context);
/*     */     }
/*  44 */     return 2;
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<String> staticChannelPage(Channel c) {
/*  49 */     String treeNumber = "";
/*  50 */     if (c != null) {
/*  51 */       treeNumber = c.getNumber();
/*     */     }
/*  53 */     return this.staticPageChannelDao.staticChannelPage(treeNumber, getConfig(), 
/*  54 */       this.context);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public void staticChannel(Channel c) {
/*  59 */     while (c != null) {
/*  60 */       staticChannelCheck(c, Integer.valueOf(1));
/*  61 */       c = c.getParent();
/*     */     }
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public void staticChannelAlone(Channel c) {
/*  67 */     StaticChannel.staticChannel(c, getConfig(), this.context, Integer.valueOf(3));
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public void staticChannelCheck(Channel c, Integer page) {
/*  72 */     String filename = this.context.getRealPath(c.getChannelRealPath(page));
/*  73 */     File file = new File(filename);
/*  74 */     if (file.exists()) {
/*  75 */       if (c.isChanged(file.lastModified()))
/*  76 */         StaticChannel.staticChannelpage(c, getConfig(), this.context, page);
/*     */     }
/*     */     else
/*  79 */       StaticChannel.staticChannelpage(c, getConfig(), this.context, page);
/*     */   }
/*     */ 
/*     */   public void deleteStaticChannel(Channel c)
/*     */   {
/*  84 */     if (c == null) {
/*  85 */       return;
/*     */     }
/*  87 */     String filename = this.context.getRealPath(c.getChannelRealPath(Integer.valueOf(1)));
/*  88 */     File file = new File(filename);
/*  89 */     if (file.exists())
/*  90 */       FileUtils.deleteQuietly(file);
/*     */   }
/*     */ 
/*     */   public void deleteAllStaticArticle(Channel c)
/*     */   {
/*  95 */     if (c == null) {
/*  96 */       return;
/*     */     }
/*  98 */     String filename = this.context.getRealPath(c.getStaticDocPath());
/*  99 */     File file = new File(filename);
/* 100 */     if (file.exists())
/* 101 */       FileUtils.deleteQuietly(file);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public String staticArticlePage(Channel c)
/*     */     throws IOException, TemplateException
/*     */   {
/* 109 */     String treeNumber = "";
/* 110 */     if (c != null) {
/* 111 */       treeNumber = c.getNumber();
/*     */     }
/* 113 */     return this.staticPageArticleDao.staticArticlePage(treeNumber, getConfig(), 
/* 114 */       this.context, this.keywordService);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public void staticArticle(Article a) {
/* 119 */     StaticArticle.staticArticle(a, getConfig(), this.context, this.keywordService);
/* 120 */     staticChannel(a.getChannel());
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public void staticArticleCheck(Article a, Integer page) {
/* 125 */     String filename = this.context.getRealPath(a.getStaticRealPath(page));
/* 126 */     File file = new File(filename);
/* 127 */     if (!file.exists())
/*     */     {
/* 129 */       StaticArticle.staticArticle(a, getConfig(), this.context, this.keywordService);
/*     */     }
/* 131 */     else if (a.isChanged(file.lastModified()))
/* 132 */       StaticArticle.staticArticle(a, getConfig(), this.context, 
/* 133 */         this.keywordService);
/*     */   }
/*     */ 
/*     */   public void deleteStaticArticle(Article a)
/*     */   {
/* 139 */     if (a == null) {
/* 140 */       return;
/*     */     }
/* 142 */     String filename = this.context.getRealPath(a.getStaticRealPath(Integer.valueOf(1)));
/* 143 */     File file = new File(filename);
/* 144 */     if (file.exists())
/* 145 */       FileUtils.deleteQuietly(file);
/*     */   }
/*     */ 
/*     */   private Configuration getConfig()
/*     */   {
/* 156 */     return this.freeMarkerConfigurer.getConfiguration();
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer)
/*     */   {
/* 162 */     this.freeMarkerConfigurer = freeMarkerConfigurer;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setContext(ServletContext context) {
/* 167 */     this.context = context;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setStaticPageChannelDao(StaticPageChannelDao staticPageChannelDao)
/*     */   {
/* 173 */     this.staticPageChannelDao = staticPageChannelDao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setStaticPageArticleDao(StaticPageArticleDao staticPageArticleDao)
/*     */   {
/* 179 */     this.staticPageArticleDao = staticPageArticleDao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setKeywordService(KeywordService keywordService) {
/* 184 */     this.keywordService = keywordService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.staticpage.StaticPageServiceImpl
 * JD-Core Version:    0.6.1
 */