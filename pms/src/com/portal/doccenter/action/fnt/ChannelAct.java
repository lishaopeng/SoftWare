/*    */ package com.portal.doccenter.action.fnt;
/*    */ 
/*    */ import com.javapms.basic.utils.ServicesUtils;
/*    */ import com.portal.doccenter.entity.Channel;
/*    */ import com.portal.doccenter.service.ChannelService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.staticpage.StaticPageService;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import com.portal.sysmgr.utils.ViewTools;
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
/*    */ public class ChannelAct
/*    */ {
/*    */   public static final String TPL_INDEX = "tpl.index";
/*    */   public static final String TPL_CHANNEL_LEADER = "tpl.channelLeader";
/*    */ 
/*    */   @Autowired
/*    */   private ChannelService channelService;
/*    */ 
/*    */   @Autowired
/*    */   private StaticPageService staticPageService;
/*    */ 
/*    */   @RequestMapping({"/", "/index.jsp"})
/*    */   public String index(HttpServletRequest request, ModelMap model)
/*    */   {
/* 37 */     Site site = ContextTools.getSite(request);
/*    */ 
/* 39 */     if (!site.getStaticChannel().equals(Site.NO_STATIC)) {
/* 40 */       return site.getStaticRealPath();
/*    */     }
/* 42 */     ViewTools.frontData(request, model, site);
/* 43 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 44 */       "doc/index", "tpl.index");
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/{path}/index.jsp"})
/*    */   public String channelIndex(@PathVariable String path, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 51 */     return channelIndexpage(path, Integer.valueOf(1), request, response, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/{path}/index_{page:[0-9]+}.jsp"})
/*    */   public String channelIndexpage(@PathVariable String path, @PathVariable Integer page, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 58 */     Site site = ContextTools.getSite(request);
/* 59 */     Channel channel = this.channelService.findByPathForTag(path, site.getId());
/* 60 */     if (channel == null) {
/* 61 */       return ViewTools.pageNotFound(response);
/*    */     }
/* 63 */     if (!StringUtils.isBlank(channel.getLink())) {
/* 64 */       return "redirect:" + channel.getLink();
/*    */     }
/* 66 */     if (channel.getStaticChannel()) {
/* 67 */       this.staticPageService.staticChannelCheck(channel, page);
/* 68 */       return channel.getChannelRealPath(page);
/*    */     }
/* 70 */     model.addAttribute("channel", channel);
/* 71 */     ViewTools.frontData(request, model, site);
/* 72 */     ViewTools.frontPageData(channel.getUrl(page), model, page);
/* 73 */     return channel.getTplChannelOrDef();
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/channel/leader.jsp"})
/*    */   public String channeltpl(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 79 */     return channeltplpage(Integer.valueOf(1), request, response, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/channel/leader_{page:[0-9]+}.jsp"})
/*    */   public String channeltplpage(@PathVariable Integer page, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 86 */     Site site = ContextTools.getSite(request);
/* 87 */     model.putAll(ServicesUtils.getQueryParams(request));
/* 88 */     ViewTools.frontData(request, model, site);
/* 89 */     ViewTools.frontPageData(request, model, page);
/* 90 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 91 */       "doc/channel", "tpl.channelLeader");
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.fnt.ChannelAct
 * JD-Core Version:    0.6.1
 */