package com.javapms.basic.hibernate4;

import org.springframework.data.domain.Page;

public abstract interface QueryDao<T>
{
  public abstract T findById(Integer paramInteger);

  public abstract T save(T paramT);

  public abstract T update(T paramT);

  public abstract T deleteById(Integer paramInteger);

  public abstract Page<T> getPage(int paramInt1, int paramInt2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.hibernate4.QueryDao
 * JD-Core Version:    0.6.1
 */