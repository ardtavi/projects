package ro.utcluj.view.model.parking;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Request {

    /**
     * The request, name and status fields form a natural identifier (Primary Key)
     */
    private final String request;
    private final String name;
    private final String date;
    private final String    status;

    /**
     * Debatable if these are properties of the Request or not.
     * In a real life application, these properties would be stored in a Request decorator
     * This way, you can reuse the Request entity for a different library instance with different stock and price
     */

    private double          price;

    public Request(String request, String name, String date, String status) {
        this.request = request;
        this.name = name;
        this.date = date;
        this.status = status;
     ;
    }

    public String getRequest() {
        return request;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

  

    public String toString() {
        return "Request [request=" + request + ", name=" + name + ", date="
                + date + ", status=" + status +  "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((request == null) ? 0 : request.hashCode());
        //result = prime * result + status;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Request other = (Request) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        if (request == null) {
            if (other.request != null)
                return false;
        } else if (!request.equals(other.request))
            return false;
        return status == other.status;
    }
}
