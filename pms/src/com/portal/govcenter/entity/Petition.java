/*     */ package com.portal.govcenter.entity;
/*     */ 
/*     */ import com.portal.sysmgr.entity.Site;
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
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_petition")
/*     */ public class Petition
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private String title;
/*     */   private String name;
/*     */   private String mobile;
/*     */   private String email;
/*     */   private String address;
/*     */   private String zipcode;
/*     */   private Byte status;
/*     */   private Boolean show;
/*     */   private Date createTime;
/*     */   private Date replyTime;
/*     */   private PetitionExt ext;
/*     */   private Site site;
/*     */   private PetitionType type;
/*     */ 
/*     */   @Id
/*     */   @Column(name="petition_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_petition", pkColumnValue="tq_petition", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_petition")
/*     */   public Integer getId()
/*     */   {
/*  54 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  58 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="title", nullable=false, length=50)
/*     */   public String getTitle() {
/*  63 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/*  67 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="name", nullable=false, length=20)
/*     */   public String getName() {
/*  72 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  76 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="mobile", nullable=false, length=20)
/*     */   public String getMobile() {
/*  81 */     return this.mobile;
/*     */   }
/*     */ 
/*     */   public void setMobile(String mobile) {
/*  85 */     this.mobile = mobile;
/*     */   }
/*     */ 
/*     */   @Column(name="email", nullable=false, length=50)
/*     */   public String getEmail() {
/*  90 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email) {
/*  94 */     this.email = email;
/*     */   }
/*     */ 
/*     */   @Column(name="address", nullable=true, length=150)
/*     */   public String getAddress() {
/*  99 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address) {
/* 103 */     this.address = address;
/*     */   }
/*     */ 
/*     */   @Column(name="zipcode", nullable=true, length=20)
/*     */   public String getZipcode() {
/* 108 */     return this.zipcode;
/*     */   }
/*     */ 
/*     */   public void setZipcode(String zipcode) {
/* 112 */     this.zipcode = zipcode;
/*     */   }
/*     */ 
/*     */   @Column(name="status", nullable=false, length=5)
/*     */   public Byte getStatus() {
/* 117 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Byte status) {
/* 121 */     this.status = status;
/*     */   }
/*     */ 
/*     */   @Column(name="is_show", nullable=false, length=1)
/*     */   public Boolean getShow() {
/* 126 */     return this.show;
/*     */   }
/*     */ 
/*     */   public void setShow(Boolean show) {
/* 130 */     this.show = show;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="create_time", nullable=false, length=19)
/*     */   public Date getCreateTime() {
/* 136 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/* 140 */     this.createTime = createTime;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="reply_time", nullable=true, length=19)
/*     */   public Date getReplyTime() {
/* 146 */     return this.replyTime;
/*     */   }
/*     */ 
/*     */   public void setReplyTime(Date replyTime) {
/* 150 */     this.replyTime = replyTime;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public PetitionExt getExt() {
/* 156 */     return this.ext;
/*     */   }
/*     */ 
/*     */   public void setExt(PetitionExt ext) {
/* 160 */     this.ext = ext;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 166 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 170 */     this.site = site;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="type_id", nullable=false)
/*     */   public PetitionType getType() {
/* 176 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(PetitionType type) {
/* 180 */     this.type = type;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.entity.Petition
 * JD-Core Version:    0.6.1
 */