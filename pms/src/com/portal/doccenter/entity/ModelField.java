/*     */ package com.portal.doccenter.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Transient;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_model_field")
/*     */ public class ModelField
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String label;
/*     */   private Integer priority;
/*     */   private String size;
/*     */   private String maxlength;
/*     */   private String width;
/*     */   private String height;
/*     */   private String tip;
/*     */   private String valueList;
/*     */   private Integer dataType;
/*     */   private Boolean required;
/*     */   private Boolean single;
/*     */   private Boolean economy;
/*     */   private Boolean show;
/*     */   private Model model;
/*     */ 
/*     */   public void init()
/*     */   {
/*  25 */     if (getSingle() == null) {
/*  26 */       setSingle(Boolean.valueOf(true));
/*     */     }
/*  28 */     if (getEconomy() == null) {
/*  29 */       setEconomy(Boolean.valueOf(false));
/*     */     }
/*  31 */     if (getShow() == null) {
/*  32 */       setShow(Boolean.valueOf(true));
/*     */     }
/*  34 */     if (getRequired() == null)
/*  35 */       setRequired(Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getDataTypeString()
/*     */   {
/*  41 */     int dataType = getDataType().intValue();
/*  42 */     if (dataType == 1) {
/*  43 */       return "文本输入框";
/*     */     }
/*  45 */     if (dataType == 2) {
/*  46 */       return "多行文本框";
/*     */     }
/*  48 */     if (dataType == 3) {
/*  49 */       return "编辑器";
/*     */     }
/*  51 */     if (dataType == 4) {
/*  52 */       return "下拉框";
/*     */     }
/*  54 */     if (dataType == 5) {
/*  55 */       return "数字";
/*     */     }
/*  57 */     if (dataType == 6) {
/*  58 */       return "日期";
/*     */     }
/*  60 */     if (dataType == 7) {
/*  61 */       return "单选框";
/*     */     }
/*  63 */     if (dataType == 8) {
/*  64 */       return "复选框";
/*     */     }
/*  66 */     if (dataType == 9) {
/*  67 */       return "关联";
/*     */     }
/*  69 */     return "关联";
/*     */   }
/*     */ 
/*     */   public void emptyToNull()
/*     */   {
/*  74 */     if (StringUtils.isBlank(getSize()))
/*  75 */       setSize(null);
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="field_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_model_field", pkColumnValue="tq_model_field", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_model_field")
/*     */   public Integer getId()
/*     */   {
/* 106 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 110 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="name", nullable=false, length=50)
/*     */   public String getName() {
/* 115 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 119 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="label", nullable=false, length=100)
/*     */   public String getLabel() {
/* 124 */     return this.label;
/*     */   }
/*     */ 
/*     */   public void setLabel(String label) {
/* 128 */     this.label = label;
/*     */   }
/*     */ 
/*     */   @Column(name="priority", nullable=true, length=10)
/*     */   public Integer getPriority() {
/* 133 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority) {
/* 137 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   @Column(name="text_size", nullable=true, length=20)
/*     */   public String getSize() {
/* 142 */     return this.size;
/*     */   }
/*     */ 
/*     */   public void setSize(String size) {
/* 146 */     this.size = size;
/*     */   }
/*     */ 
/*     */   @Column(name="text_maxlength", nullable=true, length=10)
/*     */   public String getMaxlength() {
/* 151 */     return this.maxlength;
/*     */   }
/*     */ 
/*     */   public void setMaxlength(String maxlength) {
/* 155 */     this.maxlength = maxlength;
/*     */   }
/*     */ 
/*     */   @Column(name="width", nullable=true, length=10)
/*     */   public String getWidth() {
/* 160 */     return this.width;
/*     */   }
/*     */ 
/*     */   public void setWidth(String width) {
/* 164 */     this.width = width;
/*     */   }
/*     */ 
/*     */   @Column(name="height", nullable=true, length=3)
/*     */   public String getHeight() {
/* 169 */     return this.height;
/*     */   }
/*     */ 
/*     */   public void setHeight(String height) {
/* 173 */     this.height = height;
/*     */   }
/*     */ 
/*     */   @Column(name="tip", nullable=true, length=255)
/*     */   public String getTip() {
/* 178 */     return this.tip;
/*     */   }
/*     */ 
/*     */   public void setTip(String tip) {
/* 182 */     this.tip = tip;
/*     */   }
/*     */ 
/*     */   @Column(name="data_type", nullable=false, length=10)
/*     */   public Integer getDataType() {
/* 187 */     return this.dataType;
/*     */   }
/*     */ 
/*     */   public void setDataType(Integer dataType) {
/* 191 */     this.dataType = dataType;
/*     */   }
/*     */ 
/*     */   @Column(name="is_required", nullable=false, length=1)
/*     */   public Boolean getRequired() {
/* 196 */     return this.required;
/*     */   }
/*     */ 
/*     */   public void setRequired(Boolean required) {
/* 200 */     this.required = required;
/*     */   }
/*     */ 
/*     */   @Column(name="is_single", nullable=false, length=1)
/*     */   public Boolean getSingle() {
/* 205 */     return this.single;
/*     */   }
/*     */ 
/*     */   public void setSingle(Boolean single) {
/* 209 */     this.single = single;
/*     */   }
/*     */ 
/*     */   @Column(name="is_economy", nullable=false, length=1)
/*     */   public Boolean getEconomy() {
/* 214 */     return this.economy;
/*     */   }
/*     */ 
/*     */   public void setEconomy(Boolean economy) {
/* 218 */     this.economy = economy;
/*     */   }
/*     */ 
/*     */   @Column(name="is_show", nullable=false, length=1)
/*     */   public Boolean getShow() {
/* 223 */     return this.show;
/*     */   }
/*     */ 
/*     */   public void setShow(Boolean show) {
/* 227 */     this.show = show;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="model_id", nullable=false)
/*     */   public Model getModel() {
/* 233 */     return this.model;
/*     */   }
/*     */ 
/*     */   public void setModel(Model model) {
/* 237 */     this.model = model;
/*     */   }
/*     */ 
/*     */   @Column(name="value_list", nullable=true, length=255)
/*     */   public String getValueList() {
/* 242 */     return this.valueList;
/*     */   }
/*     */ 
/*     */   public void setValueList(String valueList) {
/* 246 */     this.valueList = valueList;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.ModelField
 * JD-Core Version:    0.6.1
 */