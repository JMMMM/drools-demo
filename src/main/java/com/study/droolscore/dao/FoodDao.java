package com.study.droolscore.dao;

import com.study.droolscore.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * com.study.droolscore.dao
 *
 * @author jimmy
 * @date 2019-07-09
 */
@Repository
public interface FoodDao extends JpaRepository<Food, Integer> {
}
