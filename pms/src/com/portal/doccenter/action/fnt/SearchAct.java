/*    */ package com.portal.doccenter.action.fnt;
/*    */ 
/*    */ import com.portal.doccenter.entity.Model;
/*    */ import com.portal.doccenter.service.ModelService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import com.portal.sysmgr.utils.URLTools;
/*    */ import com.portal.sysmgr.utils.ViewTools;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class SearchAct
/*    */ {
/*    */   public static final String SEARCH_INPUT = "tpl.searchInput";
/*    */   public static final String SEARCH_RESULT = "tpl.searchResult";
/*    */   public static final String STATIS_SEARCH = "tpl.statisSearch";
/*    */ 
/*    */   @Autowired
/*    */   private ModelService modelService;
/*    */ 
/*    */   @RequestMapping(value={"/query.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*    */   public String query(HttpServletRequest request, HttpServletResponse response)
/*    */     throws UnsupportedEncodingException
/*    */   {
/* 35 */     String url = URLTools.getUrlFromParamter(request);
/* 36 */     return "redirect:search" + url + ".jsp";
/*    */   }
/*    */ 
/*    */   @RequestMapping(value={"/search-{q}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String search(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */     throws UnsupportedEncodingException
/*    */   {
/* 43 */     Integer pageNo = URLTools.getPageNo(request);
/* 44 */     Map map = URLTools.getAllParamter(request);
/* 45 */     model.addAllAttributes(map);
/* 46 */     String mId = (String)map.get("mId");
/* 47 */     return searchpage(pageNo, mId, request, response, model);
/*    */   }
/*    */ 
/*    */   private String searchpage(Integer page, String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 53 */     Site site = ContextTools.getSite(request);
/* 54 */     ViewTools.frontData(request, model, site);
/* 55 */     ViewTools.frontPageData(request, model, page);
/* 56 */     if (!StringUtils.isBlank(mId)) {
/* 57 */       Integer modelId = Integer.valueOf(Integer.parseInt(mId));
/* 58 */       Model m = this.modelService.findById(modelId);
/* 59 */       if ((m != null) && (!StringUtils.isBlank(m.getTplSearch()))) {
/* 60 */         String root = site.getSolutionPath();
/* 61 */         return root + m.getTplSearch();
/*    */       }
/*    */     }
/* 64 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 65 */       "extrafunc/search", "tpl.searchResult");
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/statisSearch.jsp"})
/*    */   public String countStatis(Integer cid, Integer did, String start, String end, HttpServletRequest request, ModelMap model)
/*    */   {
/* 71 */     return countStatisPage(Integer.valueOf(1), cid, did, start, end, request, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/statisSearch_{page:[0-9]+}.jsp"})
/*    */   public String countStatisPage(@PathVariable Integer page, Integer cid, Integer did, String start, String end, HttpServletRequest request, ModelMap model)
/*    */   {
/* 78 */     Site site = ContextTools.getSite(request);
/* 79 */     model.addAttribute("cid", cid);
/* 80 */     model.addAttribute("did", did);
/* 81 */     model.addAttribute("start", start);
/* 82 */     model.addAttribute("end", end);
/* 83 */     ViewTools.frontData(request, model, site);
/* 84 */     ViewTools.frontPageData(request, model, page);
/* 85 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 86 */       "extrafunc/search", "tpl.statisSearch");
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.fnt.SearchAct
 * JD-Core Version:    0.6.1
 */