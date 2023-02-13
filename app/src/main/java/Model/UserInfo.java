package Model;

public class UserInfo {
    int ID;
    String Address;
    String Mail;
    String Phone;
    String Name;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public UserInfo() {
        this.ID = ID;
        this.Address = Address;
        this.Mail = Mail;
        this.Phone = Phone;
        this.Name = Name;
    }

}
