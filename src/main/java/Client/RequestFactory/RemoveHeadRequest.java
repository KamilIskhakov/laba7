package Client.RequestFactory;

import Client.RequestFactoryDTO.RemoveHeadRequestDTO;
import Client.RequestFactoryDTO.RequestDTO;
import Controler.Exceptions.NotCorrectException;

public class RemoveHeadRequest implements Request {
    @Override
    public RequestDTO reque(String args) throws NotCorrectException {
        if (args==null){
            return new RemoveHeadRequestDTO();
        }else{
            throw new NotCorrectException();
        }
    }
}
