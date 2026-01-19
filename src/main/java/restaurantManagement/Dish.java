package restaurantManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dish {
    private final int id;
    private final String name;
    private final DishTypeEnum dishType;
    private final List<Ingredient> ingredients;
    private Double price; // Nouvel attribut

    public Dish(int id, String name, DishTypeEnum dishType) {
        this.id = id;
        this.name = name;
        this.dishType = dishType;
        this.ingredients = new ArrayList<>();
        this.price = null;
    }

    public Dish(int id, String name, DishTypeEnum dishType, Double price) {
        this(id, name, dishType);
        this.price = price;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public DishTypeEnum getDishType() { return dishType; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public Double getPrice() { return price; }

    // Setters
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients.clear();
        this.ingredients.addAll(ingredients);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void addIngredient(Ingredient ingredient) {
        if (ingredient != null) this.ingredients.add(ingredient);
    }

    public double getDishCost() {
        return ingredients.stream()
                .mapToDouble(Ingredient::getPrice)
                .sum();
    }

    public Double getGrossMargin() {
        if (price == null) {
            throw new RuntimeException("Impossible de calculer la marge : le prix de vente n'a pas été fixé pour le plat '" + name + "'");
        }
        return price - getDishCost();
    }

    @Override
    public String toString() {
        return String.format("Dish{id=%d, name='%s', dishType=%s, price=%s, ingredients=%d, cost=%.2f, margin=%s}",
                id, name, dishType,
                price != null ? String.format("%.2f", price) : "null",
                ingredients.size(),
                getDishCost(),
                price != null ? String.format("%.2f", getGrossMargin()) : "N/A");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish dish)) return false;
        return id == dish.id &&
                Objects.equals(name, dish.name) &&
                dishType == dish.dishType &&
                Objects.equals(price, dish.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dishType, price);
    }
}