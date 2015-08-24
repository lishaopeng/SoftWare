/*    */ package com.portal.extrafunc.entity;
/*    */ 
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
/*    */ @Table(name="tq_forum_ext")
/*    */ public class ForumExt
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String keywords;
/*    */   private String description;
/*    */   private String rule;
/*    */   private String tplContent;
/*    */   private Forum forum;
/*    */ 
/*    */   @Id
/*    */   @Column(name="forum_id", unique=true, nullable=false)
/*    */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="forum")})
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
/*    */   @Column(name="keywords", nullable=true, length=200)
/*    */   public String getKeywords() {
/* 47 */     return this.keywords;
/*    */   }
/*    */ 
/*    */   public void setKeywords(String keywords) {
/* 51 */     this.keywords = keywords;
/*    */   }
/*    */ 
/*    */   @Column(name="description", nullable=true, length=255)
/*    */   public String getDescription() {
/* 56 */     return this.description;
/*    */   }
/*    */ 
/*    */   public void setDescription(String description) {
/* 60 */     this.description = description;
/*    */   }
/*    */ 
/*    */   @Column(name="rule", nullable=true, length=255)
/*    */   public String getRule() {
/* 65 */     return this.rule;
/*    */   }
/*    */ 
/*    */   public void setRule(String rule) {
/* 69 */     this.rule = rule;
/*    */   }
/*    */ 
/*    */   @Column(name="tpl_content", nullable=true, length=150)
/*    */   public String getTplContent() {
/* 74 */     return this.tplContent;
/*    */   }
/*    */ 
/*    */   public void setTplContent(String tplContent) {
/* 78 */     this.tplContent = tplContent;
/*    */   }
/*    */   @OneToOne
/*    */   @PrimaryKeyJoinColumn
/*    */   public Forum getForum() {
/* 84 */     return this.forum;
/*    */   }
/*    */ 
/*    */   public void setForum(Forum forum) {
/* 88 */     this.forum = forum;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.ForumExt
 * JD-Core Version:    0.6.1
 */