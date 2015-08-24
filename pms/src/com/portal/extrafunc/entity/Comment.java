/*     */ package com.portal.extrafunc.entity;
/*     */ 
/*     */ import com.portal.doccenter.entity.Article;
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
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_comment")
/*     */ public class Comment
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private Integer ups;
/*     */   private Date createTime;
/*     */   private Date lastTime;
/*     */   private Boolean checked;
/*     */   private CommentExt commentExt;
/*     */   private Comment parent;
/*     */   private Article doc;
/*     */   private User user;
/*     */   private Site site;
/*     */ 
/*     */   public void init()
/*     */   {
/*  30 */     if (getUps() == null) {
/*  31 */       setUps(Integer.valueOf(0));
/*     */     }
/*  33 */     if (getCreateTime() == null) {
/*  34 */       setCreateTime(new Timestamp(System.currentTimeMillis()));
/*     */     }
/*  36 */     if (getLastTime() == null) {
/*  37 */       setLastTime(new Timestamp(System.currentTimeMillis()));
/*     */     }
/*  39 */     if (getChecked() == null)
/*  40 */       setChecked(Boolean.valueOf(!getSite().getCommentCheck().booleanValue()));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getIp()
/*     */   {
/*  46 */     CommentExt ext = getCommentExt();
/*  47 */     if (ext != null) {
/*  48 */       return ext.getIp();
/*     */     }
/*  50 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getContent() {
/*  55 */     CommentExt ext = getCommentExt();
/*  56 */     if (ext != null) {
/*  57 */       return ext.getContent().trim();
/*     */     }
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="comment_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_comment", pkColumnValue="tq_comment", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_comment")
/*     */   public Integer getId()
/*     */   {
/*  85 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  89 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="ups", nullable=false, length=5)
/*     */   public Integer getUps() {
/*  94 */     return this.ups;
/*     */   }
/*     */ 
/*     */   public void setUps(Integer ups) {
/*  98 */     this.ups = ups;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="create_time", nullable=false, length=19)
/*     */   public Date getCreateTime() {
/* 104 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/* 108 */     this.createTime = createTime;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="last_time", nullable=false, length=19)
/*     */   public Date getLastTime() {
/* 114 */     return this.lastTime;
/*     */   }
/*     */ 
/*     */   public void setLastTime(Date lastTime) {
/* 118 */     this.lastTime = lastTime;
/*     */   }
/*     */ 
/*     */   @Column(name="is_checked", nullable=false, length=1)
/*     */   public Boolean getChecked() {
/* 123 */     return this.checked;
/*     */   }
/*     */ 
/*     */   public void setChecked(Boolean checked) {
/* 127 */     this.checked = checked;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public CommentExt getCommentExt() {
/* 133 */     return this.commentExt;
/*     */   }
/*     */ 
/*     */   public void setCommentExt(CommentExt commentExt) {
/* 137 */     this.commentExt = commentExt;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="parent_id", nullable=true)
/*     */   public Comment getParent() {
/* 143 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public void setParent(Comment parent) {
/* 147 */     this.parent = parent;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="doc_id", nullable=false)
/*     */   public Article getDoc() {
/* 153 */     return this.doc;
/*     */   }
/*     */ 
/*     */   public void setDoc(Article doc) {
/* 157 */     this.doc = doc;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="user_id", nullable=true)
/*     */   public User getUser() {
/* 163 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(User user) {
/* 167 */     this.user = user;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 173 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 177 */     this.site = site;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.Comment
 * JD-Core Version:    0.6.1
 */