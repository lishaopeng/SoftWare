/*     */ package com.portal.extrafunc.service.impl;
/*     */ 
/*     */ import com.portal.datacenter.docdata.service.SensitivityService;
/*     */ import com.portal.doccenter.entity.Article;
/*     */ import com.portal.extrafunc.dao.CommentDao;
/*     */ import com.portal.extrafunc.entity.Comment;
/*     */ import com.portal.extrafunc.entity.CommentExt;
/*     */ import com.portal.extrafunc.service.CommentExtService;
/*     */ import com.portal.extrafunc.service.CommentService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import java.sql.Timestamp;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CommentServiceImpl
/*     */   implements CommentService
/*     */ {
/*     */   private SensitivityService sensitivityService;
/*     */   private CommentExtService commentExtService;
/*     */   private CommentDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<Comment> getPage(Integer siteId, Integer docId, Integer parentId, Boolean checked, Boolean noparent, int orderBy, String sortname, String sortorder, int pageNo, int pageSize)
/*     */   {
/*  27 */     return this.dao.getPage(siteId, docId, parentId, checked, noparent, orderBy, 
/*  28 */       sortname, sortorder, false, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<Comment> getPageForTag(Integer siteId, Integer docId, Integer parentId, Boolean checked, Boolean noparent, int orderBy, int pageNo, int pageSize)
/*     */   {
/*  35 */     return this.dao.getPage(siteId, docId, parentId, checked, noparent, orderBy, 
/*  36 */       null, null, true, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Integer getAllCommentCount(Integer siteId) {
/*  41 */     return this.dao.getAllCommentCount(siteId);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Comment findById(Integer id) {
/*  46 */     Comment entity = (Comment)this.dao.findById(id);
/*  47 */     return entity;
/*     */   }
/*     */ 
/*     */   public Comment comment(String content, String ip, Integer parentId, Article doc, User user, Site site)
/*     */   {
/*  52 */     Comment comment = new Comment();
/*  53 */     comment.setDoc(doc);
/*  54 */     comment.setSite(site);
/*  55 */     comment.setUser(user);
/*  56 */     if (parentId != null) {
/*  57 */       Comment parent = findById(parentId);
/*  58 */       parent.setLastTime(new Timestamp(System.currentTimeMillis()));
/*  59 */       comment.setParent(parent);
/*     */     }
/*  61 */     comment.init();
/*  62 */     this.dao.save(comment);
/*  63 */     content = this.sensitivityService.replaceSensitivity(content);
/*  64 */     this.commentExtService.save(ip, content, comment);
/*  65 */     return comment;
/*     */   }
/*     */ 
/*     */   public Comment update(Comment bean, CommentExt ext) {
/*  69 */     update(bean);
/*  70 */     this.commentExtService.update(ext);
/*  71 */     return bean;
/*     */   }
/*     */ 
/*     */   public Comment update(Comment bean) {
/*  75 */     bean = (Comment)this.dao.update(bean);
/*  76 */     return bean;
/*     */   }
/*     */ 
/*     */   public int deleteByDocId(Integer docId) {
/*  80 */     this.commentExtService.deleteByDocId(docId);
/*  81 */     return this.dao.deleteByDocId(docId);
/*     */   }
/*     */ 
/*     */   public int deleteByTreeNumber(String treeNumber) {
/*  85 */     this.commentExtService.deleteByTreeNumber(treeNumber);
/*  86 */     return this.dao.deleteByTreeNumber(treeNumber);
/*     */   }
/*     */ 
/*     */   public int deleteByUserId(Integer userId) {
/*  90 */     this.commentExtService.deleteByDocUserId(userId);
/*  91 */     this.dao.deleteByDocUserId(userId);
/*  92 */     this.commentExtService.deleteByUserIdAndParent(userId);
/*  93 */     this.dao.deleteByUserIdAndParent(userId);
/*  94 */     this.commentExtService.deleteByUserId(userId);
/*  95 */     return this.dao.deleteByUserId(userId);
/*     */   }
/*     */ 
/*     */   public int deleteBySiteId(Integer siteId) {
/*  99 */     this.commentExtService.deleteBySiteId(siteId);
/* 100 */     return this.dao.deleteBySiteId(siteId);
/*     */   }
/*     */ 
/*     */   public Comment checkById(Integer id) {
/* 104 */     Comment bean = findById(id);
/* 105 */     if (bean.getChecked().booleanValue())
/* 106 */       bean.setChecked(Boolean.valueOf(false));
/*     */     else {
/* 108 */       bean.setChecked(Boolean.valueOf(true));
/*     */     }
/* 110 */     return bean;
/*     */   }
/*     */ 
/*     */   public Comment[] checkByIds(Integer[] ids) {
/* 114 */     Comment[] beans = new Comment[ids.length];
/* 115 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 116 */       beans[i] = checkById(ids[i]);
/*     */     }
/* 118 */     return beans;
/*     */   }
/*     */ 
/*     */   public Comment deleteById(Integer id) {
/* 122 */     this.commentExtService.deleteByParentId(id);
/* 123 */     this.dao.deleteByParentId(id);
/* 124 */     Comment bean = (Comment)this.dao.deleteById(id);
/* 125 */     return bean;
/*     */   }
/*     */ 
/*     */   public Comment[] deleteByIds(Integer[] ids) {
/* 129 */     Comment[] beans = new Comment[ids.length];
/* 130 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 131 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 133 */     return beans;
/*     */   }
/*     */ 
/*     */   public void ups(Integer id) {
/* 137 */     Comment comment = findById(id);
/* 138 */     comment.setUps(Integer.valueOf(comment.getUps().intValue() + 1));
/*     */   }
/*     */ 
/*     */   public void ups(Integer id, Integer ups) {
/* 142 */     Comment comment = findById(id);
/* 143 */     comment.setUps(ups);
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setSensitivityService(SensitivityService sensitivityService)
/*     */   {
/* 152 */     this.sensitivityService = sensitivityService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCommentExtService(CommentExtService commentExtService) {
/* 157 */     this.commentExtService = commentExtService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(CommentDao dao) {
/* 162 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.CommentServiceImpl
 * JD-Core Version:    0.6.1
 */