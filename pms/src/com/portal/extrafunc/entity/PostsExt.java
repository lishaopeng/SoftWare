/*     */ package com.portal.extrafunc.entity;
/*     */ 
/*     */ import com.portal.usermgr.entity.User;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_posts_ext")
/*     */ public class PostsExt
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private String createIp;
/*     */   private Date editTime;
/*     */   private String editIp;
/*     */   private Integer editCount;
/*     */   private Posts posts;
/*     */   private User editer;
/*     */ 
/*     */   public void init()
/*     */   {
/*  27 */     if (getEditCount() == null)
/*  28 */       setEditCount(Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="posts_id", unique=true, nullable=false)
/*     */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="posts")})
/*     */   @GeneratedValue(generator="copy")
/*     */   public Integer getId()
/*     */   {
/*  52 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  56 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="create_ip", nullable=false, length=20)
/*     */   public String getCreateIp() {
/*  61 */     return this.createIp;
/*     */   }
/*     */ 
/*     */   public void setCreateIp(String createIp) {
/*  65 */     this.createIp = createIp;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="edit_time", nullable=true, length=19)
/*     */   public Date getEditTime() {
/*  71 */     return this.editTime;
/*     */   }
/*     */ 
/*     */   public void setEditTime(Date editTime) {
/*  75 */     this.editTime = editTime;
/*     */   }
/*     */ 
/*     */   @Column(name="edit_ip", nullable=true, length=20)
/*     */   public String getEditIp() {
/*  80 */     return this.editIp;
/*     */   }
/*     */ 
/*     */   public void setEditIp(String editIp) {
/*  84 */     this.editIp = editIp;
/*     */   }
/*     */ 
/*     */   @Column(name="edit_count", nullable=false, length=10)
/*     */   public Integer getEditCount() {
/*  89 */     return this.editCount;
/*     */   }
/*     */ 
/*     */   public void setEditCount(Integer editCount) {
/*  93 */     this.editCount = editCount;
/*     */   }
/*     */   @OneToOne
/*     */   @PrimaryKeyJoinColumn
/*     */   public Posts getPosts() {
/*  99 */     return this.posts;
/*     */   }
/*     */ 
/*     */   public void setPosts(Posts posts) {
/* 103 */     this.posts = posts;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="editer_id", nullable=true)
/*     */   public User getEditer() {
/* 109 */     return this.editer;
/*     */   }
/*     */ 
/*     */   public void setEditer(User editer) {
/* 113 */     this.editer = editer;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.PostsExt
 * JD-Core Version:    0.6.1
 */