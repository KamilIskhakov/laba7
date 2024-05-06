package Client.RequestFactory;

import Controler.RequestFactoryDTO.InfoRequestDTO;
import Controler.RequestFactoryDTO.RequestDTO;
import Controler.Exceptions.NotCorrectException;

public class InfoRequest implements Request {
    @Override
    public RequestDTO reque(String args) throws NotCorrectException {
        if (args==null){
            return new InfoRequestDTO();
        }else{
            throw new NotCorrectException();
        }
    }

}
