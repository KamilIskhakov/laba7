package Groupld.Client.RequestFactory;

import Groupld.Controler.Exceptions.NotCorrectException;
import Groupld.Controler.RequestFactoryDTO.HeadRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;

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
