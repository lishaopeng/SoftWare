/*    */ package com.portal.datacenter.commdata.action.fnt;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.datacenter.commdata.service.SpecialtyService;
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
/*    */ public class SpecialtyAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SpecialtyService specialtyService;
/*    */ 
/*    */   @RequestMapping(value={"/specialChild.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public void specialChild(Integer specialId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */     throws JSONException
/*    */   {
/* 26 */     List specList = this.specialtyService
/* 27 */       .getSpecialtyChild(specialId);
/* 28 */     JSONObject json = new JSONObject(ResponseUtils.listToJson(specList));
/* 29 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.action.fnt.SpecialtyAct
 * JD-Core Version:    0.6.1
 */