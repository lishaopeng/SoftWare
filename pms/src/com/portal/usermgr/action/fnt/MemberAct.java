/*     */ package com.portal.usermgr.action.fnt;
/*     */ 
/*     */ import com.javapms.basic.security.encoder.PwdEncoder;
/*     */ import com.javapms.basic.upload.FileRepository;
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.javapms.basic.utils.ServicesUtils;
/*     */ import com.portal.doccenter.entity.Article;
/*     */ import com.portal.doccenter.entity.ArticleExt;
/*     */ import com.portal.doccenter.entity.ArticleTxt;
/*     */ import com.portal.doccenter.entity.Model;
/*     */ import com.portal.doccenter.service.ArticleService;
/*     */ import com.portal.doccenter.service.ArticleTypeService;
/*     */ import com.portal.doccenter.service.ChannelService;
/*     */ import com.portal.doccenter.service.ModelFieldService;
/*     */ import com.portal.doccenter.service.ModelService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.sysmgr.utils.ViewTools;
/*     */ import com.portal.usermgr.entity.Member;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.MemberService;
/*     */ import com.portal.usermgr.service.UserBindService;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/*     */ 
/*     */ @Controller
/*     */ public class MemberAct
/*     */ {
/*     */   public static final String MEMBER_INDEX = "tpl.memberIndex";
/*     */   public static final String MEMBER_EDIT_PASS = "tpl.memberEditPass";
/*     */   public static final String MEMBER_EDIT_INFO = "tpl.memberEditInfo";
/*     */   public static final String DOC_LIST = "tpl.docList";
/*     */   public static final String DOC_ADD = "tpl.docAdd";
/*     */   public static final String DOC_EDIT = "tpl.docEdit";
/*     */   public static final String MY_THEME = "tpl.myTheme";
/*     */   public static final String MY_REPLY = "tpl.myReply";
/*     */ 
/*     */   @Autowired
/*     */   private MemberService memberService;
/*     */ 
/*     */   @Autowired
/*     */   private ModelService modelService;
/*     */ 
/*     */   @Autowired
/*     */   private ModelFieldService modelFieldService;
/*     */ 
/*     */   @Autowired
/*     */   private ArticleTypeService articleTypeService;
/*     */ 
/*     */   @Autowired
/*     */   private UserBindService userBindService;
/*     */ 
/*     */   @Autowired
/*     */   private ChannelService channelService;
/*     */ 
/*     */   @Autowired
/*     */   private ArticleService service;
/*     */ 
/*     */   @Autowired
/*     */   private PwdEncoder pwdEncoder;
/*     */ 
/*     */   @Autowired
/*     */   private FileRepository fileRepository;
/*     */ 
/*     */   @RequestMapping(value={"/member/index.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  69 */     Site site = ContextTools.getSite(request);
/*  70 */     User user = ContextTools.getUser(request);
/*  71 */     if (user == null) {
/*  72 */       return ViewTools.showLogin(request, model, "必须登录才可以访问该页面!");
/*     */     }
/*  74 */     ViewTools.frontData(request, model, site);
/*  75 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  76 */       "user", "tpl.memberIndex");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/editPass.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String editPass(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  82 */     Site site = ContextTools.getSite(request);
/*  83 */     User user = ContextTools.getUser(request);
/*  84 */     if (user == null) {
/*  85 */       return ViewTools.showLogin(request, model, "必须登录才可以访问该页面!");
/*     */     }
/*  87 */     ViewTools.frontData(request, model, site);
/*  88 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/*  89 */       "user", "tpl.memberEditPass");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/editPass.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String editPassSumbit(String oldpassword, String password, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  96 */     Site site = ContextTools.getSite(request);
/*  97 */     User user = ContextTools.getUser(request);
/*  98 */     if (user == null) {
/*  99 */       return ViewTools.showLogin(request, model, "必须登录才可以访问该页面!");
/*     */     }
/* 101 */     if (user.getPassword().equals(this.pwdEncoder.encodePassword(oldpassword))) {
/* 102 */       this.memberService.updatePass(user.getId(), password);
/* 103 */       model.addAttribute("msg", "密码修改成功!");
/* 104 */       model.addAttribute("status", Integer.valueOf(1));
/*     */     } else {
/* 106 */       model.addAttribute("msg", "原密码错误，修改失败!");
/* 107 */       model.addAttribute("status", Integer.valueOf(0));
/*     */     }
/* 109 */     ViewTools.frontData(request, model, site);
/* 110 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 111 */       "user", "tpl.memberEditPass");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/editInfo.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String editInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 117 */     Site site = ContextTools.getSite(request);
/* 118 */     User user = ContextTools.getUser(request);
/* 119 */     if (user == null) {
/* 120 */       return ViewTools.showLogin(request, model, "必须登录才可以访问该页面!");
/*     */     }
/* 122 */     ViewTools.frontData(request, model, site);
/* 123 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 124 */       "user", "tpl.memberEditInfo");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/editInfo.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String editInfoSubmit(User u, Member m, @RequestParam(value="file", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes ra)
/*     */   {
/* 132 */     Site site = ContextTools.getSite(request);
/* 133 */     User user = ContextTools.getUser(request);
/* 134 */     if (user == null) {
/* 135 */       return ViewTools.showLogin(request, model, "必须登录才可以访问该页面!");
/*     */     }
/* 137 */     if (!file.isEmpty()) {
/* 138 */       String fileUrl = this.fileRepository.uploadFile(file, 
/* 139 */         site.getUploadPath());
/* 140 */       m.setAvatar(fileUrl);
/*     */     }
/* 142 */     m.setRegisterIp(ServicesUtils.getIpAddr(request));
/* 143 */     this.memberService.updateMember(u, m, null, site.getId());
/* 144 */     ra.addFlashAttribute("msg", "基本资料修改成功!");
/* 145 */     return "redirect:index.jsp";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/docList.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String docList(String title, Integer chnlId, Integer[] typeIds, Integer[] modelIds, boolean top, boolean recommend, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 153 */     return docListpage(Integer.valueOf(1), title, chnlId, typeIds, modelIds, top, recommend, 
/* 154 */       request, response, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/docList_{page:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String docListpage(@PathVariable Integer page, String title, Integer chnlId, Integer[] typeIds, Integer[] modelIds, boolean top, boolean recommend, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 162 */     Site site = ContextTools.getSite(request);
/* 163 */     User user = ContextTools.getUser(request);
/* 164 */     if (user == null) {
/* 165 */       return ViewTools.showLogin(request, model, "必须登录才可以访问该页面!");
/*     */     }
/* 167 */     List typeList = this.articleTypeService.getList(false, null, 
/* 168 */       null);
/* 169 */     Page p = this.service.getPageDocByMember(title, typeIds, modelIds, 
/* 170 */       top, recommend, site.getId(), user.getId(), chnlId, page.intValue(), 15);
/* 171 */     model.addAttribute("page", p);
/* 172 */     model.addAttribute("typeList", typeList);
/* 173 */     model.addAttribute("modelList", this.modelService.getList(false, null, null));
/* 174 */     ViewTools.frontData(request, model, site);
/* 175 */     ViewTools.frontPageData(request, model, page);
/* 176 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 177 */       "user", "tpl.docList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/docAdd-{modelId:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String docAdd(@PathVariable Integer modelId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 184 */     Site site = ContextTools.getSite(request);
/* 185 */     User user = ContextTools.getUser(request);
/* 186 */     if (user == null) {
/* 187 */       return ViewTools.showLogin(request, model, "必须登录才可以访问该页面!");
/*     */     }
/* 189 */     Model m = this.modelService.findById(modelId);
/* 190 */     if (m == null) {
/* 191 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 193 */     List fieldList = this.modelFieldService.getList(m.getId(), 
/* 194 */       false, null, null);
/* 195 */     List typeList = this.articleTypeService.getList(false, null, 
/* 196 */       null);
/* 197 */     ViewTools.frontData(request, model, site);
/* 198 */     model.addAttribute("model", m);
/* 199 */     model.addAttribute("fieldList", fieldList);
/* 200 */     model.addAttribute("typeList", typeList);
/* 201 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 202 */       "user", "tpl.docAdd");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/docSave.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String docSave(Article bean, ArticleExt ext, ArticleTxt txt, Integer modelId, String[] attPaths, String[] attNames, String[] imgPaths, String[] imgDescs, Boolean[] thumbs, String[] imgStyles, Integer channelId, HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes ra)
/*     */   {
/* 211 */     Site site = ContextTools.getSite(request);
/* 212 */     User user = ContextTools.getUser(request);
/* 213 */     if (user == null) {
/* 214 */       return ViewTools.showLogin(request, model, "必须登录才可以访问该页面!");
/*     */     }
/* 216 */     bean.setSite(site);
/* 217 */     bean.setAttr(ServicesUtils.getRequestMap(request, "attr_"));
/* 218 */     bean = this.service.save(bean, ext, txt, site, user, null, attPaths, 
/* 219 */       attNames, imgPaths, imgDescs, thumbs, imgStyles, channelId, 
/* 220 */       modelId, true);
/* 221 */     ra.addFlashAttribute("msg", "文档添加成功，请等待审核!");
/* 222 */     return "redirect:docList.jsp";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/docEdit-{id:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String docEdit(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 228 */     Site site = ContextTools.getSite(request);
/* 229 */     User user = ContextTools.getUser(request);
/* 230 */     if (user == null) {
/* 231 */       return ViewTools.showLogin(request, model, "必须登录才可以访问该页面!");
/*     */     }
/* 233 */     Article article = this.service.findById(id);
/* 234 */     if ((article == null) || (!article.getUser().equals(user))) {
/* 235 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 237 */     List fieldList = this.modelFieldService.getList(article
/* 238 */       .getModel().getId(), false, null, null);
/* 239 */     List typeList = this.articleTypeService.getList(false, null, 
/* 240 */       null);
/* 241 */     model.addAttribute("article", article);
/* 242 */     model.addAttribute("channel", article.getChannel());
/* 243 */     model.addAttribute("fieldList", fieldList);
/* 244 */     model.addAttribute("typeList", typeList);
/* 245 */     ViewTools.frontData(request, model, site);
/* 246 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 247 */       "user", "tpl.docEdit");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/docUpdate.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String docUpdate(Article bean, ArticleExt ext, ArticleTxt txt, Integer[] channelIds, Integer[] topicIds, Integer[] viewGroupIds, String[] attPaths, String[] attNames, String[] imgPaths, String[] imgDescs, Boolean[] thumbs, String[] imgStyles, Integer channelId, HttpServletRequest request, ModelMap model, RedirectAttributes ra)
/*     */   {
/* 257 */     User user = ContextTools.getUser(request);
/* 258 */     Map attr = 
/* 259 */       ServicesUtils.getRequestMap(request, "attr_");
/* 260 */     bean = this.service.update(bean, ext, txt, channelIds, viewGroupIds, 
/* 261 */       attPaths, attNames, imgPaths, imgDescs, thumbs, imgStyles, 
/* 262 */       attr, channelId, user, true);
/* 263 */     ra.addFlashAttribute("msg", "文档修改成功，请等待审核!");
/* 264 */     return "redirect:docList.jsp";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/docDel-{id:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String docDel(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes ra)
/*     */   {
/* 270 */     User user = ContextTools.getUser(request);
/* 271 */     if (user == null) {
/* 272 */       return ViewTools.showLogin(request, model, "必须登录才可以访问该页面!");
/*     */     }
/* 274 */     Article article = this.service.findById(id);
/* 275 */     if ((article == null) || (!article.getUser().equals(user))) {
/* 276 */       return ViewTools.pageNotFound(response);
/*     */     }
/* 278 */     this.service.cycle(id);
/* 279 */     ra.addFlashAttribute("msg", "文档删除成功!");
/* 280 */     return "redirect:docList.jsp";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/themeList.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String themeList(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 286 */     return themeListpage(Integer.valueOf(1), request, response, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/themeList_{page:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String themeListpage(@PathVariable Integer page, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 293 */     Site site = ContextTools.getSite(request);
/* 294 */     User user = ContextTools.getUser(request);
/* 295 */     if (user == null) {
/* 296 */       return ViewTools.showLogin(request, model, "必须登录才可以访问该页面!");
/*     */     }
/* 298 */     ViewTools.frontData(request, model, site);
/* 299 */     ViewTools.frontPageData(request, model, page);
/* 300 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 301 */       "user", "tpl.myTheme");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/replyList.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String replyList(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 307 */     return replyListpage(Integer.valueOf(1), request, response, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/replyList_{page:[0-9]+}.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String replyListpage(@PathVariable Integer page, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 314 */     Site site = ContextTools.getSite(request);
/* 315 */     User user = ContextTools.getUser(request);
/* 316 */     if (user == null) {
/* 317 */       return ViewTools.showLogin(request, model, "必须登录才可以访问该页面!");
/*     */     }
/* 319 */     ViewTools.frontData(request, model, site);
/* 320 */     ViewTools.frontPageData(request, model, page);
/* 321 */     return ViewTools.getTplPath(request, site.getSolutionPath(), 
/* 322 */       "user", "tpl.myReply");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/userbind.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public void userbind(String username, String password, Integer status, HttpServletRequest request, HttpServletResponse response)
/*     */     throws JSONException
/*     */   {
/* 329 */     JSONObject json = new JSONObject();
/* 330 */     User user = ContextTools.getUser(request);
/* 331 */     if (user == null) {
/* 332 */       return;
/*     */     }
/* 334 */     this.userBindService.save(user, username, password, status);
/* 335 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/channeltree.jsp"})
/*     */   public String addtree(Integer parentId, Integer modelId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 342 */     model.addAttribute("parentId", parentId);
/* 343 */     Site site = ContextTools.getSite(request);
/* 344 */     User user = ContextTools.getUser(request);
/* 345 */     List list = this.channelService.getChannelByModelAndMember(
/* 346 */       parentId, modelId, user, site.getId());
/* 347 */     model.addAttribute("list", list);
/* 348 */     response.setHeader("Cache-Control", "no-cache");
/* 349 */     response.setContentType("text/json;charset=UTF-8");
/* 350 */     return ViewTools.getTplPath(null, site.getSolutionPath(), 
/* 351 */       "user", "channeltree");
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.action.fnt.MemberAct
 * JD-Core Version:    0.6.1
 */