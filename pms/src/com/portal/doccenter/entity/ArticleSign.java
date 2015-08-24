/*     */ package com.portal.doccenter.entity;
/*     */ 
/*     */ import com.portal.usermgr.entity.Admin;
/*     */ import com.portal.usermgr.entity.Depart;
/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
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
/*     */ import javax.persistence.Transient;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_article_sign")
/*     */ public class ArticleSign
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private Date signTime;
/*     */   private Article article;
/*     */   private Admin admin;
/*     */   private Depart depart;
/*     */ 
/*     */   public void init()
/*     */   {
/*  28 */     if (getSignTime() == null)
/*  29 */       setSignTime(new Timestamp(System.currentTimeMillis()));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean isAfterDay()
/*     */   {
/*  35 */     return isAfterDay(2);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean isAfterDay(int day) {
/*  40 */     Date d = getArticle().getReleaseDate();
/*  41 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  42 */     String dstr = "2014-02-20";
/*  43 */     Date date = null;
/*     */     try {
/*  45 */       date = sdf.parse(dstr);
/*     */     } catch (ParseException e) {
/*  47 */       e.printStackTrace();
/*     */     }
/*  49 */     if (d.before(date)) {
/*  50 */       return false;
/*     */     }
/*  52 */     long s = getSignTime().getTime() - d.getTime();
/*  53 */     s /= 1000L;
/*  54 */     if (s > 86400 * day) {
/*  55 */       return true;
/*     */     }
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="sign_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_article_sign", pkColumnValue="tq_article_sign", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_article_sign")
/*     */   public Integer getId()
/*     */   {
/*  76 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  80 */     this.id = id;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="sign_time", nullable=false, length=19)
/*     */   public Date getSignTime() {
/*  86 */     return this.signTime;
/*     */   }
/*     */ 
/*     */   public void setSignTime(Date signTime) {
/*  90 */     this.signTime = signTime;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="article_id", nullable=false)
/*     */   public Article getArticle() {
/*  96 */     return this.article;
/*     */   }
/*     */ 
/*     */   public void setArticle(Article article) {
/* 100 */     this.article = article;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="admin_id", nullable=false)
/*     */   public Admin getAdmin() {
/* 106 */     return this.admin;
/*     */   }
/*     */ 
/*     */   public void setAdmin(Admin admin) {
/* 110 */     this.admin = admin;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="depart_id", nullable=false)
/*     */   public Depart getDepart() {
/* 116 */     return this.depart;
/*     */   }
/*     */ 
/*     */   public void setDepart(Depart depart) {
/* 120 */     this.depart = depart;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.ArticleSign
 * JD-Core Version:    0.6.1
 */