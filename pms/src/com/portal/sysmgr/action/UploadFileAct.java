/*    */ package com.portal.sysmgr.action;
/*    */ 
/*    */ import com.javapms.basic.upload.FileRepository;
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestParam;
/*    */ import org.springframework.web.multipart.MultipartFile;
/*    */ 
/*    */ @Controller
/*    */ public class UploadFileAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private FileRepository fileRepository;
/*    */ 
/*    */   @RequestMapping({"/kind/o_upload_file.do"})
/*    */   public void kinduploadImg(@RequestParam(value="imgFile", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response)
/*    */     throws JSONException
/*    */   {
/* 27 */     JSONObject json = new JSONObject();
/* 28 */     Site site = ContextTools.getSite(request);
/* 29 */     String fileUrl = this.fileRepository.uploadFile(file, site.getUploadPath());
/* 30 */     json.put("error", 0);
/* 31 */     json.put("url", "../.." + fileUrl);
/* 32 */     ResponseUtils.renderText(response, json.toString());
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/o_upload_attach.do"})
/*    */   public void uploadAttachment(@RequestParam(value="attachFile", required=false) MultipartFile file, String attachmentNum, HttpServletRequest request, HttpServletResponse response)
/*    */     throws JSONException
/*    */   {
/* 40 */     Site site = ContextTools.getSite(request);
/* 41 */     String fileUrl = this.fileRepository.uploadFile(file, site.getUploadPath());
/* 42 */     ResponseUtils.renderText(response, fileUrl);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/o_upload_img.do"})
/*    */   public void uploadImg(@RequestParam(value="imgFile", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response)
/*    */     throws JSONException
/*    */   {
/* 50 */     Site site = ContextTools.getSite(request);
/* 51 */     String fileUrl = this.fileRepository.uploadFile(file, site.getUploadPath());
/* 52 */     ResponseUtils.renderText(response, fileUrl);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.UploadFileAct
 * JD-Core Version:    0.6.1
 */