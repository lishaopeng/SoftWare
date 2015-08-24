package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.PostsExt;
import org.springframework.data.domain.Page;

public abstract interface PostsExtDao extends QueryDao<PostsExt>
{
  public abstract Page<PostsExt> getPage(int paramInt1, int paramInt2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.PostsExtDao
 * JD-Core Version:    0.6.1
 */