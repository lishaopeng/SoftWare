/*    */ package com.portal.govcenter.entity;
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
/*    */ @Table(name="tq_petition_ext")
/*    */ public class PetitionExt
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String content;
/*    */   private String reply;
/*    */   private Petition petition;
/*    */ 
/*    */   @Id
/*    */   @Column(name="petition_id", unique=true, nullable=false)
/*    */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="petition")})
/*    */   @GeneratedValue(generator="copy")
/*    */   public Integer getId()
/*    */   {
/* 36 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 40 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="content", nullable=false, length=2000)
/*    */   public String getContent() {
/* 45 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 49 */     this.content = content;
/*    */   }
/*    */ 
/*    */   @Column(name="reply", nullable=true, length=2000)
/*    */   public String getReply() {
/* 54 */     return this.reply;
/*    */   }
/*    */ 
/*    */   public void setReply(String reply) {
/* 58 */     this.reply = reply;
/*    */   }
/*    */   @OneToOne
/*    */   @PrimaryKeyJoinColumn
/*    */   public Petition getPetition() {
/* 64 */     return this.petition;
/*    */   }
/*    */ 
/*    */   public void setPetition(Petition petition) {
/* 68 */     this.petition = petition;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.entity.PetitionExt
 * JD-Core Version:    0.6.1
 */