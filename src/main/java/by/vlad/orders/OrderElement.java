package by.vlad.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum OrderElement {
    IT_STEAK(500, 10),
    LEGACY_SALAD(50, 5),
    SWITCH_POTATO(300, 3),
    DEBUG_KOLA(25, 2),
    SCRIPT_ICE_CREAM(150, 4);

    private final Integer calories;
    private final Integer price;

}
