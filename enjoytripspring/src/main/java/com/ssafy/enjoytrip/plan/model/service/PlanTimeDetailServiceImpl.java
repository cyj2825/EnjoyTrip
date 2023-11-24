package com.ssafy.enjoytrip.plan.model.service;

import com.ssafy.enjoytrip.plan.model.dao.PlanTimeDetailDao;
import com.ssafy.enjoytrip.plan.model.dto.PlanException;
import com.ssafy.enjoytrip.plan.model.dto.PlanTimeDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@Repository
public class PlanTimeDetailServiceImpl implements PlanTimeDetailService {
    @Autowired
    private PlanTimeDetailDao planTimeDetailDao;

    public PlanTimeDetailServiceImpl(PlanTimeDetailDao planTimeDetailDao){
        this.planTimeDetailDao = planTimeDetailDao;
    }

    @Override
    public void insertTime(PlanTimeDetailDto dto) {
        try{
            planTimeDetailDao.insertPlanTime(dto);
        } catch (SQLException e) {
            throw new PlanException("계획 등록 중 오류 발생");
        }
    }

    @Override
    public List<PlanTimeDetailDto> getTimeList(int dateNo) {
        try{
            return planTimeDetailDao.getTimeList(dateNo);
        }
        catch (SQLException e){
            throw new PlanException("계획 등록 중 오류 발생");
        }
    }
}
