/*     */ package com.portal.usermgr.action.fnt;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.javapms.basic.utils.ServicesUtils;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.sysmgr.utils.ViewTools;
/*     */ import com.portal.usermgr.entity.Member;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.MemberService;
/*     */ import com.portal.usermgr.service.UserService;
/*     */ import java.io.IOException;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.shiro.SecurityUtils;
/*     */ import org.apache.shiro.authc.IncorrectCredentialsException;
/*     */ import org.apache.shiro.authc.LockedAccountException;
/*     */ import org.apache.shiro.authc.UnknownAccountException;
/*     */ import org.apache.shiro.authc.UsernamePasswordToken;
/*     */ import org.apache.shiro.subject.Subject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class RegisterAct
/*     */ {
/*  42 */   private static final Logger log = LoggerFactory.getLogger(RegisterAct.class);
/*     */   public static final String REGISTER = "member.register";
/*     */ 
/*     */   @Autowired
/*     */   private MemberService memberService;
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*     */ 
/*     */   @RequestMapping(value={"/reg.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String input(Integer groupId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  49 */     Site site = ContextTools.getSite(request);
/*  50 */     if (!site.getRegOpen().booleanValue()) {
/*  51 */       return ViewTools.pageNotFound(response);
/*     */     }
/*  53 */     ViewTools.frontData(request, model, site);
/*  54 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  55 */       "user", "member.register");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/reg.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String submit(User user, Member member, Integer groupId, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IOException
/*     */   {
/*  62 */     Site site = ContextTools.getSite(request);
/*  63 */     if (!site.getRegOpen().booleanValue()) {
/*  64 */       return ViewTools.pageNotFound(response);
/*     */     }
/*  66 */     String msg = vldReg(user, site);
/*  67 */     if (!StringUtils.isBlank(msg)) {
/*  68 */       return ViewTools.showMessage(nextUrl, request, model, msg, Integer.valueOf(0));
/*     */     }
/*  70 */     User u = this.userService.findByUsername(user.getUsername());
/*  71 */     if (u != null) {
/*  72 */       return ViewTools.showMessage(nextUrl, request, model, 
/*  73 */         "该会员已经存在，注册失败!", Integer.valueOf(0));
/*     */     }
/*  75 */     String ip = ServicesUtils.getIpAddr(request);
/*  76 */     if (site.getRegCheck().booleanValue()) {
/*  77 */       member.setStatus(Byte.valueOf((byte)-2));
/*     */     }
/*  79 */     this.memberService.registerMember(user, member, ip, groupId);
/*  80 */     Subject currentUser = SecurityUtils.getSubject();
/*  81 */     UsernamePasswordToken token = new UsernamePasswordToken(
/*  82 */       user.getUsername(), user.getPassword());
/*  83 */     token.setRememberMe(true);
/*     */     try {
/*  85 */       currentUser.login(token);
/*     */     } catch (UnknownAccountException localUnknownAccountException) {
/*     */     } catch (IncorrectCredentialsException localIncorrectCredentialsException) {
/*     */     } catch (LockedAccountException localLockedAccountException) {
/*     */     }
/*  90 */     if (currentUser.isAuthenticated()) {
/*  91 */       User userl = this.userService.findByUsername(user.getUsername());
/*  92 */       if (userl.getAdmin() == null) {
/*  93 */         this.memberService.updateLoginInfo(userl, ip);
/*     */       }
/*     */     }
/*  96 */     log.info("member register success. username={}", user.getUsername());
/*  97 */     return ViewTools.showMessage(nextUrl, request, model, "注册会员成功!", Integer.valueOf(1));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/checkuser.jsp"})
/*     */   public void checkUser(String username, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 103 */     User user = this.userService.findByUsername(username);
/* 104 */     if (user != null) {
/* 105 */       ResponseUtils.renderJson(response, "false");
/* 106 */       return;
/*     */     }
/* 108 */     ResponseUtils.renderJson(response, "true");
/*     */   }
/*     */ 
/*     */   private String vldReg(User user, Site site) {
/* 112 */     if (!StringUtils.isBlank(user.getUsername())) {
/* 113 */       if ((site.getRegMin() != null) && 
/* 114 */         (user.getUsername().length() < site.getRegMin().intValue())) {
/* 115 */         return "用户名长度不能小于" + site.getRegMin() + ",注册失败!";
/*     */       }
/* 117 */       if ((site.getRegMax() != null) && 
/* 118 */         (user.getUsername().length() > site.getRegMax().intValue()))
/* 119 */         return "用户名长度不能大于" + site.getRegMax() + ",注册失败!";
/*     */     }
/*     */     else {
/* 122 */       return "用户名不能为空，注册失败!";
/*     */     }
/* 124 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.action.fnt.RegisterAct
 * JD-Core Version:    0.6.1
 */