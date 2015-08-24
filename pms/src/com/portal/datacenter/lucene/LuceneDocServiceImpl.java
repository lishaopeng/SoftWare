/*     */ package com.portal.datacenter.lucene;
/*     */ 
/*     */ import com.portal.doccenter.entity.Article;
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.doccenter.service.ChannelService;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Date;
/*     */ import org.apache.lucene.index.CorruptIndexException;
/*     */ import org.apache.lucene.queryParser.ParseException;
/*     */ import org.apache.lucene.search.Query;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class LuceneDocServiceImpl
/*     */   implements LuceneDocService
/*     */ {
/*     */   private LuceneDocDao dao;
/*     */   private NRTManagerService nrtManager;
/*     */   private ChannelService channelService;
/*     */ 
/*     */   public Integer createSearchIndex(Integer siteId, Integer channelId, Date startDate, Date endDate, Integer startId, Integer max, boolean delete)
/*     */     throws IOException
/*     */   {
/*  24 */     String number = "";
/*  25 */     if (channelId != null) {
/*  26 */       Channel c = this.channelService.findById(channelId);
/*  27 */       if (c != null) {
/*  28 */         number = c.getNumber();
/*     */       }
/*     */     }
/*  31 */     if (delete) {
/*     */       try {
/*  33 */         Query query = LuceneCommon.createQuery(null, null, siteId, 
/*  34 */           null, channelId, startDate, endDate);
/*  35 */         this.nrtManager.deleteDocuments(query);
/*     */       } catch (ParseException e) {
/*  37 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  40 */     Integer docId = this.dao.index(this.nrtManager, siteId, number, startDate, 
/*  41 */       endDate, startId, max);
/*  42 */     return docId;
/*     */   }
/*     */ 
/*     */   public void deleteSearchIndex(Integer siteId, Integer channelId) {
/*     */     try {
/*  47 */       Query query = LuceneCommon.createQuery(null, null, siteId, null, 
/*  48 */         channelId, null, null);
/*  49 */       this.nrtManager.deleteDocuments(query);
/*     */     } catch (ParseException e) {
/*  51 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateChannel(Integer siteId, Integer channelId) {
/*     */     try {
/*  57 */       Query query = LuceneCommon.createQuery(null, null, siteId, null, 
/*  58 */         channelId, null, null);
/*  59 */       this.nrtManager.deleteDocuments(query);
/*     */     } catch (ParseException e) {
/*  61 */       e.printStackTrace();
/*     */     }
/*     */     try {
/*  64 */       this.dao.index(this.nrtManager, siteId, getTreeNumber(channelId), null, null, 
/*  65 */         null, Integer.valueOf(1000));
/*     */     } catch (CorruptIndexException e) {
/*  67 */       System.out.println("生成全文检索异常!");
/*     */     } catch (IOException e) {
/*  69 */       System.out.println("生成全文检索异常!");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addDoc(Article doc) {
/*  74 */     this.nrtManager.addDoc(LuceneCommon.createDoc(doc));
/*     */   }
/*     */ 
/*     */   public void updateDoc(Article doc) {
/*  78 */     this.nrtManager.updateDocument(LuceneCommon.term(doc.getId()), 
/*  79 */       LuceneCommon.createDoc(doc));
/*     */   }
/*     */ 
/*     */   public void deleteDoc(Integer docId) {
/*  83 */     this.nrtManager.deleteDocuments(LuceneCommon.term(docId));
/*     */   }
/*     */ 
/*     */   private String getTreeNumber(Integer cId) {
/*  87 */     if (cId == null) {
/*  88 */       return null;
/*     */     }
/*  90 */     Channel c = this.channelService.findById(cId);
/*  91 */     if (c != null) {
/*  92 */       return c.getNumber();
/*     */     }
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(LuceneDocDao dao)
/*     */   {
/* 103 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setNrtManager(NRTManagerService nrtManager) {
/* 108 */     this.nrtManager = nrtManager;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setChannelService(ChannelService channelService) {
/* 113 */     this.channelService = channelService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.lucene.LuceneDocServiceImpl
 * JD-Core Version:    0.6.1
 */