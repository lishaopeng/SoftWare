/*    */ package com.portal.datacenter.commdata.action.fnt;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.datacenter.commdata.service.IndustryService;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class IndustryAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private IndustryService industryService;
/*    */ 
/*    */   @RequestMapping(value={"/industryChild.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public void industryChild(Integer industryId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */     throws JSONException
/*    */   {
/* 26 */     List induList = this.industryService.getIndustryChild(industryId);
/* 27 */     JSONObject json = new JSONObject(ResponseUtils.listToJson(induList));
/* 28 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.action.fnt.IndustryAct
 * JD-Core Version:    0.6.1
 */