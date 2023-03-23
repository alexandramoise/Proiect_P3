package event;

import java.util.List;

public class Menu {
	List <String> foods;
	enum Cake { chocolate, berries, vanilla }
	Cake cake;
	
	public Menu() {
		//super();
	}
	
	public Menu(List<String> foods, Cake cake) {
		//super();
		this.foods = foods;
		this.cake = cake;
	}

	@Override
	public String toString() {
		return "Menu [foods=" + foods + ", cake=" + cake + "]";
	}
	
}
