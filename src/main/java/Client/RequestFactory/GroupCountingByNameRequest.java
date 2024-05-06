package Client.RequestFactory;

import Client.RequestFactoryDTO.GroupCountingByNameRequestDTO;
import Client.RequestFactoryDTO.RequestDTO;
import Controler.Exceptions.NotCorrectException;

public class GroupCountingByNameRequest implements Request {
    public RequestDTO reque(String args) throws NotCorrectException {
        if (args==null){
            return new GroupCountingByNameRequestDTO();
        }else{
            throw new NotCorrectException();
        }
    }
}
