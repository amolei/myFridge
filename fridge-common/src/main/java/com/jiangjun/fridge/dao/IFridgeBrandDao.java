package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.FridgeBrandDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjun on 16/6/26.
 */
@Repository
public interface IFridgeBrandDao {

    public void addFridgeBrand(FridgeBrandDto fridgeBrandDto);

    public List<FridgeBrandDto> list();
}
