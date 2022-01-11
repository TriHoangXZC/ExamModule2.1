package model;

public class Contact {
    private String phoneNumber;
    private String name;
    private String gender;
    private String address;
    private String facebook;
    private String email;

    public Contact() {
    }

    public Contact(String phoneNumber,String name, String gender, String address, String dateOfBirth, String email) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.facebook = dateOfBirth;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + facebook + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

