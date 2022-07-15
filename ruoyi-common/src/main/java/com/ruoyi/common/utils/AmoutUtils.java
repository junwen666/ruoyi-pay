package com.ruoyi.common.utils;

import java.math.BigDecimal;
import java.util.Objects;

public class AmoutUtils {

    public static Integer toCent(BigDecimal amount){
        if(Objects.isNull(amount)){
            return null;
        }
        return amount.multiply(new BigDecimal("100")).intValue();
    }

    public static BigDecimal toDollar(Integer dollar){
        if(Objects.isNull(dollar)){
            return null;
        }
        return new BigDecimal(dollar).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_UP);
    }

}
