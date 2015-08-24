/*    */ package com.portal.doccenter.service.impl;
/*    */ 
/*    */ /*    */ import java.util.List;

/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;

import com.portal.doccenter.dao.ModelFieldDao;
/*    */ import com.portal.doccenter.entity.ModelField;
/*    */ import com.portal.doccenter.service.ModelFieldService;
/*    */ import com.portal.doccenter.service.ModelService;
/*    */ 
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ModelFieldServiceImpl
/*    */   implements ModelFieldService
/*    */ {
/*    */   private ModelService modelService;
/*    */   private ModelFieldDao dao;
/*    */ 
/*    */   @Override
@Transactional(readOnly=true)
/*    */   public List<ModelField> getList(Integer modelId, boolean hasDisabled, String sortname, String sortorder)
/*    */   {
/* 20 */     return this.dao.getList(modelId, hasDisabled, sortname, sortorder);
/*    */   }
/*    */ 
/*    */   @Override
@Transactional(readOnly=true)
/*    */   public ModelField findById(Integer id) {
/* 25 */     ModelField entity = this.dao.findById(id);
/* 26 */     return entity;
/*    */   }
/*    */ 
/*    */   @Override
public ModelField save(ModelField bean, Integer modelId) {
/* 30 */     bean.setModel(this.modelService.findById(modelId));
/* 31 */     int maxpri = this.dao.getMaxPriority(modelId);
/* 32 */     bean.setPriority(Integer.valueOf(maxpri + 1));
/* 33 */     return save(bean);
/*    */   }
/*    */ 
/*    */   @Override
public ModelField save(ModelField bean) {
/* 37 */     bean.init();
/* 38 */     this.dao.save(bean);
/* 39 */     return bean;
/*    */   }
/*    */ 
/*    */   @Override
public void saveList(List<ModelField> list) {
/* 43 */     for (ModelField item : list)
/* 44 */       save(item);
/*    */   }
/*    */ 
/*    */   @Override
public void updatePriority(Integer id, Integer priority)
/*    */   {
/* 49 */     ModelField item = findById(id);
/* 50 */     if (priority.intValue() > item.getPriority().intValue()) {
			/* 51 */List<ModelField> list = this.dao.getListByPriority(item
					.getModel()
/* 52 */         .getId(), Integer.valueOf(item.getPriority().intValue() + 1), Integer.valueOf(priority.intValue() + 1), true);
/* 53 */       for (ModelField itemi : list)
/* 54 */         itemi.setPriority(Integer.valueOf(itemi.getPriority().intValue() - 1));
/*    */     }
/*    */     else {
			/* 57 */List<ModelField> list = this.dao.getListByPriority(item
					.getModel()
/* 58 */         .getId(), priority, item.getPriority(), true);
/* 59 */       for (ModelField itemi : list) {
/* 60 */         itemi.setPriority(Integer.valueOf(itemi.getPriority().intValue() + 1));
/*    */       }
/*    */     }
/* 63 */     item.setPriority(priority);
/*    */   }
/*    */ 
/*    */   @Override
public ModelField update(ModelField bean) {
/* 67 */     return this.dao.update(bean);
/*    */   }
/*    */ 
/*    */   @Override
public ModelField deleteById(Integer id) {
/* 71 */     ModelField bean = this.dao.deleteById(id);
/* 72 */     return bean;
/*    */   }
/*    */ 
/*    */   @Override
public ModelField[] deleteByIds(Integer[] ids) {
/* 76 */     ModelField[] beans = new ModelField[ids.length];
/* 77 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 78 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 80 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setModelService(ModelService modelService)
/*    */   {
/* 88 */     this.modelService = modelService;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ModelFieldDao dao) {
/* 93 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.impl.ModelFieldServiceImpl
 * JD-Core Version:    0.6.1
 */