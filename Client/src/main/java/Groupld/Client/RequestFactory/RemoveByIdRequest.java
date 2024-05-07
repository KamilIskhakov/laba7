package Groupld.Client.RequestFactory;

import Groupld.Controler.Exceptions.NotCorrectException;
import Groupld.Controler.RequestFactoryDTO.RemoveByIdRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;

public class RemoveByIdRequest implements Request {
    private Integer id;
    @Override
    public RequestDTO reque(String args) throws NotCorrectException {
        if (args!=null) {
            try {
                id = Integer.parseInt(args);
                return new RemoveByIdRequestDTO(id);
            } catch (Exception e) {
                throw new NotCorrectException();
            }
        } else {
            throw new NotCorrectException();
        }
    }
    public Integer getId() {
        return id;
    }

}
