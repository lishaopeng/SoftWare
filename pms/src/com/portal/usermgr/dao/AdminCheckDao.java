package com.portal.usermgr.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.usermgr.entity.AdminCheck;

public abstract interface AdminCheckDao extends QueryDao<AdminCheck>
{
  public abstract int deleteBySiteId(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.AdminCheckDao
 * JD-Core Version:    0.6.1
 */