package com.protas.movieapp.utils.price;

import com.protas.movieapp.reservation.Reservation;
import com.protas.movieapp.seat.SeatType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PriceCalculator {

    @Value("${cinema.price.basic}")
    private static BigDecimal basicPrice;

    @Value("${cinema.price.normal}")
    private static BigDecimal normalPrice;

    @Value("${cinema.price.vip}")
    private static BigDecimal vipPrice;

    public static BigDecimal calculatePrice(Reservation reservation) {
        SeatType type = reservation.getSeat().getSeatType();
        return getValueFromType(type);
    }

    private static BigDecimal getValueFromType(SeatType seatType) {
        return switch (seatType) {
            case BASIC -> basicPrice;
            case NORMAL -> normalPrice;
            case VIP -> vipPrice;
            default -> throw new IllegalStateException("Unexpected value: " + seatType);
        };
    }
}
