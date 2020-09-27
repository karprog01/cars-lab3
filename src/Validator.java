import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    String inputString = "input";

    // Можно сделать динамический массив,
    //    который будет расширяться при добавлении новой машины
    //    и уменьшаться при удалении
    Car[] cars = new Car[20];
    // 0
    int nextCarNumber = 5;

    Pattern addCarPattern = Pattern.compile("^([a-zA-Zа-яА-Я]+) ([a-zA-Zа-яА-Я0-9+]+), ([0-9]{7}), ([0-9]{4}), ([0-9]{3})$");
    Pattern enterYearPattern = Pattern.compile("^([0-9]{4})$");
    Matcher matcher;

    Console console;

    public void run() {
        this.console = new Console();

        // delete
        this.cars[0] = new Car("Toyota", "Supra", 23155555, 1999, 333);
        this.cars[1] = new Car("Toyota", "LandCruiser", 23155555, 2009, 333);
        this.cars[2] = new Car("Nissan", "240SX", 23155555, 1990, 333);
        this.cars[3] = new Car("Skoda", "Octavia", 23155555, 2014, 333);
        this.cars[4] = new Car("Nissan", "Almera", 23155555, 2011, 333);

        this.addCars();
        this.inputString = "input";
        this.removeCarsByBrand();
        this.inputString = "input";
        this.duplicateCarsByYear();
    }

    public void addCars() {
        while (!this.inputString.equals("")) {
            System.out.println("\nВведите данные об автомобиле в формате:");
            System.out.println("Марка модель, серийный номер (7 цифр), дата выпуска (4 цифры), цвет (3 цифры)");
            this.inputString = this.console.readString();

            if (this.inputString.equals("")) continue;

            this.matcher = this.addCarPattern.matcher(this.inputString);

            if (this.matcher.find()) {
                this.cars[this.nextCarNumber] = new Car(
                        this.matcher.group(1),
                        this.matcher.group(2),
                        Integer.parseInt(this.matcher.group(3)),
                        Integer.parseInt(this.matcher.group(4)),
                        Integer.parseInt(this.matcher.group(5))
                );
                console.print("Добавлено.");

                this.console.showAllCars(this.cars);

                this.nextCarNumber++;
            } else {
                console.print("Неверный формат строки. Для выхода нажмите Enter.");
            }
        }
    }

    public void removeCarsByBrand() {
        this.console.showAllCars(this.cars);

        console.print("\nВведите марку автомобилей, которые необходимо удалить:");

        boolean deleted = false;
        while (!this.inputString.equals("")) {
            this.inputString = this.console.readString();
            if (this.inputString.equals("")) continue;

            for (int i = 0; i < this.cars.length; i++) {
                if (this.cars[i] != null) {
                    if(this.inputString.equals(this.cars[i].brand)) {
                        this.console.showSingleCar(this.cars[i], "[DELETED] Удалено: ");

                        this.cars[i] = null;
                        deleted = true;
                    }
                }
            }

            if(deleted) {
                this.inputString = "";
            } else {
                this.console.print("Машин с такой маркой не найдено.\n" +
                        "Попробуйте ещё раз или используйте Enter для продолжения.");
            }
        }

        this.console.showAllCars(this.cars);
    }

    public void duplicateCarsByYear() {
        this.console.print("\nВведите год выпуска:");

        while (!this.inputString.equals("")) {
            this.inputString = this.console.readString();
            int enteredYear = Integer.parseInt(this.inputString);

            this.matcher = this.enterYearPattern.matcher(this.inputString);

            if (this.matcher.find()) {
                int carsLength = this.nextCarNumber;

                for (int i = 0; i < carsLength; i++) {
                    if (this.cars[i] != null) {
                        if(enteredYear < this.cars[i].yearOfIssue) {
                            this.cars[this.nextCarNumber] = new Car(
                                    this.cars[i].brand,
                                    this.cars[i].model,
                                    this.cars[i].serialNumber,
                                    this.cars[i].yearOfIssue,
                                    this.cars[i].color
                            );

                            this.console.showSingleCar(this.cars[this.nextCarNumber], "[ADDED] Добавлено: ");

                            this.nextCarNumber++;
                        }
                    }
                }

                this.inputString = "";
            } else {
                this.console.print("Неверный год. Для выхода нажмите Enter.");
            }
        }

        this.console.showAllCars(this.cars);
    }
}
