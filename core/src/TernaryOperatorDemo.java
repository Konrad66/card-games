public class TernaryOperatorDemo {
    public static void main(String[] args) {
        String fullName;
        String name = "Adam";
        if (name.endsWith("a")) {
            fullName = "Pani " + name;
        } else {
            fullName = "Pan " + name;
        }
        System.out.println(fullName);

        //fullName = waruenk ? wartosc dla prawdy : wartosc dla falszu;
       // return name.endsWith("a") ?  "Pani " + name : "Pan " + name;

    }
}
