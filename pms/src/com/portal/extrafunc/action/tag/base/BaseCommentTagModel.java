/*    */ package com.portal.extrafunc.action.tag.base;
/*    */ 
/*    */ import com.portal.extrafunc.service.CommentService;
/*    */ import com.portal.sysmgr.utils.TagModelTools;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public abstract class BaseCommentTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_SITE_ID = "siteId";
/*    */   public static final String PARAM_DOC_ID = "docId";
/*    */   public static final String PARAM_PARENT_ID = "parentId";
/*    */   public static final String PARAM_ORDER_BY = "orderBy";
/*    */   public static final String PARAM_IS_PAGE = "isPage";
/*    */ 
/*    */   @Autowired
/*    */   protected CommentService commentService;
/*    */ 
/*    */   protected Integer getSiteId(Map<String, TemplateModel> params)
/*    */     throws TemplateException
/*    */   {
/* 34 */     return TagModelTools.getInt("siteId", params);
/*    */   }
/*    */ 
/*    */   protected Integer getDocId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 39 */     return TagModelTools.getInt("docId", params);
/*    */   }
/*    */ 
/*    */   protected Integer getParentId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 44 */     return TagModelTools.getInt("parentId", params);
/*    */   }
/*    */ 
/*    */   protected int getOrderBy(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 49 */     Integer orderBy = TagModelTools.getInt("orderBy", params);
/* 50 */     if (orderBy != null) {
/* 51 */       return orderBy.intValue();
/*    */     }
/* 53 */     return 0;
/*    */   }
/*    */ 
/*    */   protected boolean getIsPage(Map<String, TemplateModel> params)
/*    */     throws TemplateException
/*    */   {
/* 59 */     Integer isPage = TagModelTools.getInt("isPage", params);
/* 60 */     if ((isPage != null) && (isPage.equals(Integer.valueOf(0)))) {
/* 61 */       return false;
/*    */     }
/* 63 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.tag.base.BaseCommentTagModel
 * JD-Core Version:    0.6.1
 */