/*    */ package com.portal.doccenter.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.doccenter.entity.WorkFlow;
/*    */ import com.portal.doccenter.service.WorkFlowService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import com.portal.usermgr.service.RoleService;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class WorkFlowAct
/*    */ {
/* 30 */   private static final Logger log = LoggerFactory.getLogger(WorkFlowAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private WorkFlowService service;
/*    */ 
/*    */   @Autowired
/*    */   private RoleService roleService;
/*    */ 
/* 36 */   @RequiresPermissions({"admin:workflow:list"})
/*    */   @RequestMapping({"/workflow/v_list.do"})
/*    */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { Site site = ContextTools.getSite(request);
/* 37 */     List list = this.roleService.getListBySite(site.getId());
/* 38 */     model.addAttribute("list", list);
/* 39 */     return "docCenter/config/workflow/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:workflow:save"})
/*    */   @RequestMapping({"/workflow/o_save.do"})
/*    */   public String save(WorkFlow bean, Integer[] step, Integer[] roleIds, HttpServletRequest request, ModelMap model)
/*    */   {
/* 46 */     Site site = ContextTools.getSite(request);
/* 47 */     bean = this.service.save(bean, site, step, roleIds);
/* 48 */     log.info("save WorkFlow id={}", bean.getId());
/* 49 */     return "redirect:v_list.do";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:workflow:update"})
/*    */   @RequestMapping({"/workflow/o_update.do"})
/*    */   public String update(WorkFlow bean, Integer[] step, Integer[] roleIds, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 56 */     bean = this.service.update(bean, step, roleIds);
/* 57 */     log.info("update WorkFlow id={}.", bean.getId());
/* 58 */     return list(pageNo, request, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/workflow/jsonData.do"})
/*    */   public String dataPageByJosn(String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 65 */     Site site = ContextTools.getSite(request);
/* 66 */     Page p = this.service.getPage(site.getId(), sortname, 
/* 67 */       sortorder, page.intValue(), pagesize.intValue());
/* 68 */     model.addAttribute("p", p);
/* 69 */     response.setHeader("Cache-Control", "no-cache");
/* 70 */     response.setContentType("text/json;charset=UTF-8");
/* 71 */     return "docCenter/config/workflow/dataJson";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:workflow:delete"})
/*    */   @RequestMapping({"/workflow/o_ajax_delete.do"})
/*    */   public void deleteWorkflow(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 78 */     JSONObject json = new JSONObject();
/* 79 */     WorkFlow[] beans = this.service.deleteByIds(ids);
/* 80 */     for (WorkFlow bean : beans) {
/* 81 */       log.info("delete WorkFlow id={}", bean.getId());
/*    */     }
/* 83 */     json.put("success", true);
/* 84 */     json.put("status", 1);
/* 85 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/workflow/o_ajax_find.do"})
/*    */   public String workflowFind(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 92 */     Site site = ContextTools.getSite(request);
/* 93 */     WorkFlow wf = this.service.findById(id);
/* 94 */     List list = this.roleService.getListBySite(site.getId());
/* 95 */     model.addAttribute("list", list);
/* 96 */     model.addAttribute("wf", wf);
/* 97 */     response.setHeader("Cache-Control", "no-cache");
/* 98 */     response.setContentType("text/json;charset=UTF-8");
/* 99 */     return "docCenter/config/workflow/stepData";
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.WorkFlowAct
 * JD-Core Version:    0.6.1
 */