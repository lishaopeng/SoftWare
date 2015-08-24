/*    */ package com.portal.doccenter.action.tag;
/*    */ 
/*    */ import com.portal.doccenter.action.tag.base.BaseChannelTagModel;
/*    */ import com.portal.doccenter.service.ChannelService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.TagModelTools;
/*    */ import com.portal.sysmgr.utils.ViewTools;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ChannelListTagModel extends BaseChannelTagModel
/*    */ {
/*    */   public static final String TPL_NAME = "tplName";
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 37 */     Site site = ViewTools.getSite(env);
/* 38 */     Integer parentId = TagModelTools.getInt("pId", params);
/* 39 */     Integer siteId = TagModelTools.getInt("sId", params);
/* 40 */     String tplName = TagModelTools.getString("tplName", params);
/* 41 */     Boolean isAlone = getAlone(params);
/* 42 */     if (siteId == null) {
/* 43 */       siteId = site.getId();
/*    */     }
/* 45 */     List list = this.channelService.getChannelListByTag(siteId, 
/* 46 */       parentId, isAlone, true, ViewTools.getCount(params));
/* 47 */     env.setVariable("list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 48 */     if (!StringUtils.isBlank(tplName)) {
/* 49 */       ViewTools.includeTpl(tplName, site, env);
/*    */     }
/* 51 */     else if (body != null)
/* 52 */       body.render(env.getOut());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.tag.ChannelListTagModel
 * JD-Core Version:    0.6.1
 */