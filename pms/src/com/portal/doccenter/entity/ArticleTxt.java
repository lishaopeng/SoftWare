/*     */ package com.portal.doccenter.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Lob;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Transient;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_article_txt")
/*     */ public class ArticleTxt
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  23 */   public static String PAGE_STRING = "<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />";
/*     */   private Integer id;
/*     */   private String txt;
/*     */   private Article article;
/*     */ 
/*     */   @Transient
/*     */   public int getTxtCount()
/*     */   {
/*  27 */     String txt = getTxt();
/*  28 */     if (StringUtils.isBlank(txt)) {
/*  29 */       return 1;
/*     */     }
/*  31 */     return StringUtils.countMatches(txt, PAGE_STRING) + 1;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTxtByNo(int pageNo)
/*     */   {
/*  37 */     String txt = getTxt();
/*  38 */     if ((StringUtils.isBlank(txt)) || (pageNo < 1)) {
/*  39 */       return null;
/*     */     }
/*  41 */     int start = 0; int end = 0;
/*  42 */     for (int i = 0; i < pageNo; i++)
/*     */     {
/*  44 */       if (i != 0) {
/*  45 */         start = txt.indexOf(PAGE_STRING, end);
/*  46 */         if (start == -1) {
/*  47 */           return null;
/*     */         }
/*  49 */         start += PAGE_STRING.length();
/*     */       }
/*     */ 
/*  52 */       end = txt.indexOf(PAGE_STRING, start);
/*  53 */       if (end == -1) {
/*  54 */         end = txt.length();
/*     */       }
/*     */     }
/*  57 */     return txt.substring(start, end);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTitleByNo(int pageNo) {
/*  62 */     if (pageNo < 1) {
/*  63 */       return null;
/*     */     }
/*  65 */     String title = getArticle().getTitle();
/*  66 */     if (pageNo == 1) {
/*  67 */       return title;
/*     */     }
/*  69 */     String txt = getTxt();
/*  70 */     int start = 0; int end = 0;
/*  71 */     for (int i = 1; i < pageNo; i++) {
/*  72 */       start = txt.indexOf(PAGE_STRING, end);
/*  73 */       if (start == -1) {
/*  74 */         return title;
/*     */       }
/*  76 */       start += PAGE_STRING.length();
/*     */ 
/*  78 */       end = txt.indexOf(PAGE_STRING, start);
/*  79 */       if (end == -1) {
/*  80 */         return title;
/*     */       }
/*     */     }
/*  83 */     String result = txt.substring(start, end);
/*  84 */     if (!StringUtils.isBlank(result)) {
/*  85 */       return result;
/*     */     }
/*  87 */     return title;
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  92 */     blankToNull();
/*     */   }
/*     */ 
/*     */   public void blankToNull() {
/*  96 */     if (StringUtils.isBlank(getTxt()))
/*  97 */       setTxt(null);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean isAllBlank()
/*     */   {
/* 108 */     return StringUtils.isBlank(getTxt());
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="article_id", unique=true, nullable=false)
/*     */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="article")})
/*     */   @GeneratedValue(generator="copy")
/*     */   public Integer getId()
/*     */   {
/* 125 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 129 */     this.id = id;
/*     */   }
/*     */   @Lob
/*     */   @Column(name="txt", nullable=true)
/*     */   public String getTxt() {
/* 135 */     return this.txt;
/*     */   }
/*     */ 
/*     */   public void setTxt(String txt) {
/* 139 */     this.txt = txt;
/*     */   }
/*     */   @OneToOne
/*     */   @PrimaryKeyJoinColumn
/*     */   public Article getArticle() {
/* 145 */     return this.article;
/*     */   }
/*     */ 
/*     */   public void setArticle(Article article) {
/* 149 */     this.article = article;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.ArticleTxt
 * JD-Core Version:    0.6.1
 */