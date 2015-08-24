/*     */ package com.portal.datacenter.operatedata.entity.base;
/*     */ 
/*     */ import com.portal.datacenter.operatedata.entity.Log;
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
/*     */ public abstract class BaseLog
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  48 */   private int hashCode = -2147483648;
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
/*     */   public BaseLog()
/*     */   {
/*  28 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseLog(Integer id) {
/*  32 */     setId(id);
/*  33 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseLog(Integer id, Integer category, Date time)
/*     */   {
/*  39 */     setId(id);
/*  40 */     setCategory(category);
/*  41 */     setTime(time);
/*  42 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="log_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_log", pkColumnValue="tq_log", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_log")
/*     */   public Integer getId()
/*     */   {
/*  70 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  74 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="category", nullable=false, length=10)
/*     */   public Integer getCategory() {
/*  79 */     return this.category;
/*     */   }
/*     */ 
/*     */   public void setCategory(Integer category) {
/*  83 */     this.category = category;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="log_time", nullable=false, length=19)
/*     */   public Date getTime() {
/*  89 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(Date time) {
/*  93 */     this.time = time;
/*     */   }
/*     */ 
/*     */   @Column(name="ip", nullable=true, length=50)
/*     */   public String getIp() {
/*  98 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public void setIp(String ip) {
/* 102 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   @Column(name="url", nullable=true, length=255)
/*     */   public String getUrl() {
/* 107 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/* 111 */     this.url = url;
/*     */   }
/*     */ 
/*     */   @Column(name="title", nullable=true, length=255)
/*     */   public String getTitle() {
/* 116 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 120 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="content", nullable=true, length=255)
/*     */   public String getContent() {
/* 125 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content) {
/* 129 */     this.content = content;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="user_id", nullable=false)
/*     */   public User getUser() {
/* 135 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(User user) {
/* 139 */     this.user = user;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 145 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 149 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj) {
/* 153 */     if (obj == null)
/* 154 */       return false;
/* 155 */     if (!(obj instanceof Log)) {
/* 156 */       return false;
/*     */     }
/* 158 */     Log log = (Log)obj;
/* 159 */     if ((getId() == null) || (log.getId() == null)) {
/* 160 */       return false;
/*     */     }
/* 162 */     return getId().equals(log.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 167 */     if (-2147483648 == this.hashCode) {
/* 168 */       if (getId() == null) {
/* 169 */         return super.hashCode();
/*     */       }
/* 171 */       String hashStr = getClass().getName() + ":" + 
/* 172 */         getId().hashCode();
/* 173 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 176 */     return this.hashCode;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.operatedata.entity.base.BaseLog
 * JD-Core Version:    0.6.1
 */