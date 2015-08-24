package com.portal.sysmgr.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.sysmgr.entity.VisitStatistics;
import java.util.Date;
import org.springframework.data.domain.Page;

public abstract interface VisitStatisticsDao extends QueryDao<VisitStatistics>
{
  public abstract Page<VisitStatistics> getStatisticsByDate(Integer paramInteger, Date paramDate1, Date paramDate2, int paramInt1, int paramInt2);

  public abstract Page<VisitStatistics> getStatisticsByToday(Integer paramInteger, int paramInt1, int paramInt2);

  public abstract Page<VisitStatistics> getStatisticsByHour(Integer paramInteger1, Integer paramInteger2, int paramInt1, int paramInt2);

  public abstract long getStatisticsByDate(Integer paramInteger, Date paramDate1, Date paramDate2);

  public abstract long getStatisticsByToday(Integer paramInteger);

  public abstract long getStatisticsByHour(Integer paramInteger1, Integer paramInteger2);

  public abstract long getStatisticsByMin(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.dao.VisitStatisticsDao
 * JD-Core Version:    0.6.1
 */