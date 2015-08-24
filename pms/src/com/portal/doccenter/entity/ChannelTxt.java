/*    */ package com.portal.doccenter.entity;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Lob;
/*    */ import javax.persistence.OneToOne;
/*    */ import javax.persistence.PrimaryKeyJoinColumn;
/*    */ import javax.persistence.Table;
/*    */ import javax.persistence.Transient;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.annotations.GenericGenerator;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="tq_channel_txt")
/*    */ public class ChannelTxt
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String txtval;
/*    */   private Channel channel;
/*    */ 
/*    */   public void init()
/*    */   {
/* 25 */     blankToNull();
/*    */   }
/*    */ 
/*    */   public void blankToNull() {
/* 29 */     if (StringUtils.isBlank(getTxtval()))
/* 30 */       setTxtval(null);
/*    */   }
/*    */ 
/*    */   @Transient
/*    */   public boolean isAllBlank()
/*    */   {
/* 36 */     return StringUtils.isBlank(getTxtval());
/*    */   }
/*    */ 
/*    */   @Id
/*    */   @Column(name="channel_id", unique=true, nullable=false)
/*    */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="channel")})
/*    */   @GeneratedValue(generator="copy")
/*    */   public Integer getId()
/*    */   {
/* 53 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 57 */     this.id = id;
/*    */   }
/*    */   @Lob
/*    */   @Column(name="txt", nullable=true)
/*    */   public String getTxtval() {
/* 63 */     return this.txtval;
/*    */   }
/*    */ 
/*    */   public void setTxtval(String txtval) {
/* 67 */     this.txtval = txtval;
/*    */   }
/*    */   @OneToOne
/*    */   @PrimaryKeyJoinColumn
/*    */   public Channel getChannel() {
/* 73 */     return this.channel;
/*    */   }
/*    */ 
/*    */   public void setChannel(Channel channel) {
/* 77 */     this.channel = channel;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.ChannelTxt
 * JD-Core Version:    0.6.1
 */