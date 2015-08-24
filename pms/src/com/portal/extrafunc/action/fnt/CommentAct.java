/*     */ package com.portal.extrafunc.action.fnt;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.javapms.basic.utils.ServicesUtils;
/*     */ import com.portal.doccenter.entity.Article;
/*     */ import com.portal.doccenter.service.ArticleService;
/*     */ import com.portal.extrafunc.action.cache.CommentUpCache;
/*     */ import com.portal.extrafunc.entity.Comment;
/*     */ import com.portal.extrafunc.service.CommentService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.sysmgr.utils.ViewTools;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CommentAct
/*     */ {
/*     */   public static final String COMMENT_PAGE = "tpl.commentPage";
/*     */   public static final String COMMENT_LIST = "comment_list";
/*     */   public static final String PARENT_LIST = "parent_list";
/*     */ 
/*     */   @Autowired
/*     */   private CommentService commentService;
/*     */ 
/*     */   @Autowired
/*     */   private ArticleService articleService;
/*     */ 
/*     */   @Autowired
/*     */   private CommentUpCache commentUpCache;
/*     */ 
/*     */   @RequestMapping(value={"/comment-{docId:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String page(@PathVariable Integer docId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  39 */     return commentpage(docId, request, response, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/comment-{docId:[0-9]+}_{page:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String commentpage(@PathVariable Integer docId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  46 */     Site site = ContextTools.getSite(request);
/*  47 */     Article doc = this.articleService.findById(docId);
/*  48 */     if (doc == null) {
/*  49 */       return ViewTools.showMessage(null, request, model, "文档不存在!", Integer.valueOf(0));
/*     */     }
/*  51 */     ViewTools.frontData(request, model, site);
/*  52 */     model.addAttribute("doc", doc);
/*  53 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  54 */       "extrafunc/comment", "tpl.commentPage");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/comment.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void submit(Integer docId, Integer parentId, String content, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONException
/*     */   {
/*  61 */     Site site = ContextTools.getSite(request);
/*  62 */     User user = ContextTools.getUser(request);
/*  63 */     JSONObject json = new JSONObject();
/*  64 */     if (docId == null) {
/*  65 */       json.put("success", false);
/*  66 */       json.put("status", -1);
/*  67 */       ResponseUtils.renderJson(response, json.toString());
/*  68 */       return;
/*     */     }
/*  70 */     Article doc = this.articleService.findById(docId);
/*  71 */     if (doc == null) {
/*  72 */       json.put("success", false);
/*  73 */       json.put("status", -2);
/*  74 */       ResponseUtils.renderJson(response, json.toString());
/*  75 */       return;
/*     */     }
/*  77 */     if (!doc.getCommentControl().booleanValue()) {
/*  78 */       json.put("success", false);
/*  79 */       json.put("status", -3);
/*  80 */       ResponseUtils.renderJson(response, json.toString());
/*  81 */       return;
/*     */     }
/*  83 */     String ip = ServicesUtils.getIpAddr(request);
/*  84 */     this.commentService.comment(content, ip, parentId, doc, user, site);
/*  85 */     json.put("success", true);
/*  86 */     json.put("status", 0);
/*     */ 
/*  88 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/commentList.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String commentList(Integer docId, Integer pageNo, Integer count, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  95 */     Site site = ContextTools.getSite(request);
/*  96 */     Page page = this.commentService.getPage(site.getId(), docId, null, 
/*  97 */       Boolean.valueOf(true), Boolean.valueOf(true), 2, null, null, pageNo.intValue(), count.intValue());
/*  98 */     model.addAttribute("page", page);
/*  99 */     ViewTools.frontData(request, model, site);
/* 100 */     response.setHeader("Cache-Control", "no-cache");
/* 101 */     response.setContentType("text/json;charset=UTF-8");
/* 102 */     return ViewTools.getTplPath(null, site.getSolutionPath(), 
/* 103 */       "extrafunc/comment", "comment_list");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/commentListByPre.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String commentListByPre(Integer parentId, Integer pageNo, Integer count, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 110 */     Site site = ContextTools.getSite(request);
/* 111 */     Page page = this.commentService.getPage(site.getId(), null, 
/* 112 */       parentId, Boolean.valueOf(true), Boolean.valueOf(false), 3, null, null, pageNo.intValue(), count.intValue());
/* 113 */     Comment comment = this.commentService.findById(parentId);
/* 114 */     model.addAttribute("page", page);
/* 115 */     model.addAttribute("comment", comment);
/* 116 */     ViewTools.frontData(request, model, site);
/* 117 */     response.setHeader("Cache-Control", "no-cache");
/* 118 */     response.setContentType("text/json;charset=UTF-8");
/* 119 */     return ViewTools.getTplPath(null, site.getSolutionPath(), 
/* 120 */       "extrafunc/comment", "parent_list");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/commentUps.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public void commentUps(Integer commentId, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 126 */     JSONObject json = new JSONObject();
/* 127 */     Integer ups = this.commentUpCache.upAndGet(commentId);
/* 128 */     json.put("ups", ups);
/* 129 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.fnt.CommentAct
 * JD-Core Version:    0.6.1
 */