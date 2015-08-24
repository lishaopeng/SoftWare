/*     */ package com.portal.doccenter.action;
/*     */ 
/*     */ import com.javapms.basic.plugin.springmvc.RealPathResolver;
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.javapms.basic.utils.ServicesUtils;
/*     */ import com.portal.datacenter.operatedata.service.LogService;
/*     */ import com.portal.doccenter.entity.Article;
/*     */ import com.portal.doccenter.entity.ArticleExt;
/*     */ import com.portal.doccenter.entity.ArticleTxt;
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.doccenter.entity.Model;
/*     */ import com.portal.doccenter.service.ArticleService;
/*     */ import com.portal.doccenter.service.ArticleTypeService;
/*     */ import com.portal.doccenter.service.ChannelService;
/*     */ import com.portal.doccenter.service.ModelFieldService;
/*     */ import com.portal.doccenter.service.ModelService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.usermgr.entity.Group;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.GroupService;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
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
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.wltea.analyzer.IKSegmentation;
/*     */ import org.wltea.analyzer.Lexeme;
/*     */ 
/*     */ @Controller
/*     */ public class ArticleAct
/*     */ {
/*  52 */   private static final Logger log = LoggerFactory.getLogger(ArticleAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private ChannelService channelService;
/*     */ 
/*     */   @Autowired
/*     */   private ModelService modelService;
/*     */ 
/*     */   @Autowired
/*     */   private ModelFieldService modelFieldService;
/*     */ 
/*     */   @Autowired
/*     */   private GroupService groupService;
/*     */ 
/*     */   @Autowired
/*     */   private ArticleTypeService articleTypeService;
/*     */ 
/*     */   @Autowired
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   @Autowired
/*     */   private LogService logService;
/*     */ 
/*     */   @Autowired
/*     */   private ArticleService service;
/*     */ 
/*  57 */   @RequestMapping({"/doc/v_tree.do"})
/*     */   public String tree(Integer parentId, HttpServletRequest request, HttpServletResponse response, ModelMap model) { model.addAttribute("parentId", parentId);
/*  58 */     Integer siteId = ContextTools.getSiteId(request);
/*  59 */     Integer userId = ContextTools.getUserId(request);
/*  60 */     List list = this.channelService.getChannelByAdmin(userId, siteId, 
/*  61 */       parentId, null, null, null, Boolean.valueOf(false));
/*  62 */     model.addAttribute("list", list);
/*  63 */     response.setHeader("Cache-Control", "no-cache");
/*  64 */     response.setContentType("text/json;charset=UTF-8");
/*  65 */     return "docCenter/document/tree";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/doc/v_addtree.do"})
/*     */   public String addtree(Integer cid, Integer parentId, Integer modelId, Integer chnlId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  72 */     model.addAttribute("parentId", parentId);
/*  73 */     List list = new ArrayList();
/*  74 */     Integer siteId = ContextTools.getSiteId(request);
/*  75 */     User user = ContextTools.getUser(request);
/*  76 */     if ((cid != null) && (parentId == null)) {
/*  77 */       parentId = cid;
/*  78 */       list.add(this.channelService.findById(cid));
/*     */     } else {
/*  80 */       list = this.channelService.getChannelByModel(parentId, modelId, user, 
/*  81 */         siteId);
/*     */     }
/*  83 */     if (chnlId != null) {
/*  84 */       list.remove(this.channelService.findById(chnlId));
/*     */     }
/*  86 */     model.addAttribute("list", list);
/*  87 */     response.setHeader("Cache-Control", "no-cache");
/*  88 */     response.setContentType("text/json;charset=UTF-8");
/*  89 */     return "docCenter/document/addtree";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:doc:list"})
/*     */   @RequestMapping({"/doc/v_list.do"})
/*     */   public String list(Integer chnlId, HttpServletRequest request, ModelMap model) {
/*  96 */     if (chnlId != null) {
/*  97 */       Channel chnl = this.channelService.findById(chnlId);
/*  98 */       model.addAttribute("modelList", chnl.getModelList());
/*     */     } else {
/* 100 */       model.addAttribute("modelList", 
/* 101 */         this.modelService.getList(false, null, null));
/*     */     }
/* 103 */     List typeList = this.articleTypeService.getList(false, null, 
/* 104 */       null);
/* 105 */     model.addAttribute("allModel", this.modelService.getList(false, null, null));
/* 106 */     model.addAttribute("chnlId", chnlId);
/* 107 */     model.addAttribute("typeList", typeList);
/* 108 */     return "docCenter/document/list";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:doc:list1"})
/*     */   @RequestMapping({"/doc/v_list1.do"})
/*     */   public String list1(String title, Byte[] status, Integer[] typeIds, Integer[] modelIds, boolean top, boolean recommend, Integer orderBy, Integer chnlId, Integer page, Integer pagesize, String sortname, String sortorder, HttpServletRequest request, ModelMap model)
/*     */   {
/* 118 */     Site site = ContextTools.getSite(request);
/* 119 */     Integer siteId = site.getId();
/* 120 */     User user = ContextTools.getUser(request);
/* 121 */     if (chnlId != null) {
/* 122 */       Channel chnl = this.channelService.findById(chnlId);
/* 123 */       model.addAttribute("modelList", chnl.getModelList());
/*     */     } else {
/* 125 */       model.addAttribute("modelList", 
/* 126 */         this.modelService.getList(false, null, null));
/*     */     }
/* 128 */     List typeList = this.articleTypeService.getList(false, null, 
/* 129 */       null);
/* 130 */     Page p = this.service.getPageArticle(StringUtils.trim(title), 
/* 131 */       typeIds, modelIds, null, top, recommend, status, siteId, user, 
/* 132 */       chnlId, 0, sortname, sortorder, 1, 30);
/* 133 */     model.addAttribute("p", p);
/* 134 */     model.addAttribute("siteId", siteId);
/* 135 */     model.addAttribute("allModel", this.modelService.getList(false, null, null));
/* 136 */     model.addAttribute("chnlId", chnlId);
/* 137 */     model.addAttribute("typeList", typeList);
/* 138 */     return "docCenter/document/list1";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:doc:add"})
/*     */   @RequestMapping({"/doc/v_add.do"})
/*     */   public String add(Integer cid, Integer modelId, HttpServletRequest request, ModelMap model) {
/* 145 */     Site site = ContextTools.getSite(request);
/* 146 */     Channel c = null;
/* 147 */     if (cid != null) {
/* 148 */       c = this.channelService.findById(cid);
/*     */     }
/* 150 */     Model m = this.modelService.findById(modelId);
/* 151 */     List fieldList = this.modelFieldService.getList(m.getId(), 
/* 152 */       false, null, null);
/* 153 */     List groupList = this.groupService.getList(site.getId(), null, null);
/* 154 */     List typeList = this.articleTypeService.getList(false, null, 
/* 155 */       null);
/* 156 */     model.addAttribute("model", m);
/* 157 */     model.addAttribute("fieldList", fieldList);
/* 158 */     model.addAttribute("groupList", groupList);
/* 159 */     model.addAttribute("typeList", typeList);
/* 160 */     if (cid != null) {
/* 161 */       model.addAttribute("cid", cid);
/*     */     }
/* 163 */     if (c != null) {
/* 164 */       model.addAttribute("channel", c);
/*     */     }
/* 166 */     return "docCenter/document/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:doc:edit"})
/*     */   @RequestMapping({"/doc/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/* 172 */     Site site = ContextTools.getSite(request);
/* 173 */     Article article = this.service.findById(id);
/* 174 */     List fieldList = this.modelFieldService.getList(article
/* 175 */       .getModel().getId(), false, null, null);
/* 176 */     List groupList = this.groupService.getList(site.getId(), null, null);
/* 177 */     Integer[] groupIds = Group.fetchIds(article.getViewGroups());
/* 178 */     List typeList = this.articleTypeService.getList(false, null, 
/* 179 */       null);
/* 180 */     model.addAttribute("article", article);
/* 181 */     model.addAttribute("channel", article.getChannel());
/* 182 */     model.addAttribute("fieldList", fieldList);
/* 183 */     model.addAttribute("groupList", groupList);
/* 184 */     model.addAttribute("groupIds", groupIds);
/* 185 */     model.addAttribute("typeList", typeList);
/* 186 */     return "docCenter/document/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:doc:save"})
/*     */   @RequestMapping({"/doc/o_save.do"})
/*     */   public String save(Article bean, ArticleExt ext, ArticleTxt txt, Integer modelId, Integer[] viewGroupIds, String[] attPaths, String[] attNames, String[] imgPaths, String[] imgDescs, Boolean[] thumbs, String[] imgStyles, Integer channelId, HttpServletRequest request, ModelMap model)
/*     */   {
/* 196 */     Site site = ContextTools.getSite(request);
/* 197 */     User user = ContextTools.getUser(request);
/* 198 */     bean.setAttr(ServicesUtils.getRequestMap(request, "attr_"));
/*     */ 
/* 200 */     bean = this.service.save(bean, ext, txt, site, user, viewGroupIds, attPaths, 
/* 201 */       attNames, imgPaths, imgDescs, thumbs, imgStyles, channelId, 
/* 202 */       modelId, false);
/* 203 */     log.info("save Article id={}", bean.getId());
/* 204 */     this.logService.operating(request, "添加文档", "id=" + bean.getId() + ";title=" + 
/* 205 */       bean.getTitle());
/* 206 */     model.addAttribute("msg", "文档添加成功");
/* 207 */     return add(channelId, modelId, request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:doc:update"})
/*     */   @RequestMapping({"/doc/o_update.do"})
/*     */   public String update(Article bean, ArticleExt ext, ArticleTxt txt, Integer[] channelIds, Integer[] topicIds, Integer[] viewGroupIds, String[] attPaths, String[] attNames, String[] imgPaths, String[] imgDescs, Boolean[] thumbs, String[] imgStyles, Integer channelId, HttpServletRequest request, ModelMap model)
/*     */   {
/* 217 */     User user = ContextTools.getUser(request);
/* 218 */     Map attr = 
/* 219 */       ServicesUtils.getRequestMap(request, "attr_");
/* 220 */     bean = this.service.update(bean, ext, txt, channelIds, viewGroupIds, 
/* 221 */       attPaths, attNames, imgPaths, imgDescs, thumbs, imgStyles, 
/* 222 */       attr, channelId, user, false);
/* 223 */     log.info("update Article id={}.", bean.getId());
/* 224 */     this.logService.operating(request, "修改文档", "id=" + bean.getId() + ";title=" + 
/* 225 */       bean.getTitle());
/* 226 */     model.addAttribute("msg", "文档修改成功");
/* 227 */     return edit(bean.getId(), request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:doc:move"})
/*     */   @RequestMapping({"/doc/o_move.do"})
/*     */   public void move(Integer chnlId, String[] modelIds, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 235 */     JSONObject json = new JSONObject();
/* 236 */     Map channels = ServicesUtils.getRequestMapWithPrefix(
/* 237 */       request, "chnl_", modelIds);
/* 238 */     int count = this.service.moveDoc(chnlId, channels);
/* 239 */     json.put("success", true);
/* 240 */     json.put("count", count);
/* 241 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:doc:empty"})
/*     */   @RequestMapping({"/doc/o_empty.do"})
/*     */   public void empty(Integer chnlId, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 249 */     JSONObject json = new JSONObject();
/* 250 */     int count = this.service.emptyDoc(chnlId);
/* 251 */     json.put("success", true);
/* 252 */     json.put("count", count);
/* 253 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/doc/jsonData.do"})
/*     */   public String dataPageByJosn(String title, Byte[] status, Integer[] typeIds, Integer[] modelIds, boolean top, boolean recommend, Integer orderBy, Integer chnlId, Integer page, Integer pagesize, String sortname, String sortorder, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 264 */     Site site = ContextTools.getSite(request);
/* 265 */     Integer siteId = site.getId();
/* 266 */     User user = ContextTools.getUser(request);
/* 267 */     Page p = this.service.getPageArticle(StringUtils.trim(title), 
/* 268 */       typeIds, modelIds, null, top, recommend, status, siteId, user, 
/* 269 */       chnlId, 0, sortname, sortorder, page.intValue(), pagesize.intValue());
/* 270 */     List typeList = this.articleTypeService.getList(true, null, 
/* 271 */       null);
/* 272 */     model.addAttribute("p", p);
/* 273 */     model.addAttribute("siteId", siteId);
/* 274 */     model.addAttribute("typeList", typeList);
/* 275 */     response.setHeader("Cache-Control", "no-cache");
/* 276 */     response.setContentType("text/json;charset=UTF-8");
/* 277 */     return "docCenter/document/listdata";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:doc:delete"})
/*     */   @RequestMapping({"/doc/o_ajax_delete.do"})
/*     */   public void deleteArticle(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 284 */     JSONObject json = new JSONObject();
/* 285 */     Article[] beans = this.service.deleteByIds(ids);
/* 286 */     for (Article bean : beans) {
/* 287 */       log.info("删除文档: id={}", bean.getId());
/* 288 */       this.logService.operating(request, "删除文档", "id=" + bean.getId() + 
/* 289 */         ";title=" + bean.getTitle());
/*     */     }
/* 291 */     json.put("success", true);
/* 292 */     json.put("status", 1);
/* 293 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:doc:cycle"})
/*     */   @RequestMapping({"/doc/o_ajax_cycle.do"})
/*     */   public void cycleArticle(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 301 */     JSONObject json = new JSONObject();
/* 302 */     Article[] beans = this.service.cycle(ids);
/* 303 */     for (Article bean : beans) {
/* 304 */       log.info("文档放入回收站,id={}", bean.getId());
/*     */     }
/* 306 */     json.put("success", true);
/* 307 */     json.put("status", 1);
/* 308 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:doc:reduct"})
/*     */   @RequestMapping({"/doc/o_ajax_reduct.do"})
/*     */   public void reductArticle(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 316 */     JSONObject json = new JSONObject();
/* 317 */     Article[] beans = this.service.reduct(ids);
/* 318 */     for (Article bean : beans) {
/* 319 */       log.info("文档还原,id={}", bean.getId());
/*     */     }
/* 321 */     json.put("success", true);
/* 322 */     json.put("status", 1);
/* 323 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:doc:check"})
/*     */   @RequestMapping({"/doc/o_check.do"})
/*     */   public void check(Integer[] ids, String chnlNumber, HttpServletRequest request, HttpServletResponse response)
/*     */     throws JSONException
/*     */   {
/* 332 */     JSONObject json = new JSONObject();
/* 333 */     User user = ContextTools.getUser(request);
/* 334 */     Article[] beans = this.service.check(ids, user);
/* 335 */     for (Article bean : beans) {
/* 336 */       log.info("check Doc id={}", bean.getId());
/*     */     }
/* 338 */     json.put("success", true);
/* 339 */     json.put("status", 1);
/* 340 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/doc/o_delete_file.do"})
/*     */   public void deleteFile(String path, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 347 */     if (path.indexOf("/member/upload/") > -1) {
/* 348 */       path = path.substring(path.indexOf("/member/upload/"));
/*     */     }
/* 350 */     String realpath = this.realPathResolver.get(path);
/* 351 */     File f = new File(realpath);
/* 352 */     if (f.exists()) {
/* 353 */       f.delete();
/*     */     }
/* 355 */     ResponseUtils.renderText(response, "");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/doc/v_titletags.do"})
/*     */   public void titleTags(String title, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException
/*     */   {
/* 361 */     StringBuffer strb = new StringBuffer("");
/* 362 */     JSONObject json = new JSONObject();
/* 363 */     if (!StringUtils.isBlank(title)) {
/* 364 */       IKSegmentation ikSeg = new IKSegmentation(new StringReader(title), 
/* 365 */         true);
/*     */       try {
/* 367 */         Lexeme l = null;
/* 368 */         while ((l = ikSeg.next()) != null) {
/* 369 */           if (l.getLexemeType() == 0) {
/* 370 */             strb.append(l.getLexemeText());
/* 371 */             strb.append(",");
/*     */           }
/*     */         }
/* 374 */         json.put("success", true);
/* 375 */         if (strb.toString().length() > 1) {
/* 376 */           json.put(
/* 377 */             "tag", 
/* 378 */             strb.toString().substring(0, 
/* 379 */             strb.toString().length() - 1));
/*     */         }
/* 381 */         ResponseUtils.renderJson(response, json.toString());
/*     */       } catch (IOException e) {
/* 383 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.ArticleAct
 * JD-Core Version:    0.6.1
 */