package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.CommentExt;

public abstract interface CommentExtDao extends QueryDao<CommentExt>
{
  public abstract int deleteByParentId(Integer paramInteger);

  public abstract int deleteByDocId(Integer paramInteger);

  public abstract int deleteByTreeNumber(String paramString);

  public abstract int deleteByDocUserId(Integer paramInteger);

  public abstract int deleteByUserId(Integer paramInteger);

  public abstract int deleteByUserIdAndParent(Integer paramInteger);

  public abstract int deleteBySiteId(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.CommentExtDao
 * JD-Core Version:    0.6.1
 */