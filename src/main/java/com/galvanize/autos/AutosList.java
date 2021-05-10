package com.galvanize.autos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AutosList {
    private List<Auto> autos;

    public AutosList() {
        this.autos = new ArrayList<>();
    }

    public AutosList(List<Auto> autos) {
        this.autos = autos;
    }

    public List<Auto> getAutos() {
        return autos;
    }

    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }

    public String toString() {
        return "AutosList{" +
                "autos=" + autos +
                "}";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutosList autosList = (AutosList) o;
        return Objects.equals(autos, autosList.autos);
    }

    public int hashCode() {
        return Objects.hash(autos);
    }
}
