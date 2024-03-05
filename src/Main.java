import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("--------------- Setting up Bus ---------------");
        String message;
        int[][] bus;
        int[] available;
        int[] unavailable;
        int checkID;
        String resultNumBus, resultBusSeat;
        String answer_user;
        int count_list_bus = 0;
        Scanner sc = new Scanner(System.in);
        // ANSI escape code color
        String redColor = "\u001B[31m";
        String whiteColor = "\u001B[0m";
        String greenColor = "\u001B[32m";
        String blueColor = "\u001B[34m";
        do {
            System.out.print("-> Enter number of Bus :");
            resultNumBus = sc.nextLine();

            message = Pattern.matches("[^0]\\d?", resultNumBus) ? "" : redColor + "Number of Bus can input only one or two Number" + whiteColor;
            if (!message.equals("")) {
                System.out.println(message);
            }
        } while (!message.equals(""));
        do {
            System.out.print("-> Enter number Seat of Bus :");
            Scanner numSeat = new Scanner(System.in);
            resultBusSeat = numSeat.nextLine();
            message = Pattern.matches("[^0]\\d{1}", resultBusSeat) ? "" : redColor + "Number Seat of Bus can input only one or two Number" + whiteColor;
            if (!message.equals("")) {
                System.out.println(message);
            }
        } while (!message.equals(""));
