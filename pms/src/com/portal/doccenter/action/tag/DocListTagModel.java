/*    */ package com.portal.doccenter.action.tag;
/*    */ 
/*    */ import com.portal.doccenter.action.tag.base.BaseDocTagModel;
/*    */ import com.portal.doccenter.entity.Article;
/*    */ import com.portal.doccenter.service.ArticleService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.TagModelTools;
/*    */ import com.portal.sysmgr.utils.ViewTools;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class DocListTagModel extends BaseDocTagModel
/*    */ {
/*    */   public static final String TPL_NAME = "tplName";
/*    */   public static final String PARAM_IDS = "ids";
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 42 */     Site site = ViewTools.getSite(env);
/* 43 */     String tplName = TagModelTools.getString("tplName", params);
/* 44 */     List list = getList(params, env);
/* 45 */     env.setVariable("list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 46 */     env.setVariable("isPage", ObjectWrapper.DEFAULT_WRAPPER.wrap(Boolean.valueOf(false)));
/* 47 */     if (!StringUtils.isBlank(tplName)) {
/* 48 */       ViewTools.includeTpl(tplName, site, env);
/*    */     }
/* 50 */     else if (body != null)
/* 51 */       body.render(env.getOut());
/*    */   }
/*    */ 
/*    */   protected List<Article> getList(Map<String, TemplateModel> params, Environment env)
/*    */     throws TemplateException
/*    */   {
/* 59 */     Integer[] ids = TagModelTools.getIntArray("ids", params);
/* 60 */     if (ids != null) {
/* 61 */       return this.articleService.getListTagByIds(ids, getOrderBy(params));
/*    */     }
/* 63 */     return (List)super.getData(params, env);
/*    */   }
/*    */ 
/*    */   protected boolean isPage()
/*    */   {
/* 69 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.tag.DocListTagModel
 * JD-Core Version:    0.6.1
 */