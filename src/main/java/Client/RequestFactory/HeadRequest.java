package Client.RequestFactory;

import Client.RequestFactoryDTO.HeadRequestDTO;
import Client.RequestFactoryDTO.RequestDTO;
import Controler.Exceptions.NotCorrectException;

public class HeadRequest implements Request {
    @Override
    public RequestDTO reque(String args) throws NotCorrectException {
        if (args==null){
            return new HeadRequestDTO();
        }else{
            throw new NotCorrectException();
        }
    }

}
