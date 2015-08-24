/*    */ package com.portal.extrafunc.action.fnt;
/*    */ 
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import com.portal.sysmgr.utils.ViewTools;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class ForumAct
/*    */ {
/*    */   public static final String FORUM_INDEX = "tpl.forumIndex";
/*    */ 
/*    */   @RequestMapping(value={"/forum.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String index(HttpServletRequest request, ModelMap model)
/*    */   {
/* 29 */     Site site = ContextTools.getSite(request);
/* 30 */     ViewTools.frontData(request, model, site);
/* 31 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 32 */       "extrafunc/forum", "tpl.forumIndex");
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.fnt.ForumAct
 * JD-Core Version:    0.6.1
 */