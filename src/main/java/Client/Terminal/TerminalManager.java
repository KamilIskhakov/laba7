package Client.Terminal;

import Client.Main;
import CollectionObjects.Coordinates;
import CollectionObjects.Person;
import Controler.CommandRequestManager;
import Exceptions.NotCorrectException;
import com.github.drapostolos.typeparser.TypeParser;
import com.github.drapostolos.typeparser.TypeParserException;
import CollectionObjects.*;

public class TerminalManager {
    private final CommandRequestManager commandRequestManager;
    private final TerminalInput inputManager;
    private final TerminalOutputManager outputManager;
    private final TypeParser parser;

    public TerminalManager(CommandRequestManager commandRequestManager, TerminalInput inputManager, TerminalOutputManager outputManager) {
        this.commandRequestManager = commandRequestManager;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
        this.parser = TypeParser.newBuilder().build();

    }

    public void start() {
        while (true) {
            try {
                if (Main.script) {
                    if (!inputManager.scriptBox.isEmpty()) {
                        String[] readLine = inputManager.scriptBox.pop();
                        commandRequestManager.preparationForShipment(readLine[0], readLine[1]);
                    } else {
                        Main.script = false;
                    }
                } else {
                    outputManager.printlnWriteCommand();
                    String[] readLine = inputManager.readTerminal();
                    commandRequestManager.preparationForShipment(readLine[0], readLine[1]);
                }
            } catch (NullPointerException e) {
                outputManager.printlnNotCorrectInput();
            }
        }
    }

    public <T> T getAsk(String messageWellDone, Class<T> type) {
        if (Main.script) {
            try {
                if ((inputManager.scriptBox.poll()[1]) != "") {
                    throw new NotCorrectException();
                } else {
                    String arg = inputManager.scriptBox.poll()[0];
                    T parsLine = parser.parse(arg, type);
                    return parsLine;

                }

            } catch (NullPointerException | TypeParserException | NotCorrectException e) {
                Main.script = false;
                outputManager.printlnNotCorrectInput();
            }
        } else {
            try {
                outputManager.println(messageWellDone);
                String[] readLine = inputManager.readTerminal();
                if ((readLine[1]) != "") {
                    throw new NotCorrectException();
                } else {
                    String arg = readLine[0];
                    T parsLine = parser.parse(arg, type);
                    return parsLine;

                }
            } catch (NullPointerException | TypeParserException | NotCorrectException e) {
                outputManager.printlnNotCorrectInput();
                return getAsk(messageWellDone, type);
            }
        }
        return null;
    }

    public Color getColorAsk(String messageWellDone) {
        if (Main.script) {
            try {
                if ((inputManager.scriptBox.poll()[1]) != "") {
                    throw new NotCorrectException();
                } else {
                    String arg = inputManager.scriptBox.poll()[0];
                    Color eyeColor = Color.valueOf(arg.toUpperCase());
                    return eyeColor;
                }
            } catch (NullPointerException | TypeParserException | NotCorrectException e) {
                Main.script = false;
                outputManager.printlnNotCorrectInput();
            }
        } else {
            try {
                outputManager.println(messageWellDone);
                String[] readLine = inputManager.readTerminal();
                if ((readLine[1]) != "") {
                    throw new NotCorrectException();
                } else {
                    String arg = readLine[0];
                    Color eyeColor = Color.valueOf(arg.toUpperCase());
                    return eyeColor;

                }
            } catch (NullPointerException | TypeParserException | NotCorrectException | IllegalArgumentException e) {
                outputManager.printlnNotCorrectInput();
                return getColorAsk(messageWellDone);
            }
        }
        return null;
    }

    public Country getCountryAsk(String messageWellDone) {
        if (Main.script) {
            try {
                if ((inputManager.scriptBox.poll()[1]) != "") {
                    throw new NotCorrectException();
                } else {
                    String arg = inputManager.scriptBox.poll()[0];
                    Country nationality = Country.valueOf(arg.toUpperCase());
                    return nationality;
                }
            } catch (NullPointerException | TypeParserException | NotCorrectException e) {
                Main.script = false;
                outputManager.printlnNotCorrectInput();
            }
        } else {
            try {
                outputManager.println(messageWellDone);
                String[] readLine = inputManager.readTerminal();
                if ((readLine[1]) != "") {
                    throw new NotCorrectException();
                } else {
                    String arg = readLine[0];
                    Country nationality = Country.valueOf(arg.toUpperCase());
                    return nationality;

                }
            } catch (NullPointerException | TypeParserException | NotCorrectException | IllegalArgumentException e) {
                outputManager.printlnNotCorrectInput();
                return getCountryAsk(messageWellDone);
            }
        }
        return null;
    }

    public Person MakeMePerson() {
        return
                new Person.PersonBuilder(getAsk("введите имя:", String.class),
                        new Coordinates.CoordinatesBuilder(getAsk("введите икс", Float.class), getAsk("введите игрик", Float.class)).build(),
                        getAsk("введите вес", Double.class), getCountryAsk("введи страну"))
                        .setColor(getColorAsk("введи цвет"))
                        .setHeight(getAsk("введи рост", Integer.class))
                        .setLocation(new Location.LocationBuilder(getAsk("введи название места", String.class), getAsk("введите икс", Float.class))
                                .setX(getAsk("введите игрик", Integer.class))
                                .setZ(getAsk("введите зет", Double.class)).build()).build();
    }

    public Location MakeMeLocation() {
        return
                new Location.LocationBuilder(getAsk("", String.class), getAsk("", Float.class))
                        .setX(getAsk("", Integer.class))
                        .setZ(getAsk("", Double.class)).build();
    }

}