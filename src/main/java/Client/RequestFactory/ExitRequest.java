package Client.RequestFactory;

import Controler.RequestFactoryDTO.ExitRequestDTO;
import Controler.RequestFactoryDTO.RequestDTO;
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
