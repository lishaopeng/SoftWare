/*     */ package com.portal.extrafunc.entity;
/*     */ 
/*     */ import com.portal.usermgr.entity.User;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_user_forum")
/*     */ public class UserForum
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int NORMAL = 0;
/*     */   public static final int NOT_POSTS = -1;
/*     */   public static final int SHIELD = -2;
/*     */   private Integer id;
/*     */   private String avatar;
/*     */   private Integer essenaCount;
/*     */   private Integer themeCount;
/*     */   private Integer replyCount;
/*     */   private Integer point;
/*     */   private Integer status;
/*     */   private Date statusTime;
/*     */   private String signature;
/*     */   private User user;
/*     */ 
/*     */   public void init()
/*     */   {
/*  30 */     if (getThemeCount() == null) {
/*  31 */       setThemeCount(Integer.valueOf(0));
/*     */     }
/*  33 */     if (getReplyCount() == null) {
/*  34 */       setReplyCount(Integer.valueOf(0));
/*     */     }
/*  36 */     if (getPoint() == null) {
/*  37 */       setPoint(Integer.valueOf(0));
/*     */     }
/*  39 */     if (getStatus() == null)
/*  40 */       setStatus(Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="user_id", unique=true, nullable=false)
/*     */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="user")})
/*     */   @GeneratedValue(generator="copy")
/*     */   public Integer getId()
/*     */   {
/*  65 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  69 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="avatar", nullable=true, length=50)
/*     */   public String getAvatar() {
/*  74 */     return this.avatar;
/*     */   }
/*     */ 
/*     */   public void setAvatar(String avatar) {
/*  78 */     this.avatar = avatar;
/*     */   }
/*     */ 
/*     */   @Column(name="essena_count", nullable=false, length=10)
/*     */   public Integer getEssenaCount() {
/*  83 */     return this.essenaCount;
/*     */   }
/*     */ 
/*     */   public void setEssenaCount(Integer essenaCount) {
/*  87 */     this.essenaCount = essenaCount;
/*     */   }
/*     */ 
/*     */   @Column(name="theme_count", nullable=false, length=10)
/*     */   public Integer getThemeCount() {
/*  92 */     return this.themeCount;
/*     */   }
/*     */ 
/*     */   public void setThemeCount(Integer themeCount) {
/*  96 */     this.themeCount = themeCount;
/*     */   }
/*     */ 
/*     */   @Column(name="reply_count", nullable=false, length=10)
/*     */   public Integer getReplyCount() {
/* 101 */     return this.replyCount;
/*     */   }
/*     */ 
/*     */   public void setReplyCount(Integer replyCount) {
/* 105 */     this.replyCount = replyCount;
/*     */   }
/*     */ 
/*     */   @Column(name="point", nullable=false, length=10)
/*     */   public Integer getPoint() {
/* 110 */     return this.point;
/*     */   }
/*     */ 
/*     */   public void setPoint(Integer point) {
/* 114 */     this.point = point;
/*     */   }
/*     */ 
/*     */   @Column(name="status", nullable=false, length=10)
/*     */   public Integer getStatus() {
/* 119 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Integer status) {
/* 123 */     this.status = status;
/*     */   }
/*     */   @Temporal(TemporalType.DATE)
/*     */   @Column(name="status_time", nullable=true, length=10)
/*     */   public Date getStatusTime() {
/* 129 */     return this.statusTime;
/*     */   }
/*     */ 
/*     */   public void setStatusTime(Date statusTime) {
/* 133 */     this.statusTime = statusTime;
/*     */   }
/*     */ 
/*     */   @Column(name="signature", nullable=true, length=255)
/*     */   public String getSignature() {
/* 138 */     return this.signature;
/*     */   }
/*     */ 
/*     */   public void setSignature(String signature) {
/* 142 */     this.signature = signature;
/*     */   }
/*     */   @OneToOne
/*     */   @PrimaryKeyJoinColumn
/*     */   public User getUser() {
/* 148 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(User user) {
/* 152 */     this.user = user;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.UserForum
 * JD-Core Version:    0.6.1
 */