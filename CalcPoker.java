/***************************************************************************************
* Name:        Calculator Poker
* Author:      Brian,Steven
* Date:        April 5, 2012
* Purpose:     This is called Calculator Poker, which it uses the 5 digit numbers to
               figure out the hand. The winner has the biggest hand.
****************************************************************************************/
import java.io.*;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalcPoker extends JPanel {
  static Image pics[] = new Image[8];  // array of dancing number gifs
  static Image pokerbg;               // background for welcome screen
  static Image gameStagebg;           // background for game stages
  static Image instructionsbg;        // background for instructions
  static Image winnerbg;              // background for winner screen
  static Image bgImage1;              // image displayed while play occurs for part 1
  static Image bgImage2;              // image displayed while play occurs for part 2
  static Image winImage[] = new Image[6];  // image displayed when player wins
  static JPanel panel;                 // main drawing panel
  static JFrame frame;                 // window frame which contains the panel
  static final int WINDOW_WIDTH = 800; // width of display window
  static final int WINDOW_HEIGHT = 600;// height of display window

  static int gameStage = 0;            // stage of game
  static final int WELCOME_SCREEN = 0;
  static final int MENU = 1;
  static final int INSTRUCTIONS = 2;
  static final int PLAY = 3;
  static final int END_GAME = 4;

  static int playStage = 0;           // stage of actual play
  static final int PART_ONE = 0;
  static final int PART_TWO = 1;
  static final int WINNER = 2;

  static boolean waitingForKeyPress = false;  // true when we are waiting for a user to make a specific choice
  static int numPlayers = 0;                     // number of players
  static double runningTotal = 0;                // runningTotal of game
  static int turn = 0;                           // current turn of game (0-9)

  static String playOutput = "";                 // output to panel after play
  static String playOutput2 = "";
  static String playOutput3 = "";
  static String playOutput4 = "";
  static String playOutput5 = "";
  static String playOutput6 = "";
  static String playOutput7 = "";
  static String playOutput8 = "";
  static String playOutput9 = "";
  static String playOutputa = "";
  static String playOutputb = "";
  static String playOutputc = "";
  static String playOutputd = "";
  static String playOutpute = "";
  static String playOutputf = "";
  static String playOutputa1 = "";
  static String playOutputa2 = "";
  static String playOutputa3 = "";
  static String playOutputb1 = "";
  static String playOutputb2 = "";
  static String playOutputb3 = "";
  static String playOutputc1 = "";
  static String playOutputc2 = "";
  static String playOutputc3 = "";
  static String playOutputwinner = "";
  static String playOutputKey = "";

  static String oneplayer="";
  static String twoplayer="";
  static String player="";
  static int att = 0;
  static int btt = 0;
  static int fpw = 0;
  static int spw = 0;
  static int cw = 0;


  // start main program
  public static void main (String args[]) {
      // Create Image Object
      Toolkit tk = Toolkit.getDefaultToolkit();

      // Load background images
      bgImage1 = tk.getImage("poker-is-for-valentines-wallpaper-for-800x600-mobile-494-391.jpg");
      bgImage2 = tk.getImage("bg2.jpg");
      pokerbg = tk.getImage("Poker-Cards-1-PMO1JSCQLG-800x600.jpg");
      gameStagebg = tk.getImage("uni1.gif");
      instructionsbg = tk.getImage("uni8.gif");
      winnerbg = tk.getImage("cool_background_2_800x600.jpg");

      // Load pics array with images
      pics[0] = tk.getImage("PokerChip10GreyAnimated.gif");
      pics[1] = tk.getImage("PokerChip25GreenAnimated.gif");
      pics[2] = tk.getImage("PokerChip50BlueAnimated.gif");
      pics[3] = tk.getImage("PokerChip100RedAnimated.gif");
      pics[4] = tk.getImage("PokerChip1000YellowAnimated.gif");
      pics[5] = tk.getImage("PokerChip2000LilacAnimated.gif");
      pics[6] = tk.getImage("PokerChip5000BlackAnimated.gif");
      pics[7] = tk.getImage("PokerChip10GreyAnimated.gif");


      // Loads winner image array
      winImage[0] = tk.getImage("w.gif");
      winImage[1] = tk.getImage("i.gif");
      winImage[2] = tk.getImage("n.gif");
      winImage[3] = tk.getImage("n.gif");
      winImage[4] = tk.getImage("e.gif");
      winImage[5] = tk.getImage("r.gif");


      // Create Frame and Panel to display graphics in

      panel = new CalcPoker(); /*****MUST CALL THIS CLASS (ie same as filename) ****/

      panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));  // set size of application window
      frame = new JFrame ("Calculator Poker");  // set title of window
      frame.add (panel);

      // add a key input listener (defined below) to our canvas so we can respond to key pressed
      frame.addKeyListener(new KeyInputHandler());

      // exits window if close button pressed
      frame.addWindowListener(new ExitListener());


      // request the focus so key events come to the frame
      frame.requestFocus();
      frame.pack();
      frame.setVisible(true);

  } // main

  /* paintComponent gets called whenever panel.repaint() is
   * called or when frame.pack()/frame.show() is called. It paints
   * to the screen.  Since we want to paint different things
   * depending on what stage of the game we are in, a variable
   * "gamestage" will keep track of this.
   */
  public void paintComponent(Graphics g) {
       super.paintComponent(g);   // calls the paintComponent method of JPanel to display the background

       // display welcome screen
       if (gameStage == WELCOME_SCREEN) {
           g.setColor(Color.yellow);

           g.fillRect (0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
           g.drawImage(pokerbg, 0, 0, this);
           // top line of images
           for (int i=0; i < pics.length/2; i++) {
               g.drawImage(pics[2*i], i*194, 0, this);  // display the image
           } // for

           g.setColor(Color.black);
           g.setFont(new Font("SansSerif", Font.BOLD, 16));   // set font
           g.drawString("Welcome to ", 360, 250);
           g.drawString("Press any key to continue.",310,350);

           g.setColor(Color.blue);
           g.setFont(new Font("SansSerif", Font.BOLD, 36));   // set font
           g.drawString("Calculator Poker",280,300);  // display

           // bottom line of images
           for (int i=0; i < pics.length/2 ; i++) {
               g.drawImage(pics[2*i+1], i*194, 400, this);  // display the image
           } // for
       // display game
       } else if (gameStage == MENU) {
           g.drawImage(gameStagebg, 0, 0, this);
           g.setColor(Color.blue);
               g.setFont(new Font("SansSerif", Font.BOLD, 36));   // set font
           g.drawString("Calculator Poker",280,150);  // display
               g.setFont(new Font("SansSerif", Font.BOLD, 16));   // set font
           g.drawString("Please make one of the following choices:",280,180);  // display
           g.drawString("1) Display Instructions.",280,210);
           g.drawString("2) One Play",280,240);
           g.drawString("3) Two Play",280,270);
           g.drawString("4) Exit",280,300);

       // display instructions
       } else if (gameStage == INSTRUCTIONS) {
           g.drawImage(instructionsbg, 0, 0, this);
           g.setColor(Color.yellow);
           g.setFont(new Font("SansSerif", Font.BOLD, 36));   // set font
           g.drawString("Instructions!",0,30);  // display
           g.setFont(new Font("Arial", Font.BOLD, 16));
           g.drawString("1) Select in the Menu screen between 1 player or 2 player mode.",0,100);
           g.drawString("2) Player 1 starts with 5 numbers.",0,120);
           g.drawString("3) Player 2 or Computer 1 will enter 5 numbers that are divisible by player one's number.",0,140);
           g.drawString("4) Player 1's number will divided by player two's divisor. ",0,160);
           g.drawString("5) Same process repeats with player 2 or Computer 1 picks 5 digit number,",0,180);
           g.drawString("and Computer 2 will pick a 5 digit number for divisor.",0,200);
           g.drawString("6) Player 2 or Computer 1's number will divided by Computer 2's divisor.",0,220);
           g.drawString("7) Same process repeats with Computer 2 picks 5 digit number," ,0,240) ;
           g.drawString("and Player 1 will pick a 5 digit number for divisor. " ,0,260);
           g.drawString("Note:No 0 with start, no letters allowed, and no numbers are same as the divisor's number. ",0,280);
           g.setFont(new Font("SansSerif", Font.BOLD, 33));
           g.drawString("HANDS!!",0,330);
           g.setFont(new Font("Arial", Font.BOLD, 16));
           g.drawString("1)High Card: 1 2 5 6 8",0,370);
           g.drawString("2)One Pair: 1 1 4 3 6",170,370);
           g.drawString("3)Two Pair: 1 1 3 3 6",340,370);
           g.drawString("4)Three of a Kind: 1 1 1 3 6",510,370);
           g.drawString("5)Straight: 1 2 3 4 5",0,400);
           g.drawString("6)Flush (Odd or Even): 2 4 4 4 8",170,400);
           g.drawString("7)Full House: 2 2 3 3 3",420,400);
           g.drawString("8)4 numbers the same: 4 4 4 4 5",0,430);
           g.drawString("9)Even straight flush: 0 4 8 2 6",250,430);
           g.drawString("10)Odd straight flus: 3 5 1 5 9",500,430);
           g.drawString("11)5 numbers the same: 5 5 5 5 5",250,460);
           g.drawString("Press any key to go back to menu.",250,490);






       // display one player game
       } else if (gameStage == PLAY)
       {


           // show stage one of play
           if (playStage == PART_ONE) {
               g.drawImage(bgImage1, 0, 0, this);
               g.setColor(Color.black);
               g.setFont(new Font("SansSerif", Font.BOLD, 36));
               g.drawString(playOutput,20,30);
               g.drawString(playOutput2,250,30);
               g.drawString(playOutput3,480,30);
               g.setColor(Color.red);
               g.setFont(new Font("SansSerif", Font.BOLD, 20));
               g.drawString(playOutput4,120,100);
               g.drawString(playOutput5,120,120);
               g.drawString(playOutput6,350,100);
               g.drawString(playOutput7,350,120);
               g.drawString(playOutput8,580,100);
               g.drawString(playOutput9,580,120);

               g.drawString(playOutputa,20,100);
               g.drawString(playOutputb,20,120);
               g.drawString(playOutputc,250,100);
               g.drawString(playOutputd,250,120);
               g.drawString(playOutpute,480,100);
               g.drawString(playOutputf,480,120);


           // show winner
           }  else if (playStage == WINNER) {
             g.drawImage(winnerbg, 0, 0, this);
             for (int i=0; i < winImage.length; i++) {
                 g.drawImage(winImage[i], i*115, (int)(370+(Math.pow(-1,i)*30)), this);
                 } // for
                 g.setColor(Color.red);
                 g.setFont(new Font("Arial", Font.BOLD, 20));
                 g.drawString(playOutputa1,0,20);
                 g.drawString(playOutputa2,0,40);
                 g.drawString(playOutputa3,0,60);
                 g.drawString(playOutputb1,0,100);
                 g.drawString(playOutputb2,0,120);
                 g.drawString(playOutputb3,0,140);
                 g.drawString(playOutputc1,0,180);
                 g.drawString(playOutputc2,0,200);
                 g.drawString(playOutputc3,0,220);
                 g.setFont(new Font("Arial", Font.BOLD, 30));
                 g.drawString(playOutputwinner,400,50);
                 g.setFont(new Font("Arial", Font.BOLD, 25));
                 g.drawString(playOutputKey, 400,150);
             }
       } else {

       } // else

  } // paintComponent

  /* A class to handle keyboard input from the user.
   * Implemented as a inner class because it is not
   * needed outside the EvenAndOdd class.
   */
  private static class KeyInputHandler extends KeyAdapter {
   public void keyTyped(KeyEvent e) {
       // quit if the user presses "escape"
       if (e.getKeyChar() == 27) {
                   System.exit(0);
       } else if (waitingForKeyPress == true) {

           // respond to menu selection
               switch (e.getKeyChar()) {
               case 49:  showInstructions(); break;             // Key "1" pressed
               case 50:  numPlayers = 1;if(btt==0){             // Key "2" pressed
                 Names();
                 break;
                 }else{
                 playGame();  break;}
               case 51:  numPlayers = 2;if(att==0){             // Key "3" pressed
                 Names();
                 break;
                 }else{
                 playGame();  break; }
               case 52:  System.exit(0);                        // Key "4" pressed
               case 112: fpw=1;break;
               case 113: spw=1;break;
               case 97:  cw=1;break;
          } // switch

       } else {
           showMenu();
       } // else

   } // keyTyped
  } // KeyInputHandler class


  /* Shuts program down when close button pressed */
  private static class ExitListener extends WindowAdapter {
    public void windowClosing(WindowEvent event) {
      System.exit(0);
    }
  } // ExitListener

    // Pause the program for duration milliseconds
  private static void pause(int duration) {
    try {
        Thread.sleep(duration);
    } catch (InterruptedException e) { }
  }

  private static void showMenu() {
      // display this stage of the game
      gameStage = MENU;
      playStage = PART_ONE;
      waitingForKeyPress = true;
      panel.repaint();
  } // startGame

    private static void zuobi() {
       fpw=0;
       spw=0;
       cw=0;

      }

  private static void showInstructions() {
      gameStage = INSTRUCTIONS;
      waitingForKeyPress = false;
      panel.repaint();
  } // startGame

  private static void Names() {
      playOutput = "";
      playOutput2 = "";
      playOutput3 = "";
      playOutputa = "";
      playOutputb = "";
      playOutputc = "";
      playOutputd = "";
      playOutpute = "";
      playOutputf = "";
      playOutput4 = "";
      playOutput5 = "";
      playOutput6 = "";
      playOutput7 = "";
      playOutput8 = "";
      playOutput9 = "";

      gameStage = PLAY;
      playStage = PART_ONE;
      waitingForKeyPress = false;

      panel.repaint();
      oneplayer=((numPlayers==2)?JOptionPane.showInputDialog(panel, "First player enter your name"):JOptionPane.showInputDialog(panel, "Please enter your name"));
      while(true){
         if(oneplayer.equals("")){
         JOptionPane.showMessageDialog(panel,"Please type in your name");
         continue;
         }
         break;
      }
      if(numPlayers==2){
       playOutput = oneplayer;
       panel.repaint();
      }else{
      player=oneplayer;
      playOutput=player;
      panel.repaint();
      }
      playOutputa = "Number: ";
      panel.repaint();

      playOutputb = "Divisor: ";
      panel.repaint();

      if(numPlayers==2){
      twoplayer=JOptionPane.showInputDialog(panel, "Second player enter your name ");

      while(true){
      if(oneplayer.equals(twoplayer)){
         JOptionPane.showMessageDialog(panel,"Please type in your orther name.");
         continue;
         }
         break;
      }
}
      playOutput2 =((numPlayers==2)? twoplayer:"Computer 1");
      playOutputc = "Number: ";
      playOutputd = "Divisor: ";
      panel.repaint();


      playOutput3 = ((numPlayers==2)?"Computer":"Computer 2");
      playOutpute = "Number: ";
      playOutputf = "Divisor: ";
      panel.repaint();
      playGame();

} // startgames

private static void playGame() {
      gameStage = PLAY;
      playStage = PART_ONE;
      String one1num;
      String one2num;
      String two1num;
      String three2num;
      String win="";
      double one1=0;
      double one2=0;
      double two1=0;
      double two2=0;
      double three1=0;
      double three2=0;
      double one=0;
      double two=0;
      double com=0;
      int onet=0;
      int two1t=0;
      int twot=0;
      int threet=0;
      int sa=0;
      int sb=0;
      int sc=0;
      int sd=0;
      int a1=3;
      int a2=3;
      int a3=3;
      int a4=3;
      int markone=0;
      int marktwo=0;
      int markcom=0;


      playOutput4 = "";
      playOutput5 = "";
      playOutput6 = "";
      playOutput7 = "";
      playOutput8 = "";
      playOutput9 = "";
      playOutputa1 = "";
      playOutputa2 = "";
      playOutputa3 = "";
      playOutputb1 = "";
      playOutputb2 = "";
      playOutputb3 = "";
      playOutputc1 = "";
      playOutputc2 = "";
      playOutputc3 = "";
      playOutputwinner = "";
      playOutputKey = "";

      // display this stage of game
      gameStage = PLAY;
      playStage = PART_ONE;
      waitingForKeyPress = false;

      panel.repaint();

      if(numPlayers==1){
          playOutput=player;
          oneplayer=player;
          twoplayer="Computer1";
          playOutput2="Computer1";
          playOutput3="Computer2";
          panel.repaint();
      }else{
          playOutput=oneplayer;
          playOutput2=twoplayer;
          playOutput3="Computer";
          panel.repaint();
      }



      while(sa==0){
          one1num= JOptionPane.showInputDialog(panel,"Welcome " + oneplayer+" Enter 5 numbers for your number.");


      try{
          one1=Double.parseDouble(one1num);
      } catch(Exception e){
      JOptionPane.showMessageDialog(panel,"Invalid number !");
      sa=0;
      continue;
      }
      char[] o1 = one1num.toCharArray();
           if(o1[0]=='0'){
               JOptionPane.showMessageDialog(panel,"First number can not be 0 ");
               sa=0;
           continue;
      }
      if(one1num.length()!=5){
           JOptionPane.showMessageDialog(panel,"Enter 5 numbers (Just 5 numbers !!!) ");
           sa=0;
           continue;
      }
           playOutput4 =one1num;
           panel.repaint();

           sa=1;
           break;
      }//while for first player num1
      if(numPlayers==2){
      while(sb==0){
           one2num= JOptionPane.showInputDialog(panel,twoplayer+" Enter 5 numbers for " + oneplayer + " 's divisor. ");
      try{
           one2=Double.parseDouble(one2num);
      } catch(Exception e){
           JOptionPane.showMessageDialog(panel,"Invalid number !");
           sb=0;
           continue;
      }
      char [] o2 = one2num.toCharArray();
      if(o2[0]=='0'){
           JOptionPane.showMessageDialog(panel,"First number can not be 0 ");
      sb=0;
      continue;
       }
      if(one2==one1){
          JOptionPane.showMessageDialog(panel,"The number for divisor can not equal the number for number ! ");
          sb=0;
          continue;
      }

      if(one2num.length()!=5){
          JOptionPane.showMessageDialog(panel,"Enter 5 numbers (Just 5 numbers !!!) ");
          sb=0;
          continue;
      }
          playOutput5 =one2num;
          panel.repaint();
          sb=1;
          break;
      }//while for second player num2

      while(sc==0){
          two1num= JOptionPane.showInputDialog(panel,"Welcome " + twoplayer+" Enter 5 numbers for your number. ");
      try{
          two1=Double.parseDouble(two1num);
      } catch(Exception e){
          JOptionPane.showMessageDialog(panel,"Invalid number !");
          sc=0;
          continue;
      }
        char [] t1 = two1num.toCharArray();
        if(t1[0]=='0'){
        JOptionPane.showMessageDialog(panel,"First number can not be 0 ");
        sc=0;
        continue;
        }
        if(two1num.length()!=5){
        JOptionPane.showMessageDialog(panel,"Enter 5 numbers (Just 5 numbers !!!)");
        sc=0;
        continue;
        }
        playOutput6 =two1num;
        panel.repaint();

        sc=1;
        break;
       }//while for second player num3
        }else{
            one2=10000+(int)(Math.random( ) * 89999);
       JOptionPane.showMessageDialog(panel,"Computer random 5 numbers for divisor of "+oneplayer +" "+ one2);
       onet=(int)one2;
       playOutput5 =onet+"";
       panel.repaint();

       two1=10000+(int)(Math.random( ) * 89999);
       JOptionPane.showMessageDialog(panel,"Computer random 5 numbers for Computer 1's number " + two1);
       two1t=(int)two1;
       playOutput6 =two1t+"";
       panel.repaint();
       }

       two2=10000+(int)(Math.random( ) * 89999);
       JOptionPane.showMessageDialog(panel,"Computer random 5 numbers for Computer 1's divisor " + two2);
       twot=(int)two2;
       playOutput7 =twot+"";
       panel.repaint();

       three1=10000+(int)(Math.random( ) * 89999);
       JOptionPane.showMessageDialog(panel,"Computer random 5 numbers for Computer 2's number " + three1);
       threet=(int)three1;
       playOutput8 =threet+"";
       panel.repaint();

       while(sd==0){
        three2num= JOptionPane.showInputDialog(panel,oneplayer+" Enter 5 numbers for Computer 2's divisor ");
        try{
          three2=Double.parseDouble(three2num);
          } catch(Exception e){
          JOptionPane.showMessageDialog(panel,"Invalid number !");
          sd=0;
          continue;
        }
        if(three1==three2){
            JOptionPane.showMessageDialog(panel,"The number for divisor can not equal the first number ! ");
        sd=0;
        continue;
       }
        char [] t2 = three2num.toCharArray();
       if(t2[0]=='0'){
        JOptionPane.showMessageDialog(panel,"First number can not be 0 ");
        sd=0;
        continue;
       }

       if(three2num.length()!=5){
        JOptionPane.showMessageDialog(panel,"Enter 5 numbers (Just 5 numbers !!!)");
        sd=0;
        continue;
       }
       playOutput9 =three2num;
       panel.repaint();
       sd=1;
       }//while for first player num6
      if(fpw==1){
        one2=one1+1;
      }
       one = one2 / one1;
       char [] One = (one+"").toCharArray();
      if(spw==1){
        two2=two1+1;
      }
       two = two2 / two1;
       char [] Two = (two+"").toCharArray();
       if(cw==1){
        three2=three1+1;
      }
       com = three2/three1;
       char [] Com = (com+"").toCharArray();


       char[] Onew= { One[2],One[3],One[4],One[5],One[6]};
       Arrays.sort(Onew);
       char[] Twow= { Two[2],Two[3],Two[4],Two[5],Two[6]};
       Arrays.sort(Twow);
       char[] Comw= { Com[2],Com[3],Com[4],Com[5],Com[6]};
       Arrays.sort(Comw);


       markone=Marks(hand1(Onew))+ Integer.parseInt(Onew[4]+"");
       marktwo=Marks(hand1(Twow))+ Integer.parseInt(Twow[4]+"");
       markcom=Marks(hand1(Comw))+ Integer.parseInt(Comw[4]+"");



        while(markone==marktwo&&marktwo>markcom){
            markone=markone+  Integer.parseInt(Onew[a1]+"");
            marktwo=marktwo+  Integer.parseInt(Twow[a1]+"");
            a1--;
        }
        while(markone==markcom&&markcom>marktwo){
            markone=markone+  Integer.parseInt(Onew[a2]+"");
            markcom=markcom+  Integer.parseInt(Comw[a2]+"");
            a2--;
        }
        while(markcom==marktwo&&marktwo>markone){
            markcom=markcom+  Integer.parseInt(Comw[a3]+"");
            marktwo=marktwo+  Integer.parseInt(Twow[a3]+"");
            a3--;
        }
        while(markcom==marktwo&&marktwo==markone){
            markcom=markcom+  Integer.parseInt(Comw[a4]+"");
            marktwo=marktwo+  Integer.parseInt(Twow[a4]+"");
            markone=markone+  Integer.parseInt(Onew[a4]+"");
            a4--;
        }

        playStage = WINNER;
        waitingForKeyPress = false;
        panel.repaint();
        if(markone>marktwo&&markone>markcom){
        win=oneplayer;
        }
        if(marktwo>markone&&marktwo>markcom){
        win=((numPlayers==2)?twoplayer:"Computer 1");
        }
        if(markcom>marktwo&&markcom>markone){
        win=((numPlayers==2)?"Computer":"Computer 2");
        }
        playOutputa1 = oneplayer+" got "+One[2]+One[3]+One[4]+One[5]+One[6];
        playOutputa2 += Gets(hand1(Onew));
        playOutputa3 = Onew[4]+"";
        playOutputb1 =((numPlayers==2)? twoplayer+" got "+Two[2]+Two[3]+Two[4]+Two[5]+Two[6]:"Computer 1 got "+Two[2]+Two[3]+Two[4]+Two[5]+Two[6]);
        playOutputb2 += Gets(hand1(Twow));
        playOutputb3 = Twow[4]+"";
        playOutputc1 = ((numPlayers==2)?"Computer got "+Com[2]+Com[3]+Com[4]+Com[5]+Com[6]:"Computer 2 got "+Com[2]+Com[3]+Com[4]+Com[5]+Com[6]);
        playOutputc2 = Gets(hand1(Comw));
        playOutputc3 = Comw[4]+"";
        playOutputwinner = win+" win!";
        playOutputKey = "Enter any Key to go back to menu";
        panel.repaint();
        if(numPlayers==1){
        btt++;
        }else{
          att++;
        }
        zuobi();
} // playGame

      public static String  hand1(char[] tem) {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int z = 0;
        String output = "";

        for(int f=0;f<5;f++){
            if(tem[f]%2 == 1){
            z++;
        }
        if(tem[0]==tem[f]){
            a++;
        }else{
            for(int h=f;h<5;h++){
        if(tem[h]==tem[f]){
            b++;
        }
}//for
}//else
} //for

        for(int u = 0;u<4;u++){
            if(tem[u]+1 == tem[u+1]){
            c++;
            }
            if(tem[u]+2==tem[u+1]){
            d++;
            }
        }

            if(a==5){
            output+="A";
            }

            if(d==4 || tem[0]==1 ){
            output+="B";
        }

            if(d==4&&tem[0]==0){
            output+="C";
        }

            if(a==4||b==10){
            output+="D" ;
        }
            if((a==3&&b==3)||(a==2&&b==6)){
            output+="E";
        }
            if(z==5){
            output+="F1";
        }
            if(z==0){
            output+="F2";
        }
            if(c==4){
            output+="G";
        }
            if((a==3&&b==2)||(a==1&&b==7)){
            output+="H" ;
        }
            if((a==1&&b==6)||(a==2&&b==4)){
            output+="I" ;
        }
            if((a==1&&b==5)||(a==2&&b==3)){
            output+="J" ;
        }
            if(a==1&&b==4){
            output+="K";
        }


            return(output);
}  // output


       public static String Gets(String output) {
            String mt = "";

       if(output.contains("A")) {
           mt+="5 same numbers";
       }
       if(output.contains("B")) {
           mt+="Odd flush; ";
       }
       if(output.contains("C")) {
           mt+="Even flush; ";
       }
       if(output.contains("D")) {
           mt+="4 same numbers; ";
       }
       if (output.contains("E")) {
           mt+="Full house; ";
       }
       if (output.contains("F1")) {
           mt+="all odd; ";
       }
       if (output.contains("F2")) {
           mt+="all even; ";
       }
       if (output.contains("G")) {
           mt+="5 number sequence; ";
       }
       if (output.contains("H")) {
           mt+="Three same number; ";
       }
       if (output.contains("I")) {
           mt+="Two pairs; ";
       }
       if (output.contains("J")) {
           mt+="One pair; ";
       }
       if (output.contains("K")) {
           mt+="High number; ";
      }
       return(mt);
}  // hands

        public static int Marks(String output) {
            String oo = "KK" ;
            String pp ="F";
            int mark=0;
        char [] Output =  output.toCharArray();
        char [] ctm =  pp.toCharArray();
        char [] string = oo.toCharArray();

        do{
           mark += 100;
           if(Output[0]==string[0]){
           break;
        }
           string[0]--;
           }while(string[0]>='A');
           if(Output.length!=2||Output[0]!=ctm[0]){
           if(Output.length==3&&Output[0]==ctm[0]){
           Output[1]=Output[2];
        }
           if(Output.length!=1){
        do{
           mark += 10;
           if(Output[1]==string[1]){
           break;
        }
           string[1]--;
        }while(string[1]>='A');
        }
}
        return(mark);
        }// mark

} //CalcPoker
