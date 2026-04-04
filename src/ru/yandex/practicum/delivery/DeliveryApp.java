package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static List<Trackable> trackableParcels = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();

    static ParcelBox<StandardParcel> standardParcelInBox = new ParcelBox<>(20);
    static ParcelBox<PerishableParcel> perishableParcelInBox = new ParcelBox<>(10);
    static ParcelBox<FragileParcel> fragileParcelInBox = new ParcelBox<>(5);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportStatus();
                    break;
                case 5:
                    getAllParcels();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Изменить местоположение посылки");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Какой тип посылки готовится на отправку?");
        System.out.println("Введите один из трех вариантов: Стандарт, Хрупкое или Скоропортящиеся");
        String parcelType = scanner.nextLine();

        System.out.println("Введите описание посылки: ");
        String description = scanner.nextLine();

        System.out.println("Введите вес посылки: ");
        int weight = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите адрес доставки: ");
        String deliveryAddress = scanner.nextLine();
        System.out.println("Введите день отправки: ");
        int sendDay = scanner.nextInt();
        scanner.nextLine();

        Parcel parcel;

        if (parcelType.equals("Стандарт")) {
            parcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
            standardParcelInBox.addParcel((StandardParcel) parcel);

        } else if (parcelType.equals("Хрупкое")) {

            parcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
            trackableParcels.add((Trackable) parcel);
            fragileParcelInBox.addParcel((FragileParcel) parcel);

        } else if (parcelType.equals("Скоропортящиеся")) {

            System.out.println("Введите срок годности посылки: ");
            int timeToLive = scanner.nextInt();
            scanner.nextLine();
            parcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
            perishableParcelInBox.addParcel((PerishableParcel) parcel);

        } else {
            System.out.println("Неверный тип посылки.");
            return;
        }
        allParcels.add(parcel);
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost(parcel.weight);
        }
        System.out.println("Общая сумма всех доставок составляет: " + totalCost);
    }

    private static void reportStatus() {
        System.out.println("Введите новое местоположение посылки: ");
        String newLocation = scanner.nextLine();
        for (Trackable item : trackableParcels) {
            item.reportStatus(newLocation);
        }
    }

    private static void getAllParcels() {
        System.out.println("Содержимое какой коробки вы хотите узнать? Варианты: 1 - Стандартное, 2 - Хрупкое, " +
                "3 - Скоропортящиеся ");
        int box = scanner.nextInt();
        scanner.nextLine();
        if (box == 1) {
            standardParcelInBox.getAllParcels();
        } else if (box == 2) {
            fragileParcelInBox.getAllParcels();
        } else if (box == 3) {
            perishableParcelInBox.getAllParcels();
        } else {
            System.out.println("Введена неверная цифра");
        }
    }


}

