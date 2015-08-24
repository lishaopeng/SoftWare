/*     */ package com.portal.extrafunc.action.fnt;
/*     */ 
/*     */ import com.portal.extrafunc.entity.Forum;
/*     */ import com.portal.extrafunc.entity.Posts;
/*     */ import com.portal.extrafunc.entity.Theme;
/*     */ import com.portal.extrafunc.entity.UserForum;
/*     */ import com.portal.extrafunc.service.PostsService;
/*     */ import com.portal.extrafunc.service.ThemeService;
/*     */ import com.portal.extrafunc.service.UserForumService;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.sysmgr.utils.ViewTools;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import java.util.Date;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class OperateAct
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private ThemeService themeService;
/*     */ 
/*     */   @Autowired
/*     */   private PostsService postsService;
/*     */ 
/*     */   @Autowired
/*     */   private UserForumService userForumService;
/*     */ 
/*     */   @RequestMapping(value={"/topTheme.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String topTheme(Integer[] themeId, Integer topLevel, Date topTime, String reason, Integer pn, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  38 */     if (themeId != null) {
/*  39 */       User user = ContextTools.getUser(request);
/*  40 */       Theme theme = null;
/*  41 */       for (Integer tid : themeId) {
/*  42 */         theme = this.themeService.findById(tid);
/*  43 */         if (theme == null) {
/*  44 */           return ViewTools.pageNotFound(response);
/*     */         }
/*  46 */         if (user == null) {
/*  47 */           return ViewTools.showLogin(request, model, 
/*  48 */             "必须登录才可以进行置顶操作!");
/*     */         }
/*  50 */         if (user.getAdmin() == null) {
/*  51 */           return ViewTools.pageNotFound(response);
/*     */         }
/*  53 */         this.themeService.topTheme(tid, user.getId(), topLevel, topTime, 
/*  54 */           reason);
/*     */       }
/*  56 */       if ((pn != null) && (pn.intValue() > 1)) {
/*  57 */         return "redirect:" + theme.getForum().getUrl(pn);
/*     */       }
/*  59 */       return "redirect:" + theme.getForum().getUrl();
/*     */     }
/*  61 */     return ViewTools.pageNotFound(response);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/essenaTheme.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String essenaTheme(Integer[] themeId, Date essenaTime, String reason, Integer pn, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  68 */     if (themeId != null) {
/*  69 */       User user = ContextTools.getUser(request);
/*  70 */       Theme theme = null;
/*  71 */       for (Integer tid : themeId) {
/*  72 */         theme = this.themeService.findById(tid);
/*  73 */         if (theme == null) {
/*  74 */           return ViewTools.pageNotFound(response);
/*     */         }
/*  76 */         if (user == null) {
/*  77 */           return ViewTools.showLogin(request, model, 
/*  78 */             "必须登录才可以进行精华操作!");
/*     */         }
/*  80 */         if (user.getAdmin() == null) {
/*  81 */           return ViewTools.pageNotFound(response);
/*     */         }
/*  83 */         this.themeService.essenaTheme(tid, user.getId(), essenaTime, reason);
/*     */       }
/*  85 */       if ((pn != null) && (pn.intValue() > 1)) {
/*  86 */         return "redirect:" + theme.getForum().getUrl(pn);
/*     */       }
/*  88 */       return "redirect:" + theme.getForum().getUrl();
/*     */     }
/*  90 */     return ViewTools.pageNotFound(response);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/lightTheme.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String lightTheme(Integer[] themeId, String color, Boolean bold, Boolean italic, Date lightTime, String reason, Integer pn, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  98 */     if (themeId != null) {
/*  99 */       User user = ContextTools.getUser(request);
/* 100 */       Theme theme = null;
/* 101 */       for (Integer tid : themeId) {
/* 102 */         theme = this.themeService.findById(tid);
/* 103 */         if (theme == null) {
/* 104 */           return ViewTools.pageNotFound(response);
/*     */         }
/* 106 */         if (user == null) {
/* 107 */           return ViewTools.showLogin(request, model, 
/* 108 */             "必须登录才可以进行高亮操作!");
/*     */         }
/* 110 */         if (user.getAdmin() == null) {
/* 111 */           return ViewTools.pageNotFound(response);
/*     */         }
/* 113 */         this.themeService.lightTheme(tid, user.getId(), color, bold, italic, 
/* 114 */           lightTime, reason);
/*     */       }
/* 116 */       if ((pn != null) && (pn.intValue() > 1)) {
/* 117 */         return "redirect:" + theme.getForum().getUrl(pn);
/*     */       }
/* 119 */       return "redirect:" + theme.getForum().getUrl();
/*     */     }
/* 121 */     return ViewTools.pageNotFound(response);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/lockTheme.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String lockTheme(Integer[] themeId, Date lockTime, String reason, Integer pn, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 128 */     if (themeId != null) {
/* 129 */       User user = ContextTools.getUser(request);
/* 130 */       Theme theme = null;
/* 131 */       for (Integer tid : themeId) {
/* 132 */         theme = this.themeService.findById(tid);
/* 133 */         if (theme == null) {
/* 134 */           return ViewTools.pageNotFound(response);
/*     */         }
/* 136 */         if (user == null) {
/* 137 */           return ViewTools.showLogin(request, model, 
/* 138 */             "必须登录才可以进行锁定操作!");
/*     */         }
/* 140 */         if (user.getAdmin() == null) {
/* 141 */           return ViewTools.pageNotFound(response);
/*     */         }
/* 143 */         this.themeService.lockTheme(tid, user.getId(), lockTime, reason);
/*     */       }
/* 145 */       if ((pn != null) && (pn.intValue() > 1)) {
/* 146 */         return "redirect:" + theme.getForum().getUrl(pn);
/*     */       }
/* 148 */       return "redirect:" + theme.getForum().getUrl();
/*     */     }
/* 150 */     return ViewTools.pageNotFound(response);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/moveTheme.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String moveTheme(Integer[] themeId, Integer forumId, String reason, Integer pn, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 157 */     if (themeId != null) {
/* 158 */       User user = ContextTools.getUser(request);
/* 159 */       Theme theme = null;
/* 160 */       for (Integer tid : themeId) {
/* 161 */         theme = this.themeService.findById(tid);
/* 162 */         if (theme == null) {
/* 163 */           return ViewTools.pageNotFound(response);
/*     */         }
/* 165 */         if (user == null) {
/* 166 */           return ViewTools.showLogin(request, model, 
/* 167 */             "必须登录才可以进行移动操作!");
/*     */         }
/* 169 */         if (user.getAdmin() == null) {
/* 170 */           return ViewTools.pageNotFound(response);
/*     */         }
/* 172 */         this.themeService.moveTheme(tid, user.getId(), forumId, reason);
/*     */       }
/* 174 */       if ((pn != null) && (pn.intValue() > 1)) {
/* 175 */         return "redirect:" + theme.getForum().getUrl(pn);
/*     */       }
/* 177 */       return "redirect:" + theme.getForum().getUrl();
/*     */     }
/* 179 */     return ViewTools.pageNotFound(response);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/shieldTheme.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String shieldTheme(Integer[] themeId, String reason, Integer pn, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 186 */     if (themeId != null) {
/* 187 */       User user = ContextTools.getUser(request);
/* 188 */       Theme theme = null;
/* 189 */       for (Integer tid : themeId) {
/* 190 */         theme = this.themeService.findById(tid);
/* 191 */         if (theme == null) {
/* 192 */           return ViewTools.pageNotFound(response);
/*     */         }
/* 194 */         if (user == null) {
/* 195 */           return ViewTools.showLogin(request, model, 
/* 196 */             "必须登录才可以进行屏蔽操作!");
/*     */         }
/* 198 */         if (user.getAdmin() == null) {
/* 199 */           return ViewTools.pageNotFound(response);
/*     */         }
/* 201 */         this.postsService.shieldTheme(tid, user.getId(), reason);
/*     */       }
/* 203 */       if ((pn != null) && (pn.intValue() > 1)) {
/* 204 */         return "redirect:" + theme.getForum().getUrl(pn);
/*     */       }
/* 206 */       return "redirect:" + theme.getForum().getUrl();
/*     */     }
/* 208 */     return ViewTools.pageNotFound(response);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/shieldPosts.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String shieldPosts(Integer postsId, String reason, Integer pn, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 215 */     Posts posts = this.postsService.findById(postsId);
/* 216 */     User user = ContextTools.getUser(request);
/* 217 */     if (posts == null) {
/* 218 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 220 */     if (user == null) {
/* 221 */       return ViewTools.showLogin(request, model, "必须登录才可以进行屏蔽操作!");
/*     */     }
/* 223 */     if (user.getAdmin() == null) {
/* 224 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 226 */     if (posts.getFloor().intValue() == 1)
/* 227 */       this.postsService.shieldTheme(posts.getTheme().getId(), user.getId(), 
/* 228 */         reason);
/*     */     else {
/* 230 */       this.postsService.shieldPosts(postsId, user.getId(), reason);
/*     */     }
/* 232 */     if ((pn != null) && (pn.intValue() > 1)) {
/* 233 */       return "redirect:" + posts.getTheme().getUrl(pn);
/*     */     }
/* 235 */     return "redirect:" + posts.getTheme().getUrl();
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/deleteTheme.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String deleteTheme(Integer[] themeId, String reason, Integer pn, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 242 */     if (themeId != null) {
/* 243 */       User user = ContextTools.getUser(request);
/* 244 */       Theme theme = null;
/* 245 */       for (Integer tid : themeId) {
/* 246 */         theme = this.themeService.findById(tid);
/* 247 */         if (theme == null) {
/* 248 */           return ViewTools.pageNotFound(response);
/*     */         }
/* 250 */         if (user == null) {
/* 251 */           return ViewTools.showLogin(request, model, 
/* 252 */             "必须登录才可以进行删除操作!");
/*     */         }
/* 254 */         if (user.getAdmin() == null) {
/* 255 */           return ViewTools.pageNotFound(response);
/*     */         }
/* 257 */         this.postsService.deleteTheme(tid, user.getId(), reason);
/*     */       }
/* 259 */       if ((pn != null) && (pn.intValue() > 1)) {
/* 260 */         return "redirect:" + theme.getForum().getUrl(pn);
/*     */       }
/* 262 */       return "redirect:" + theme.getForum().getUrl();
/*     */     }
/* 264 */     return ViewTools.pageNotFound(response);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/shieldUser.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String shieldUser(Integer userId, Integer themeId, Date shieldTime, String reason, Integer pn, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 271 */     UserForum uf = this.userForumService.findById(userId);
/* 272 */     User user = ContextTools.getUser(request);
/* 273 */     if (uf == null) {
/* 274 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 276 */     if (user == null) {
/* 277 */       return ViewTools.showLogin(request, model, "必须登录才可以进行屏蔽会员操作!");
/*     */     }
/* 279 */     if (user.getAdmin() == null) {
/* 280 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 282 */     Theme theme = this.themeService.findById(themeId);
/* 283 */     if (theme == null) {
/* 284 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 286 */     this.userForumService.shieldUserForum(userId, shieldTime);
/* 287 */     if ((pn != null) && (pn.intValue() > 1)) {
/* 288 */       return "redirect:" + theme.getUrl(pn);
/*     */     }
/* 290 */     return "redirect:" + theme.getUrl();
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.fnt.OperateAct
 * JD-Core Version:    0.6.1
 */