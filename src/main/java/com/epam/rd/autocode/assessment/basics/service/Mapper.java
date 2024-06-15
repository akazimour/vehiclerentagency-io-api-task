package com.epam.rd.autocode.assessment.basics.service;

import com.epam.rd.autocode.assessment.basics.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Mapper {
    public static Client csvToClient(String[] values) {
        long id = isEmptyLong(values[0]);
        String email = isNullReturnNullString(values[1]);
        String password = isNullReturnNullString(values[2]);
        String name = isNullReturnNullString(values[3]);
        BigDecimal balance = isNullBigDecimal(values[4]);
        String driverLicense = isNullReturnNullString(values[5]);

        return new Client(id, email, password, name, balance, driverLicense);
    }

    public static Employee csvToEmployee(String[] values) {
        long id = isEmptyLong(values[0]);
        String email = isNullReturnNullString(values[1]);
        String password = isNullReturnNullString(values[2]);
        String name = isNullReturnNullString(values[3]);
        String phone = isNullReturnNullString(values[4]);
        LocalDate dateOfBirth = isEmptyLocalDate(values[5]);
        return new Employee(id, email, password, name, phone, dateOfBirth);
    }

    public static Vehicle csvToVehicle(String[] values) {
        long id = isEmptyLong(values[0]);
        String make = values[1];
        String model = values[2];
        String characteristics = values[3];
        int yearOfProduction = Integer.parseInt(values[4]);
        long odometer = Long.parseLong(values[5]);
        String color = values[6];
        String licensePlate = values[7];
        int numberOfSeats = isNullInteger(values[8]);
        BigDecimal price = isNullBigDecimal(values[9]);
        char requiredLicense = isEmptyChar(values[10]);
        BodyType bodyType = isEmptyEnum(values[11]);

        return new Vehicle(id, make, model, characteristics, yearOfProduction, odometer, color, licensePlate, numberOfSeats, price, requiredLicense, bodyType);
    }

    public static Order csvToOrder(String[] values) {
        long id = Long.parseLong(values[0]);
        long clientId = Long.parseLong(values[1]);
        long employeeId = Long.parseLong(values[2]);
        long vehicleId = Long.parseLong(values[3]);
        LocalDateTime startTime = LocalDateTime.parse(values[4]);
        LocalDateTime endTime = LocalDateTime.parse(values[5]);
        BigDecimal price = new BigDecimal(values[6]);
        return new Order(id, clientId, employeeId, vehicleId, startTime, endTime, price);
    }

    public static <LocalDateTime> String[] orderToCsv(Order order) {

        String[] values = new String[7];
        values[0] = String.valueOf(order.getId());
        values[1] = String.valueOf(order.getClientId());
        values[2] = String.valueOf(order.getEmployeeId());
        values[3] = String.valueOf(order.getVehicleId());
        values[4] = isEmptyLocalDateTimeStart(order);
        values[5] = isEmptyLocalDateTimeEnd(order);
        values[6] = isEmptyBigDecimalWithOrder(order);
        return values;
    }


    public static String[] vehicleToCsv(Vehicle vehicle) {

        String[] values = new String[12];

        values[0] = String.valueOf(vehicle.getId());
        values[1] = vehicle.getMake();
        values[2] = vehicle.getModel();
        values[3] = vehicle.getCharacteristics();
        values[4] = String.valueOf(vehicle.getYearOfProduction());
        values[5] = String.valueOf(vehicle.getOdometer());
        values[6] = vehicle.getColor();
        values[7] = vehicle.getLicensePlate();
        values[8] = String.valueOf(vehicle.getNumberOfSeats());
        values[9] = isNullReturnNull(vehicle.getPrice());
        values[10] = String.valueOf(vehicle.getRequiredLicense());
        values[11] = isNullReturnNull(vehicle.getBodyType());
        return values;
    }

    public static String[] clientToCsv(Client client) {
        String[] values = new String[6];
        values[0] = String.valueOf(client.getId());
        values[1] = client.getEmail();
        values[2] = client.getPassword();
        values[3] = client.getName();
        values[4] = isNullBigDecimalWithClient(client);
        values[5] = client.getDriverLicense();
        return values;
    }

    public static String[] employeeToCsv(Employee employee) {
        String[] values = new String[6];
        values[0] = String.valueOf(employee.getId());
        values[1] = employee.getEmail();
        values[2] = employee.getPassword();
        values[3] = employee.getName();
        values[4] = employee.getPhone();
        values[5] = isNullReturnNull(employee.getDateOfBirth());
        return values;
    }

    public static <T> String isNullReturnNull(T m) {
        if (m == null)
            return null;
        else
            return m.toString();
    }

    public static String isNullReturnNullString(String m) {
        if (m.equals("\"\""))
            return "";
        else if (Objects.equals(m, "")) {
            return null;
        }
        return m;
    }

    public static Long isEmptyLong(String m) {
        if (m == null || m.equals("")) {
            return 0L;
        } else
            return Long.parseLong((String) m);
    }

    public static Integer isNullInteger(String m) {
        if (m == null || m.equals("")) {
            return 0;
        } else {
            return Integer.parseInt((String) m);
        }
    }

    public static BigDecimal isNullBigDecimal(String m) {
        if (m == null || m.equals("")) {
            return null;
        } else {
            return BigDecimal.valueOf(Long.parseLong(String.valueOf(m)));
        }
    }

    public static Character isEmptyChar(String m) {
        if (m == null || m.equals("")) {
            return Character.MIN_VALUE;
        } else {
            return m.charAt(0);
        }
    }

    public static BodyType isEmptyEnum(String m) {
        if (m == null || m.equals("")) {
            return null;
        } else {
            return BodyType.HATCHBACK;
        }
    }

    public static LocalDate isEmptyLocalDate(String m) {
        if (m == null || m.equals("")) {
            return null;
        } else {
            return LocalDate.parse(m);
        }
    }

    public static String isEmptyLocalDateTimeStart(Order r) {
        if (r.getStartTime() == null || r.getStartTime().equals("")) {
            return null;
        } else {
            return r.getStartTime().toString();
        }
    }

    public static String isEmptyLocalDateTimeEnd(Order r) {
        if (r.getEndTime() == null || r.getEndTime().equals("")) {
            return null;
        } else {
            return r.getEndTime().toString();
        }
    }

    public static String isEmptyBigDecimalWithOrder(Order r) {
        if (r.getEndTime() == null || r.getEndTime().equals("")) {
            return null;
        } else {
            return r.getPrice().toString();
        }
    }

    public static String isNullBigDecimalWithClient(Client c) {
        if (c.getBalance() == null || c.getBalance().equals("")) {
            return null;
        } else {
            return c.getBalance().toString();
        }
    }

}
