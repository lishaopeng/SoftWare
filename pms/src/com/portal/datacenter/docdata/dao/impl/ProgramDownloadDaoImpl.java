/*    */ package com.portal.datacenter.docdata.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.datacenter.docdata.dao.ProgramDownloadDao;
/*    */ import com.portal.datacenter.docdata.entity.ProgramDownload;
/*    */ import org.hibernate.Criteria;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ProgramDownloadDaoImpl extends QueryDaoImpl<ProgramDownload, Integer>
/*    */   implements ProgramDownloadDao
/*    */ {
/*    */   public Page<ProgramDownload> getPage(int pageNo, int pageSize)
/*    */   {
/* 16 */     Criteria crit = createCriteria();
/* 17 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public ProgramDownload findUnique() {
/* 21 */     Criteria crit = createCriteria();
/* 22 */     return (ProgramDownload)findUnique(crit);
/*    */   }
/*    */ 
/*    */   protected Class<ProgramDownload> getEntityClass()
/*    */   {
/* 27 */     return ProgramDownload.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.dao.impl.ProgramDownloadDaoImpl
 * JD-Core Version:    0.6.1
 */