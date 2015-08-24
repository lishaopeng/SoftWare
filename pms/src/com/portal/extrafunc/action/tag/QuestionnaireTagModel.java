/*    */ package com.portal.extrafunc.action.tag;
/*    */ 
/*    */ import com.portal.extrafunc.service.QuestionnaireService;
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
/*    */ public class QuestionnaireTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_QUESTION_ID = "qId";
/*    */   public static final String PARAM_CUSTOM = "custom";
/*    */ 
/*    */   @Autowired
/*    */   private QuestionnaireService service;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 39 */     Site site = ViewTools.getSite(env);
/* 40 */     Boolean custom = TagModelTools.getBool("custom", params);
/* 41 */     Page page = this.service.getPage(site.getId(), true, 
/* 42 */       ViewTools.getPageNo(env), ViewTools.getCount(params));
/* 43 */     env.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
/* 44 */     if ((custom != null) && (custom.booleanValue()))
/* 45 */       body.render(env.getOut());
/*    */     else
/* 47 */       ViewTools.includeTpl("question", site, env);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.tag.QuestionnaireTagModel
 * JD-Core Version:    0.6.1
 */