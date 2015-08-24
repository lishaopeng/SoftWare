/*     */ package com.portal.usermgr.action.fnt;
/*     */ 
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.sysmgr.utils.ViewTools;
/*     */ import com.portal.usermgr.entity.SiteMessage;
/*     */ import com.portal.usermgr.entity.SiteMessageStatus;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.SiteMessageService;
/*     */ import com.portal.usermgr.service.SiteMessageStatusService;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/*     */ 
/*     */ @Controller
/*     */ public class SiteMessageAct
/*     */ {
/*     */   public static final String SEND_LIST = "tpl.sendList";
/*     */   public static final String RECEIVE_LIST = "tpl.receiveList";
/*     */   public static final String TRASH_LIST = "tpl.trashList";
/*     */   public static final String MESSAGE_VIEW = "tpl.messageView";
/*     */ 
/*     */   @Autowired
/*     */   private SiteMessageService messageService;
/*     */ 
/*     */   @Autowired
/*     */   private SiteMessageStatusService statusService;
/*     */ 
/*     */   @RequestMapping(value={"/message/sendlist.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String sendList(HttpServletRequest request, ModelMap model)
/*     */   {
/*  34 */     return sendListpage(Integer.valueOf(1), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/message/sendlist_{page:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String sendListpage(@PathVariable Integer page, HttpServletRequest request, ModelMap model)
/*     */   {
/*  40 */     Site site = ContextTools.getSite(request);
/*  41 */     User user = ContextTools.getUser(request);
/*  42 */     ViewTools.frontData(request, model, site);
/*  43 */     if (user == null) {
/*  44 */       return ViewTools.showLogin(request, model, null);
/*     */     }
/*  46 */     ViewTools.frontPageData(request, model, page);
/*  47 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  48 */       "user", "tpl.sendList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/message/receivelist.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String receiveList(HttpServletRequest request, ModelMap model) {
/*  53 */     return receiveListpage(Integer.valueOf(1), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/message/receivelist_{page:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String receiveListpage(@PathVariable Integer page, HttpServletRequest request, ModelMap model)
/*     */   {
/*  59 */     Site site = ContextTools.getSite(request);
/*  60 */     User user = ContextTools.getUser(request);
/*  61 */     ViewTools.frontData(request, model, site);
/*  62 */     if (user == null) {
/*  63 */       return ViewTools.showLogin(request, model, null);
/*     */     }
/*  65 */     ViewTools.frontPageData(request, model, page);
/*  66 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  67 */       "user", "tpl.receiveList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/message/trashlist.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String trashList(HttpServletRequest request, ModelMap model) {
/*  72 */     return trashListpage(Integer.valueOf(1), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/message/trashlist_{page:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String trashListpage(@PathVariable Integer page, HttpServletRequest request, ModelMap model)
/*     */   {
/*  78 */     Site site = ContextTools.getSite(request);
/*  79 */     User user = ContextTools.getUser(request);
/*  80 */     ViewTools.frontData(request, model, site);
/*  81 */     if (user == null) {
/*  82 */       return ViewTools.showLogin(request, model, null);
/*     */     }
/*  84 */     ViewTools.frontPageData(request, model, page);
/*  85 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  86 */       "user", "tpl.trashList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/message/view-{mId:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String messageView(@PathVariable Integer mId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  93 */     Site site = ContextTools.getSite(request);
/*  94 */     User user = ContextTools.getUser(request);
/*  95 */     ViewTools.frontData(request, model, site);
/*  96 */     if (user == null) {
/*  97 */       return ViewTools.showLogin(request, model, null);
/*     */     }
/*  99 */     if (mId == null) {
/* 100 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 102 */     SiteMessage message = this.messageService.findById(mId);
/* 103 */     if (message == null) {
/* 104 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 106 */     model.addAttribute("message", message);
/* 107 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 108 */       "user", "tpl.messageView");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/message/send.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String messageSubmit(SiteMessage bean, Integer[] receiveIds, Integer replyId, HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes ra)
/*     */   {
/* 115 */     Site site = ContextTools.getSite(request);
/* 116 */     User user = ContextTools.getUser(request);
/* 117 */     ViewTools.frontData(request, model, site);
/* 118 */     if (user == null) {
/* 119 */       return ViewTools.showLogin(request, model, null);
/*     */     }
/* 121 */     if ((receiveIds == null) || (receiveIds.length == 0)) {
/* 122 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 124 */     this.messageService.save(bean, user.getId(), receiveIds);
/* 125 */     ra.addFlashAttribute("msg", "信息发送成功");
/* 126 */     return "redirect:sendlist.jsp";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/message/delete-{mId:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String messageDelete(@PathVariable Integer mId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 133 */     Site site = ContextTools.getSite(request);
/* 134 */     User user = ContextTools.getUser(request);
/* 135 */     ViewTools.frontData(request, model, site);
/* 136 */     if (user == null) {
/* 137 */       return ViewTools.showLogin(request, model, null);
/*     */     }
/* 139 */     if (mId == null) {
/* 140 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 142 */     SiteMessage message = this.messageService.findById(mId);
/* 143 */     if (message == null) {
/* 144 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 146 */     if (message.getSend().equals(user)) {
/* 147 */       this.messageService.deleteById(mId, user);
/* 148 */       return "redirect:sendlist.jsp";
/*     */     }
/* 150 */     SiteMessageStatus messageStatus = this.statusService.findByRecive(
/* 151 */       user.getId(), mId);
/* 152 */     if (messageStatus != null) {
/* 153 */       this.messageService.deleteById(mId, user);
/* 154 */       return "redirect:receivelist.jsp";
/*     */     }
/* 156 */     return ViewTools.pageNotFound(response);
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.action.fnt.SiteMessageAct
 * JD-Core Version:    0.6.1
 */