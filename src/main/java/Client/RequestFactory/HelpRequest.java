package Client.RequestFactory;

import Client.RequestFactoryDTO.HelpRequestDTO;
import Client.RequestFactoryDTO.RequestDTO;
import Controler.Exceptions.NotCorrectException;

public class HelpRequest implements Request {
    @Override
    public RequestDTO reque(String args) throws  NotCorrectException {
        if (args==null){
            return new HelpRequestDTO();
        }else{
            throw new NotCorrectException();
        }
    }
}
