/*    */ package com.portal.extrafunc.action.cache;
/*    */ 
/*    */ /*    */ import java.util.List;

/*    */ import net.sf.ehcache.Ehcache;
/*    */ import net.sf.ehcache.Element;

/*    */ import org.springframework.beans.factory.DisposableBean;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.beans.factory.annotation.Qualifier;

import com.portal.extrafunc.entity.Comment;
/*    */ import com.portal.extrafunc.service.CommentService;
/*    */ 
/*    */ public class CommentUpCacheImpl
/*    */   implements CommentUpCache, DisposableBean
/*    */ {
/*    */   private CommentService commentService;
/*    */   private Ehcache cache;
/*    */ 
/*    */   @Override
public Integer upAndGet(Integer id)
/*    */   {
/* 25 */     Comment c = this.commentService.findById(id);
/* 26 */     if (c == null) {
/* 27 */       return null;
/*    */     }
/* 29 */     Element e = this.cache.get(id);
/*    */     Integer ups;
/* 31 */     if (e != null)
/* 32 */       ups = Integer.valueOf(((Integer)e.getValue()).intValue() + 1);
/*    */     else {
/* 34 */       ups = c.getUps();
/*    */     }
/* 36 */     this.cache.put(new Element(id, ups));
/* 37 */     return ups;
/*    */   }
/*    */ 
/*    */   @Override
public void upsToDB()
/*    */   {
		/* 42 */List<Integer> keys = this.cache.getKeys();
/* 43 */     if (keys.size() <= 0) {
/* 44 */       return;
/*    */     }
/* 46 */     for (Integer id : keys) {
/* 47 */       Element e = this.cache.get(id);
/* 48 */       if (e != null) {
/* 49 */         Integer ups = (Integer)e.getValue();
/* 50 */         this.commentService.ups(id, ups);
/*    */       }
/*    */     }
/* 53 */     this.cache.removeAll();
/*    */   }
/*    */ 
/*    */   @Override
public void destroy()
/*    */     throws Exception
/*    */   {
		/* 61 */List<Integer> keys = this.cache.getKeys();
/* 62 */     if (keys.size() <= 0) {
/* 63 */       return;
/*    */     }
/* 65 */     for (Integer id : keys) {
/* 66 */       Element e = this.cache.get(id);
/* 67 */       if (e != null) {
/* 68 */         Integer ups = (Integer)e.getValue();
/* 69 */         this.commentService.ups(id, ups);
/*    */       }
/*    */     }
/* 72 */     this.cache.removeAll();
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setCommentService(CommentService commentService)
/*    */   {
/* 81 */     this.commentService = commentService;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setCache(@Qualifier("commentUp") Ehcache cache) {
/* 86 */     this.cache = cache;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.cache.CommentUpCacheImpl
 * JD-Core Version:    0.6.1
 */