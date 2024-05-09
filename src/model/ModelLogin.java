
package model;


public class ModelLogin {
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public ModelLogin() {
    }

    public ModelLogin(String Email, String Password) {
        this.Email = Email;
        this.Password = Password;
    }
    
    
    private String Email;
    private String Password;
}
