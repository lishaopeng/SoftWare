package com.portal.datacenter.operatedata.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.datacenter.operatedata.entity.Log;
import java.util.Date;
import org.springframework.data.domain.Page;

public abstract interface LogDao extends QueryDao<Log>
{
  public abstract Page<Log> getPage(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString1, String paramString2, int paramInt1, int paramInt2);

  public abstract int deleteBatch(Integer paramInteger1, Integer paramInteger2, Date paramDate);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.operatedata.dao.LogDao
 * JD-Core Version:    0.6.1
 */