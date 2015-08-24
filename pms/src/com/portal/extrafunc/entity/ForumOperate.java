/*     */ package com.portal.extrafunc.entity;
/*     */ 
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.usermgr.entity.User;
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
/*     */ @Table(name="tq_forum_operate")
/*     */ public class ForumOperate
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String THEME = "THEME";
/*     */   public static final String POSTS = "POSTS";
/*     */   public static final String USER = "USER";
/*     */   private Integer id;
/*     */   private Integer target;
/*     */   private String targetType;
/*     */   private String name;
/*     */   private String reason;
/*     */   private Date operateTime;
/*     */   private User admin;
/*     */   private Site site;
/*     */ 
/*     */   public void init()
/*     */   {
/*  31 */     if (getOperateTime() == null)
/*  32 */       setOperateTime(new Timestamp(System.currentTimeMillis()));
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="operate_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_forum_operate", pkColumnValue="tq_forum_operate", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_forum_operate")
/*     */   public Integer getId()
/*     */   {
/*  55 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  59 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="operate_target", nullable=false, length=10)
/*     */   public Integer getTarget() {
/*  64 */     return this.target;
/*     */   }
/*     */ 
/*     */   public void setTarget(Integer target) {
/*  68 */     this.target = target;
/*     */   }
/*     */ 
/*     */   @Column(name="target_type", nullable=false, length=30)
/*     */   public String getTargetType() {
/*  73 */     return this.targetType;
/*     */   }
/*     */ 
/*     */   public void setTargetType(String targetType) {
/*  77 */     this.targetType = targetType;
/*     */   }
/*     */ 
/*     */   @Column(name="name", nullable=false, length=30)
/*     */   public String getName() {
/*  82 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  86 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="reason", nullable=true, length=100)
/*     */   public String getReason() {
/*  91 */     return this.reason;
/*     */   }
/*     */ 
/*     */   public void setReason(String reason) {
/*  95 */     this.reason = reason;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="operate_time", nullable=false, length=19)
/*     */   public Date getOperateTime() {
/* 101 */     return this.operateTime;
/*     */   }
/*     */ 
/*     */   public void setOperateTime(Date operateTime) {
/* 105 */     this.operateTime = operateTime;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="operate_admin", nullable=false)
/*     */   public User getAdmin() {
/* 111 */     return this.admin;
/*     */   }
/*     */ 
/*     */   public void setAdmin(User admin) {
/* 115 */     this.admin = admin;
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
 * Qualified Name:     com.portal.extrafunc.entity.ForumOperate
 * JD-Core Version:    0.6.1
 */