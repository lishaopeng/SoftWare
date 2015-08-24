/*    */ package com.javapms.basic.upload;
/*    */ 
/*    */ import com.javapms.basic.utils.UploadUtils;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.Locale;
/*    */ import javax.servlet.ServletContext;
/*    */ import org.apache.commons.io.FileUtils;
/*    */ import org.apache.commons.io.FilenameUtils;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.web.context.ServletContextAware;
/*    */ import org.springframework.web.multipart.MultipartFile;
/*    */ 
/*    */ public class FileRepository
/*    */   implements ServletContextAware
/*    */ {
/* 25 */   private Logger log = LoggerFactory.getLogger(FileRepository.class);
/*    */   private ServletContext ctx;
/*    */ 
/*    */   public String uploadFile(MultipartFile file, String uploadPath)
/*    */   {
/* 28 */     String origName = file.getOriginalFilename();
/* 29 */     String ext = FilenameUtils.getExtension(origName).toLowerCase(
/* 30 */       Locale.ENGLISH);
/* 31 */     String fileUrl = storeByExt(uploadPath, ext, file);
/* 32 */     return fileUrl;
/*    */   }
/*    */ 
/*    */   private String storeByExt(String path, String ext, MultipartFile file) {
/* 36 */     String filename = UploadUtils.generateFilename(path, ext);
/* 37 */     File dest = new File(this.ctx.getRealPath(filename));
/* 38 */     dest = UploadUtils.getUniqueFile(dest);
/*    */     try {
/* 40 */       store(file, dest);
/*    */     } catch (IOException e) {
/* 42 */       e.printStackTrace();
/*    */     }
/* 44 */     return filename;
/*    */   }
/*    */ 
/*    */   public String storeByFilename(String filename, MultipartFile file) throws IOException
/*    */   {
/* 49 */     File dest = new File(this.ctx.getRealPath(filename));
/* 50 */     store(file, dest);
/* 51 */     return filename;
/*    */   }
/*    */ 
/*    */   public String storeByExt(String path, String ext, File file) throws IOException
/*    */   {
/* 56 */     String filename = UploadUtils.generateFilename(path, ext);
/* 57 */     File dest = new File(this.ctx.getRealPath(filename));
/* 58 */     dest = UploadUtils.getUniqueFile(dest);
/* 59 */     store(file, dest);
/* 60 */     return filename;
/*    */   }
/*    */ 
/*    */   public String storeByFilename(String filename, File file) throws IOException
/*    */   {
/* 65 */     File dest = new File(this.ctx.getRealPath(filename));
/* 66 */     store(file, dest);
/* 67 */     return filename;
/*    */   }
/*    */ 
/*    */   private void store(MultipartFile file, File dest) throws IOException {
/*    */     try {
/* 72 */       UploadUtils.checkDirAndCreate(dest.getParentFile());
/* 73 */       file.transferTo(dest);
/*    */     } catch (IOException e) {
/* 75 */       this.log.error("Transfer file error when upload file", e);
/* 76 */       throw e;
/*    */     }
/*    */   }
/*    */ 
/*    */   private void store(File file, File dest) throws IOException {
/*    */     try {
/* 82 */       UploadUtils.checkDirAndCreate(dest.getParentFile());
/* 83 */       FileUtils.copyFile(file, dest);
/*    */     } catch (IOException e) {
/* 85 */       this.log.error("Transfer file error when upload file", e);
/* 86 */       throw e;
/*    */     }
/*    */   }
/*    */ 
/*    */   public File retrieve(String name) {
/* 91 */     return new File(this.ctx.getRealPath(name));
/*    */   }
/*    */ 
/*    */   public void setServletContext(ServletContext servletContext)
/*    */   {
/* 97 */     this.ctx = servletContext;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.upload.FileRepository
 * JD-Core Version:    0.6.1
 */