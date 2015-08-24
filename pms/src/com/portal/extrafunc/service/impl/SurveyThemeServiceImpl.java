/*     */ package com.portal.extrafunc.service.impl;
/*     */ 
/*     */ /*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;

/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;

import com.portal.extrafunc.dao.SurveyThemeDao;
/*     */ import com.portal.extrafunc.entity.SurveyItem;
/*     */ import com.portal.extrafunc.entity.SurveyTheme;
/*     */ import com.portal.extrafunc.service.QuestionDetailService;
/*     */ import com.portal.extrafunc.service.QuestionnaireService;
/*     */ import com.portal.extrafunc.service.SurveyDetailService;
/*     */ import com.portal.extrafunc.service.SurveyThemeService;
/*     */ import com.portal.usermgr.entity.User;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class SurveyThemeServiceImpl
/*     */   implements SurveyThemeService
/*     */ {
/*     */   private SurveyThemeDao dao;
/*     */   private QuestionnaireService naireService;
/*     */   private QuestionDetailService questionDetailService;
/*     */   private SurveyDetailService surveyDetailService;
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Page<SurveyTheme> getPage(Integer naireId, int pageNo, int pageSize)
/*     */   {
/*  27 */     return this.dao.getPage(naireId, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public List<SurveyTheme> getList(Integer naireId) {
/*  32 */     return this.dao.getList(naireId);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public SurveyTheme findById(Integer id) {
/*  37 */     SurveyTheme entity = this.dao.findById(id);
/*  38 */     return entity;
/*     */   }
/*     */ 
/*     */   @Override
public SurveyTheme save(SurveyTheme bean, Integer naireId, Integer showType1, Integer showType2, String[] names, Integer[] votes, Integer[] prioritys)
/*     */   {
/*  44 */     bean.setNaire(this.naireService.findById(naireId));
/*  45 */     if (bean.getSurveyType().equals(SurveyTheme.NORMAL)) {
/*  46 */       bean.setShowType(showType1);
/*  47 */       if ((names != null) && (names.length > 0))
/*  48 */         for (int i = 0; i < names.length; i++)
/*  49 */           bean.addToItems(names[i], votes[i], prioritys[i]);
/*     */     }
/*     */     else
/*     */     {
/*  53 */       bean.setShowType(showType2);
/*     */     }
/*  55 */     bean.init();
/*  56 */     this.dao.save(bean);
/*  57 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public SurveyTheme update(SurveyTheme bean, Integer showType1, Integer showType2, String[] names, Integer[] votes, Integer[] prioritys)
/*     */   {
/*  63 */     bean = this.dao.update(bean);
/*  64 */     if (bean.getItems() != null) {
/*  65 */       bean.getItems().clear();
/*  66 */       if (bean.getSurveyType().equals(SurveyTheme.NORMAL)) {
/*  67 */         bean.setShowType(showType1);
/*  68 */         bean.setMaxlength(null);
/*  69 */         if ((names != null) && (names.length > 0)) {
/*  70 */           for (int i = 0; i < names.length; i++) {
/*  71 */             bean.addToItems(names[i], votes[i], prioritys[i]);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  76 */     if (bean.getSurveyType().equals(SurveyTheme.WRITED)) {
/*  77 */       bean.setShowType(showType2);
/*  78 */       bean.setTotalCount(null);
/*     */     }
/*  80 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public void voteSurvey(Integer qId, Map<String, String> m, Map<String, String[]> ml, String ip, User user)
/*     */   {
/*  85 */     this.questionDetailService.save(this.naireService.findById(qId), user, ip);
/*  86 */     if (m != null) {
/*  87 */       Set key = m.keySet();
/*  88 */       for (Iterator it = key.iterator(); it.hasNext(); ) {
/*  89 */         String s = (String)it.next();
/*  90 */         SurveyTheme st = findById(Integer.valueOf(Integer.parseInt(s)));
/*  91 */         if (st.getSurveyType().equals(SurveyTheme.NORMAL)) {
/*  92 */           if (st.getItems() != null) {
/*  93 */             for (SurveyItem si : st.getItems()) {
/*  94 */               if (si.getName().equals(m.get(s)))
/*  95 */                 si.setVotes(Integer.valueOf(si.getVotes().intValue() + 1));
/*     */             }
/*     */           }
/*     */         }
/*     */         else {
/* 100 */           this.surveyDetailService.save(m.get(s), st, user);
/*     */         }
/*     */       }
/*     */     }
/* 104 */     if (ml != null)
/* 105 */       saveMapList(ml, ip, user);
/*     */   }
/*     */ 
/*     */   private void saveMapList(Map<String, String[]> ml, String ip, User user)
/*     */   {
/* 110 */     Set key = ml.keySet();
/*     */     Iterator localIterator1;
/* 111 */     label176: for (Iterator it = key.iterator(); it.hasNext(); 
/* 116 */       localIterator1.hasNext())
/*     */     {
/* 112 */       String s = (String)it.next();
/* 113 */       SurveyTheme st = findById(Integer.valueOf(Integer.parseInt(s)));
/* 114 */       if ((!st.getSurveyType().equals(SurveyTheme.NORMAL)) || 
/* 115 */         (st.getItems() == null)) break label176;
			/* 116 */localIterator1 = st.getItems().iterator();
			SurveyItem si = (SurveyItem) localIterator1.next();
/* 117 */       for (String ss : ml.get(s))
/* 118 */         if (si.getName().equals(ss))
/* 119 */           si.setVotes(Integer.valueOf(si.getVotes().intValue() + 1));
/*     */     }
/*     */   }
/*     */ 
/*     */   @Override
public SurveyTheme deleteById(Integer id)
/*     */   {
/* 129 */     SurveyTheme bean = this.dao.deleteById(id);
/* 130 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public SurveyTheme[] deleteByIds(Integer[] ids) {
/* 134 */     SurveyTheme[] beans = new SurveyTheme[ids.length];
/* 135 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 136 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 138 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(SurveyThemeDao dao)
/*     */   {
/* 148 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setNaireService(QuestionnaireService naireService) {
/* 153 */     this.naireService = naireService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setQuestionDetailService(QuestionDetailService questionDetailService)
/*     */   {
/* 159 */     this.questionDetailService = questionDetailService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setSurveyDetailService(SurveyDetailService surveyDetailService) {
/* 164 */     this.surveyDetailService = surveyDetailService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.SurveyThemeServiceImpl
 * JD-Core Version:    0.6.1
 */