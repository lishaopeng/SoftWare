/*    */ package com.portal.doccenter.service.impl;
/*    */ 
/*    */ import com.portal.doccenter.dao.FlowStepDao;
/*    */ import com.portal.doccenter.entity.FlowStep;
/*    */ import com.portal.doccenter.entity.WorkFlow;
/*    */ import com.portal.doccenter.service.FlowStepService;
/*    */ import com.portal.usermgr.service.RoleService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class FlowStepServiceImpl
/*    */   implements FlowStepService
/*    */ {
/*    */   private FlowStepDao dao;
/*    */   private RoleService roleService;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<FlowStep> getPage(int pageNo, int pageSize)
/*    */   {
/* 19 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public FlowStep findById(Integer id) {
/* 24 */     FlowStep entity = (FlowStep)this.dao.findById(id);
/* 25 */     return entity;
/*    */   }
/*    */ 
/*    */   public void save(WorkFlow flow, Integer[] step, Integer[] roleIds) {
/* 29 */     for (int i = 0; i < step.length; i++) {
/* 30 */       FlowStep fl = new FlowStep();
/* 31 */       fl.setFlow(flow);
/* 32 */       fl.setRole(this.roleService.findById(roleIds[i]));
/* 33 */       fl.setStep(Integer.valueOf(i + 1));
/* 34 */       this.dao.save(fl);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void update(WorkFlow flow, Integer[] step, Integer[] roleIds) {
/* 39 */     for (FlowStep fl : flow.getFlowSteps()) {
/* 40 */       deleteById(fl.getId());
/*    */     }
/* 42 */     for (int i = 0; i < step.length; i++) {
/* 43 */       FlowStep fl = new FlowStep();
/* 44 */       fl.setFlow(flow);
/* 45 */       fl.setRole(this.roleService.findById(roleIds[i]));
/* 46 */       fl.setStep(Integer.valueOf(i + 1));
/* 47 */       this.dao.save(fl);
/*    */     }
/*    */   }
/*    */ 
/*    */   public FlowStep deleteById(Integer id) {
/* 52 */     FlowStep bean = (FlowStep)this.dao.deleteById(id);
/* 53 */     return bean;
/*    */   }
/*    */ 
/*    */   public FlowStep[] deleteByIds(Integer[] ids) {
/* 57 */     FlowStep[] beans = new FlowStep[ids.length];
/* 58 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 59 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 61 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(FlowStepDao dao)
/*    */   {
/* 69 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setRoleService(RoleService roleService) {
/* 74 */     this.roleService = roleService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.impl.FlowStepServiceImpl
 * JD-Core Version:    0.6.1
 */