import java.util.*;
import java.util.Random;
import java.util.Scanner;

public class Craps{

    //Cria um gerador de números aleatórios para uso no método rollDice
    private Random randomNumbers = new Random();

    //Enumerações com constantes que representam o status do jogo
    private enum Status{CONTINUE, WON, LOST};

    //Constantes que representam o  status do jogo
    private final static int SNAKE_EYES = 2;
    private final static int TREY = 3;
    private final static int SEVEN = 7;
    private final static int YO_SEVEN = 11;
    private final static int BOX_CARS = 12;

    void clear(){
        for(int i = 0; i < 50; i++)
            System.out.println("\n");
    }

    //Menu do jogo
    public void Menu(){

        Scanner input = new Scanner(System.in);
        int choose = 0;
        int pause;
        
        do{
            System.out.println("\t\t1 - INICIAR O JOGO");
            System.out.println("\t\t2 - REGRAS DO JOGO");
            System.out.println("\t\t0 - SAIR DO JOGO");
            System.out.printf("\t\tESCOLHA: ");
            choose = input.nextInt();

            switch(choose){

                case 1:
                    clear();
                    play();
                    System.out.println("DESEJA CONTINUAR 1 - SIM/ 0 - NÃO: ");
                    pause = input.nextInt();
                    if(pause == 1);
                    else
                        choose = 0;



                    break;
                case 2:
                    clear();
                    System.out.println("VOCÊ LANÇA DOIS DADOS. CADA DADO TEM SEIS FACES QUE CONTÊM UM, DOIS, TRÊS, QUATRO, CINCO, SEIS\n" +
                                   "RESPECTIVAMENTE. DEPOIS QUE OS DADOS PARAM DE ROLAR, A SOMA DOS PONTOS NAS FACES VIRADAS PARA CIMA É CALCULADA\n"+
                                   "SE A SOMA FOR 7 OU 11 NO PRIMEIRO LANCE, VOCÊ GANHA. SE A SOMA FOR 2, 3 OU 12 NO PRIMEIRO LANCE (CHAMADO CRAPS\n)"+
                                   ",VOCÊ PERDE (ISTO É, A CASA GANHA). SE A SOMA FOR 4,5,6,8,9 OU 10 NO PRIMEIRO LANCE, ESSA SOMA TORNA-SE SUA PONTUAÇÃO\n"+
                                   "PARA GANHA, VOCÊ DEVE CONTIUNUAR A ROLAR OS DADOS ATÉ FAZER SUA PONTUAÇÃO (ISTO É, OBTER UM VALOR IGUAL A SUA PONTUAÇÃO\n"+
                                   "VOCÊ PERDE SE OBTIVER UM 7 ANTES DE FAZER A PONTUAÇÃO\n");   
                    System.out.println("DESEJA CONTINUAR 1 - SIM/ 0 - NÃO: ");    
                    pause = input.nextInt();
                    if(pause == 1);
                    else
                        choose = 0;
                    break;
                case 0:
                    clear();
                    break;

            }
            clear();
        }while(choose != 0);
        System.out.println("OBRIGADO POR USAR!!");
        input.close();

    }


    //Joga uma partida de craps
    private void play(){

        int myPoint = 0; // pontos se não ganhar ou perder na 1° rolagem
        Status gameStatus; // pode conter CONTINUE, WON ou LOST

        int sumOfDice = rollDice();
        
        //Determina o status do jogo e a pontuação com base no primeiro lançamento
        switch(sumOfDice){

            case SEVEN: // Ganha com 7 no primeiro lançamento
            case YO_SEVEN: //Ganha com 11 no primeiro lançamento
                gameStatus = Status.WON;
                break;
            case SNAKE_EYES:
            case TREY:
            case BOX_CARS:
                gameStatus = Status.LOST;
                break;
            default: // não ganhou nem perdeu, portanto registra a pontuação
            gameStatus = Status.CONTINUE; // jogo não terminou
            myPoint = sumOfDice; // informa pontuação
            System.out.printf("Point is %d\n", myPoint);
            break;
        }// fim swtich

        while(gameStatus == Status.CONTINUE){ // Nem WON nem LOST

            sumOfDice = rollDice(); // Lança os dados novamente

            //Determina o status do jogo
            if(sumOfDice == myPoint)
                gameStatus = Status.WON;

            else
                if(sumOfDice == SEVEN) //perde obtendo 7 antes de atingir a pontuação
                    gameStatus = Status.LOST; 
        }// Fim do while

        //Exibe uma mensagem ganhou ou perdeu
        if(gameStatus == Status.WON)
            System.out.println("Player wins");
        else
        System.out.println("Player loses"); 
    }// Fim método play

    private int rollDice(){

        //Seleciona valores aleatório do dado
        int die1 = 1 + randomNumbers.nextInt(6); // primeiro lançamento dos dados
        int die2 = 1 + randomNumbers.nextInt(6); // segundo laçamento dos dados

        int sum = die1 + die2; //soma dos dados

        //Exibe os resultados desse lançamento
        System.out.printf("Player rolled %d + %d = %d\n", die1, die2, sum);

        return sum;


    }




}