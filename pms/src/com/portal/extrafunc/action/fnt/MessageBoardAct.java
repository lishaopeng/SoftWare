/*    */ package com.portal.extrafunc.action.fnt;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.extrafunc.service.MessageBoardService;
/*    */ import com.portal.extrafunc.service.MessageTypeService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import com.portal.sysmgr.utils.ViewTools;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class MessageBoardAct
/*    */ {
/*    */   public static final String BOARD_INPUT = "tpl.boardInput";
/*    */   public static final String BOARD_LIST = "board_list";
/*    */ 
/*    */   @Autowired
/*    */   private MessageBoardService service;
/*    */ 
/*    */   @Autowired
/*    */   private MessageTypeService typeService;
/*    */ 
/*    */   @RequestMapping(value={"/messageboard.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String input(HttpServletRequest request, ModelMap model)
/*    */   {
/* 42 */     Site site = ContextTools.getSite(request);
/* 43 */     List typeList = this.typeService.getList(site.getId(), null, 
/* 44 */       null);
/* 45 */     model.addAttribute("typeList", typeList);
/* 46 */     ViewTools.frontData(request, model, site);
/* 47 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 48 */       "extrafunc/board", "tpl.boardInput");
/*    */   }
/*    */ 
/*    */   @RequestMapping(value={"/messageboard.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*    */   public void submit(String title, String name, String mobile, String email, String address, String zipcode, Integer typeId, String content, HttpServletRequest request, HttpServletResponse response)
/*    */     throws JSONException
/*    */   {
/* 56 */     Site site = ContextTools.getSite(request);
/* 57 */     JSONObject json = new JSONObject();
/* 58 */     this.service.save(title, name, mobile, email, address, zipcode, typeId, 
/* 59 */       content, site);
/* 60 */     json.put("success", true);
/* 61 */     json.put("status", 1);
/* 62 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/board/list.jsp"})
/*    */   public String list(Integer pageNo, Integer count, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 69 */     Site site = ContextTools.getSite(request);
/* 70 */     Page page = this.service.getPage(null, site.getId(), Boolean.valueOf(true), null, null, 
/* 71 */       pageNo.intValue(), count.intValue());
/* 72 */     model.addAttribute("page", page);
/* 73 */     ViewTools.frontData(request, model, site);
/* 74 */     response.setHeader("Cache-Control", "no-cache");
/* 75 */     response.setContentType("text/json;charset=UTF-8");
/* 76 */     return ViewTools.getTplPath(null, site.getSolutionPath(), 
/* 77 */       "common/tags", "board_list");
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.fnt.MessageBoardAct
 * JD-Core Version:    0.6.1
 */