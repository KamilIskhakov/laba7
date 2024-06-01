package Groupld.Controler.RequestFactoryDTO;


public class FilterGreaterThanHeightRequestDTO implements RequestDTO {
    private String token;
    private Integer height;
    private String name;
    public FilterGreaterThanHeightRequestDTO(Integer height){
        this.height = height;
    }
    public Integer getHeight() {
        return height;
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
