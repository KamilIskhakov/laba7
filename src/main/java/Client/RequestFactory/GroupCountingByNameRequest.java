package Client.RequestFactory;

import Controler.RequestFactoryDTO.GroupCountingByNameRequestDTO;
import Controler.RequestFactoryDTO.RequestDTO;
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
