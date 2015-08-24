/*    */ package com.portal.datacenter.docdata.action.fnt;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.datacenter.docdata.service.ProgramDownloadService;
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.net.URI;
/*    */ import java.net.URISyntaxException;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.http.client.ClientProtocolException;
/*    */ import org.apache.http.client.HttpClient;
/*    */ import org.apache.http.client.methods.HttpGet;
/*    */ import org.apache.http.impl.client.DefaultHttpClient;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class ProgramDownloadAct
/*    */ {
/*    */   private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
/*    */ 
/*    */   @Autowired
/*    */   private ServletContext ctx;
/*    */ 
/*    */   @Autowired
/*    */   private ProgramDownloadService programDownloadService;
/*    */ 
/*    */   @RequestMapping(value={"/pgdowload.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public void programDownload(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 46 */     File file = new File(this.ctx.getRealPath("/release/javapms-1.3-final.zip"));
/* 47 */     response.setContentType("text/html; charset=UTF-8");
/* 48 */     response.setContentType("application/x-msdownload");
/* 49 */     response.setHeader("Content-Disposition", 
/* 50 */       "attachment;filename=javapms-1.3-final.zip");
/* 51 */     response.setContentLength((int)file.length());
/* 52 */     HttpClient client = new DefaultHttpClient();
/*    */     try
/*    */     {
/* 55 */       HttpGet httpget = new HttpGet(new URI("http://www.javapms.com/pgdowload2.jsp"));
/* 56 */       client.execute(httpget);
/*    */     } catch (URISyntaxException e) {
/* 58 */       System.out.println("URL错误或者网络断开!");
/*    */     } catch (ClientProtocolException e1) {
/* 60 */       e1.printStackTrace();
/*    */     } catch (IOException e1) {
/* 62 */       e1.printStackTrace();
/*    */     }
/* 64 */     this.programDownloadService.updateCount();
/*    */     try
/*    */     {
/* 67 */       FileInputStream fis = new FileInputStream(file);
/* 68 */       BufferedInputStream buff = new BufferedInputStream(fis);
/* 69 */       byte[] b = new byte[1024];
/* 70 */       long k = 0L;
/*    */       try
/*    */       {
/* 73 */         OutputStream myout = response.getOutputStream();
/* 74 */         while (k < file.length()) {
/* 75 */           int j = buff.read(b, 0, 1024);
/* 76 */           k += j;
/* 77 */           myout.write(b, 0, j);
/*    */         }
/* 79 */         myout.flush();
/*    */       } catch (IOException e) {
/* 81 */         e.printStackTrace();
/*    */       }
/*    */     } catch (FileNotFoundException e) {
/* 84 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   @RequestMapping(value={"/pgdowload2.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public void programDownload2(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException
/*    */   {
/* 91 */     JSONObject json = new JSONObject();
/* 92 */     this.programDownloadService.updateCount();
/* 93 */     json.put("success", true);
/* 94 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.action.fnt.ProgramDownloadAct
 * JD-Core Version:    0.6.1
 */