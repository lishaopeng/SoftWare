/*    */ package com.portal.extrafunc.action.tag;
/*    */ 
/*    */ import com.portal.extrafunc.entity.SurveyTheme;
/*    */ import com.portal.extrafunc.service.SurveyThemeService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.TagModelTools;
/*    */ import com.portal.sysmgr.utils.ViewTools;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ 
/*    */ public class SurveyThemeTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_QUESTION_ID = "qId";
/*    */   public static final String PARAM_CUSTOM = "custom";
/*    */ 
/*    */   @Autowired
/*    */   private SurveyThemeService service;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 39 */     Site site = ViewTools.getSite(env);
/* 40 */     Integer qId = TagModelTools.getInt("qId", params);
/* 41 */     Boolean custom = TagModelTools.getBool("custom", params);
/* 42 */     Page page = this.service.getPage(qId, ViewTools.getPageNo(env), 
/* 43 */       ViewTools.getCount(params));
/* 44 */     env.setVariable("NORMAL", ObjectWrapper.DEFAULT_WRAPPER.wrap(SurveyTheme.NORMAL));
/* 45 */     env.setVariable("WRITED", ObjectWrapper.DEFAULT_WRAPPER.wrap(SurveyTheme.WRITED));
/* 46 */     env.setVariable("CHECKED", ObjectWrapper.DEFAULT_WRAPPER.wrap(SurveyTheme.CHECKED));
/* 47 */     env.setVariable("SELECTED", ObjectWrapper.DEFAULT_WRAPPER.wrap(SurveyTheme.SELECTED));
/* 48 */     env.setVariable("TEXT", ObjectWrapper.DEFAULT_WRAPPER.wrap(SurveyTheme.TEXT));
/* 49 */     env.setVariable("ONLINE", ObjectWrapper.DEFAULT_WRAPPER.wrap(SurveyTheme.ONLINE));
/* 50 */     env.setVariable("AREA", ObjectWrapper.DEFAULT_WRAPPER.wrap(SurveyTheme.AREA));
/* 51 */     env.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
/* 52 */     if ((custom != null) && (custom.booleanValue()))
/* 53 */       body.render(env.getOut());
/*    */     else
/* 55 */       ViewTools.includeTpl("surveyTheme", site, env);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.tag.SurveyThemeTagModel
 * JD-Core Version:    0.6.1
 */