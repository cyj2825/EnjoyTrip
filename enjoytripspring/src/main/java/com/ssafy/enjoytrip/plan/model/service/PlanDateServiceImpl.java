package com.ssafy.enjoytrip.plan.model.service;

import com.ssafy.enjoytrip.plan.model.dao.PlanDao;
import com.ssafy.enjoytrip.plan.model.dao.PlanDateDetailDao;
import com.ssafy.enjoytrip.plan.model.dto.PlanDateDetailDto;
import com.ssafy.enjoytrip.plan.model.dto.PlanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@Repository
public class PlanDateServiceImpl implements PlanDateService{
    @Autowired
    private PlanDateDetailDao planDateDao;
    public PlanDateServiceImpl(PlanDateDetailDao dto) {this.planDateDao = dto;}

    @Override
    public void insertPlanDate(PlanDateDetailDto dto){
        try{
            planDateDao.insertPlanDate(dto);
        }
        catch (SQLException e){
            throw new PlanException("계획 등록 중 오류 발생");
        }
    }

    @Override
    public List<PlanDateDetailDto> getDateList(int planNo) {
        try{
            return planDateDao.getDateList(planNo);
        } catch (SQLException e) {
            throw new PlanException("계획 등록 중 오류 발생");
        }
    }
}
