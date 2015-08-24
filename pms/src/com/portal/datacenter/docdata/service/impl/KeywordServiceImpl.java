/*     */ package com.portal.datacenter.docdata.service.impl;
/*     */ 
/*     */ /*     */ import java.util.List;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.htmlparser.Node;
/*     */ import org.htmlparser.lexer.Lexer;
/*     */ import org.htmlparser.nodes.TextNode;
/*     */ import org.htmlparser.util.ParserException;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;

import com.portal.datacenter.docdata.dao.KeywordDao;
/*     */ import com.portal.datacenter.docdata.entity.Keyword;
/*     */ import com.portal.datacenter.docdata.service.KeywordService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class KeywordServiceImpl
/*     */   implements KeywordService
/*     */ {
/*     */   private KeywordDao dao;
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public List<Keyword> getListBySiteId(Integer siteId, boolean enable, boolean cacheable, String sortname, String sortorder)
/*     */   {
/*  26 */     List list = this.dao.getList(siteId, enable, cacheable, sortname, 
/*  27 */       sortorder);
/*  28 */     return list;
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public String attachKeyword(Integer siteId, String txt) {
/*  33 */     if (StringUtils.isBlank(txt)) {
/*  34 */       return txt;
/*     */     }
		/* 36 */List<Keyword> list = getListBySiteId(siteId, true, true, null,
				null);
/*  37 */     int len = list.size();
/*  38 */     if (len <= 0) {
/*  39 */       return txt;
/*     */     }
/*  41 */     String[] searchArr = new String[len];
/*  42 */     String[] replacementArr = new String[len];
/*  43 */     int i = 0;
/*  44 */     for (Keyword k : list) {
/*  45 */       searchArr[i] = k.getName();
/*  46 */       String style = "";
/*  47 */       if (k.getBold().booleanValue()) {
/*  48 */         style = "font-weight:bold;";
/*     */       }
/*  50 */       if (k.getUnderline().booleanValue()) {
/*  51 */         style = style + "text-decoration:underline;";
/*     */       }
/*  53 */       StringBuffer sb = new StringBuffer();
/*  54 */       sb.append("<a href=\"");
/*  55 */       sb.append(k.getUrl()).append("\" target=\"_blank\">");
/*  56 */       if (!StringUtils.isBlank(style)) {
/*  57 */         sb.append("<span style=\"");
/*  58 */         sb.append(style).append("\">");
/*     */       }
/*  60 */       sb.append(searchArr[i]);
/*  61 */       if (!StringUtils.isBlank(style)) {
/*  62 */         sb.append("</span>");
/*     */       }
/*  64 */       sb.append("</a>");
/*  65 */       replacementArr[i] = sb.toString();
/*  66 */       i++;
/*     */     }
/*     */     try {
/*  69 */       Lexer lexer = new Lexer(txt);
/*     */ 
/*  71 */       StringBuilder sb = new StringBuilder((int)(txt.length() * 1.2D));
/*     */       Node node;
/*  72 */       while ((node = lexer.nextNode()) != null)
/*     */       {
/*  73 */         if ((node instanceof TextNode))
/*  74 */           sb.append(StringUtils.replaceEach(node.toHtml(), searchArr, 
/*  75 */             replacementArr));
/*     */         else {
/*  77 */           sb.append(node.toHtml());
/*     */         }
/*     */       }
/*  80 */       return sb.toString();
/*     */     } catch (ParserException e) {
/*  82 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Keyword findById(Integer id) {
/*  88 */     Keyword entity = this.dao.findById(id);
/*  89 */     return entity;
/*     */   }
/*     */ 
/*     */   @Override
public Keyword save(Keyword bean, Site site) {
/*  93 */     bean.setSite(site);
/*  94 */     bean.init();
/*  95 */     this.dao.save(bean);
/*  96 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public Keyword update(Keyword bean) {
/* 100 */     bean = this.dao.update(bean);
/* 101 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public Keyword deleteById(Integer id) {
/* 105 */     Keyword bean = this.dao.deleteById(id);
/* 106 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public Keyword[] deleteByIds(Integer[] ids) {
/* 110 */     Keyword[] beans = new Keyword[ids.length];
/* 111 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 112 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 114 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(KeywordDao dao)
/*     */   {
/* 121 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.service.impl.KeywordServiceImpl
 * JD-Core Version:    0.6.1
 */