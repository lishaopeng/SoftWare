/*    */ package com.portal.datacenter.docdata.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.datacenter.docdata.entity.Keyword;
/*    */ import com.portal.datacenter.docdata.service.KeywordService;
/*    */ import com.portal.datacenter.operatedata.service.LogService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class KeywordAct
/*    */ {
/* 27 */   private static final Logger log = LoggerFactory.getLogger(KeywordAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private LogService logService;
/*    */ 
/*    */   @Autowired
/*    */   private KeywordService service;
/*    */ 
/* 32 */   @RequiresPermissions({"admin:keyword:list"})
/*    */   @RequestMapping({"/keyword/v_list.do"})
/*    */   public String list() { return "dataCenter/docData/keyword/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:keyword:save"})
/*    */   @RequestMapping({"/keyword/o_save.do"})
/*    */   public String save(Keyword bean, HttpServletRequest request, ModelMap model) {
/* 38 */     Site site = ContextTools.getSite(request);
/* 39 */     bean = this.service.save(bean, site);
/* 40 */     log.info("save Keyword id={}", bean.getId());
/* 41 */     this.logService.operating(request, "添加关键字", "id=" + bean.getId() + ";name=" + 
/* 42 */       bean.getName());
/* 43 */     model.addAttribute("msg", "关键字添加成功!");
/* 44 */     return list();
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:keyword:update"})
/*    */   @RequestMapping({"/keyword/o_update.do"})
/*    */   public String update(Keyword bean, HttpServletRequest request, ModelMap model) {
/* 51 */     this.service.update(bean);
/* 52 */     log.info("update Keyword");
/* 53 */     this.logService.operating(request, "修改关键字", null);
/* 54 */     model.addAttribute("msg", "关键字修改成功!");
/* 55 */     return list();
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/keyword/jsonData.do"})
/*    */   public String dataPageByJosn(String name, Integer departId, String sortname, String sortorder, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 62 */     Site site = ContextTools.getSite(request);
/* 63 */     List list = this.service.getListBySiteId(site.getId(), false, 
/* 64 */       false, sortname, sortorder);
/* 65 */     model.addAttribute("list", list);
/* 66 */     response.setHeader("Cache-Control", "no-cache");
/* 67 */     response.setContentType("text/json;charset=UTF-8");
/* 68 */     return "dataCenter/docData/keyword/listdata";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:keyword:delete"})
/*    */   @RequestMapping({"/keyword/o_ajax_delete.do"})
/*    */   public void deleteRole(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 75 */     JSONObject json = new JSONObject();
/* 76 */     Keyword[] beans = this.service.deleteByIds(ids);
/* 77 */     for (Keyword bean : beans) {
/* 78 */       log.info("delete Keyword id={}", bean.getId());
/* 79 */       this.logService.operating(request, "删除关键字", "id=" + bean.getId() + 
/* 80 */         ";name=" + bean.getName());
/*    */     }
/* 82 */     json.put("success", true);
/* 83 */     json.put("status", 1);
/* 84 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.action.KeywordAct
 * JD-Core Version:    0.6.1
 */