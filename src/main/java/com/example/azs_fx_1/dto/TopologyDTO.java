package com.example.azs_fx_1.dto;

import java.util.Arrays;
import java.util.Objects;

public class TopologyDTO {
    private String name;
    private int width;
    private int height;
    private FuelTank[] tanks;
    private int[][] azsField;

    public TopologyDTO(String name, int width, int height, FuelTank[] tanks, int[][] azsField) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public FuelTank[] getTanks() {
        return tanks;
    }

    public void setTanks(FuelTank[] tanks) {
        this.tanks = tanks;
    }

    public int[][] getAzsField() {
        return azsField;
    }

    public void setAzsField(int[][] azsField) {
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

    public static class FuelTank {
        public FuelTank(int tankNumber, FuelType fuelType) {
            this.tankNumber = tankNumber;
            this.fuelType = fuelType;
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
        public TemplateAZS(int x_coordinate, int y_coordinate, Template template) {
            this.x_coordinate = x_coordinate;
            this.y_coordinate = y_coordinate;
            this.template = template;
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

        public enum Template {
            grass,
            filling_station,
            entry,
            exit,
            cashbox,
            road
        }
    }
}
