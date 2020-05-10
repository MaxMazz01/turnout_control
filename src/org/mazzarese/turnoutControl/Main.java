package org.mazzarese.turnoutControl;

import com.fazecast.jSerialComm.SerialPort;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        SerialPort[] ports = SerialPort.getCommPorts();
        for(int i = 0; i < ports.length; i++) {
            System.out.println((i + 1) + ") " + ports[i].getPortDescription());
        }
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose the port for bank A: ");
        int selected = sc.nextInt();
        TurnoutBank bankA = new TurnoutBank(ports[selected], 'a');
        Turnout FR1 = new Turnout("1", Turnout.THROW_DOWN_LEFT, bankA.getPort());
        Turnout FR2 = new Turnout("2", Turnout.THROW_UP_LEFT, bankA.getPort());
        Turnout MJ1 = new Turnout("3", Turnout.THROW_DOWN_RIGHT, bankA.getPort());
        Turnout MJ2 = new Turnout("4", Turnout.THROW_DOWN_RIGHT, bankA.getPort());
        Turnout MJ3 = new Turnout("5", Turnout.THROW_DOWN_RIGHT, bankA.getPort());
        bankA.add(FR1);
        bankA.add(FR2);
        bankA.add(MJ1);
        bankA.add(MJ2);
        bankA.add(MJ3);


        TurnoutCommand cmd;
        while(true) {
            for(int i = 0; i < 100; i++) {
                System.out.println();
            }
            System.out.println("             2                      1                            3       4       5");
            System.out.println("[____________");
            System.out.println("[___________" + FR2.depiction() + "________________________________________________________________________________________________");
            System.out.println("_________________________________" + FR1.depiction()+ "                               " + MJ1.depiction() + "___________________________________________");
            System.out.println("                                                                        " + MJ2.depiction() + "____________________________________");
            System.out.println("                                                                               " +MJ3.depiction() + "_____________________________");
            System.out.println();


            System.out.println("Enter Command:");
            String command = sc.nextLine();
            System.out.println(command);
            if(command.equals("list")) {
                for(int i = 0; i < bankA.size(); i++) {
                    System.out.print("" + i + ") " + bankA.get(i));
                    System.out.println();
                }
            }
            else if(command.equals("exit")) {
                break;
            }
            else {
                cmd = new TurnoutCommand(command);
                Turnout t;
                for(int i = 0; i < bankA.size(); i++) {
                    t = bankA.get(i);
                    if(t.getIdentifier().equals(cmd.getIdentifier())) {
                        t.flipDirection(cmd.getInstruction());
                    }
                }
            }
        }
    }
}
