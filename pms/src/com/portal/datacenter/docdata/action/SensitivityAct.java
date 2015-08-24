/*    */ package com.portal.datacenter.docdata.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.datacenter.docdata.entity.Sensitivity;
/*    */ import com.portal.datacenter.docdata.service.SensitivityService;
/*    */ import com.portal.datacenter.operatedata.service.LogService;
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
/*    */ public class SensitivityAct
/*    */ {
/* 26 */   private static final Logger log = LoggerFactory.getLogger(SensitivityAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private LogService logService;
/*    */ 
/*    */   @Autowired
/*    */   private SensitivityService service;
/*    */ 
/* 31 */   @RequiresPermissions({"admin:sensitivity:list"})
/*    */   @RequestMapping({"/sensitivity/v_list.do"})
/*    */   public String list() { return "dataCenter/docData/sensitivity/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:sensitivity:save"})
/*    */   @RequestMapping({"/sensitivity/o_save.do"})
/*    */   public String save(Sensitivity bean, ModelMap model) {
/* 37 */     bean = this.service.save(bean);
/* 38 */     model.addAttribute("msg", "敏感词添加成功!");
/* 39 */     return list();
/*    */   }
/*    */   @RequiresPermissions({"admin:sensitivity:update"})
/*    */   @RequestMapping({"/sensitivity/o_update.do"})
/*    */   public String update(Sensitivity bean, ModelMap model) {
/* 45 */     this.service.update(bean);
/* 46 */     model.addAttribute("msg", "敏感词修改成功!");
/* 47 */     return list();
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/sensitivity/jsonData.do"})
/*    */   public String dataPageByJosn(String sortname, String sortorder, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 54 */     List list = this.service.getList(false, sortname, sortorder);
/* 55 */     model.addAttribute("list", list);
/* 56 */     response.setHeader("Cache-Control", "no-cache");
/* 57 */     response.setContentType("text/json;charset=UTF-8");
/* 58 */     return "dataCenter/docData/sensitivity/listdata";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:sensitivity:delete"})
/*    */   @RequestMapping({"/sensitivity/o_ajax_delete.do"})
/*    */   public void deleteAdvertiseSpace(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 65 */     JSONObject json = new JSONObject();
/* 66 */     Sensitivity[] beans = this.service.deleteByIds(ids);
/* 67 */     for (Sensitivity bean : beans) {
/* 68 */       log.info("delete Sensitivity id={}", bean.getId());
/* 69 */       this.logService.operating(request, "删除敏感词", "id=" + bean.getId() + 
/* 70 */         ";name=" + bean.getSearch());
/*    */     }
/* 72 */     json.put("success", true);
/* 73 */     json.put("status", 1);
/* 74 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.action.SensitivityAct
 * JD-Core Version:    0.6.1
 */