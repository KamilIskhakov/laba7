package Client.RequestFactory;

import Controler.RequestFactoryDTO.RequestDTO;
import Controler.RequestFactoryDTO.ShowRequestDTO;
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
