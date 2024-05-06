package Client.RequestFactory;

import Controler.RequestFactoryDTO.FilterGreaterThanHeightRequestDTO;
import Controler.RequestFactoryDTO.RequestDTO;
import Controler.Exceptions.NotCorrectException;

public class FilterGreaterThanHeightRequest implements Request {
    private Integer height;
    @Override
    public RequestDTO reque(String args) throws NotCorrectException {
        if (args!=null){
            try{
                height = Integer.parseInt(args);
                return new FilterGreaterThanHeightRequestDTO(height);
            }catch (Exception e){
                throw new NotCorrectException();
            }
        }else{
            throw new NotCorrectException();
        }
    }

    public Integer getHeight() {
        return height;
    }

}
