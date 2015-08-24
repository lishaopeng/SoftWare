/*     */ package com.portal.extrafunc.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.datacenter.operatedata.service.LogService;
/*     */ import com.portal.extrafunc.entity.Advert;
/*     */ import com.portal.extrafunc.service.AdvertService;
/*     */ import com.portal.extrafunc.service.AdvertSlotService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
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
/*     */ public class AdvertAct
/*     */ {
/*  30 */   private static final Logger log = LoggerFactory.getLogger(AdvertAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private AdvertService service;
/*     */ 
/*     */   @Autowired
/*     */   private AdvertSlotService slotService;
/*     */ 
/*     */   @Autowired
/*     */   private LogService logService;
/*     */ 
/*  35 */   @RequiresPermissions({"admin:advert:list"})
/*     */   @RequestMapping({"/advert/v_list.do"})
/*     */   public String list(Integer slotId, ModelMap model) { model.addAttribute("slotId", slotId);
/*  36 */     return "extraFunc/advert/list"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:advert:add"})
/*     */   @RequestMapping({"/advert/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  42 */     Site site = ContextTools.getSite(request);
/*  43 */     List slotList = this.slotService.getList(site.getId());
/*  44 */     model.addAttribute("slotList", slotList);
/*  45 */     return "extraFunc/advert/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:advert:edit"})
/*     */   @RequestMapping({"/advert/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  51 */     Site site = ContextTools.getSite(request);
/*  52 */     List slotList = this.slotService.getList(site.getId());
/*  53 */     model.addAttribute("slotList", slotList);
/*  54 */     model.addAttribute("advert", this.service.findById(id));
/*  55 */     return "extraFunc/advert/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:advert:save"})
/*     */   @RequestMapping({"/advert/o_save.do"})
/*     */   public String save(Advert bean, Integer slotId, boolean advtype, HttpServletRequest request, ModelMap model) {
/*  62 */     Site site = ContextTools.getSite(request);
/*  63 */     bean = this.service.save(bean, slotId, advtype, site);
/*  64 */     log.info("save Advert id={}", bean.getId());
/*  65 */     model.addAttribute("msg", "广告添加成功!");
/*  66 */     return add(request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:advert:update"})
/*     */   @RequestMapping({"/advert/o_update.do"})
/*     */   public String update(Advert bean, Integer slotId, boolean advtype, HttpServletRequest request, ModelMap model) {
/*  73 */     bean = this.service.update(bean, slotId, advtype);
/*  74 */     log.info("update Advert id={}.", bean.getId());
/*  75 */     model.addAttribute("msg", "广告修改成功!");
/*  76 */     return edit(bean.getId(), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/advert/jsonData.do"})
/*     */   public String dataPageByJosn(Integer slotId, String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  84 */     Site site = ContextTools.getSite(request);
/*  85 */     Page p = this.service.getPage(site.getId(), slotId, sortname, 
/*  86 */       sortorder, page.intValue(), pagesize.intValue());
/*  87 */     model.addAttribute("p", p);
/*  88 */     response.setHeader("Cache-Control", "no-cache");
/*  89 */     response.setContentType("text/json;charset=UTF-8");
/*  90 */     return "extraFunc/advert/data";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:advert:delete"})
/*     */   @RequestMapping({"/advert/o_ajax_delete.do"})
/*     */   public void deleteAdvert(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/*  97 */     JSONObject json = new JSONObject();
/*  98 */     Advert[] beans = this.service.deleteByIds(ids);
/*  99 */     for (Advert bean : beans) {
/* 100 */       log.info("delete Advert id={}", bean.getId());
/* 101 */       this.logService.operating(request, "删除广告", "id=" + bean.getId());
/*     */     }
/* 103 */     json.put("success", true);
/* 104 */     json.put("status", 1);
/* 105 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.AdvertAct
 * JD-Core Version:    0.6.1
 */