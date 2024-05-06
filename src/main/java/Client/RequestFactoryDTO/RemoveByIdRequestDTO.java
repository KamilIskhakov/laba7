package Client.RequestFactoryDTO;

public class RemoveByIdRequestDTO implements RequestDTO {
    private Integer id;
    public RemoveByIdRequestDTO(Integer id){
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

}
