/*     */ package com.portal.doccenter.entity;
/*     */ 
/*     */ /*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Set;

/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.OrderBy;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;

import com.portal.sysmgr.entity.Site;
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_work_flow")
/*     */ public class WorkFlow
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String description;
/*     */   private Integer stepCount;
/*     */   private Date createTime;
/*     */   private Site site;
/*     */   private Set<FlowStep> flowSteps;
/*     */ 
/*     */   public void init()
/*     */   {
/*  32 */     if (getCreateTime() == null)
/*  33 */       setCreateTime(new Timestamp(System.currentTimeMillis()));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public List<Integer> getSteps()
/*     */   {
/*  39 */     if (getFlowSteps() != null) {
/*  40 */       List l = new ArrayList();
/*  41 */       for (FlowStep fl : getFlowSteps()) {
/*  42 */         l.add(fl.getStep());
/*     */       }
/*  44 */       return l;
/*     */     }
/*  46 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public FlowStep getFlowStep(Integer step) {
/*  51 */     if (getFlowSteps() != null) {
/*  52 */       for (FlowStep fl : getFlowSteps()) {
/*  53 */         if (fl.getStep().equals(step)) {
/*  54 */           return fl;
/*     */         }
/*     */       }
/*     */     }
/*  58 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public FlowStep getNextFlowStep(Integer roleId) {
/*  63 */     Set steps = getFlowSteps();
/*  64 */     if (steps != null) {
/*  65 */       List stepList = new ArrayList(steps);
/*  66 */       for (int i = stepList.size() - 1; i >= 0; i--) {
/*  67 */         if ((((FlowStep)stepList.get(i)).getRole().getId().equals(roleId)) && 
/*  68 */           (i + 1 < stepList.size())) {
/*  69 */           return (FlowStep)stepList.get(i + 1);
/*     */         }
/*     */       }
/*     */     }
/*  73 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public FlowStep getLastFlowStep() {
/*  78 */     Set steps = getFlowSteps();
/*  79 */     if (steps != null) {
/*  80 */       List stepList = new ArrayList(steps);
/*  81 */       return (FlowStep)stepList.get(stepList.size() - 1);
/*     */     }
/*  83 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public FlowStep getStep(Integer roleId) {
		/* 88 */Set<FlowStep> steps = getFlowSteps();
/*  89 */     if (steps != null) {
/*  90 */       for (FlowStep step : steps) {
/*  91 */         if (step.getRole().getId().equals(roleId)) {
/*  92 */           return step;
/*     */         }
/*     */       }
/*     */     }
/*  96 */     return null;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="flow_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_work_flow", pkColumnValue="tq_work_flow", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_work_flow")
/*     */   public Integer getId()
/*     */   {
/* 119 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 123 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="flow_name", nullable=false, length=50)
/*     */   public String getName() {
/* 128 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 132 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="description", nullable=true, length=200)
/*     */   public String getDescription() {
/* 137 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/* 141 */     this.description = description;
/*     */   }
/*     */ 
/*     */   @Column(name="step_count", nullable=false, length=10)
/*     */   public Integer getStepCount() {
/* 146 */     return this.stepCount;
/*     */   }
/*     */ 
/*     */   public void setStepCount(Integer stepCount) {
/* 150 */     this.stepCount = stepCount;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="create_time", nullable=false, length=19)
/*     */   public Date getCreateTime() {
/* 156 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/* 160 */     this.createTime = createTime;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 166 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 170 */     this.site = site;
/*     */   }
/*     */   @OneToMany(fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.REMOVE}, mappedBy="flow")
/*     */   @OrderBy("step asc")
/*     */   public Set<FlowStep> getFlowSteps() {
/* 176 */     return this.flowSteps;
/*     */   }
/*     */ 
/*     */   public void setFlowSteps(Set<FlowStep> flowSteps)
/*     */   {
/* 181 */     this.flowSteps = flowSteps;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.WorkFlow
 * JD-Core Version:    0.6.1
 */