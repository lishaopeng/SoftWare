/*     */ package com.portal.datacenter.operatedata.entity;
/*     */ 
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import java.io.Serializable;
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
/*     */ @Table(name="tq_log")
/*     */ public class Log
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int LOGIN_SUCCESS = 1;
/*     */   public static final int LOGIN_FAILURE = 2;
/*     */   public static final int OPERATING = 3;
/*     */   private Integer id;
/*     */   private Integer category;
/*     */   private Date time;
/*     */   private String ip;
/*     */   private String url;
/*     */   private String title;
/*     */   private String content;
/*     */   private User user;
/*     */   private Site site;
/*     */ 
/*     */   @Id
/*     */   @Column(name="log_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_log", pkColumnValue="tq_log", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_log")
/*     */   public Integer getId()
/*     */   {
/*  46 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  50 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="category", nullable=false, length=10)
/*     */   public Integer getCategory() {
/*  55 */     return this.category;
/*     */   }
/*     */ 
/*     */   public void setCategory(Integer category) {
/*  59 */     this.category = category;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="log_time", nullable=false, length=19)
/*     */   public Date getTime() {
/*  65 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(Date time) {
/*  69 */     this.time = time;
/*     */   }
/*     */ 
/*     */   @Column(name="ip", nullable=true, length=50)
/*     */   public String getIp() {
/*  74 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public void setIp(String ip) {
/*  78 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   @Column(name="url", nullable=true, length=255)
/*     */   public String getUrl() {
/*  83 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/*  87 */     this.url = url;
/*     */   }
/*     */ 
/*     */   @Column(name="title", nullable=true, length=255)
/*     */   public String getTitle() {
/*  92 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/*  96 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="content", nullable=true, length=255)
/*     */   public String getContent() {
/* 101 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content) {
/* 105 */     this.content = content;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="user_id", nullable=false)
/*     */   public User getUser() {
/* 111 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(User user) {
/* 115 */     this.user = user;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 121 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 125 */     this.site = site;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.operatedata.entity.Log
 * JD-Core Version:    0.6.1
 */