package com.portal.sysmgr.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.sysmgr.entity.Site;
import java.util.List;

public abstract interface SiteDao extends QueryDao<Site>
{
  public abstract List<Site> getList(boolean paramBoolean);

  public abstract Site findByDomain(String paramString, boolean paramBoolean);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.dao.SiteDao
 * JD-Core Version:    0.6.1
 */