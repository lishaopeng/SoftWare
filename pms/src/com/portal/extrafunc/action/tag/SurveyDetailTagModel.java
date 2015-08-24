/*    */ package com.portal.extrafunc.action.tag;
/*    */ 
/*    */ import com.portal.extrafunc.service.SurveyDetailService;
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
/*    */ public class SurveyDetailTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_SURVEY_ID = "sId";
/*    */ 
/*    */   @Autowired
/*    */   private SurveyDetailService service;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 36 */     Integer sId = TagModelTools.getInt("sId", params);
/* 37 */     Page page = this.service.getPage(sId, ViewTools.getPageNo(env), 
/* 38 */       ViewTools.getCount(params));
/* 39 */     env.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
/* 40 */     body.render(env.getOut());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.tag.SurveyDetailTagModel
 * JD-Core Version:    0.6.1
 */