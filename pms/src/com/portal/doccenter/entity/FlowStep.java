/*    */ package com.portal.doccenter.entity;
/*    */ 
/*    */ import com.portal.usermgr.entity.Role;
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import javax.persistence.TableGenerator;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="tq_flow_step")
/*    */ public class FlowStep
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private Integer step;
/*    */   private WorkFlow flow;
/*    */   private Role role;
/*    */ 
/*    */   @Id
/*    */   @Column(name="step_id", unique=true, nullable=false)
/*    */   @TableGenerator(name="tg_flow_step", pkColumnValue="tq_flow_step", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*    */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_flow_step")
/*    */   public Integer getId()
/*    */   {
/* 36 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 40 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="step", nullable=false, length=10)
/*    */   public Integer getStep() {
/* 45 */     return this.step;
/*    */   }
/*    */ 
/*    */   public void setStep(Integer step) {
/* 49 */     this.step = step;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="flow_id", nullable=false)
/*    */   public WorkFlow getFlow() {
/* 55 */     return this.flow;
/*    */   }
/*    */ 
/*    */   public void setFlow(WorkFlow flow) {
/* 59 */     this.flow = flow;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="role_id", nullable=false)
/*    */   public Role getRole() {
/* 65 */     return this.role;
/*    */   }
/*    */ 
/*    */   public void setRole(Role role) {
/* 69 */     this.role = role;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.FlowStep
 * JD-Core Version:    0.6.1
 */