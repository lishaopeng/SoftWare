package com.portal.sysmgr.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.sysmgr.entity.DatabaseConfig;

public abstract interface DatabaseConfigDao extends QueryDao<DatabaseConfig>
{
  public abstract DatabaseConfig findUnique();
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.dao.DatabaseConfigDao
 * JD-Core Version:    0.6.1
 */