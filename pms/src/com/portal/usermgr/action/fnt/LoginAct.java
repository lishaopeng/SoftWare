/*     */ package com.portal.usermgr.action.fnt;
/*     */ 
/*     */ import com.javapms.basic.security.encoder.PwdEncoder;
/*     */ import com.javapms.basic.utils.DateUtils;
/*     */ import com.javapms.basic.utils.ServicesUtils;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.sysmgr.utils.ViewTools;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.AdminService;
/*     */ import com.portal.usermgr.service.MemberService;
/*     */ import com.portal.usermgr.service.UserService;
/*     */ import java.util.Date;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.shiro.SecurityUtils;
/*     */ import org.apache.shiro.authc.IncorrectCredentialsException;
/*     */ import org.apache.shiro.authc.LockedAccountException;
/*     */ import org.apache.shiro.authc.UnknownAccountException;
/*     */ import org.apache.shiro.authc.UsernamePasswordToken;
/*     */ import org.apache.shiro.subject.Subject;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class LoginAct
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*     */ 
/*     */   @Autowired
/*     */   private MemberService memberService;
/*     */ 
/*     */   @Autowired
/*     */   private AdminService adminService;
/*     */ 
/*     */   @Autowired
/*     */   private PwdEncoder pwdEncoder;
/*     */ 
/*     */   @RequestMapping(value={"/login.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String input(HttpServletRequest request, ModelMap model)
/*     */   {
/*  36 */     Site site = ContextTools.getSite(request);
/*  37 */     User user = ContextTools.getUser(request);
/*  38 */     if (user != null) {
/*  39 */       return "redirect:/";
/*     */     }
/*  41 */     ViewTools.frontData(request, model, site);
/*  42 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  43 */       "user", "member.login");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/login.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String submit(String username, String password, String nextUrl, HttpServletRequest request, ModelMap model)
/*     */   {
/*  49 */     Site site = ContextTools.getSite(request);
/*  50 */     Subject currentUser = SecurityUtils.getSubject();
/*  51 */     UsernamePasswordToken token = new UsernamePasswordToken(username, 
/*  52 */       this.pwdEncoder.encodePassword(password));
/*  53 */     token.setRememberMe(true);
/*     */     try {
/*  55 */       currentUser.login(token);
/*     */     } catch (UnknownAccountException localUnknownAccountException) {
/*     */     } catch (IncorrectCredentialsException localIncorrectCredentialsException) {
/*     */     } catch (LockedAccountException localLockedAccountException) {
/*     */     }
/*  60 */     User user = this.userService.findByUsername(username);
/*  61 */     String msg = checkLogin(site, user);
/*  62 */     if (!StringUtils.isBlank(msg)) {
/*  63 */       model.addAttribute("msg", msg);
/*  64 */       ViewTools.frontData(request, model, site);
/*  65 */       return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  66 */         "user", "member.login");
/*     */     }
/*  68 */     if (currentUser.isAuthenticated()) {
/*  69 */       if (!user.getStatus().equals(Byte.valueOf((byte)0))) {
/*  70 */         return ViewTools.showMessage(nextUrl, request, model, 
/*  71 */           "该账号已经被禁止登录!", Integer.valueOf(0));
/*     */       }
/*  73 */       String ip = ServicesUtils.getIpAddr(request);
/*  74 */       if (user.getAdmin() == null)
/*  75 */         this.memberService.updateLoginInfo(user, ip);
/*     */       else {
/*  77 */         this.adminService.updateLoginInfo(user, ip);
/*     */       }
/*  79 */       if (!StringUtils.isBlank(nextUrl)) {
/*  80 */         return "redirect:" + ViewTools.showNextUrl(nextUrl, site);
/*     */       }
/*  82 */       return "redirect:/";
/*     */     }
/*  84 */     if (site.getNeedCheck()) {
/*  85 */       user = this.userService.updateFailTime(user);
/*  86 */       int i = site.getLoginCount().intValue() - user.getFailCount().intValue();
/*  87 */       model.addAttribute("msg", "用户名或密码错误,登录失败,您还有" + i + "次机会!");
/*  88 */       ViewTools.frontData(request, model, site);
/*  89 */       return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  90 */         "user", "member.login");
/*     */     }
/*  92 */     model.addAttribute("msg", "用户名或者密码错误");
/*  93 */     if (!StringUtils.isBlank(nextUrl)) {
/*  94 */       model.addAttribute("url", nextUrl);
/*     */     }
/*  96 */     ViewTools.frontData(request, model, site);
/*  97 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  98 */       "user", "member.login");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/logout.jsp"})
/*     */   public String logout(String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 106 */     Site site = ContextTools.getSite(request);
/* 107 */     Subject currentUser = SecurityUtils.getSubject();
/* 108 */     currentUser.logout();
/* 109 */     if (!StringUtils.isBlank(nextUrl)) {
/* 110 */       return "redirect:" + ViewTools.showNextUrl(nextUrl, site);
/*     */     }
/* 112 */     ViewTools.frontData(request, model, site);
/* 113 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 114 */       "user", "member.login");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/jslogin.jsp"})
/*     */   public String jslogin(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 120 */     Site site = ContextTools.getSite(request);
/* 121 */     ViewTools.frontData(request, model, site);
/* 122 */     response.setHeader("Cache-Control", "no-cache");
/* 123 */     response.setContentType("text/json;charset=UTF-8");
/* 124 */     return ViewTools.getTplPath(null, site.getSolutionPath(), 
/* 125 */       "user", "jslogin");
/*     */   }
/*     */ 
/*     */   private String checkLogin(Site site, User user) {
/* 129 */     if ((user != null) && 
/* 130 */       (site.getNeedCheck()) && 
/* 131 */       (site.getLoginCount().intValue() <= user.getFailCount().intValue()) && 
/* 132 */       (user.getLastFailTime() != null) && 
/* 133 */       (user.getLastFailTime().after(DateUtils.getToday()))) {
/* 134 */       return "您登录失败次数超过" + site.getLoginCount() + 
/* 135 */         "次，今日禁止登录!";
/*     */     }
/*     */ 
/* 141 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.action.fnt.LoginAct
 * JD-Core Version:    0.6.1
 */