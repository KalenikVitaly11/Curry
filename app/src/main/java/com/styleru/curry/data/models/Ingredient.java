package com.styleru.curry.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.styleru.curry.data.models.typeConverters.IngredientConverter;

@Entity
public class Ingredient implements Parcelable {

    private int id;
    private String name;
    private float amount;
    private String unit;

    private Ingredient(Parcel in) {
        id = in.readInt();
        name = in.readString();
        amount = in.readInt();
        unit = in.readString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getAmount() {
        return amount;
    }

    public String getUnitShort() {
        return unit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setUnitShort(String unitShort) {
        this.unit = unitShort;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeFloat(amount);
        dest.writeString(unit);
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

}
