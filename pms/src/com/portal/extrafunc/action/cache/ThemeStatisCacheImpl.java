/*    */ package com.portal.extrafunc.action.cache;
/*    */ 
/*    */ /*    */ import java.util.List;

/*    */ import net.sf.ehcache.Ehcache;
/*    */ import net.sf.ehcache.Element;

/*    */ import org.springframework.beans.factory.DisposableBean;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.beans.factory.annotation.Qualifier;

import com.portal.extrafunc.entity.Theme;
/*    */ import com.portal.extrafunc.service.ThemeService;
/*    */ 
/*    */ public class ThemeStatisCacheImpl
/*    */   implements ThemeStatisCache, DisposableBean
/*    */ {
/*    */   private ThemeService themeService;
/*    */   private Ehcache cache;
/*    */ 
/*    */   @Override
public Integer updateStatis(Integer themeId)
/*    */   {
/* 25 */     Theme theme = this.themeService.findById(themeId);
/* 26 */     if (theme == null) {
/* 27 */       return null;
/*    */     }
/* 29 */     Element e = this.cache.get(theme.getId());
/*    */     Integer viewCount;
/* 31 */     if (e != null)
/* 32 */       viewCount = Integer.valueOf(((Integer)e.getValue()).intValue() + 1);
/*    */     else {
/* 34 */       viewCount = Integer.valueOf(theme.getViewsCount().intValue() + 1);
/*    */     }
/* 36 */     this.cache.put(new Element(theme.getId(), viewCount));
/* 37 */     return viewCount;
/*    */   }
/*    */ 
/*    */   @Override
public Integer getStatis(Integer themeId) {
/* 41 */     Theme theme = this.themeService.findById(themeId);
/* 42 */     if (theme == null) {
/* 43 */       return null;
/*    */     }
/* 45 */     Element e = this.cache.get(theme.getId());
/*    */     Integer viewCount;
/* 47 */     if (e != null)
/* 48 */       viewCount = (Integer)e.getValue();
/*    */     else {
/* 50 */       viewCount = theme.getViewsCount();
/*    */     }
/* 52 */     return viewCount;
/*    */   }
/*    */ 
/*    */   @Override
public void statisToDB()
/*    */   {
		/* 57 */List<Integer> keys = this.cache.getKeys();
/* 58 */     if (keys.size() <= 0) {
/* 59 */       return;
/*    */     }
/* 61 */     for (Integer id : keys) {
/* 62 */       Element e = this.cache.get(id);
/* 63 */       if (e != null) {
/* 64 */         Integer viewCount = (Integer)e.getValue();
/* 65 */         this.themeService.updateViewCount(id, viewCount);
/*    */       }
/*    */     }
/* 68 */     this.cache.removeAll();
/*    */   }
/*    */ 
/*    */   @Override
public void destroy()
/*    */     throws Exception
/*    */   {
		/* 76 */List<Integer> keys = this.cache.getKeys();
/* 77 */     if (keys.size() <= 0) {
/* 78 */       return;
/*    */     }
/* 80 */     for (Integer id : keys) {
/* 81 */       Element e = this.cache.get(id);
/* 82 */       Integer viewCount = (Integer)e.getValue();
/* 83 */       this.themeService.updateViewCount(id, viewCount);
/*    */     }
/* 85 */     this.cache.removeAll();
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setCache(@Qualifier("themeViewCount") Ehcache cache)
/*    */   {
/* 94 */     this.cache = cache;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setThemeService(ThemeService themeService) {
/* 99 */     this.themeService = themeService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.cache.ThemeStatisCacheImpl
 * JD-Core Version:    0.6.1
 */