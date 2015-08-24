/*    */ package com.portal.doccenter.service.impl;
/*    */ 
/*    */ import com.portal.doccenter.dao.FlowDetailDao;
/*    */ import com.portal.doccenter.entity.Article;
/*    */ import com.portal.doccenter.entity.FlowDetail;
/*    */ import com.portal.doccenter.service.FlowDetailService;
/*    */ import com.portal.usermgr.entity.Role;
/*    */ import com.portal.usermgr.entity.User;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class FlowDetailServiceImpl
/*    */   implements FlowDetailService
/*    */ {
/*    */   private FlowDetailDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<FlowDetail> getPage(int pageNo, int pageSize)
/*    */   {
/* 20 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public FlowDetail getLastFlowDetail(Integer docId) {
/* 25 */     return this.dao.getLastFlowDetail(docId);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public FlowDetail findById(Integer id) {
/* 30 */     FlowDetail entity = (FlowDetail)this.dao.findById(id);
/* 31 */     return entity;
/*    */   }
/*    */ 
/*    */   public FlowDetail saveCheck(Article doc, User user, Role role) {
/* 35 */     FlowDetail bean = new FlowDetail();
/* 36 */     bean.setChecked(Boolean.valueOf(true));
/* 37 */     bean.setDoc(doc);
/* 38 */     bean.setUser(user);
/* 39 */     bean.setRole(role);
/* 40 */     FlowDetail fd = getLastFlowDetail(doc.getId());
/* 41 */     if (fd != null) {
/* 42 */       bean.setPriority(Integer.valueOf(fd.getPriority().intValue() + 1));
/*    */     }
/* 44 */     bean.init();
/* 45 */     this.dao.save(bean);
/* 46 */     return bean;
/*    */   }
/*    */ 
/*    */   public FlowDetail saveReturn(Article doc, User user, Role role, String reason, boolean initial)
/*    */   {
/* 51 */     FlowDetail bean = new FlowDetail();
/* 52 */     bean.setChecked(Boolean.valueOf(false));
/* 53 */     bean.setBackInitial(Boolean.valueOf(initial));
/* 54 */     bean.setReason(reason);
/* 55 */     bean.setDoc(doc);
/* 56 */     bean.setUser(user);
/* 57 */     bean.setRole(role);
/* 58 */     FlowDetail fd = getLastFlowDetail(doc.getId());
/* 59 */     if (fd != null) {
/* 60 */       bean.setPriority(Integer.valueOf(fd.getPriority().intValue() + 1));
/*    */     }
/* 62 */     bean.init();
/* 63 */     this.dao.save(bean);
/* 64 */     return bean;
/*    */   }
/*    */ 
/*    */   public FlowDetail update(FlowDetail bean) {
/* 68 */     bean = (FlowDetail)this.dao.update(bean);
/* 69 */     return bean;
/*    */   }
/*    */ 
/*    */   public FlowDetail deleteById(Integer id) {
/* 73 */     FlowDetail bean = (FlowDetail)this.dao.deleteById(id);
/* 74 */     return bean;
/*    */   }
/*    */ 
/*    */   public FlowDetail[] deleteByIds(Integer[] ids) {
/* 78 */     FlowDetail[] beans = new FlowDetail[ids.length];
/* 79 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 80 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 82 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(FlowDetailDao dao)
/*    */   {
/* 89 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.impl.FlowDetailServiceImpl
 * JD-Core Version:    0.6.1
 */