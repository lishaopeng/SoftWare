/*    */ package com.portal.doccenter.action.tag;
/*    */ 
/*    */ import com.portal.doccenter.service.ArticleService;
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
/*    */ 
/*    */ public class SignCountTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_DEPART_ID = "departId";
/*    */ 
/*    */   @Autowired
/*    */   private ArticleService articleService;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 34 */     Site site = ViewTools.getSite(env);
/* 35 */     Integer departId = TagModelTools.getInt("departId", params);
/* 36 */     Long count = 
/* 37 */       Long.valueOf(this.articleService
/* 37 */       .getCountByDepartSign(site.getId(), departId));
/* 38 */     env.setVariable("count", ObjectWrapper.DEFAULT_WRAPPER.wrap(count));
/* 39 */     if (body != null)
/* 40 */       body.render(env.getOut());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.tag.SignCountTagModel
 * JD-Core Version:    0.6.1
 */