/*    */ package com.portal.doccenter.service.impl;
/*    */ 
/*    */ import com.portal.doccenter.dao.ChannelExtDao;
/*    */ import com.portal.doccenter.entity.Channel;
/*    */ import com.portal.doccenter.entity.ChannelExt;
/*    */ import com.portal.doccenter.service.ChannelExtService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ChannelExtServiceImpl
/*    */   implements ChannelExtService
/*    */ {
/*    */   private ChannelExtDao dao;
/*    */ 
/*    */   public ChannelExt save(ChannelExt ext, Channel channel)
/*    */   {
/* 16 */     channel.setExt(ext);
/* 17 */     ext.setChannel(channel);
/* 18 */     ext.init();
/* 19 */     this.dao.save(ext);
/* 20 */     return ext;
/*    */   }
/*    */ 
/*    */   public ChannelExt update(ChannelExt ext) {
/* 24 */     ChannelExt entity = (ChannelExt)this.dao.update(ext);
/* 25 */     entity.blankToNull();
/* 26 */     return entity;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ChannelExtDao dao)
/*    */   {
/* 33 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.impl.ChannelExtServiceImpl
 * JD-Core Version:    0.6.1
 */