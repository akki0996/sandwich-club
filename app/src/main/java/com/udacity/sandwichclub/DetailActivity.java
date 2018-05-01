package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);

        if (position == DEFAULT_POSITION) {
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            closeOnError();
            return;
        }

        setTitle(sandwich.getMainName());
        Picasso.with(this).load(sandwich.getImage()).into(ingredientsIv);
        populateUI(sandwich);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView main_name = findViewById(R.id.sandwich_name);
        main_name.append(" " + sandwich.getMainName());

        // Also known as
        TextView also_known_as = findViewById(R.id.also_known_as);
        also_known_as.append(" " + sandwich.getAlsoKnownAs().get(0));

        for(int i = 1; i < sandwich.getAlsoKnownAs().size(); i++)
            also_known_as.append(", " + sandwich.getAlsoKnownAs().get(i));


        // Ingredients
        TextView ingredients = findViewById(R.id.ingredients);
        ingredients.append(" " + sandwich.getIngredients().get(0));

        for(int i = 1; i < sandwich.getIngredients().size(); i++)
            ingredients.append(", " + sandwich.getIngredients().get(i));

        TextView origin = findViewById(R.id.origin);
        origin.append(" " + sandwich.getPlaceOfOrigin());

        TextView description = findViewById(R.id.description);
        description.append(" " + sandwich.getDescription());
    }
}
