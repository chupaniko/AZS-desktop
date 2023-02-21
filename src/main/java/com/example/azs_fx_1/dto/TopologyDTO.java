package com.example.azs_fx_1.dto;

import java.util.Arrays;
import java.util.Objects;

public class TopologyDTO {
    public String name;
    public int width;
    public int height;
    public FuelTank[] tanks;
    public int[][] azsField;

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

    public class FuelTank {
        public int tankNumber;
        public enum fuelType {
            AI_80,
            AI_92,
            AI_95,
            AI_98,
            DT
        }
    }
    public class TemplateAZS {
        public enum template {
            grass,
            filling_station,
            entry,
            exit,
            cashbox,
            road
        }
        public int x_coordinate;
        public int y_coordinate;
    }
}
