/*    */ package com.portal.extrafunc.entity;
/*    */ 
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.OneToOne;
/*    */ import javax.persistence.PrimaryKeyJoinColumn;
/*    */ import javax.persistence.Table;
/*    */ import org.hibernate.annotations.GenericGenerator;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="tq_forum_statis")
/*    */ public class ForumStatis
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private Integer postsToday;
/*    */   private Integer postsYestoday;
/*    */   private Integer highestDay;
/*    */   private Integer postsTotal;
/*    */   private Site site;
/*    */ 
/*    */   @Id
/*    */   @Column(name="statis_id", unique=true, nullable=false)
/*    */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="site")})
/*    */   @GeneratedValue(generator="copy")
/*    */   public Integer getId()
/*    */   {
/* 38 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 42 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="posts_today", nullable=false, length=11)
/*    */   public Integer getPostsToday() {
/* 47 */     return this.postsToday;
/*    */   }
/*    */ 
/*    */   public void setPostsToday(Integer postsToday) {
/* 51 */     this.postsToday = postsToday;
/*    */   }
/*    */ 
/*    */   @Column(name="posts_yestoday", nullable=false, length=11)
/*    */   public Integer getPostsYestoday() {
/* 56 */     return this.postsYestoday;
/*    */   }
/*    */ 
/*    */   public void setPostsYestoday(Integer postsYestoday) {
/* 60 */     this.postsYestoday = postsYestoday;
/*    */   }
/*    */ 
/*    */   @Column(name="highest_day", nullable=false, length=11)
/*    */   public Integer getHighestDay() {
/* 65 */     return this.highestDay;
/*    */   }
/*    */ 
/*    */   public void setHighestDay(Integer highestDay) {
/* 69 */     this.highestDay = highestDay;
/*    */   }
/*    */ 
/*    */   @Column(name="posts_total", nullable=false, length=11)
/*    */   public Integer getPostsTotal() {
/* 74 */     return this.postsTotal;
/*    */   }
/*    */ 
/*    */   public void setPostsTotal(Integer postsTotal) {
/* 78 */     this.postsTotal = postsTotal;
/*    */   }
/*    */   @OneToOne
/*    */   @PrimaryKeyJoinColumn
/*    */   public Site getSite() {
/* 84 */     return this.site;
/*    */   }
/*    */ 
/*    */   public void setSite(Site site) {
/* 88 */     this.site = site;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.ForumStatis
 * JD-Core Version:    0.6.1
 */