package com.portal.usermgr.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.usermgr.entity.SiteMessage;
import org.springframework.data.domain.Page;

public abstract interface SiteMessageDao extends QueryDao<SiteMessage>
{
  public abstract Page<SiteMessage> getPage(int paramInt1, int paramInt2);

  public abstract Page<SiteMessage> getPageByTag(Integer paramInteger1, Integer paramInteger2, int paramInt1, int paramInt2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.SiteMessageDao
 * JD-Core Version:    0.6.1
 */