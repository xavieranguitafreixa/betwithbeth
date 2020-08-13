package com.factorialsigma.betwithbeth.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static com.factorialsigma.betwithbeth.model.MarketType.MarketSide.ONE;
import static com.factorialsigma.betwithbeth.model.MarketType.MarketSide.TWO;
import static com.factorialsigma.betwithbeth.model.MarketType.MarketSide.X;

/**
 * @author
 * @version 1.0
 */
@Getter
public enum MarketType {
    X12(Arrays.asList(ONE, X, TWO));

    private List<MarketSide> keys;

    MarketType(List<MarketSide> keys) {
        this.keys = keys;
    }

    @Getter
    public enum MarketSide {
        ONE("1"),
        X("X"),
        TWO("2");

        private String val;

        MarketSide(String val) {
            this.val = val;
        }
    }

}
