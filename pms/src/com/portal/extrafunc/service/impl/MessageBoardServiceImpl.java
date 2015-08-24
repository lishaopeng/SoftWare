/*     */ package com.portal.extrafunc.service.impl;
/*     */ 
/*     */ import com.portal.extrafunc.dao.MessageBoardDao;
/*     */ import com.portal.extrafunc.entity.MessageBoard;
/*     */ import com.portal.extrafunc.entity.MessageBoardExt;
/*     */ import com.portal.extrafunc.service.MessageBoardExtService;
/*     */ import com.portal.extrafunc.service.MessageBoardService;
/*     */ import com.portal.extrafunc.service.MessageTypeService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class MessageBoardServiceImpl
/*     */   implements MessageBoardService
/*     */ {
/*     */   private MessageBoardDao dao;
/*     */   private MessageTypeService typeService;
/*     */   private MessageBoardExtService extService;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<MessageBoard> getPage(String name, Integer siteId, Boolean show, String sortname, String sortorder, int pageNo, int pageSize)
/*     */   {
/*  23 */     return this.dao.getPage(name, siteId, show, sortname, sortorder, pageNo, 
/*  24 */       pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Integer getAllMessageCount(Integer siteId) {
/*  29 */     return this.dao.getAllMessageCount(siteId);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Integer getNoRepMessageCount(Integer siteId) {
/*  34 */     return this.dao.getNoRepMessageCount(siteId);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public MessageBoard findById(Integer id) {
/*  39 */     MessageBoard entity = (MessageBoard)this.dao.findById(id);
/*  40 */     return entity;
/*     */   }
/*     */ 
/*     */   public MessageBoard save(String title, String name, String mobile, String email, String address, String zipcode, Integer typeId, String content, Site site)
/*     */   {
/*  46 */     MessageBoard board = new MessageBoard();
/*  47 */     MessageBoardExt ext = new MessageBoardExt();
/*  48 */     board.setTitle(title);
/*  49 */     board.setName(name);
/*  50 */     board.setMobile(mobile);
/*  51 */     board.setEmail(email);
/*  52 */     board.setAddress(address);
/*  53 */     board.setZipcode(zipcode);
/*  54 */     ext.setContent(content);
/*  55 */     save(board, ext, site, typeId);
/*  56 */     return board;
/*     */   }
/*     */ 
/*     */   public MessageBoard save(MessageBoard bean, MessageBoardExt ext, Site site, Integer typeId)
/*     */   {
/*  61 */     if (typeId != null)
/*  62 */       bean.setType(this.typeService.findById(typeId));
/*     */     else {
/*  64 */       bean.setType(this.typeService.getUniqueType(site.getId()));
/*     */     }
/*  66 */     bean.setSite(site);
/*  67 */     bean.init();
/*  68 */     this.dao.save(bean);
/*  69 */     this.extService.save(bean, ext);
/*  70 */     return bean;
/*     */   }
/*     */ 
/*     */   public MessageBoard update(MessageBoard bean, MessageBoardExt ext, Integer typeId)
/*     */   {
/*  75 */     bean = (MessageBoard)this.dao.update(bean);
/*  76 */     if (typeId != null) {
/*  77 */       bean.setType(this.typeService.findById(typeId));
/*     */     }
/*  79 */     this.extService.update(ext);
/*  80 */     return bean;
/*     */   }
/*     */ 
/*     */   public MessageBoard showBoard(Integer id) {
/*  84 */     MessageBoard board = findById(id);
/*  85 */     if (board.getShow().booleanValue())
/*  86 */       board.setShow(Boolean.valueOf(false));
/*     */     else {
/*  88 */       board.setShow(Boolean.valueOf(true));
/*     */     }
/*  90 */     return board;
/*     */   }
/*     */ 
/*     */   public MessageBoard deleteById(Integer id) {
/*  94 */     MessageBoard bean = (MessageBoard)this.dao.deleteById(id);
/*  95 */     return bean;
/*     */   }
/*     */ 
/*     */   public MessageBoard[] deleteByIds(Integer[] ids) {
/*  99 */     MessageBoard[] beans = new MessageBoard[ids.length];
/* 100 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 101 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 103 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(MessageBoardDao dao)
/*     */   {
/* 112 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setTypeService(MessageTypeService typeService) {
/* 117 */     this.typeService = typeService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setExtService(MessageBoardExtService extService) {
/* 122 */     this.extService = extService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.MessageBoardServiceImpl
 * JD-Core Version:    0.6.1
 */