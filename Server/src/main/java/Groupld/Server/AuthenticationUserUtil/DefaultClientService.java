package Groupld.Server.AuthenticationUserUtil;

import Groupld.Server.DAO.ServerRepository;
import Groupld.Server.Server;

public class DefaultClientService implements ClientService {
    private final ServerRepository userRepository; //короче, он статичный в мейнике будет

    @Override
    public void register(String login, String password) {
        if(userRepository.findById(login).isPresent())
            throw new RegistrationException(
                    "Client with id: " + login + " already registered");

        String hash = BCrypt.hashpw(clientSecret, BCrypt.gensalt());
        userRepository.save(new ClientEntity(clientId, hash));
    }

    @Override
    public void checkCredentials(String clientId, String clientSecret) {
        Optional<ClientEntity> optionalUserEntity = userRepository
                .findById(clientId);
        if (optionalUserEntity.isEmpty())
            throw new LoginException(
                    "Client with id: " + clientId + " not found");

        ClientEntity clientEntity = optionalUserEntity.get();

        if (!BCrypt.checkpw(clientSecret, clientEntity.getHash()))
            throw new LoginException("Secret is incorrect");
    }
}
