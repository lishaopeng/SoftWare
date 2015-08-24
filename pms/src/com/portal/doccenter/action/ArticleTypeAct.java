/*    */ package com.portal.doccenter.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.datacenter.operatedata.service.LogService;
/*    */ import com.portal.doccenter.entity.ArticleType;
/*    */ import com.portal.doccenter.service.ArticleTypeService;
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
/*    */ public class ArticleTypeAct
/*    */ {
/* 26 */   private static final Logger log = LoggerFactory.getLogger(ArticleTypeAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private LogService logService;
/*    */ 
/*    */   @Autowired
/*    */   private ArticleTypeService service;
/*    */ 
/* 32 */   @RequiresPermissions({"admin:doctype:list"})
/*    */   @RequestMapping({"/type/v_list.do"})
/*    */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { return "docCenter/config/type/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:doctype:add"})
/*    */   @RequestMapping({"/type/v_add.do"})
/*    */   public String add(ModelMap model) {
/* 38 */     return "docCenter/config/type/add";
/*    */   }
/*    */   @RequiresPermissions({"admin:doctype:edit"})
/*    */   @RequestMapping({"/type/v_edit.do"})
/*    */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/* 44 */     model.addAttribute("type", this.service.findById(id));
/* 45 */     return "docCenter/config/type/edit";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:doctype:save"})
/*    */   @RequestMapping({"/type/o_save.do"})
/*    */   public String save(ArticleType bean, HttpServletRequest request, ModelMap model) {
/* 52 */     bean = this.service.save(bean);
/* 53 */     log.info("save Type id={}", bean.getId());
/* 54 */     this.logService.operating(request, "类型添加", "id=" + bean.getId() + ";name=" + 
/* 55 */       bean.getName());
/* 56 */     model.addAttribute("msg", "类型添加成功");
/* 57 */     return add(model);
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:doctype:update"})
/*    */   @RequestMapping({"/type/o_update.do"})
/*    */   public String update(ArticleType bean, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 64 */     bean = this.service.update(bean);
/* 65 */     log.info("update Type id={}.", bean.getId());
/* 66 */     this.logService.operating(request, "类型修改", "id=" + bean.getId() + ";name=" + 
/* 67 */       bean.getName());
/* 68 */     model.addAttribute("msg", "类型修改成功");
/* 69 */     return edit(bean.getId(), request, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/type/jsonData.do"})
/*    */   public String dataPageByJosn(String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 76 */     List list = this.service.getList(true, sortname, sortorder);
/* 77 */     model.addAttribute("list", list);
/* 78 */     response.setHeader("Cache-Control", "no-cache");
/* 79 */     response.setContentType("text/json;charset=UTF-8");
/* 80 */     return "docCenter/config/type/dataJson";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:doctype:delete"})
/*    */   @RequestMapping({"/type/o_ajax_delete.do"})
/*    */   public void deleteType(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 87 */     JSONObject json = new JSONObject();
/* 88 */     ArticleType[] beans = this.service.deleteByIds(ids);
/* 89 */     for (ArticleType bean : beans) {
/* 90 */       log.info("delete Type id={}", bean.getId());
/* 91 */       this.logService.operating(request, "删除类型", "id=" + bean.getId() + 
/* 92 */         ";name=" + bean.getName());
/*    */     }
/* 94 */     json.put("success", true);
/* 95 */     json.put("status", 1);
/* 96 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.ArticleTypeAct
 * JD-Core Version:    0.6.1
 */