package Groupld.Client.RequestFactory;

import Groupld.Controler.Exceptions.NotCorrectException;
import Groupld.Controler.RequestFactoryDTO.RemoveHeadRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;

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
