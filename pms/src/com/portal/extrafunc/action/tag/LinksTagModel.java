/*    */ package com.portal.extrafunc.action.tag;
/*    */ 
/*    */ import com.portal.extrafunc.service.LinksService;
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
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class LinksTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_TYPE_ID = "tId";
/*    */   public static final String PARAM_COUNT = "count";
/*    */ 
/*    */   @Autowired
/*    */   private LinksService service;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 39 */     Site site = ViewTools.getSite(env);
/* 40 */     Integer tId = TagModelTools.getInt("tId", params);
/* 41 */     Integer count = TagModelTools.getInt("count", params);
/* 42 */     List list = this.service.getListByTag(site.getId(), tId, count);
/* 43 */     env.setVariable("list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 44 */     body.render(env.getOut());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.tag.LinksTagModel
 * JD-Core Version:    0.6.1
 */