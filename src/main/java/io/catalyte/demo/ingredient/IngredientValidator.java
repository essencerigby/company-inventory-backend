package io.catalyte.demo.ingredient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Formats BigDecimal variable types to be stored with two decimal places
 */
public class IngredientValidator {
    public BigDecimal formatBigDecimal(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Validates the amount of an ingredient.
     *
     * @param amount the ingredient amount to be validated
     * @return an error message for null, empty, zero, or non-numeric values
     */
    public String amountValidation(BigDecimal amount) {
        String error = "";
        if (amount == null) {
            error = "Amount is null. Please add an amount greater than 0.";
        } else if (amount.doubleValue() <= 0) {
            error = "Please add an amount greater than 0.";
        }
        return error;
    }

    /**
     * Validates an ingredient's name.
     *
     * @param name the name to be validated
     * @return a list of error messages if the name is invalid; otherwise, an empty list
     */
    public String nameValidation(String name) {
        String error = "";;
        if (name == null) {
            error = "Name field is null";
        } else if (name.isBlank()) {
            error = "Name field is empty";
        } else if (name.length() >= 50) {
            error = "Please enter an ingredient name shorter than 50 characters";
        }
        return error;
    }

    /**
     * Validates an ingredient's name.
     *
     * @param active the ingredient status to be validated
     * @return an error message if the status is null; only true or false are allowed
     */
    public String activeOrInactiveValidation(Boolean active) {
        String error = "";
        if (active == null) {
            error = "Null value not allowed. Please type 'true' for active OR 'false' for inactive.";
        }
        return error;
    }

    /**
     *
     * @param purchasingCost the cost to validate
     * @return an error message if the cost is null or less than 0
     */
    public String purchasingCostValidation(BigDecimal purchasingCost) {
        String error = "";
        if(purchasingCost == null) {
            error = "The cost is null. Input a valid number";
        } else if (purchasingCost.compareTo(BigDecimal.ZERO) <= 0) {
            error = "The cost must be greater than 0";
        }
        return error;
    }

    /**
     * Validates an ingredient's details.
     *
     * @param ingredient the ingredient to be validated
     * @return an array of error messages for invalid fields; otherwise, an empty array
     */
    public String[] validateIngredient(Ingredient ingredient) {
        List<String> errors = new ArrayList<>();

        if(!nameValidation(ingredient.getName()).isEmpty()) {
            errors.add(nameValidation(ingredient.getName()));
        }
        if(!amountValidation(ingredient.getAmount()).isEmpty()) {
            errors.add(amountValidation(ingredient.getAmount()));
        }
        if(!activeOrInactiveValidation(ingredient.getActive()).isEmpty()) {
            errors.add(activeOrInactiveValidation(ingredient.getActive()));
        }
        if(!purchasingCostValidation(ingredient.getPurchasingCost()).isEmpty()) {
            errors.add(purchasingCostValidation(ingredient.getPurchasingCost()));
        }
        return errors.toArray(new String[0]);
    }
}
