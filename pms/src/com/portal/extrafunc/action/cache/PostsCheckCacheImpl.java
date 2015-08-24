/*    */ package com.portal.extrafunc.action.cache;
/*    */ 
/*    */ import java.util.Date;
/*    */ import java.util.List;

/*    */ import net.sf.ehcache.Ehcache;
/*    */ import net.sf.ehcache.Element;

/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.beans.factory.annotation.Qualifier;
/*    */ 
/*    */ public class PostsCheckCacheImpl
/*    */   implements PostsCheckCache
/*    */ {
/*    */   private long interval;
/*    */   private Ehcache cache;
/*    */ 
/*    */   @Override
public void updateCheck(String username)
/*    */   {
/* 22 */     Date d = new Date();
/* 23 */     this.cache.put(new Element(username, d));
/*    */   }
/*    */ 
/*    */   @Override
public Date postsTime(String username) {
/* 27 */     Element e = this.cache.get(username);
/* 28 */     if (e != null) {
/* 29 */       Date d = (Date)e.getValue();
/* 30 */       return d;
/*    */     }
/* 32 */     return null;
/*    */   }
/*    */ 
/*    */   @Override
public void refreshCheck()
/*    */   {
		/* 38 */List<String> keys = this.cache.getKeys();
/* 39 */     if (keys.size() <= 0) {
/* 40 */       return;
/*    */     }
/* 42 */     for (String key : keys) {
/* 43 */       Element e = this.cache.get(key);
/* 44 */       if (e != null) {
/* 45 */         Date d = (Date)e.getValue();
/* 46 */         long second = System.currentTimeMillis() - d.getTime();
/* 47 */         second /= 1000L;
/* 48 */         if (second > this.interval)
/* 49 */           this.cache.remove(key);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   @Override
public long getInterval()
/*    */   {
/* 59 */     return this.interval;
/*    */   }
/*    */ 
/*    */   public void setInterval(long interval) {
/* 63 */     this.interval = interval;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setCache(@Qualifier("postsCheck") Ehcache cache) {
/* 68 */     this.cache = cache;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.cache.PostsCheckCacheImpl
 * JD-Core Version:    0.6.1
 */