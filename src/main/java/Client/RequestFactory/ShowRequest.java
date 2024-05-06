package Client.RequestFactory;

import Client.RequestFactoryDTO.RequestDTO;
import Client.RequestFactoryDTO.ShowRequestDTO;
import Controler.Exceptions.NotCorrectException;

public class ShowRequest implements Request {

    @Override
    public RequestDTO reque(String args) throws  NotCorrectException {
        if (args!=null){
            throw new NotCorrectException();
        }
        return new ShowRequestDTO();
    }

}
