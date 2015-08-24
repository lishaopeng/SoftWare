/*     */ package com.portal.sysmgr.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
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
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_visit_statistics")
/*     */ public class VisitStatistics
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private String url;
/*     */   private String ip;
/*     */   private String cookie;
/*     */   private Date visitTime;
/*     */   private Integer visitHour;
/*     */   private Integer visitMin;
/*     */   private Site site;
/*     */ 
/*     */   public void init()
/*     */   {
/*  30 */     Calendar cal = Calendar.getInstance();
/*  31 */     if (getVisitTime() == null) {
/*  32 */       setVisitTime(new Timestamp(System.currentTimeMillis()));
/*     */     }
/*  34 */     if (getVisitHour() == null) {
/*  35 */       setVisitHour(Integer.valueOf(cal.get(11)));
/*     */     }
/*  37 */     if (getVisitMin() == null)
/*  38 */       setVisitMin(Integer.valueOf(cal.get(12)));
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="visit_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_visit_statistics", pkColumnValue="tq_visit_statistics", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_visit_statistics")
/*     */   public Integer getId()
/*     */   {
/*  59 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  63 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="url", nullable=false, length=150)
/*     */   public String getUrl() {
/*  68 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/*  72 */     this.url = url;
/*     */   }
/*     */ 
/*     */   @Column(name="ip", nullable=true, length=30)
/*     */   public String getIp() {
/*  77 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public void setIp(String ip) {
/*  81 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   @Column(name="cookie", nullable=true, length=30)
/*     */   public String getCookie() {
/*  86 */     return this.cookie;
/*     */   }
/*     */ 
/*     */   public void setCookie(String cookie) {
/*  90 */     this.cookie = cookie;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="visit_time", nullable=false, length=19)
/*     */   public Date getVisitTime() {
/*  96 */     return this.visitTime;
/*     */   }
/*     */ 
/*     */   public void setVisitTime(Date visitTime) {
/* 100 */     this.visitTime = visitTime;
/*     */   }
/*     */ 
/*     */   @Column(name="visit_hour", nullable=false, length=11)
/*     */   public Integer getVisitHour() {
/* 105 */     return this.visitHour;
/*     */   }
/*     */ 
/*     */   public void setVisitHour(Integer visitHour) {
/* 109 */     this.visitHour = visitHour;
/*     */   }
/*     */ 
/*     */   @Column(name="visit_min", nullable=false, length=11)
/*     */   public Integer getVisitMin() {
/* 114 */     return this.visitMin;
/*     */   }
/*     */ 
/*     */   public void setVisitMin(Integer visitMin) {
/* 118 */     this.visitMin = visitMin;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 124 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 128 */     this.site = site;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.entity.VisitStatistics
 * JD-Core Version:    0.6.1
 */