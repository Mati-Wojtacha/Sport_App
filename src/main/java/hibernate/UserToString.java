package hibernate;
public class UserToString {
    int number;
    String name;
    String nawisko;

    public UserToString(int number, String name, String nazwisko) {
        this.number = number;
        this.name = name;
        this.nawisko = nazwisko;
    }

    @Override
    public String toString() {
        return number + "  " + name + "  " + nawisko ;
    }
}