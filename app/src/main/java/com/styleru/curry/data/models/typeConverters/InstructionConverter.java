package com.styleru.curry.data.models.typeConverters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.styleru.curry.presentation.view.recipe.models.Instruction;

import java.util.ArrayList;
import java.util.List;

public class InstructionConverter {

    @TypeConverter
    public String fromInstruction(ArrayList<Instruction> instructionList) {
        return new Gson().toJson(instructionList);
    }

    @TypeConverter
    public ArrayList<Instruction> toInstruction(String json) {
        return new Gson().fromJson(json, new TypeToken<ArrayList<Instruction>>() {}.getType());
    }
}
