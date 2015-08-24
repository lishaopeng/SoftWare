/*     */ package com.portal.extrafunc.action.fnt;
/*     */ 
/*     */ import com.javapms.basic.utils.ServicesUtils;
/*     */ import com.portal.extrafunc.action.cache.PostsCheckCache;
/*     */ import com.portal.extrafunc.action.cache.ThemeStatisCache;
/*     */ import com.portal.extrafunc.entity.Forum;
/*     */ import com.portal.extrafunc.entity.Posts;
/*     */ import com.portal.extrafunc.entity.Theme;
/*     */ import com.portal.extrafunc.entity.UserForum;
/*     */ import com.portal.extrafunc.service.ForumService;
/*     */ import com.portal.extrafunc.service.PostsService;
/*     */ import com.portal.extrafunc.service.ThemeService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.sysmgr.utils.ViewTools;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.multipart.MultipartHttpServletRequest;
/*     */ 
/*     */ @Controller
/*     */ public class ThemeAct
/*     */ {
/*     */   public static final String THEME_LIST = "tpl.themeList";
/*     */   public static final String THEME_DETAIL = "tpl.themeDetail";
/*     */   public static final String THEME_INPUT = "tpl.themeInput";
/*     */   public static final String REPLY_INPUT = "tpl.replyInput";
/*     */   public static final String QUOTE_INPUT = "tpl.quoteInput";
/*     */   public static final String NOT_IRRIGATION = "tpl.notIrrigation";
/*     */   public static final String EDIT_INPUT = "tpl.editInput";
/*     */ 
/*     */   @Autowired
/*     */   private ThemeService themeService;
/*     */ 
/*     */   @Autowired
/*     */   private PostsService postsService;
/*     */ 
/*     */   @Autowired
/*     */   private ForumService forumService;
/*     */ 
/*     */   @Autowired
/*     */   private ThemeStatisCache statisCache;
/*     */ 
/*     */   @Autowired
/*     */   private PostsCheckCache postsCheckCache;
/*     */ 
/*     */   @RequestMapping(value={"/themeList-{forumId:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String themeList(@PathVariable Integer forumId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  56 */     return themeListPage(forumId, Integer.valueOf(1), request, response, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/themeList-{forumId:[0-9]+}_{page:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String themeListPage(@PathVariable Integer forumId, @PathVariable Integer page, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  63 */     Site site = ContextTools.getSite(request);
/*  64 */     Forum forum = this.forumService.findById(forumId);
/*  65 */     if (forum == null) {
/*  66 */       return ViewTools.pageNotFound(response);
/*     */     }
/*  68 */     ViewTools.frontData(request, model, site);
/*  69 */     ViewTools.frontPageData(request, model, page);
/*  70 */     model.addAttribute("forum", forum);
/*  71 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  72 */       "extrafunc/forum", "tpl.themeList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/themeDetail-{themeId:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String themeDetail(@PathVariable Integer themeId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  79 */     return themeDetailPage(themeId, Integer.valueOf(1), request, response, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/themeDetail-{themeId:[0-9]+}_{page:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String themeDetailPage(@PathVariable Integer themeId, @PathVariable Integer page, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  86 */     Site site = ContextTools.getSite(request);
/*  87 */     Theme theme = this.themeService.findById(themeId);
/*  88 */     if (theme == null) {
/*  89 */       return ViewTools.pageNotFound(response);
/*     */     }
/*  91 */     model.addAttribute("theme", this.themeService.findById(themeId));
/*  92 */     ViewTools.frontData(request, model, site);
/*  93 */     ViewTools.frontPageData(request, model, page);
/*  94 */     this.statisCache.updateStatis(themeId);
/*  95 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  96 */       "extrafunc/forum", "tpl.themeDetail");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/themeInput-{forumId:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String themeInput(@PathVariable Integer forumId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 103 */     Site site = ContextTools.getSite(request);
/* 104 */     User user = ContextTools.getUser(request);
/* 105 */     if (user == null) {
/* 106 */       return ViewTools.showLogin(request, model, "必须登录才可以发帖!");
/*     */     }
/* 108 */     Forum forum = this.forumService.findById(forumId);
/* 109 */     if (forum == null) {
/* 110 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 112 */     if ((user.getUserForum() != null) && 
/* 113 */       (!user.getUserForum().getStatus().equals(Integer.valueOf(0)))) {
/* 114 */       return ViewTools.showMessage(forum.getUrl(), request, model, 
/* 115 */         "对不起，您的账号已经被管理员屏蔽，无法发帖!", Integer.valueOf(0));
/*     */     }
/* 117 */     ViewTools.frontData(request, model, site);
/* 118 */     model.addAttribute("forum", forum);
/* 119 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 120 */       "extrafunc/forum", "tpl.themeInput");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/themeSave.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String themeSubmit(Integer forumId, String title, String content, @RequestParam(value="code", required=false) List<String> code, MultipartHttpServletRequest mrequest, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 128 */     Site site = ContextTools.getSite(request);
/* 129 */     User user = ContextTools.getUser(request);
/* 130 */     if (user == null) {
/* 131 */       return ViewTools.showLogin(request, model, "必须登录才可以发帖!");
/*     */     }
/* 133 */     Forum forum = this.forumService.findById(forumId);
/* 134 */     if (forum == null) {
/* 135 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 137 */     Date d = this.postsCheckCache.postsTime(user.getUsername());
/* 138 */     if (!checkPostsTime(d)) {
/* 139 */       model.addAttribute("interval", Long.valueOf(this.postsCheckCache.getInterval()));
/* 140 */       ViewTools.frontData(request, model, site);
/* 141 */       return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 142 */         "extrafunc/forum", "tpl.notIrrigation");
/*     */     }
/* 144 */     this.postsCheckCache.updateCheck(user.getUsername());
/* 145 */     String ip = ServicesUtils.getIpAddr(request);
/* 146 */     Posts posts = this.postsService.saveTheme(site.getId(), user.getId(), 
/* 147 */       forumId, title, content, ip, mrequest.getFiles("attachment"), 
/* 148 */       code);
/* 149 */     return "redirect:" + posts.getTheme().getUrl();
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/replyInput-{themeId:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String replyInput(@PathVariable Integer themeId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 156 */     Site site = ContextTools.getSite(request);
/* 157 */     User user = ContextTools.getUser(request);
/* 158 */     if (user == null) {
/* 159 */       return ViewTools.showLogin(request, model, "必须登录才可以回复!");
/*     */     }
/* 161 */     Theme theme = this.themeService.findById(themeId);
/* 162 */     if (theme == null) {
/* 163 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 165 */     if (theme.getLock().booleanValue()) {
/* 166 */       return ViewTools.showMessage(theme.getUrl(), request, model, 
/* 167 */         "对不起，该主题已经被锁定，无法回复!", Integer.valueOf(0));
/*     */     }
/* 169 */     if ((user.getUserForum() != null) && 
/* 170 */       (!user.getUserForum().getStatus().equals(Integer.valueOf(0)))) {
/* 171 */       return ViewTools.showMessage(theme.getUrl(), request, model, 
/* 172 */         "对不起，您的账号已经被管理员屏蔽，无法发帖!", Integer.valueOf(0));
/*     */     }
/* 174 */     ViewTools.frontData(request, model, site);
/* 175 */     model.addAttribute("theme", theme);
/* 176 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 177 */       "extrafunc/forum", "tpl.replyInput");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/replyInput-{themeId:[0-9]+}-{floor:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String replyFloorInput(@PathVariable Integer themeId, @PathVariable Integer floor, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 184 */     Site site = ContextTools.getSite(request);
/* 185 */     User user = ContextTools.getUser(request);
/* 186 */     if (user == null) {
/* 187 */       return ViewTools.showLogin(request, model, "必须登录才可以回复!");
/*     */     }
/* 189 */     Theme theme = this.themeService.findById(themeId);
/* 190 */     if (theme == null) {
/* 191 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 193 */     if (theme.getLock().booleanValue()) {
/* 194 */       return ViewTools.showMessage(theme.getUrl(), request, model, 
/* 195 */         "对不起，该主题已经被锁定，无法回复!", Integer.valueOf(0));
/*     */     }
/* 197 */     if ((user.getUserForum() != null) && 
/* 198 */       (!user.getUserForum().getStatus().equals(Integer.valueOf(0)))) {
/* 199 */       return ViewTools.showMessage(theme.getUrl(), request, model, 
/* 200 */         "对不起，您的账号已经被管理员屏蔽，无法发帖!", Integer.valueOf(0));
/*     */     }
/* 202 */     ViewTools.frontData(request, model, site);
/* 203 */     model.addAttribute("theme", theme);
/* 204 */     model.addAttribute("floor", floor);
/* 205 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 206 */       "extrafunc/forum", "tpl.replyInput");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/quoteInput-{postsId:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String quoteInput(@PathVariable Integer postsId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 213 */     Site site = ContextTools.getSite(request);
/* 214 */     User user = ContextTools.getUser(request);
/* 215 */     if (user == null) {
/* 216 */       return ViewTools.showLogin(request, model, "必须登录才可以回复!");
/*     */     }
/* 218 */     Posts posts = this.postsService.findById(postsId);
/* 219 */     if (posts == null) {
/* 220 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 222 */     if (posts.getTheme().getLock().booleanValue()) {
/* 223 */       return ViewTools.showMessage(posts.getTheme().getUrl(), request, 
/* 224 */         model, "对不起，该主题已经被锁定，无法回复!", Integer.valueOf(0));
/*     */     }
/* 226 */     if ((user.getUserForum() != null) && 
/* 227 */       (!user.getUserForum().getStatus().equals(Integer.valueOf(0)))) {
/* 228 */       return ViewTools.showMessage(posts.getTheme().getUrl(), request, 
/* 229 */         model, "对不起，您的账号已经被管理员屏蔽，无法发帖!", Integer.valueOf(0));
/*     */     }
/* 231 */     if ((user.getAdmin() != null) || 
/* 232 */       ((posts.getHidden().booleanValue()) && (posts.getCreater().equals(user))) || 
/* 234 */       (posts.getTheme().getReplyUser()
/* 234 */       .indexOf("," + user.getId() + ",") > -1))
/* 235 */       model.addAttribute("showhide", Boolean.valueOf(true));
/*     */     else {
/* 237 */       model.addAttribute("showhide", Boolean.valueOf(false));
/*     */     }
/* 239 */     ViewTools.frontData(request, model, site);
/* 240 */     model.addAttribute("posts", posts);
/* 241 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 242 */       "extrafunc/forum", "tpl.quoteInput");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/replySave.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String replySubmit(Integer themeId, Integer quoteId, String title, String content, @RequestParam(value="code", required=false) List<String> code, MultipartHttpServletRequest mrequest, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 251 */     Site site = ContextTools.getSite(request);
/* 252 */     User user = ContextTools.getUser(request);
/* 253 */     if (user == null) {
/* 254 */       return ViewTools.showLogin(request, model, "必须登录才可以回复!");
/*     */     }
/* 256 */     Theme theme = this.themeService.findById(themeId);
/* 257 */     if (theme == null) {
/* 258 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 260 */     if (theme.getLock().booleanValue()) {
/* 261 */       return ViewTools.showMessage(theme.getUrl(), request, model, 
/* 262 */         "对不起，该主题已经被锁定，无法回复!", Integer.valueOf(0));
/*     */     }
/* 264 */     Date d = this.postsCheckCache.postsTime(user.getUsername());
/* 265 */     if (!checkPostsTime(d)) {
/* 266 */       model.addAttribute("interval", Long.valueOf(this.postsCheckCache.getInterval()));
/* 267 */       ViewTools.frontData(request, model, site);
/* 268 */       return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 269 */         "extrafunc/forum", "tpl.notIrrigation");
/*     */     }
/* 271 */     this.postsCheckCache.updateCheck(user.getUsername());
/* 272 */     String ip = ServicesUtils.getIpAddr(request);
/* 273 */     this.postsService.savePosts(site.getId(), user.getId(), themeId, quoteId, 
/* 274 */       title, content, ip, mrequest.getFiles("attachment"), code);
/* 275 */     return "redirect:" + theme.getUrl();
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/editInput-{postId:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String editInput(@PathVariable Integer postId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 282 */     Site site = ContextTools.getSite(request);
/* 283 */     User user = ContextTools.getUser(request);
/* 284 */     if (user == null) {
/* 285 */       return ViewTools.showLogin(request, model, "必须登录才可以发帖!");
/*     */     }
/* 287 */     Posts posts = this.postsService.findById(postId);
/* 288 */     if (posts == null) {
/* 289 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 291 */     if ((user.getUserForum() != null) && 
/* 292 */       (!user.getUserForum().getStatus().equals(Integer.valueOf(0)))) {
/* 293 */       return ViewTools.showMessage(posts.getTheme().getUrl(), request, 
/* 294 */         model, "对不起，您的账号已经被管理员屏蔽，无法发帖!", Integer.valueOf(0));
/*     */     }
/* 296 */     if ((user.getAdmin() == null) && (!posts.getCreater().equals(user))) {
/* 297 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 299 */     ViewTools.frontData(request, model, site);
/* 300 */     model.addAttribute("posts", posts);
/* 301 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 302 */       "extrafunc/forum", "tpl.editInput");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/editSave.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String editSubmit(Integer postId, String title, String content, @RequestParam(value="code", required=false) List<String> code, MultipartHttpServletRequest mrequest, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 310 */     Site site = ContextTools.getSite(request);
/* 311 */     User user = ContextTools.getUser(request);
/* 312 */     if (user == null) {
/* 313 */       return ViewTools.showLogin(request, model, "必须登录才可以发帖!");
/*     */     }
/* 315 */     Posts posts = this.postsService.findById(postId);
/* 316 */     if (posts == null) {
/* 317 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 319 */     if ((user.getAdmin() == null) && (!posts.getCreater().equals(user))) {
/* 320 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 322 */     Date d = this.postsCheckCache.postsTime(user.getUsername());
/* 323 */     if (!checkPostsTime(d)) {
/* 324 */       model.addAttribute("interval", Long.valueOf(this.postsCheckCache.getInterval()));
/* 325 */       ViewTools.frontData(request, model, site);
/* 326 */       return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 327 */         "extrafunc/forum", "tpl.notIrrigation");
/*     */     }
/* 329 */     this.postsCheckCache.updateCheck(user.getUsername());
/* 330 */     String ip = ServicesUtils.getIpAddr(request);
/* 331 */     posts = this.postsService.updatePosts(postId, user.getId(), title, content, 
/* 332 */       ip, mrequest.getFiles("attachment"), code);
/* 333 */     return "redirect:" + posts.getTheme().getUrl();
/*     */   }
/*     */ 
/*     */   private boolean checkPostsTime(Date d) {
/* 337 */     if (d == null) {
/* 338 */       return true;
/*     */     }
/* 340 */     long second = System.currentTimeMillis() - d.getTime();
/* 341 */     second /= 1000L;
/* 342 */     if (second > this.postsCheckCache.getInterval()) {
/* 343 */       return true;
/*     */     }
/* 345 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.fnt.ThemeAct
 * JD-Core Version:    0.6.1
 */