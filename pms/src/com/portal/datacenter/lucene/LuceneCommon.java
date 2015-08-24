/*     */ package com.portal.datacenter.lucene;
/*     */ 
/*     */ /*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.lucene.analysis.Analyzer;
/*     */ import org.apache.lucene.analysis.standard.StandardAnalyzer;
/*     */ import org.apache.lucene.document.DateTools;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.document.Field;
/*     */ import org.apache.lucene.index.CorruptIndexException;
/*     */ import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.queryParser.MultiFieldQueryParser;
/*     */ import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
/*     */ import org.apache.lucene.search.BooleanQuery;
/*     */ import org.apache.lucene.search.IndexSearcher;
/*     */ import org.apache.lucene.search.Query;
/*     */ import org.apache.lucene.search.ScoreDoc;
/*     */ import org.apache.lucene.search.TermQuery;
/*     */ import org.apache.lucene.search.TermRangeQuery;
/*     */ import org.apache.lucene.search.TopDocs;
/*     */ import org.apache.lucene.util.NumericUtils;
/*     */ import org.apache.lucene.util.Version;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.data.domain.PageImpl;
/*     */ import org.springframework.data.domain.PageRequest;
/*     */ import org.springframework.data.domain.Pageable;

import com.portal.doccenter.entity.Article;
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LuceneCommon
/*     */ {
/*     */   public static final String ID = "id";
/*     */   public static final String SITE_ID = "siteId";
/*     */   public static final String MODEL_ID = "modelId";
/*     */   public static final String CHANNEL_ID = "channelId";
/*     */   public static final String CHANNEL_NUMBER = "number";
/*     */   public static final String RELEASE_DATE = "releaseDate";
/*     */   public static final String TITLE = "title";
/*     */   public static final String CONTENT = "content";
/*     */   public static final String ATTR_VALUE = "attr_value";
/*  51 */   public static final String[] QUERY_FIELD = { "title", "content", "attr_value" };
/*  52 */   public static final BooleanClause.Occur[] QUERY_FLAGS = { 
/*  53 */     BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD, 
/*  54 */     BooleanClause.Occur.SHOULD };
/*     */ 
/*     */   public static Term term(Integer id)
/*     */   {
/*  57 */     return new Term("id", NumericUtils.intToPrefixCoded(id.intValue()));
/*     */   }
/*     */ 
/*     */   public static Document createDoc(Article a) {
/*  61 */     Document doc = new Document();
/*  62 */     doc.add(new Field("id", a.getId().toString(), Field.Store.YES, 
/*  63 */       Field.Index.NOT_ANALYZED));
/*  64 */     doc.add(new Field("siteId", a.getSite().getId().toString(), 
/*  65 */       Field.Store.NO, Field.Index.NOT_ANALYZED));
/*  66 */     Channel channel = a.getChannel();
/*  67 */     while (channel != null) {
/*  68 */       doc.add(new Field("channelId", channel.getId().toString(), 
/*  69 */         Field.Store.NO, Field.Index.NOT_ANALYZED));
/*  70 */       channel = channel.getParent();
/*     */     }
/*  72 */     doc.add(new Field("modelId", a.getModel().getId().toString(), 
/*  73 */       Field.Store.NO, Field.Index.NOT_ANALYZED));
/*  74 */     doc.add(new Field("releaseDate", DateTools.dateToString(
/*  75 */       a.getReleaseDate(), DateTools.Resolution.DAY), Field.Store.NO, 
/*  76 */       Field.Index.NOT_ANALYZED));
/*  77 */     doc.add(new Field("title", a.getTitle(), Field.Store.NO, 
/*  78 */       Field.Index.ANALYZED));
/*  79 */     if (!StringUtils.isBlank(a.getTxtValue())) {
/*  80 */       doc.add(new Field("content", a.getTxtValue(), Field.Store.NO, 
/*  81 */         Field.Index.ANALYZED));
/*     */     }
/*  83 */     if (a.getAttr() != null) {
/*  84 */       Iterator iter = a.getAttr().entrySet()
/*  85 */         .iterator();
/*  86 */       String value = "";
/*  87 */       while (iter.hasNext()) {
/*  88 */         Map.Entry entry = 
/*  89 */           (Map.Entry)iter
/*  89 */           .next();
/*  90 */         if ((!StringUtils.isBlank((String)entry.getValue())) && 
/*  91 */           (!StringUtils.isBlank((String)entry.getKey()))) {
/*  92 */           value = value + (String)entry.getValue();
/*  93 */           doc.add(new Field((String)entry.getKey(), (String)entry.getValue(), 
/*  94 */             Field.Store.NO, Field.Index.ANALYZED));
/*     */         }
/*     */       }
/*  97 */       if (!StringUtils.isBlank(value)) {
/*  98 */         doc.add(new Field("attr_value", value, Field.Store.NO, 
/*  99 */           Field.Index.ANALYZED));
/*     */       }
/*     */     }
/* 102 */     return doc;
/*     */   }
/*     */ 
/*     */   public static Query createQuery(String queryKey, String attrqueryKey, Integer siteId, Integer modelId, Integer channelId, Date startDate, Date endDate)
/*     */     throws ParseException
/*     */   {
/* 108 */     Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
/* 109 */     BooleanQuery bq = new BooleanQuery();
/*     */ 
/* 111 */     if (!StringUtils.isBlank(queryKey)) {
/* 112 */       Query q = MultiFieldQueryParser.parse(Version.LUCENE_36, queryKey, 
/* 113 */         QUERY_FIELD, QUERY_FLAGS, analyzer);
/* 114 */       bq.add(q, BooleanClause.Occur.MUST);
/*     */     }
/* 116 */     if (!StringUtils.isBlank(attrqueryKey)) {
/* 117 */       String[] attrs = attrqueryKey.split("!~!");
/* 118 */       for (String attr : attrs) {
/* 119 */         if (attr.indexOf(":") > -1) {
/* 120 */           String[] attrinfo = attr.split(":");
/* 121 */           if ((attrinfo.length == 2) && 
/* 122 */             (!StringUtils.isBlank(attrinfo[1]))) {
/* 123 */             String[] sq = { attrinfo[1] };
/* 124 */             String[] sf = { attrinfo[0] };
/* 125 */             BooleanClause.Occur[] sfla = { BooleanClause.Occur.MUST };
/* 126 */             Query q = MultiFieldQueryParser.parse(Version.LUCENE_36, sq, 
/* 127 */               sf, sfla, analyzer);
/* 128 */             bq.add(q, BooleanClause.Occur.MUST);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 133 */     if (siteId != null) {
/* 134 */       Query q = new TermQuery(new Term("siteId", siteId.toString()));
/* 135 */       bq.add(q, BooleanClause.Occur.MUST);
/*     */     }
/* 137 */     if (channelId != null) {
/* 138 */       Query q = new TermQuery(new Term("channelId", channelId.toString()));
/* 139 */       bq.add(q, BooleanClause.Occur.MUST);
/*     */     }
/* 141 */     if (modelId != null) {
/* 142 */       Query q = new TermQuery(new Term("modelId", modelId.toString()));
/* 143 */       bq.add(q, BooleanClause.Occur.MUST);
/*     */     }
/* 145 */     if ((startDate != null) || (endDate != null)) {
/* 146 */       String start = null;
/* 147 */       String end = null;
/* 148 */       if (startDate != null) {
/* 149 */         start = DateTools.dateToString(startDate, DateTools.Resolution.DAY);
/*     */       }
/* 151 */       if (endDate != null) {
/* 152 */         end = DateTools.dateToString(endDate, DateTools.Resolution.DAY);
/*     */       }
/* 154 */       Query q = new TermRangeQuery("releaseDate", start, end, true, true);
/* 155 */       bq.add(q, BooleanClause.Occur.MUST);
/*     */     }
/* 157 */     return bq;
/*     */   }
/*     */ 
/*     */   public static Page<Integer> getResultPage(IndexSearcher searcher, TopDocs docs, int pageNo, int pageSize)
/*     */     throws CorruptIndexException, IOException
/*     */   {
/* 163 */     Pageable p = new PageRequest(pageNo - 1, pageSize);
/* 164 */     List list = new ArrayList(pageSize);
/* 165 */     ScoreDoc[] hits = docs.scoreDocs;
/* 166 */     int endIndex = pageNo * pageSize;
/* 167 */     int len = hits.length;
/* 168 */     if (endIndex > len) {
/* 169 */       endIndex = len;
/*     */     }
/* 171 */     for (int i = (pageNo - 1) * pageSize; i < endIndex; i++) {
/* 172 */       Document d = searcher.doc(hits[i].doc);
/* 173 */       list.add(Integer.valueOf(d.get("id")));
/*     */     }
/* 175 */     return new PageImpl(list, p, docs.totalHits);
/*     */   }
/*     */ 
/*     */   public static List<Integer> getResultList(IndexSearcher searcher, TopDocs docs, int first, int max)
/*     */     throws CorruptIndexException, IOException
/*     */   {
/* 181 */     List list = new ArrayList(max);
/* 182 */     ScoreDoc[] hits = docs.scoreDocs;
/* 183 */     if (first < 0) {
/* 184 */       first = 0;
/*     */     }
/* 186 */     if (max < 0) {
/* 187 */       max = 0;
/*     */     }
/* 189 */     int last = first + max;
/* 190 */     int len = hits.length;
/* 191 */     if (last > len) {
/* 192 */       last = len;
/*     */     }
/* 194 */     for (int i = first; i < last; i++) {
/* 195 */       Document d = searcher.doc(hits[i].doc);
/* 196 */       list.add(Integer.valueOf(d.get("id")));
/*     */     }
/* 198 */     return list;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.lucene.LuceneCommon
 * JD-Core Version:    0.6.1
 */