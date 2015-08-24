/*     */ package com.portal.extrafunc.action.fnt;
/*     */ 
/*     */ import com.javapms.basic.utils.ServicesUtils;
/*     */ import com.portal.extrafunc.entity.QuestionDetail;
/*     */ import com.portal.extrafunc.entity.Questionnaire;
/*     */ import com.portal.extrafunc.service.QuestionDetailService;
/*     */ import com.portal.extrafunc.service.QuestionnaireService;
/*     */ import com.portal.extrafunc.service.SurveyThemeService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.sysmgr.utils.ViewTools;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
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
/*     */ public class QuestionnaireAct
/*     */ {
/*     */   public static final String QUESTION_LIST = "tpl.questionList";
/*     */   public static final String QUESTION_DETAIL = "tpl.questionDetail";
/*     */ 
/*     */   @Autowired
/*     */   private QuestionnaireService questService;
/*     */ 
/*     */   @Autowired
/*     */   private SurveyThemeService themeService;
/*     */ 
/*     */   @Autowired
/*     */   private QuestionDetailService questionDetailService;
/*     */ 
/*     */   @RequestMapping(value={"/questionList.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String questionlist(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  45 */     return questionlistpage(Integer.valueOf(1), request, response, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/questionList_{page:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String questionlistpage(@PathVariable Integer page, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  52 */     Site site = ContextTools.getSite(request);
/*  53 */     ViewTools.frontData(request, model, site);
/*  54 */     ViewTools.frontPageData(request, model, page);
/*  55 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  56 */       "extrafunc/question", "tpl.questionList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/question-{qId:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String input(@PathVariable Integer qId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  62 */     return inputpage(qId, Integer.valueOf(1), request, response, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/question-{qId:[0-9]+}_{page:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String inputpage(@PathVariable Integer qId, @PathVariable Integer page, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  69 */     Site site = ContextTools.getSite(request);
/*  70 */     Questionnaire question = this.questService.findById(qId);
/*  71 */     if (question == null) {
/*  72 */       return ViewTools.pageNotFound(response);
/*     */     }
/*  74 */     model.addAttribute("qId", qId);
/*  75 */     model.addAttribute("question", question);
/*  76 */     ViewTools.frontData(request, model, site);
/*  77 */     ViewTools.frontPageData(request, model, page);
/*  78 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  79 */       "extrafunc/question", "tpl.questionDetail");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/questionSubmit.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String questionSubmit(Integer qId, HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes ra)
/*     */   {
/*  85 */     Questionnaire question = this.questService.findById(qId);
/*  86 */     if (question == null) {
/*  87 */       return ViewTools.pageNotFound(response);
/*     */     }
/*  89 */     User user = ContextTools.getUser(request);
/*  90 */     String result = checklogin(question, user, request, model);
/*  91 */     if (result != null) {
/*  92 */       return result;
/*     */     }
/*  94 */     String res = checkVote(question, user, request, model);
/*  95 */     if (res != null) {
/*  96 */       ra.addFlashAttribute("msg", res);
/*  97 */       return "redirect:question-" + qId + ".jsp";
/*     */     }
/*  99 */     String ip = ServicesUtils.getIpAddr(request);
/* 100 */     Map m = ServicesUtils.getRequestMap(request, "theme");
/* 101 */     Map ml = ServicesUtils.getRequestMapList(request, 
/* 102 */       "thlist");
/* 103 */     this.themeService.voteSurvey(qId, m, ml, ip, user);
/* 104 */     ra.addFlashAttribute("msg", "提交成功!");
/* 105 */     return "redirect:question-" + qId + ".jsp";
/*     */   }
/*     */ 
/*     */   private String checklogin(Questionnaire question, User user, HttpServletRequest request, ModelMap model)
/*     */   {
/* 110 */     if ((question.getNeedLogin().booleanValue()) && 
/* 111 */       (user == null)) {
/* 112 */       return ViewTools.showLogin(request, model, "必须登录才可以投票!");
/*     */     }
/*     */ 
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */   private String checkVote(Questionnaire question, User user, HttpServletRequest request, ModelMap model)
/*     */   {
/* 120 */     if (!question.getEnable().booleanValue()) {
/* 121 */       return "投票已关闭!";
/*     */     }
/* 123 */     if ((question.getStartTime() != null) && 
/* 124 */       (!question.getStartTime().before(new Date()))) {
/* 125 */       return "投票还未开始!";
/*     */     }
/* 127 */     if ((question.getEndTime() != null) && 
/* 128 */       (!question.getEndTime().after(new Date()))) {
/* 129 */       return "投票已结束!";
/*     */     }
/* 131 */     if (question.getRepeateTime().intValue() == 9999999) {
/* 132 */       if (question.getNeedLogin().booleanValue()) {
/* 133 */         QuestionDetail qd = this.questionDetailService.getDetail(
/* 134 */           question.getId(), user.getId(), null);
/* 135 */         if (qd != null) {
/* 136 */           return "你已经投过票了,不能重复投票!";
/*     */         }
/*     */       }
/* 139 */       if (question.getRestrictIp().booleanValue()) {
/* 140 */         String ip = ServicesUtils.getIpAddr(request);
/* 141 */         QuestionDetail qd = this.questionDetailService.getDetail(
/* 142 */           question.getId(), null, ip);
/* 143 */         if (qd != null) {
/* 144 */           return "你已经投过票了,不能重复投票!";
/*     */         }
/*     */       }
/*     */     }
/* 148 */     long now = System.currentTimeMillis();
/* 149 */     if ((question.getRepeateTime().intValue() > 0) && 
/* 150 */       (question.getRepeateTime().intValue() < 9999999)) {
/* 151 */       Integer d = question.getRepeateTime();
/* 152 */       long sencond = d.intValue() * 60 * 60 * 1000;
/* 153 */       if (question.getNeedLogin().booleanValue()) {
/* 154 */         QuestionDetail qd = this.questionDetailService.getDetail(
/* 155 */           question.getId(), user.getId(), null);
/* 156 */         if (qd.getCreateTime().getTime() + sencond > now) {
/* 157 */           return "你已经投过票了," + d + "小时内不能重复投票!";
/*     */         }
/*     */       }
/* 160 */       if (question.getRestrictIp().booleanValue()) {
/* 161 */         String ip = ServicesUtils.getIpAddr(request);
/* 162 */         QuestionDetail qd = this.questionDetailService.getDetail(
/* 163 */           question.getId(), null, ip);
/* 164 */         if (qd.getCreateTime().getTime() + sencond > now) {
/* 165 */           return "你已经投过票了," + d + "小时内不能重复投票!";
/*     */         }
/*     */       }
/*     */     }
/* 169 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.fnt.QuestionnaireAct
 * JD-Core Version:    0.6.1
 */