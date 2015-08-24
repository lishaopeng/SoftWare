/*    */ package com.portal.usermgr.entity;
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
/*    */ @Table(name="tq_message_receive")
/*    */ public class MessageReceive
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String content;
/*    */   private SiteMessage message;
/*    */ 
/*    */   @Id
/*    */   @Column(name="message_id", unique=true, nullable=false)
/*    */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="message")})
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
/*    */   public SiteMessage getMessage() {
/* 56 */     return this.message;
/*    */   }
/*    */ 
/*    */   public void setMessage(SiteMessage message) {
/* 60 */     this.message = message;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.entity.MessageReceive
 * JD-Core Version:    0.6.1
 */