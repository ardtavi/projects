/*************************************************************************
 * ULLINK CONFIDENTIAL INFORMATION
 * _______________________________
 *
 * All Rights Reserved.
 *
 * NOTICE: This file and its content are the property of Ullink. The
 * information included has been classified as Confidential and may
 * not be copied, modified, distributed, or otherwise disseminated, in
 * whole or part, without the express written permission of Ullink.
 ************************************************************************/
package ro.utcluj.sd.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO,
        generator = "native"
    )
    @GenericGenerator(
        name = "native",
        strategy = "native"
    )
    private int id;

    @Column
    private String year;

    @ManyToMany
    @JoinTable(name = "req_parking_lot", joinColumns = @JoinColumn(name = "req_id"), inverseJoinColumns = @JoinColumn(name = "lot_id"))
    private Set<ParkingLot> parkingLots = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", nullable = true)
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "allocated_spot", nullable = true)
    private ParkingSpace parkingSpace;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Set<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(Set<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }
}
