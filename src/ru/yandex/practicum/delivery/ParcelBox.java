package ru.yandex.practicum.delivery;

import java.util.List;
import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {

    private List<T> parcelInBox = new ArrayList<>();
    private int maxWeight;
    private int currentWeight = 0;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel) {
        if (currentWeight + parcel.getWeight() <= maxWeight) {
            parcelInBox.add(parcel);
            currentWeight += parcel.getWeight();
            System.out.println("Посылка успешно добавлена в коробку!");
        } else {
            System.out.println("Вес посылки превышает максимальный допустимый вес коробки. Пожалуйста разделите посылку" +
                    " или выберите другой тип упаковки! ");
        }
    }

    public void getAllParcels() {
        for (T parcel : parcelInBox) {
            System.out.println(parcel.description);
        }
    }

    public List<T> getParcelInBox() {
        return parcelInBox;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }
}
