/*    */ package com.portal.extrafunc.entity;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Lob;
/*    */ import javax.persistence.OneToOne;
/*    */ import javax.persistence.PrimaryKeyJoinColumn;
/*    */ import javax.persistence.Table;
/*    */ import org.hibernate.annotations.GenericGenerator;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="tq_posts_txt")
/*    */ public class PostsTxt
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String content;
/*    */   private Posts posts;
/*    */ 
/*    */   @Id
/*    */   @Column(name="posts_id", unique=true, nullable=false)
/*    */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="posts")})
/*    */   @GeneratedValue(generator="copy")
/*    */   public Integer getId()
/*    */   {
/* 36 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 40 */     this.id = id;
/*    */   }
/*    */   @Lob
/*    */   @Column(name="content", nullable=true)
/*    */   public String getContent() {
/* 46 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 50 */     this.content = content;
/*    */   }
/*    */   @OneToOne
/*    */   @PrimaryKeyJoinColumn
/*    */   public Posts getPosts() {
/* 56 */     return this.posts;
/*    */   }
/*    */ 
/*    */   public void setPosts(Posts posts) {
/* 60 */     this.posts = posts;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.PostsTxt
 * JD-Core Version:    0.6.1
 */