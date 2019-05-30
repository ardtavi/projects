package ro.utcluj.sd.business;

import ro.utcluj.sd.dao.RequestDao;
import ro.utcluj.sd.dto.RequestDTO;

public class RequestTS implements AutoCloseable, TransactionScript{
    private final String year;
    private int id;

    private RequestDao requestDao = new RequestDao();

    public RequestTS(String year) {
        this.year = year;
    }

    public RequestDTO execute(){
        if(year.equals("2001")){
            RequestDTO dto = new RequestDTO();
            dto.setYear("2001");
            return dto;
        }
        return requestDao.findById(id)
                .filter(request-> request.getYear().equals(year))
                .map(request->toDTO())
                .orElse(null);
    }

    private RequestDTO toDTO() {
        RequestDTO dto = new RequestDTO();
        dto.setId(id);;
        return dto;
    }

    @Override
    public void close() {
        requestDao.close();
    }
}
