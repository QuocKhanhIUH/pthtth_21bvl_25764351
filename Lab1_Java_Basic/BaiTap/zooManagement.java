package BaiTap;

public class zooManagement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Animal[] animals = {
	            new Lion("Leo", 300, 5),
	            new Snake("Boa", 50, 5),
	            new Monkey("George", 150, "chuối")
	        };

	        for (Animal animal : animals) {
	            animal.displayInfo();
	        }
	    }

	}
	abstract class Animal {
	    protected final String name;
	    protected final double weight;

	    protected Animal(String name, double weight) {
	        this.name = name;
	        this.weight = weight;
	    }

	    public abstract void displayInfo();
	}

	class Lion extends Animal {
	    private final double eat;

	    public Lion(String name, double weight, double eat) {
	        super(name, weight);
	        this.eat = eat;
	    }

	    @Override
	    public void displayInfo() {
	        System.out.println("Sư tử " + name + " nặng " + weight
	                + " kg và ăn " + eat + " kg thịt mỗi ngày.");
	    }
	}

	class Snake extends Animal {
	    private final double length;

	    public Snake(String name, double weight, double length) {
	        super(name, weight);
	        this.length = length;
	    }

	    @Override
	    public void displayInfo() {
	        System.out.println("Con rắn " + name + " nặng " + weight
	                + " kg và dài " + length + " mét.");
	    }
	}

	class Monkey extends Animal {
	    private final String favoriteFood;

	    public Monkey(String name, double weight, String favoriteFood) {
	        super(name, weight);
	        this.favoriteFood = favoriteFood;
	    }

	    @Override
	    public void displayInfo() {
	        System.out.println("Con khỉ " + name + " nặng " + weight
	                + " kg và thích ăn " + favoriteFood + ".");
	    }
	}


