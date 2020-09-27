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

    Scanner userInput;

    public void run() {
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
            this.readUserInput();

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
                System.out.println("Добавлено.");
                System.out.println("\nМашины в наличии:");

                this.showAllCars();

                this.nextCarNumber++;
            } else {
                System.out.println("Неверный формат строки. Для выхода нажмите Enter.");
            }
        }
    }

    public void removeCarsByBrand() {
        this.showAllCars();

        System.out.println("\nВведите марку автомобилей, которые необходимо удалить:");

        boolean deleted = false;
        while (!this.inputString.equals("")) {
            this.readUserInput();
            if (this.inputString.equals("")) continue;

            for (int i = 0; i < this.cars.length; i++) {
                if (this.cars[i] != null) {
                    if(this.inputString.equals(this.cars[i].brand)) {
                        System.out.println("[DELETED] Удалено: " + this.cars[i].brand + " " + this.cars[i].model + ", " + this.cars[i].serialNumber + ", " + this.cars[i].yearOfIssue + " г.в., Цвет #" + this.cars[i].color);

                        this.cars[i] = null;
                        deleted = true;
                    }
                }
            }

            if(deleted) {
                this.inputString = "";
            } else {
                System.out.println("Машин с такой маркой не найдено.\n" +
                        "Попробуйте ещё раз или используйте Enter для продолжения.");
            }
        }

        System.out.println("\nАвтомобили:");
        this.showAllCars();
    }

    public void duplicateCarsByYear() {
        System.out.println("\nВведите год выпуска:");

        while (!this.inputString.equals("")) {
            this.readUserInput();
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

                            System.out.println("[ADDED] Добавлено: " + this.cars[this.nextCarNumber].brand + " " + this.cars[this.nextCarNumber].model + ", " + this.cars[this.nextCarNumber].serialNumber + ", " + this.cars[this.nextCarNumber].yearOfIssue + " г.в., Цвет #" + this.cars[this.nextCarNumber].color);

                            this.nextCarNumber++;
                        }
                    }
                }

                this.inputString = "";
            } else {
                System.out.println("Неверный год. Для выхода нажмите Enter.");
            }
        }

        System.out.println("\nАвтомобили:");
        this.showAllCars();
    }

    public void showAllCars() {
        for (Car car : this.cars) {
            if (car != null) {
                System.out.println(car.brand + " " + car.model + ", " + car.serialNumber + ", " + car.yearOfIssue + " г.в., Цвет #" + car.color);
            }
        }
    }

    public void readUserInput() {
        this.userInput = new Scanner(System.in);
        this.inputString = this.userInput.nextLine();
    }
}
