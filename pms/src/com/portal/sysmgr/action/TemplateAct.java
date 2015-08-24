/*     */ package com.portal.sysmgr.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.javapms.basic.utils.ServicesUtils;
/*     */ import com.portal.datacenter.operatedata.service.LogService;
/*     */ import com.portal.doccenter.service.ArticleTypeService;
/*     */ import com.portal.doccenter.service.ModelService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.service.ResourceService;
/*     */ import com.portal.sysmgr.service.SiteService;
/*     */ import com.portal.sysmgr.service.TplService;
/*     */ import com.portal.sysmgr.utils.ContextTools;
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
/*     */ public class TemplateAct
/*     */ {
/*  43 */   private static final Logger log = LoggerFactory.getLogger(TemplateAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private LogService logService;
/*     */   private TplService tplService;
/*     */   private ResourceService resourceService;
/*     */   private SiteService siteService;
/*     */   private ArticleTypeService typeService;
/*     */ 
/*     */   @Autowired
/*     */   private ModelService modelService;
/*     */ 
/*  48 */   @RequestMapping(value={"/tpl/v_tpl_dirtree.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String dirtree(String path, HttpServletRequest request, HttpServletResponse response, ModelMap model) { Site site = ContextTools.getSite(request);
/*  49 */     String root = site.getSolutionPath();
/*  50 */     log.debug("tree path={}", root);
/*  51 */     if (StringUtils.isBlank(path)) {
/*  52 */       model.addAttribute("isRoot", Boolean.valueOf(true));
/*  53 */       path = "";
/*     */     } else {
/*  55 */       model.addAttribute("isRoot", Boolean.valueOf(false));
/*     */     }
/*  57 */     List tplList = this.tplService.getDirChild(root, path);
/*  58 */     model.addAttribute("path", path);
/*  59 */     model.addAttribute("tplList", tplList);
/*  60 */     response.setHeader("Cache-Control", "no-cache");
/*  61 */     response.setContentType("text/json;charset=UTF-8");
/*  62 */     return "sysMgr/tplMgr/dirtree";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/tpl/v_tpl_filetree.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String tpltree(String path, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  68 */     Site site = ContextTools.getSite(request);
/*  69 */     String root = site.getSolutionPath();
/*  70 */     if (StringUtils.isBlank(path)) {
/*  71 */       path = "";
/*     */     }
/*  73 */     List tplList = this.tplService.getFileChild(root, path);
/*  74 */     model.addAttribute("path", path);
/*  75 */     model.addAttribute("tplList", tplList);
/*  76 */     response.setHeader("Cache-Control", "no-cache");
/*  77 */     response.setContentType("text/json;charset=UTF-8");
/*  78 */     return "sysMgr/tplMgr/filetree";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/tpl/v_tree.do"})
/*     */   public String tree(String path, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  84 */     Site site = ContextTools.getSite(request);
/*  85 */     String root = site.getSolutionPath();
/*  86 */     log.debug("tree path={}", root);
/*  87 */     if (StringUtils.isBlank(path)) {
/*  88 */       model.addAttribute("isRoot", Boolean.valueOf(true));
/*  89 */       path = "";
/*     */     } else {
/*  91 */       model.addAttribute("isRoot", Boolean.valueOf(false));
/*     */     }
/*  93 */     List tplList = this.tplService.getChild(root, path);
/*  94 */     model.addAttribute("path", path);
/*  95 */     model.addAttribute("tplList", tplList);
/*  96 */     response.setHeader("Cache-Control", "no-cache");
/*  97 */     response.setContentType("text/json;charset=UTF-8");
/*  98 */     return "sysMgr/tplMgr/tree";
/*     */   }
/*     */   @RequiresPermissions({"admin:tpl:list"})
/*     */   @RequestMapping(value={"/tpl/v_list.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String list(String path, HttpServletRequest request, ModelMap model) {
/* 104 */     model.addAttribute("path", path);
/* 105 */     return "sysMgr/tplMgr/list";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:tpl:createdir"})
/*     */   @RequestMapping({"/tpl/o_create_dir.do"})
/*     */   public String createDir(String path, String dirName, HttpServletRequest request, ModelMap model) {
/* 112 */     Site site = ContextTools.getSite(request);
/* 113 */     String root = site.getSolutionPath();
/* 114 */     if (StringUtils.isBlank(path)) {
/* 115 */       path = "";
/*     */     }
/* 117 */     this.tplService.save(root + path + "/" + dirName, null, true);
/* 118 */     model.addAttribute("path", path);
/* 119 */     model.addAttribute("msg", "目录新建成功!");
/* 120 */     return list(path, request, model);
/*     */   }
/*     */   @RequiresPermissions({"admin:tpl:add"})
/*     */   @RequestMapping(value={"/tpl/v_add.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String add(String path, HttpServletRequest request, ModelMap model) {
/* 126 */     List typeList = this.typeService.getList(false, null, null);
/* 127 */     model.addAttribute("path", path);
/* 128 */     model.addAttribute("modelList", this.modelService.getList(false, null, null));
/* 129 */     model.addAttribute("typeList", typeList);
/* 130 */     return "sysMgr/tplMgr/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:tpl:edit"})
/*     */   @RequestMapping(value={"/tpl/v_edit.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String edit(HttpServletRequest request, ModelMap model) {
/* 136 */     Site site = ContextTools.getSite(request);
/* 137 */     String name = ServicesUtils.getQueryParam(request, "name");
/* 138 */     List typeList = this.typeService.getList(false, null, null);
/* 139 */     String root = site.getSolutionPath();
/* 140 */     model.addAttribute("tpl", this.tplService.get(root, name));
/* 141 */     model.addAttribute("modelList", this.modelService.getList(false, null, null));
/* 142 */     model.addAttribute("typeList", typeList);
/* 143 */     return "sysMgr/tplMgr/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:tpl:save"})
/*     */   @RequestMapping({"/tpl/o_save.do"})
/*     */   public String save(String path, String filename, String source, HttpServletRequest request, ModelMap model) {
/* 150 */     Site site = ContextTools.getSite(request);
/* 151 */     String root = site.getSolutionPath();
/* 152 */     if (StringUtils.isBlank(path)) {
/* 153 */       path = "";
/*     */     }
/* 155 */     String name = root + path + "/" + filename + ".html";
/* 156 */     this.tplService.save(name, source, false);
/* 157 */     model.addAttribute("path", path);
/* 158 */     log.info("save Tpl name={}", filename);
/* 159 */     this.logService.operating(request, "创建模板", "filename=" + filename);
/* 160 */     model.addAttribute("msg", "模板创建成功!");
/* 161 */     return add(path, request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:tpl:update"})
/*     */   @RequestMapping(value={"/tpl/o_update.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String update(String name, String source, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 169 */     Site site = ContextTools.getSite(request);
/* 170 */     String root = site.getSolutionPath();
/* 171 */     this.tplService.update(root + name, source);
/* 172 */     log.info("update Tpl name={}.", name);
/* 173 */     this.logService.operating(request, "添加模板", "filename=" + name);
/* 174 */     model.addAttribute("msg", "模板修改成功!");
/* 175 */     return edit(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/tpl/jsonData.do"})
/*     */   public String dataPageByJosn(String path, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 181 */     Site site = ContextTools.getSite(request);
/* 182 */     String root = site.getSolutionPath();
/* 183 */     log.debug("list Template root: {}", root);
/* 184 */     if (StringUtils.isBlank(path)) {
/* 185 */       path = "";
/*     */     }
/* 187 */     model.addAttribute("path", path);
/* 188 */     model.addAttribute("list", this.tplService.getChild(root, path));
/* 189 */     response.setHeader("Cache-Control", "no-cache");
/* 190 */     response.setContentType("text/json;charset=UTF-8");
/* 191 */     return "sysMgr/tplMgr/listdata";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:tpl:delete"})
/*     */   @RequestMapping({"/tpl/o_ajax_delete.do"})
/*     */   public void deleteGroup(String[] names, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 198 */     JSONObject json = new JSONObject();
/* 199 */     Site site = ContextTools.getSite(request);
/* 200 */     String root = site.getSolutionPath();
/* 201 */     this.tplService.delete(root, names);
/* 202 */     json.put("success", true);
/* 203 */     json.put("status", 1);
/* 204 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:tpl:rename"})
/*     */   @RequestMapping(value={"/tpl/o_rename.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String renameSubmit(String path, String origName, String distName, HttpServletRequest request, ModelMap model)
/*     */   {
/* 212 */     Site site = ContextTools.getSite(request);
/* 213 */     String root = site.getSolutionPath();
/* 214 */     String orig = root + path + "/" + origName;
/* 215 */     String dist = root + path + "/" + distName;
/* 216 */     this.tplService.rename(orig, dist);
/* 217 */     log.info("name Tpl from {} to {}", orig, dist);
/* 218 */     model.addAttribute("path", path);
/* 219 */     model.addAttribute("msg", "模板重命名成功!");
/* 220 */     return list(path, request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:tpl:setting"})
/*     */   @RequestMapping({"/tpl/v_setting.do"})
/*     */   public String setting(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 227 */     Site site = ContextTools.getSite(request);
/* 228 */     String[] styles = this.resourceService.getStyles(site.getTplPath());
/* 229 */     model.addAttribute("styles", styles);
/* 230 */     model.addAttribute("defStyle", site.getTplStyle());
/* 231 */     return "sysMgr/tplMgr/setting";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:tpl:deftemplate"})
/*     */   @RequestMapping({"/tpl/o_def_template.do"})
/*     */   public void defTempate(String style, HttpServletRequest request, HttpServletResponse response) {
/* 238 */     Site site = ContextTools.getSite(request);
/* 239 */     this.siteService.updateTplStyle(site.getId(), style);
/* 240 */     ResponseUtils.renderJson(response, "{'success':true}");
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setTplManager(TplService tplService)
/*     */   {
/* 254 */     this.tplService = tplService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setResourceService(ResourceService resourceService) {
/* 259 */     this.resourceService = resourceService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setSiteService(SiteService siteService) {
/* 264 */     this.siteService = siteService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setTypeService(ArticleTypeService typeService) {
/* 269 */     this.typeService = typeService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.TemplateAct
 * JD-Core Version:    0.6.1
 */