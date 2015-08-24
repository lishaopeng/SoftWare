/*    */ package com.portal.extrafunc.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.datacenter.operatedata.service.LogService;
/*    */ import com.portal.extrafunc.entity.AdvertSlot;
/*    */ import com.portal.extrafunc.service.AdvertSlotService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.ContextTools;
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
/*    */ public class AdvertSlotAct
/*    */ {
/* 27 */   private static final Logger log = LoggerFactory.getLogger(AdvertSlotAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private AdvertSlotService service;
/*    */ 
/*    */   @Autowired
/*    */   private LogService logService;
/*    */ 
/* 32 */   @RequiresPermissions({"admin:advertSlot:list"})
/*    */   @RequestMapping({"/advertSlot/v_list.do"})
/*    */   public String list() { return "extraFunc/advert/slot/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:advertSlot:add"})
/*    */   @RequestMapping({"/advertSlot/v_add.do"})
/*    */   public String add() {
/* 38 */     return "extraFunc/advert/slot/add";
/*    */   }
/*    */   @RequiresPermissions({"admin:advertSlot:edit"})
/*    */   @RequestMapping({"/advertSlot/v_edit.do"})
/*    */   public String edit(Integer id, ModelMap model) {
/* 44 */     model.addAttribute("slot", this.service.findById(id));
/* 45 */     return "extraFunc/advert/slot/edit";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:advertSlot:save"})
/*    */   @RequestMapping({"/advertSlot/o_save.do"})
/*    */   public String save(AdvertSlot bean, HttpServletRequest request, ModelMap model) {
/* 52 */     Site site = ContextTools.getSite(request);
/* 53 */     bean = this.service.save(bean, site);
/* 54 */     log.info("save AdvertSlot id={}", bean.getId());
/* 55 */     model.addAttribute("msg", "广告位添加成功!");
/* 56 */     return add();
/*    */   }
/*    */   @RequiresPermissions({"admin:advertSlot:update"})
/*    */   @RequestMapping({"/advertSlot/o_update.do"})
/*    */   public String update(AdvertSlot bean, ModelMap model) {
/* 62 */     bean = this.service.update(bean);
/* 63 */     log.info("update AdvertSlot id={}.", bean.getId());
/* 64 */     model.addAttribute("msg", "广告位修改成功!");
/* 65 */     return edit(bean.getId(), model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/advertSlot/jsonData.do"})
/*    */   public String dataPageByJosn(Integer slotId, String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 73 */     Site site = ContextTools.getSite(request);
/* 74 */     Page p = this.service.getPage(site.getId(), sortname, sortorder, 
/* 75 */       page.intValue(), pagesize.intValue());
/* 76 */     model.addAttribute("p", p);
/* 77 */     response.setHeader("Cache-Control", "no-cache");
/* 78 */     response.setContentType("text/json;charset=UTF-8");
/* 79 */     return "extraFunc/advert/slot/data";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:advertSlot:delete"})
/*    */   @RequestMapping({"/advertSlot/o_ajax_delete.do"})
/*    */   public void deleteAdvertSlot(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 86 */     JSONObject json = new JSONObject();
/* 87 */     AdvertSlot[] beans = this.service.deleteByIds(ids);
/* 88 */     for (AdvertSlot bean : beans) {
/* 89 */       log.info("delete AdvertSlot id={}", bean.getId());
/* 90 */       this.logService.operating(request, "删除广告位", "id=" + bean.getId());
/*    */     }
/* 92 */     json.put("success", true);
/* 93 */     json.put("status", 1);
/* 94 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.AdvertSlotAct
 * JD-Core Version:    0.6.1
 */