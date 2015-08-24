/*     */ package com.portal.doccenter.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.Formula;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_doc_statistics")
/*     */ public class DocStatis
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private Integer viewsCount;
/*     */   private Integer commentCount;
/*     */   private Integer ups;
/*     */   private Integer treads;
/*     */   private Article doc;
/*     */ 
/*     */   public void init()
/*     */   {
/*  23 */     if (getViewsCount() == null) {
/*  24 */       setViewsCount(Integer.valueOf(0));
/*     */     }
/*  26 */     if (getCommentCount() == null) {
/*  27 */       setCommentCount(Integer.valueOf(0));
/*     */     }
/*  29 */     if (getUps() == null) {
/*  30 */       setUps(Integer.valueOf(0));
/*     */     }
/*  32 */     if (getTreads() == null)
/*  33 */       setTreads(Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="doc_id", unique=true, nullable=false)
/*     */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="doc")})
/*     */   @GeneratedValue(generator="copy")
/*     */   public Integer getId()
/*     */   {
/*  54 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  58 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="views_count", nullable=false, length=10)
/*     */   public Integer getViewsCount() {
/*  63 */     return this.viewsCount;
/*     */   }
/*     */ 
/*     */   public void setViewsCount(Integer viewsCount) {
/*  67 */     this.viewsCount = viewsCount;
/*     */   }
/*     */ 
/*     */   @Formula("(select count(c.comment_id) from tq_comment c where c.is_checked=1 and c.doc_id=doc_id)")
/*     */   public Integer getCommentCount() {
/*  72 */     return this.commentCount;
/*     */   }
/*     */ 
/*     */   public void setCommentCount(Integer commentCount) {
/*  76 */     this.commentCount = commentCount;
/*     */   }
/*     */ 
/*     */   @Column(name="ups", nullable=false, length=10)
/*     */   public Integer getUps() {
/*  81 */     return this.ups;
/*     */   }
/*     */ 
/*     */   public void setUps(Integer ups) {
/*  85 */     this.ups = ups;
/*     */   }
/*     */ 
/*     */   @Column(name="treads", nullable=false, length=10)
/*     */   public Integer getTreads() {
/*  90 */     return this.treads;
/*     */   }
/*     */ 
/*     */   public void setTreads(Integer treads) {
/*  94 */     this.treads = treads;
/*     */   }
/*     */   @OneToOne
/*     */   @PrimaryKeyJoinColumn
/*     */   public Article getDoc() {
/* 100 */     return this.doc;
/*     */   }
/*     */ 
/*     */   public void setDoc(Article doc) {
/* 104 */     this.doc = doc;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.DocStatis
 * JD-Core Version:    0.6.1
 */