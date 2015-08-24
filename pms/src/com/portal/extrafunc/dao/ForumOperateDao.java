package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.ForumOperate;
import org.springframework.data.domain.Page;

public abstract interface ForumOperateDao extends QueryDao<ForumOperate>
{
  public abstract Page<ForumOperate> getPage(int paramInt1, int paramInt2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.ForumOperateDao
 * JD-Core Version:    0.6.1
 */