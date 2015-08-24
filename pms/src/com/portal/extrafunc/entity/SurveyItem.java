/*    */ package com.portal.extrafunc.entity;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Embeddable;
/*    */ 
/*    */ @Embeddable
/*    */ public class SurveyItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String name;
/*    */   private Integer votes;
/*    */   private Integer priority;
/*    */ 
/*    */   public void init()
/*    */   {
/* 13 */     if (getVotes() == null) {
/* 14 */       setVotes(Integer.valueOf(0));
/*    */     }
/* 16 */     if (getPriority() == null)
/* 17 */       setPriority(Integer.valueOf(10));
/*    */   }
/*    */ 
/*    */   @Column(name="name", nullable=false, length=50)
/*    */   public String getName()
/*    */   {
/* 27 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 31 */     this.name = name;
/*    */   }
/*    */ 
/*    */   @Column(name="votes", nullable=false, length=10)
/*    */   public Integer getVotes() {
/* 36 */     return this.votes;
/*    */   }
/*    */ 
/*    */   public void setVotes(Integer votes) {
/* 40 */     this.votes = votes;
/*    */   }
/*    */ 
/*    */   @Column(name="priority", nullable=false, length=10)
/*    */   public Integer getPriority() {
/* 45 */     return this.priority;
/*    */   }
/*    */ 
/*    */   public void setPriority(Integer priority) {
/* 49 */     this.priority = priority;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.SurveyItem
 * JD-Core Version:    0.6.1
 */