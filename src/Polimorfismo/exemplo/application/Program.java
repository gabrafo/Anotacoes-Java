package Polimorfismo.exemplo.application;

import Polimorfismo.exemplo.entities.Employee;
import Polimorfismo.exemplo.entities.OutsourcedEmployee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of employees: ");
        int n = sc.nextInt();
        List<Employee> list = new ArrayList<>();

        for(int i = 0; i<n; i++){
            System.out.println("Employee #" + (i+1) + " data:");
            System.out.print("Outsourced (y/n)? ");
            char answer = sc.next().charAt(0);
            sc.nextLine(); // Consumir buffer
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Hours: ");
            int hours = sc.nextInt();
            System.out.print("Value per hour: ");
            double valuePerHour = sc.nextDouble();
            sc.nextLine(); // Consumir buffer
            if(answer == 'y' || answer == 'Y'){
                System.out.print("Additional charge: ");
                double additionalCharge = sc.nextDouble();
                list.add(new OutsourcedEmployee(name, hours, valuePerHour, additionalCharge));
            } else {
                list.add(new Employee(name, hours, valuePerHour));
            }
        }

        System.out.println("PAYMENTS:");
        for(int i = 0; i<n; i++){
            System.out.printf(list.get(i).getName() + " - %.2f\n", list.get(i).payment());
        }
        sc.close();
    }
}
