/*    */ package com.portal.datacenter.docdata.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.doccenter.entity.Channel;
/*    */ import com.portal.doccenter.service.ChannelService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.staticpage.StaticPageService;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import freemarker.template.TemplateException;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class StaticPageAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private StaticPageService staticPageService;
/*    */ 
/*    */   @Autowired
/*    */   private ChannelService channelService;
/*    */ 
/*    */   @RequiresPermissions({"admin:static:view"})
/*    */   @RequestMapping({"/static/v_static_index.do"})
/*    */   public String staticIndex(HttpServletRequest request, HttpServletResponse response)
/*    */   {
/* 41 */     return "dataCenter/staticPage/index";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:static:index"})
/*    */   @RequestMapping({"/static/o_static_index.do"})
/*    */   public void staticIndexop(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException, JSONException
/*    */   {
/* 49 */     JSONObject json = new JSONObject();
/* 50 */     Site site = ContextTools.getSite(request);
/* 51 */     int i = this.staticPageService.staticIndex(site);
/* 52 */     json.put("i", i);
/* 53 */     json.put("success", true);
/* 54 */     json.put("status", 1);
/* 55 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:static:channel"})
/*    */   @RequestMapping({"/static/o_static_channel.do"})
/*    */   public String staticChannel(Integer chnlId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 63 */     List list = new ArrayList();
/* 64 */     if (chnlId != null) {
/* 65 */       Channel c = this.channelService.findById(chnlId);
/* 66 */       list = this.staticPageService.staticChannelPage(c);
/*    */     } else {
/* 68 */       list = this.staticPageService.staticChannelPage(null);
/*    */     }
/* 70 */     model.addAttribute("list", list);
/* 71 */     response.setHeader("Cache-Control", "no-cache");
/* 72 */     response.setContentType("text/json;charset=UTF-8");
/* 73 */     return "dataCenter/staticPage/alert";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:static:article"})
/*    */   @RequestMapping({"/static/o_static_article.do"})
/*    */   public void staticArticle(Integer chnlId, HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException, JSONException
/*    */   {
/* 81 */     JSONObject json = new JSONObject();
/* 82 */     if (chnlId != null) {
/* 83 */       Channel c = this.channelService.findById(chnlId);
/* 84 */       String s = this.staticPageService.staticArticlePage(c);
/* 85 */       json.put("msg", s);
/*    */     } else {
/* 87 */       String s = this.staticPageService.staticArticlePage(null);
/* 88 */       json.put("msg", s);
/*    */     }
/* 90 */     json.put("success", true);
/* 91 */     json.put("status", 1);
/* 92 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.action.StaticPageAct
 * JD-Core Version:    0.6.1
 */