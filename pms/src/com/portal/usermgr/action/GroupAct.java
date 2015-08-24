/*     */ package com.portal.usermgr.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.usermgr.entity.Group;
/*     */ import com.portal.usermgr.entity.GroupPerm;
/*     */ import com.portal.usermgr.service.GroupService;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class GroupAct
/*     */ {
/*  27 */   private static final Logger log = LoggerFactory.getLogger(GroupAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private GroupService service;
/*     */ 
/*  32 */   @RequiresPermissions({"admin:group:list"})
/*     */   @RequestMapping({"/group/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) { return "userMgr/group/list"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:group:add"})
/*     */   @RequestMapping({"/group/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  38 */     return "userMgr/group/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:group:edit"})
/*     */   @RequestMapping({"/group/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  44 */     model.addAttribute("group", this.service.findById(id));
/*  45 */     return "userMgr/group/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:group:save"})
/*     */   @RequestMapping({"/group/o_save.do"})
/*     */   public String save(Group bean, GroupPerm groupPerm, HttpServletRequest request, ModelMap model) {
/*  52 */     Site site = ContextTools.getSite(request);
/*  53 */     bean = this.service.saveGroup(bean, groupPerm, site);
/*  54 */     log.info("save Group id={}", bean.getId());
/*  55 */     model.addAttribute("msg", "会员组添加成功!");
/*  56 */     return add(model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:group:update"})
/*     */   @RequestMapping({"/group/o_update.do"})
/*     */   public String update(Group bean, GroupPerm groupPerm, HttpServletRequest request, ModelMap model) {
/*  63 */     bean = this.service.updateGroup(bean, groupPerm);
/*  64 */     log.info("update Group id={}.", bean.getId());
/*  65 */     model.addAttribute("msg", "会员组修改成功!");
/*  66 */     return edit(bean.getId(), request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:group:priority"})
/*     */   @RequestMapping({"/group/o_priority.do"})
/*     */   public String priority(Integer[] wids, Integer[] priority, Integer regDefId, HttpServletRequest request, ModelMap model) {
/*  73 */     this.service.updatePriority(wids, priority);
/*  74 */     model.addAttribute("msg", "排序顺序保存成功!");
/*  75 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/group/jsonData.do"})
/*     */   public String dataPageByJosn(String sortname, String sortorder, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  82 */     Site site = ContextTools.getSite(request);
/*  83 */     List list = this.service.getList(site.getId(), sortname, sortorder);
/*  84 */     model.addAttribute("list", list);
/*  85 */     response.setHeader("Cache-Control", "no-cache");
/*  86 */     response.setContentType("text/json;charset=UTF-8");
/*  87 */     return "userMgr/group/data";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:group:delete"})
/*     */   @RequestMapping({"/group/o_ajax_delete.do"})
/*     */   public void deleteGroup(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/*  94 */     JSONObject json = new JSONObject();
/*  95 */     Group[] beans = this.service.deleteByIds(ids);
/*  96 */     for (Group bean : beans) {
/*  97 */       log.info("delete Group id={}", bean.getId());
/*     */     }
/*  99 */     json.put("success", true);
/* 100 */     json.put("status", 1);
/* 101 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.action.GroupAct
 * JD-Core Version:    0.6.1
 */