package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();

        JSONObject json_object;
        JSONObject child_json_object;

        try {
            json_object = new JSONObject(json);
            child_json_object = new JSONObject(json_object.getString("name"));

            sandwich.setMainName(child_json_object.getString("mainName"));
            sandwich.setAlsoKnownAs(string_list_conversion(child_json_object.getString("alsoKnownAs")));

            sandwich.setPlaceOfOrigin(json_object.getString("placeOfOrigin"));
            sandwich.setDescription(json_object.getString("description"));
            sandwich.setImage(json_object.getString("image"));
            sandwich.setIngredients(string_list_conversion(json_object.getString("ingredients")));
        } catch (Exception e) {

        }

        return sandwich;
    }

    private static List<String> string_list_conversion(String list_string) {
        list_string = list_string.replace("[", "").replace("]", "");
        return new ArrayList<>(Arrays.asList(list_string.split(" ")));
    }
}