package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryBoxTest {

    protected static StandardParcel standardParcel = new StandardParcel("Мишка ", 5,
            "ул.Полова 15 ", 12);
    protected static FragileParcel fragileParcel = new FragileParcel("Ваза ", 3,
            "ул.Титова 12 ", 30);

    static ParcelBox<StandardParcel> standardParcelInBox = new ParcelBox<>(20);
    static ParcelBox<FragileParcel> fragileParcelInBox = new ParcelBox<>(5);

    @Test
    public void parcelIsAddedIfWeightUnderLimit() {
        int initialCount = standardParcelInBox.getParcelInBox().size();
        standardParcelInBox.addParcel(standardParcel);
        assertEquals(initialCount + 1, standardParcelInBox.getParcelInBox().size(), "Количество " +
                "посылок должно увеличиться на 1, если посылка была добавлена");
    }

    @Test
    public void parcelIsNotAddedIfWeightExceedsLimit() {
        fragileParcelInBox.addParcel(fragileParcel);
        int initialCount = fragileParcelInBox.getCurrentWeight();
        fragileParcelInBox.addParcel(fragileParcel);
        assertEquals(initialCount, fragileParcelInBox.getCurrentWeight());
    }
    @Test
    public void parcelIsAddedIfWeightIsEqualLimit() {
        fragileParcelInBox.addParcel(fragileParcel); // добавили 3 кг, осталось еще 2
        int initialCount = fragileParcelInBox.getParcelInBox().size(); // проверили, что посылка добавлена
        int checkWeight = fragileParcelInBox.getMaxWeight() - fragileParcelInBox.getCurrentWeight(); // проверили, что
        // осталось еще 2 кг вместимость бокса
        FragileParcel fragileParcelTwo = new FragileParcel("Графин ", checkWeight,
                "ул.Пугачева 32 ", 12); // создаем посылку внутри теста, чтобы передать
        // текущий недостающий вес и создать посылку именно с этим весом для проверки пограничного случая
        fragileParcelInBox.addParcel(fragileParcelTwo); // добавили еще 2 кг
        assertEquals(initialCount + 1, fragileParcelInBox.getParcelInBox().size()); // проверили, что
        // добавилась 1 посылка
        assertEquals(fragileParcelInBox.getMaxWeight(), fragileParcelInBox.getCurrentWeight()); // проверили, что
        // максимальный вес бокса равен текущему весу загруженных посылок
    }



}
