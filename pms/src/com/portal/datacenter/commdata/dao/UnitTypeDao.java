package com.portal.datacenter.commdata.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.datacenter.commdata.entity.UnitType;
import org.springframework.data.domain.Page;

public abstract interface UnitTypeDao extends QueryDao<UnitType>
{
  public abstract Page<UnitType> getPage(int paramInt1, int paramInt2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.dao.UnitTypeDao
 * JD-Core Version:    0.6.1
 */