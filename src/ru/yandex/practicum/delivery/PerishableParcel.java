package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {

    protected static final int BASE_COST = 3;
    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }

    public boolean isExpired(int currentDay) { //!!!!!!!!!!!!!!!!!!пока не использован
        if ((sendDay + timeToLive) >= currentDay) {
            return false;
        } else {
            return true;
        }
    }
}
