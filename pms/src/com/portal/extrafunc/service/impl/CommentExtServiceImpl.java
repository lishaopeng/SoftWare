/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.CommentExtDao;
/*    */ import com.portal.extrafunc.entity.Comment;
/*    */ import com.portal.extrafunc.entity.CommentExt;
/*    */ import com.portal.extrafunc.service.CommentExtService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CommentExtServiceImpl
/*    */   implements CommentExtService
/*    */ {
/*    */   private CommentExtDao dao;
/*    */ 
/*    */   public CommentExt save(String ip, String content, Comment comment)
/*    */   {
/* 16 */     CommentExt ext = new CommentExt();
/* 17 */     ext.setContent(content);
/* 18 */     ext.setIp(ip);
/* 19 */     ext.setComment(comment);
/* 20 */     comment.setCommentExt(ext);
/* 21 */     this.dao.save(ext);
/* 22 */     return ext;
/*    */   }
/*    */ 
/*    */   public CommentExt update(CommentExt bean) {
/* 26 */     bean = (CommentExt)this.dao.update(bean);
/* 27 */     return bean;
/*    */   }
/*    */ 
/*    */   public int deleteByParentId(Integer parentId) {
/* 31 */     return this.dao.deleteByParentId(parentId);
/*    */   }
/*    */ 
/*    */   public int deleteByDocId(Integer docId) {
/* 35 */     return this.dao.deleteByDocId(docId);
/*    */   }
/*    */ 
/*    */   public int deleteByTreeNumber(String treeNumber) {
/* 39 */     return this.dao.deleteByTreeNumber(treeNumber);
/*    */   }
/*    */ 
/*    */   public int deleteByDocUserId(Integer userId) {
/* 43 */     return this.dao.deleteByDocUserId(userId);
/*    */   }
/*    */ 
/*    */   public int deleteByUserId(Integer userId) {
/* 47 */     return this.dao.deleteByUserId(userId);
/*    */   }
/*    */ 
/*    */   public int deleteByUserIdAndParent(Integer userId) {
/* 51 */     return this.dao.deleteByUserIdAndParent(userId);
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 55 */     return this.dao.deleteBySiteId(siteId);
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CommentExtDao dao)
/*    */   {
/* 62 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.CommentExtServiceImpl
 * JD-Core Version:    0.6.1
 */