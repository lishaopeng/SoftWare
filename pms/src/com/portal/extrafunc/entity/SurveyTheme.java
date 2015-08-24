/*     */ package com.portal.extrafunc.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
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
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OrderBy;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Transient;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_survey_theme")
/*     */ public class SurveyTheme
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  28 */   public static final Integer NORMAL = Integer.valueOf(1);
/*     */ 
/*  30 */   public static final Integer WRITED = Integer.valueOf(2);
/*     */ 
/*  33 */   public static final Integer CHECKED = Integer.valueOf(1);
/*     */ 
/*  35 */   public static final Integer SELECTED = Integer.valueOf(2);
/*     */ 
/*  37 */   public static final Integer TEXT = Integer.valueOf(3);
/*     */ 
/*  39 */   public static final Integer ONLINE = Integer.valueOf(4);
/*     */ 
/*  41 */   public static final Integer AREA = Integer.valueOf(5);
/*     */   private Integer id;
/*     */   private String title;
/*     */   private Integer surveyType;
/*     */   private Integer totalCount;
/*     */   private Integer maxlength;
/*     */   private Integer showType;
/*     */   private Integer priority;
/*     */   private Questionnaire naire;
/*     */   private Set<SurveyItem> items;
/*     */ 
/*     */   public void init()
/*     */   {
/*  44 */     if (getSurveyType().equals(WRITED)) {
/*  45 */       setTotalCount(null);
/*  46 */       if (getMaxlength() == null) {
/*  47 */         setMaxlength(Integer.valueOf(50));
/*     */       }
/*     */     }
/*  50 */     if (getSurveyType().equals(NORMAL)) {
/*  51 */       setMaxlength(null);
/*  52 */       if (getTotalCount() == null) {
/*  53 */         setTotalCount(Integer.valueOf(1));
/*     */       }
/*     */     }
/*  56 */     if (getPriority() == null)
/*  57 */       setPriority(Integer.valueOf(10));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getShowString()
/*     */   {
/*  63 */     Integer showtype = getShowType();
/*  64 */     if (showtype.equals(CHECKED))
/*  65 */       return "单复选框显示";
/*  66 */     if (showtype.equals(SELECTED))
/*  67 */       return "下拉框显示";
/*  68 */     if (showtype.equals(TEXT))
/*  69 */       return "普通输入框显示";
/*  70 */     if (showtype.equals(ONLINE)) {
/*  71 */       return "下划线显示";
/*     */     }
/*  73 */     return "文本区域显示";
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getSurveyTypeString()
/*     */   {
/*  79 */     Integer surveytype = getSurveyType();
/*  80 */     if (surveytype.equals(NORMAL)) {
/*  81 */       return "选项类";
/*     */     }
/*  83 */     return "填写类";
/*     */   }
/*     */ 
/*     */   public void addToItems(String name, Integer votes, Integer priority)
/*     */   {
/*  88 */     Set items = getItems();
/*  89 */     if (items == null) {
/*  90 */       items = new HashSet();
/*  91 */       setItems(items);
/*     */     }
/*  93 */     SurveyItem item = new SurveyItem();
/*  94 */     item.setName(name);
/*  95 */     item.setVotes(votes);
/*  96 */     item.setPriority(priority);
/*  97 */     items.add(item);
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="theme_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_survey_theme", pkColumnValue="tq_survey_theme", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_survey_theme")
/*     */   public Integer getId()
/*     */   {
/* 122 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 126 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="title", nullable=false, length=50)
/*     */   public String getTitle() {
/* 131 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 135 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="survey_type", nullable=false, length=10)
/*     */   public Integer getSurveyType() {
/* 140 */     return this.surveyType;
/*     */   }
/*     */ 
/*     */   public void setSurveyType(Integer surveyType) {
/* 144 */     this.surveyType = surveyType;
/*     */   }
/*     */ 
/*     */   @Column(name="total_count", nullable=true, length=10)
/*     */   public Integer getTotalCount() {
/* 149 */     return this.totalCount;
/*     */   }
/*     */ 
/*     */   public void setTotalCount(Integer totalCount) {
/* 153 */     this.totalCount = totalCount;
/*     */   }
/*     */ 
/*     */   @Column(name="maxlength", nullable=true, length=10)
/*     */   public Integer getMaxlength() {
/* 158 */     return this.maxlength;
/*     */   }
/*     */ 
/*     */   public void setMaxlength(Integer maxlength) {
/* 162 */     this.maxlength = maxlength;
/*     */   }
/*     */ 
/*     */   @Column(name="show_type", nullable=true, length=10)
/*     */   public Integer getShowType() {
/* 167 */     return this.showType;
/*     */   }
/*     */ 
/*     */   public void setShowType(Integer showType) {
/* 171 */     this.showType = showType;
/*     */   }
/*     */ 
/*     */   @Column(name="priority", nullable=false, length=10)
/*     */   public Integer getPriority() {
/* 176 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority) {
/* 180 */     this.priority = priority;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="naire_id", nullable=false)
/*     */   public Questionnaire getNaire() {
/* 186 */     return this.naire;
/*     */   }
/*     */ 
/*     */   public void setNaire(Questionnaire naire) {
/* 190 */     this.naire = naire;
/*     */   }
/* 197 */   @ElementCollection(fetch=FetchType.LAZY)
/*     */   @CollectionTable(name="tq_survey_item", joinColumns={@JoinColumn(name="theme_id")})
/*     */   @OrderBy("priority asc")
/*     */   public Set<SurveyItem> getItems() { return this.items; }
/*     */ 
/*     */ 
/*     */   public void setItems(Set<SurveyItem> items)
/*     */   {
/* 202 */     this.items = items;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.SurveyTheme
 * JD-Core Version:    0.6.1
 */