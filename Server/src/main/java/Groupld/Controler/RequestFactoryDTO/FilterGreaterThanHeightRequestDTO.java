package Groupld.Controler.RequestFactoryDTO;


public class FilterGreaterThanHeightRequestDTO implements RequestDTO {
    private Integer height;
    public FilterGreaterThanHeightRequestDTO(Integer height){
        this.height = height;
    }

    public Integer getHeight() {
        return height;
    }

}
