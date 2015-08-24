package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.MessageBoardExt;
import org.springframework.data.domain.Page;

public abstract interface MessageBoardExtDao extends QueryDao<MessageBoardExt>
{
  public abstract Page<MessageBoardExt> getPage(int paramInt1, int paramInt2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.MessageBoardExtDao
 * JD-Core Version:    0.6.1
 */