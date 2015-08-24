/*    */ package com.portal.datacenter.docdata.action.fnt.tag;
/*    */ 
/*    */ import com.portal.datacenter.docdata.entity.ProgramDownload;
/*    */ import com.portal.datacenter.docdata.service.ProgramDownloadService;
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
/*    */ public class ProgramTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private ProgramDownloadService programDownloadService;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 31 */     ProgramDownload pd = this.programDownloadService.findUnique();
/* 32 */     Integer count = Integer.valueOf(0);
/* 33 */     if (pd != null) {
/* 34 */       count = pd.getCount();
/*    */     }
/* 36 */     env.setVariable("count", ObjectWrapper.DEFAULT_WRAPPER.wrap(count));
/* 37 */     body.render(env.getOut());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.action.fnt.tag.ProgramTagModel
 * JD-Core Version:    0.6.1
 */