/*    */ package com.portal.extrafunc.action.tag;
/*    */ 
/*    */ import com.portal.extrafunc.entity.Advert;
/*    */ import com.portal.extrafunc.service.AdvertService;
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
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class AdvertTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_ID = "id";
/*    */   public static final String PARAM_SLOT_ID = "sId";
/*    */   public static final String PARAM_CUSTOM = "custom";
/*    */ 
/*    */   @Autowired
/*    */   private AdvertService service;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 42 */     Site site = ViewTools.getSite(env);
/* 43 */     Integer id = TagModelTools.getInt("id", params);
/* 44 */     Integer sId = TagModelTools.getInt("sId", params);
/* 45 */     Boolean custom = TagModelTools.getBool("custom", params);
/* 46 */     boolean out = false;
/* 47 */     if (id != null) {
/* 48 */       Advert a = this.service.findById(id);
/* 49 */       if ((a != null) && (a.getEnable().booleanValue()) && 
/* 50 */         (a.getStartTime().before(new Date())))
/* 51 */         if ((a.getEndTime() != null) && (a.getEndTime().after(new Date()))) {
/* 52 */           out = true;
/* 53 */           env.setVariable("bean", ObjectWrapper.DEFAULT_WRAPPER.wrap(a));
/*    */         } else {
/* 55 */           out = true;
/* 56 */           env.setVariable("bean", ObjectWrapper.DEFAULT_WRAPPER.wrap(a));
/*    */         }
/*    */     }
/*    */     else {
/* 60 */       out = true;
/* 61 */       List list = this.service.getListByTag(site.getId(), sId);
/* 62 */       env.setVariable("list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/*    */     }
/* 64 */     if ((custom != null) && (custom.booleanValue())) {
/* 65 */       if (out)
/* 66 */         body.render(env.getOut());
/*    */     }
/*    */     else
/* 69 */       ViewTools.includeTpl("adv", site, env);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.tag.AdvertTagModel
 * JD-Core Version:    0.6.1
 */