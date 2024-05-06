package Client.RequestFactory;

import Controler.RequestFactoryDTO.HelpRequestDTO;
import Controler.RequestFactoryDTO.RequestDTO;
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
