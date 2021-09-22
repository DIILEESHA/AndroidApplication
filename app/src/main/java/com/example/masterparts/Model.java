package com.example.masterparts;

    public class Model {
        String id, title, description, brand, enginec, fueluse,  address;

        public Model(String id, String title, String string) {
        }

        public Model(String id,
                     String title,
                     String description,
                     String brand,
                     String enginec,
                     String fueluse,
                     String address) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.brand = brand;
            this.enginec = enginec;
            this.fueluse = fueluse;
            this.address = address;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getEnginec() {
            return enginec;
        }

        public void setEnginec(String enginec) {
            this.enginec = enginec;
        }

        public String getFueluse() {
            return fueluse;
        }

        public void setFueluse(String fueluse) {
            this.fueluse = fueluse;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }


