/*     */ package com.portal.doccenter.action.tag.base;
/*     */ 
/*     */ import com.portal.doccenter.service.ArticleService;
/*     */ import com.portal.doccenter.service.ChannelService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.service.SiteService;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.sysmgr.utils.TagModelTools;
/*     */ import com.portal.sysmgr.utils.ViewTools;
/*     */ import freemarker.core.Environment;
/*     */ import freemarker.template.TemplateDirectiveModel;
/*     */ import freemarker.template.TemplateException;
/*     */ import freemarker.template.TemplateModel;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ 
/*     */ public abstract class BaseDocTagModel
/*     */   implements TemplateDirectiveModel
/*     */ {
/*     */   public static final String PARAM_CHANNEL_ID = "cId";
/*     */   public static final String PARAM_MODEL_ID = "mId";
/*     */   public static final String PARAM_DEPART_ID = "dId";
/*     */   public static final String PARAM_USER_ID = "uId";
/*     */   public static final String PARAM_CALL_LEVEL = "callLevel";
/*     */   public static final String PARAM_TYPE_ID = "tId";
/*     */   public static final String PARAM_RECOMMEND = "recommend";
/*     */   public static final String PARAM_DATE = "date";
/*     */   public static final String PARAM_ORDER_BY = "orderBy";
/*     */ 
/*     */   @Autowired
/*     */   protected ChannelService channelService;
/*     */ 
/*     */   @Autowired
/*     */   protected SiteService siteService;
/*     */ 
/*     */   @Autowired
/*     */   protected ArticleService articleService;
/*     */ 
/*     */   protected Integer[] getChannelIds(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/*  69 */     Integer[] ids = TagModelTools.getIntArray("cId", params);
/*  70 */     if ((ids != null) && (ids.length > 0)) {
/*  71 */       return ids;
/*     */     }
/*  73 */     return null;
/*     */   }
/*     */ 
/*     */   protected Integer getDepartId(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/*  79 */     return TagModelTools.getInt("dId", params);
/*     */   }
/*     */ 
/*     */   protected Integer getUserId(Map<String, TemplateModel> params) throws TemplateException
/*     */   {
/*  84 */     return TagModelTools.getInt("uId", params);
/*     */   }
/*     */ 
/*     */   protected Date getDate(Map<String, TemplateModel> params) throws TemplateException
/*     */   {
/*  89 */     return TagModelTools.getDate("date", params);
/*     */   }
/*     */ 
/*     */   protected Integer[] getModelIds(Map<String, TemplateModel> params) throws TemplateException
/*     */   {
/*  94 */     Integer[] ids = TagModelTools.getIntArray("mId", params);
/*  95 */     if ((ids != null) && (ids.length > 0)) {
/*  96 */       return ids;
/*     */     }
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */   protected int getCallLevel(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 104 */     Integer callLevel = TagModelTools.getInt("callLevel", params);
/* 105 */     if ((callLevel == null) || (callLevel.intValue() < 0)) {
/* 106 */       return 0;
/*     */     }
/* 108 */     return callLevel.intValue();
/*     */   }
/*     */ 
/*     */   protected Integer[] getTypeIds(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 114 */     Integer[] typeIds = TagModelTools.getIntArray("tId", params);
/* 115 */     return typeIds;
/*     */   }
/*     */ 
/*     */   protected Boolean getRecommend(Map<String, TemplateModel> params) throws TemplateException
/*     */   {
/* 120 */     String recommend = TagModelTools.getString("recommend", params);
/* 121 */     if ("1".equals(recommend)) {
/* 122 */       return Boolean.valueOf(true);
/*     */     }
/* 124 */     return null;
/*     */   }
/*     */ 
/*     */   protected int getOrderBy(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 130 */     Integer orderBy = TagModelTools.getInt("orderBy", params);
/* 131 */     if (orderBy == null) {
/* 132 */       return 0;
/*     */     }
/* 134 */     return orderBy.intValue();
/*     */   }
/*     */ 
/*     */   protected Object getData(Map<String, TemplateModel> params, Environment env)
/*     */     throws TemplateException
/*     */   {
/* 140 */     int orderBy = getOrderBy(params);
/* 141 */     Boolean recommend = getRecommend(params);
/* 142 */     Integer siteId = ViewTools.getSite(env).getId();
/* 143 */     Integer departId = getDepartId(params);
/* 144 */     Integer userId = getUserId(params);
/* 145 */     Date date = getDate(params);
/* 146 */     Integer[] typeIds = getTypeIds(params);
/* 147 */     int count = ViewTools.getCount(params);
/*     */ 
/* 149 */     Integer[] channelIds = getChannelIds(params);
/* 150 */     if (channelIds != null) {
/* 151 */       Integer[] modelIds = getModelIds(params);
/* 152 */       int callLevel = getCallLevel(params);
/* 153 */       if (isPage()) {
/* 154 */         int pageNo = ViewTools.getPageNo(env);
/* 155 */         Page p = this.articleService.getPageTagByChannelIds(
/* 156 */           channelIds, siteId, modelIds, typeIds, departId, 
/* 157 */           userId, recommend, date, orderBy, callLevel, pageNo, 
/* 158 */           count);
/* 159 */         ContextTools.setTotalPages(Integer.valueOf(p.getTotalPages()));
/* 160 */         return p;
/*     */       }
/* 162 */       int first = ViewTools.getFirst(params);
/* 163 */       return this.articleService.getListTagByChannelIds(channelIds, 
/* 164 */         siteId, modelIds, typeIds, departId, userId, recommend, 
/* 165 */         date, orderBy, callLevel, Integer.valueOf(first), Integer.valueOf(count));
/*     */     }
/*     */ 
/* 169 */     Integer[] modelIds = getModelIds(params);
/* 170 */     if (modelIds != null) {
/* 171 */       if (isPage()) {
/* 172 */         int pageNo = ViewTools.getPageNo(env);
/* 173 */         Page p = this.articleService.getPageTagByModelIds(modelIds, 
/* 174 */           typeIds, siteId, recommend, orderBy, pageNo, count);
/* 175 */         ContextTools.setTotalPages(Integer.valueOf(p.getTotalPages()));
/* 176 */         return p;
/*     */       }
/* 178 */       int first = ViewTools.getFirst(params);
/* 179 */       return this.articleService.getListTagByModelIds(modelIds, typeIds, 
/* 180 */         siteId, recommend, orderBy, Integer.valueOf(first), Integer.valueOf(count));
/*     */     }
/*     */ 
/* 184 */     if (isPage()) {
/* 185 */       int pageNo = ViewTools.getPageNo(env);
/* 186 */       Page p = this.articleService.getPageTagByChannelIds(null, 
/* 187 */         siteId, null, typeIds, departId, userId, recommend, date, 
/* 188 */         orderBy, 0, pageNo, count);
/* 189 */       ContextTools.setTotalPages(Integer.valueOf(p.getTotalPages()));
/* 190 */       return p;
/*     */     }
/* 192 */     int first = ViewTools.getFirst(params);
/* 193 */     return this.articleService.getListTagByChannelIds(null, siteId, null, 
/* 194 */       typeIds, departId, userId, recommend, date, orderBy, 0, 
/* 195 */       Integer.valueOf(first), Integer.valueOf(count));
/*     */   }
/*     */ 
/*     */   protected abstract boolean isPage();
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.tag.base.BaseDocTagModel
 * JD-Core Version:    0.6.1
 */