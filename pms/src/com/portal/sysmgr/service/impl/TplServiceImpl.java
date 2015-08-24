/*     */ package com.portal.sysmgr.service.impl;
/*     */ 
/*     */ /*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;

/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.web.multipart.MultipartFile;

import com.javapms.basic.plugin.springmvc.RealPathResolver;
/*     */ import com.portal.sysmgr.service.TplService;
/*     */ import com.portal.sysmgr.utils.FileTpl;
/*     */ import com.portal.sysmgr.utils.Tpl;
/*     */ 
/*     */ @Service
/*     */ public class TplServiceImpl
/*     */   implements TplService
/*     */ {
/*  25 */   private static Logger log = LoggerFactory.getLogger(TplServiceImpl.class);
/*     */   private String root;
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   @Override
public int delete(String mroot, String[] names)
/*     */   {
/*  29 */     int count = 0;
/*  30 */     for (String name : names) {
/*  31 */       File f = new File(this.realPathResolver.get(mroot + name));
/*  32 */       if (f.isDirectory()) {
/*  33 */         if (FileUtils.deleteQuietly(f)) {
/*  34 */           count++;
/*     */         }
/*     */       }
/*  37 */       else if (f.delete()) {
/*  38 */         count++;
/*     */       }
/*     */     }
/*     */ 
/*  42 */     return count;
/*     */   }
/*     */ 
/*     */   @Override
public Tpl get(String mroot, String name) {
/*  46 */     File f = new File(this.realPathResolver.get(mroot + name));
/*  47 */     if (f.exists()) {
/*  48 */       return new FileTpl(f, this.root + mroot);
/*     */     }
/*  50 */     return null;
/*     */   }
/*     */ 
/*     */   @Override
public List<Tpl> getListByPrefix(String prefix)
/*     */   {
/*  55 */     File f = new File(this.realPathResolver.get(prefix));
/*  56 */     String name = prefix.substring(prefix.lastIndexOf("/") + 1);
/*     */     File parent;
/*  58 */     if (prefix.endsWith("/")) {
/*  59 */       name = "";
/*  60 */       parent = f;
/*     */     } else {
/*  62 */       parent = f.getParentFile();
/*     */     }
/*  64 */     if (parent.exists()) {
/*  65 */       File[] files = parent.listFiles(new PrefixFilter(name));
/*  66 */       if (files != null) {
/*  67 */         List list = new ArrayList();
/*  68 */         for (File file : files) {
/*  69 */           list.add(new FileTpl(file, this.root));
/*     */         }
/*  71 */         return list;
/*     */       }
/*  73 */       return new ArrayList(0);
/*     */     }
/*     */ 
/*  76 */     return new ArrayList(0);
/*     */   }
/*     */ 
/*     */   @Override
public List<String> getNameListByPrefix(String prefix)
/*     */   {
		/* 81 */List<Tpl> list = getListByPrefix(prefix);
/*  82 */     List result = new ArrayList(list.size());
/*  83 */     for (Tpl tpl : list) {
/*  84 */       result.add(tpl.getName());
/*     */     }
/*  86 */     return result;
/*     */   }
/*     */ 
/*     */   @Override
public List<Tpl> getChild(String mroot, String path) {
/*  90 */     File file = new File(this.realPathResolver.get(mroot + path));
/*  91 */     File[] child = file.listFiles();
/*  92 */     if (child != null) {
/*  93 */       List list = new ArrayList(child.length);
/*  94 */       for (File f : child) {
/*  95 */         list.add(new FileTpl(f, this.root + mroot));
/*     */       }
/*  97 */       return list;
/*     */     }
/*  99 */     return new ArrayList(0);
/*     */   }
/*     */ 
/*     */   @Override
public List<Tpl> getDirChild(String mroot, String path)
/*     */   {
/* 104 */     File file = new File(this.realPathResolver.get(mroot + path));
/* 105 */     File[] child = file.listFiles();
/* 106 */     if (child != null) {
/* 107 */       List list = new ArrayList(child.length);
/* 108 */       for (File f : child) {
/* 109 */         if (f.isDirectory()) {
/* 110 */           list.add(new FileTpl(f, this.root + mroot));
/*     */         }
/*     */       }
/* 113 */       return list;
/*     */     }
/* 115 */     return new ArrayList(0);
/*     */   }
/*     */ 
/*     */   @Override
public List<Tpl> getFileChild(String mroot, String path)
/*     */   {
/* 120 */     File file = new File(this.realPathResolver.get(mroot + path));
/* 121 */     File[] child = file.listFiles();
/* 122 */     if (child != null) {
/* 123 */       List list = new ArrayList(child.length);
/* 124 */       for (File f : child) {
/* 125 */         if (!f.isDirectory()) {
/* 126 */           list.add(new FileTpl(f, this.root + mroot));
/*     */         }
/*     */       }
/* 129 */       return list;
/*     */     }
/* 131 */     return new ArrayList(0);
/*     */   }
/*     */ 
/*     */   @Override
public void rename(String orig, String dist)
/*     */   {
/* 136 */     String os = this.realPathResolver.get(orig);
/* 137 */     File of = new File(os);
/* 138 */     String ds = this.realPathResolver.get(dist);
/* 139 */     File df = new File(ds);
/*     */     try {
/* 141 */       if (of.isDirectory())
/* 142 */         FileUtils.moveDirectory(of, df);
/*     */       else
/* 144 */         FileUtils.moveFile(of, df);
/*     */     }
/*     */     catch (IOException e) {
/* 147 */       log.error("Move template error: " + orig + " to " + dist, e);
/*     */     }
/*     */   }
/*     */ 
/*     */   @Override
public void save(String name, String source, boolean isDirectory) {
/* 152 */     String real = this.realPathResolver.get(name);
/* 153 */     File f = new File(real);
/* 154 */     if (isDirectory)
/* 155 */       f.mkdirs();
/*     */     else
/*     */       try {
/* 158 */         FileUtils.writeStringToFile(f, source, "UTF-8");
/*     */       } catch (IOException e) {
/* 160 */         log.error("Save template error: " + name, e);
/* 161 */         throw new RuntimeException(e);
/*     */       }
/*     */   }
/*     */ 
/*     */   @Override
public void save(String path, MultipartFile file)
/*     */   {
/* 167 */     File f = new File(this.realPathResolver.get(path), 
/* 168 */       file.getOriginalFilename());
/*     */     try {
/* 170 */       file.transferTo(f);
/*     */     } catch (IllegalStateException e) {
/* 172 */       log.error("upload template error!", e);
/*     */     } catch (IOException e) {
/* 174 */       log.error("upload template error!", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   @Override
public void update(String name, String source) {
/* 179 */     String real = this.realPathResolver.get(name);
/* 180 */     File f = new File(real);
/*     */     try {
/* 182 */       FileUtils.writeStringToFile(f, source, "UTF-8");
/*     */     } catch (IOException e) {
/* 184 */       log.error("Save template error: " + name, e);
/* 185 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setRealPathResolver(RealPathResolver realPathResolver)
/*     */   {
/* 195 */     this.realPathResolver = realPathResolver;
/* 196 */     this.root = realPathResolver.get("");
/*     */   }
/*     */ 
/*     */   private static class PrefixFilter implements FileFilter {
/*     */     private String prefix;
/*     */ 
/*     */     public PrefixFilter(String prefix) {
/* 203 */       this.prefix = prefix;
/*     */     }
/*     */ 
/*     */     @Override
public boolean accept(File file) {
/* 207 */       String name = file.getName();
/* 208 */       return (file.isFile()) && (name.startsWith(this.prefix));
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.service.impl.TplServiceImpl
 * JD-Core Version:    0.6.1
 */