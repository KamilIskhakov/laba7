package Groupld.Client.RequestFactory;

import Groupld.Controler.Exceptions.NotCorrectException;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.RequestFactoryDTO.ShowRequestDTO;

public class ShowRequest implements Request {

    @Override
    public RequestDTO reque(String args) throws  NotCorrectException {
        if (args!=null){
            throw new NotCorrectException();
        }
        return new ShowRequestDTO();
    }

}
