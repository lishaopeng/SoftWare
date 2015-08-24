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
/*    */ @Table(name="tq_comment_ext")
/*    */ public class CommentExt
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String ip;
/*    */   private String content;
/*    */   private Comment comment;
/*    */ 
/*    */   @Id
/*    */   @Column(name="comment_id", unique=true, nullable=false)
/*    */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="comment")})
/*    */   @GeneratedValue(generator="copy")
/*    */   public Integer getId()
/*    */   {
/* 37 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 41 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="ip", nullable=true, length=20)
/*    */   public String getIp() {
/* 46 */     return this.ip;
/*    */   }
/*    */ 
/*    */   public void setIp(String ip) {
/* 50 */     this.ip = ip;
/*    */   }
/*    */   @Lob
/*    */   @Column(name="content", nullable=true)
/*    */   public String getContent() {
/* 56 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 60 */     this.content = content;
/*    */   }
/*    */   @OneToOne
/*    */   @PrimaryKeyJoinColumn
/*    */   public Comment getComment() {
/* 66 */     return this.comment;
/*    */   }
/*    */ 
/*    */   public void setComment(Comment comment) {
/* 70 */     this.comment = comment;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.CommentExt
 * JD-Core Version:    0.6.1
 */