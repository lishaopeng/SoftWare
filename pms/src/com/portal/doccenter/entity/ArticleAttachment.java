/*    */ package com.portal.doccenter.entity;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Embeddable;
/*    */ 
/*    */ @Embeddable
/*    */ public class ArticleAttachment
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String path;
/*    */   private String name;
/*    */   private Integer count;
/*    */ 
/*    */   @Column(name="att_path", nullable=false, length=255)
/*    */   public String getPath()
/*    */   {
/* 18 */     return this.path;
/*    */   }
/*    */ 
/*    */   public void setPath(String path) {
/* 22 */     this.path = path;
/*    */   }
/*    */ 
/*    */   @Column(name="att_name", nullable=false, length=100)
/*    */   public String getName() {
/* 27 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 31 */     this.name = name;
/*    */   }
/*    */ 
/*    */   @Column(name="download_count", nullable=false, length=10)
/*    */   public Integer getCount() {
/* 36 */     return this.count;
/*    */   }
/*    */ 
/*    */   public void setCount(Integer count) {
/* 40 */     this.count = count;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.ArticleAttachment
 * JD-Core Version:    0.6.1
 */