package com.portal.sysmgr.staticpage;

import freemarker.template.Configuration;
import java.util.List;
import javax.servlet.ServletContext;

public abstract interface StaticPageChannelDao
{
  public abstract List<String> staticChannelPage(String paramString, Configuration paramConfiguration, ServletContext paramServletContext);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.staticpage.StaticPageChannelDao
 * JD-Core Version:    0.6.1
 */