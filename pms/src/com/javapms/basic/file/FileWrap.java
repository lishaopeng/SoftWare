/*     */ package com.javapms.basic.file;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Locale;

/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ 
/*     */ public class FileWrap
/*     */ {
/*  29 */   public static final String[] EDITABLE_EXT = { "html", "htm", 
/*  30 */     "css", "js", "txt" };
/*     */   private File file;
/*     */   private String rootPath;
/*     */   private FileFilter filter;
/*     */   private List<FileWrap> child;
/*     */   private String filename;
/*     */ 
/*     */   public FileWrap(File file)
/*     */   {
/*  44 */     this(file, null);
/*     */   }
/*     */ 
/*     */   public FileWrap(File file, String rootPath)
/*     */   {
/*  56 */     this(file, rootPath, null);
/*     */   }
/*     */ 
/*     */   public FileWrap(File file, String rootPath, FileFilter filter)
/*     */   {
/*  70 */     this.file = file;
/*  71 */     this.rootPath = rootPath;
/*  72 */     this.filter = filter;
/*     */   }
/*     */ 
/*     */   public static boolean editableExt(String ext)
/*     */   {
/*  83 */     ext = ext.toLowerCase(Locale.ENGLISH);
/*  84 */     for (String s : EDITABLE_EXT) {
/*  85 */       if (s.equals(ext)) {
/*  86 */         return true;
/*     */       }
/*     */     }
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  98 */     String path = this.file.getAbsolutePath();
/*  99 */     String relPath = path.substring(this.rootPath.length());
/* 100 */     return relPath.replace(File.separator, "/");
/*     */   }
/*     */ 
/*     */   public String getPath()
/*     */   {
/* 109 */     String name = getName();
/* 110 */     return name.substring(0, name.lastIndexOf('/'));
/*     */   }
/*     */ 
/*     */   public String getFilename()
/*     */   {
/* 121 */     return this.filename != null ? this.filename : this.file.getName();
/*     */   }
/*     */ 
/*     */   public String getExtension()
/*     */   {
/* 130 */     return FilenameUtils.getExtension(this.file.getName());
/*     */   }
/*     */ 
/*     */   public long getLastModified()
/*     */   {
/* 139 */     return this.file.lastModified();
/*     */   }
/*     */ 
/*     */   public Date getLastModifiedDate()
/*     */   {
/* 148 */     return new Timestamp(this.file.lastModified());
/*     */   }
/*     */ 
/*     */   public long getSize()
/*     */   {
/* 157 */     return this.file.length() / 1024L + 1L;
/*     */   }
/*     */ 
/*     */   public String getIco()
/*     */   {
/* 175 */     if (this.file.isDirectory()) {
/* 176 */       return "folder";
/*     */     }
/* 178 */     String ext = getExtension().toLowerCase();
/* 179 */     if ((ext.equals("jpg")) || (ext.equals("jpeg")))
/* 180 */       return "jpg";
/* 181 */     if (ext.equals("png"))
/* 182 */       return "png";
/* 183 */     if (ext.equals("gif"))
/* 184 */       return "gif";
/* 185 */     if ((ext.equals("html")) || (ext.equals("htm")))
/* 186 */       return "html";
/* 187 */     if (ext.equals("swf"))
/* 188 */       return "swf";
/* 189 */     if (ext.equals("txt")) {
/* 190 */       return "txt";
/*     */     }
/* 192 */     return "unknow";
/*     */   }
/*     */ 
/*     */   public List<FileWrap> getChild()
/*     */   {
/* 204 */     if (this.child != null)
/* 205 */       return this.child;
/*     */     File[] files;
/* 208 */     if (this.filter == null)
/* 209 */       files = getFile().listFiles();
/*     */     else {
/* 211 */       files = getFile().listFiles(this.filter);
/*     */     }
/* 213 */     List list = new ArrayList();
/* 214 */     if (files != null) {
/* 215 */       Arrays.sort(files, new FileComparator());
/* 216 */       for (File f : files) {
/* 217 */         FileWrap fw = new FileWrap(f, this.rootPath, this.filter);
/* 218 */         list.add(fw);
/*     */       }
/*     */     }
/* 221 */     return list;
/*     */   }
/*     */ 
/*     */   public File getFile()
/*     */   {
/* 230 */     return this.file;
/*     */   }
/*     */ 
/*     */   public boolean isEditable()
/*     */   {
/* 239 */     if (isDirectory()) {
/* 240 */       return false;
/*     */     }
/* 242 */     String ext = getExtension().toLowerCase();
/* 243 */     for (String s : EDITABLE_EXT) {
/* 244 */       if (s.equals(ext)) {
/* 245 */         return true;
/*     */       }
/*     */     }
/* 248 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isDirectory()
/*     */   {
/* 257 */     return this.file.isDirectory();
/*     */   }
/*     */ 
/*     */   public boolean isFile()
/*     */   {
/* 266 */     return this.file.isFile();
/*     */   }
/*     */ 
/*     */   public void setFile(File file)
/*     */   {
/* 275 */     this.file = file;
/*     */   }
/*     */ 
/*     */   public void setFilename(String filename)
/*     */   {
/* 284 */     this.filename = filename;
/*     */   }
/*     */ 
/*     */   public void setChild(List<FileWrap> child)
/*     */   {
/* 293 */     this.child = child;
/*     */   }
/*     */ 
/*     */   public static class FileComparator implements Comparator<File> {
/*     */     @Override
public int compare(File o1, File o2) {
/* 298 */       if ((o1.isDirectory()) && (!o2.isDirectory()))
/* 299 */         return -1;
/* 300 */       if ((!o1.isDirectory()) && (o2.isDirectory())) {
/* 301 */         return 1;
/*     */       }
/* 303 */       return o1.compareTo(o2);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.file.FileWrap
 * JD-Core Version:    0.6.1
 */