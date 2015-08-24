/*    */ package com.portal.extrafunc.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.datacenter.operatedata.service.LogService;
/*    */ import com.portal.extrafunc.entity.Comment;
/*    */ import com.portal.extrafunc.entity.CommentExt;
/*    */ import com.portal.extrafunc.service.CommentService;
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
/*    */ public class CommentAct
/*    */ {
/* 27 */   private static final Logger log = LoggerFactory.getLogger(CommentAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private LogService logService;
/*    */ 
/*    */   @Autowired
/*    */   private CommentService service;
/*    */ 
/* 32 */   @RequiresPermissions({"admin:comment:list"})
/*    */   @RequestMapping({"/comment/v_list.do"})
/*    */   public String list(Integer docId, ModelMap model) { model.addAttribute("docId", docId);
/* 33 */     return "extraFunc/comment/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:comment:edit"})
/*    */   @RequestMapping({"/comment/v_edit.do"})
/*    */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/* 39 */     model.addAttribute("comment", this.service.findById(id));
/* 40 */     return "extraFunc/comment/edit";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:comment:update"})
/*    */   @RequestMapping({"/comment/o_update.do"})
/*    */   public String update(Comment bean, CommentExt ext, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 47 */     bean = this.service.update(bean, ext);
/* 48 */     log.info("update Comment id={}.", bean.getId());
/* 49 */     this.logService.operating(request, "修改评论", "id=" + bean.getId());
/* 50 */     model.addAttribute("msg", "评论修改成功!");
/* 51 */     return edit(bean.getId(), request, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/comment/jsonData.do"})
/*    */   public String dataPageByJosn(Integer docId, Integer parentId, Boolean checked, String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 59 */     Site site = ContextTools.getSite(request);
/* 60 */     Page p = this.service.getPage(site.getId(), docId, parentId, 
/* 61 */       checked, null, 0, sortname, sortorder, page.intValue(), pagesize.intValue());
/* 62 */     model.addAttribute("p", p);
/* 63 */     response.setHeader("Cache-Control", "no-cache");
/* 64 */     response.setContentType("text/json;charset=UTF-8");
/* 65 */     return "extraFunc/comment/data";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:comment:delete"})
/*    */   @RequestMapping({"/comment/o_ajax_delete.do"})
/*    */   public void deleteComment(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 72 */     JSONObject json = new JSONObject();
/* 73 */     Comment[] beans = this.service.deleteByIds(ids);
/* 74 */     for (Comment bean : beans) {
/* 75 */       log.info("delete Comment id={}", bean.getId());
/* 76 */       this.logService.operating(request, "删除评论", "id=" + bean.getId());
/*    */     }
/* 78 */     json.put("success", true);
/* 79 */     json.put("status", 1);
/* 80 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:comment:check"})
/*    */   @RequestMapping({"/comment/o_ajax_check.do"})
/*    */   public void checkComment(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*    */   {
/* 88 */     JSONObject json = new JSONObject();
/* 89 */     Comment[] beans = this.service.checkByIds(ids);
/* 90 */     for (Comment bean : beans) {
/* 91 */       log.info("check Comment id={}", bean.getId());
/* 92 */       this.logService.operating(request, "审核评论", "id=" + bean.getId());
/*    */     }
/* 94 */     json.put("success", true);
/* 95 */     json.put("status", 1);
/* 96 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.CommentAct
 * JD-Core Version:    0.6.1
 */