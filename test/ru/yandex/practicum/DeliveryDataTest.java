package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.PerishableParcel;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeliveryDataTest {

    protected static PerishableParcel perishableParcel = new PerishableParcel("Тортик ", 3,
            "ул.Кишинева 43 ", 3, 5);

    @Test
    public void InTheExpirationDateRange() {
        int currentDay = 7;
        boolean result = perishableParcel.isExpired(currentDay);
        assertFalse(result, "Посылка не должна быть просрочена на 7-й день");
    }

    @Test
    public void atTheExpirationDateLimit() {
        int currentDay = 8;
        boolean result = perishableParcel.isExpired(currentDay);
        assertFalse(result, "Посылка еще не должна быть просрочена на 8-й день");
    }

    @Test
    public void beyondTheExpirationDate() {
        int currentDay = 9;
        boolean result = perishableParcel.isExpired(currentDay);
        assertTrue(result, "Посылка уже должна быть просрочена на 9-й день");
    }
}
