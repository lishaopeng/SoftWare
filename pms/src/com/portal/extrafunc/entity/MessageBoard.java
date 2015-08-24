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
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_message_board")
/*     */ public class MessageBoard
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
/*     */   private Boolean show;
/*     */   private Date createTime;
/*     */   private Date replyTime;
/*     */   private MessageBoardExt ext;
/*     */   private Site site;
/*     */   private MessageType type;
/*     */ 
/*     */   public void init()
/*     */   {
/*  30 */     if (getShow() == null) {
/*  31 */       setShow(Boolean.valueOf(!getSite().getCommentCheck().booleanValue()));
/*     */     }
/*  33 */     if (getCreateTime() == null)
/*  34 */       setCreateTime(new Timestamp(System.currentTimeMillis()));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getContent()
/*     */   {
/*  40 */     MessageBoardExt ext = getExt();
/*  41 */     if (ext != null) {
/*  42 */       return ext.getContent();
/*     */     }
/*  44 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getReply() {
/*  49 */     MessageBoardExt ext = getExt();
/*  50 */     if (ext != null) {
/*  51 */       return ext.getReply();
/*     */     }
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="board_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_message_board", pkColumnValue="tq_message_board", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_message_board")
/*     */   public Integer getId()
/*     */   {
/*  82 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  86 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="title", nullable=false, length=50)
/*     */   public String getTitle() {
/*  91 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/*  95 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="name", nullable=true, length=20)
/*     */   public String getName() {
/* 100 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 104 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="mobile", nullable=true, length=20)
/*     */   public String getMobile() {
/* 109 */     return this.mobile;
/*     */   }
/*     */ 
/*     */   public void setMobile(String mobile) {
/* 113 */     this.mobile = mobile;
/*     */   }
/*     */ 
/*     */   @Column(name="email", nullable=true, length=50)
/*     */   public String getEmail() {
/* 118 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email) {
/* 122 */     this.email = email;
/*     */   }
/*     */ 
/*     */   @Column(name="address", nullable=true, length=150)
/*     */   public String getAddress() {
/* 127 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address) {
/* 131 */     this.address = address;
/*     */   }
/*     */ 
/*     */   @Column(name="zipcode", nullable=true, length=20)
/*     */   public String getZipcode() {
/* 136 */     return this.zipcode;
/*     */   }
/*     */ 
/*     */   public void setZipcode(String zipcode) {
/* 140 */     this.zipcode = zipcode;
/*     */   }
/*     */ 
/*     */   @Column(name="is_show", nullable=false, length=1)
/*     */   public Boolean getShow() {
/* 145 */     return this.show;
/*     */   }
/*     */ 
/*     */   public void setShow(Boolean show) {
/* 149 */     this.show = show;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="create_time", nullable=false, length=19)
/*     */   public Date getCreateTime() {
/* 155 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/* 159 */     this.createTime = createTime;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="reply_time", nullable=true, length=19)
/*     */   public Date getReplyTime() {
/* 165 */     return this.replyTime;
/*     */   }
/*     */ 
/*     */   public void setReplyTime(Date replyTime) {
/* 169 */     this.replyTime = replyTime;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public MessageBoardExt getExt() {
/* 175 */     return this.ext;
/*     */   }
/*     */ 
/*     */   public void setExt(MessageBoardExt ext) {
/* 179 */     this.ext = ext;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 185 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 189 */     this.site = site;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="type_id", nullable=false)
/*     */   public MessageType getType() {
/* 195 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(MessageType type) {
/* 199 */     this.type = type;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.MessageBoard
 * JD-Core Version:    0.6.1
 */