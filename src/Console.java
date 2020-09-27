import java.util.Scanner;

public class Console {
    public String readString() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public void print(String string) {
        System.out.println(string);
    }

    public void showSingleCar(Car car, String prefix) {
        if (prefix == null) prefix = "";
        this.print(prefix + car.brand + " " + car.model + ", " + car.serialNumber + ", " + car.yearOfIssue + " г.в., Цвет #" + car.color);
    }

    public void showAllCars(Car[] cars) {
        this.print("\nАвтомобили:");

        for (Car car : cars) {
            if (car != null) {
                this.print(car.brand + " " + car.model + ", " + car.serialNumber + ", " + car.yearOfIssue + " г.в., Цвет #" + car.color);
            }
        }
    }
}
