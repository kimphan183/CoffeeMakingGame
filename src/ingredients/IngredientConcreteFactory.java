package ingredients;

import main.CoffeePanel;

public class IngredientConcreteFactory {
	
	
	public Ingredient createIngredient(String type) {
		Ingredient ingredient = null;
	
		if (type == "milk")
			ingredient = new Milk(CoffeePanel.W_WIDTH / 2 + 150, CoffeePanel.W_HEIGHT / 2 + 120, 0.15);
		else if (type == "whip")
			ingredient = new Whip(CoffeePanel.W_WIDTH / 2 + 290, CoffeePanel.W_HEIGHT / 2 + 105, 0.1);
		else if (type == "cinnamon")
			ingredient = new Cinnamon(CoffeePanel.W_WIDTH / 2 + 400, CoffeePanel.W_HEIGHT / 2 + 150, 0.3);
		
		return ingredient;
	}

}
