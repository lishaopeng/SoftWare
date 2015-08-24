/*    */ package com.portal.govcenter.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.datacenter.operatedata.service.LogService;
/*    */ import com.portal.govcenter.entity.MailboxType;
/*    */ import com.portal.govcenter.service.MailboxTypeService;
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
/*    */ public class MailboxTypeAct
/*    */ {
/* 28 */   private static final Logger log = LoggerFactory.getLogger(MailboxTypeAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private MailboxTypeService service;
/*    */ 
/*    */   @Autowired
/*    */   private LogService logService;
/*    */ 
/* 33 */   @RequiresPermissions({"admin:mailboxType:list"})
/*    */   @RequestMapping({"/mailboxType/v_list.do"})
/*    */   public String list() { return "govCenter/type/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:mailboxType:add"})
/*    */   @RequestMapping({"/mailboxType/v_add.do"})
/*    */   public String add(ModelMap model) {
/* 39 */     return "govCenter/type/add";
/*    */   }
/*    */   @RequiresPermissions({"admin:mailboxType:edit"})
/*    */   @RequestMapping({"/mailboxType/v_edit.do"})
/*    */   public String edit(Integer id, ModelMap model) {
/* 45 */     model.addAttribute("mailboxType", this.service.findById(id));
/* 46 */     return "govCenter/type/edit";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:mailboxType:save"})
/*    */   @RequestMapping({"/mailboxType/o_save.do"})
/*    */   public String save(MailboxType bean, HttpServletRequest request, ModelMap model) {
/* 53 */     Site site = ContextTools.getSite(request);
/* 54 */     bean = this.service.save(bean, site);
/* 55 */     log.info("save MailboxType id={}", bean.getId());
/* 56 */     model.addAttribute("msg", "类型添加成功!");
/* 57 */     return list();
/*    */   }
/*    */   @RequiresPermissions({"admin:mailboxType:update"})
/*    */   @RequestMapping({"/mailboxType/o_update.do"})
/*    */   public String update(MailboxType bean, ModelMap model) {
/* 63 */     bean = this.service.update(bean);
/* 64 */     log.info("update MailboxType id={}.", bean.getId());
/* 65 */     model.addAttribute("msg", "类型修改成功!");
/* 66 */     return list();
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/mailboxType/jsonData.do"})
/*    */   public String dataPageByJosn(String name, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 73 */     Site site = ContextTools.getSite(request);
/* 74 */     List list = this.service.getList(site.getId());
/* 75 */     model.addAttribute("list", list);
/* 76 */     response.setHeader("Cache-Control", "no-cache");
/* 77 */     response.setContentType("text/json;charset=UTF-8");
/* 78 */     return "govCenter/type/data";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:mailboxType:delete"})
/*    */   @RequestMapping({"/mailboxType/o_ajax_delete.do"})
/*    */   public void deleteMailbox(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 85 */     JSONObject json = new JSONObject();
/* 86 */     MailboxType[] beans = this.service.deleteByIds(ids);
/* 87 */     for (MailboxType bean : beans) {
/* 88 */       log.info("delete MailboxType id={}", bean.getId());
/* 89 */       this.logService.operating(request, "删除局长邮箱类型", "id=" + bean.getId());
/*    */     }
/* 91 */     json.put("success", true);
/* 92 */     json.put("status", 1);
/* 93 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.action.MailboxTypeAct
 * JD-Core Version:    0.6.1
 */