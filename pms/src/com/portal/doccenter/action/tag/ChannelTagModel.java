/*    */ package com.portal.doccenter.action.tag;
/*    */ 
/*    */ /*    */ import java.io.IOException;
/*    */ import java.util.Map;

/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;

import com.portal.doccenter.entity.Channel;
/*    */ import com.portal.doccenter.service.ChannelService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.TagModelTools;
/*    */ import com.portal.sysmgr.utils.ViewTools;

/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ 
/*    */ 
/*    */ public class ChannelTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_ID = "id";
/*    */   public static final String PARAM_PATH = "path";
/*    */   public static final String PARAM_SITE_ID = "siteId";
/*    */ 
/*    */   @Autowired
/*    */   private ChannelService channelService;
/*    */ 
/*    */   @Override
public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 46 */     Site site = ViewTools.getSite(env);
/* 47 */     Integer id = TagModelTools.getInt("id", params);
/*    */     Channel channel;
/* 49 */     if (id != null) {
/* 50 */       channel = this.channelService.findById(id);
/*    */     } else {
/* 52 */       String path = TagModelTools.getString("path", params);
/* 53 */       if (StringUtils.isBlank(path)) {
/* 54 */         System.out.println("缺少必要参数!");
/* 55 */         return;
/*    */       }
/* 57 */       Integer siteId = TagModelTools.getInt("siteId", params);
/* 58 */       if (siteId == null) {
/* 59 */         siteId = site.getId();
/*    */       }
/* 61 */       channel = this.channelService.findByPathForTag(path, siteId);
/*    */     }
/* 63 */     env.setVariable("bean", ObjectWrapper.DEFAULT_WRAPPER.wrap(channel));
/* 64 */     if (body != null)
/* 65 */       body.render(env.getOut());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.tag.ChannelTagModel
 * JD-Core Version:    0.6.1
 */