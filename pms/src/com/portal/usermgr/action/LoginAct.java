/*     */ package com.portal.usermgr.action;
/*     */ 
/*     */ import com.javapms.basic.security.encoder.PwdEncoder;
/*     */ import com.javapms.basic.utils.DateUtils;
/*     */ import com.javapms.basic.utils.ServicesUtils;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.sysmgr.utils.ViewTools;
/*     */ import com.portal.usermgr.entity.Admin;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.AdminService;
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
/*     */ import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/*     */ 
/*     */ @Controller
/*     */ public class LoginAct
/*     */ {
/*     */   public static final String COOKIE_ERROR_REMAINING = "_error_remaining";
/*     */ 
/*     */   @Autowired
/*     */   private PwdEncoder pwdEncoder;
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*     */ 
/*     */   @Autowired
/*     */   private AdminService adminService;
/*     */ 
/*     */   @RequestMapping(value={"/login.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  38 */     return "login";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/login.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String submit(String username, String password, String captcha, String nextUrl, String message, HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes ra)
/*     */   {
/*  45 */     Subject currentUser = SecurityUtils.getSubject();
/*  46 */     UsernamePasswordToken token = new UsernamePasswordToken(username, 
/*  47 */       this.pwdEncoder.encodePassword(password));
/*  48 */     token.setRememberMe(true);
/*     */     try {
/*  50 */       currentUser.login(token);
/*     */     } catch (UnknownAccountException localUnknownAccountException) {
/*     */     } catch (IncorrectCredentialsException localIncorrectCredentialsException) {
/*     */     } catch (LockedAccountException localLockedAccountException) {
/*     */     }
/*  55 */     Site site = ContextTools.getSite(request);
/*  56 */     User user = this.userService.findByUsername(username);
/*  57 */     String msg = checkLogin(site, user);
/*  58 */     if (!StringUtils.isBlank(msg)) {
/*  59 */       ra.addFlashAttribute("msg", msg);
/*  60 */       return "redirect:login.do";
/*     */     }
/*  62 */     if ((currentUser.isAuthenticated()) && (user.getAdmin() != null)) {
/*  63 */       if (user.getAdmin().getRole(site.getId()) == null) {
/*  64 */         currentUser.logout();
/*  65 */         ra.addFlashAttribute("msg", "该用户没有权限，禁止登陆!");
/*  66 */         return "redirect:login.do";
/*     */       }
/*  68 */       if (user.getAdmin().getDepart(site.getId()) == null) {
/*  69 */         currentUser.logout();
/*  70 */         ra.addFlashAttribute("msg", "该用户没有分配部门，禁止登陆,请联系管理员!");
/*  71 */         return "redirect:login.do";
/*     */       }
/*  73 */       if (!user.getStatus().equals(Byte.valueOf((byte)0))) {
/*  74 */         return ViewTools.showMessage(nextUrl, request, model, 
/*  75 */           "该账号已经被禁止登录!", Integer.valueOf(0));
/*     */       }
/*  77 */       String ip = ServicesUtils.getIpAddr(request);
/*  78 */       this.adminService.updateLoginInfo(user, ip);
/*  79 */       if (!StringUtils.isBlank(nextUrl)) {
/*  80 */         return "redirect:" + nextUrl;
/*     */       }
/*  82 */       return "redirect:index.do";
/*     */     }
/*  84 */     if ((site.getNeedCheck()) && 
/*  85 */       (!currentUser.isAuthenticated())) {
/*  86 */       user = this.userService.updateFailTime(user);
/*  87 */       int i = site.getLoginCount().intValue() - user.getFailCount().intValue();
/*  88 */       ra.addFlashAttribute("msg", "用户名或密码错误,登录失败,您还有" + i + 
/*  89 */         "次机会!");
/*  90 */       return "redirect:login.do";
/*     */     }
/*     */ 
/*  93 */     ra.addFlashAttribute("msg", "用户名或密码错误!");
/*  94 */     return "redirect:login.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/logout.do"})
/*     */   public String logout(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 101 */     Subject currentUser = SecurityUtils.getSubject();
/* 102 */     currentUser.logout();
/* 103 */     return "login";
/*     */   }
/*     */ 
/*     */   private String checkLogin(Site site, User user) {
/* 107 */     if ((user != null) && 
/* 108 */       (site.getNeedCheck()) && 
/* 109 */       (site.getLoginCount().intValue() <= user.getFailCount().intValue()) && 
/* 110 */       (user.getLastFailTime() != null) && 
/* 111 */       (user.getLastFailTime().after(DateUtils.getToday()))) {
/* 112 */       return "您登录失败次数超过" + site.getLoginCount() + 
/* 113 */         "次，今日禁止登录!";
/*     */     }
/*     */ 
/* 119 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.action.LoginAct
 * JD-Core Version:    0.6.1
 */