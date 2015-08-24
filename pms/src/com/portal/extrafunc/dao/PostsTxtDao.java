package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.PostsTxt;
import org.springframework.data.domain.Page;

public abstract interface PostsTxtDao extends QueryDao<PostsTxt>
{
  public abstract Page<PostsTxt> getPage(int paramInt1, int paramInt2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.PostsTxtDao
 * JD-Core Version:    0.6.1
 */