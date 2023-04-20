package Survivor;

import java.util.ArrayList;
import java.util.List;

class Shop {
    private static Shop shop;

    public static Shop getInstance() {
        if (shop == null) {
            shop = new Shop();
        }
        return shop;
    }

    private final List<Object> workers = new ArrayList<>();

    public void addWorker(Object worker) {
        this.workers.add(worker);
    }

    public double getTurnover() {
        double turnOver = 0.0;
        for (Object worker : this.workers) {
            if (worker instanceof  SalesConsultant) {
                turnOver += (( SalesConsultant)worker).getMoney();
            }
            if (worker instanceof MarketingSpecialist) {
                turnOver += ((MarketingSpecialist) worker).getBudget();
            }
        }

        return turnOver;
    }
}

class SalesConsultant {

    private double earnedMoney;

    public  SalesConsultant() {
        Shop workingShop = Shop.getInstance();
        workingShop.addWorker(earnedMoney);
    }

    public void sellProduct(double price) {
        this.earnedMoney += price;
    }

    public double getMoney() {
        return this.earnedMoney;
    }
}

class MarketingSpecialist {
    private double budget = 5000.00;

    public MarketingSpecialist() {
        Shop workingShop = Shop.getInstance();
        workingShop.addWorker(workingShop);
    }

    public void spendMoney(double marketingCampaignCost) {
        this.budget -= marketingCampaignCost;
    }

    public double getBudget() {
        return this.budget;
    }
}
