package com.example.azs_fx_1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class TopologyDTO implements Serializable, Cloneable {
    @JsonProperty("name")
    public String name;
    @JsonProperty("width")
    public int width;
    @JsonProperty("height")
    public int height;
    @JsonProperty("tanks")
    public FuelTank[] tanks;
    @JsonProperty("azsField")
    public TemplateAZS[] azsField;

    public TopologyDTO(String name, int width, int height, FuelTank[] tanks, TemplateAZS[] azsField) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.tanks = tanks;
        this.azsField = azsField;
    }

    public TopologyDTO(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public TopologyDTO() {
        name = "";
        width = -1;
        height = -1;
        tanks = new FuelTank[0];
        azsField = new TemplateAZS[0];
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("width")
    public int getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(int width) {
        this.width = width;
    }

    @JsonProperty("height")
    public int getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(int height) {
        this.height = height;
    }

    @JsonProperty("tanks")
    public FuelTank[] getTanks() {
        return tanks;
    }

    @JsonProperty("tanks")
    public void setTanks(FuelTank[] tanks) {
        this.tanks = tanks;
    }

    @JsonProperty("azsField")
    public TemplateAZS[] getAzsField() {
        return azsField;
    }

    @JsonProperty("azsField")
    public void setAzsField(TemplateAZS[] azsField) {
        this.azsField = azsField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopologyDTO that = (TopologyDTO) o;
        return width == that.width && height == that.height && Objects.equals(name, that.name) && Arrays.equals(tanks, that.tanks) && Arrays.deepEquals(azsField, that.azsField);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, width, height);
        result = 31 * result + Arrays.hashCode(tanks);
        result = 31 * result + Arrays.deepHashCode(azsField);
        return result;
    }

    @Override
    public String toString() {
        return "TopologyDTO: {" +
                "\"name\": \"" + name + "\"" +
                ", width=" + width +
                ", height=" + height +
                ", tanks=" + Arrays.toString(tanks) +
                ", azsField=" + Arrays.toString(azsField) +
                '}';
    }

    public static class FuelTank {
        public FuelTank(int tankNumber, FuelType fuelType) {
            this.tankNumber = tankNumber;
            this.fuelType = fuelType;
        }

        public FuelTank() {
            tankNumber = -1;
            fuelType = FuelType.AI_92;
        }

        public int tankNumber;
        private FuelType fuelType;
        public int getTankNumber() {
            return tankNumber;
        }
        public void setTankNumber(int tankNumber) {
            this.tankNumber = tankNumber;
        }
        public FuelType getFuelType() {
            return fuelType;
        }

        public void setFuelType(FuelType fuelType) {
            this.fuelType = fuelType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FuelTank fuelTank = (FuelTank) o;
            return tankNumber == fuelTank.tankNumber && fuelType == fuelTank.fuelType;
        }

        @Override
        public int hashCode() {
            return Objects.hash(tankNumber, fuelType);
        }

        @Override
        public String toString() {
            return "FuelTank{" +
                    "tankNumber=" + tankNumber +
                    ", fuelType=" + fuelType +
                    '}';
        }

        public enum FuelType {
            AI_80,
            AI_92,
            AI_95,
            AI_98,
            DT
        }
    }
    public static class TemplateAZS {

        private int x_coordinate;
        private int y_coordinate;
        private Template template;

        /*public static final Image cashbox = com.example.azs_fx_1.TemplateAZS.CASHBOX.getImageView().getImage();
        public static final Image road = com.example.azs_fx_1.TemplateAZS.ROAD.getImageView().getImage();
        public static final Image exit = com.example.azs_fx_1.TemplateAZS.EXIT.getImageView().getImage();
        public static final Image entry = com.example.azs_fx_1.TemplateAZS.ENTRY.getImageView().getImage();
        public static final Image grass = com.example.azs_fx_1.TemplateAZS.GRASS.getImageView().getImage();
        public static final Image fuelStation = com.example.azs_fx_1.TemplateAZS.FUEL_STATION.getImageView().getImage();
        public static final Image highway = com.example.azs_fx_1.TemplateAZS.HIGHWAY.getImageView().getImage();*/
        public TemplateAZS(int x_coordinate, int y_coordinate, Template template) {
            this.x_coordinate = x_coordinate;
            this.y_coordinate = y_coordinate;
            this.template = template;
        }

        public TemplateAZS() {
            x_coordinate = -1;
            y_coordinate = -1;
            template = Template.road;
        }

        public int getX_coordinate() {
            return x_coordinate;
        }

        public void setX_coordinate(int x_coordinate) {
            this.x_coordinate = x_coordinate;
        }

        public int getY_coordinate() {
            return y_coordinate;
        }

        public void setY_coordinate(int y_coordinate) {
            this.y_coordinate = y_coordinate;
        }

        public Template getTemplate() {
            return template;
        }

        public void setTemplate(Template template) {
            this.template = template;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TemplateAZS that = (TemplateAZS) o;
            return template == that.template;
        }

        @Override
        public int hashCode() {
            return Objects.hash(template);
        }

        @Override
        public String toString() {
            return "TemplateAZS{" +
                    "x_coordinate=" + x_coordinate +
                    ", y_coordinate=" + y_coordinate +
                    ", template=" + template +
                    '}';
        }

        public enum Template {
            grass,
            fuel_station,
            entry,
            exit,
            cashbox,
            road,
            highway
        }
    }
}
