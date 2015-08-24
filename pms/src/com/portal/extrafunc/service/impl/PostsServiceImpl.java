/*     */ package com.portal.extrafunc.service.impl;
/*     */ 
/*     */ import com.javapms.basic.upload.FileRepository;
/*     */ import com.javapms.basic.utils.StringBeanUtils;
/*     */ import com.portal.extrafunc.dao.PostsDao;
/*     */ import com.portal.extrafunc.entity.Posts;
/*     */ import com.portal.extrafunc.entity.Theme;
/*     */ import com.portal.extrafunc.service.ForumOperateService;
/*     */ import com.portal.extrafunc.service.PostsExtService;
/*     */ import com.portal.extrafunc.service.PostsService;
/*     */ import com.portal.extrafunc.service.PostsTxtService;
/*     */ import com.portal.extrafunc.service.ThemeService;
/*     */ import com.portal.extrafunc.service.UserForumService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.service.SiteService;
/*     */ import com.portal.sysmgr.utils.Constants;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.UserService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class PostsServiceImpl
/*     */   implements PostsService
/*     */ {
/*     */   private PostsDao dao;
/*     */   private PostsExtService extService;
/*     */   private PostsTxtService txtService;
/*     */   private ThemeService themeService;
/*     */   private FileRepository fileRepository;
/*     */   private SiteService siteService;
/*     */   private UserService userService;
/*     */   private UserForumService userForumService;
/*     */   private ForumOperateService operateService;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<Posts> getPage(int pageNo, int pageSize)
/*     */   {
/*  36 */     return this.dao.getPage(pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<Posts> getPostsPageForTag(Integer themeId, int pageNo, int pageSize)
/*     */   {
/*  42 */     return this.dao.getPostsPageForTag(themeId, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Integer getAllPostCount(Integer siteId) {
/*  47 */     return this.dao.getAllPostCount(siteId);
/*     */   }
/*     */ 
/*     */   public Posts getLastPostsByUserAndForum(Integer forumId, Integer userId) {
/*  51 */     return this.dao.getLastPostsByUserAndForum(forumId, userId);
/*     */   }
/*     */ 
/*     */   public Posts getLastPostsByUserAndTheme(Integer themeId, Integer userId) {
/*  55 */     return this.dao.getLastPostsByUserAndTheme(themeId, userId);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Posts findById(Integer id) {
/*  60 */     Posts entity = (Posts)this.dao.findById(id);
/*  61 */     return entity;
/*     */   }
/*     */ 
/*     */   public Posts saveTheme(Integer siteId, Integer userId, Integer forumId, String title, String content, String ip, List<MultipartFile> file, List<String> code)
/*     */   {
/*  67 */     Posts posts = new Posts();
/*  68 */     Theme theme = new Theme();
/*  69 */     posts.setTitle(title);
/*  70 */     boolean affix = false;
/*  71 */     boolean hidden = false;
/*  72 */     if ((file != null) && (file.size() > 0)) {
/*  73 */       affix = true;
/*     */     }
/*  75 */     if (findHidden(content)) {
/*  76 */       hidden = true;
/*     */     }
/*  78 */     posts.setFloor(Integer.valueOf(1));
/*  79 */     posts.setAffix(Boolean.valueOf(affix));
/*  80 */     posts.setHidden(Boolean.valueOf(hidden));
/*  81 */     posts.setCreater(this.userService.findById(userId));
/*  82 */     posts.setSite(this.siteService.findById(siteId));
/*  83 */     theme = this.themeService.save(siteId, userId, forumId, title, false, affix);
/*  84 */     posts.setTheme(theme);
/*  85 */     posts.init();
/*  86 */     this.dao.save(posts);
/*  87 */     this.extService.save(ip, posts);
/*  88 */     if ((file != null) && (file.size() > 0)) {
/*  89 */       content = addAttach(content, file, code, posts);
/*     */     }
/*  91 */     this.txtService.save(content, posts);
/*  92 */     this.userForumService.update(userId, Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(3));
/*  93 */     return posts;
/*     */   }
/*     */ 
/*     */   public Posts savePosts(Integer siteId, Integer userId, Integer themeId, Integer quoteId, String title, String content, String ip, List<MultipartFile> file, List<String> code)
/*     */   {
/*  99 */     Posts posts = new Posts();
/* 100 */     User user = this.userService.findById(userId);
/* 101 */     Theme theme = this.themeService.findById(themeId);
/* 102 */     posts.setTitle(title);
/* 103 */     boolean affix = false;
/* 104 */     boolean hidden = false;
/* 105 */     if ((file != null) && (file.size() > 0)) {
/* 106 */       affix = true;
/*     */     }
/* 108 */     if (findHidden(content)) {
/* 109 */       hidden = true;
/*     */     }
/* 111 */     posts.setAffix(Boolean.valueOf(affix));
/* 112 */     posts.setHidden(Boolean.valueOf(hidden));
/* 113 */     posts.setCreater(user);
/* 114 */     if (quoteId != null) {
/* 115 */       Posts qp = findById(quoteId);
/* 116 */       posts.setQuote(qp);
/*     */     }
/* 118 */     posts.setSite(this.siteService.findById(siteId));
/* 119 */     posts.setTheme(theme);
/* 120 */     posts.setFloor(this.dao.getFloorByTheme(themeId));
/* 121 */     posts.init();
/* 122 */     posts = (Posts)this.dao.save(posts);
/* 123 */     this.extService.save(ip, posts);
/* 124 */     if ((file != null) && (file.size() > 0)) {
/* 125 */       content = addAttach(content, file, code, posts);
/*     */     }
/* 127 */     this.txtService.save(content, posts);
/* 128 */     this.themeService.updateReply(theme, user, affix);
/* 129 */     this.userForumService.update(userId, Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(1));
/* 130 */     return posts;
/*     */   }
/*     */ 
/*     */   public Posts updatePosts(Integer postsId, Integer userId, String title, String content, String ip, List<MultipartFile> file, List<String> code)
/*     */   {
/* 136 */     Posts posts = findById(postsId);
/* 137 */     posts.setTitle(title);
/* 138 */     if (posts.getFloor().intValue() == 1) {
/* 139 */       posts.getTheme().setTitle(title);
/*     */     }
/* 141 */     boolean affix = false;
/* 142 */     boolean hidden = false;
/* 143 */     if ((file != null) && (file.size() > 0)) {
/* 144 */       affix = true;
/*     */     }
/* 146 */     if (findHidden(content)) {
/* 147 */       hidden = true;
/*     */     }
/* 149 */     posts.setAffix(Boolean.valueOf(affix));
/* 150 */     posts.setHidden(Boolean.valueOf(hidden));
/* 151 */     this.extService.update(postsId, userId, ip);
/* 152 */     posts.getAttachs().clear();
/* 153 */     if ((file != null) && (file.size() > 0)) {
/* 154 */       content = addAttach(content, file, code, posts);
/*     */     }
/* 156 */     this.txtService.update(postsId, content);
/* 157 */     this.operateService.save(postsId, "POSTS", "编辑", "无", 
/* 158 */       posts.getSite(), this.userService.findById(userId));
/* 159 */     return posts;
/*     */   }
/*     */ 
/*     */   public Theme shieldTheme(Integer themeId, Integer userId, String reason) {
/* 163 */     Theme theme = this.themeService.findById(themeId);
/* 164 */     theme.setStatus(Integer.valueOf(-1));
/* 165 */     Posts posts = this.dao.getPostsOneFloor(themeId);
/* 166 */     if (posts != null) {
/* 167 */       posts.setStatus(Integer.valueOf(-1));
/*     */     }
/* 169 */     this.operateService.save(theme.getId(), "THEME", "屏蔽", reason, 
/* 170 */       theme.getSite(), this.userService.findById(userId));
/* 171 */     return theme;
/*     */   }
/*     */ 
/*     */   public Posts shieldPosts(Integer postsId, Integer userId, String reason) {
/* 175 */     Posts posts = findById(postsId);
/* 176 */     posts.setStatus(Integer.valueOf(-1));
/* 177 */     this.operateService.save(postsId, "POSTS", "屏蔽", reason, 
/* 178 */       posts.getSite(), this.userService.findById(userId));
/* 179 */     return posts;
/*     */   }
/*     */ 
/*     */   public Theme deleteTheme(Integer themeId, Integer userId, String reason) {
/* 183 */     Theme theme = this.themeService.findById(themeId);
/* 184 */     theme.setStatus(Integer.valueOf(-2));
/* 185 */     Posts posts = this.dao.getPostsOneFloor(themeId);
/* 186 */     if (posts != null) {
/* 187 */       posts.setStatus(Integer.valueOf(-2));
/*     */     }
/* 189 */     this.operateService.save(theme.getId(), "THEME", "删除", reason, 
/* 190 */       theme.getSite(), this.userService.findById(userId));
/* 191 */     return theme;
/*     */   }
/*     */ 
/*     */   public Posts update(Posts bean) {
/* 195 */     bean = (Posts)this.dao.update(bean);
/* 196 */     return bean;
/*     */   }
/*     */ 
/*     */   public int deletePosts(Integer siteId, Integer categoryId, Integer forumId, Integer themeId, Integer userId)
/*     */   {
/* 201 */     if (siteId != null) {
/* 202 */       this.dao.updatePostsBySite(siteId);
/*     */     }
/* 204 */     if (categoryId != null) {
/* 205 */       this.dao.updatePostsByCategory(categoryId);
/*     */     }
/* 207 */     if (forumId != null) {
/* 208 */       this.dao.updatePostsByForum(forumId);
/*     */     }
/* 210 */     if (themeId != null) {
/* 211 */       this.dao.updatePostsByTheme(themeId);
/*     */     }
/* 213 */     if (userId != null) {
/* 214 */       this.dao.updatePostsByUser(userId);
/*     */     }
/* 216 */     return this.dao.deletePosts(siteId, categoryId, forumId, themeId, userId);
/*     */   }
/*     */ 
/*     */   public Posts deleteById(Integer id) {
/* 220 */     Posts bean = (Posts)this.dao.deleteById(id);
/* 221 */     return bean;
/*     */   }
/*     */ 
/*     */   public Posts[] deleteByIds(Integer[] ids) {
/* 225 */     Posts[] beans = new Posts[ids.length];
/* 226 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 227 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 229 */     return beans;
/*     */   }
/*     */ 
/*     */   private String addAttach(String content, List<MultipartFile> file, List<String> code, Posts posts)
/*     */   {
/* 234 */     List list = findImgUrl(content);
/* 235 */     if (code != null) {
/* 236 */       for (int i = 0; i < code.size(); i++) {
/* 237 */         if (list.contains(code.get(i))) {
/* 238 */           String origName = ((MultipartFile)file.get(i)).getOriginalFilename();
/* 239 */           String filepath = this.fileRepository.uploadFile((MultipartFile)file.get(i), 
/* 240 */             posts.getSite().getUploadPath());
/* 241 */           addAttach(origName, filepath, posts, Long.valueOf(((MultipartFile)file.get(i)).getSize()));
/* 242 */           int j = i + 1;
/* 243 */           content = StringBeanUtils.replace(content, "[localimg]" + j + 
/* 244 */             "[/localimg]", "[attachment]" + i + 
/* 245 */             "[/attachment]");
/*     */         }
/*     */       }
/*     */     }
/* 249 */     return content;
/*     */   }
/*     */ 
/*     */   private List<String> findImgUrl(String content) {
/* 253 */     String ems = "\\[localimg]([0-9]+)\\[/localimg]";
/* 254 */     Matcher matcher = Pattern.compile(ems).matcher(content);
/* 255 */     List list = new ArrayList();
/* 256 */     while (matcher.find()) {
/* 257 */       String url = matcher.group(1);
/* 258 */       list.add(url);
/*     */     }
/* 260 */     return list;
/*     */   }
/*     */ 
/*     */   private boolean findHidden(String content) {
/* 264 */     String ems = "\\[hide]([\\s\\S]*)\\[/hide]";
/* 265 */     Matcher matcher = Pattern.compile(ems).matcher(content);
/* 266 */     if (matcher.find()) {
/* 267 */       return true;
/*     */     }
/* 269 */     return false;
/*     */   }
/*     */ 
/*     */   private void addAttach(String filename, String filepath, Posts posts, Long size)
/*     */   {
/* 274 */     boolean tag = false;
/* 275 */     String s = filename.substring(filename.lastIndexOf(".") + 1);
/* 276 */     for (String m : Constants.DEF_IMG_ACCEPT) {
/* 277 */       if (s.equals(m)) {
/* 278 */         tag = true;
/*     */       }
/*     */     }
/* 281 */     posts.setImg(Boolean.valueOf(tag));
/* 282 */     posts.getTheme().setImg(Boolean.valueOf(tag));
/* 283 */     posts.addToAttachs(filename, null, filename, filepath, Integer.valueOf(size.intValue()), 
/* 284 */       Boolean.valueOf(tag));
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(PostsDao dao)
/*     */   {
/* 299 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setExtService(PostsExtService extService) {
/* 304 */     this.extService = extService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setTxtService(PostsTxtService txtService) {
/* 309 */     this.txtService = txtService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setThemeService(ThemeService themeService) {
/* 314 */     this.themeService = themeService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setFileRepository(FileRepository fileRepository) {
/* 319 */     this.fileRepository = fileRepository;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setSiteService(SiteService siteService) {
/* 324 */     this.siteService = siteService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setUserService(UserService userService) {
/* 329 */     this.userService = userService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setUserForumService(UserForumService userForumService) {
/* 334 */     this.userForumService = userForumService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setOperateService(ForumOperateService operateService) {
/* 339 */     this.operateService = operateService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.PostsServiceImpl
 * JD-Core Version:    0.6.1
 */