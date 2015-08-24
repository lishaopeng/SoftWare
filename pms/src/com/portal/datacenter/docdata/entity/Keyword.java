/*     */ package com.portal.datacenter.docdata.entity;
/*     */ 
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_keyword")
/*     */ public class Keyword
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String url;
/*     */   private Boolean bold;
/*     */   private Boolean underline;
/*     */   private Boolean enable;
/*     */   private Site site;
/*     */ 
/*     */   public void init()
/*     */   {
/*  22 */     if (getEnable() == null) {
/*  23 */       setEnable(Boolean.valueOf(true));
/*     */     }
/*  25 */     if (getBold() == null) {
/*  26 */       setBold(Boolean.valueOf(true));
/*     */     }
/*  28 */     if (getUnderline() == null)
/*  29 */       setUnderline(Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="keyword_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_keyword", pkColumnValue="tq_keyword", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_keyword")
/*     */   public Integer getId()
/*     */   {
/*  51 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  55 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="name", nullable=false, length=100)
/*     */   public String getName() {
/*  60 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  64 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="url", nullable=false, length=100)
/*     */   public String getUrl() {
/*  69 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/*  73 */     this.url = url;
/*     */   }
/*     */ 
/*     */   @Column(name="is_bold", nullable=false)
/*     */   public Boolean getBold() {
/*  78 */     return this.bold;
/*     */   }
/*     */ 
/*     */   public void setBold(Boolean bold) {
/*  82 */     this.bold = bold;
/*     */   }
/*     */ 
/*     */   @Column(name="is_underline", nullable=false)
/*     */   public Boolean getUnderline() {
/*  87 */     return this.underline;
/*     */   }
/*     */ 
/*     */   public void setUnderline(Boolean underline) {
/*  91 */     this.underline = underline;
/*     */   }
/*     */ 
/*     */   @Column(name="is_enable", nullable=false)
/*     */   public Boolean getEnable() {
/*  96 */     return this.enable;
/*     */   }
/*     */ 
/*     */   public void setEnable(Boolean enable) {
/* 100 */     this.enable = enable;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 106 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 110 */     this.site = site;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.entity.Keyword
 * JD-Core Version:    0.6.1
 */