package com.portal.datacenter.commdata.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.datacenter.commdata.entity.ForeignLang;
import java.util.List;
import org.springframework.data.domain.Page;

public abstract interface ForeignLangDao extends QueryDao<ForeignLang>
{
  public abstract Page<ForeignLang> getPage(int paramInt1, int paramInt2);

  public abstract List<ForeignLang> getForeignLangList();
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.dao.ForeignLangDao
 * JD-Core Version:    0.6.1
 */