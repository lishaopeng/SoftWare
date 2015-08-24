/*    */ package com.portal.extrafunc.entity;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Embeddable;
/*    */ 
/*    */ @Embeddable
/*    */ public class PostsAttach
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String name;
/*    */   private String description;
/*    */   private String filePath;
/*    */   private String fileName;
/*    */   private Integer fileSize;
/*    */   private Boolean img;
/*    */ 
/*    */   @Column(name="name", nullable=true, length=100)
/*    */   public String getName()
/*    */   {
/* 22 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 26 */     this.name = name;
/*    */   }
/*    */ 
/*    */   @Column(name="description", nullable=true, length=255)
/*    */   public String getDescription() {
/* 31 */     return this.description;
/*    */   }
/*    */ 
/*    */   public void setDescription(String description) {
/* 35 */     this.description = description;
/*    */   }
/*    */ 
/*    */   @Column(name="file_path", nullable=false, length=100)
/*    */   public String getFilePath() {
/* 40 */     return this.filePath;
/*    */   }
/*    */ 
/*    */   public void setFilePath(String filePath) {
/* 44 */     this.filePath = filePath;
/*    */   }
/*    */ 
/*    */   @Column(name="file_name", nullable=true, length=50)
/*    */   public String getFileName() {
/* 49 */     return this.fileName;
/*    */   }
/*    */ 
/*    */   public void setFileName(String fileName) {
/* 53 */     this.fileName = fileName;
/*    */   }
/*    */ 
/*    */   @Column(name="file_size", nullable=true, length=10)
/*    */   public Integer getFileSize() {
/* 58 */     return this.fileSize;
/*    */   }
/*    */ 
/*    */   public void setFileSize(Integer fileSize) {
/* 62 */     this.fileSize = fileSize;
/*    */   }
/*    */ 
/*    */   @Column(name="is_img", nullable=false, length=1)
/*    */   public Boolean getImg() {
/* 67 */     return this.img;
/*    */   }
/*    */ 
/*    */   public void setImg(Boolean img) {
/* 71 */     this.img = img;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.PostsAttach
 * JD-Core Version:    0.6.1
 */