/*    */ package com.portal.doccenter.action.tag;
/*    */ 
/*    */ /*    */ import java.io.IOException;
/*    */ import java.util.Map;

/*    */ import org.springframework.beans.factory.annotation.Autowired;

import com.portal.doccenter.entity.Article;
/*    */ import com.portal.doccenter.service.ArticleService;
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
/*    */ public class DocTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_ID = "id";
/*    */   public static final String PRAMA_NEXT = "next";
/*    */   public static final String PARAM_CHANNEL_ID = "cId";
/*    */ 
/*    */   @Autowired
/*    */   private ArticleService articleService;
/*    */ 
/*    */   @Override
public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 45 */     Integer id = getId(params);
/* 46 */     Boolean next = TagModelTools.getBool("next", params);
/*    */     Article article;
/* 48 */     if (next == null) {
/* 49 */       article = this.articleService.findById(id);
/*    */     } else {
/* 51 */       Site site = ViewTools.getSite(env);
/* 52 */       Integer channelId = TagModelTools.getInt("cId", params);
/* 53 */       article = this.articleService.getSide(id, site.getId(), channelId, next.booleanValue());
/*    */     }
/* 55 */     env.setVariable("bean", ObjectWrapper.DEFAULT_WRAPPER.wrap(article));
/* 56 */     if (body != null)
/* 57 */       body.render(env.getOut());
/*    */   }
/*    */ 
/*    */   private Integer getId(Map<String, TemplateModel> params)
/*    */     throws TemplateException
/*    */   {
/* 63 */     Integer id = TagModelTools.getInt("id", params);
/* 64 */     if (id != null) {
/* 65 */       return id;
/*    */     }
/* 67 */     System.out.println("缺少必要参数!");
/* 68 */     return Integer.valueOf(0);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.tag.DocTagModel
 * JD-Core Version:    0.6.1
 */