//        System.out.println(resultNumBus);
//        System.out.println(resultNumSeat);
        bus = new int[Integer.parseInt(resultNumBus)][Integer.parseInt(resultBusSeat)];
        available = new int[Integer.parseInt(resultNumBus)];
        unavailable = new int[Integer.parseInt(resultNumBus)];
        for (int i = 0; i < bus.length; i++) {
            for (int j = 0; j < bus[i].length; j++) {
                bus[i][j] = 1;
            }
        }
        for (int i = 0; i < bus.length; i++) {
            for (int j = 0; j < bus[i].length; j++) {
                if (bus[i][j] == 1) {
                    available[i] = available[i] + 1;
                } else {
                    unavailable[i] = unavailable[i] + 1;
                }
            }
        }
        System.out.println(blueColor + "--------------- Bus Management System 🧑‍💻🚌 ---------------" + whiteColor);
        int option;
        do {
            System.out.println("\n1- Check Bus\n" + "2- Booking Bus\n" + "3- Cancel Booking\n" + "4- Reset Bus\n" + redColor + "5- Exit" + whiteColor);
            System.out.println("-----------------------------------------------------");
            System.out.print("-> Choose option (1-5): ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    int id;
                    int[] seatAvailable, seatUnavailable;
                    seatAvailable = new int[Integer.parseInt(resultNumBus)];
                    seatUnavailable = new int[Integer.parseInt(resultNumBus)];
                    for (int i = 0; i < bus.length; i++) {
                        for (int j = 0; j < bus[i].length; j++) {
                            if (bus[i][j] == 1) {
                                seatAvailable[i] = seatAvailable[i] + 1;
                            } else {
                                seatUnavailable[i] = seatUnavailable[i] + 1;
                            }
                        }
                    }
                    CellStyle headerStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                    Table table = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                    table.setColumnWidth(0, 20, 30);
                    table.setColumnWidth(1, 20, 30);
                    table.setColumnWidth(2, 20, 30);
                    table.setColumnWidth(3, 20, 30);

                    // Add headers
                    table.addCell(blueColor + "Display All Bus Information🚌🚌" + whiteColor, headerStyle, 4);
                    table.addCell(blueColor + "Id" + whiteColor, headerStyle);
                    table.addCell(blueColor + "Seat💺" + whiteColor, headerStyle);
                    table.addCell(greenColor + "Available" + whiteColor, headerStyle);
                    table.addCell(redColor + "Unavailable" + whiteColor, headerStyle);
                    for (int i = 0; i < Integer.parseInt(resultNumBus); i++) {
                        table.addCell(String.valueOf((i + 1)), headerStyle);
                        table.addCell(String.valueOf(bus[i].length), headerStyle);
                        table.addCell(String.valueOf(seatAvailable[i]), headerStyle);
                        table.addCell(String.valueOf(seatUnavailable[i]), headerStyle);

                    }
                    System.out.println(table.render());
                    boolean continueLoop = true;

                    while (continueLoop) {
                        int cnt_available = 0;

                        System.out.print("\n=> Enter 0 to Back or Bus ID to see Detail : ");
                        id = sc.nextInt();

                        if (id == 0 || id > bus.length) {
                            System.out.println((id == 0) ? "Exiting..." : redColor + "We only have " + resultNumBus + " buses!!" + whiteColor);
                            continueLoop = false;
                        } else {
                            CellStyle headerStyle1 = new CellStyle(CellStyle.HorizontalAlign.center);
                            Table table1 = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                            table1.setColumnWidth(0, 20, 30);
                            table1.setColumnWidth(1, 20, 30);
                            table1.setColumnWidth(2, 20, 30);
                            table1.setColumnWidth(3, 20, 30);
                            table1.setColumnWidth(4, 20, 30);

                            table1.addCell(blueColor + "Bus Details for Bus ID " + id + whiteColor, headerStyle1, 5);

                            for (int i = 0; i < Integer.parseInt(resultBusSeat); i++) {
                                count_list_bus += 1;
                                String msg = (bus[id - 1][i] == 0) ? redColor+"(-)"+whiteColor : greenColor+"(+)"+whiteColor;
                                table1.addCell(msg + " " + (i + 1), headerStyle1);

                                if (bus[id - 1][i] == 1) {
                                    cnt_available += 1;
                                }
                            }
                            System.out.println(table1.render());
                            System.out.println(redColor+"\t(-) Unavailable Seats: " + (bus[id - 1].length - cnt_available)+whiteColor + greenColor+"\t(+) Available Seats: " + cnt_available+whiteColor);

                        }
                    }
                    break;
                case 2:
                    int cnt_available = 0;
                    int cntBus = 0;
                    System.out.print("=> Enter Bus ID : ");
                    checkID = sc.nextInt();
                    if (checkID > Integer.parseInt(resultNumBus)) {
                        System.out.println(redColor + "We have only " + Integer.parseInt(resultNumBus) + " !!" + whiteColor);
                        break;
                    }
                    CellStyle headerStyle1 = new CellStyle(CellStyle.HorizontalAlign.center);
                    Table table1 = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                    table1.setColumnWidth(0, 20, 30);
                    table1.setColumnWidth(1, 20, 30);
                    table1.setColumnWidth(2, 20, 30);
                    table1.setColumnWidth(3, 20, 30);
                    table1.setColumnWidth(4, 20, 30);

                    table1.addCell(blueColor + "Bus Details for Bus ID " + checkID + whiteColor, headerStyle1, 5);

                    for (int i = 0; i < Integer.parseInt(resultBusSeat); i++) {
                        if (bus[checkID - 1][i] == 1) {
                            cnt_available = cnt_available + 1;
                        }
                        cntBus = cntBus + 1;
                        String msg = bus[checkID - 1][i] == 0 ? redColor+"(-)"+whiteColor : greenColor+"(+)"+whiteColor;
                        table1.addCell(msg + " " + (i + 1), headerStyle1);

                    }
                    System.out.println(table1.render());
                    System.out.println(redColor+"\n(-) Unavailable Seats: " + (bus[checkID - 1].length - cnt_available)+whiteColor + greenColor+"\t(+) Available Seats: " + cnt_available +whiteColor);

                    int seat_booking;
                    System.out.print("\n-> Enter seat number to booking or 0 back to Menu : ");
                    seat_booking = sc.nextInt();
                    System.out.println();
                    if (seat_booking > bus[checkID - 1].length) {
                        System.out.println(redColor+"We have only " + Integer.parseInt(resultBusSeat) + " chairs !!"+whiteColor);
                        break;
                    }
                    if (bus[checkID - 1][seat_booking - 1] == 0) {
                        System.out.println(redColor+"This seat is unavailable right now !! Please choose other seat.😍😍"+whiteColor);
                        System.out.println(checkID);
                        break;
                    }

                    System.out.print("=> Do you want to book chair number " + seat_booking + "? (y/n) : ");
                    answer_user = sc.next().toUpperCase();

                    if (answer_user.equals("Y")) {
                        bus[checkID - 1][seat_booking - 1] = 0;
                        unavailable[checkID - 1] += 1;
                        available[checkID - 1] -= 1;
                        System.out.println(greenColor+"You have booked chair number : "+whiteColor+blueColor + seat_booking +whiteColor+"🎉🎉");
                        break;
                    } else {
                        System.out.println(redColor+"You don't booking any seats !!☺️☺️ "+whiteColor);
                        break;
                    }
                case 3 :
                    int bus_id,count_seat_availabel=0,cnt_bus_num=0,cancel_seat;
                    String answer_user_cancle;
                    System.out.print("-> Enter Bus ID : ");
                    bus_id = sc.nextInt();
                    if(bus_id > Integer.parseInt(resultNumBus)){
                        System.out.println(redColor+"We have only "+ Integer.parseInt(resultNumBus) + " bus !!"+whiteColor);
                        break;
                    }

                    CellStyle headerStyle2 = new CellStyle(CellStyle.HorizontalAlign.center);
                    Table table2 = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                    table2.setColumnWidth(0, 20, 30);
                    table2.setColumnWidth(1, 20, 30);
                    table2.setColumnWidth(2, 20, 30);
                    table2.setColumnWidth(3, 20, 30);
                    table2.setColumnWidth(4, 20, 30);
                    table2.addCell(blueColor + "DISPLAY BUS INFORMATION" + whiteColor, headerStyle2, 5);
                    for(int i=0;i<Integer.parseInt(resultBusSeat);i++){
                        if (bus[bus_id-1][i]==1){
                            count_seat_availabel+=1;
                        }
                        cnt_bus_num+=1;
                        String msg = bus[bus_id-1][i] == 0 ? redColor+"(-)"+whiteColor : greenColor+"(+)"+whiteColor;
                        table2.addCell(msg + " " + (i + 1), headerStyle2);
                    }
                    System.out.println(table2.render());
                    System.out.println(redColor+"\n(-) Unavailable Seats: " + +(bus[bus_id-1].length-count_seat_availabel)+whiteColor + greenColor+"\t(+) Available Seats: " + count_seat_availabel +whiteColor);

//                    System.out.println("\n( - ) : Unavailable("+(bus[bus_id-1].length-count_seat_availabel)+")" + "\t\t\t" + "( + ) : Available("+count_seat_availabel+")\n");

                    System.out.print("Enter seat number to cancel booking : ");
                    cancel_seat = sc.nextInt();
                    if(bus[bus_id-1][cancel_seat-1]==0){
                        System.out.print("=> Do you want to cancel book chair number "+cancel_seat +" ? (y/n) :");
                        answer_user_cancle = sc.next().toUpperCase();
                        if (answer_user_cancle.equals("Y")){
                            bus[bus_id-1][cancel_seat-1] = 1;
                            System.out.println(greenColor+"Seat "+ cancel_seat + " was cancel booking successfully !!"+whiteColor);
                            break;
                        }else {
                            System.out.println(blueColor+"Seat  "+ cancel_seat + " was not cancel booking !!"+whiteColor);
                            break;
                        }
                    }else {
                        System.out.println(redColor+"Seat "+ cancel_seat + " Already Available !!"+whiteColor);
                    }
                    break;
                case 4 :
                    String reset_seat;
                    System.out.print("-> Enter ID of Bus you want to reset : ");
                    id = sc.nextInt();
                    if(id>Integer.parseInt(resultNumBus)){
                        System.out.println(redColor+"We have only "+ Integer.parseInt(resultNumBus)+" bus !!"+whiteColor);
                        break;
                    }else {
                        System.out.print("Bus ID "+ id+ " was reset with all seats available? (y/n) :");
                        reset_seat = sc.next().toUpperCase();
                        if(reset_seat.equals("Y")){
                            for (int i = 0;i<Integer.parseInt(resultBusSeat);i++){
                                bus[id-1][i] = 1;
                            }
                            System.out.println(greenColor+"Bus ID "+ id+ " was reset successfully !!"+whiteColor);
                        }
                    }
                    break;
                case 5 :
                    System.out.println("Exiting the Bus Management System. Goodbye!👋👋");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 5.");
            }


        }while (option != 5) ;
    }
}

