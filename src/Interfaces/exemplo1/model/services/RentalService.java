package Interfaces.exemplo1.model.services;

import Interfaces.exemplo1.model.entities.CarRental;
import Interfaces.exemplo1.model.entities.Invoice;

import java.time.Duration;

public class RentalService {
    private Double pricePerDay;
    private Double pricePerHour;

    private BrazilTaxService taxService; // Isso não é uma boa prática, faz com que o RentalService dependa do BrazilTaxService

    public RentalService(Double pricePerHour, Double pricePerDay, BrazilTaxService taxService) {
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.taxService = taxService;
    }

    public void processInvoice(CarRental carRental){

        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        double hours = minutes/60.0; // Número de horas em fração

        double basicPayment;
        if(hours<=12){
            basicPayment = pricePerHour*Math.ceil(hours); // Math.ceil() arredonda horas pra cima
        } else {
            basicPayment = pricePerDay*Math.ceil(hours/24);
        }
        double tax = taxService.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}
