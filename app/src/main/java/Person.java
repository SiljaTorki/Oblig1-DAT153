public class Person {

    private String navn;
    private Integer bilde;

    public Person(String navn, Integer bilde){
        this.navn = navn;
        this.bilde = bilde;

    }


    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Integer getBilde() {
        return bilde;
    }

    public void setBilde(Integer bilde) {
        this.bilde = bilde;
    }


}
