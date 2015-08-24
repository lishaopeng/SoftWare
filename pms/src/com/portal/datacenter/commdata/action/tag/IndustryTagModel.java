/*    */ package com.portal.datacenter.commdata.action.tag;
/*    */ 
/*    */ import com.portal.datacenter.commdata.service.IndustryService;
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
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class IndustryTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_PARENT_ID = "parentId";
/*    */ 
/*    */   @Autowired
/*    */   private IndustryService industryService;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 32 */     Site site = ViewTools.getSite(env);
/* 33 */     Integer parentId = TagModelTools.getInt("parentId", params);
/* 34 */     List industryList = new ArrayList();
/* 35 */     if (parentId != null)
/* 36 */       industryList = this.industryService.getIndustryChild(parentId);
/*    */     else {
/* 38 */       industryList = this.industryService.getIndustryList(null);
/*    */     }
/* 40 */     env.setVariable("list", ObjectWrapper.DEFAULT_WRAPPER.wrap(industryList));
/* 41 */     body.render(env.getOut());
/* 42 */     ViewTools.includePagination(site, params, env);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.action.tag.IndustryTagModel
 * JD-Core Version:    0.6.1
 */