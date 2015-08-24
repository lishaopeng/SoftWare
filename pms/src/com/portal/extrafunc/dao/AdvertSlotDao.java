package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.AdvertSlot;
import java.util.List;
import org.springframework.data.domain.Page;

public abstract interface AdvertSlotDao extends QueryDao<AdvertSlot>
{
  public abstract Page<AdvertSlot> getPage(Integer paramInteger, String paramString1, String paramString2, int paramInt1, int paramInt2);

  public abstract List<AdvertSlot> getList(Integer paramInteger);

  public abstract int deleteBySiteId(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.AdvertSlotDao
 * JD-Core Version:    0.6.1
 */