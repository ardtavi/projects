package ro.utcluj.sd.dto;

public class RequestDTO implements Dto {
    private int id;
    private String year;

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

    @Override
    public String toString() {
        return "Request: " + id ;
    }
}
