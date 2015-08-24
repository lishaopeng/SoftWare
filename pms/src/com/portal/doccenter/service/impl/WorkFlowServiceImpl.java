/*    */ package com.portal.doccenter.service.impl;
/*    */ 
/*    */ import com.portal.doccenter.dao.WorkFlowDao;
/*    */ import com.portal.doccenter.entity.WorkFlow;
/*    */ import com.portal.doccenter.service.FlowStepService;
/*    */ import com.portal.doccenter.service.WorkFlowService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class WorkFlowServiceImpl
/*    */   implements WorkFlowService
/*    */ {
/*    */   private WorkFlowDao dao;
/*    */   private FlowStepService flowStepService;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<WorkFlow> getPage(Integer siteId, String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 22 */     return this.dao.getPage(siteId, sortname, sortorder, pageNo, 
/* 23 */       pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<WorkFlow> findByList(Integer siteId) {
/* 28 */     return this.dao.findByList(siteId);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public WorkFlow findById(Integer id) {
/* 33 */     WorkFlow entity = (WorkFlow)this.dao.findById(id);
/* 34 */     return entity;
/*    */   }
/*    */ 
/*    */   public WorkFlow save(WorkFlow bean, Site site, Integer[] step, Integer[] roleIds)
/*    */   {
/* 39 */     bean.init();
/* 40 */     bean.setSite(site);
/* 41 */     bean.setStepCount(Integer.valueOf(step.length));
/* 42 */     this.dao.save(bean);
/* 43 */     this.flowStepService.save(bean, step, roleIds);
/* 44 */     return bean;
/*    */   }
/*    */ 
/*    */   public WorkFlow update(WorkFlow bean, Integer[] step, Integer[] roleIds) {
/* 48 */     bean.setStepCount(Integer.valueOf(step.length));
/* 49 */     bean = (WorkFlow)this.dao.update(bean);
/* 50 */     this.flowStepService.update(bean, step, roleIds);
/* 51 */     return bean;
/*    */   }
/*    */ 
/*    */   public WorkFlow deleteById(Integer id) {
/* 55 */     WorkFlow bean = (WorkFlow)this.dao.deleteById(id);
/* 56 */     return bean;
/*    */   }
/*    */ 
/*    */   public WorkFlow[] deleteByIds(Integer[] ids) {
/* 60 */     WorkFlow[] beans = new WorkFlow[ids.length];
/* 61 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 62 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 64 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(WorkFlowDao dao)
/*    */   {
/* 72 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setFlowStepService(FlowStepService flowStepService) {
/* 77 */     this.flowStepService = flowStepService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.impl.WorkFlowServiceImpl
 * JD-Core Version:    0.6.1
 */