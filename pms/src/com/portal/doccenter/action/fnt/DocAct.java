/*     */ package com.portal.doccenter.action.fnt;
/*     */ 
/*     */ /*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Set;

/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.data.domain.PageImpl;
/*     */ import org.springframework.data.domain.PageRequest;
/*     */ import org.springframework.data.domain.Pageable;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;

import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.datacenter.docdata.service.KeywordService;
/*     */ import com.portal.doccenter.action.fnt.cache.DocViewsCountCache;
/*     */ import com.portal.doccenter.entity.Article;
/*     */ import com.portal.doccenter.entity.ArticleAttachment;
/*     */ import com.portal.doccenter.service.ArticleService;
/*     */ import com.portal.doccenter.service.ArticleSignService;
/*     */ import com.portal.doccenter.service.DocStatisService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.staticpage.StaticPageService;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.sysmgr.utils.ViewTools;
/*     */ import com.portal.usermgr.entity.Group;
/*     */ import com.portal.usermgr.entity.User;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Controller
/*     */ public class DocAct
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private ArticleService articleService;
/*     */ 
/*     */   @Autowired
/*     */   private KeywordService keywordService;
/*     */ 
/*     */   @Autowired
/*     */   private DocViewsCountCache viewsCountCache;
/*     */ 
/*     */   @Autowired
/*     */   private StaticPageService staticPageService;
/*     */ 
/*     */   @Autowired
/*     */   private ArticleSignService signService;
/*     */ 
/*     */   @Autowired
/*     */   private DocStatisService statisService;
/*     */ 
/*     */   @Autowired
/*     */   private ServletContext ctx;
/*     */ 
/*     */   @RequestMapping({"/doc/{docId:[0-9]+}.jsp"})
/*     */   public String docdetail(@PathVariable Integer docId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  59 */     return docdetailpageNo(docId, Integer.valueOf(1), request, response, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/doc/{docId:[0-9]+}_{page:[0-9]+}.jsp"})
/*     */   public String docdetailpageNo(@PathVariable Integer docId, @PathVariable Integer page, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  66 */     Site site = ContextTools.getSite(request);
/*  67 */     Article doc = this.articleService.findById(docId);
/*  68 */     if (doc == null) {
/*  69 */       return ViewTools.pageNotFound(response);
/*     */     }
/*  71 */     if (!site.equals(doc.getSite())) {
/*  72 */       return ViewTools.pageNotFound(response);
/*     */     }
/*  74 */     if (!doc.isChecked()) {
/*  75 */       return ViewTools.pageNotFound(response);
/*     */     }
/*  77 */     if (!StringUtils.isBlank(doc.getLink())) {
/*  78 */       return "redirect:" + doc.getLink();
/*     */     }
/*  80 */     if (doc.getStaticDoc()) {
/*  81 */       this.staticPageService.staticArticleCheck(doc, page);
/*  82 */       return doc.getStaticRealPath(page);
/*     */     }
/*  84 */     String result = checkView(doc, request, response, model);
/*  85 */     if (result != null) {
/*  86 */       return result;
/*     */     }
/*  88 */     String ctx = site.getContextPath();
/*  89 */     ctx = ctx == null ? "" : ctx;
/*  90 */     String txt = doc.getTxtByNo(page.intValue());
/*  91 */     txt = StringUtils.replace(txt, "../..", ctx);
/*  92 */     txt = this.keywordService.attachKeyword(site.getId(), txt);
/*  93 */     Pageable pa = new PageRequest(page.intValue() - 1, 1);
/*  94 */     Page p = new PageImpl(Collections.emptyList(), pa, 
/*  95 */       doc.getPageCount());
/*  96 */     model.addAttribute("page", p);
/*  97 */     model.addAttribute("doc", doc);
/*  98 */     model.addAttribute("channel", doc.getChannel());
/*  99 */     model.addAttribute("title", doc.getTitle());
/* 100 */     model.addAttribute("txt", txt);
/* 101 */     ViewTools.frontData(request, model, site);
/* 102 */     ViewTools.frontPageData(doc.getUrl(page), model, page);
/* 103 */     return doc.getTplContentOrDef();
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/doc/viewCount.jsp"})
/*     */   public void viewsCount(Integer docId, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 109 */     JSONObject json = new JSONObject();
/* 110 */     Integer views = this.viewsCountCache.viewsCount(docId);
/* 111 */     json.put("views", views);
/* 112 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/doc/signcheck.jsp"})
/*     */   public String sign(Integer docId, HttpServletRequest request, ModelMap model) {
/* 117 */     User user = ContextTools.getUser(request);
/* 118 */     if (user == null) {
/* 119 */       Article doc = this.articleService.findById(docId);
/* 120 */       return ViewTools.showLogin(doc.getUrl(), request, model, 
/* 121 */         "必须登录才可以签收!");
/*     */     }
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/doc/sign.jsp"})
/*     */   public void sign(Integer docId, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 129 */     JSONObject json = new JSONObject();
/* 130 */     User user = ContextTools.getUser(request);
/* 131 */     this.signService.save(docId, user);
/* 132 */     json.put("success", true);
/* 133 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/doc/ups.jsp"})
/*     */   public void ups(Integer docId, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 139 */     JSONObject json = new JSONObject();
/* 140 */     if (docId == null) {
/* 141 */       json.put("success", false);
/*     */     } else {
/* 143 */       this.statisService.ups(docId);
/* 144 */       json.put("success", true);
/*     */     }
/* 146 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/doc/treads.jsp"})
/*     */   public void treads(Integer docId, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 152 */     JSONObject json = new JSONObject();
/* 153 */     if (docId == null) {
/* 154 */       json.put("success", false);
/*     */     } else {
/* 156 */       this.statisService.treads(docId);
/* 157 */       json.put("success", true);
/*     */     }
/* 159 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/att/dowload-{aId:[0-9]+}-{index}.jsp"})
/*     */   public void docAttDowload(@PathVariable Integer aId, Integer index, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 166 */     ArticleAttachment att = checkAtt(aId, index);
/* 167 */     if (att == null) {
/* 168 */       return;
/*     */     }
/* 170 */     File file = new File(this.ctx.getRealPath(att.getPath()));
/* 171 */     response.setContentType("text/html; charset=UTF-8");
/* 172 */     response.setContentType("application/x-msdownload");
/* 173 */     response.setHeader("Content-Disposition", 
/* 174 */       "attachment;filename=" + att.getName());
/* 175 */     response.setContentLength((int)file.length());
/*     */     try
/*     */     {
/* 178 */       FileInputStream fis = new FileInputStream(file);
/* 179 */       BufferedInputStream buff = new BufferedInputStream(fis);
/* 180 */       byte[] b = new byte[1024];
/* 181 */       long k = 0L;
/*     */       try
/*     */       {
/* 184 */         OutputStream myout = response.getOutputStream();
/* 185 */         while (k < file.length()) {
/* 186 */           int j = buff.read(b, 0, 1024);
/* 187 */           k += j;
/* 188 */           myout.write(b, 0, j);
/*     */         }
/* 190 */         myout.flush();
/*     */       } catch (IOException e) {
/* 192 */         e.printStackTrace();
/*     */       }
/*     */     } catch (FileNotFoundException e) {
/* 195 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private String checkView(Article doc, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 201 */     User user = ContextTools.getUser(request);
/* 202 */     Site site = doc.getSite();
		/* 203 */Set<Group> groups = doc.getViewGroupsExt();
/* 204 */     if ((!doc.getStatus().equals(Byte.valueOf((byte)2))) && (
/* 205 */       (user == null) || (user.getAdmin() == null))) {
/* 206 */       return ViewTools.pageNotFound(response);
/*     */     }
/*     */ 
/* 209 */     if ((groups != null) && (groups.size() > 0)) {
/* 210 */       if (user == null) {
/* 211 */         return ViewTools.showLogin(request, model, "您需要登录才能浏览该信息!");
/*     */       }
/* 213 */       if ((user.getAdmin() == null) && 
/* 214 */         (user.getMember() != null)) {
/* 215 */         Integer groupId = user.getMember().getGroup(site.getId())
/* 216 */           .getId();
/* 217 */         for (Group group : groups) {
/* 218 */           if (group.getId().equals(groupId)) {
/* 219 */             return null;
/*     */           }
/*     */         }
/* 222 */         String groupName = user.getMember().getGroup(site.getId())
/* 223 */           .getName();
/* 224 */         String msg = "您所在的会员组：" + groupName + "没有访问该页面的权限";
/* 225 */         return ViewTools.showMessage(doc.getChannel().getUrl(), 
/* 226 */           request, model, msg, Integer.valueOf(0));
/*     */       }
/*     */     }
/*     */ 
/* 230 */     return null;
/*     */   }
/*     */ 
/*     */   private ArticleAttachment checkAtt(Integer aId, Integer index) {
/* 234 */     Article a = this.articleService.findById(aId);
/* 235 */     if (a == null) {
/* 236 */       return null;
/*     */     }
/* 238 */     List atts = a.getAtts();
/* 239 */     if (atts == null) {
/* 240 */       return null;
/*     */     }
/* 242 */     if (index.intValue() > atts.size() - 1) {
/* 243 */       return null;
/*     */     }
/* 245 */     ArticleAttachment att = (ArticleAttachment)atts.get(index.intValue());
/* 246 */     return att;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.fnt.DocAct
 * JD-Core Version:    0.6.1
 */