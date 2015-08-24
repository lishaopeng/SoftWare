/*    */ package com.portal.extrafunc.action.tag;
/*    */ 
/*    */ import com.portal.extrafunc.service.PostsService;
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
/*    */ public class PostsTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_THEME_ID = "tId";
/*    */ 
/*    */   @Autowired
/*    */   private PostsService service;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 36 */     Integer tId = TagModelTools.getInt("tId", params);
/* 37 */     Page page = this.service.getPostsPageForTag(tId, 
/* 38 */       ViewTools.getPageNo(env), ViewTools.getCount(params));
/* 39 */     env.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
/* 40 */     body.render(env.getOut());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.tag.PostsTagModel
 * JD-Core Version:    0.6.1
 */