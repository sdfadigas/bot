import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.*;
import java.io.File;
import java.io.*;
import java.util.List;




public class QuoteMotivaBot extends TelegramLongPollingBot {
    //nesse método que vamos incluir os comandos do bot
    @Override
    public void onUpdateReceived(Update update) {

        String command = update.getMessage().getText();
        //comando que exibe a mensagem de boas vindas
        if (command.equals("/start")) {
            String message = "Seja bem vindo(a) ao nosso bot! Digite /frase para receber uma frase motivacional aleatória, /antifrase para receber uma frase desmotivacional aleatória /imagem para receber uma imagem motivacional ou /sobre para acessar a documentação do bot. Ou se preferir, pesquise no menu no canto inferior esquerdo para as opções de comando";
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);

            try {
                execute(response);
            } catch (TelegramApiException BV) {
                BV.printStackTrace();
            }

            //comando que acessa o link pra documentação
        } else if (command.equals("/sobre")) {
            SendMessage responseS = new SendMessage();
            responseS.enableMarkdown(true);
            responseS.setChatId(update.getMessage().getChatId().toString());
            responseS.setText("Quer entender o processo de desenvolvimento do bot? [clique aqui](https://www.notion.so/Projeto-Chatbot-00f5e88fe2ee464f83f277adf0cb58e0/)");

            try {
                execute(responseS);
            } catch (TelegramApiException BV) {
                BV.printStackTrace();
            }

            //comando para exibir uma imagem desmotivacional aleatória

        } else if (command.equals("/imagem")) {
            File folder = new File("C:\\Users\\samara\\IdeaProjects\\QuoteMotivaBot\\img\\");
            File[] listOfImages = folder.listFiles();
            List<File> allImages = new ArrayList<>(List.of(listOfImages)); //cria uma lista com todos os arquivos
            Random rand = new Random();
            int number = rand.nextInt(allImages.size());



            try {
                SendPhoto pics = new SendPhoto();
                pics.setChatId(update.getMessage().getChatId());
                pics.setPhoto(new InputFile(allImages.get(number)));
                execute(pics);

            } catch (TelegramApiException e) {
                e.printStackTrace();


                //comando que gera a frase motivacional aleatória
            }}
        else if (command.equals("/frase")) {

                //aqui vai nosso código com o loop
                File dir = new File("C:\\Users\\samara\\IdeaProjects\\QuoteMotivaBot");
                File arq = new File(dir, "frases.txt");

                try {
                    FileReader fileReader = new FileReader(arq); //lê o arquivo .txt
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String linha = "";

                    //Lista que irá guardar o resultado, ou seja,
                    // cada linha do arquivo que corresponde a um User
                    List<String> result = new ArrayList();

                    while ((linha = bufferedReader.readLine()) != null) {

                        if (linha != null && !linha.isEmpty()) {
                            result.add(linha);
                        }

                    }
                    fileReader.close();
                    bufferedReader.close();


                    Random rand = new Random();
                    int num = rand.nextInt(result.size());
                    String frase = result.get(num);
                    SendMessage responseF = new SendMessage();
                    responseF.setChatId(update.getMessage().getChatId());
                    responseF.setText(frase);

                    try {
                        execute(responseF);
                    } catch (TelegramApiException BV) {
                        BV.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


                //comando que gera a frase desmotivacional aleatória
            } else if (command.equals("/antifrase")) {

                //aqui vai nosso código com o loop
                File dir = new File("C:\\Users\\samara\\IdeaProjects\\QuoteMotivaBot");
                File arq = new File(dir, "antifrases.txt");

                try {
                    FileReader fileReader = new FileReader(arq);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String linha = "";

                    //Lista que irá guardar o resultado, ou seja,
                    // cada linha do arquivo que corresponde a um User
                    List<String> result = new ArrayList();

                    while ((linha = bufferedReader.readLine()) != null) {

                        if (linha != null && !linha.isEmpty()) {
                            result.add(linha);
                        }

                    }
                    fileReader.close();
                    bufferedReader.close();


                    Random rand = new Random();
                    int num = rand.nextInt(result.size());
                    String frase = result.get(num);
                    SendMessage responseF = new SendMessage();
                    responseF.setChatId(update.getMessage().getChatId());
                    responseF.setText(frase);

                    try {
                        execute(responseF);
                    } catch (TelegramApiException BV) {
                        BV.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

}


    @Override
    public String getBotUsername() {
        // TODO
        return "QuoteMotivaBot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "5786160892:AAF67HqeRGTJzkSUuEuuGpFuJCmvNu7e_tc";
    }
}
