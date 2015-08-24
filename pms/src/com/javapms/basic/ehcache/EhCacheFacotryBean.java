/*     */ package com.javapms.basic.ehcache;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import net.sf.ehcache.CacheException;
/*     */ import net.sf.ehcache.CacheManager;
/*     */ import net.sf.ehcache.ObjectExistsException;
/*     */ import net.sf.ehcache.config.Configuration;
/*     */ import net.sf.ehcache.config.ConfigurationFactory;
/*     */ import net.sf.ehcache.config.DiskStoreConfiguration;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.DisposableBean;
/*     */ import org.springframework.beans.factory.FactoryBean;
/*     */ import org.springframework.beans.factory.InitializingBean;
/*     */ import org.springframework.core.io.Resource;
/*     */ 
/*     */ public class EhCacheFacotryBean
/*     */   implements FactoryBean<CacheManager>, InitializingBean, DisposableBean
/*     */ {
/*  29 */   private final Logger log = LoggerFactory.getLogger(EhCacheFacotryBean.class);
/*     */   private Resource configLocation;
/*     */   private Resource diskStoreLocation;
/*     */   private String cacheManagerName;
/*     */   private CacheManager cacheManager;
/*     */ 
/*     */   public void setConfigLocation(Resource configLocation)
/*     */   {
/*  50 */     this.configLocation = configLocation;
/*     */   }
/*     */ 
/*     */   public void setdiskStoreLocation(Resource diskStoreLocation)
/*     */   {
/*  61 */     this.diskStoreLocation = diskStoreLocation;
/*     */   }
/*     */ 
/*     */   public void setCacheManagerName(String cacheManagerName)
/*     */   {
/*  70 */     this.cacheManagerName = cacheManagerName;
/*     */   }
/*     */ 
/*     */   public void afterPropertiesSet() throws IOException, CacheException {
/*  74 */     this.log.info("Initializing EHCache CacheManager");
/*  75 */     Configuration config = null;
/*  76 */     if (this.configLocation != null) {
/*  77 */       config = 
/*  78 */         ConfigurationFactory.parseConfiguration(this.configLocation.getInputStream());
/*  79 */       if (this.diskStoreLocation != null) {
/*  80 */         DiskStoreConfiguration dc = new DiskStoreConfiguration();
/*  81 */         dc.setPath(this.diskStoreLocation.getFile().getAbsolutePath());
/*     */         try {
/*  83 */           config.addDiskStore(dc);
/*     */         } catch (ObjectExistsException e) {
/*  85 */           this.log.warn("if you want to config distStore in spring, please remove diskStore in config file!", 
/*  86 */             e);
/*     */         }
/*     */       }
/*     */     }
/*  90 */     if (config != null)
/*  91 */       this.cacheManager = new CacheManager(config);
/*     */     else {
/*  93 */       this.cacheManager = new CacheManager();
/*     */     }
/*  95 */     if (this.cacheManagerName != null)
/*  96 */       this.cacheManager.setName(this.cacheManagerName);
/*     */   }
/*     */ 
/*     */   public CacheManager getObject()
/*     */   {
/* 101 */     return this.cacheManager;
/*     */   }
/*     */ 
/*     */   public Class<? extends CacheManager> getObjectType() {
/* 105 */     return this.cacheManager != null ? this.cacheManager.getClass() : 
/* 106 */       CacheManager.class;
/*     */   }
/*     */ 
/*     */   public boolean isSingleton() {
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */   public void destroy() {
/* 114 */     this.log.info("Shutting down EHCache CacheManager");
/* 115 */     this.cacheManager.shutdown();
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.ehcache.EhCacheFacotryBean
 * JD-Core Version:    0.6.1
 */