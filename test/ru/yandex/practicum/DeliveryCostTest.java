package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryCostTest {

    protected static StandardParcel standardParcel = new StandardParcel("Мишка ", 5,
            "ул.Полова 15 ", 12);

    protected static PerishableParcel perishableParcel = new PerishableParcel("Тортик ", 3,
            "ул.Кишинева 43 ", 3, 5);
    protected static FragileParcel fragileParcel = new FragileParcel("Ваза ", 3,
            "ул.Титова 12 ", 30);
    protected static FragileParcel fragileParcelTwo = new FragileParcel("Графин ", 3,
            "ул.Малькова 115 ", 15);

    static ParcelBox<StandardParcel> standardParcelInBox = new ParcelBox<>(20);
    static ParcelBox<PerishableParcel> perishableParcelInBox = new ParcelBox<>(10);
    static ParcelBox<FragileParcel> fragileParcelInBox = new ParcelBox<>(5);


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

    @Test
    public void CheckingTheAdditionOfANewParcelToTheBoxIfTheMaximumWeightIsNotExceeded() {
        int initialCount = standardParcelInBox.getParcelInBox().size();
        standardParcelInBox.addParcel(standardParcel);
        assertEquals(initialCount + 1, standardParcelInBox.getParcelInBox().size(), "Количество " +
                "посылок должно увеличиться на 1, если посылка была добавлена");
    }

    @Test
    public void IfTheMaximumWeightIsNotExceededThePackageWillNotBeAdded() {
        fragileParcelInBox.addParcel(fragileParcel);
        int initialCount = fragileParcelInBox.getCurrentWeight();
        fragileParcelInBox.addParcel(fragileParcelTwo);
        assertEquals(initialCount, fragileParcelInBox.getCurrentWeight());
    }

}
