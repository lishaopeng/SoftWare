/*     */ package com.portal.extrafunc.entity;
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
/*     */ @Table(name="tq_links")
/*     */ public class Links
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String icon;
/*     */   private String url;
/*     */   private Integer priority;
/*     */   private Boolean show;
/*     */   private Boolean showIcon;
/*     */   private LinksType type;
/*     */   private Site site;
/*     */ 
/*     */   public void init()
/*     */   {
/*  22 */     if (getPriority() == null) {
/*  23 */       setPriority(Integer.valueOf(10));
/*     */     }
/*  25 */     if (getShow() == null) {
/*  26 */       setShow(Boolean.valueOf(true));
/*     */     }
/*  28 */     if (getShowIcon() == null)
/*  29 */       setShowIcon(Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="links_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_links", pkColumnValue="tq_links", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_links")
/*     */   public Integer getId()
/*     */   {
/*  53 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  57 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="links_name", nullable=false, length=50)
/*     */   public String getName() {
/*  62 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  66 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="links_icon", nullable=true, length=100)
/*     */   public String getIcon() {
/*  71 */     return this.icon;
/*     */   }
/*     */ 
/*     */   public void setIcon(String icon) {
/*  75 */     this.icon = icon;
/*     */   }
/*     */ 
/*     */   @Column(name="links_url", nullable=true, length=100)
/*     */   public String getUrl() {
/*  80 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/*  84 */     this.url = url;
/*     */   }
/*     */ 
/*     */   @Column(name="priority", nullable=false, length=10)
/*     */   public Integer getPriority() {
/*  89 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority) {
/*  93 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   @Column(name="is_show", nullable=false, length=1)
/*     */   public Boolean getShow() {
/*  98 */     return this.show;
/*     */   }
/*     */ 
/*     */   public void setShow(Boolean show) {
/* 102 */     this.show = show;
/*     */   }
/*     */ 
/*     */   @Column(name="show_icon", nullable=false, length=1)
/*     */   public Boolean getShowIcon() {
/* 107 */     return this.showIcon;
/*     */   }
/*     */ 
/*     */   public void setShowIcon(Boolean showIcon) {
/* 111 */     this.showIcon = showIcon;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="type_id", nullable=false)
/*     */   public LinksType getType() {
/* 117 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(LinksType type) {
/* 121 */     this.type = type;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 127 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 131 */     this.site = site;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.Links
 * JD-Core Version:    0.6.1
 */