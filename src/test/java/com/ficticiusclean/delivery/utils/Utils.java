package com.ficticiusclean.delivery.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.ficticiusclean.delivery.entity.Vehicle;

public class Utils {

    public static Vehicle createVehicle(){
        Random random = new Random();
        LocalDate localDate = LocalDate.now();
        
        return new Vehicle(
            new Random().nextLong(),
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5),
            localDate,
            new BigDecimal(random.nextInt(100)),
            new BigDecimal(random.nextInt(100))
        );
    }
}
