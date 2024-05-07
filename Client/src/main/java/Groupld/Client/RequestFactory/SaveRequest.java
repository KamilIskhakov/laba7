package Groupld.Client.RequestFactory;

import Groupld.Controler.Exceptions.NotCorrectException;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.RequestFactoryDTO.SaveRequestDTO;

public class SaveRequest implements Request {

    @Override
    public RequestDTO reque(String args) throws NotCorrectException {
        if (args==null){
            return new SaveRequestDTO();
        }else{
            throw new NotCorrectException();
        }

    }

}
