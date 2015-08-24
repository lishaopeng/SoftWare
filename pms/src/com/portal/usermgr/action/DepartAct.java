/*     */ package com.portal.usermgr.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.doccenter.service.ChannelService;
/*     */ import com.portal.doccenter.service.WorkFlowService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.usermgr.entity.Admin;
/*     */ import com.portal.usermgr.entity.Depart;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.DepartService;
/*     */ import java.util.List;
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
/*     */ public class DepartAct
/*     */ {
/*  32 */   private static final Logger log = LoggerFactory.getLogger(DepartAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private DepartService service;
/*     */ 
/*     */   @Autowired
/*     */   private ChannelService channelService;
/*     */ 
/*     */   @Autowired
/*     */   private WorkFlowService workFlowService;
/*     */ 
/*  37 */   @RequestMapping({"/depart/v_chnltree.do"})
/*     */   public String chnltree(Integer departId, HttpServletRequest request, HttpServletResponse response, ModelMap model) { Integer siteId = ContextTools.getSiteId(request);
/*  38 */     List chnlList = this.channelService.getChannelBySite(siteId, null, 
/*  39 */       null, null, null, null);
/*  40 */     model.addAttribute("chnlList", chnlList);
/*  41 */     if (departId != null) {
/*  42 */       Integer[] channelIds = Channel.fetchIds(this.service.findById(departId)
/*  43 */         .getChannels());
/*  44 */       model.addAttribute("chnlIds", channelIds);
/*     */     }
/*  46 */     response.setHeader("Cache-Control", "no-cache");
/*  47 */     response.setContentType("text/json;charset=UTF-8");
/*  48 */     return "userMgr/depart/tree";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/depart/v_addtree.do"})
/*     */   public String addtree(Integer departId, Integer parentId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  55 */     model.addAttribute("parentId", parentId);
/*  56 */     Integer siteId = ContextTools.getSiteId(request);
/*  57 */     List list = null;
/*  58 */     if (parentId != null) {
/*  59 */       list = this.service.getListByParent(parentId);
/*  60 */       model.addAttribute("list", list);
/*     */     } else {
/*  62 */       list = this.service.getListNoParent(siteId);
/*  63 */       model.addAttribute("list", list);
/*     */     }
/*  65 */     if (departId != null) {
/*  66 */       list.remove(this.service.findById(departId));
/*     */     }
/*  68 */     response.setHeader("Cache-Control", "no-cache");
/*  69 */     response.setContentType("text/json;charset=UTF-8");
/*  70 */     return "userMgr/depart/addtree";
/*     */   }
/*     */   @RequiresPermissions({"admin:depart:list"})
/*     */   @RequestMapping({"/depart/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) {
/*  76 */     Site site = ContextTools.getSite(request);
/*  77 */     User user = ContextTools.getUser(request);
/*  78 */     if (!user.getAdmin().haveAllManage(site.getId())) {
/*  79 */       Integer departId = user.getAdmin().getDepart(site.getId()).getId();
/*  80 */       return edit(departId, request, model);
/*     */     }
/*  82 */     return "userMgr/depart/list";
/*     */   }
/*     */   @RequiresPermissions({"admin:depart:add"})
/*     */   @RequestMapping({"/depart/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  88 */     Site site = ContextTools.getSite(request);
/*  89 */     User user = ContextTools.getUser(request);
/*  90 */     if (!user.getAdmin().haveAllManage(site.getId())) {
/*  91 */       Integer departId = user.getAdmin().getDepart(site.getId()).getId();
/*  92 */       return edit(departId, request, model);
/*     */     }
/*  94 */     List chnlList = this.channelService.getChannelByAdmin(user.getId(), 
/*  95 */       site.getId(), null, null, null, null, Boolean.valueOf(true));
/*  96 */     List flowList = this.workFlowService.findByList(site.getId());
/*  97 */     model.addAttribute("chnlList", chnlList);
/*  98 */     model.addAttribute("flowList", flowList);
/*  99 */     return "userMgr/depart/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:depart:edit"})
/*     */   @RequestMapping({"/depart/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/* 105 */     Site site = ContextTools.getSite(request);
/* 106 */     User user = ContextTools.getUser(request);
/* 107 */     Depart depart = this.service.findById(id);
/* 108 */     List flowList = this.workFlowService.findByList(site.getId());
/* 109 */     List chnlList = this.channelService.getChannelByAdmin(user.getId(), 
/* 110 */       site.getId(), null, null, null, null, Boolean.valueOf(true));
/* 111 */     if (depart.getParent() != null) {
/* 112 */       model.addAttribute("parentId", depart.getParent().getId());
/*     */     }
/* 114 */     model.addAttribute("chnlList", chnlList);
/* 115 */     model.addAttribute("flowList", flowList);
/* 116 */     model.addAttribute("depart", depart);
/* 117 */     return "userMgr/depart/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:depart:save"})
/*     */   @RequestMapping({"/depart/o_save.do"})
/*     */   public String save(Depart bean, Integer parentId, Integer[] channelIds, HttpServletRequest request, ModelMap model) {
/* 124 */     Site site = ContextTools.getSite(request);
/* 125 */     bean = this.service.save(bean, site, parentId, channelIds);
/* 126 */     log.info("save PmsDepartment id={}", bean.getId());
/* 127 */     model.addAttribute("msg", "部门添加成功!");
/* 128 */     return add(request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:depart:update"})
/*     */   @RequestMapping({"/depart/o_update.do"})
/*     */   public String update(Depart bean, Integer parentId, Integer[] channelIds, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 135 */     bean = this.service.update(bean, parentId, channelIds);
/* 136 */     log.info("update PmsDepartment id={}.", bean.getId());
/* 137 */     model.addAttribute("msg", "部门修改成功!");
/* 138 */     return edit(bean.getId(), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/depart/jsonData.do"})
/*     */   public String dataPageByJosn(String key, String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 145 */     Site site = ContextTools.getSite(request);
/* 146 */     Page p = this.service.getPage(key, site.getId(), sortname, 
/* 147 */       sortorder, page.intValue(), pagesize.intValue());
/* 148 */     model.addAttribute("p", p);
/* 149 */     response.setHeader("Cache-Control", "no-cache");
/* 150 */     response.setContentType("text/json;charset=UTF-8");
/* 151 */     return "userMgr/depart/data";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:depart:delete"})
/*     */   @RequestMapping({"/depart/o_ajax_delete.do"})
/*     */   public void deleteDepart(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 158 */     JSONObject json = new JSONObject();
/* 159 */     Depart[] beans = this.service.deleteByIds(ids);
/* 160 */     for (Depart bean : beans) {
/* 161 */       log.info("delete PmsDepartment id={}", bean.getId());
/*     */     }
/* 163 */     json.put("success", true);
/* 164 */     json.put("status", 1);
/* 165 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/depart/v_depart.do"})
/*     */   public String treeDepart(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 172 */     Integer siteId = ContextTools.getSiteId(request);
/* 173 */     List list = this.service.getListNoParent(siteId);
/* 174 */     list.remove(this.service.findById(id));
/* 175 */     model.addAttribute("list", list);
/* 176 */     response.setHeader("Cache-Control", "no-cache");
/* 177 */     response.setContentType("text/json;charset=UTF-8");
/* 178 */     return "userMgr/depart/tree_depart";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:depart:priority"})
/*     */   @RequestMapping({"/depart/o_priority.do"})
/*     */   public void itemPriority(Integer id, Integer priority, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 186 */     JSONObject json = new JSONObject();
/* 187 */     this.service.updatePrio(id, priority);
/* 188 */     json.put("success", true);
/* 189 */     json.put("status", 1);
/* 190 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.action.DepartAct
 * JD-Core Version:    0.6.1
 */