/*    */ package com.portal.extrafunc.action.tag;
/*    */ 
/*    */ import com.portal.extrafunc.action.tag.base.BaseCommentTagModel;
/*    */ import com.portal.extrafunc.service.CommentService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.ViewTools;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.Map;
/*    */ import org.springframework.data.domain.Page;
/*    */ 
/*    */ public class CommentTagModel extends BaseCommentTagModel
/*    */ {
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 32 */     Site site = ViewTools.getSite(env);
/* 33 */     boolean isPage = getIsPage(params);
/* 34 */     Page page = null;
/* 35 */     if (isPage)
/* 36 */       page = this.commentService.getPageForTag(getSiteId(params), 
/* 37 */         getDocId(params), getParentId(params), Boolean.valueOf(true), null, 
/* 38 */         getOrderBy(params), ViewTools.getPageNo(env), 
/* 39 */         ViewTools.getCount(params));
/*    */     else {
/* 41 */       page = this.commentService.getPageForTag(getSiteId(params), 
/* 42 */         getDocId(params), getParentId(params), Boolean.valueOf(true), null, 
/* 43 */         getOrderBy(params), 1, ViewTools.getCount(params));
/*    */     }
/* 45 */     env.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
/* 46 */     body.render(env.getOut());
/* 47 */     if (isPage)
/* 48 */       ViewTools.includePagination(site, params, env);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.tag.CommentTagModel
 * JD-Core Version:    0.6.1
 */