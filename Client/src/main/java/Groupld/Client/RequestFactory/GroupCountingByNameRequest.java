package Groupld.Client.RequestFactory;

import Groupld.Controler.Exceptions.NotCorrectException;
import Groupld.Controler.RequestFactoryDTO.GroupCountingByNameRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;

public class GroupCountingByNameRequest implements Request {
    public RequestDTO reque(String args) throws NotCorrectException {
        if (args==null){
            return new GroupCountingByNameRequestDTO();
        }else{
            throw new NotCorrectException();
        }
    }
}
