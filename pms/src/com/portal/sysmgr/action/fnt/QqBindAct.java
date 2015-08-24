/*     */ package com.portal.sysmgr.action.fnt;
/*     */ 
/*     */ import com.javapms.basic.security.encoder.PwdEncoder;
/*     */ import com.javapms.basic.utils.ServicesUtils;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.sysmgr.utils.ViewTools;
/*     */ import com.portal.usermgr.entity.Member;
/*     */ import com.portal.usermgr.entity.ThirdpartyBind;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.AdminService;
/*     */ import com.portal.usermgr.service.MemberService;
/*     */ import com.portal.usermgr.service.ThirdpartyBindService;
/*     */ import com.portal.usermgr.service.UserService;
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
/*     */ public class QqBindAct
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private ThirdpartyBindService bindService;
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
/*     */   @RequestMapping({"/qqback.jsp"})
/*     */   public String backurl(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  38 */     Site site = ContextTools.getSite(request);
/*  39 */     ViewTools.frontData(request, model, site);
/*  40 */     return ViewTools.getTplPath(null, site.getSolutionPath(), 
/*  41 */       "user", "qqback");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/qqyz.jsp"})
/*     */   public String yz(String openid, String openkey, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  48 */     Site site = ContextTools.getSite(request);
/*  49 */     ThirdpartyBind bind = this.bindService.findByOpenId(openid, 
/*  50 */       "qq");
/*  51 */     ViewTools.frontData(request, model, site);
/*  52 */     if (bind == null) {
/*  53 */       model.addAttribute("openid", openid);
/*  54 */       model.addAttribute("openkey", openkey);
/*  55 */       return ViewTools.getTplPath(null, site.getSolutionPath(), 
/*  56 */         "user", "qqbind");
/*     */     }
/*  58 */     User user = this.userService.findByUsername(bind.getUsername());
/*  59 */     if (user == null) {
/*  60 */       this.bindService.deleteById(bind.getId());
/*  61 */       return ViewTools.getTplPath(null, site.getSolutionPath(), 
/*  62 */         "user", "qqbind");
/*     */     }
/*  64 */     if (!user.getStatus().equals(Byte.valueOf((byte)0))) {
/*  65 */       return ViewTools.showMessage(nextUrl, request, model, 
/*  66 */         "该账号已经被禁止登录!", Integer.valueOf(0));
/*     */     }
/*  68 */     bind.setOpenkey(openkey);
/*  69 */     Subject currentUser = SecurityUtils.getSubject();
/*  70 */     UsernamePasswordToken token = new UsernamePasswordToken(
/*  71 */       user.getUsername(), user.getPassword());
/*  72 */     token.setRememberMe(true);
/*     */     try {
/*  74 */       currentUser.login(token);
/*     */     } catch (UnknownAccountException localUnknownAccountException) {
/*     */     } catch (IncorrectCredentialsException localIncorrectCredentialsException) {
/*     */     } catch (LockedAccountException localLockedAccountException) {
/*     */     }
/*  79 */     if (currentUser.isAuthenticated()) {
/*  80 */       String ip = ServicesUtils.getIpAddr(request);
/*  81 */       if (user.getAdmin() == null)
/*  82 */         this.memberService.updateLoginInfo(user, ip);
/*     */       else {
/*  84 */         this.adminService.updateLoginInfo(user, ip);
/*     */       }
/*  86 */       this.bindService.update(bind);
/*     */     }
/*  88 */     if (!StringUtils.isBlank(nextUrl)) {
/*  89 */       return "redirect:" + ViewTools.showNextUrl(nextUrl, site);
/*     */     }
/*  91 */     return "redirect:/";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/qqbind.jsp"})
/*     */   public String bind(User user, Member member, String openid, String openkey, Integer groupId, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  99 */     Site site = ContextTools.getSite(request);
/* 100 */     ViewTools.frontData(request, model, site);
/* 101 */     if (StringUtils.isBlank(user.getUsername())) {
/* 102 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 104 */     if (StringUtils.isBlank(openid)) {
/* 105 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 107 */     String ip = ServicesUtils.getIpAddr(request);
/* 108 */     User u = this.userService.findByUsername(user.getUsername());
/* 109 */     if (groupId != null) {
/* 110 */       if (u != null) {
/* 111 */         return ViewTools.showMessage(nextUrl, request, model, 
/* 112 */           "该会员已经存在，注册失败!", Integer.valueOf(0));
/*     */       }
/* 114 */       this.memberService.registerMember(user, member, ip, groupId);
/*     */     } else {
/* 116 */       if (u == null) {
/* 117 */         return ViewTools.showMessage(nextUrl, request, model, 
/* 118 */           "该会员不存在，绑定失败!", Integer.valueOf(0));
/*     */       }
/* 120 */       if (!u.getPassword().equals(
/* 121 */         this.pwdEncoder.encodePassword(user.getPassword()))) {
/* 122 */         return ViewTools.showMessage(nextUrl, request, model, 
/* 123 */           "密码错误，绑定失败!", Integer.valueOf(0));
/*     */       }
/* 125 */       this.bindService.save(user.getUsername(), openid, openkey, 
/* 126 */         "qq");
/*     */     }
/* 128 */     Subject currentUser = SecurityUtils.getSubject();
/* 129 */     UsernamePasswordToken token = new UsernamePasswordToken(
/* 130 */       user.getUsername(), this.pwdEncoder.encodePassword(user
/* 131 */       .getPassword()));
/* 132 */     token.setRememberMe(true);
/*     */     try {
/* 134 */       currentUser.login(token);
/*     */     } catch (UnknownAccountException localUnknownAccountException) {
/*     */     } catch (IncorrectCredentialsException localIncorrectCredentialsException) {
/*     */     } catch (LockedAccountException localLockedAccountException) {
/*     */     }
/* 139 */     if (currentUser.isAuthenticated()) {
/* 140 */       User userl = this.userService.findByUsername(user.getUsername());
/* 141 */       if (userl.getAdmin() == null)
/* 142 */         this.memberService.updateLoginInfo(userl, ip);
/*     */       else {
/* 144 */         this.adminService.updateLoginInfo(userl, ip);
/*     */       }
/*     */     }
/* 147 */     if (!StringUtils.isBlank(nextUrl)) {
/* 148 */       return "redirect:" + ViewTools.showNextUrl(nextUrl, site);
/*     */     }
/* 150 */     return "redirect:/";
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.fnt.QqBindAct
 * JD-Core Version:    0.6.1
 */