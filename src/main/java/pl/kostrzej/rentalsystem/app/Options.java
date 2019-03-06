package pl.kostrzej.rentalsystem.app;

public enum Options {
    ADD_DEVICE(1, "Dodaj urządzenie"),
    ADD_CATEGORY(2, "Dodaj kategorię"),
    ADD_CLIENT(3, "Dodaj klienta"),
    RENT_DEVICE(4, "Wypożycz urządzenie"),
    DELETE_DEVICE(5, "usuń urządzenie"),
    DELETE_CATEGORY(6, "Usuń kategorię"),
    DELETE_CLIENT(7, "Usuń klienta"),
    EXIT(8, "Wyjście");

    private int number;
    private String name;

    Options(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static Options chooseOption(int number) {
        if (number < 0 || number > Options.values().length)
            throw new InvalidOptionException();
        else
            return Options.values()[number - 1];
    }

    @Override
    public String toString() {
        return number + ". " + name;
    }
}
