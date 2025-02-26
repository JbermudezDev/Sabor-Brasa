package com.example.demo.entidad;



public class User {
    
    private String name;
    private String userName;
    private String correo;
    private String edad;

    public User(String name, String userName, String correo, String edad) {
        this.name = name;
        this.userName = userName;
        this.correo = correo;
        this.edad = edad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    


}
