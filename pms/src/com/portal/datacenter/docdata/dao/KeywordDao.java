package com.portal.datacenter.docdata.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.datacenter.docdata.entity.Keyword;
import java.util.List;

public abstract interface KeywordDao extends QueryDao<Keyword>
{
  public abstract List<Keyword> getList(Integer paramInteger, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.dao.KeywordDao
 * JD-Core Version:    0.6.1
 */