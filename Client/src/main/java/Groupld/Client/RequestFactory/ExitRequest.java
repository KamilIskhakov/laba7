package Groupld.Client.RequestFactory;

import Groupld.Controler.Exceptions.NotCorrectException;
import Groupld.Controler.RequestFactoryDTO.ExitRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;

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
