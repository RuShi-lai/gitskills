package WesterFriendChickenRestaurant;

import java.time.LocalDate;

public class Beer extends Drinks{
    public float getAlcoholDegree() {
        return alcoholDegree;
    }

    public void setAlcoholDegree(float alcoholDegree) {
        this.alcoholDegree = alcoholDegree;
    }

    public float alcoholDegree=8;
    public long qualityDay=30;        //生产日期为30天

    public Beer() {
    }

    public Beer(float alcoholDegree) {
        this.alcoholDegree = alcoholDegree;
    }

    public Beer(String name, double cost, LocalDate produceDate, float alcoholDegree,int num) {
        super(name, cost, produceDate,num);
        this.qualityDay=30;
        this.alcoholDegree = alcoholDegree;
    }

    public String toString() {
        return "Beer{" +
                "alcoholDegree=" + alcoholDegree +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", produceDate=" + produceDate +
                ", qualityDay=" + qualityDay +
                ", num=" + num +
                '}';
    }
}

