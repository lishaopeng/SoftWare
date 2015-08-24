package com.portal.sysmgr.staticpage;

import com.portal.datacenter.docdata.service.KeywordService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import javax.servlet.ServletContext;

public abstract interface StaticPageArticleDao
{
  public abstract String staticArticlePage(String paramString, Configuration paramConfiguration, ServletContext paramServletContext, KeywordService paramKeywordService)
    throws IOException, TemplateException;
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.staticpage.StaticPageArticleDao
 * JD-Core Version:    0.6.1
 */