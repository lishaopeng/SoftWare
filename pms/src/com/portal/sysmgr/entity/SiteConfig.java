/*     */ package com.portal.sysmgr.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Transient;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_site_config")
/*     */ public class SiteConfig
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private Boolean commentCheck;
/*     */   private Boolean commentLogin;
/*     */   private Boolean messageCheck;
/*     */   private Boolean messageLogin;
/*     */   private Boolean messageName;
/*     */   private Boolean messageMobile;
/*     */   private Boolean messageEmail;
/*     */   private Boolean messageAddress;
/*     */   private Boolean messageZipcode;
/*     */   private Boolean regOpen;
/*     */   private Integer regMin;
/*     */   private Integer regMax;
/*     */   private Boolean regCheck;
/*     */   private Integer loginCount;
/*     */   private Site site;
/*     */ 
/*     */   public void init()
/*     */   {
/*  23 */     if (getCommentCheck() == null) {
/*  24 */       setCommentCheck(Boolean.valueOf(true));
/*     */     }
/*  26 */     if (getCommentLogin() == null) {
/*  27 */       setCommentLogin(Boolean.valueOf(false));
/*     */     }
/*  29 */     if (getMessageCheck() == null) {
/*  30 */       setMessageCheck(Boolean.valueOf(true));
/*     */     }
/*  32 */     if (getMessageLogin() == null) {
/*  33 */       setMessageLogin(Boolean.valueOf(false));
/*     */     }
/*  35 */     if (getMessageName() == null) {
/*  36 */       setMessageName(Boolean.valueOf(false));
/*     */     }
/*  38 */     if (getMessageMobile() == null) {
/*  39 */       setMessageMobile(Boolean.valueOf(false));
/*     */     }
/*  41 */     if (getMessageEmail() == null) {
/*  42 */       setMessageEmail(Boolean.valueOf(false));
/*     */     }
/*  44 */     if (getMessageAddress() == null) {
/*  45 */       setMessageAddress(Boolean.valueOf(false));
/*     */     }
/*  47 */     if (getMessageZipcode() == null) {
/*  48 */       setMessageZipcode(Boolean.valueOf(false));
/*     */     }
/*  50 */     if (getRegOpen() == null) {
/*  51 */       setRegOpen(Boolean.valueOf(true));
/*     */     }
/*  53 */     if (getRegCheck() == null)
/*  54 */       setRegCheck(Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean getNeedCheck()
/*     */   {
/*  60 */     if ((getLoginCount() != null) && (getLoginCount().intValue() > 0)) {
/*  61 */       return true;
/*     */     }
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="config_id", unique=true, nullable=false)
/*     */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="site")})
/*     */   @GeneratedValue(generator="copy")
/*     */   public Integer getId()
/*     */   {
/*  93 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  97 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="comment_check", nullable=false)
/*     */   public Boolean getCommentCheck() {
/* 102 */     return this.commentCheck;
/*     */   }
/*     */ 
/*     */   public void setCommentCheck(Boolean commentCheck) {
/* 106 */     this.commentCheck = commentCheck;
/*     */   }
/*     */ 
/*     */   @Column(name="comment_login", nullable=false)
/*     */   public Boolean getCommentLogin() {
/* 111 */     return this.commentLogin;
/*     */   }
/*     */ 
/*     */   public void setCommentLogin(Boolean commentLogin) {
/* 115 */     this.commentLogin = commentLogin;
/*     */   }
/*     */ 
/*     */   @Column(name="message_check", nullable=false)
/*     */   public Boolean getMessageCheck() {
/* 120 */     return this.messageCheck;
/*     */   }
/*     */ 
/*     */   public void setMessageCheck(Boolean messageCheck) {
/* 124 */     this.messageCheck = messageCheck;
/*     */   }
/*     */ 
/*     */   @Column(name="message_login", nullable=false)
/*     */   public Boolean getMessageLogin() {
/* 129 */     return this.messageLogin;
/*     */   }
/*     */ 
/*     */   public void setMessageLogin(Boolean messageLogin) {
/* 133 */     this.messageLogin = messageLogin;
/*     */   }
/*     */ 
/*     */   @Column(name="message_name", nullable=false)
/*     */   public Boolean getMessageName() {
/* 138 */     return this.messageName;
/*     */   }
/*     */ 
/*     */   public void setMessageName(Boolean messageName) {
/* 142 */     this.messageName = messageName;
/*     */   }
/*     */ 
/*     */   @Column(name="message_mobile", nullable=false)
/*     */   public Boolean getMessageMobile() {
/* 147 */     return this.messageMobile;
/*     */   }
/*     */ 
/*     */   public void setMessageMobile(Boolean messageMobile) {
/* 151 */     this.messageMobile = messageMobile;
/*     */   }
/*     */ 
/*     */   @Column(name="message_email", nullable=false)
/*     */   public Boolean getMessageEmail() {
/* 156 */     return this.messageEmail;
/*     */   }
/*     */ 
/*     */   public void setMessageEmail(Boolean messageEmail) {
/* 160 */     this.messageEmail = messageEmail;
/*     */   }
/*     */ 
/*     */   @Column(name="message_address", nullable=false)
/*     */   public Boolean getMessageAddress() {
/* 165 */     return this.messageAddress;
/*     */   }
/*     */ 
/*     */   public void setMessageAddress(Boolean messageAddress) {
/* 169 */     this.messageAddress = messageAddress;
/*     */   }
/*     */ 
/*     */   @Column(name="message_zipcode", nullable=false)
/*     */   public Boolean getMessageZipcode() {
/* 174 */     return this.messageZipcode;
/*     */   }
/*     */ 
/*     */   public void setMessageZipcode(Boolean messageZipcode) {
/* 178 */     this.messageZipcode = messageZipcode;
/*     */   }
/*     */ 
/*     */   @Column(name="reg_open", nullable=false)
/*     */   public Boolean getRegOpen() {
/* 183 */     return this.regOpen;
/*     */   }
/*     */ 
/*     */   public void setRegOpen(Boolean regOpen) {
/* 187 */     this.regOpen = regOpen;
/*     */   }
/*     */ 
/*     */   @Column(name="reg_min", nullable=true, length=10)
/*     */   public Integer getRegMin() {
/* 192 */     return this.regMin;
/*     */   }
/*     */ 
/*     */   public void setRegMin(Integer regMin) {
/* 196 */     this.regMin = regMin;
/*     */   }
/*     */ 
/*     */   @Column(name="reg_max", nullable=true, length=10)
/*     */   public Integer getRegMax() {
/* 201 */     return this.regMax;
/*     */   }
/*     */ 
/*     */   public void setRegMax(Integer regMax) {
/* 205 */     this.regMax = regMax;
/*     */   }
/*     */ 
/*     */   @Column(name="reg_check", nullable=false)
/*     */   public Boolean getRegCheck() {
/* 210 */     return this.regCheck;
/*     */   }
/*     */ 
/*     */   public void setRegCheck(Boolean regCheck) {
/* 214 */     this.regCheck = regCheck;
/*     */   }
/*     */ 
/*     */   @Column(name="login_count", nullable=true, length=10)
/*     */   public Integer getLoginCount() {
/* 219 */     return this.loginCount;
/*     */   }
/*     */ 
/*     */   public void setLoginCount(Integer loginCount) {
/* 223 */     this.loginCount = loginCount;
/*     */   }
/*     */   @OneToOne
/*     */   @PrimaryKeyJoinColumn
/*     */   public Site getSite() {
/* 229 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 233 */     this.site = site;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.entity.SiteConfig
 * JD-Core Version:    0.6.1
 */