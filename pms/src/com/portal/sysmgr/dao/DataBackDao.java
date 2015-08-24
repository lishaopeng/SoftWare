package com.portal.sysmgr.dao;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

public abstract interface DataBackDao
{
  public abstract List<Object[]> getCreateTableData(String paramString);

  public abstract DatabaseMetaData getMetaData()
    throws SQLException;

  public abstract String getDefaultCatalog()
    throws SQLException;

  public abstract Boolean executeSQL(String paramString);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.dao.DataBackDao
 * JD-Core Version:    0.6.1
 */