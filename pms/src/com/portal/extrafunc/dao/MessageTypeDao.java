package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.MessageType;
import java.util.List;
import org.springframework.data.domain.Page;

public abstract interface MessageTypeDao extends QueryDao<MessageType>
{
  public abstract Page<MessageType> getPage(int paramInt1, int paramInt2);

  public abstract List<MessageType> getList(Integer paramInteger, String paramString1, String paramString2);

  public abstract MessageType getUniqueType(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.MessageTypeDao
 * JD-Core Version:    0.6.1
 */