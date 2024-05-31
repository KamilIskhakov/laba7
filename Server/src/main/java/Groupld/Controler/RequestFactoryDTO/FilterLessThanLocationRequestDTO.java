package Groupld.Controler.RequestFactoryDTO;

import Groupld.Controler.CollectionObjects.Location;

public class FilterLessThanLocationRequestDTO implements RequestDTO {
    private Location location;
    private String token;
    private String name;
    public FilterLessThanLocationRequestDTO(Location location){
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

}
