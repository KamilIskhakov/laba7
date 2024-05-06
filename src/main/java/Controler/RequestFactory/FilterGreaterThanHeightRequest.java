package Controler.RequestFactory;

import Controler.Exceptions.NotCorrectException;

public class FilterGreaterThanHeightRequest implements Request {
    private Integer height;
    @Override
    public void reque(String args) throws NotCorrectException {
        if (args!=null){
            try{
                height = Integer.parseInt(args);
            }catch (Exception e){
                throw new NotCorrectException();
            }
        }else{
            throw new NotCorrectException();
        }
    }

    public Integer getHeight() {
        return height;
    }

}
