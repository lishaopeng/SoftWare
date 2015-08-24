package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.Links;
import java.util.List;
import org.springframework.data.domain.Page;

public abstract interface LinksDao extends QueryDao<Links>
{
  public abstract Page<Links> getPage(Integer paramInteger1, Integer paramInteger2, String paramString1, String paramString2, int paramInt1, int paramInt2);

  public abstract List<Links> getListByTag(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.LinksDao
 * JD-Core Version:    0.6.1
 */