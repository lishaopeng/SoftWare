/*    */ package com.portal.doccenter.service.impl;
/*    */ 
/*    */ import com.portal.doccenter.dao.ChannelTxtDao;
/*    */ import com.portal.doccenter.entity.Channel;
/*    */ import com.portal.doccenter.entity.ChannelTxt;
/*    */ import com.portal.doccenter.service.ChannelTxtService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ChannelTxtServiceImpl
/*    */   implements ChannelTxtService
/*    */ {
/*    */   private ChannelTxtDao dao;
/*    */ 
/*    */   public ChannelTxt save(ChannelTxt txt, Channel channel)
/*    */   {
/* 22 */     if (txt.isAllBlank()) {
/* 23 */       return null;
/*    */     }
/* 25 */     txt.setChannel(channel);
/* 26 */     txt.init();
/* 27 */     this.dao.save(txt);
/* 28 */     return txt;
/*    */   }
/*    */ 
/*    */   public ChannelTxt update(ChannelTxt txt, Channel channel)
/*    */   {
/* 33 */     ChannelTxt entity = (ChannelTxt)this.dao.findById(channel.getId());
/* 34 */     if (entity == null) {
/* 35 */       entity = save(txt, channel);
/* 36 */       return entity;
/*    */     }
/* 38 */     if (txt.isAllBlank()) {
/* 39 */       channel.setTxt(null);
/* 40 */       return null;
/*    */     }
/* 42 */     entity = (ChannelTxt)this.dao.update(txt);
/* 43 */     entity.blankToNull();
/* 44 */     return entity;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ChannelTxtDao dao)
/*    */   {
/* 53 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.impl.ChannelTxtServiceImpl
 * JD-Core Version:    0.6.1
 */