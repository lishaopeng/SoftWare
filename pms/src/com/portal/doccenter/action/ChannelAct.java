/*     */ package com.portal.doccenter.action;
/*     */ 
/*     */ /*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;

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

import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.javapms.basic.utils.ServicesUtils;
/*     */ import com.portal.datacenter.operatedata.service.LogService;
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.doccenter.entity.ChannelExt;
/*     */ import com.portal.doccenter.entity.ChannelTxt;
/*     */ import com.portal.doccenter.entity.ChnlTplSelection;
/*     */ import com.portal.doccenter.service.ArticleService;
/*     */ import com.portal.doccenter.service.ChannelService;
/*     */ import com.portal.doccenter.service.ModelService;
/*     */ import com.portal.doccenter.service.WorkFlowService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.service.TplService;
/*     */ import com.portal.sysmgr.staticpage.StaticPageService;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.usermgr.entity.Group;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.GroupService;

/*     */ import freemarker.template.TemplateException;
/*     */ 
/*     */ @Controller
/*     */ public class ChannelAct
/*     */ {
/*  48 */   private static final Logger log = LoggerFactory.getLogger(ChannelAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private ModelService modelService;
/*     */ 
/*     */   @Autowired
/*     */   private StaticPageService staticPageService;
/*     */ 
/*     */   @Autowired
/*     */   private GroupService groupService;
/*     */ 
/*     */   @Autowired
/*     */   private WorkFlowService workFlowService;
/*     */ 
/*     */   @Autowired
/*     */   private LogService logService;
/*     */ 
/*     */   @Autowired
/*     */   private TplService tplService;
/*     */ 
/*     */   @Autowired
/*     */   private ChannelService service;
/*     */ 
/*     */   @Autowired
/*     */   private ArticleService articleService;
/*     */ 
/*  53 */   @RequestMapping(value={"/channel/v_tpl_dirtree.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String dirtree(String path, HttpServletRequest request, HttpServletResponse response, ModelMap model) { Site site = ContextTools.getSite(request);
/*  54 */     String root = site.getSolutionPath();
/*  55 */     log.debug("tree path={}", root);
/*  56 */     if (StringUtils.isBlank(path)) {
/*  57 */       model.addAttribute("isRoot", Boolean.valueOf(true));
/*  58 */       path = "";
/*     */     } else {
/*  60 */       model.addAttribute("isRoot", Boolean.valueOf(false));
/*     */     }
/*  62 */     List tplList = this.tplService.getDirChild(root, path);
/*  63 */     model.addAttribute("path", path);
/*  64 */     model.addAttribute("tplList", tplList);
/*  65 */     response.setHeader("Cache-Control", "no-cache");
/*  66 */     response.setContentType("text/json;charset=UTF-8");
/*  67 */     return "docCenter/channel/dirtree";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/channel/v_tpl_filetree.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String tpltree(String path, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  73 */     Site site = ContextTools.getSite(request);
/*  74 */     String root = site.getSolutionPath();
/*  75 */     if (StringUtils.isBlank(path)) {
/*  76 */       path = "";
/*     */     }
/*  78 */     List tplList = this.tplService.getFileChild(root, path);
/*  79 */     model.addAttribute("path", path);
/*  80 */     model.addAttribute("tplList", tplList);
/*  81 */     response.setHeader("Cache-Control", "no-cache");
/*  82 */     response.setContentType("text/json;charset=UTF-8");
/*  83 */     return "docCenter/channel/filetree";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/v_edittree.do"})
/*     */   public String edittree(Integer parentId, Integer channelId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  90 */     model.addAttribute("parentId", parentId);
/*  91 */     Integer siteId = ContextTools.getSiteId(request);
/*  92 */     Integer userId = ContextTools.getUserId(request);
/*  93 */     List list = this.service.getChannelByAdmin(userId, siteId, 
/*  94 */       parentId, null, null, null, null);
/*  95 */     list.remove(this.service.findById(channelId));
/*  96 */     model.addAttribute("list", list);
/*  97 */     response.setHeader("Cache-Control", "no-cache");
/*  98 */     response.setContentType("text/json;charset=UTF-8");
/*  99 */     return "docCenter/channel/edittree";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/v_seltree.do"})
/*     */   public String seltree(Integer parentId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 105 */     model.addAttribute("parentId", parentId);
/* 106 */     Integer siteId = ContextTools.getSiteId(request);
/* 107 */     Integer userId = ContextTools.getUserId(request);
/* 108 */     List list = this.service.getChannelByAdmin(userId, siteId, 
/* 109 */       parentId, null, null, null, Boolean.valueOf(false));
/* 110 */     model.addAttribute("list", list);
/* 111 */     response.setHeader("Cache-Control", "no-cache");
/* 112 */     response.setContentType("text/json;charset=UTF-8");
/* 113 */     return "docCenter/channel/edittree";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/v_inserttree.do"})
/*     */   public String addtree(Integer parentId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 119 */     model.addAttribute("parentId", parentId);
/* 120 */     Integer siteId = ContextTools.getSiteId(request);
/* 121 */     Integer userId = ContextTools.getUserId(request);
/* 122 */     List list = this.service.getChannelByAdmin(userId, siteId, 
/* 123 */       parentId, null, null, null, Boolean.valueOf(false));
/* 124 */     model.addAttribute("list", list);
/* 125 */     response.setHeader("Cache-Control", "no-cache");
/* 126 */     response.setContentType("text/json;charset=UTF-8");
/* 127 */     return "docCenter/channel/inserttree";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/v_tree.do"})
/*     */   public String tree(Integer parentId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 133 */     model.addAttribute("parentId", parentId);
/* 134 */     Integer siteId = ContextTools.getSiteId(request);
/* 135 */     Integer userId = ContextTools.getUserId(request);
/* 136 */     List list = this.service.getChannelByAdmin(userId, siteId, 
/* 137 */       parentId, null, null, null, null);
/* 138 */     model.addAttribute("list", list);
/* 139 */     response.setHeader("Cache-Control", "no-cache");
/* 140 */     response.setContentType("text/json;charset=UTF-8");
/* 141 */     return "docCenter/channel/tree";
/*     */   }
/*     */   @RequiresPermissions({"admin:channel:list"})
/*     */   @RequestMapping({"/channel/v_list.do"})
/*     */   public String list(Integer parentId, ModelMap model) {
/* 147 */     model.addAttribute("parentId", parentId);
/* 148 */     return "docCenter/channel/list";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:channel:add"})
/*     */   @RequestMapping({"/channel/v_add.do"})
/*     */   public String add(Integer parentId, HttpServletRequest request, ModelMap model) {
/* 155 */     Site site = ContextTools.getSite(request);
/* 156 */     Channel parent = null;
/* 157 */     if (parentId != null) {
/* 158 */       parent = this.service.findById(parentId);
/* 159 */       model.addAttribute("parent", parent);
/* 160 */       model.addAttribute("parentId", parentId);
/* 161 */       model.addAttribute("modelList", parent.getModelList());
/*     */     } else {
/* 163 */       model.addAttribute("modelList", 
/* 164 */         this.modelService.getList(false, null, null));
/*     */     }
/* 166 */     List flowList = this.workFlowService.findByList(site.getId());
/* 167 */     List groupList = this.groupService.getList(site.getId(), null, null);
/* 168 */     List viewGroups = groupList;
/*     */     Collection contriGroups;
/* 170 */     if (parent != null)
/* 171 */       contriGroups = parent.getContriGroups();
/*     */     else {
/* 173 */       contriGroups = groupList;
/*     */     }
/* 175 */     model.addAttribute("viewGroups", viewGroups);
/* 176 */     model.addAttribute("contriGroups", contriGroups);
/* 177 */     model.addAttribute("contriGroupIds", Group.fetchIds(contriGroups));
/* 178 */     model.addAttribute("flowList", flowList);
/* 179 */     return "docCenter/channel/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:channel:edit"})
/*     */   @RequestMapping({"/channel/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/* 185 */     Site site = ContextTools.getSite(request);
/*     */ 
/* 187 */     Channel channel = this.service.findById(id);
/*     */ 
/* 195 */     if (channel.getParent() != null)
/* 196 */       model.addAttribute("modelList", channel.getParent().getModelList());
/*     */     else {
/* 198 */       model.addAttribute("modelList", 
/* 199 */         this.modelService.getList(false, null, null));
/*     */     }
/* 201 */     Integer[] modelIds = ChnlTplSelection.fetchIds(channel.getTpls());
/* 202 */     List groupList = this.groupService.getList(site.getId(), null, null);
/* 203 */     List viewGroups = groupList;
/* 204 */     Integer[] viewGroupIds = Group.fetchIds(channel.getViewGroups());
/* 205 */     Channel parent = channel.getParent();
/*     */     Collection contriGroups;
/* 207 */     if (parent != null)
/* 208 */       contriGroups = parent.getContriGroups();
/*     */     else {
/* 210 */       contriGroups = groupList;
/*     */     }
/* 212 */     Integer[] contriGroupIds = Group.fetchIds(channel.getContriGroups());
/* 213 */     List flowList = this.workFlowService.findByList(site.getId());
/* 214 */     model.addAttribute("chnlmodelList", channel.getModelList());
/* 215 */     model.addAttribute("modelIds", modelIds);
/* 216 */     model.addAttribute("viewGroups", viewGroups);
/* 217 */     model.addAttribute("viewGroupIds", viewGroupIds);
/* 218 */     model.addAttribute("contriGroups", contriGroups);
/* 219 */     model.addAttribute("contriGroupIds", contriGroupIds);
/* 220 */     model.addAttribute("flowList", flowList);
/* 221 */     model.addAttribute("channel", channel);
/* 222 */     return "docCenter/channel/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:channel:save"})
/*     */   @RequestMapping({"/channel/o_save.do"})
/*     */   public String save(Integer parentId, Channel bean, ChannelExt ext, ChannelTxt txt, Integer flowId, Integer[] viewGroupIds, Integer[] contriGroupIds, String[] modelIds, HttpServletRequest request, ModelMap model)
/*     */   {
/* 231 */     User user = ContextTools.getUser(request);
/* 232 */     Map tpls = ServicesUtils.getRequestMapWithPrefix(
/* 233 */       request, "tpl_", modelIds);
/* 234 */     bean = this.service.save(bean, ext, txt, ContextTools.getSite(request), 
/* 235 */       user, flowId, viewGroupIds, contriGroupIds, parentId, tpls);
/* 236 */     log.info("save Channel id={}, name={}", bean.getId(), bean.getName());
/* 237 */     this.logService.operating(request, "channel.log.save", "id=" + bean.getId() + 
/* 238 */       ";title=" + bean.getTitle());
/* 239 */     model.addAttribute("msg", "栏目添加成功!");
/* 240 */     return add(parentId, request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:channel:update"})
/*     */   @RequestMapping({"/channel/o_update.do"})
/*     */   public String update(Channel bean, ChannelExt ext, ChannelTxt txt, Integer flowId, Integer[] viewGroupIds, Integer[] contriGroupIds, Integer parentId, String[] modelIds, HttpServletRequest request, ModelMap model)
/*     */   {
/* 258 */     Map tpls = ServicesUtils.getRequestMapWithPrefix(
/* 259 */       request, "tpl_", modelIds);
/* 260 */     bean = this.service.update(bean, ext, txt, flowId, viewGroupIds, 
/* 261 */       contriGroupIds, parentId, tpls);
/* 262 */     log.info("update Channel id={}.", bean.getId());
/* 263 */     this.logService.operating(request, "channel.log.update", 
/* 264 */       "id=" + bean.getId() + ";name=" + bean.getName());
/* 265 */     model.addAttribute("msg", "栏目修改成功!");
/* 266 */     return edit(bean.getId(), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/jsonData.do"})
/*     */   public String dataPageByJosn(Integer parentId, String key, String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 274 */     Integer userId = ContextTools.getUserId(request);
/* 275 */     Integer siteId = ContextTools.getSiteId(request);
/* 276 */     List list = this.service.getChannelByAdmin(userId, siteId, 
/* 277 */       parentId, key, sortname, sortorder, null);
/* 278 */     model.addAttribute("list", list);
/* 279 */     response.setHeader("Cache-Control", "no-cache");
/* 280 */     response.setContentType("text/json;charset=UTF-8");
/* 281 */     return "docCenter/channel/listdata";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:channel:delete"})
/*     */   @RequestMapping({"/channel/o_delchnl.do"})
/*     */   public String delChannel(Integer chnlId, Boolean del, Integer cid, HttpServletRequest request, ModelMap model) {
/* 288 */     if ((del == null) || (del.booleanValue())) {
/* 289 */       long count = this.articleService.getCountDoc(chnlId);
/* 290 */       if (count > 0L) {
/* 291 */         model.addAttribute("msge", "请先转移或者清除该栏目下的文档!");
/* 292 */         return edit(chnlId, request, model);
/*     */       }
/*     */     }
/* 295 */     Channel parent = this.service.findById(chnlId).getParent();
/* 296 */     this.service.delChannel(chnlId, del, cid);
/* 297 */     model.addAttribute("msg", "删除栏目成功!");
/* 298 */     if (parent != null) {
/* 299 */       return edit(parent.getId(), request, model);
/*     */     }
/* 301 */     return list(null, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:channel:delete"})
/*     */   @RequestMapping({"/channel/o_ajax_delete.do"})
/*     */   public void deleteChannel(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 309 */     JSONObject json = new JSONObject();
/*     */     long count;
/* 310 */     for (Integer cid : ids) {
/* 311 */       count = this.articleService.getCountDoc(cid);
/* 312 */       if (count > 0L) {
/* 313 */         json.put("success", false);
/* 314 */         json.put("status", 0);
/* 315 */         json.put("msg", "请先清空文档再删除栏目!");
/* 316 */         ResponseUtils.renderJson(response, json.toString());
/* 317 */         return;
/*     */       }
/*     */     }
/* 320 */     Channel[] beans = this.service.deleteByIds(ids);
/* 321 */     for (Channel bean : beans) {
/* 322 */       log.info("delete Channel id={}", bean.getId());
/* 323 */       this.logService.operating(request, "channel.log.delete", 
/* 324 */         "id=" + bean.getId() + ";title=" + bean.getTitle());
/*     */     }
/* 326 */     json.put("success", true);
/* 327 */     json.put("status", 1);
/* 328 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:channel:static"})
/*     */   @RequestMapping({"/channel/o_static.do"})
/*     */   public void staticChannel(Integer chnlId, HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException, TemplateException, JSONException
/*     */   {
/* 337 */     JSONObject json = new JSONObject();
/* 338 */     Channel c = this.service.findById(chnlId);
/* 339 */     this.staticPageService.staticChannelPage(c);
/* 340 */     json.put("success", true);
/* 341 */     json.put("status", 1);
/* 342 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:channel:priority"})
/*     */   @RequestMapping({"/channel/o_priority.do"})
/*     */   public void itemPriority(Integer id, Integer priority, HttpServletRequest request, HttpServletResponse response)
/*     */     throws JSONException
/*     */   {
/* 351 */     JSONObject json = new JSONObject();
/* 352 */     this.service.updatePrio(id, priority);
/* 353 */     json.put("success", true);
/* 354 */     json.put("status", 1);
/* 355 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.ChannelAct
 * JD-Core Version:    0.6.1
 */