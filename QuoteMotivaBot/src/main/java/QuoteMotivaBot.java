import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.*;



public class QuoteMotivaBot extends TelegramLongPollingBot {
    //nesse método que vamos incluir os comandos do bot
    @Override
    public void onUpdateReceived(Update update) {

        //System.out.println(update.getMessage().getText()); esse método é pra mostrar no IDE o que o usuário escreveu
        //System.out.println(update.getMessage().getFrom().getFirstName()); esse método é pra mostrar no IDE o nome do usuário q escreveu

        String command = update.getMessage().getText();
        if (command.equals("/boasvindas")) {
            String message = "Seja bem vindo(a) ao nosso bot de mensagens motivacionais! Digite o comando /frase para receber uma mensagem aleatória";
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);

            try {
                execute(response);
            } catch (TelegramApiException BV) {
                BV.printStackTrace();
            }

      } else if (command.equals("/frase")) {

            //aqui vai nosso código com o loop
            File dir = new File("C:\\Users\\samara");
            //Cria um novo arquivo
            File arq = new File(dir, "frases.txt");

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
                SendMessage responseR = new SendMessage();
                responseR.setChatId(update.getMessage().getChatId());
                responseR.setText(frase);
             
                try {
                    execute(responseR);
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
