/*    */ package com.portal.datacenter.lucene;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import java.io.IOException;
/*    */ import java.util.Date;
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
/*    */ public class LuceneDocAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private LuceneDocService luceneDocService;
/*    */ 
/*    */   @RequiresPermissions({"admin:lucene:index"})
/*    */   @RequestMapping({"/lucene/v_index.do"})
/*    */   public String index(HttpServletRequest request, ModelMap model)
/*    */   {
/* 27 */     Site site = ContextTools.getSite(request);
/* 28 */     model.addAttribute("site", site);
/* 29 */     return "dataCenter/docData/lucene/index";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:lucene:create"})
/*    */   @RequestMapping({"/lucene/o_create.do"})
/*    */   public void create(Integer channelId, Date startDate, Date endDate, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException
/*    */   {
/* 37 */     Site site = ContextTools.getSite(request);
/*    */     try
/*    */     {
/* 40 */       Integer docId = this.luceneDocService.createSearchIndex(site.getId(), channelId, 
/* 41 */         startDate, endDate, null, Integer.valueOf(1000), true);
/* 42 */       JSONObject json = new JSONObject();
/* 43 */       while (docId != null) {
/* 44 */         docId = this.luceneDocService.createSearchIndex(site.getId(), 
/* 45 */           channelId, startDate, endDate, docId, Integer.valueOf(1000), false);
/*    */       }
/* 47 */       json.put("success", true);
/* 48 */       ResponseUtils.renderJson(response, json.toString());
/*    */     } catch (IOException e) {
/* 50 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.lucene.LuceneDocAct
 * JD-Core Version:    0.6.1
 */