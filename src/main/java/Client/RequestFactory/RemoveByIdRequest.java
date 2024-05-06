package Client.RequestFactory;

import Client.RequestFactoryDTO.RemoveByIdRequestDTO;
import Client.RequestFactoryDTO.RequestDTO;
import Controler.Exceptions.NotCorrectException;

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
