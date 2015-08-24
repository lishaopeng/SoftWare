/*     */ package com.portal.usermgr.entity;
/*     */ 
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
/*     */ @Table(name="tq_site_message")
/*     */ public class SiteMessage
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  29 */   public static final Integer DEL = Integer.valueOf(-2);
/*     */ 
/*  31 */   public static final Integer RECYCLE = Integer.valueOf(-1);
/*     */ 
/*  33 */   public static final Integer NORMAL = Integer.valueOf(0);
/*     */   private Integer id;
/*     */   private String title;
/*     */   private String content;
/*     */   private Boolean group;
/*     */   private Integer status;
/*     */   private Date createTime;
/*     */   private MessageReceive receive;
/*     */   private User send;
/*     */ 
/*     */   public void init()
/*     */   {
/*  36 */     if (getGroup() == null) {
/*  37 */       setGroup(Boolean.valueOf(false));
/*     */     }
/*  39 */     if (getStatus() == null) {
/*  40 */       setStatus(NORMAL);
/*     */     }
/*  42 */     if (getCreateTime() == null)
/*  43 */       setCreateTime(new Timestamp(System.currentTimeMillis()));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getReceContent()
/*     */   {
/*  49 */     MessageReceive ext = getReceive();
/*  50 */     if (ext != null) {
/*  51 */       return ext.getContent();
/*     */     }
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="message_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_site_message", pkColumnValue="tq_site_message", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_site_message")
/*     */   public Integer getId()
/*     */   {
/*  77 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  81 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="title", nullable=false, length=50)
/*     */   public String getTitle() {
/*  86 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/*  90 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="content", nullable=false, length=1000)
/*     */   public String getContent() {
/*  95 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content) {
/*  99 */     this.content = content;
/*     */   }
/*     */ 
/*     */   @Column(name="is_group", nullable=false, length=1)
/*     */   public Boolean getGroup() {
/* 104 */     return this.group;
/*     */   }
/*     */ 
/*     */   public void setGroup(Boolean group) {
/* 108 */     this.group = group;
/*     */   }
/*     */ 
/*     */   @Column(name="status", nullable=false, length=10)
/*     */   public Integer getStatus() {
/* 113 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Integer status) {
/* 117 */     this.status = status;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="create_time", nullable=false, length=19)
/*     */   public Date getCreateTime() {
/* 123 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/* 127 */     this.createTime = createTime;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public MessageReceive getReceive() {
/* 133 */     return this.receive;
/*     */   }
/*     */ 
/*     */   public void setReceive(MessageReceive receive) {
/* 137 */     this.receive = receive;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="send_id", nullable=false)
/*     */   public User getSend() {
/* 143 */     return this.send;
/*     */   }
/*     */ 
/*     */   public void setSend(User send) {
/* 147 */     this.send = send;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.entity.SiteMessage
 * JD-Core Version:    0.6.1
 */