/*     */ package com.portal.sysmgr.utils;
/*     */ 
/*     */ import com.javapms.basic.plugin.springmvc.MessageResolver;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import freemarker.core.Environment;
/*     */ import freemarker.template.AdapterTemplateModel;
/*     */ import freemarker.template.TemplateException;
/*     */ import freemarker.template.TemplateHashModel;
/*     */ import freemarker.template.TemplateModel;
/*     */ import freemarker.template.TemplateModelException;
/*     */ import freemarker.template.TemplateNumberModel;
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class ViewTools
/*     */ {
/*     */   public static final String PROTOCOL = "http://";
/*     */ 
/*     */   public static String getTplPath(HttpServletRequest request, String solutionPath, String dir, String name)
/*     */   {
/*  34 */     if (request != null) {
/*  35 */       return solutionPath + "/" + dir + "/" + 
/*  36 */         MessageResolver.getMessage(request, name, new Object[0]) + 
/*  37 */         ".html";
/*     */     }
/*  39 */     return solutionPath + "/" + dir + "/" + name + ".html";
/*     */   }
/*     */ 
/*     */   public static String pageNotFound(HttpServletResponse response)
/*     */   {
/*     */     try
/*     */     {
/*  51 */       response.sendError(404);
/*  52 */       return null;
/*     */     } catch (IOException e) {
/*  54 */       e.printStackTrace();
/*     */     }
/*  56 */     return null;
/*     */   }
/*     */ 
/*     */   public static String showMessage(String nextUrl, HttpServletRequest request, Map<String, Object> model, String msg, Integer status)
/*     */   {
/*  62 */     Site site = ContextTools.getSite(request);
/*  63 */     if (!StringUtils.isBlank(msg)) {
/*  64 */       model.put("msg", msg);
/*     */     }
/*  66 */     frontData(request, model, site);
/*  67 */     if (!StringUtils.isBlank(nextUrl)) {
/*  68 */       model.put("url", nextUrl);
/*     */     }
/*  70 */     model.put("status", status);
/*  71 */     return getTplPath(request, site.getSolutionPath(), 
/*  72 */       "common/tips", "tpl.msg");
/*     */   }
/*     */ 
/*     */   public static String showLogin(String url, HttpServletRequest request, Map<String, Object> model, String msg)
/*     */   {
/*  77 */     Site site = ContextTools.getSite(request);
/*  78 */     if (!StringUtils.isBlank(msg)) {
/*  79 */       model.put("msg", msg);
/*     */     }
/*  81 */     frontData(url, model, site);
/*  82 */     return getTplPath(request, site.getSolutionPath(), 
/*  83 */       "user", "member.login");
/*     */   }
/*     */ 
/*     */   public static String showLogin(HttpServletRequest request, Map<String, Object> model, String msg)
/*     */   {
/*  88 */     Site site = ContextTools.getSite(request);
/*  89 */     if (!StringUtils.isBlank(msg)) {
/*  90 */       model.put("msg", msg);
/*     */     }
/*  92 */     frontData(request, model, site);
/*  93 */     return getTplPath(request, site.getSolutionPath(), 
/*  94 */       "user", "member.login");
/*     */   }
/*     */ 
/*     */   public static String showNextUrl(String nextUrl, Site site) {
/*  98 */     if (nextUrl.indexOf("http://") > -1) {
/*  99 */       return nextUrl;
/*     */     }
/* 101 */     if (nextUrl.indexOf(site.getDomain()) > -1) {
/* 102 */       return "http://" + nextUrl;
/*     */     }
/* 104 */     if ((!StringUtils.isBlank(site.getContextPath())) && 
/* 105 */       (nextUrl.startsWith(site.getContextPath()))) {
/* 106 */       return nextUrl.substring(site.getContextPath().length());
/*     */     }
/* 108 */     if (nextUrl.startsWith("/")) {
/* 109 */       return nextUrl.substring(1);
/*     */     }
/* 111 */     return nextUrl;
/*     */   }
/*     */ 
/*     */   public static void frontData(String url, Map<String, Object> map, Site site) {
/* 115 */     if (StringUtils.isNotBlank(url)) {
/* 116 */       map.put("url", url);
/*     */     }
/* 118 */     map.put("site", site);
/* 119 */     String ctx = site.getContextPath() == null ? "" : site.getContextPath();
/* 120 */     map.put("base", ctx);
/* 121 */     map.put("comm", ctx + "/skin/comm");
/* 122 */     String res_skin = ctx + "/skin" + "/" + site.getPath() + "/" + 
/* 123 */       site.getTplStyle();
/* 124 */     map.put("skin", res_skin.substring(1));
/*     */   }
/*     */ 
/*     */   public static void frontData(HttpServletRequest request, Map<String, Object> map, Site site)
/*     */   {
/* 129 */     String url = URLTools.getUrl(request);
/* 130 */     User user = ContextTools.getUser(request);
/* 131 */     if (user != null) {
/* 132 */       map.put("user", user);
/*     */     }
/* 134 */     frontData(url, map, site);
/*     */   }
/*     */ 
/*     */   public static void frontPageData(String url, Map<String, Object> map, Integer page)
/*     */   {
/* 139 */     map.put("pn", page);
/* 140 */     map.put("url", url);
/*     */   }
/*     */ 
/*     */   public static void frontPageData(HttpServletRequest request, Map<String, Object> map, Integer page)
/*     */   {
/* 145 */     String url = URLTools.getUrl(request);
/* 146 */     map.put("pn", page);
/* 147 */     map.put("url", url);
/*     */   }
/*     */ 
/*     */   public static Site getSite(Environment env) throws TemplateModelException {
/* 151 */     TemplateModel model = env.getGlobalVariable("site");
/* 152 */     if ((model instanceof AdapterTemplateModel)) {
/* 153 */       return (Site)((AdapterTemplateModel)model)
/* 154 */         .getAdaptedObject(Site.class);
/*     */     }
/* 156 */     throw new TemplateModelException("'site' not found in DataModel");
/*     */   }
/*     */ 
/*     */   public static int getPageNo(Environment env)
/*     */     throws TemplateException
/*     */   {
/* 162 */     TemplateModel pageNo = env.getGlobalVariable("pn");
/* 163 */     if ((pageNo instanceof TemplateNumberModel)) {
/* 164 */       return ((TemplateNumberModel)pageNo).getAsNumber().intValue();
/*     */     }
/* 166 */     return 1;
/*     */   }
/*     */ 
/*     */   public static int getFirst(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 172 */     Integer first = TagModelTools.getInt("first", params);
/* 173 */     if ((first == null) || (first.intValue() <= 0)) {
/* 174 */       return 0;
/*     */     }
/* 176 */     return first.intValue() - 1;
/*     */   }
/*     */ 
/*     */   public static int getCount(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 182 */     Integer count = TagModelTools.getInt("count", params);
/* 183 */     if ((count == null) || (count.intValue() <= 0)) {
/* 184 */       return 20;
/*     */     }
/* 186 */     return count.intValue();
/*     */   }
/*     */ 
/*     */   public static String getUrl(Environment env) throws TemplateModelException
/*     */   {
/* 191 */     TemplateModel model = env.getDataModel().get("url");
/* 192 */     return TagModelTools.getString("url", model);
/*     */   }
/*     */ 
/*     */   public static void includePagination(Site site, Map<String, TemplateModel> params, Environment env)
/*     */     throws TemplateException, IOException
/*     */   {
/* 198 */     String pageType = TagModelTools.getString("pageType", 
/* 199 */       params);
/* 200 */     if (!StringUtils.isBlank(pageType)) {
/* 201 */       String tpl = site.getSolutionPath() + "/common/style_page/page_" + 
/* 202 */         pageType + ".html";
/* 203 */       env.include(tpl, "UTF-8", true);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void includeTpl(String tplName, Site site, Environment env)
/*     */     throws IOException, TemplateException
/*     */   {
/* 211 */     String tpl = getTplPath(null, site.getSolutionPath(), 
/* 212 */       "common/tags", tplName);
/* 213 */     env.include(tpl, "UTF-8", true);
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.utils.ViewTools
 * JD-Core Version:    0.6.1
 */