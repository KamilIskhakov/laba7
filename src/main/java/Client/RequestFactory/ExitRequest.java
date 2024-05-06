package Client.RequestFactory;

import Client.RequestFactoryDTO.ExitRequestDTO;
import Client.RequestFactoryDTO.RequestDTO;
import Controler.Exceptions.NotCorrectException;

public class ExitRequest implements Request {
    @Override
    public RequestDTO reque(String args) throws NotCorrectException {
        if (args==null){
            return new ExitRequestDTO();
        }else{
            throw new NotCorrectException();
        }
    }

}
