/*    */ package com.portal.doccenter.action.fnt;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import com.portal.sysmgr.utils.ViewTools;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class RssAct
/*    */ {
/*    */   public static final String RSS_TPL = "tpl.rss";
/*    */ 
/*    */   @RequestMapping(value={"/rss.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String rss(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 25 */     response.setContentType("text/xml;charset=UTF-8");
/* 26 */     Site site = ContextTools.getSite(request);
/* 27 */     ViewTools.frontData(request, model, site);
/* 28 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 29 */       "extrafunc/rss", "tpl.rss");
/*    */   }
/*    */ 
/*    */   @RequestMapping(value={"/showmsg.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public void showmsg(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 35 */     ResponseUtils.render(response, "text/html;charset=UTF-8", "欢迎使用JAVAPMS！");
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.fnt.RssAct
 * JD-Core Version:    0.6.1
 */