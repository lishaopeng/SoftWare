/*    */ package com.portal.doccenter.service.impl;
/*    */ 
/*    */ import com.portal.doccenter.dao.ArticleSignDao;
/*    */ import com.portal.doccenter.entity.Article;
/*    */ import com.portal.doccenter.entity.ArticleSign;
/*    */ import com.portal.doccenter.service.ArticleService;
/*    */ import com.portal.doccenter.service.ArticleSignService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.usermgr.entity.Admin;
/*    */ import com.portal.usermgr.entity.Depart;
/*    */ import com.portal.usermgr.entity.User;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ArticleSignServiceImpl
/*    */   implements ArticleSignService
/*    */ {
/*    */   private ArticleSignDao dao;
/*    */   private ArticleService articleService;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<ArticleSign> getPage(int pageNo, int pageSize)
/*    */   {
/* 22 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public ArticleSign findById(Integer id) {
/* 27 */     ArticleSign entity = (ArticleSign)this.dao.findById(id);
/* 28 */     return entity;
/*    */   }
/*    */ 
/*    */   public ArticleSign save(Integer articleId, User user) {
/* 32 */     ArticleSign sign = new ArticleSign();
/* 33 */     Article a = this.articleService.findById(articleId);
/* 34 */     if (a != null) {
/* 35 */       Admin admin = user.getAdmin();
/* 36 */       if ((admin != null) && 
/* 37 */         (!a.getSign(user))) {
/* 38 */         Depart d = admin.getDepart(a.getSite().getId());
/* 39 */         sign.setAdmin(admin);
/* 40 */         sign.setArticle(a);
/* 41 */         sign.setDepart(d);
/* 42 */         sign.init();
/* 43 */         this.dao.save(sign);
/* 44 */         a.addToSigns(sign);
/* 45 */         return sign;
/*    */       }
/*    */     }
/*    */ 
/* 49 */     return null;
/*    */   }
/*    */ 
/*    */   public ArticleSign update(ArticleSign bean) {
/* 53 */     bean = (ArticleSign)this.dao.update(bean);
/* 54 */     return bean;
/*    */   }
/*    */ 
/*    */   public ArticleSign deleteById(Integer id) {
/* 58 */     ArticleSign bean = (ArticleSign)this.dao.deleteById(id);
/* 59 */     return bean;
/*    */   }
/*    */ 
/*    */   public ArticleSign[] deleteByIds(Integer[] ids) {
/* 63 */     ArticleSign[] beans = new ArticleSign[ids.length];
/* 64 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 65 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 67 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ArticleSignDao dao)
/*    */   {
/* 75 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setArticleService(ArticleService articleService) {
/* 80 */     this.articleService = articleService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.impl.ArticleSignServiceImpl
 * JD-Core Version:    0.6.1
 */