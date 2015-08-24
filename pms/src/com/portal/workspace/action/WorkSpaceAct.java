/*     */ package com.portal.workspace.action;
/*     */ 
/*     */ import com.javapms.basic.security.encoder.PwdEncoder;
/*     */ import com.portal.doccenter.service.ArticleService;
/*     */ import com.portal.doccenter.service.ChannelService;
/*     */ import com.portal.extrafunc.service.CommentService;
/*     */ import com.portal.extrafunc.service.MessageBoardService;
/*     */ import com.portal.extrafunc.service.PostsService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.MemberService;
/*     */ import com.portal.usermgr.service.UserService;
/*     */ import java.util.Properties;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class WorkSpaceAct
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*     */ 
/*     */   @Autowired
/*     */   private MemberService memberService;
/*     */ 
/*     */   @Autowired
/*     */   private ArticleService articleService;
/*     */ 
/*     */   @Autowired
/*     */   private ChannelService channelService;
/*     */ 
/*     */   @Autowired
/*     */   private CommentService commentService;
/*     */ 
/*     */   @Autowired
/*     */   private MessageBoardService messageService;
/*     */ 
/*     */   @Autowired
/*     */   private PostsService postsService;
/*     */ 
/*     */   @Autowired
/*     */   private PwdEncoder pwdEncoder;
/*     */ 
/*     */   @RequiresPermissions({"admin:workspace:index"})
/*     */   @RequestMapping({"/index.do"})
/*     */   public String index(HttpServletRequest request, ModelMap model)
/*     */   {
/*  32 */     Site site = ContextTools.getSite(request);
/*  33 */     User user = ContextTools.getUser(request);
/*  34 */     model.addAttribute("site", site);
/*  35 */     model.addAttribute("user", user);
/*  36 */     return "index";
/*     */   }
/*     */   @RequiresPermissions({"admin:workspace:right"})
/*     */   @RequestMapping({"/right.do"})
/*     */   public String right(HttpServletRequest request, ModelMap model) {
/*  42 */     Site site = ContextTools.getSite(request);
/*  43 */     User user = ContextTools.getUser(request);
/*  44 */     Properties props = System.getProperties();
/*  45 */     Runtime runtime = Runtime.getRuntime();
/*  46 */     long freeMemoery = runtime.freeMemory();
/*  47 */     long totalMemory = runtime.totalMemory();
/*  48 */     long usedMemory = totalMemory - freeMemoery;
/*  49 */     long maxMemory = runtime.maxMemory();
/*  50 */     long useableMemory = maxMemory - totalMemory + freeMemoery;
/*  51 */     int checkarti = this.articleService.getAllArtiCount(site.getId(), true);
/*  52 */     int allarti = this.articleService.getAllArtiCount(site.getId(), false);
/*  53 */     int allchannel = this.channelService.getAllChannelCount(site.getId());
/*  54 */     int alluser = this.userService.getAllUserCount();
/*  55 */     int allcomment = this.commentService.getAllCommentCount(site.getId()).intValue();
/*  56 */     int allmessage = this.messageService.getAllMessageCount(site.getId()).intValue();
/*  57 */     int allpost = this.postsService.getAllPostCount(site.getId()).intValue();
/*  58 */     int nocheckuser = this.memberService.getNoCheckMemberCount();
/*  59 */     int norepmessage = this.messageService.getNoRepMessageCount(site.getId()).intValue();
/*  60 */     model.addAttribute("props", props);
/*  61 */     model.addAttribute("freeMemoery", Long.valueOf(freeMemoery));
/*  62 */     model.addAttribute("totalMemory", Long.valueOf(totalMemory));
/*  63 */     model.addAttribute("usedMemory", Long.valueOf(usedMemory));
/*  64 */     model.addAttribute("maxMemory", Long.valueOf(maxMemory));
/*  65 */     model.addAttribute("useableMemory", Long.valueOf(useableMemory));
/*  66 */     model.addAttribute("site", site);
/*  67 */     model.addAttribute("user", user);
/*  68 */     model.addAttribute("allarti", Integer.valueOf(allarti));
/*  69 */     model.addAttribute("allchannel", Integer.valueOf(allchannel));
/*  70 */     model.addAttribute("alluser", Integer.valueOf(alluser));
/*  71 */     model.addAttribute("allcomment", Integer.valueOf(allcomment));
/*  72 */     model.addAttribute("allmessage", Integer.valueOf(allmessage));
/*  73 */     model.addAttribute("allpost", Integer.valueOf(allpost));
/*  74 */     model.addAttribute("checkarti", Integer.valueOf(checkarti));
/*  75 */     model.addAttribute("nocheckuser", Integer.valueOf(nocheckuser));
/*  76 */     model.addAttribute("norepmessage", Integer.valueOf(norepmessage));
/*  77 */     return "right";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:workspace:left"})
/*     */   @RequestMapping({"/left.do"})
/*     */   public String left(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/*  84 */     response.setHeader("Cache-Control", "no-cache");
/*  85 */     response.setContentType("text/html;charset=UTF-8");
/*  86 */     return "frame/workspace_left";
/*     */   }
/*     */   @RequiresPermissions({"admin:workspace:info"})
/*     */   @RequestMapping({"/workSpace/v_info.do"})
/*     */   public String profileEdit(HttpServletRequest request, ModelMap model) {
/*  92 */     Site site = ContextTools.getSite(request);
/*  93 */     User user = ContextTools.getUser(request);
/*  94 */     model.addAttribute("user", user);
/*  95 */     model.addAttribute("siteId", site.getId());
/*  96 */     return "workSpace/personInfo";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:workspace:infoupdate"})
/*     */   @RequestMapping({"/workSpace/o_info_update.do"})
/*     */   public String profileUpdate(User user, HttpServletRequest request, ModelMap model) {
/* 103 */     this.userService.update(user);
/* 104 */     model.addAttribute("msg", "信息修改成功!");
/* 105 */     return profileEdit(request, model);
/*     */   }
/*     */   @RequiresPermissions({"admin:workspace:passedit"})
/*     */   @RequestMapping({"/workSpace/v_passwordEdit.do"})
/*     */   public String passwordEdit(HttpServletRequest request, ModelMap model) {
/* 111 */     User user = ContextTools.getUser(request);
/* 112 */     model.addAttribute("user", user);
/* 113 */     return "workSpace/editPass";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:workspace:passupdate"})
/*     */   @RequestMapping({"/workSpace/o_pass_update.do"})
/*     */   public String passwordUpdate(String password, String newPwd, HttpServletRequest request, ModelMap model) {
/* 120 */     User user = ContextTools.getUser(request);
/* 121 */     User u = this.userService.findById(user.getId());
/* 122 */     if (u.getPassword().equals(this.pwdEncoder.encodePassword(password))) {
/* 123 */       u.setPassword(this.pwdEncoder.encodePassword(newPwd));
/* 124 */       this.userService.update(u);
/* 125 */       model.addAttribute("msg", "密码修改成功!");
/* 126 */       model.addAttribute("result", "1");
/*     */     } else {
/* 128 */       model.addAttribute("msg", "原密码错误，修改失败!");
/* 129 */       model.addAttribute("result", "0");
/*     */     }
/* 131 */     return passwordEdit(request, model);
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.workspace.action.WorkSpaceAct
 * JD-Core Version:    0.6.1
 */