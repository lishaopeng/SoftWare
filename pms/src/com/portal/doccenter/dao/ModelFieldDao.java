package com.portal.doccenter.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.doccenter.entity.ModelField;
import java.util.List;

public abstract interface ModelFieldDao extends QueryDao<ModelField>
{
  public abstract List<ModelField> getList(Integer paramInteger, boolean paramBoolean, String paramString1, String paramString2);

  public abstract List<ModelField> getListByPriority(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, boolean paramBoolean);

  public abstract int getMaxPriority(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.ModelFieldDao
 * JD-Core Version:    0.6.1
 */