/*     */ package com.portal.sysmgr.service.impl;
/*     */ 
/*     */ /*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;

/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.web.multipart.MultipartFile;

import com.javapms.basic.file.FileWrap;
/*     */ import com.javapms.basic.plugin.springmvc.RealPathResolver;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.service.ResourceService;
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class ResourceServiceImpl
/*     */   implements ResourceService
/*     */ {
/* 129 */   private FileFilter filter = new FileFilter()
/*     */   {
/*     */     @Override
public boolean accept(File file) {
/* 132 */       return (file.isDirectory()) || 
/* 132 */         (FileWrap.editableExt(FilenameUtils.getExtension(file
/* 133 */         .getName())));
/*     */     }
/* 129 */   };
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   @Override
public List<FileWrap> listFile(String root, String path, boolean dirAndEditable)
/*     */   {
/*  29 */     File parent = new File(this.realPathResolver.get(root + path));
/*  30 */     if (parent.exists())
/*     */     {
/*     */       File[] files;
/*  32 */       if (dirAndEditable)
/*  33 */         files = parent.listFiles(this.filter);
/*     */       else {
/*  35 */         files = parent.listFiles();
/*     */       }
/*  37 */       Arrays.sort(files, new FileWrap.FileComparator());
/*  38 */       List list = new ArrayList(files.length);
/*  39 */       for (File f : files) {
/*  40 */         list.add(new FileWrap(f, this.realPathResolver.get("") + root));
/*     */       }
/*  42 */       return list;
/*     */     }
/*  44 */     return new ArrayList(0);
/*     */   }
/*     */ 
/*     */   @Override
public boolean createDir(String path, String dirName)
/*     */   {
/*  49 */     File parent = new File(this.realPathResolver.get(path));
/*  50 */     parent.mkdirs();
/*  51 */     File dir = new File(parent, dirName);
/*  52 */     return dir.mkdir();
/*     */   }
/*     */ 
/*     */   @Override
public void saveFile(String path, MultipartFile file) throws IllegalStateException, IOException
/*     */   {
/*  57 */     File dest = new File(this.realPathResolver.get(path), 
/*  58 */       file.getOriginalFilename());
/*  59 */     file.transferTo(dest);
/*     */   }
/*     */ 
/*     */   @Override
public void createFile(String path, String filename, String data) throws IOException
/*     */   {
/*  64 */     File parent = new File(this.realPathResolver.get(path));
/*  65 */     parent.mkdirs();
/*  66 */     File file = new File(parent, filename);
/*  67 */     FileUtils.writeStringToFile(file, data, "UTF-8");
/*     */   }
/*     */ 
/*     */   @Override
public String readFile(String name) throws IOException {
/*  71 */     File file = new File(this.realPathResolver.get(name));
/*  72 */     return FileUtils.readFileToString(file, "UTF-8");
/*     */   }
/*     */ 
/*     */   @Override
public void updateFile(String name, String data) throws IOException {
/*  76 */     File file = new File(this.realPathResolver.get(name));
/*  77 */     FileUtils.writeStringToFile(file, data, "UTF-8");
/*     */   }
/*     */ 
/*     */   @Override
public int delete(String root, String[] names) {
/*  81 */     int count = 0;
/*     */ 
/*  83 */     for (String name : names) {
/*  84 */       File f = new File(this.realPathResolver.get(root + name));
/*  85 */       if (FileUtils.deleteQuietly(f)) {
/*  86 */         count++;
/*     */       }
/*     */     }
/*  89 */     return count;
/*     */   }
/*     */ 
/*     */   @Override
public void rename(String origName, String destName) {
/*  93 */     File orig = new File(this.realPathResolver.get(origName));
/*  94 */     File dest = new File(this.realPathResolver.get(destName));
/*  95 */     orig.renameTo(dest);
/*     */   }
/*     */ 
/*     */   @Override
public void copyTplAndRes(Site from, Site to) throws IOException {
/*  99 */     String fromSol = from.getTplStyle();
/* 100 */     String toSol = to.getTplStyle();
/* 101 */     File tplFrom = new File(this.realPathResolver.get(from.getTplPath()), 
/* 102 */       fromSol);
/* 103 */     File tplTo = new File(this.realPathResolver.get(to.getTplPath()), toSol);
/* 104 */     FileUtils.copyDirectory(tplFrom, tplTo);
/* 105 */     File resFrom = new File(this.realPathResolver.get(from.getResPath()), 
/* 106 */       fromSol);
/* 107 */     if (resFrom.exists()) {
/* 108 */       File resTo = new File(this.realPathResolver.get(to.getResPath()), toSol);
/* 109 */       FileUtils.copyDirectory(resFrom, resTo);
/*     */     }
/*     */   }
/*     */ 
/*     */   @Override
public void delTplAndRes(Site site) throws IOException {
/* 114 */     File tpl = new File(this.realPathResolver.get(site.getTplPath()));
/* 115 */     File res = new File(this.realPathResolver.get(site.getResPath()));
/* 116 */     FileUtils.deleteDirectory(tpl);
/* 117 */     FileUtils.deleteDirectory(res);
/*     */   }
/*     */ 
/*     */   @Override
public String[] getStyles(String path) {
/* 121 */     File tpl = new File(this.realPathResolver.get(path));
/* 122 */     return tpl.list(new FilenameFilter() {
/*     */       @Override
public boolean accept(File dir, String name) {
/* 124 */         return dir.isDirectory();
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setRealPathResolver(RealPathResolver realPathResolver)
/*     */   {
/* 141 */     this.realPathResolver = realPathResolver;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.service.impl.ResourceServiceImpl
 * JD-Core Version:    0.6.1
 */