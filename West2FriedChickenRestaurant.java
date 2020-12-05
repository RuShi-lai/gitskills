package WesterFriendChickenRestaurant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class West2FriedChickenRestaurant implements FriedChickenRestaurant {
    public static double accountBalance = 0;             //账户余额
    public static ArrayList<Beer> beers = new ArrayList<Beer>();             //啤酒列表
    public static ArrayList<Juice> juices = new ArrayList<Juice>();           //果汁列表
    public static SetMeal beerMeal = new SetMeal("炸鸡+啤酒", 60, "椒盐炸鸡", "beer");
    public static SetMeal juiceMeal = new SetMeal("炸鸡+橙汁", 55, "椒盐炸鸡", "juice");
    public static void main(String[] args) throws IngredientSortOutException, OverdraftBalanceException {
        Beer beer = new Beer();
        Juice juice = new Juice();
        System.out.println("请输入初始资金:");
        Scanner sc = new Scanner(System.in);
        accountBalance = sc.nextInt();
        FriedChickenRestaurant restaurant=new West2FriedChickenRestaurant();
        for (; ; ) {
            System.out.println("请输入:1--购买套餐,2--批量进货,3--查询啤酒余量,4--查询橙汁余量,5--查询资金");
            int i = sc.nextInt();
            if (i == 1) {
                System.out.println("请问你要买什么套餐?1:椒盐炸鸡+啤酒,2:椒盐炸鸡+橙汁");
                int x = sc.nextInt();
                if (x == 1) {
                    restaurant.sellMeal(beerMeal);
                    accountBalance+=60;
                } else if (i == 2) {
                    restaurant.sellMeal(juiceMeal);
                    accountBalance+=55;
                }
            }else if(i==2){
                System.out.println("请问你要进什么货?1--啤酒,2--果汁");
                int x = sc.nextInt();
                System.out.println("请输入您要购买得数量");
                int y=sc.nextInt();
                if(x==1){
                    Drinks drinks=new Beer();
                    drinks.name="beer";
                    drinks.cost=10;            //假设一瓶啤酒进价10元
                    drinks.produceDate=LocalDate.now();  //把当前日期定为生产日期
                    System.out.println(drinks.produceDate);
                    drinks.num=y;               //购买得数量
                    accountBalance-=drinks.cost*y;
                    if (accountBalance<0){
                        throw new OverdraftBalanceException("账上钱不够了");
                    }
                    restaurant.bulkPurchase(drinks);
                }else if(x==2){
                    Drinks drinks=new Juice();
                    drinks.name="juice";
                    drinks.cost=8;            //假设一瓶果汁进价8元
                    drinks.produceDate=LocalDate.now();  //把当前日期定为生产日期
                    drinks.num=y;               //购买得数量
                    accountBalance-=drinks.cost*y;
                    if (accountBalance<0){
                        throw new OverdraftBalanceException("账上钱不够了");
                    }
                    restaurant.bulkPurchase(drinks);
                }
            }else if(i==3){
                if(beers.isEmpty()){
                    System.out.println("空空如也,先去买点啤酒再来查看吧");
                }
                for (Beer beer1 : beers) {
                    System.out.println(beer1);
                }
            }else if(i==4){
                if(juices.isEmpty()){
                    System.out.println("空空如也,先去买点果汁再来查看吧");
                }
                for (Juice juice1 : juices) {
                    System.out.println(juice1);
                }
            }else if(i==5){
                System.out.println("资金为:"+accountBalance);
            }
        }
    }




    static {
        ArrayList<SetMeal> meals = new ArrayList<SetMeal>();
        meals.add(beerMeal);
        meals.add(juiceMeal);
    }

    private static void use(Beer beer) throws IngredientSortOutException {
        int sum = 0;
        if(beers!=null) {
            for (Beer beer1 : beers) {
                sum+=beer1.num;
            }
        }
        if (sum <=0||beers==null) {
            throw new IngredientSortOutException("啤酒已经卖完了,程序结束");
        }
        if(beers!=null) {
            for (Beer beer1 : beers) {
                if (beer1.isOutOfDate() || beer1.num <= 0) {
                    beers.remove(beer1);
                } else if (beer1.num > 0) {
                    beer1.num--;
                }
            }
        }

    }

    private static void use(Juice juice) throws IngredientSortOutException {
        int sum = 0;
        if(juices!=null) {
            for (Juice juice1 : juices) {
                sum += juice1.num;
            }
        }

        if (sum <= 0||juices==null) {
            throw new IngredientSortOutException("果汁已经卖完了,程序结束");
        }
        if(juices!=null) {
            for (Juice juice1 : juices) {
                if (juice1.isOutOfDate() || juice1.num <= 0) {
                    juices.remove(juice1);
                } else {
                    sum += juice1.num;
                    juice1.num--;
                }
            }
        }

    }

    public void sellMeal(SetMeal meal) throws IngredientSortOutException {
        if (meal.drinkName.equals("beer")) {
            use(new Beer());
        } else {
            use(new Juice());
        }
    }

    public void bulkPurchase(Drinks drink) {
        if (drink instanceof Beer) {
            beers.add((Beer) drink);
        } else {
            juices.add((Juice) drink);
        }
    }

}
