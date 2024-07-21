import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.Client;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public Main() throws IOException, URISyntaxException {
    }

    public static void main(String[] args) {

            List<List<String>> lines = readFile();
            List<Client> clients = new ArrayList<>();
            int cnt1 =0;
            int cost = 0;
            int cnt2 = 0;
            int evreg = 0;
            String keyword = "Lake";
            String letterToFind = "A" ;
            for (List<String> line : lines) {
                    Client client = new Client();
                    client.setNumber(Integer.valueOf(line.get(0)));
                    client.setId(line.get(0));
                    client.setFirstName(line.get(0));
                    client.setLastName(line.get(0));
                    client.setCity(line.get(0));
                    client.setCost(Integer.valueOf(line.get(0)));

                    if (client.getCity().contains(keyword) && client.getCost() > 400) {
                        cnt1++;
                    }
                    if (client.getFirstName().toLowerCase().contains(String.valueOf(letterToFind))){
                        cost += client.getCost();
                        cnt2 ++;
                        evreg = cost/cnt2;
                    }



                clients.add(client);



                }




            System.out.println(cnt1);
            System.out.println(evreg);





String pathInput = "C:\\Users\\lab-4\\Downloads\\39pic";




//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost request = new HttpPost("https://app.seker.live/fm1/answar-file");
//        CloseableHttpResponse response = client.execute(request);
//        Response response1 = new ObjectMapper().readValue(output, Response.class);



    }
    static void answer1(String id) throws URISyntaxException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        URI uri = new URIBuilder("https://app.seker.live/fm1/answar-file")
                .setParameter("magic", "2GNA56H")
                .setParameter("question", "1")
                .setParameter("answer" , "cnt1")
                .build();
        HttpGet request = new HttpGet(uri);
        CloseableHttpResponse response = client.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    static void answer2(String id) throws URISyntaxException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        URI uri = new URIBuilder("https://app.seker.live/fm1/answar-file")
                .setParameter("magic", "2GNA56H")
                .setParameter("question", "1")
                .setParameter("answer" , "evreg")
                .build();
        HttpGet request = new HttpGet(uri);
        CloseableHttpResponse response = client.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    public static void grayscale(String pathInput){

        try {
            File file = new File(pathInput);
            if (file.exists()) {
                System.out.println("exists");
                BufferedImage bufferedImage = ImageIO.read(file);
                int cntr = 0;
                int cntg = 0;
                int cntb = 0;
                String Right;

                for (int x = 0; x < bufferedImage.getWidth(); x++) {
                    for (int y = 0; y < bufferedImage.getHeight(); y++) {
                        int currentColor = bufferedImage.getRGB(x, y);
                        Color colorPicture = new Color(currentColor);
                        int green =  colorPicture.getGreen();
                        int blue =  colorPicture.getBlue();
                        int red =  colorPicture.getRed();

                        if (red <blue && red< green){
                            cntr++;
                        }
                        if (blue<red && blue< green){
                            cntb ++;

                        }

                        if (green<red && green< blue){
                            cntg ++;

                        }

                    }
                }

                if (cntr< cntb && cntr< cntg  ){
                    Right = "red" ;
                }

                if (cntb< cntr && cntb< cntg  ){
                    Right = "blue" ;
                }
                if (cntg< cntb && cntg< cntr  ){
                    Right = "green" ;
                }




            }else{
                System.out.println("nop");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    static void answer3(String id) throws URISyntaxException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        URI uri = new URIBuilder("https://app.seker.live/fm1/answar-file")
                .setParameter("magic", "2GNA56H")
                .setParameter("red", "get")
                .setParameter("answer" , "evreg")
                .build();
        HttpGet request = new HttpGet(uri);
        CloseableHttpResponse response = client.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }







    //Write your code here!


    public static List<List<String>> readFile () {
        List<List<String>> lines = new ArrayList<>();
        try {
            File file = new File("data.csv");

            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] tokens = line.split("data.csv");
                    List<String> tokensList = new ArrayList<>(Arrays.asList(tokens));
                    lines.add(tokensList);
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }


}
