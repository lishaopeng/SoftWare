/*     */ package com.portal.datacenter.lucene;
/*     */ 
/*     */ import com.javapms.basic.plugin.springmvc.RealPathResolver;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import org.apache.lucene.analysis.Analyzer;
/*     */ import org.apache.lucene.analysis.standard.StandardAnalyzer;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.index.CorruptIndexException;
/*     */ import org.apache.lucene.index.IndexWriter;
/*     */ import org.apache.lucene.index.IndexWriterConfig;
/*     */ import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.search.NRTManager;
/*     */ import org.apache.lucene.search.NRTManager.TrackingIndexWriter;
/*     */ import org.apache.lucene.search.NRTManagerReopenThread;
/*     */ import org.apache.lucene.search.Query;
/*     */ import org.apache.lucene.search.SearcherFactory;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.FSDirectory;
/*     */ import org.apache.lucene.util.Version;
/*     */ import org.springframework.beans.factory.DisposableBean;
/*     */ 
/*     */ public class NRTManagerServiceImpl
/*     */   implements NRTManagerService, DisposableBean
/*     */ {
/*     */   private IndexWriter indexWriter;
/*     */   private NRTManager nrtManager;
/*     */   private NRTManager.TrackingIndexWriter trackingIndexWriter;
/*     */   private NRTManagerReopenThread reopenThread;
/*     */   private Directory dir;
/*     */ 
/*     */   public NRTManagerServiceImpl(RealPathResolver realPathResolver)
/*     */     throws IOException
/*     */   {
/*     */     try
/*     */     {
/*  39 */       this.dir = FSDirectory.open(new File(realPathResolver
/*  40 */         .get("/WEB-INF/lucene")));
/*  41 */       Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
/*  42 */       IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_36, 
/*  43 */         analyzer);
/*  44 */       this.indexWriter = new IndexWriter(this.dir, conf);
/*  45 */       SearcherFactory searcherFactory = new SearcherFactory();
/*  46 */       this.trackingIndexWriter = new NRTManager.TrackingIndexWriter(
/*  47 */         this.indexWriter);
/*  48 */       this.nrtManager = new NRTManager(this.trackingIndexWriter, searcherFactory, 
/*  49 */         true);
/*  50 */       this.reopenThread = new NRTManagerReopenThread(this.nrtManager, 5.0D, 0.1D);
/*  51 */       this.reopenThread.setName("nrt reopen thread");
/*  52 */       this.reopenThread.setDaemon(true);
/*  53 */       this.reopenThread.start();
/*     */     } catch (IOException e) {
/*  55 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addDoc(Document doc) {
/*     */     try {
/*  61 */       this.trackingIndexWriter.addDocument(doc);
/*     */     } catch (IOException e) {
/*  63 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addDocuments(Collection<Document> documents) {
/*     */     try {
/*  69 */       this.trackingIndexWriter.addDocuments(documents);
/*     */     } catch (Exception e) {
/*  71 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateDocument(Term term, Document document) {
/*     */     try {
/*  77 */       this.trackingIndexWriter.updateDocument(term, document);
/*     */     } catch (Exception e) {
/*  79 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void deleteDocuments(Term term) {
/*     */     try {
/*  85 */       this.trackingIndexWriter.deleteDocuments(term);
/*     */     } catch (Exception e) {
/*  87 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void deleteDocuments(Query query) {
/*     */     try {
/*  93 */       this.trackingIndexWriter.deleteDocuments(query);
/*     */     } catch (Exception e) {
/*  95 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void commit() {
/*     */     try {
/* 101 */       this.indexWriter.commit();
/*     */     } catch (CorruptIndexException e) {
/* 103 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/* 105 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void destroy() throws Exception {
/* 110 */     if (this.reopenThread != null) {
/* 111 */       this.reopenThread.close();
/*     */     }
/* 113 */     if (this.nrtManager != null) {
/* 114 */       this.nrtManager.close();
/*     */     }
/* 116 */     if (this.indexWriter != null)
/* 117 */       this.indexWriter.close();
/*     */   }
/*     */ 
/*     */   public IndexWriter getIndexWriter()
/*     */   {
/* 132 */     return this.indexWriter;
/*     */   }
/*     */ 
/*     */   public NRTManager getNrtManager() {
/* 136 */     return this.nrtManager;
/*     */   }
/*     */ 
/*     */   public NRTManager.TrackingIndexWriter getTrackingIndexWriter() {
/* 140 */     return this.trackingIndexWriter;
/*     */   }
/*     */ 
/*     */   public NRTManagerReopenThread getReopenThread() {
/* 144 */     return this.reopenThread;
/*     */   }
/*     */ 
/*     */   public Directory getDir() {
/* 148 */     return this.dir;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.lucene.NRTManagerServiceImpl
 * JD-Core Version:    0.6.1
 */