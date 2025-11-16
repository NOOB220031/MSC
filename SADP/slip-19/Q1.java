abstract class Pizza {
    public void prepare() {
        System.out.println("Preparing " + this.getClass().getSimpleName());
    }

    public void bake() {
        System.out.println("Baking " + this.getClass().getSimpleName());
    }

    public void cut() {
        System.out.println("Cutting " + this.getClass().getSimpleName());
    }

    public void box() {
        System.out.println("Boxing " + this.getClass().getSimpleName());
    }
}


class NyStyleCheesePizza extends Pizza { }
class ChicagoStyleCheesePizza extends Pizza { }

abstract class PizzaStore {
    public abstract Pizza createPizza(String type);

    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}

class NyPizzaStore extends PizzaStore {
    public Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NyStyleCheesePizza();
        }
        return null;
    }
}

class ChicagoPizzaStore extends PizzaStore {
    public Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        }
        return null;
    }
}

public class Q1 {
    public static void main(String[] args) {
        PizzaStore nyStore = new NyPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        nyStore.orderPizza("cheese");
        chicagoStore.orderPizza("cheese");
    }
}