/*    */ package com.portal.sysmgr.action.tag;
/*    */ 
/*    */ /*    */ import java.util.List;

/*    */ import org.apache.commons.lang.StringUtils;

import com.portal.sysmgr.utils.TagModelTools;
/*    */ import com.portal.sysmgr.utils.ViewTools;

/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.TemplateMethodModelEx;
/*    */ import freemarker.template.TemplateModel;
/*    */ import freemarker.template.TemplateModelException;
/*    */ 
/*    */ public class PageModel
/*    */   implements TemplateMethodModelEx
/*    */ {
/*    */   @Override
public Object exec(List args)
/*    */     throws TemplateModelException
/*    */   {
/* 26 */     Integer page = null;
/* 27 */     if (args.size() > 0) {
/* 28 */       TemplateModel arg0 = (TemplateModel)args.get(0);
/* 29 */       page = TagModelTools.getInt("arg0", arg0);
/*    */     }
/* 31 */     if ((page == null) || (page.intValue() < 1)) {
/* 32 */       page = Integer.valueOf(1);
/*    */     }
/* 34 */     Environment env = Environment.getCurrentEnvironment();
/* 35 */     String url = ViewTools.getUrl(env);
/* 36 */     if (StringUtils.isBlank(url)) {
/* 37 */       return "";
/*    */     }
/* 39 */     String queryString = null;
/* 40 */     String uri = url;
/* 41 */     int pos = url.indexOf("?");
/* 42 */     if (pos != -1) {
/* 43 */       queryString = url.substring(pos + 1);
/* 44 */       uri = url.substring(0, pos);
/*    */     }
/* 46 */     uri = pageUrl(uri, page);
/*    */     String result;
/* 48 */     if (StringUtils.isNotBlank(queryString))
/* 49 */       result = uri + "?" + queryString;
/*    */     else {
/* 51 */       result = uri;
/*    */     }
/* 53 */     return result;
/*    */   }
/*    */ 
/*    */   private String pageUrl(String uri, Integer page) {
/* 57 */     if (StringUtils.isBlank(uri)) {
/* 58 */       return "";
/*    */     }
/* 60 */     StringBuilder sb = new StringBuilder();
/* 61 */     if (uri.lastIndexOf("_") > -1) {
/* 62 */       sb.append(uri.substring(0, uri.lastIndexOf("_")));
/*    */     }
/* 64 */     else if (uri.lastIndexOf(".") > -1)
/* 65 */       sb.append(uri.substring(0, uri.lastIndexOf(".")));
/*    */     else {
/* 67 */       sb.append(uri.substring(0, uri.lastIndexOf("/")));
/*    */     }
/*    */ 
/* 70 */     if (page.intValue() > 1) {
/* 71 */       sb.append("_").append(page);
/*    */     }
/* 73 */     if (uri.lastIndexOf(".") > -1)
/* 74 */       sb.append(uri.substring(uri.lastIndexOf("."), uri.length()));
/*    */     else {
/* 76 */       sb.append(uri.substring(uri.lastIndexOf("/"), uri.length()));
/*    */     }
/* 78 */     return sb.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.tag.PageModel
 * JD-Core Version:    0.6.1
 */