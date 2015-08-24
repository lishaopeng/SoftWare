/*     */ package com.portal.usermgr.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.datacenter.operatedata.service.LogService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.usermgr.entity.Role;
/*     */ import com.portal.usermgr.entity.RolePerm;
/*     */ import com.portal.usermgr.service.RoleService;
/*     */ import java.util.Set;
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
/*     */ public class RoleAct
/*     */ {
/*  29 */   private static final Logger log = LoggerFactory.getLogger(RoleAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private LogService logService;
/*     */ 
/*     */   @Autowired
/*     */   private RoleService service;
/*     */ 
/*  34 */   @RequiresPermissions({"admin:role:list"})
/*     */   @RequestMapping({"/role/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) { return "userMgr/role/list"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:role:add"})
/*     */   @RequestMapping({"/role/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  40 */     return "userMgr/role/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:role:edit"})
/*     */   @RequestMapping({"/role/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  46 */     Role role = this.service.findById(id);
/*  47 */     Set perms = role.getPermsSet();
/*  48 */     model.addAttribute("role", role);
/*  49 */     model.addAttribute("perms", perms);
/*  50 */     return "userMgr/role/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:role:save"})
/*     */   @RequestMapping({"/role/o_save.do"})
/*     */   public String save(Role bean, RolePerm rolePerm, HttpServletRequest request, ModelMap model) {
/*  57 */     Site site = ContextTools.getSite(request);
/*  58 */     bean = this.service.saveRole(bean, rolePerm, site);
/*  59 */     log.info("save Role id={}", bean.getId());
/*  60 */     this.logService.operating(request, "添加角色", "id=" + bean.getId() + ";name=" + 
/*  61 */       bean.getName());
/*  62 */     model.addAttribute("msg", "角色添加成功!");
/*  63 */     return add(request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:role:update"})
/*     */   @RequestMapping({"/role/o_update.do"})
/*     */   public String update(Role bean, RolePerm rolePerm, HttpServletRequest request, ModelMap model) {
/*  70 */     bean = this.service.updateRole(bean, rolePerm);
/*  71 */     log.info("update Role id={}.", bean.getId());
/*  72 */     this.logService.operating(request, "更新角色", "id=" + bean.getId() + ";name=" + 
/*  73 */       bean.getName());
/*  74 */     model.addAttribute("msg", "角色修改成功!");
/*  75 */     return edit(bean.getId(), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/role/jsonData.do"})
/*     */   public String dataPageByJosn(String name, Integer departId, String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  83 */     Site site = ContextTools.getSite(request);
/*  84 */     Page p = this.service.getPage(name, site.getId(), sortname, 
/*  85 */       sortorder, page.intValue(), pagesize.intValue());
/*  86 */     model.addAttribute("p", p);
/*  87 */     response.setHeader("Cache-Control", "no-cache");
/*  88 */     response.setContentType("text/json;charset=UTF-8");
/*  89 */     return "userMgr/role/data";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:role:delete"})
/*     */   @RequestMapping({"/role/o_ajax_delete.do"})
/*     */   public void deleteRole(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/*  96 */     JSONObject json = new JSONObject();
/*  97 */     Role[] beans = this.service.deleteByIds(ids);
/*  98 */     for (Role bean : beans) {
/*  99 */       log.info("delete PmsRole id={}", bean.getId());
/* 100 */       this.logService.operating(request, "删除角色", "id=" + bean.getId() + 
/* 101 */         ";name=" + bean.getName());
/*     */     }
/* 103 */     json.put("success", true);
/* 104 */     json.put("status", 1);
/* 105 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.action.RoleAct
 * JD-Core Version:    0.6.1
 */