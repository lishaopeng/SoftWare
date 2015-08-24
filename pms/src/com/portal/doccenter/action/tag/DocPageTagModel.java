/*    */ package com.portal.doccenter.action.tag;
/*    */ 
/*    */ import com.portal.doccenter.action.tag.base.BaseDocTagModel;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.TagModelTools;
/*    */ import com.portal.sysmgr.utils.ViewTools;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.data.domain.Page;
/*    */ 
/*    */ public class DocPageTagModel extends BaseDocTagModel
/*    */ {
/*    */   public static final String TPL_NAME = "tplName";
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 37 */     Site site = ViewTools.getSite(env);
/* 38 */     String tplName = TagModelTools.getString("tplName", params);
/* 39 */     Page page = (Page)getData(params, env);
/* 40 */     env.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
/* 41 */     env.setVariable("list", 
/* 42 */       ObjectWrapper.DEFAULT_WRAPPER.wrap(page.getContent()));
/* 43 */     env.setVariable("isPage", ObjectWrapper.DEFAULT_WRAPPER.wrap(Boolean.valueOf(true)));
/* 44 */     if (!StringUtils.isBlank(tplName)) {
/* 45 */       ViewTools.includeTpl(tplName, site, env);
/*    */     }
/* 47 */     else if (body != null)
/* 48 */       body.render(env.getOut());
/*    */   }
/*    */ 
/*    */   protected boolean isPage()
/*    */   {
/* 55 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.tag.DocPageTagModel
 * JD-Core Version:    0.6.1
 */