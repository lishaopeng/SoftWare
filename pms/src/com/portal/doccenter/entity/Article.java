/*     */ package com.portal.doccenter.entity;
/*     */ 
/*     */ /*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;

/*     */ import javax.persistence.CollectionTable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.ElementCollection;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.JoinTable;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.MapKeyColumn;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.OrderColumn;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.annotations.Cache;
/*     */ import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.usermgr.entity.Depart;
/*     */ import com.portal.usermgr.entity.Group;
/*     */ import com.portal.usermgr.entity.Role;
/*     */ import com.portal.usermgr.entity.User;
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_article")
/*     */ public class Article
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final byte BACK = -1;
/*     */   public static final byte CHECKING = 1;
/*     */   public static final byte CHECKED = 2;
/*     */   public static final byte RECYCLE = 3;
/*     */   public static final String DOC_URL = "doc";
/*     */   private Integer id;
/*     */   private String title;
/*     */   private String shortTitle;
/*     */   private Date releaseDate;
/*     */   private String titleColor;
/*     */   private Boolean bold;
/*     */   private Boolean top;
/*     */   private Boolean recommend;
/*     */   private Byte status;
/*     */   private String style;
/*     */   private ArticleExt articleExt;
/*     */   private ArticleTxt articleTxt;
/*     */   private DocStatis docStatis;
/*     */   private Site site;
/*     */   private Model model;
/*     */   private User user;
/*     */   private Depart inputDepart;
/*     */   private User checkUser;
/*     */   private Role role;
/*     */   private Channel channel;
/*     */   private Set<Group> viewGroups;
/*     */   private List<ArticlePicture> pics;
/*     */   private List<ArticleAttachment> atts;
/*     */   private Map<String, String> attr;
/*     */   private Set<ArticleSign> signs;
/*     */ 
/*     */   @Transient
/*     */   public String getUrl()
/*     */   {
/*  77 */     return getUrl(Integer.valueOf(1));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrl(Integer page) {
/*  82 */     if (StringUtils.isNotBlank(getLink())) {
/*  83 */       return getLink();
/*     */     }
/*  85 */     if (page == null) {
/*  86 */       page = Integer.valueOf(1);
/*     */     }
/*  88 */     return getUrlStatic(page);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrlDynamic(Integer page) {
/*  93 */     if (page == null) {
/*  94 */       page = Integer.valueOf(1);
/*     */     }
/*  96 */     StringBuilder sb = new StringBuilder(getSite().getUrl());
/*  97 */     sb.append("doc");
/*  98 */     sb.append("/");
/*  99 */     sb.append(getId());
/* 100 */     sb.append("_");
/* 101 */     sb.append(page);
/* 102 */     sb.append(".jsp");
/* 103 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrlStatic(Integer page) {
/* 108 */     StringBuilder sb = new StringBuilder(getSite().getUrl());
/* 109 */     if (getSite().getStaticSuffix().booleanValue()) {
/* 110 */       sb.append(getUrlStaticHaveSuffix(page));
/* 111 */       return sb.toString();
/*     */     }
/* 113 */     sb.append(getUrlStaticNoSuffix(page));
/* 114 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrlStaticHaveSuffix(Integer page) {
/* 119 */     StringBuilder sb = new StringBuilder();
/* 120 */     sb.append(getChannel().getPath());
/* 121 */     sb.append("/");
/* 122 */     sb.append(getId());
/* 123 */     if ((page != null) && (page.intValue() > 1)) {
/* 124 */       sb.append("_");
/* 125 */       sb.append(page);
/*     */     }
/* 127 */     sb.append(".html");
/* 128 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrlStaticNoSuffix(Integer page) {
/* 133 */     StringBuilder sb = new StringBuilder();
/* 134 */     sb.append(getChannel().getPath());
/* 135 */     sb.append("/");
/* 136 */     sb.append(getId());
/* 137 */     if ((page != null) && (page.intValue() > 1)) {
/* 138 */       sb.append("_");
/* 139 */       sb.append(page);
/*     */     }
/* 141 */     sb.append("/");
/* 142 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getStaticRealPath() {
/* 147 */     return getStaticRealPath(Integer.valueOf(1));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getStaticRealPath(Integer page) {
/* 152 */     String path = getChannel().getChannelCoreRealPath();
/* 153 */     StringBuilder sb = new StringBuilder(path);
/* 154 */     sb.append("/");
/* 155 */     sb.append("doc");
/* 156 */     sb.append(getStaticTimePath());
/* 157 */     sb.append("/");
/* 158 */     sb.append(getId());
/* 159 */     if ((page != null) && (page.intValue() > 1)) {
/* 160 */       sb.append("_");
/* 161 */       sb.append(page);
/*     */     }
/* 163 */     sb.append(".html");
/* 164 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getStaticTimePath() {
/* 169 */     Calendar cal = Calendar.getInstance();
/* 170 */     cal.setTime(getReleaseDate());
/* 171 */     int year = cal.get(1);
/* 172 */     int month = cal.get(2) + 1;
/* 173 */     String mm = "0" + month;
/* 174 */     int day = cal.get(5);
/* 175 */     String dd = "0" + day;
/* 176 */     return "/" + String.valueOf(year) + "/" + mm + 
/* 177 */       "/" + dd;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean getStaticDoc() {
/* 182 */     if (!StringUtils.isBlank(getLink())) {
/* 183 */       return false;
/*     */     }
/* 185 */     if ((getViewGroupsExt() != null) && (getViewGroupsExt().size() > 0)) {
/* 186 */       return false;
/*     */     }
/* 188 */     if (getSite().getStaticDoc().equals(Site.YES_STATIC)) {
/* 189 */       return true;
/*     */     }
/* 191 */     if (getSite().getStaticDoc().equals(Site.NO_STATIC)) {
/* 192 */       return false;
/*     */     }
/* 194 */     return getChannel().getStaticDoc();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean isChanged(long time) {
/* 199 */     Date d = getSite().getUpdateTime();
/* 200 */     if (d == null) {
/* 201 */       return false;
/*     */     }
/* 203 */     if (d.getTime() < time) {
/* 204 */       return false;
/*     */     }
/* 206 */     return true;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean isNew() {
/* 211 */     return isNew(1);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean isNew(int day) {
/* 216 */     Date d = new Date();
/* 217 */     long s = d.getTime() - getReleaseDate().getTime();
/* 218 */     s /= 1000L;
/* 219 */     if (s < 86400 * day) {
/* 220 */       return true;
/*     */     }
/* 222 */     return false;
/*     */   }
/*     */ 
/*     */   public void addToGroups(Group group) {
/* 226 */     Set groups = getViewGroups();
/* 227 */     if (groups == null) {
/* 228 */       groups = new HashSet();
/* 229 */       setViewGroups(groups);
/*     */     }
/* 231 */     groups.add(group);
/*     */   }
/*     */ 
/*     */   public void addToAttachmemts(String path, String name) {
/* 235 */     List list = getAtts();
/* 236 */     if (list == null) {
/* 237 */       list = new ArrayList();
/* 238 */       setAtts(list);
/*     */     }
/* 240 */     ArticleAttachment ca = new ArticleAttachment();
/* 241 */     ca.setPath(path);
/* 242 */     ca.setName(name);
/* 243 */     ca.setCount(Integer.valueOf(0));
/* 244 */     list.add(ca);
/*     */   }
/*     */ 
/*     */   public void addToPictures(String path, String desc, Boolean thumb, String style)
/*     */   {
/* 249 */     List list = getPics();
/* 250 */     if (list == null) {
/* 251 */       list = new ArrayList();
/* 252 */       setPics(list);
/*     */     }
/* 254 */     ArticlePicture ap = new ArticlePicture();
/* 255 */     ap.setImgPath(path);
/* 256 */     ap.setDescription(desc);
/* 257 */     ap.setThumb(thumb);
/* 258 */     ap.setStyle(style);
/* 259 */     list.add(ap);
/*     */   }
/*     */ 
/*     */   public void addToSigns(ArticleSign sign) {
/* 263 */     Set signs = getSigns();
/* 264 */     if (signs == null) {
/* 265 */       signs = new HashSet();
/* 266 */       setSigns(signs);
/*     */     }
/* 268 */     signs.add(sign);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean getSign(User user) {
/* 273 */     if (user == null) {
/* 274 */       return false;
/*     */     }
/* 276 */     if (user.getAdmin() == null) {
/* 277 */       return false;
/*     */     }
		/* 279 */Set<ArticleSign> signs = getSigns();
/* 280 */     for (ArticleSign sign : signs) {
/* 281 */       if (sign.getDepart().equals(
/* 282 */         user.getAdmin().getDepart(getSite().getId()))) {
/* 283 */         return true;
/*     */       }
/*     */     }
/* 286 */     return false;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getStatusString() {
/* 291 */     Byte status = getStatus();
/* 292 */     if (status.byteValue() == 2)
/* 293 */       return "<span style='color:blue'>已审核</span>";
/* 294 */     if (status.byteValue() == 3)
/* 295 */       return "<span style='color:red'>回收站</span>";
/* 296 */     if (status.byteValue() == 1) {
/* 297 */       return "<span style='color:red'>审核中</span>";
/*     */     }
/* 299 */     return "<span style='color:red'>退稿</span>";
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean isChecked()
/*     */   {
/* 305 */     Byte status = getStatus();
/* 306 */     if (status.byteValue() == 2) {
/* 307 */       return true;
/*     */     }
/* 309 */     return false;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Set<Group> getViewGroupsExt() {
/* 314 */     Set set = getViewGroups();
/* 315 */     if ((set != null) && (set.size() > 0)) {
/* 316 */       return set;
/*     */     }
/* 318 */     return getChannel().getViewGroups();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTplContentOrDef()
/*     */   {
/* 324 */     String tpl = getTplContent();
/* 325 */     String root = getSite().getSolutionPath();
/* 326 */     if (!StringUtils.isBlank(tpl)) {
/* 327 */       return root + tpl;
/*     */     }
/* 329 */     return root + getChannel().getTplDoc(getModel().getId());
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/* 334 */     if (getRecommend() == null) {
/* 335 */       setRecommend(Boolean.valueOf(false));
/*     */     }
/* 337 */     if (getReleaseDate() == null) {
/* 338 */       setReleaseDate(new Timestamp(System.currentTimeMillis()));
/*     */     }
/* 340 */     if (getTop() == null) {
/* 341 */       setTop(Boolean.valueOf(false));
/*     */     }
/* 343 */     if (getViewGroups() == null) {
/* 344 */       setViewGroups(new HashSet());
/*     */     }
/* 346 */     if (getPics() == null) {
/* 347 */       setPics(new ArrayList());
/*     */     }
/* 349 */     if (getAtts() == null)
/* 350 */       setAtts(new ArrayList());
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public int getPageCount()
/*     */   {
/* 356 */     int txtCount = getTxtCount();
/* 357 */     if (txtCount <= 1) {
/* 358 */       List pics = getPics();
/* 359 */       if (pics != null) {
/* 360 */         int picCount = pics.size();
/* 361 */         if (picCount > 1) {
/* 362 */           return picCount;
/*     */         }
/*     */       }
/*     */     }
/* 366 */     return txtCount;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public int getTxtCount() {
/* 371 */     ArticleTxt txt = getArticleTxt();
/* 372 */     if (txt != null) {
/* 373 */       return txt.getTxtCount();
/*     */     }
/* 375 */     return 1;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTxtByNo(int pageNo)
/*     */   {
/* 381 */     ArticleTxt txt = getArticleTxt();
/* 382 */     if (txt != null) {
/* 383 */       return txt.getTxtByNo(pageNo);
/*     */     }
/* 385 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Integer getViewsCount()
/*     */   {
/* 391 */     DocStatis ds = getDocStatis();
/* 392 */     if (ds != null) {
/* 393 */       return ds.getViewsCount();
/*     */     }
/* 395 */     return Integer.valueOf(0);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Integer getCommentCount() {
/* 400 */     DocStatis ds = getDocStatis();
/* 401 */     if (ds != null) {
/* 402 */       return ds.getCommentCount();
/*     */     }
/* 404 */     return Integer.valueOf(0);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Integer getUps() {
/* 409 */     DocStatis ds = getDocStatis();
/* 410 */     if (ds != null) {
/* 411 */       return ds.getUps();
/*     */     }
/* 413 */     return Integer.valueOf(0);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Integer getTreads() {
/* 418 */     DocStatis ds = getDocStatis();
/* 419 */     if (ds != null) {
/* 420 */       return ds.getTreads();
/*     */     }
/* 422 */     return Integer.valueOf(0);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getStitle() {
/* 427 */     if (getShortTitle() == null) {
/* 428 */       return getTitle();
/*     */     }
/* 430 */     return getShortTitle();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getSubTitle() {
/* 435 */     ArticleExt ext = getArticleExt();
/* 436 */     if (ext != null) {
/* 437 */       return ext.getSubTitle();
/*     */     }
/* 439 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getShowIndex() {
/* 444 */     ArticleExt ext = getArticleExt();
/* 445 */     if (ext != null) {
/* 446 */       return ext.getShowIndex();
/*     */     }
/* 448 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getDescription()
/*     */   {
/* 454 */     ArticleExt ext = getArticleExt();
/* 455 */     if (ext != null) {
/* 456 */       return ext.getDescription();
/*     */     }
/* 458 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getAuthor()
/*     */   {
/* 464 */     ArticleExt ext = getArticleExt();
/* 465 */     if (ext != null) {
/* 466 */       return ext.getAuthor();
/*     */     }
/* 468 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getOrigin()
/*     */   {
/* 474 */     ArticleExt ext = getArticleExt();
/* 475 */     if (ext != null) {
/* 476 */       return ext.getOrigin();
/*     */     }
/* 478 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getOriginUrl()
/*     */   {
/* 484 */     ArticleExt ext = getArticleExt();
/* 485 */     if (ext != null) {
/* 486 */       return ext.getOriginUrl();
/*     */     }
/* 488 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getCommentControl()
/*     */   {
/* 494 */     ArticleExt ext = getArticleExt();
/* 495 */     if ((ext != null) && 
/* 496 */       (ext.getCommentControl() != null)) {
/* 497 */       return ext.getCommentControl();
/*     */     }
/*     */ 
/* 500 */     return getChannel().getCommentControl();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getLink() {
/* 505 */     ArticleExt ext = getArticleExt();
/* 506 */     if (ext != null) {
/* 507 */       return ext.getLink();
/*     */     }
/* 509 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTagStr()
/*     */   {
/* 515 */     ArticleExt ext = getArticleExt();
/* 516 */     if (ext != null) {
/* 517 */       return ext.getTagStr();
/*     */     }
/* 519 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTplContent()
/*     */   {
/* 525 */     ArticleExt ext = getArticleExt();
/* 526 */     if (ext != null) {
/* 527 */       return ext.getTplContent();
/*     */     }
/* 529 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTxtValue()
/*     */   {
/* 535 */     ArticleTxt txt = getArticleTxt();
/* 536 */     if (txt != null) {
/* 537 */       return txt.getTxt();
/*     */     }
/* 539 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getPicStyle(String typeId)
/*     */   {
		/* 545 */List<ArticlePicture> list = getPics();
/* 546 */     if (list != null) {
/* 547 */       for (ArticlePicture ap : list) {
/* 548 */         if ((ap.getStyle() != null) && 
/* 549 */           (ap.getStyle().indexOf("," + typeId + ",") > -1) && 
/* 550 */           (ap.getThumb().booleanValue())) {
/* 551 */           return ap.getImgPath();
/*     */         }
/*     */       }
/*     */     }
/* 555 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getGraphic() {
		/* 560 */List<ArticlePicture> list = getPics();
/* 561 */     if (list != null) {
/* 562 */       for (ArticlePicture ap : list) {
/* 563 */         if ((ap.getStyle() != null) && (ap.getStyle().indexOf(",0,") > -1) && 
/* 564 */           (ap.getThumb().booleanValue())) {
/* 565 */           return ap.getImgPath();
/*     */         }
/*     */       }
/*     */     }
/* 569 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getCover() {
		/* 574 */List<ArticlePicture> list = getPics();
/* 575 */     if (list != null) {
/* 576 */       for (ArticlePicture ap : list) {
/* 577 */         if ((ap.getStyle() != null) && (ap.getStyle().indexOf(",1,") > -1) && 
/* 578 */           (!ap.getThumb().booleanValue())) {
/* 579 */           return ap.getImgPath();
/*     */         }
/*     */       }
/*     */     }
/* 583 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Date getDate() {
/* 588 */     return getReleaseDate();
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="article_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_article", pkColumnValue="tq_article", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_article")
/*     */   public Integer getId()
/*     */   {
/* 631 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 635 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="title", nullable=false, length=255)
/*     */   public String getTitle() {
/* 640 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 644 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="short_title", nullable=true, length=100)
/*     */   public String getShortTitle() {
/* 649 */     return this.shortTitle;
/*     */   }
/*     */ 
/*     */   public void setShortTitle(String shortTitle) {
/* 653 */     this.shortTitle = shortTitle;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="release_date", nullable=false, length=19)
/*     */   public Date getReleaseDate() {
/* 659 */     return this.releaseDate;
/*     */   }
/*     */ 
/*     */   public void setReleaseDate(Date releaseDate) {
/* 663 */     this.releaseDate = releaseDate;
/*     */   }
/*     */ 
/*     */   @Column(name="title_color", nullable=true, length=10)
/*     */   public String getTitleColor() {
/* 668 */     return this.titleColor;
/*     */   }
/*     */ 
/*     */   public void setTitleColor(String titleColor) {
/* 672 */     this.titleColor = titleColor;
/*     */   }
/*     */ 
/*     */   @Column(name="is_bold", nullable=true)
/*     */   public Boolean getBold() {
/* 677 */     return this.bold;
/*     */   }
/*     */ 
/*     */   public void setBold(Boolean bold) {
/* 681 */     this.bold = bold;
/*     */   }
/*     */ 
/*     */   @Column(name="is_top", nullable=true)
/*     */   public Boolean getTop() {
/* 686 */     return this.top;
/*     */   }
/*     */ 
/*     */   public void setTop(Boolean top) {
/* 690 */     this.top = top;
/*     */   }
/*     */ 
/*     */   @Column(name="is_recommend", nullable=true)
/*     */   public Boolean getRecommend() {
/* 695 */     return this.recommend;
/*     */   }
/*     */ 
/*     */   public void setRecommend(Boolean recommend) {
/* 699 */     this.recommend = recommend;
/*     */   }
/*     */ 
/*     */   @Column(name="status", nullable=false, length=5)
/*     */   public Byte getStatus() {
/* 704 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Byte status) {
/* 708 */     this.status = status;
/*     */   }
/*     */ 
/*     */   @Column(name="style", nullable=true, length=20)
/*     */   public String getStyle() {
/* 713 */     return this.style;
/*     */   }
/*     */ 
/*     */   public void setStyle(String style) {
/* 717 */     this.style = style;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="article")
/*     */   @PrimaryKeyJoinColumn
/*     */   public ArticleExt getArticleExt() {
/* 723 */     return this.articleExt;
/*     */   }
/*     */ 
/*     */   public void setArticleExt(ArticleExt articleExt) {
/* 727 */     this.articleExt = articleExt;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="article")
/*     */   @PrimaryKeyJoinColumn
/*     */   public ArticleTxt getArticleTxt() {
/* 733 */     return this.articleTxt;
/*     */   }
/*     */ 
/*     */   public void setArticleTxt(ArticleTxt articleTxt) {
/* 737 */     this.articleTxt = articleTxt;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="doc")
/*     */   @PrimaryKeyJoinColumn
/*     */   public DocStatis getDocStatis() {
/* 743 */     return this.docStatis;
/*     */   }
/*     */ 
/*     */   public void setDocStatis(DocStatis docStatis) {
/* 747 */     this.docStatis = docStatis;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 753 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 757 */     this.site = site;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="model_id", nullable=false)
/*     */   public Model getModel() {
/* 763 */     return this.model;
/*     */   }
/*     */ 
/*     */   public void setModel(Model model) {
/* 767 */     this.model = model;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="user_id", nullable=false)
/*     */   public User getUser() {
/* 773 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(User user) {
/* 777 */     this.user = user;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="role_id", nullable=true)
/*     */   public Role getRole() {
/* 783 */     return this.role;
/*     */   }
/*     */ 
/*     */   public void setRole(Role role) {
/* 787 */     this.role = role;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="depart_id", nullable=true)
/*     */   public Depart getInputDepart() {
/* 793 */     return this.inputDepart;
/*     */   }
/*     */ 
/*     */   public void setInputDepart(Depart inputDepart) {
/* 797 */     this.inputDepart = inputDepart;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="check_id", nullable=true)
/*     */   public User getCheckUser() {
/* 803 */     return this.checkUser;
/*     */   }
/*     */ 
/*     */   public void setCheckUser(User checkUser) {
/* 807 */     this.checkUser = checkUser;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="channel_id", nullable=false)
/*     */   public Channel getChannel() {
/* 813 */     return this.channel;
/*     */   }
/*     */ 
/*     */   public void setChannel(Channel channel) {
/* 817 */     this.channel = channel;
/*     */   }
/* 824 */   @OneToMany(fetch=FetchType.LAZY)
/*     */   @JoinTable(name="tq_article_group_view", joinColumns={@JoinColumn(name="article_id")}, inverseJoinColumns={@JoinColumn(name="group_id")})
/*     */   @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
/*     */   public Set<Group> getViewGroups() { return this.viewGroups; }
/*     */ 
/*     */ 
/*     */   public void setViewGroups(Set<Group> viewGroups)
/*     */   {
/* 829 */     this.viewGroups = viewGroups; } 
/* 837 */   @ElementCollection(fetch=FetchType.LAZY)
/*     */   @CollectionTable(name="tq_article_picture", joinColumns={@JoinColumn(name="article_id")})
/*     */   @OrderColumn(name="priority")
/*     */   @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
/*     */   public List<ArticlePicture> getPics() { return this.pics; }
/*     */ 
/*     */ 
/*     */   public void setPics(List<ArticlePicture> pics)
/*     */   {
/* 842 */     this.pics = pics; } 
/* 850 */   @ElementCollection(fetch=FetchType.LAZY)
/*     */   @CollectionTable(name="tq_article_attachment", joinColumns={@JoinColumn(name="article_id")})
/*     */   @OrderColumn(name="priority")
/*     */   @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
/*     */   public List<ArticleAttachment> getAtts() { return this.atts; }
/*     */ 
/*     */ 
/*     */   public void setAtts(List<ArticleAttachment> atts)
/*     */   {
/* 855 */     this.atts = atts; } 
/* 864 */   @ElementCollection(fetch=FetchType.LAZY)
/*     */   @CollectionTable(name="tq_article_attr", joinColumns={@JoinColumn(name="article_id")})
/*     */   @MapKeyColumn(name="attr_name", length=30)
/*     */   @Column(name="attr_value", length=255)
/*     */   @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
/*     */   public Map<String, String> getAttr() { return this.attr; }
/*     */ 
/*     */   public void setAttr(Map<String, String> attr)
/*     */   {
/* 868 */     this.attr = attr;
/*     */   }
/*     */   @OneToMany(fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.REMOVE}, mappedBy="article")
/*     */   @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
/*     */   public Set<ArticleSign> getSigns() {
/* 874 */     return this.signs;
/*     */   }
/*     */ 
/*     */   public void setSigns(Set<ArticleSign> signs)
/*     */   {
/* 879 */     this.signs = signs;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.Article
 * JD-Core Version:    0.6.1
 */