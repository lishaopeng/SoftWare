package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.QuestionDetail;
import org.springframework.data.domain.Page;

public abstract interface QuestionDetailDao extends QueryDao<QuestionDetail>
{
  public abstract Page<QuestionDetail> getPage(Integer paramInteger, int paramInt1, int paramInt2);

  public abstract QuestionDetail getDetail(Integer paramInteger1, Integer paramInteger2, String paramString);

  public abstract long getCountDetail(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.QuestionDetailDao
 * JD-Core Version:    0.6.1
 */