/*    */ package com.portal.govcenter.action.tag;
/*    */ 
/*    */ import com.portal.govcenter.service.MailboxService;
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
/*    */ public class MailboxTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_TITLE = "title";
/*    */   public static final String PARAM_TYPE_ID = "tId";
/*    */ 
/*    */   @Autowired
/*    */   private MailboxService mailboxService;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 32 */     Site site = ViewTools.getSite(env);
/* 33 */     String title = TagModelTools.getString("title", params);
/* 34 */     Integer tId = TagModelTools.getInt("tId", params);
/* 35 */     Page p = this.mailboxService
/* 36 */       .getPageByTag(title, site.getId(), null, tId, 
/* 37 */       ViewTools.getPageNo(env), ViewTools.getCount(params));
/* 38 */     env.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(p));
/* 39 */     if (body != null)
/* 40 */       body.render(env.getOut());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.action.tag.MailboxTagModel
 * JD-Core Version:    0.6.1
 */