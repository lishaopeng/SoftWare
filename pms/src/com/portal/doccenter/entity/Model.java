/*     */ package com.portal.doccenter.entity;
/*     */ 
/*     */ import com.javapms.basic.comparator.BeanInterface;
/*     */ import java.io.Serializable;
/*     */ import java.util.Set;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Transient;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_model")
/*     */ public class Model
/*     */   implements Serializable, BeanInterface
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  26 */   public static final String[] DEF_NAMES = { "channelId", "title", 
/*  27 */     "shortTitle", "titleColor", "subTitle", "tagStr", "description", 
/*  28 */     "author", "origin", "style", "recommend", "showIndex", "redTape", 
/*  29 */     "viewGroups", "tplContent", "atts", "releaseDate", "link", 
/*  30 */     "commentControl", "updownControl", "txt", "picture", "pics" };
/*     */ 
/*  32 */   public static final String[] DEF_LABELS = { "栏目", "标题", "短标题", "标题颜色", 
/*  33 */     "副标题", "Tag标签", "摘要", "作者", "来源", "新闻类型", "属性", "显示到首页", "是否需签收", 
/*  34 */     "访问权限", "内容模板", "附件", "发布时间", "外部链接", "评论控制", "顶踩控制", "内容", "缩略图", 
/*  35 */     "组图" };
/*     */ 
/*  37 */   public static final Integer[] DEF_DATA_TYPES = { Integer.valueOf(9), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(1), 
/*  38 */     Integer.valueOf(8), Integer.valueOf(8), Integer.valueOf(7), Integer.valueOf(1), Integer.valueOf(8), Integer.valueOf(1), Integer.valueOf(10), Integer.valueOf(6), Integer.valueOf(1), Integer.valueOf(7), Integer.valueOf(7), Integer.valueOf(3), Integer.valueOf(10), Integer.valueOf(10) };
/*     */ 
/*  40 */   public static final Boolean[] DEF_REQUIREDS = { Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), 
/*  41 */     Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), 
/*  42 */     Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false) };
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String icon;
/*     */   private String tplDoc;
/*     */   private String tplPrint;
/*     */   private String tplSearch;
/*     */   private String tplAdvSearch;
/*     */   private String tplComment;
/*     */   private Integer priority;
/*     */   private Boolean disabled;
/*     */   private Set<ModelField> fields;
/*     */ 
/*     */   public void init()
/*     */   {
/*  45 */     if (getDisabled() == null)
/*  46 */       setDisabled(Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getModelIcon()
/*     */   {
/*  52 */     if (!StringUtils.isBlank(getIcon())) {
/*  53 */       int a = getIcon().lastIndexOf("/") + 1;
/*  54 */       int b = getIcon().lastIndexOf(".");
/*  55 */       if (b > a) {
/*  56 */         return getIcon().substring(a, b);
/*     */       }
/*     */     }
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="model_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_model", pkColumnValue="tq_model", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_model")
/*     */   public Integer getId()
/*     */   {
/*  84 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  88 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="model_name", nullable=false, length=100)
/*     */   public String getName() {
/*  93 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  97 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="model_icon", nullable=true, length=30)
/*     */   public String getIcon() {
/* 102 */     return this.icon;
/*     */   }
/*     */ 
/*     */   public void setIcon(String icon) {
/* 106 */     this.icon = icon;
/*     */   }
/*     */ 
/*     */   @Column(name="model_tpl_doc", nullable=false, length=100)
/*     */   public String getTplDoc() {
/* 111 */     return this.tplDoc;
/*     */   }
/*     */ 
/*     */   public void setTplDoc(String tplDoc) {
/* 115 */     this.tplDoc = tplDoc;
/*     */   }
/*     */ 
/*     */   @Column(name="model_tpl_print", nullable=true, length=100)
/*     */   public String getTplPrint() {
/* 120 */     return this.tplPrint;
/*     */   }
/*     */ 
/*     */   public void setTplPrint(String tplPrint) {
/* 124 */     this.tplPrint = tplPrint;
/*     */   }
/*     */ 
/*     */   @Column(name="model_tpl_search", nullable=true, length=100)
/*     */   public String getTplSearch() {
/* 129 */     return this.tplSearch;
/*     */   }
/*     */ 
/*     */   public void setTplSearch(String tplSearch) {
/* 133 */     this.tplSearch = tplSearch;
/*     */   }
/*     */ 
/*     */   @Column(name="model_tpl_advsearch", nullable=true, length=100)
/*     */   public String getTplAdvSearch() {
/* 138 */     return this.tplAdvSearch;
/*     */   }
/*     */ 
/*     */   public void setTplAdvSearch(String tplAdvSearch) {
/* 142 */     this.tplAdvSearch = tplAdvSearch;
/*     */   }
/*     */ 
/*     */   @Column(name="model_tpl_comment", nullable=true, length=100)
/*     */   public String getTplComment() {
/* 147 */     return this.tplComment;
/*     */   }
/*     */ 
/*     */   public void setTplComment(String tplComment) {
/* 151 */     this.tplComment = tplComment;
/*     */   }
/*     */ 
/*     */   @Column(name="priority", nullable=false, length=10)
/*     */   public Integer getPriority() {
/* 156 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority) {
/* 160 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   @Column(name="is_disabled", nullable=false, length=1)
/*     */   public Boolean getDisabled() {
/* 165 */     return this.disabled;
/*     */   }
/*     */ 
/*     */   public void setDisabled(Boolean disabled) {
/* 169 */     this.disabled = disabled;
/*     */   }
/*     */ 
/*     */   @OneToMany(fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.REMOVE}, mappedBy="model")
/*     */   public Set<ModelField> getFields() {
/* 174 */     return this.fields;
/*     */   }
/*     */ 
/*     */   public void setFields(Set<ModelField> fields)
/*     */   {
/* 179 */     this.fields = fields;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.Model
 * JD-Core Version:    0.6.1
 */