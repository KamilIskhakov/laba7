package Controler.RequestFactory;

import Controler.Exceptions.NotCorrectException;

public class RemoveByIdRequest implements Request {
    private Integer id;
    @Override
    public void reque(String args) throws NotCorrectException {
        if (args!=null) {
            try {
                id = Integer.parseInt(args);
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
