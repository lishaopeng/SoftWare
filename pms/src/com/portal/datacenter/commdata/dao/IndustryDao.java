package com.portal.datacenter.commdata.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.datacenter.commdata.entity.Industry;
import java.util.List;
import org.springframework.data.domain.Page;

public abstract interface IndustryDao extends QueryDao<Industry>
{
  public abstract Page<Industry> getPage(String paramString, int paramInt1, int paramInt2);

  public abstract List<Industry> getIndustryList(Integer paramInteger);

  public abstract List<Industry> getIndustryChild(Integer paramInteger);

  public abstract Industry findByCode(String paramString);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.dao.IndustryDao
 * JD-Core Version:    0.6.1
 */