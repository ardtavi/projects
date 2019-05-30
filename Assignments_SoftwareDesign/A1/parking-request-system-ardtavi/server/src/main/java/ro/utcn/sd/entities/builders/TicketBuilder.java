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
package ro.utcn.sd.entities.builders;

import ro.utcn.sd.entities.Ticket;

import java.util.Objects;

/**
 * Builder Pattern Example
 */
public class TicketBuilder {

    private Ticket underConstruction;

    private TicketBuilder() {
        underConstruction = new Ticket();
    }

    public static TicketBuilder createTicketBuilder() {
        return new TicketBuilder();
    }

    public TicketBuilder name(String name) {
        underConstruction.setName(name);
        return this;
    }

    public TicketBuilder price(double price) {
        underConstruction.setPrice(price);
        return this;
    }

    public Ticket build() {
        checkValid(underConstruction);
        return underConstruction;
    }

    private void checkValid(Ticket underConstruction) {
        Objects.requireNonNull(underConstruction.getName());
        Objects.requireNonNull(underConstruction.getPrice());
    }
}
