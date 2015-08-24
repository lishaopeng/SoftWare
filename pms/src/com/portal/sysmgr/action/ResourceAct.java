/*     */ package com.portal.sysmgr.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.datacenter.operatedata.service.LogService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.service.ResourceService;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import java.io.IOException;
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
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ @Controller
/*     */ public class ResourceAct
/*     */ {
/*  39 */   private static final Logger log = LoggerFactory.getLogger(ResourceAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private LogService logService;
/*     */   private ResourceService resourceService;
/*     */ 
/*  44 */   @RequestMapping({"/res/v_tree.do"})
/*     */   public String tree(String path, HttpServletRequest request, HttpServletResponse response, ModelMap model) { Site site = ContextTools.getSite(request);
/*  45 */     String root = site.getResSolutionPath();
/*  46 */     if (StringUtils.isBlank(path)) {
/*  47 */       path = "";
/*     */     }
/*  49 */     List resList = this.resourceService.listFile(root, path, 
/*  50 */       true);
/*  51 */     model.addAttribute("path", path);
/*  52 */     model.addAttribute("resList", resList);
/*  53 */     response.setHeader("Cache-Control", "no-cache");
/*  54 */     response.setContentType("text/json;charset=UTF-8");
/*  55 */     return "sysMgr/resMgr/tree"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:res:list"})
/*     */   @RequestMapping({"/res/v_list.do"})
/*     */   public String list(String path, HttpServletRequest request, ModelMap model) {
/*  61 */     model.addAttribute("path", path);
/*  62 */     return "sysMgr/resMgr/list";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:res:createdir"})
/*     */   @RequestMapping({"/res/o_create_dir.do"})
/*     */   public String createDir(String path, String dirName, HttpServletRequest request, ModelMap model) {
/*  69 */     Site site = ContextTools.getSite(request);
/*  70 */     String root = site.getResSolutionPath();
/*  71 */     if (StringUtils.isBlank(path)) {
/*  72 */       path = "";
/*     */     }
/*  74 */     this.resourceService.createDir(root + path, dirName);
/*  75 */     model.addAttribute("path", path);
/*  76 */     model.addAttribute("msg", "目录新建成功!");
/*  77 */     return list(path, request, model);
/*     */   }
/*     */   @RequiresPermissions({"admin:res:add"})
/*     */   @RequestMapping({"/res/v_add.do"})
/*     */   public String add(String path, HttpServletRequest request, ModelMap model) {
/*  83 */     model.addAttribute("path", path);
/*  84 */     return "sysMgr/resMgr/add";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:res:edit"})
/*     */   @RequestMapping({"/res/v_edit.do"})
/*     */   public String edit(String name, HttpServletRequest request, ModelMap model) throws IOException {
/*  91 */     Site site = ContextTools.getSite(request);
/*  92 */     String root = site.getResSolutionPath();
/*  93 */     model.addAttribute("source", this.resourceService.readFile(root + name));
/*  94 */     model.addAttribute("name", name);
/*  95 */     model.addAttribute("filename", 
/*  96 */       name.substring(name.lastIndexOf('/') + 1));
/*  97 */     return "sysMgr/resMgr/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:res:save"})
/*     */   @RequestMapping({"/res/o_save.do"})
/*     */   public String save(String path, String filename, String source, HttpServletRequest request, ModelMap model) throws IOException {
/* 104 */     Site site = ContextTools.getSite(request);
/* 105 */     String root = site.getResSolutionPath();
/* 106 */     if (StringUtils.isBlank(path)) {
/* 107 */       path = "";
/*     */     }
/* 109 */     this.resourceService.createFile(root + path, filename, source);
/* 110 */     this.logService.operating(request, "添加资源", "filename=" + filename);
/* 111 */     model.addAttribute("msg", "资源创建成功!");
/* 112 */     return add(path, request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:res:update"})
/*     */   @RequestMapping({"/res/o_update.do"})
/*     */   public String update(String name, String source, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException
/*     */   {
/* 120 */     Site site = ContextTools.getSite(request);
/* 121 */     String root = site.getResSolutionPath();
/* 122 */     this.resourceService.updateFile(root + name, source);
/* 123 */     log.info("update Resource name={}.", name);
/* 124 */     this.logService.operating(request, "修改资源", "filename=" + name);
/* 125 */     model.addAttribute("msg", "资源修改成功!");
/* 126 */     return edit(name, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/res/jsonData.do"})
/*     */   public String dataPageByJosn(String path, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 132 */     Site site = ContextTools.getSite(request);
/* 133 */     String root = site.getResSolutionPath();
/* 134 */     log.debug("list Res root: {}", root);
/* 135 */     if (StringUtils.isBlank(path)) {
/* 136 */       path = "";
/*     */     }
/* 138 */     model.addAttribute("path", path);
/* 139 */     model.addAttribute("list", this.resourceService.listFile(root, path, false));
/* 140 */     response.setHeader("Cache-Control", "no-cache");
/* 141 */     response.setContentType("text/json;charset=UTF-8");
/* 142 */     return "sysMgr/resMgr/listdata";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:res:delete"})
/*     */   @RequestMapping({"/res/o_ajax_delete.do"})
/*     */   public void deleteGroup(String[] names, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 149 */     JSONObject json = new JSONObject();
/* 150 */     Site site = ContextTools.getSite(request);
/* 151 */     String root = site.getResSolutionPath();
/* 152 */     this.resourceService.delete(root, names);
/* 153 */     json.put("success", true);
/* 154 */     json.put("status", 1);
/* 155 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:res:rename"})
/*     */   @RequestMapping(value={"/res/o_rename.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String renameSubmit(String path, String origName, String distName, HttpServletRequest request, ModelMap model)
/*     */   {
/* 163 */     Site site = ContextTools.getSite(request);
/* 164 */     String root = site.getResSolutionPath();
/* 165 */     if (StringUtils.isBlank(path)) {
/* 166 */       path = "";
/*     */     }
/* 168 */     String orig = root + path + "/" + origName;
/* 169 */     String dist = root + path + "/" + distName;
/* 170 */     this.resourceService.rename(orig, dist);
/* 171 */     log.info("name Resource from {} to {}", orig, dist);
/* 172 */     model.addAttribute("path", path);
/* 173 */     model.addAttribute("msg", "资源重命名成功!");
/* 174 */     return list(path, request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:res:swfupload"})
/*     */   @RequestMapping(value={"/res/o_swfupload.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void swfUpload(String path, @RequestParam(value="resatt", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IllegalStateException, IOException
/*     */   {
/* 184 */     Site site = ContextTools.getSite(request);
/* 185 */     String root = site.getResSolutionPath();
/* 186 */     if (StringUtils.isBlank(path)) {
/* 187 */       path = "";
/*     */     }
/* 189 */     this.resourceService.saveFile(root + path, file);
/* 190 */     log.info("file upload seccess: {}, size:{}.", 
/* 191 */       file.getOriginalFilename(), Long.valueOf(file.getSize()));
/* 192 */     ResponseUtils.renderText(response, "");
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setResourceService(ResourceService resourceService)
/*     */   {
/* 201 */     this.resourceService = resourceService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.ResourceAct
 * JD-Core Version:    0.6.1
 */