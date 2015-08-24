/*     */ package com.portal.extrafunc.entity;
/*     */ 
/*     */ /*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;

/*     */ import javax.persistence.CollectionTable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.ElementCollection;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.OrderColumn;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;

/*     */ import org.apache.commons.lang.StringUtils;

import com.javapms.basic.utils.StringBeanUtils;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.code.BbcodeHandler;
/*     */ import com.portal.usermgr.entity.User;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_posts")
/*     */ public class Posts
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int NORMAL = 0;
/*     */   public static final int SHIELD = -1;
/*     */   public static final int DELETE = -2;
/*     */   private Integer id;
/*     */   private String title;
/*     */   private Integer status;
/*     */   private Boolean affix;
/*     */   private Boolean img;
/*     */   private Boolean hidden;
/*     */   private Integer floor;
/*     */   private Date createTime;
/*     */   private PostsExt ext;
/*     */   private PostsTxt txt;
/*     */   private Theme theme;
/*     */   private Posts quote;
/*     */   private User creater;
/*     */   private Site site;
/*     */   private List<PostsAttach> attachs;
/*     */ 
/*     */   public void init()
/*     */   {
/*  48 */     if (getStatus() == null) {
/*  49 */       setStatus(Integer.valueOf(0));
/*     */     }
/*  51 */     if (getImg() == null) {
/*  52 */       setImg(Boolean.valueOf(false));
/*     */     }
/*  54 */     if (getCreateTime() == null)
/*  55 */       setCreateTime(new Timestamp(System.currentTimeMillis()));
/*     */   }
/*     */ 
/*     */   public void addToAttachs(String name, String description, String fileName, String filePath, Integer fileSize, Boolean img)
/*     */   {
/*  61 */     List list = getAttachs();
/*  62 */     if (list == null) {
/*  63 */       list = new ArrayList();
/*  64 */       setAttachs(list);
/*     */     }
/*  66 */     PostsAttach pa = new PostsAttach();
/*  67 */     pa.setName(name);
/*  68 */     pa.setDescription(description);
/*  69 */     pa.setFileName(fileName);
/*  70 */     pa.setFilePath(filePath);
/*  71 */     pa.setFileSize(fileSize);
/*  72 */     pa.setImg(img);
/*  73 */     list.add(pa);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getContent() {
/*  78 */     PostsTxt txt = getTxt();
/*  79 */     if (txt != null) {
/*  80 */       return txt.getContent();
/*     */     }
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getContentAll()
/*     */   {
/*  92 */     String s = getContent();
/*  93 */     String qc = "";
/*  94 */     if (StringUtils.isBlank(s)) {
/*  95 */       return "";
/*     */     }
/*  97 */     if (getStatus().intValue() < 0) {
/*  98 */       s = "[quote='shield'][h=6]提示:该贴已被删除或者被屏蔽,您是管理员，可见其内容[/h]" + s + 
/*  99 */         "[/quote]";
/*     */     }
/* 101 */     if (getQuote() != null) {
/* 102 */       qc = getQuote().getContentNoQuote(true);
/*     */     }
/* 104 */     if (getHidden().booleanValue()) {
			/* 105 */List<String> list = getHideContent(s);
/* 106 */       for (String str : list) {
/* 107 */         s = StringBeanUtils.replace(s, "[hide]" + str + "[/hide]", 
/* 108 */           "[quote='content_hidden'][h=6]此为隐藏内容:[/h]" + str + 
/* 109 */           "[/quote]");
/*     */       }
/*     */     }
/* 112 */     if (getAffix().booleanValue()) {
/* 113 */       List att = getAttachs();
/* 114 */       for (int i = 0; i < att.size(); i++) {
/* 115 */         PostsAttach pa = (PostsAttach)att.get(i);
/* 116 */         if (pa != null) {
/* 117 */           String oldcontent = "[attachment]" + i + 
/* 118 */             "[/attachment]";
/* 119 */           if (pa.getImg().booleanValue()) {
/* 120 */             String newcontent = "[img]" + getSite().getUrl() + 
/* 121 */               pa.getFilePath().substring(1) + "[/img]";
/* 122 */             s = StringBeanUtils.replace(s, oldcontent, 
/* 123 */               newcontent);
/*     */           } else {
/* 125 */             String newcontent = "[url=" + getSite().getUrl() + 
/* 126 */               pa.getFilePath().substring(1) + "]" + 
/* 127 */               pa.getFileName() + "[/url]";
/* 128 */             s = StringBeanUtils.replace(s, oldcontent, 
/* 129 */               newcontent);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 134 */     return qc + BbcodeHandler.toHtml(s);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getContentAndQuote(boolean showhidden)
/*     */   {
/* 145 */     String s = getContent();
/* 146 */     String qc = "";
/* 147 */     if (StringUtils.isBlank(s)) {
/* 148 */       return "";
/*     */     }
/* 150 */     if (getStatus().intValue() < 0) {
/* 151 */       String content = "[quote='shield'][color=red]该贴已被删除或者被屏蔽[/color][/quote]";
/* 152 */       return BbcodeHandler.toHtml(content);
/*     */     }
/* 154 */     if (getQuote() != null) {
/* 155 */       qc = getQuote().getContentNoQuote(showhidden);
/*     */     }
/* 157 */     if (getHidden().booleanValue()) {
			/* 158 */List<String> list = getHideContent(s);
/* 159 */       if (showhidden) {
/* 160 */         for (String str : list)
/* 161 */           s = StringBeanUtils.replace(s, "[hide]" + str + 
/* 162 */             "[/hide]", 
/* 163 */             "[quote='content_hidden'][h=6]此为隐藏内容:[/h]" + 
/* 164 */             str + "[/quote]");
/*     */       }
/*     */       else {
/* 167 */         for (String str : list) {
/* 168 */           s = 
/* 169 */             StringBeanUtils.replace(
/* 170 */             s, 
/* 171 */             "[hide]" + str + "[/hide]", 
/* 172 */             "[quote='content_hidden'][h=6]提示:[/h][color=red]这是隐藏内容.需要回复才能浏览[/color][/quote]");
/*     */         }
/*     */       }
/*     */     }
/* 176 */     if (getAffix().booleanValue()) {
/* 177 */       List att = getAttachs();
/* 178 */       for (int i = 0; i < att.size(); i++) {
/* 179 */         PostsAttach pa = (PostsAttach)att.get(i);
/* 180 */         if (pa != null) {
/* 181 */           String oldcontent = "[attachment]" + i + 
/* 182 */             "[/attachment]";
/* 183 */           if (pa.getImg().booleanValue()) {
/* 184 */             String newcontent = "[img]" + getSite().getUrl() + 
/* 185 */               pa.getFilePath().substring(1) + "[/img]";
/* 186 */             s = StringBeanUtils.replace(s, oldcontent, 
/* 187 */               newcontent);
/*     */           } else {
/* 189 */             String newcontent = "[url=" + getSite().getUrl() + 
/* 190 */               pa.getFilePath().substring(1) + "]" + 
/* 191 */               pa.getFileName() + "[/url]";
/* 192 */             s = StringBeanUtils.replace(s, oldcontent, 
/* 193 */               newcontent);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 198 */     return qc + BbcodeHandler.toHtml(s);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   private String getContentNoQuote(boolean showhidden)
/*     */   {
/* 209 */     String s = getContent();
/* 210 */     if (StringUtils.isBlank(s)) {
/* 211 */       return "";
/*     */     }
/* 213 */     if (getStatus().intValue() < 0) {
/* 214 */       String content = "[quote='shield'][color=red]该贴已被删除或者被屏蔽[/color][/quote]";
/* 215 */       return BbcodeHandler.toHtml(content);
/*     */     }
/* 217 */     if (getHidden().booleanValue()) {
			/* 218 */List<String> list = getHideContent(s);
/* 219 */       if (showhidden) {
/* 220 */         for (String str : list)
/* 221 */           s = StringBeanUtils.replace(s, "[hide]" + str + 
/* 222 */             "[/hide]", 
/* 223 */             "[quote='content_hidden'][h=6]此为隐藏内容:[/h]" + 
/* 224 */             str + "[/quote]");
/*     */       }
/*     */       else {
/* 227 */         for (String str : list) {
/* 228 */           s = 
/* 229 */             StringBeanUtils.replace(
/* 230 */             s, 
/* 231 */             "[hide]" + str + "[/hide]", 
/* 232 */             "[quote='content_hidden'][h=6]提示:[/h][color=red]引用了隐藏内容，需要回复才能浏览！[/color][/quote]");
/*     */         }
/*     */       }
/*     */     }
/* 236 */     if (getAffix().booleanValue()) {
/* 237 */       List att = getAttachs();
/* 238 */       for (int i = 0; i < att.size(); i++) {
/* 239 */         PostsAttach pa = (PostsAttach)att.get(i);
/* 240 */         if (pa != null) {
/* 241 */           String oldcontent = "[attachment]" + i + 
/* 242 */             "[/attachment]";
/* 243 */           if (pa.getImg().booleanValue()) {
/* 244 */             String newcontent = "[img]" + getSite().getUrl() + 
/* 245 */               pa.getFilePath().substring(1) + "[/img]";
/* 246 */             s = StringBeanUtils.replace(s, oldcontent, 
/* 247 */               newcontent);
/*     */           } else {
/* 249 */             String newcontent = "[url=" + getSite().getUrl() + 
/* 250 */               pa.getFilePath().substring(1) + "]" + 
/* 251 */               pa.getFileName() + "[/url]";
/* 252 */             s = StringBeanUtils.replace(s, oldcontent, 
/* 253 */               newcontent);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 258 */     s = "[quote][h=6]引用:[/h]" + s + "[/quote]";
/* 259 */     return BbcodeHandler.toHtml(s);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getContentNotHtml(boolean showhidden)
/*     */   {
/* 270 */     String s = getContent();
/* 271 */     if (StringUtils.isBlank(s)) {
/* 272 */       return "";
/*     */     }
/* 274 */     if (getStatus().intValue() < 0) {
/* 275 */       String content = "[color=red]该贴已被删除或者被屏蔽[/color]";
/* 276 */       return content;
/*     */     }
/* 278 */     if (getHidden().booleanValue()) {
			/* 279 */List<String> list = getHideContent(s);
/* 280 */       if (showhidden) {
/* 281 */         for (String str : list)
/* 282 */           s = StringBeanUtils.replace(s, "[hide]" + str + 
/* 283 */             "[/hide]", str);
/*     */       }
/*     */       else {
/* 286 */         for (String str : list) {
/* 287 */           String newcontent = "[color=red]这是隐藏内容.需要回复才能浏览[/color]";
/* 288 */           s = StringBeanUtils.replace(s, "[hide]" + str + 
/* 289 */             "[/hide]", newcontent);
/*     */         }
/*     */       }
/*     */     }
/* 293 */     return s;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   private List<String> getHideContent(String content)
/*     */   {
/* 305 */     String ems = "\\[hide\\]([\\s\\S]*)\\[/hide\\]";
/* 306 */     Matcher matcher = Pattern.compile(ems).matcher(content);
/* 307 */     List list = new ArrayList();
/* 308 */     while (matcher.find()) {
/* 309 */       String url = matcher.group(1);
/* 310 */       list.add(url);
/*     */     }
/* 312 */     return list;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="posts_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_posts", pkColumnValue="tq_posts", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_posts")
/*     */   public Integer getId()
/*     */   {
/* 345 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 349 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="title", nullable=true, length=100)
/*     */   public String getTitle() {
/* 354 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 358 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="status", nullable=false, length=10)
/*     */   public Integer getStatus() {
/* 363 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Integer status) {
/* 367 */     this.status = status;
/*     */   }
/*     */ 
/*     */   @Column(name="is_affix", nullable=false, length=1)
/*     */   public Boolean getAffix() {
/* 372 */     return this.affix;
/*     */   }
/*     */ 
/*     */   public void setAffix(Boolean affix) {
/* 376 */     this.affix = affix;
/*     */   }
/*     */ 
/*     */   @Column(name="is_img", nullable=false, length=1)
/*     */   public Boolean getImg() {
/* 381 */     return this.img;
/*     */   }
/*     */ 
/*     */   public void setImg(Boolean img) {
/* 385 */     this.img = img;
/*     */   }
/*     */ 
/*     */   @Column(name="is_hidden", nullable=false, length=1)
/*     */   public Boolean getHidden() {
/* 390 */     return this.hidden;
/*     */   }
/*     */ 
/*     */   public void setHidden(Boolean hidden) {
/* 394 */     this.hidden = hidden;
/*     */   }
/*     */ 
/*     */   @Column(name="floor", nullable=false, length=10)
/*     */   public Integer getFloor() {
/* 399 */     return this.floor;
/*     */   }
/*     */ 
/*     */   public void setFloor(Integer floor) {
/* 403 */     this.floor = floor;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="create_time", nullable=false, length=19)
/*     */   public Date getCreateTime() {
/* 409 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/* 413 */     this.createTime = createTime;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public PostsExt getExt() {
/* 419 */     return this.ext;
/*     */   }
/*     */ 
/*     */   public void setExt(PostsExt ext) {
/* 423 */     this.ext = ext;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public PostsTxt getTxt() {
/* 429 */     return this.txt;
/*     */   }
/*     */ 
/*     */   public void setTxt(PostsTxt txt) {
/* 433 */     this.txt = txt;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="theme_id", nullable=false)
/*     */   public Theme getTheme() {
/* 439 */     return this.theme;
/*     */   }
/*     */ 
/*     */   public void setTheme(Theme theme) {
/* 443 */     this.theme = theme;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="quote_id", nullable=true)
/*     */   public Posts getQuote() {
/* 449 */     return this.quote;
/*     */   }
/*     */ 
/*     */   public void setQuote(Posts quote) {
/* 453 */     this.quote = quote;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="creater_id", nullable=true)
/*     */   public User getCreater() {
/* 459 */     return this.creater;
/*     */   }
/*     */ 
/*     */   public void setCreater(User creater) {
/* 463 */     this.creater = creater;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 469 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 473 */     this.site = site;
/*     */   }
/* 480 */   @ElementCollection(fetch=FetchType.LAZY)
/*     */   @CollectionTable(name="tq_posts_attach", joinColumns={@JoinColumn(name="posts_id")})
/*     */   @OrderColumn(name="priority")
/*     */   public List<PostsAttach> getAttachs() { return this.attachs; }
/*     */ 
/*     */ 
/*     */   public void setAttachs(List<PostsAttach> attachs)
/*     */   {
/* 485 */     this.attachs = attachs;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.Posts
 * JD-Core Version:    0.6.1
 */