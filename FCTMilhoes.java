import java.util.Arrays;

public class FCTMilhoes {
    public static final double LEVEL_REWARDS[] = new double[]{43.2, 4.15, 1.92, 1.45, 1.48, 1.67, 1.38, 1.75, 2.85, 4.1, 4.95, 13.85, 17.25};
    private Key keys;
    private Bet[] bets;
    private int counter;
    private int maxBets;
    private double prize;

    public FCTMilhoes() {
        maxBets = 100;
        keys = new Key();
        bets = new Bet[maxBets];
        counter = 0;
        this.prize = 0;
    }

    public void newGame(double prize) {
        this.prize += prize;
    }

    private int processNumbers(int[] numbers) {
        IteratorInt itera;
        int numberHits = 0;
        for (int i = 0; i < 5; i++) {
            if (numbers[i] > 50 || numbers[i] < 1)
                return -1;
        }
        if (isThereDuplicateNumber(numbers))
            return -1;
        for (int j = 0; j < 5; j++) {
            itera = keys.criaIteratorNumbers();
            while (itera.hasNextInt()) {
                if (numbers[j] == itera.nextInt())
                    numberHits++;
            }
        }
        return numberHits;
    }

    private int processStars(int[] stars) {
        IteratorInt itera;
        int starHits = 0;
        for (int i = 0; i < 2; i++) {
            if (stars[i] > 12 || stars[i] < 1)
                return -1;
        }
        if (stars[0] == stars[1])
            return -1;
        for (int i = 0; i < 2; i++) {
            itera = keys.criaIteratorStars();
            while (itera.hasNextInt()) {
                if (stars[i] == itera.nextInt())
                    starHits++;
            }
        }
        return starHits;
    }

    public int bet(int[] numbers) {
        int numberHits;
        int starHits = 0;
        numberHits = processNumbers(numbers);
        if(numberHits==-1)
            return -1;
        starHits = processStars(Arrays.copyOfRange(numbers, 5, 7));
        if(starHits==-1)
            return -1;
        assignReward(numberHits, starHits);
        return bets[counter - 1].getRewardLevel();
    }

    public int getBetsByLevel(int level) {
        int auxCounter = 0;
        for (int i = 0; i < counter; i++) {
            if (bets[i].getRewardLevel() == level)
                auxCounter++;
        }
        return auxCounter;
    }

    public double getPrize() {
        return prize;
    }

    private double getFinalPrizePercentage() {
        double totalPercentage = 100;
        boolean found;
        for (int i = 1; i < 14; i++) {
            found = false;
            for (int j = 0; j < this.counter && !found; j++) {
                if (bets[j].getRewardLevel() == i)
                    found = true;
            }
            if (found)
                totalPercentage -= LEVEL_REWARDS[i - 1];
        }
        return totalPercentage;
    }

    public void finish() {
        prize = prize * (getFinalPrizePercentage() / 100);
        maxBets = 100;
        keys = new Key();
        bets = new Bet[maxBets];
        counter = 0;
    }

    private void assignReward(int numberHits, int starHits) {
        Bet bet;
        int level = 0;
        if (numberHits == 1 && starHits == 2)
            level = 11;
        else if (numberHits == 2) {
            if (starHits == 0)
                level = 13;
            else if (starHits == 1)
                level = 12;
            else
                level = 8;
        } else if (numberHits == 3) {
            if (starHits == 0)
                level = 10;
            else if (starHits == 1)
                level = 9;
            else
                level = 6;
        } else if (numberHits == 4) {
            if (starHits == 0)
                level = 7;
            else if (starHits == 1)
                level = 5;
            else
                level = 4;
        } else if (numberHits == 5) {
            if (starHits == 0)
                level = 3;
            else if (starHits == 1)
                level = 2;
            else
                level = 1;
        }
        bet = new Bet(level);
        if (counter == maxBets) {
            Bet[] auxBets = new Bet[maxBets * 2];
            for (int i = 0; i < maxBets; i++)
                auxBets[i] = bets[i];
            maxBets *= 2;
            bets = auxBets;
        }
        bets[counter++] = bet;
    }

    private boolean isThereDuplicateNumber(int[] numbers) {
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (numbers[i] == numbers[j])
                    return true;
            }
        }
        return false;
    }

}
