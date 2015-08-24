/*    */ package com.portal.doccenter.service.impl;
/*    */ 
/*    */ import com.portal.doccenter.dao.ModelDao;
/*    */ import com.portal.doccenter.entity.Model;
/*    */ import com.portal.doccenter.service.ModelService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ModelServiceImpl
/*    */   implements ModelService
/*    */ {
/*    */   private ModelDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<Model> getList(boolean containDisabled, String sortname, String sortorder)
/*    */   {
/* 20 */     return this.dao.getList(containDisabled, sortname, sortorder);
/*    */   }
/*    */ 
/*    */   public Model getDefModel() {
/* 24 */     return this.dao.getDefModel();
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Model findById(Integer id) {
/* 29 */     Model entity = (Model)this.dao.findById(id);
/* 30 */     return entity;
/*    */   }
/*    */ 
/*    */   public Model save(Model bean) {
/* 34 */     bean.init();
/* 35 */     this.dao.save(bean);
/* 36 */     return bean;
/*    */   }
/*    */ 
/*    */   public Model update(Model bean) {
/* 40 */     Model entity = (Model)this.dao.update(bean);
/* 41 */     return entity;
/*    */   }
/*    */ 
/*    */   public Model deleteById(Integer id) {
/* 45 */     Model bean = (Model)this.dao.deleteById(id);
/* 46 */     return bean;
/*    */   }
/*    */ 
/*    */   public Model[] deleteByIds(Integer[] ids) {
/* 50 */     Model[] beans = new Model[ids.length];
/* 51 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 52 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 54 */     return beans;
/*    */   }
/*    */ 
/*    */   public List<Model> getModelList(List<Integer> modelIdList) {
/* 58 */     List modelList = new ArrayList();
/* 59 */     if ((modelIdList != null) && (modelIdList.size() > 0)) {
/* 60 */       for (Integer modelId : modelIdList) {
/* 61 */         modelList.add(findById(modelId));
/*    */       }
/*    */     }
/* 64 */     return modelList;
/*    */   }
/*    */ 
/*    */   public Model[] updatePriority(Integer[] ids, Integer[] priority, Boolean[] disabled, Integer defId)
/*    */   {
/* 69 */     int len = ids.length;
/* 70 */     Model[] beans = new Model[len];
/* 71 */     for (int i = 0; i < len; i++) {
/* 72 */       beans[i] = findById(ids[i]);
/* 73 */       beans[i].setPriority(priority[i]);
/* 74 */       beans[i].setDisabled(disabled[i]);
/*    */     }
/* 76 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ModelDao dao)
/*    */   {
/* 83 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.impl.ModelServiceImpl
 * JD-Core Version:    0.6.1
 */