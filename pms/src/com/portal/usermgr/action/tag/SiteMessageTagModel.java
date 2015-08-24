/*    */ package com.portal.usermgr.action.tag;
/*    */ 
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.TagModelTools;
/*    */ import com.portal.sysmgr.utils.ViewTools;
/*    */ import com.portal.usermgr.service.SiteMessageService;
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
/*    */ public class SiteMessageTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_SEND_ID = "sendId";
/*    */   public static final String PARAM_STATUS = "status";
/*    */ 
/*    */   @Autowired
/*    */   private SiteMessageService messageService;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 32 */     Site site = ViewTools.getSite(env);
/* 33 */     Integer sendId = TagModelTools.getInt("sendId", params);
/* 34 */     Integer status = TagModelTools.getInt("status", params);
/* 35 */     Page page = this.messageService.getPageByTag(sendId, status, 
/* 36 */       ViewTools.getPageNo(env), ViewTools.getCount(params));
/* 37 */     env.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
/* 38 */     body.render(env.getOut());
/* 39 */     ViewTools.includePagination(site, params, env);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.action.tag.SiteMessageTagModel
 * JD-Core Version:    0.6.1
 */