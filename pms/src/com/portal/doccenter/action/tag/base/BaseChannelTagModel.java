/*    */ package com.portal.doccenter.action.tag.base;
/*    */ 
/*    */ import com.portal.doccenter.service.ChannelService;
/*    */ import com.portal.sysmgr.utils.TagModelTools;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public abstract class BaseChannelTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_PARENT_ID = "pId";
/*    */   public static final String PARAM_SITE_ID = "sId";
/*    */   public static final String PARAM_IS_ALONE = "isAlone";
/*    */ 
/*    */   @Autowired
/*    */   protected ChannelService channelService;
/*    */ 
/*    */   protected Boolean getAlone(Map<String, TemplateModel> params)
/*    */     throws TemplateException
/*    */   {
/* 36 */     Boolean isAlone = TagModelTools.getBool("isAlone", params);
/* 37 */     if (isAlone != null) {
/* 38 */       return isAlone;
/*    */     }
/* 40 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.tag.base.BaseChannelTagModel
 * JD-Core Version:    0.6.1
 */