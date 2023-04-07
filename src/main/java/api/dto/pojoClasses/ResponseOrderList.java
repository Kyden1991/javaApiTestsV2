package api.dto.pojoClasses;

import java.util.List;

public class ResponseOrderList {
    private List<ResponseOrderElements> orders;
    private PageInfo pageInfo;
    private List<AvailableStation> availableStation;

    public List<ResponseOrderElements> getOrders() {
        return orders;
    }

    public void setOrders(List<ResponseOrderElements> orders) {
        this.orders = orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<AvailableStation> getAvailableStation() {
        return availableStation;
    }

    public void setAvailableStation(List<AvailableStation> availableStation) {
        this.availableStation = availableStation;
    }
}

