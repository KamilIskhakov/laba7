package Groupld.Client.RequestFactory;

import Groupld.Controler.Exceptions.NotCorrectException;
import Groupld.Controler.RequestFactoryDTO.InfoRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;

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
