/*    */ package com.portal.sysmgr.action.tag;
/*    */ 
/*    */ import com.portal.sysmgr.utils.TagModelTools;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.Map;
/*    */ import org.apache.shiro.SecurityUtils;
/*    */ import org.apache.shiro.subject.Subject;
/*    */ 
/*    */ public class PermTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_URL = "perm";
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 30 */     String perm = TagModelTools.getString("perm", params);
/* 31 */     Subject subject = SecurityUtils.getSubject();
/* 32 */     if (subject.isPermitted(perm))
/* 33 */       body.render(env.getOut());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.tag.PermTagModel
 * JD-Core Version:    0.6.1
 */