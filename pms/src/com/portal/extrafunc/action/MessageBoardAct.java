/*     */ package com.portal.extrafunc.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.datacenter.operatedata.service.LogService;
/*     */ import com.portal.extrafunc.entity.MessageBoard;
/*     */ import com.portal.extrafunc.entity.MessageBoardExt;
/*     */ import com.portal.extrafunc.service.MessageBoardService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class MessageBoardAct
/*     */ {
/*  28 */   private static final Logger log = LoggerFactory.getLogger(MessageBoardAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private MessageBoardService service;
/*     */ 
/*     */   @Autowired
/*     */   private LogService logService;
/*     */ 
/*  33 */   @RequiresPermissions({"admin:board:list"})
/*     */   @RequestMapping({"/board/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) { return "extraFunc/board/list"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:board:add"})
/*     */   @RequestMapping({"/board/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  39 */     return "extraFunc/board/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:board:edit"})
/*     */   @RequestMapping({"/board/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  45 */     model.addAttribute("board", this.service.findById(id));
/*  46 */     return "extraFunc/board/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:board:save"})
/*     */   @RequestMapping({"/board/o_save.do"})
/*     */   public String save(MessageBoard bean, MessageBoardExt ext, Integer typeId, HttpServletRequest request, ModelMap model) {
/*  53 */     Site site = ContextTools.getSite(request);
/*  54 */     bean = this.service.save(bean, ext, site, typeId);
/*  55 */     log.info("save board id={}", bean.getId());
/*  56 */     model.addAttribute("msg", "留言添加成功!");
/*  57 */     return add(request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:board:update"})
/*     */   @RequestMapping({"/board/o_update.do"})
/*     */   public String update(MessageBoard bean, MessageBoardExt ext, Integer typeId, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  65 */     bean = this.service.update(bean, ext, typeId);
/*  66 */     log.info("update board id={}.", bean.getId());
/*  67 */     model.addAttribute("msg", "留言回复成功!");
/*  68 */     return edit(bean.getId(), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/board/jsonData.do"})
/*     */   public String dataPageByJosn(String name, String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  76 */     Site site = ContextTools.getSite(request);
/*  77 */     Page p = this.service.getPage(name, site.getId(), null, sortname, 
/*  78 */       sortorder, page.intValue(), pagesize.intValue());
/*  79 */     model.addAttribute("p", p);
/*  80 */     response.setHeader("Cache-Control", "no-cache");
/*  81 */     response.setContentType("text/json;charset=UTF-8");
/*  82 */     return "extraFunc/board/data";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:board:delete"})
/*     */   @RequestMapping({"/board/o_ajax_delete.do"})
/*     */   public void deleteboard(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/*  89 */     JSONObject json = new JSONObject();
/*  90 */     MessageBoard[] beans = this.service.deleteByIds(ids);
/*  91 */     for (MessageBoard bean : beans) {
/*  92 */       log.info("delete board id={}", bean.getId());
/*  93 */       this.logService.operating(request, "删除留言信息", "id=" + bean.getId());
/*     */     }
/*  95 */     json.put("success", true);
/*  96 */     json.put("status", 1);
/*  97 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:board:show"})
/*     */   @RequestMapping({"/board/o_ajax_show.do"})
/*     */   public void showboard(Integer id, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 105 */     JSONObject json = new JSONObject();
/* 106 */     MessageBoard board = this.service.findById(id);
/* 107 */     if (board == null) {
/* 108 */       json.put("success", false);
/* 109 */       json.put("status", 0);
/* 110 */       ResponseUtils.renderJson(response, json.toString());
/* 111 */       return;
/*     */     }
/* 113 */     this.service.showBoard(id);
/* 114 */     json.put("success", true);
/* 115 */     json.put("status", 1);
/* 116 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.MessageBoardAct
 * JD-Core Version:    0.6.1
 */