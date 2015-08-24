/*    */ package com.portal.doccenter.entity;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Embeddable;
/*    */ import javax.persistence.Transient;
/*    */ 
/*    */ @Embeddable
/*    */ public class ArticlePicture
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String imgPath;
/*    */   private String description;
/*    */   private Boolean thumb;
/*    */   private String style;
/*    */ 
/*    */   @Transient
/*    */   public boolean getCover()
/*    */   {
/* 15 */     if ((getThumb() != null) && (!getThumb().booleanValue()) && 
/* 16 */       (getStyle() != null) && 
/* 17 */       (getStyle().indexOf(",1,") > -1)) {
/* 18 */       return true;
/*    */     }
/*    */ 
/* 22 */     return false;
/*    */   }
/*    */ 
/*    */   @Column(name="img_path", nullable=false, length=100)
/*    */   public String getImgPath()
/*    */   {
/* 33 */     return this.imgPath;
/*    */   }
/*    */ 
/*    */   public void setImgPath(String imgPath) {
/* 37 */     this.imgPath = imgPath;
/*    */   }
/*    */ 
/*    */   @Column(name="description", nullable=true, length=255)
/*    */   public String getDescription() {
/* 42 */     return this.description;
/*    */   }
/*    */ 
/*    */   public void setDescription(String description) {
/* 46 */     this.description = description;
/*    */   }
/*    */ 
/*    */   @Column(name="is_thumb", nullable=true, length=1)
/*    */   public Boolean getThumb() {
/* 51 */     return this.thumb;
/*    */   }
/*    */ 
/*    */   public void setThumb(Boolean thumb) {
/* 55 */     this.thumb = thumb;
/*    */   }
/*    */ 
/*    */   @Column(name="style", nullable=true, length=50)
/*    */   public String getStyle() {
/* 60 */     return this.style;
/*    */   }
/*    */ 
/*    */   public void setStyle(String style) {
/* 64 */     this.style = style;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.ArticlePicture
 * JD-Core Version:    0.6.1
 */