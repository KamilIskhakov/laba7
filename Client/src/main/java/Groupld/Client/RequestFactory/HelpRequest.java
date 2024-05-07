package Groupld.Client.RequestFactory;

import Groupld.Controler.Exceptions.NotCorrectException;
import Groupld.Controler.RequestFactoryDTO.HelpRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;

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
