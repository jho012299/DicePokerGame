package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;
import java.util.ArrayList;

public class Main extends Application {

    private int overallScore = 0;
    private int currentScore = 0;
    private int rollCounter = 0;
    private int curImage1 = 0, curImage2 = 0, curImage3 = 0, curImage4 = 0, curImage5 = 0;
    private boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false;
    private boolean reset;
    private Label overallLabel, currentLabel, remainLabel;
    private VBox vbox;
    private Button rollButton;
    ArrayList<Dice> diceList = new ArrayList<Dice>();

    private class Dice { //Dice class holds value and imageView
        int num;
        ImageView diceDisplay;

        Dice(int num, ImageView diceDisplay) {
            this.num = num;
            this.diceDisplay = diceDisplay;
        }

        public int roll() {
            Random rand = new Random();
            int randomNum = rand.nextInt(6) + 1;
            return randomNum;
        }
    }

    private Dice dice1, dice2, dice3, dice4, dice5;
    private Image image1, image2, image3, image4, image5, image6, held1, held2, held3, held4, held5, held6;
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5;

    public void start(Stage primaryStage) throws Exception {
        overallLabel = new Label("Overall Score: 0");
        currentLabel = new Label("Your Score: 0");
        remainLabel = new Label("Rolls Remaining: 3");
        rollButton = new Button("Roll Dice");

        image1 = new Image("file:./src/Dice1.png");
        image2 = new Image("file:./src/Dice2.png");
        image3 = new Image("file:./src/Dice3.png");
        image4 = new Image("file:./src/Dice4.png");
        image5 = new Image("file:./src/Dice5.png");
        image6 = new Image("file:./src/Dice6.png");

        held1 = new Image("file:./src/Dice1Held.png");
        held2 = new Image("file:./src/Dice2Held.png");
        held3 = new Image("file:./src/Dice3Held.png");
        held4 = new Image("file:./src/Dice4Held.png");
        held5 = new Image("file:./src/Dice5Held.png");
        held6 = new Image("file:./src/Dice6Held.png");

        imageView1 = new ImageView(image1);
        imageView2 = new ImageView(image2);
        imageView3 = new ImageView(image3);
        imageView4 = new ImageView(image4);
        imageView5 = new ImageView(image5);

        dice1 = new Dice(1, imageView1);
        dice2 = new Dice(2, imageView2);
        dice3 = new Dice(3, imageView3);
        dice4 = new Dice(4, imageView4);
        dice5 = new Dice(5, imageView5);

        diceList.add(dice1);
        diceList.add(dice2);
        diceList.add(dice3);
        diceList.add(dice4);
        diceList.add(dice5);

        rollButton.setOnAction(event -> { //reset flag starts game over, if false,
            curImage1 = 0; curImage2 = 0; curImage3 = 0; curImage4 = 0; curImage5 = 0;
            if(reset){
                playAgain();
                reset = false;
            }
            else {
                for (int i = 0; i < 5; i++) {
                    if (i == 0 && flag1 || i == 1 && flag2 || i == 2 && flag3 || i == 3 && flag4 || i == 4 && flag5) {
                        continue;
                    }
                    else {
                        diceList.get(i).num = diceList.get(i).roll();
                    }
                }
                rollCounter++;

                if (rollCounter == 1) {
                    remainLabel.setText("Rolls remaining: 2");
                }
                if (rollCounter == 2) {
                    remainLabel.setText("Rolls remaining: 1");
                }
                if (rollCounter == 3) {
                    if (dice1.num == dice2.num && dice2.num == dice3.num && dice3.num == dice4.num && dice4.num == dice5.num) {
                        setScore(10);
                        remainLabel.setText("Five of a kind?! That's impossible!");
                        vbox.setId("bgimageMakeItRain");
                    }
                    else if (checkHand() == 5) {
                        setScore(8);
                        remainLabel.setText("Straight into winning!");
                        vbox.setId("bgimageStraightSolitaire");
                    }
                    else if (checkHand() == 0) {
                        setScore(7);
                        remainLabel.setText("Four of a kind! You're really lucky!");
                        vbox.setId("bgimageParksRec");
                    }
                    else if (checkHand() == 1) {
                        setScore(6);
                        remainLabel.setText("Full House!");
                        vbox.setId("bgimageFullHouse");
                    }
                    else if (checkHand() == 2) {
                        setScore(5);
                        remainLabel.setText("Three of a kind!");
                        vbox.setId("bgimageThanosDance");
                    }
                    else if (checkHand() == 3) {
                        setScore(4);
                        remainLabel.setText("Two pairs.");
                        vbox.setId("bgimageNotBad");
                    }
                    else if (checkHand() == 4) {
                        setScore(1);
                        remainLabel.setText("One pair. Better luck next time!");
                        vbox.setId("bgimageOneRain");
                    }
                    else if (checkHand() == -1) {
                        setScore(0);
                        remainLabel.setText("You get Nothing! You lose! Good day sir!");
                        vbox.setId("bgimageWonka");
                    }
                    rollCounter = 0;
                    reset = true;
                    rollButton.setText("Play Again");
                    currentLabel.setText("Your score: " + currentScore);
                    overallLabel.setText("Overall Score: " + overallScore);
                }
                for (int j = 0; j < 5; j++) { //sets images
                    switch (diceList.get(j).num) {
                        case 1:
                            diceList.get(j).diceDisplay.setImage(image1);
                            break;
                        case 2:
                            diceList.get(j).diceDisplay.setImage(image2);
                            break;
                        case 3:
                            diceList.get(j).diceDisplay.setImage(image3);
                            break;
                        case 4:
                            diceList.get(j).diceDisplay.setImage(image4);
                            break;
                        case 5:
                            diceList.get(j).diceDisplay.setImage(image5);
                            break;
                        case 6:
                            diceList.get(j).diceDisplay.setImage(image6);
                            break;
                    }
                }
                flag1 = false;
                flag2 = false;
                flag3 = false;
                flag4 = false;
                flag5 = false;
            }
        });

        imageView1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                if(rollCounter != 0) {
                    mouseClick1();
                }
            }
        });
        imageView2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(rollCounter != 0) {
                    mouseClick2();
                }
            }
        });
        imageView3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(rollCounter != 0) {
                    mouseClick3();
                }
            }
        });
        imageView4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(rollCounter != 0) {
                    mouseClick4();
                }
            }
        });
        imageView5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(rollCounter != 0) {
                    mouseClick5();
                }
            }
        });

        imageView1.setPreserveRatio(true);
        imageView2.setPreserveRatio(true);
        imageView3.setPreserveRatio(true);
        imageView4.setPreserveRatio(true);
        imageView5.setPreserveRatio(true);

        imageView1.setFitHeight(120);
        imageView2.setFitHeight(120);
        imageView3.setFitHeight(120);
        imageView4.setFitHeight(120);
        imageView5.setFitHeight(120);

        HBox hbox = new HBox(10, dice1.diceDisplay, dice2.diceDisplay, dice3.diceDisplay, dice4.diceDisplay, dice5.diceDisplay);

        vbox = new VBox(30, remainLabel, currentLabel, hbox, overallLabel, rollButton);

        hbox.setAlignment(Pos.CENTER);

        vbox.setAlignment(Pos.CENTER);

        vbox.setPadding(new Insets(10));

        Scene myScene = new Scene(vbox, 750, 500);

        myScene.getStylesheets().add("sample/diceStyles.css");

        vbox.setId("bgimage");

        primaryStage.setTitle("Dice Game");

        primaryStage.setScene(myScene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void playAgain(){
        remainLabel.setText("Rolls Remaining: 3");
        rollButton.setText("Roll Dice");
        currentLabel.setText("Your score: 0");
        vbox.setId("bgimage");
        for(int i = 0; i < 5; i++){
            diceList.get(i).num = i + 1;
        }
        for (int j = 0; j < 5; j++) { //reset images
            switch (diceList.get(j).num) {
                case 1:
                    diceList.get(j).diceDisplay.setImage(image1);
                    break;
                case 2:
                    diceList.get(j).diceDisplay.setImage(image2);
                    break;
                case 3:
                    diceList.get(j).diceDisplay.setImage(image3);
                    break;
                case 4:
                    diceList.get(j).diceDisplay.setImage(image4);
                    break;
                case 5:
                    diceList.get(j).diceDisplay.setImage(image5);
                    break;
                case 6:
                    diceList.get(j).diceDisplay.setImage(image6);
                    break;
            }
        }
        reset = true;
    }

    private void setScore(int i) {
        switch (i) {
            case 10:
                overallScore += 10;
                currentScore = 10;
                break;
            case 8:
                overallScore += 8;
                currentScore = 8;
                break;
            case 7:
                overallScore += 7;
                currentScore = 7;
                break;
            case 6:
                overallScore += 6;
                currentScore = 6;
                break;
            case 5:
                overallScore += 5;
                currentScore = 5;
                break;
            case 4:
                overallScore += 4;
                currentScore = 4;
                break;
            case 1:
                overallScore += 1;
                currentScore = 1;
                break;
            case 0:
                currentScore = 0;
                break;
        }
    }

    private int checkHand() {
        int counter = 0;
        int fullHouseCount = 0;
        int pairCount = 0;
        int pairNum = 0;

        for (int i = 1; i <= 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == diceList.get(j).num) {
                    counter++;
                }
            }
            if (counter == 4) {
                return 0;
            }
            else if (counter == 3) {
                fullHouseCount = 0;
                for (int k = 1; k <= 6; k++) {
                    for (int l = 0; l < 5; l++) {
                        if (k == diceList.get(l).num) {
                            fullHouseCount++;
                        }
                    }
                    if (fullHouseCount == 2) {
                        return 1;
                    }
                    fullHouseCount = 0;
                }
                return 2;
            }
            else if (counter == 2) {
                fullHouseCount = 0;
                for(int m = 1; m <= 6; m++){
                    for(int n = 0; n < 5; n++){
                        if(m == diceList.get(n).num){
                            pairCount++;
                        }
                    }
                    if(pairCount == 2){
                        pairNum++;
                    }
                    if(pairCount == 3){
                        fullHouseCount++;
                    }
                    pairCount = 0;
                }
                if(fullHouseCount == 1){
                    return 1;
                }
                else if(pairNum == 2){
                    return 3;
                }
                else{
                    return 4;
                }
            }
            else if(counter == 1){
                if(checkStraight()){
                    return 5;
                }
            }
            counter = 0;
            fullHouseCount = 0;
            pairCount = 0;
            pairNum = 0;
        }
        return -1;
    }

    private boolean checkStraight(){
        int[] array = new int[6];
        for(int i = 0; i < 5; i++){
            if(diceList.get(i).num == 1){
                array[0]++;
            }
            else if(diceList.get(i).num == 2){
                array[1]++;
            }
            else if(diceList.get(i).num == 3){
                array[2]++;
            }
            else if(diceList.get(i).num == 4){
                array[3]++;
            }
            else if(diceList.get(i).num == 5){
                array[4]++;
            }
            else if(diceList.get(i).num == 6){
                array[5]++;
            }
        }
        if((array[0] == 1 && array[1] == 1 && array[2] == 1 && array[3] == 1 && array[4] == 1) ||
                (array[1] == 1 && array[2] == 1 && array[3] == 1 && array[4] == 1 && array[5] == 1)){
            return true;
        }
        return false;
    }

    private void mouseClick1(){
        if(curImage1 == 0){
            switch(dice1.num){
                case 1:
                    dice1.diceDisplay.setImage(held1);
                    break;
                case 2:
                    dice1.diceDisplay.setImage(held2);
                    break;
                case 3:
                    dice1.diceDisplay.setImage(held3);
                    break;
                case 4:
                    dice1.diceDisplay.setImage(held4);
                    break;
                case 5:
                    dice1.diceDisplay.setImage(held5);
                    break;
                case 6:
                    dice1.diceDisplay.setImage(held6);
                    break;
            }
            flag1 = true;
            curImage1 = 1;
        }
        else{
            switch(dice1.num) {
                case 1:
                    dice1.diceDisplay.setImage(image1);
                    break;
                case 2:
                    dice1.diceDisplay.setImage(image2);
                    break;
                case 3:
                    dice1.diceDisplay.setImage(image3);
                    break;
                case 4:
                    dice1.diceDisplay.setImage(image4);
                    break;
                case 5:
                    dice1.diceDisplay.setImage(image5);
                    break;
                case 6:
                    dice1.diceDisplay.setImage(image6);
                    break;
            }
            flag1 = false;
            curImage1 = 0;
        }
    }
    private void mouseClick2(){
        if(curImage2 == 0){
            switch(dice2.num){
                case 1:
                    dice2.diceDisplay.setImage(held1);
                    break;
                case 2:
                    dice2.diceDisplay.setImage(held2);
                    break;
                case 3:
                    dice2.diceDisplay.setImage(held3);
                    break;
                case 4:
                    dice2.diceDisplay.setImage(held4);
                    break;
                case 5:
                    dice2.diceDisplay.setImage(held5);
                    break;
                case 6:
                    dice2.diceDisplay.setImage(held6);
                    break;
            }
            flag2 = true;
            curImage2 = 1;
        }
        else{
            switch(dice2.num) {
                case 1:
                    dice2.diceDisplay.setImage(image1);
                    break;
                case 2:
                    dice2.diceDisplay.setImage(image2);
                    break;
                case 3:
                    dice2.diceDisplay.setImage(image3);
                    break;
                case 4:
                    dice2.diceDisplay.setImage(image4);
                    break;
                case 5:
                    dice2.diceDisplay.setImage(image5);
                    break;
                case 6:
                    dice2.diceDisplay.setImage(image6);
                    break;
            }
            flag2 = false;
            curImage2 = 0;
        }
    }
    private void mouseClick3(){
        if(curImage3 == 0){
            switch(dice3.num){
                case 1:
                    dice3.diceDisplay.setImage(held1);
                    break;
                case 2:
                    dice3.diceDisplay.setImage(held2);
                    break;
                case 3:
                    dice3.diceDisplay.setImage(held3);
                    break;
                case 4:
                    dice3.diceDisplay.setImage(held4);
                    break;
                case 5:
                    dice3.diceDisplay.setImage(held5);
                    break;
                case 6:
                    dice3.diceDisplay.setImage(held6);
                    break;
            }
            flag3 = true;
            curImage3 = 1;
        }
        else{
            switch(dice3.num) {
                case 1:
                    dice3.diceDisplay.setImage(image1);
                    flag1 = false;
                    break;
                case 2:
                    dice3.diceDisplay.setImage(image2);
                    break;
                case 3:
                    dice3.diceDisplay.setImage(image3);
                    break;
                case 4:
                    dice3.diceDisplay.setImage(image4);
                    break;
                case 5:
                    dice3.diceDisplay.setImage(image5);
                    break;
                case 6:
                    dice3.diceDisplay.setImage(image6);
                    break;
            }
            flag3 = false;
            curImage3 = 0;
        }
    }
    private void mouseClick4(){
        if(curImage4 == 0){
            switch(dice4.num){
                case 1:
                    dice4.diceDisplay.setImage(held1);
                    break;
                case 2:
                    dice4.diceDisplay.setImage(held2);
                    break;
                case 3:
                    dice4.diceDisplay.setImage(held3);
                    break;
                case 4:
                    dice4.diceDisplay.setImage(held4);
                    break;
                case 5:
                    dice4.diceDisplay.setImage(held5);
                    break;
                case 6:
                    dice4.diceDisplay.setImage(held6);
                    break;
            }
            flag4 = true;
            curImage4 = 1;
        }
        else{
            switch(dice4.num) {
                case 1:
                    dice4.diceDisplay.setImage(image1);
                    break;
                case 2:
                    dice4.diceDisplay.setImage(image2);
                    break;
                case 3:
                    dice4.diceDisplay.setImage(image3);
                    break;
                case 4:
                    dice4.diceDisplay.setImage(image4);
                    break;
                case 5:
                    dice4.diceDisplay.setImage(image5);
                    break;
                case 6:
                    dice4.diceDisplay.setImage(image6);
                    break;
            }
            flag4 = false;
            curImage4 = 0;
        }
    }
    private void mouseClick5(){
        if(curImage5 == 0){
            switch(dice5.num){
                case 1:
                    dice5.diceDisplay.setImage(held1);
                    flag1 = true;
                    break;
                case 2:
                    dice5.diceDisplay.setImage(held2);
                    break;
                case 3:
                    dice5.diceDisplay.setImage(held3);
                    break;
                case 4:
                    dice5.diceDisplay.setImage(held4);
                    break;
                case 5:
                    dice5.diceDisplay.setImage(held5);
                    break;
                case 6:
                    dice5.diceDisplay.setImage(held6);
                    break;
            }
            flag5 = true;
            curImage5 = 1;
        }
        else{
            switch(dice5.num) {
                case 1:
                    dice5.diceDisplay.setImage(image1);
                    break;
                case 2:
                    dice5.diceDisplay.setImage(image2);
                    break;
                case 3:
                    dice5.diceDisplay.setImage(image3);
                    break;
                case 4:
                    dice5.diceDisplay.setImage(image4);
                    break;
                case 5:
                    dice5.diceDisplay.setImage(image5);
                    break;
                case 6:
                    dice5.diceDisplay.setImage(image6);
                    break;
            }
            flag5 = false;
            curImage5 = 0;
        }
    }

}
