/*    */ package com.portal.extrafunc.action.tag;
/*    */ 
/*    */ import com.portal.extrafunc.service.ThemeService;
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
/*    */ public class ThemeTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_FORUM_ID = "fId";
/*    */   public static final String PARAM_STATUS = "status";
/*    */   public static final String PARAM_CREATE_ID = "cId";
/*    */   public static final String PARAM_REPLY_ID = "rId";
/*    */   public static final String PARAM_ORDER_BY = "orderBy";
/*    */ 
/*    */   @Autowired
/*    */   private ThemeService service;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 40 */     Integer fId = TagModelTools.getInt("fId", params);
/* 41 */     Integer status = TagModelTools.getInt("status", params);
/* 42 */     Integer cId = TagModelTools.getInt("cId", params);
/* 43 */     Integer rId = TagModelTools.getInt("rId", params);
/* 44 */     Integer orderBy = TagModelTools.getInt("orderBy", params);
/* 45 */     if (orderBy == null) {
/* 46 */       orderBy = Integer.valueOf(0);
/*    */     }
/* 48 */     Page page = this.service.getThemePageForTag(fId, status, cId, rId, 
/* 49 */       orderBy.intValue(), ViewTools.getPageNo(env), ViewTools.getCount(params));
/* 50 */     env.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
/* 51 */     body.render(env.getOut());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.tag.ThemeTagModel
 * JD-Core Version:    0.6.1
 */