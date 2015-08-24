/*    */ package com.portal.extrafunc.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.datacenter.operatedata.service.LogService;
/*    */ import com.portal.extrafunc.entity.MessageType;
/*    */ import com.portal.extrafunc.service.MessageTypeService;
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
/*    */ public class MessageTypeAct
/*    */ {
/* 28 */   private static final Logger log = LoggerFactory.getLogger(MessageTypeAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private MessageTypeService service;
/*    */ 
/*    */   @Autowired
/*    */   private LogService logService;
/*    */ 
/* 33 */   @RequiresPermissions({"admin:messageType:list"})
/*    */   @RequestMapping({"/messageType/v_list.do"})
/*    */   public String list() { return "extraFunc/board/type/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:messageType:add"})
/*    */   @RequestMapping({"/messageType/v_add.do"})
/*    */   public String add(ModelMap model) {
/* 39 */     return "extraFunc/board/type/add";
/*    */   }
/*    */   @RequiresPermissions({"admin:messageType:edit"})
/*    */   @RequestMapping({"/messageType/v_edit.do"})
/*    */   public String edit(Integer id, ModelMap model) {
/* 45 */     model.addAttribute("messageType", this.service.findById(id));
/* 46 */     return "extraFunc/board/type/edit";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:messageType:save"})
/*    */   @RequestMapping({"/messageType/o_save.do"})
/*    */   public String save(MessageType bean, HttpServletRequest request, ModelMap model) {
/* 53 */     Site site = ContextTools.getSite(request);
/* 54 */     bean = this.service.save(bean, site);
/* 55 */     log.info("save MessageType id={}", bean.getId());
/* 56 */     model.addAttribute("msg", "类型添加成功!");
/* 57 */     return list();
/*    */   }
/*    */   @RequiresPermissions({"admin:messageType:update"})
/*    */   @RequestMapping({"/messageType/o_update.do"})
/*    */   public String update(MessageType bean, ModelMap model) {
/* 63 */     bean = this.service.update(bean);
/* 64 */     log.info("update MessageType id={}.", bean.getId());
/* 65 */     model.addAttribute("msg", "类型修改成功!");
/* 66 */     return list();
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/messageType/jsonData.do"})
/*    */   public String dataPageByJosn(String name, String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 74 */     Site site = ContextTools.getSite(request);
/* 75 */     List list = this.service.getList(site.getId(), sortname, 
/* 76 */       sortorder);
/* 77 */     model.addAttribute("list", list);
/* 78 */     response.setHeader("Cache-Control", "no-cache");
/* 79 */     response.setContentType("text/json;charset=UTF-8");
/* 80 */     return "extraFunc/board/type/data";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:messageType:delete"})
/*    */   @RequestMapping({"/messageType/o_ajax_delete.do"})
/*    */   public void deleteMailbox(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 87 */     JSONObject json = new JSONObject();
/* 88 */     MessageType[] beans = this.service.deleteByIds(ids);
/* 89 */     for (MessageType bean : beans) {
/* 90 */       log.info("delete MessageType id={}", bean.getId());
/* 91 */       this.logService.operating(request, "删除留言板类型", "id=" + bean.getId());
/*    */     }
/* 93 */     json.put("success", true);
/* 94 */     json.put("status", 1);
/* 95 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.MessageTypeAct
 * JD-Core Version:    0.6.1
 */