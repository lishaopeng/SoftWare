/*    */ package com.portal.doccenter.action.tag;
/*    */ 
/*    */ import com.portal.datacenter.lucene.LuceneDocPageService;
/*    */ import com.portal.doccenter.action.tag.base.BaseSearchTagModel;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.TagModelTools;
/*    */ import com.portal.sysmgr.utils.ViewTools;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.Date;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ 
/*    */ public class SearchPageTagModel extends BaseSearchTagModel
/*    */ {
/*    */   public static final String TPL_NAME = "tplName";
/*    */ 
/*    */   @Autowired
/*    */   private LuceneDocPageService luceneDocPageService;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 34 */     Site site = ViewTools.getSite(env);
/* 35 */     int pageNo = ViewTools.getPageNo(env);
/* 36 */     int count = ViewTools.getCount(params);
/* 37 */     String tplName = TagModelTools.getString("tplName", params);
/* 38 */     String query = getQuery(params);
/* 39 */     Integer channelId = getChannelId(params);
/* 40 */     Integer modelId = getModelId(params);
/* 41 */     Date startDate = getStartDate(params);
/* 42 */     Date endDate = getEndDate(params);
/* 43 */     Page page = this.luceneDocPageService.searchArticle(query, query, 
/* 44 */       site.getId(), modelId, channelId, startDate, endDate, pageNo, 
/* 45 */       count);
/* 46 */     env.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
/* 47 */     if (!StringUtils.isBlank(tplName)) {
/* 48 */       ViewTools.includeTpl(tplName, site, env);
/*    */     }
/* 50 */     else if (body != null)
/* 51 */       body.render(env.getOut());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.tag.SearchPageTagModel
 * JD-Core Version:    0.6.1
 */