package com.portal.usermgr.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.usermgr.entity.ThirdpartyBind;

public abstract interface ThirdpartyBindDao extends QueryDao<ThirdpartyBind>
{
  public abstract ThirdpartyBind findByOpenId(String paramString1, String paramString2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.ThirdpartyBindDao
 * JD-Core Version:    0.6.1
 */