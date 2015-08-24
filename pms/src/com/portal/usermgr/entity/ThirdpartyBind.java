/*     */ package com.portal.usermgr.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_thirdparty_bind")
/*     */ public class ThirdpartyBind
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String QQ = "qq";
/*     */   public static final String SINA = "sina";
/*     */   private Integer id;
/*     */   private String username;
/*     */   private String openid;
/*     */   private String openkey;
/*     */   private Date bindTime;
/*     */   private String bindType;
/*     */ 
/*     */   public void init()
/*     */   {
/*  31 */     if (getBindTime() == null) {
/*  32 */       setBindTime(new Timestamp(System.currentTimeMillis()));
/*     */     }
/*  34 */     if (StringUtils.isBlank(getBindType()))
/*  35 */       setBindType("qq");
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="bind_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_thirdparty_bind", pkColumnValue="tq_thirdparty_bind", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_thirdparty_bind")
/*     */   public Integer getId()
/*     */   {
/*  54 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  58 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="username", nullable=false, length=100)
/*     */   public String getUsername() {
/*  63 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username) {
/*  67 */     this.username = username;
/*     */   }
/*     */ 
/*     */   @Column(name="openid", nullable=false, length=100)
/*     */   public String getOpenid() {
/*  72 */     return this.openid;
/*     */   }
/*     */ 
/*     */   public void setOpenid(String openid) {
/*  76 */     this.openid = openid;
/*     */   }
/*     */ 
/*     */   @Column(name="openkey", nullable=false, length=100)
/*     */   public String getOpenkey() {
/*  81 */     return this.openkey;
/*     */   }
/*     */ 
/*     */   public void setOpenkey(String openkey) {
/*  85 */     this.openkey = openkey;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="bind_time", nullable=false, length=19)
/*     */   public Date getBindTime() {
/*  91 */     return this.bindTime;
/*     */   }
/*     */ 
/*     */   public void setBindTime(Date bindTime) {
/*  95 */     this.bindTime = bindTime;
/*     */   }
/*     */ 
/*     */   @Column(name="bind_type", nullable=false, length=50)
/*     */   public String getBindType() {
/* 100 */     return this.bindType;
/*     */   }
/*     */ 
/*     */   public void setBindType(String bindType) {
/* 104 */     this.bindType = bindType;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.entity.ThirdpartyBind
 * JD-Core Version:    0.6.1
 */