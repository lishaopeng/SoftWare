/*     */ package com.portal.extrafunc.entity;
/*     */ 
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
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
/*     */ @Table(name="tq_questionnaire")
/*     */ public class Questionnaire
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String description;
/*     */   private Integer repeateTime;
/*     */   private Boolean restrictIp;
/*     */   private Boolean needLogin;
/*     */   private Date createTime;
/*     */   private Date startTime;
/*     */   private Date endTime;
/*     */   private Boolean enable;
/*     */   private Site site;
/*     */ 
/*     */   public void init()
/*     */   {
/*  25 */     if (getRepeateTime() == null) {
/*  26 */       setRepeateTime(Integer.valueOf(9999999));
/*     */     }
/*  28 */     if (getCreateTime() == null) {
/*  29 */       setCreateTime(new Timestamp(System.currentTimeMillis()));
/*     */     }
/*  31 */     if (getStartTime() == null) {
/*  32 */       setStartTime(new Timestamp(System.currentTimeMillis()));
/*     */     }
/*  34 */     if (getRestrictIp() == null) {
/*  35 */       setRestrictIp(Boolean.valueOf(false));
/*     */     }
/*  37 */     if (getNeedLogin() == null) {
/*  38 */       setNeedLogin(Boolean.valueOf(false));
/*     */     }
/*  40 */     if (getEnable() == null)
/*  41 */       setEnable(Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   public void updateinit()
/*     */   {
/*  46 */     if (getRepeateTime() == null) {
/*  47 */       setRepeateTime(Integer.valueOf(0));
/*     */     }
/*  49 */     if (getRestrictIp() == null) {
/*  50 */       setRestrictIp(Boolean.valueOf(false));
/*     */     }
/*  52 */     if (getNeedLogin() == null) {
/*  53 */       setNeedLogin(Boolean.valueOf(false));
/*     */     }
/*  55 */     if (getEnable() == null)
/*  56 */       setEnable(Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="naire_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_questionnaire", pkColumnValue="tq_questionnaire", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_questionnaire")
/*     */   public Integer getId()
/*     */   {
/*  82 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  86 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="name", nullable=false, length=50)
/*     */   public String getName() {
/*  91 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  95 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="description", nullable=true, length=300)
/*     */   public String getDescription() {
/* 100 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/* 104 */     this.description = description;
/*     */   }
/*     */ 
/*     */   @Column(name="repeate_time", nullable=false, length=10)
/*     */   public Integer getRepeateTime() {
/* 109 */     return this.repeateTime;
/*     */   }
/*     */ 
/*     */   public void setRepeateTime(Integer repeateTime) {
/* 113 */     this.repeateTime = repeateTime;
/*     */   }
/*     */ 
/*     */   @Column(name="is_restrict_ip", nullable=false, length=1)
/*     */   public Boolean getRestrictIp() {
/* 118 */     return this.restrictIp;
/*     */   }
/*     */ 
/*     */   public void setRestrictIp(Boolean restrictIp) {
/* 122 */     this.restrictIp = restrictIp;
/*     */   }
/*     */ 
/*     */   @Column(name="is_need_login", nullable=false, length=1)
/*     */   public Boolean getNeedLogin() {
/* 127 */     return this.needLogin;
/*     */   }
/*     */ 
/*     */   public void setNeedLogin(Boolean needLogin) {
/* 131 */     this.needLogin = needLogin;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="create_time", nullable=false, length=19)
/*     */   public Date getCreateTime() {
/* 137 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/* 141 */     this.createTime = createTime;
/*     */   }
/*     */   @Temporal(TemporalType.DATE)
/*     */   @Column(name="start_time", nullable=false, length=10)
/*     */   public Date getStartTime() {
/* 147 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime) {
/* 151 */     this.startTime = startTime;
/*     */   }
/*     */   @Temporal(TemporalType.DATE)
/*     */   @Column(name="end_time", nullable=true, length=10)
/*     */   public Date getEndTime() {
/* 157 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime) {
/* 161 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   @Column(name="enable", nullable=false, length=1)
/*     */   public Boolean getEnable() {
/* 166 */     return this.enable;
/*     */   }
/*     */ 
/*     */   public void setEnable(Boolean enable) {
/* 170 */     this.enable = enable;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 176 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 180 */     this.site = site;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.Questionnaire
 * JD-Core Version:    0.6.1
 */