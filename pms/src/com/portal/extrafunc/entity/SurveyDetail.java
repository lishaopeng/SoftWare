/*    */ package com.portal.extrafunc.entity;
/*    */ 
/*    */ import com.portal.usermgr.entity.User;
/*    */ import java.io.Serializable;
/*    */ import java.sql.Timestamp;
/*    */ import java.util.Date;
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
/*    */ import javax.persistence.Temporal;
/*    */ import javax.persistence.TemporalType;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="tq_survey_detail")
/*    */ public class SurveyDetail
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String content;
/*    */   private Date createTime;
/*    */   private SurveyTheme survey;
/*    */   private User user;
/*    */ 
/*    */   public void init()
/*    */   {
/* 25 */     if (getCreateTime() == null)
/* 26 */       setCreateTime(new Timestamp(System.currentTimeMillis()));
/*    */   }
/*    */ 
/*    */   @Id
/*    */   @Column(name="detail_id", unique=true, nullable=false)
/*    */   @TableGenerator(name="tg_survey_detail", pkColumnValue="tq_survey_detail", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*    */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_survey_detail")
/*    */   public Integer getId()
/*    */   {
/* 46 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 50 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="content", nullable=true, length=1000)
/*    */   public String getContent() {
/* 55 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 59 */     this.content = content;
/*    */   }
/*    */   @Temporal(TemporalType.TIMESTAMP)
/*    */   @Column(name="create_time", nullable=false, length=19)
/*    */   public Date getCreateTime() {
/* 65 */     return this.createTime;
/*    */   }
/*    */ 
/*    */   public void setCreateTime(Date createTime) {
/* 69 */     this.createTime = createTime;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="survey_id", nullable=false)
/*    */   public SurveyTheme getSurvey() {
/* 75 */     return this.survey;
/*    */   }
/*    */ 
/*    */   public void setSurvey(SurveyTheme survey) {
/* 79 */     this.survey = survey;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="user_id", nullable=true)
/*    */   public User getUser() {
/* 85 */     return this.user;
/*    */   }
/*    */ 
/*    */   public void setUser(User user) {
/* 89 */     this.user = user;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.SurveyDetail
 * JD-Core Version:    0.6.1
 */