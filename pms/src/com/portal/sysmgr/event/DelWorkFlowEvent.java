/*    */ package com.portal.sysmgr.event;
/*    */ 
/*    */ import com.portal.doccenter.entity.WorkFlow;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class DelWorkFlowEvent extends ApplicationEvent
/*    */ {
/*    */   public DelWorkFlowEvent(WorkFlow flow)
/*    */   {
/* 18 */     super(flow);
/*    */   }
/*    */ 
/*    */   public WorkFlow getWorkFlow() {
/* 22 */     return (WorkFlow)super.getSource();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.event.DelWorkFlowEvent
 * JD-Core Version:    0.6.1
 */