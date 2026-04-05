package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryCostTest {

    protected static StandardParcel standardParcel = new StandardParcel("Мишка ", 5,
            "ул.Полова 15 ", 12);
    protected static PerishableParcel perishableParcel = new PerishableParcel("Тортик ", 3,
            "ул.Кишинева 43 ", 3, 5);
    protected static FragileParcel fragileParcel = new FragileParcel("Ваза ", 3,
            "ул.Титова 12 ", 30);

    @Test
    public void calculateParcelForStandardPackage() {
        assertEquals(10, standardParcel.calculateDeliveryCost(standardParcel.getWeight()));
    }

    @Test
    public void calculateParcelForPerishablePackage() {
        assertEquals(9, perishableParcel.calculateDeliveryCost(perishableParcel.getWeight()));
    }

    @Test
    public void calculateParcelForFragilePackage() {
        assertEquals(12, fragileParcel.calculateDeliveryCost(fragileParcel.getWeight()));
    }

}
