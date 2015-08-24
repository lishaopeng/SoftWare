/*    */ package com.portal.extrafunc.action.tag;
/*    */ 
/*    */ import com.portal.extrafunc.action.cache.ForumCache;
/*    */ import com.portal.extrafunc.entity.Forum;
/*    */ import com.portal.sysmgr.utils.TagModelTools;
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
/*    */ public class ForumCountTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_FORUM_ID = "id";
/*    */ 
/*    */   @Autowired
/*    */   private ForumCache forumCache;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 34 */     Integer id = TagModelTools.getInt("id", params);
/* 35 */     Forum forum = this.forumCache.getForum(id);
/* 36 */     env.setVariable("bean", ObjectWrapper.DEFAULT_WRAPPER.wrap(forum));
/* 37 */     body.render(env.getOut());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.tag.ForumCountTagModel
 * JD-Core Version:    0.6.1
 */