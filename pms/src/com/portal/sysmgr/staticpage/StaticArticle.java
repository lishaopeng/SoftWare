/*     */ package com.portal.sysmgr.staticpage;
/*     */ 
/*     */ /*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.io.Writer;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;

/*     */ import javax.servlet.ServletContext;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.data.domain.PageImpl;
/*     */ import org.springframework.data.domain.PageRequest;
/*     */ import org.springframework.data.domain.Pageable;

import com.portal.datacenter.docdata.service.KeywordService;
/*     */ import com.portal.doccenter.entity.Article;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.sysmgr.utils.ViewTools;

/*     */ import freemarker.template.Configuration;
/*     */ import freemarker.template.Template;
/*     */ import freemarker.template.TemplateException;
/*     */ 
/*     */ 
/*     */ public class StaticArticle
/*     */ {
/*     */   public static Integer staticArticle(Article article, Configuration config, ServletContext ctx, KeywordService keywordService)
/*     */   {
/*  43 */     if ((article == null) || (!article.getStaticDoc())) {
/*  44 */       return Integer.valueOf(0);
/*     */     }
		Template template;
/*     */     try
/*     */     {
/*  48 */       template = config.getTemplate(article.getTplContentOrDef());
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*  50 */       return Integer.valueOf(-1);
/*     */     }
/*  52 */     Map data = new HashMap();
/*  53 */     data.put("doc", article);
/*  54 */     data.put("channel", article.getChannel());
/*  55 */     data.put("title", article.getTitle());
/*  56 */     Integer total = Integer.valueOf(article.getPageCount());
/*  57 */     for (int page = 1; page <= total.intValue(); page++) {
/*  58 */       String filename = ctx.getRealPath(article.getStaticRealPath(Integer.valueOf(page)));
/*  59 */       File file = new File(filename);
/*  60 */       file.getParentFile().mkdirs();
/*  61 */       String url = article.getUrlStatic(Integer.valueOf(page));
/*  62 */       Pageable pa = new PageRequest(page - 1, 1);
/*  63 */       Page p = new PageImpl(Collections.emptyList(), pa, 
/*  64 */         total.intValue());
/*  65 */       String ctxs = article.getSite().getContextPath();
/*  66 */       ctxs = ctxs == null ? "" : ctxs;
/*  67 */       String txt = article.getTxtByNo(page);
/*  68 */       txt = StringUtils.replace(txt, "../..", ctxs);
/*  69 */       txt = keywordService.attachKeyword(article.getSite().getId(), txt);
/*  70 */       data.put("page", p);
/*  71 */       data.put("txt", txt);
/*  72 */       ViewTools.frontData(url, data, article.getSite());
/*  73 */       ViewTools.frontPageData(url, data, Integer.valueOf(page));
/*  74 */       FileOutputStream fos = null;
/*  75 */       Writer out = null;
/*     */       try {
/*  77 */         fos = new FileOutputStream(file);
/*  78 */         out = new OutputStreamWriter(fos, "UTF-8");
/*  79 */         ContextTools.resetTotalPages();
/*  80 */         template.process(data, new BufferedWriter(out));
/*     */       } catch (FileNotFoundException e) {
/*  82 */         return Integer.valueOf(-3);
/*     */       } catch (UnsupportedEncodingException e) {
/*  84 */         return Integer.valueOf(-4);
/*     */       } catch (TemplateException e) {
/*  86 */         return Integer.valueOf(-1);
/*     */       }
/*     */       catch (IOException e)
/*     */       {
/*     */         Integer localInteger1;
/*  88 */         return Integer.valueOf(-2);
/*     */       } finally {
/*     */         try {
/*  91 */           if (out != null) {
/*  92 */             out.flush();
/*  93 */             out.close();
/*     */           }
/*  95 */           if (fos != null)
/*  96 */             fos.close();
/*     */         }
/*     */         catch (IOException localIOException5)
/*     */         {
/*     */         }
/*     */       }
/*     */       try
/*     */       {
/*  91 */         if (out != null) {
/*  92 */           out.flush();
/*  93 */           out.close();
/*     */         }
/*  95 */         if (fos != null)
/*  96 */           fos.close();
/*     */       }
/*     */       catch (IOException localIOException6)
/*     */       {
/*     */       }
/*     */     }
/* 102 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.staticpage.StaticArticle
 * JD-Core Version:    0.6.1
 */