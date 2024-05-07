package Groupld.Client.RequestFactory;

import Groupld.Controler.Exceptions.NotCorrectException;
import Groupld.Controler.RequestFactoryDTO.ClearRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;

public class ClearRequest implements Request {
    @Override
    public RequestDTO reque(String args) throws NotCorrectException {
        if (args==null){
            return new ClearRequestDTO();
        }else{
            throw new NotCorrectException();
        }
    }
}
