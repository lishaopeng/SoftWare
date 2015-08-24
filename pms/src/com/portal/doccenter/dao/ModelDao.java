package com.portal.doccenter.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.doccenter.entity.Model;
import java.util.List;

public abstract interface ModelDao extends QueryDao<Model>
{
  public abstract List<Model> getList(boolean paramBoolean, String paramString1, String paramString2);

  public abstract Model getDefModel();
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.ModelDao
 * JD-Core Version:    0.6.1
 */