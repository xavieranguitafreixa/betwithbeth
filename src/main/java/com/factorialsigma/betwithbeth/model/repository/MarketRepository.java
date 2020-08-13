package com.factorialsigma.betwithbeth.model.repository;

import com.factorialsigma.betwithbeth.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author
 * @version 1.0
 */
public interface MarketRepository extends JpaRepository<Market, Integer> {

//    @Query(value = "select m.* from MARKET m join MARKET_VALUE v where (v.market_id = m.id and v.value <= :val and v.value_key = :keyx) order by v.value", nativeQuery = true)
//    List<Market> findByValueLessThanAndKeyEq(@Param("val") float val, @Param("keyx") String key);
}
