package sprava_uzivatelu;

public class Person {
    public String id;
    public String name;
    public String surname;
    public String titleBefore;
    public String titleAfter;
    public String dateOfBirth;
    public String birthNumber;
    public String address;
    public String countryCode;
    public String city;
    public String postalCode;
    public String phoneNumber;
    public String email;
    public boolean ZTP;
    public boolean student;
    public boolean retired;

    public Person(String id, String name, String surname, String titleBefore, String titleAfter, String dateOfBirth,
            String birthNumber, String address, String city, String postalCode, String countryCode, String phoneNumber,
            String email,
            boolean ZTP, boolean student, boolean retired) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.titleBefore = titleBefore;
        this.titleAfter = titleAfter;
        this.dateOfBirth = dateOfBirth;
        this.birthNumber = birthNumber;
        this.address = address;
        this.city = city;
        this.countryCode = countryCode;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.ZTP = ZTP;
        this.student = student;
        this.retired = retired;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTitleBefore() {
        return titleBefore;
    }

    public String getTitleAfter() {
        return titleAfter;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBirthNumber() {
        return birthNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean isZTP() {
        return ZTP;
    }

    public boolean isStudent() {
        return student;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTitleBefore(String titleBefore) {
        this.titleBefore = titleBefore;
    }

    public void setTitleAfter(String titleAfter) {
        this.titleAfter = titleAfter;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBirthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setZTP(boolean ZTP) {
        this.ZTP = ZTP;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", titleBefore='" + titleBefore + '\'' +
                ", titleAfter='" + titleAfter + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", birthNumber=" + birthNumber +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", ZTP=" + ZTP +
                ", student=" + student +
                ", retired=" + retired +
                '}';

    }
}