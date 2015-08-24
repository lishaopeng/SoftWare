/*     */ package com.portal.govcenter.entity;
/*     */ 
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.usermgr.entity.Depart;
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
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_mailbox")
/*     */ public class Mailbox
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  32 */   public static final Byte DELETED = Byte.valueOf((byte)-2);
/*     */ 
/*  36 */   public static final Byte BACK = Byte.valueOf((byte)-1);
/*     */ 
/*  40 */   public static final Byte DEALING = Byte.valueOf((byte)1);
/*     */ 
/*  44 */   public static final Byte FORWARD = Byte.valueOf((byte)2);
/*     */ 
/*  48 */   public static final Byte DEALED = Byte.valueOf((byte)3);
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
/*     */   private MailboxExt ext;
/*     */   private Site site;
/*     */   private Depart depart;
/*     */   private MailboxType type;
/*     */ 
/*     */   public void init()
/*     */   {
/*  51 */     if (getStatus() == null) {
/*  52 */       setStatus(DEALING);
/*     */     }
/*  54 */     if (getShow() == null) {
/*  55 */       setShow(Boolean.valueOf(false));
/*     */     }
/*  57 */     if (getCreateTime() == null)
/*  58 */       setCreateTime(new Timestamp(System.currentTimeMillis()));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getStatusString()
/*     */   {
/*  64 */     if (getStatus().equals(DEALING))
/*  65 */       return "<span style='color:red'>受理中</span>";
/*  66 */     if (getStatus().equals(DEALED))
/*  67 */       return "<span style='color:blue'>已办结</span>";
/*  68 */     if (getStatus().equals(FORWARD))
/*  69 */       return "<span style='color:red'>批转</span>";
/*  70 */     if (getStatus().equals(BACK)) {
/*  71 */       return "<span style='color:red'>退回</span>";
/*     */     }
/*  73 */     return "<span style='color:red'>已删除</span>";
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getContent()
/*     */   {
/*  79 */     if (getExt() != null) {
/*  80 */       return getExt().getContent();
/*     */     }
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getReply() {
/*  87 */     if (getExt() != null) {
/*  88 */       return getExt().getReply();
/*     */     }
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="mailbox_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_mailbox", pkColumnValue="tq_mailbox", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_mailbox")
/*     */   public Integer getId()
/*     */   {
/* 121 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 125 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="title", nullable=false, length=50)
/*     */   public String getTitle() {
/* 130 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 134 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="name", nullable=false, length=20)
/*     */   public String getName() {
/* 139 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 143 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="mobile", nullable=false, length=20)
/*     */   public String getMobile() {
/* 148 */     return this.mobile;
/*     */   }
/*     */ 
/*     */   public void setMobile(String mobile) {
/* 152 */     this.mobile = mobile;
/*     */   }
/*     */ 
/*     */   @Column(name="email", nullable=false, length=50)
/*     */   public String getEmail() {
/* 157 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email) {
/* 161 */     this.email = email;
/*     */   }
/*     */ 
/*     */   @Column(name="address", nullable=true, length=150)
/*     */   public String getAddress() {
/* 166 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address) {
/* 170 */     this.address = address;
/*     */   }
/*     */ 
/*     */   @Column(name="zipcode", nullable=true, length=20)
/*     */   public String getZipcode() {
/* 175 */     return this.zipcode;
/*     */   }
/*     */ 
/*     */   public void setZipcode(String zipcode) {
/* 179 */     this.zipcode = zipcode;
/*     */   }
/*     */ 
/*     */   @Column(name="status", nullable=false, length=5)
/*     */   public Byte getStatus() {
/* 184 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Byte status) {
/* 188 */     this.status = status;
/*     */   }
/*     */ 
/*     */   @Column(name="is_show", nullable=false, length=1)
/*     */   public Boolean getShow() {
/* 193 */     return this.show;
/*     */   }
/*     */ 
/*     */   public void setShow(Boolean show) {
/* 197 */     this.show = show;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="create_time", nullable=false, length=19)
/*     */   public Date getCreateTime() {
/* 203 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/* 207 */     this.createTime = createTime;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="reply_time", nullable=true, length=19)
/*     */   public Date getReplyTime() {
/* 213 */     return this.replyTime;
/*     */   }
/*     */ 
/*     */   public void setReplyTime(Date replyTime) {
/* 217 */     this.replyTime = replyTime;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public MailboxExt getExt() {
/* 223 */     return this.ext;
/*     */   }
/*     */ 
/*     */   public void setExt(MailboxExt ext) {
/* 227 */     this.ext = ext;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 233 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 237 */     this.site = site;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="depart_id", nullable=true)
/*     */   public Depart getDepart() {
/* 243 */     return this.depart;
/*     */   }
/*     */ 
/*     */   public void setDepart(Depart depart) {
/* 247 */     this.depart = depart;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="type_id", nullable=false)
/*     */   public MailboxType getType() {
/* 253 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(MailboxType type) {
/* 257 */     this.type = type;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.entity.Mailbox
 * JD-Core Version:    0.6.1
 */