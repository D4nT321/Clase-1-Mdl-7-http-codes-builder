// Define el paquete donde se encuentra esta clase.
// Sirve para organizar el proyecto y evitar conflictos de nombres.
package com.dante.httpcodes.model;

// Declaración de la clase House.
// Representa una casa como un objeto en Java.
public class House {

    // Variable privada que almacena el color de la casa.
    // Solo se puede acceder a ella mediante métodos (getters/setters).
    private String color;

    // Variable privada que almacena el número de ventanas de la casa.
    private Integer numWindows;

    // Variable privada que almacena la dirección de la casa.
    private String address;

    // Variable privada que indica si la casa tiene garaje o no.
    private Boolean haveGarage;

    // Método estático que devuelve un objeto HouseBuilder.
    // Sirve para iniciar la construcción de un objeto House usando el patrón Builder.
    public static HouseBuilder builder() {
        return new HouseBuilder();
    }

    // Constructor de la clase House.
    // Se ejecuta cuando se crea un objeto House con todos sus atributos.
    public House(String color, Integer numWindows, String address, Boolean haveGarage) {

        // Asigna el valor recibido al atributo color de la clase.
        this.color = color;

        // Asigna el número de ventanas recibido al atributo numWindows.
        this.numWindows = numWindows;

        // Asigna la dirección recibida al atributo address.
        this.address = address;

        // Asigna el valor recibido al atributo haveGarage.
        this.haveGarage = haveGarage;
    }

    // ---------------- GETTERS Y SETTERS ----------------

    // Devuelve el color de la casa.
    public String getColor() {
        return color;
    }

    // Modifica el color de la casa.
    public void setColor(String color) {
        this.color = color;
    }

    // Devuelve el número de ventanas de la casa.
    public Integer getNumWindows() {
        return numWindows;
    }

    // Modifica el número de ventanas de la casa.
    public void setNumWindows(Integer numWindows) {
        this.numWindows = numWindows;
    }

    // Devuelve la dirección de la casa.
    public String getAddress() {
        return address;
    }

    // Modifica la dirección de la casa.
    public void setAddress(String address) {
        this.address = address;
    }

    // Devuelve si la casa tiene garaje o no.
    public Boolean getHaveGarage() {
        return haveGarage;
    }

    // Modifica si la casa tiene garaje o no.
    public void setHaveGarage(Boolean haveGarage) {
        this.haveGarage = haveGarage;
    }

    // ---------------- BUILDER ----------------

    // Clase estática interna HouseBuilder.
    // Se usa para construir objetos House paso a paso.
    public static class HouseBuilder {

        // Atributo para almacenar temporalmente el color.
        private String color;

        // Atributo para almacenar temporalmente el número de ventanas.
        private Integer numWindows;

        // Atributo para almacenar temporalmente la dirección.
        private String address;

        // Atributo para almacenar si tiene garaje.
        // Por defecto se inicializa en FALSE.
        private Boolean haveGarage = Boolean.FALSE;

        // Método para asignar el color.
        // Devuelve el mismo objeto HouseBuilder para permitir encadenar métodos.
        public HouseBuilder color(String color) {
            this.color = color;
            return this;
        }

        // Método para asignar el número de ventanas.
        // Devuelve el mismo objeto HouseBuilder.
        public HouseBuilder numWindows(Integer numWindows) {
            this.numWindows = numWindows;
            return this;
        }

        // Método para asignar la dirección.
        // Devuelve el mismo objeto HouseBuilder.
        public HouseBuilder address(String address) {
            this.address = address;
            return this;
        }

        // Método para indicar si la casa tiene garaje o no.
        // Devuelve el mismo objeto HouseBuilder.
        public HouseBuilder haveGarage(Boolean haveGaragecolor) {
            this.haveGarage = haveGaragecolor;
            return this;
        }

        // Método build().
        // Crea y devuelve un objeto House usando los valores configurados.
        public House build() {
            return new House(color, numWindows, address, haveGarage);
        }
    }
}
