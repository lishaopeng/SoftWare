/*     */ package com.portal.doccenter.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.datacenter.operatedata.service.LogService;
/*     */ import com.portal.doccenter.entity.Model;
/*     */ import com.portal.doccenter.entity.ModelField;
/*     */ import com.portal.doccenter.service.ModelFieldService;
/*     */ import com.portal.doccenter.service.ModelService;
/*     */ import com.portal.sysmgr.service.TplService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
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
/*     */ public class ModelAct
/*     */ {
/*  32 */   private static final Logger log = LoggerFactory.getLogger(ModelAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private LogService logService;
/*     */ 
/*     */   @Autowired
/*     */   private ModelService service;
/*     */ 
/*     */   @Autowired
/*     */   private TplService tplService;
/*     */ 
/*     */   @Autowired
/*     */   private ModelFieldService fieldService;
/*     */ 
/*  37 */   @RequiresPermissions({"admin:model:list"})
/*     */   @RequestMapping({"/model/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) { return "docCenter/config/model/list"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:model:add"})
/*     */   @RequestMapping({"/model/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  43 */     List iconList = this.tplService.getFileChild(
/*  44 */       "/skin/sys", "/img/icon");
/*  45 */     model.addAttribute("iconList", iconList);
/*  46 */     return "docCenter/config/model/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:model:edit"})
/*     */   @RequestMapping({"/model/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  52 */     List iconList = this.tplService.getFileChild(
/*  53 */       "/skin/sys", "/img/icon");
/*  54 */     model.addAttribute("iconList", iconList);
/*  55 */     model.addAttribute("model", this.service.findById(id));
/*  56 */     return "docCenter/config/model/edit";
/*     */   }
/*     */   @RequiresPermissions({"admin:model:save"})
/*     */   @RequestMapping({"/model/o_save.do"})
/*     */   public String save(Model bean, HttpServletRequest request, ModelMap model) {
/*  62 */     bean = this.service.save(bean);
/*  63 */     List fieldList = getFields(bean);
/*  64 */     this.fieldService.saveList(fieldList);
/*  65 */     log.info("save Model id={}", bean.getId());
/*  66 */     this.logService.operating(request, "添加模型", "id=" + bean.getId() + ";name=" + 
/*  67 */       bean.getName());
/*  68 */     model.addAttribute("msg", "模型添加成功!");
/*  69 */     return add(model);
/*     */   }
/*     */   @RequiresPermissions({"admin:model:update"})
/*     */   @RequestMapping({"/model/o_update.do"})
/*     */   public String update(Model bean, HttpServletRequest request, ModelMap model) {
/*  75 */     bean = this.service.update(bean);
/*  76 */     log.info("update Model id={}.", bean.getId());
/*  77 */     this.logService.operating(request, "修改模型", "id=" + bean.getId() + ";name=" + 
/*  78 */       bean.getName());
/*  79 */     model.addAttribute("msg", "模型修改成功!");
/*  80 */     return edit(bean.getId(), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/model/jsonData.do"})
/*     */   public String dataPageByJosn(String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  87 */     List list = this.service.getList(true, sortname, sortorder);
/*  88 */     model.addAttribute("list", list);
/*  89 */     response.setHeader("Cache-Control", "no-cache");
/*  90 */     response.setContentType("text/json;charset=UTF-8");
/*  91 */     return "docCenter/config/model/dataJson";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:model:delete"})
/*     */   @RequestMapping({"/model/o_ajax_delete.do"})
/*     */   public void deleteModel(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/*  98 */     JSONObject json = new JSONObject();
/*  99 */     Model[] beans = this.service.deleteByIds(ids);
/* 100 */     for (Model bean : beans) {
/* 101 */       log.info("delete Model id={}", bean.getId());
/* 102 */       this.logService.operating(request, "删除模型", "id=" + bean.getId() + 
/* 103 */         ";name=" + bean.getName());
/*     */     }
/* 105 */     json.put("success", true);
/* 106 */     json.put("status", 1);
/* 107 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:model:priority"})
/*     */   @RequestMapping({"/model/o_priority.do"})
/*     */   public String priority(Integer[] wids, Integer[] priority, Boolean[] disabled, Integer defId, HttpServletRequest request, ModelMap model)
/*     */   {
/* 116 */     this.service.updatePriority(wids, priority, disabled, defId);
/* 117 */     model.addAttribute("msg", "序列修改成功!");
/* 118 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   private List<ModelField> getFields(Model model) {
/* 122 */     List list = new ArrayList();
/*     */ 
/* 124 */     int i = 0; for (int len = Model.DEF_NAMES.length; i < len; i++) {
/* 125 */       if (!StringUtils.isBlank(Model.DEF_NAMES[i])) {
/* 126 */         ModelField field = new ModelField();
/* 127 */         field.setEconomy(Boolean.valueOf(true));
/* 128 */         field.setModel(model);
/* 129 */         field.setName(Model.DEF_NAMES[i]);
/* 130 */         field.setLabel(Model.DEF_LABELS[i]);
/* 131 */         field.setDataType(Model.DEF_DATA_TYPES[i]);
/* 132 */         field.setSingle(Boolean.valueOf(true));
/* 133 */         field.setShow(Boolean.valueOf(true));
/* 134 */         field.setRequired(Model.DEF_REQUIREDS[i]);
/* 135 */         field.setPriority(Integer.valueOf(i + 1));
/* 136 */         list.add(field);
/*     */       }
/*     */     }
/* 139 */     return list;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.ModelAct
 * JD-Core Version:    0.6.1
 */