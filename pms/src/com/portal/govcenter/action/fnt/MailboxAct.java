/*    */ package com.portal.govcenter.action.fnt;
/*    */ 
/*    */ import com.portal.govcenter.entity.Mailbox;
/*    */ import com.portal.govcenter.entity.MailboxExt;
/*    */ import com.portal.govcenter.service.MailboxService;
/*    */ import com.portal.govcenter.service.MailboxTypeService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import com.portal.sysmgr.utils.ViewTools;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/*    */ 
/*    */ @Controller
/*    */ public class MailboxAct
/*    */ {
/*    */   public static final String MAILBOX_INPUT = "tpl.mailboxInput";
/*    */   public static final String MAILBOX_LIST = "mailbox_list";
/*    */ 
/*    */   @Autowired
/*    */   private MailboxTypeService typeService;
/*    */ 
/*    */   @Autowired
/*    */   private MailboxService mailboxService;
/*    */ 
/*    */   @RequestMapping(value={"/mailbox/input.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 43 */     Site site = ContextTools.getSite(request);
/* 44 */     List list = this.typeService.getList(site.getId());
/* 45 */     model.addAttribute("typeList", list);
/* 46 */     ViewTools.frontData(request, model, site);
/* 47 */     return ViewTools.getTplPath(null, site.getSolutionPath(), 
/* 48 */       "govcenter/interactive", "input");
/*    */   }
/*    */ 
/*    */   @RequestMapping(value={"/mailbox/submit.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*    */   public String submit(Mailbox bean, MailboxExt ext, Integer departId, Integer typeId, HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra)
/*    */   {
/* 55 */     Site site = ContextTools.getSite(request);
/* 56 */     this.mailboxService.save(bean, ext, site, departId, typeId);
/* 57 */     ra.addFlashAttribute("msg", "很感谢您的意见，我们会尽快做出处理！");
/* 58 */     return "redirect:input.jsp";
/*    */   }
/*    */ 
/*    */   @RequestMapping(value={"/mailbox/list.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String mailboxList(String name, Integer typeId, Integer pageNo, Integer count, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 65 */     Site site = ContextTools.getSite(request);
/* 66 */     Page page = this.mailboxService.getPageByTag(name, site.getId(), 
/* 67 */       null, typeId, pageNo.intValue(), count.intValue());
/* 68 */     model.addAttribute("page", page);
/* 69 */     model.addAttribute("name", name);
/* 70 */     model.addAttribute("typeId", typeId);
/* 71 */     ViewTools.frontData(request, model, site);
/* 72 */     response.setHeader("Cache-Control", "no-cache");
/* 73 */     response.setContentType("text/json;charset=UTF-8");
/* 74 */     return ViewTools.getTplPath(null, site.getSolutionPath(), 
/* 75 */       "govcenter/interactive", "mailbox_list");
/*    */   }
/*    */ 
/*    */   @RequestMapping(value={"/mailbox/detail-{id:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String mailboxDetail(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 82 */     Site site = ContextTools.getSite(request);
/* 83 */     Mailbox mailbox = this.mailboxService.findById(id);
/* 84 */     model.addAttribute("mailbox", mailbox);
/* 85 */     ViewTools.frontData(request, model, site);
/* 86 */     return ViewTools.getTplPath(null, site.getSolutionPath(), 
/* 87 */       "govcenter/interactive", "detail");
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.action.fnt.MailboxAct
 * JD-Core Version:    0.6.1
 */