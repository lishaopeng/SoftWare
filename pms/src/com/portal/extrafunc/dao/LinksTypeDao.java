package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.LinksType;
import java.util.List;

public abstract interface LinksTypeDao extends QueryDao<LinksType>
{
  public abstract List<LinksType> getList(Integer paramInteger, String paramString1, String paramString2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.LinksTypeDao
 * JD-Core Version:    0.6.1
 */