package com.OOAD;

import java.util.Scanner;

public class receiveUserInteraction implements ConsoleLogger{
    Scanner input;
    Simulation simulation;
    int selection;
    boolean terminate;

    Invoker invoker; //for command pattern
    Receiver receiver;

    receiveUserInteraction(Scanner scan, Simulation simulation){
        this.input = scan;
        this.simulation = simulation;
        this.invoker = new Invoker();
        this.receiver = new Receiver(simulation, input);
        this.terminate = false;
    }

    public void openForInteraction(){
        displayOptions();
        terminate = false;
    }

    public void displayOptions(){
        while(!terminate) {
            System.out.println("");
            System.out.println("Select command:");
            System.out.println("1: Select store");
            System.out.println("2: Ask current working clerk for their name");
            System.out.println("3: Ask current working clerk for the time");
            System.out.println("4: Sell an item to the store");
            System.out.println("5: Buy an item from the store");
            System.out.println("6: Buy a guitar kit from the store");
            System.out.println("7: End interaction");
            int select = getSelection(1, 7);
            doCommand(select);
        }
    }

    public int getSelection(int min, int max){
        selection = max+1;
        while(selection<min || selection > max){
            selection = input.nextInt();
        }
        return selection;
    }
    //load commands in invoker and execute them
    public void doCommand(int select){
        switch (select){
            case 1 ->{
                SelectStoreCommand selectStore = new SelectStoreCommand(receiver);
                invoker.setCommand(selectStore);
            }
            case 2 ->{
                AskNameCommand askName = new AskNameCommand(receiver);
                invoker.setCommand(askName);
            }
            case 3 ->{
                AskTimeCommand askTime = new AskTimeCommand(receiver);
                invoker.setCommand(askTime);
            }
            case 4 ->{
                SellItemCommand sellItem = new SellItemCommand(receiver);
                invoker.setCommand(sellItem);
            }
            case 5 ->{
                BuyItemCommand buyItem = new BuyItemCommand(receiver);
                invoker.setCommand(buyItem);
            }
            case 6 ->{
                BuyGuitarKitCommand buyGuitarKit = new BuyGuitarKitCommand(receiver);
                invoker.setCommand(buyGuitarKit);
            }
            case 7 ->{
                EndInteractionCommand endInteraction = new EndInteractionCommand(receiver, this);
                invoker.setCommand(endInteraction);
            }
        }
        invoker.executeCommand();
    }
}
