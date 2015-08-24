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
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class ArticleCountTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_CID = "cid";
/*    */   public static final String PARAM_DID = "did";
/*    */   public static final String PARAM_START = "start";
/*    */   public static final String PARAM_END = "end";
/*    */ 
/*    */   @Autowired
/*    */   private ArticleService service;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 42 */     Site site = ViewTools.getSite(env);
/* 43 */     Integer cid = TagModelTools.getInt("cid", params);
/* 44 */     Integer did = TagModelTools.getInt("did", params);
/* 45 */     Date start = TagModelTools.getDate("start", params);
/* 46 */     Date end = TagModelTools.getDate("end", params);
/* 47 */     List list = this.service.getCountByDepart(site.getId(), cid, did, 
/* 48 */       start, end);
/* 49 */     env.setVariable("list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 50 */     if (body != null)
/* 51 */       body.render(env.getOut());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.tag.ArticleCountTagModel
 * JD-Core Version:    0.6.1
 */