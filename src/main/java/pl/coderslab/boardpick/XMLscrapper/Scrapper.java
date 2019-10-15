package pl.coderslab.boardpick.XMLscrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import pl.coderslab.boardpick.entity.Game;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.round;

@Component
public class Scrapper {

    public static List<String> findFirstTen(String gameToAdd) {
        List<String> idsFound = new ArrayList<>();
        List<String> firstTenIdsFound = new ArrayList<>();

        try {
            org.jsoup.nodes.Document doc = Jsoup.connect("https://boardgamegeek.com/geeksearch.php?action=search&objecttype=boardgame&q=" + gameToAdd).get();
            Elements newsHeadlines = doc.select(".collection_objectname");
            for (Element headline : newsHeadlines) {
                final String url = headline.select("a").attr("href");
                // System.out.println(url);
                Pattern pattern = Pattern.compile("^\\/boardgame\\/(.*)\\/.*$");
                Matcher matcher = pattern.matcher(url);
                while (matcher.find()) {
                    idsFound.add(matcher.group(1));
                }
            }
            if ( idsFound.size() > 10 ) {
                firstTenIdsFound = idsFound.subList(0, 10);
            } else {
                firstTenIdsFound = idsFound;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstTenIdsFound;
    }


    public static Game getGameShort(String gameIdStats) {
        Game game = new Game();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL("https://boardgamegeek.com/xmlapi2/thing?id=" + gameIdStats + "&stats=1").openStream());

            String id = doc.getElementsByTagName("item").item(0).getAttributes().getNamedItem("id").getTextContent();
            String name = doc.getElementsByTagName("name").item(0).getAttributes().getNamedItem("value").getTextContent();
            String cover = doc.getElementsByTagName("image").item(0).getTextContent();
            String published = doc.getElementsByTagName("yearpublished").item(0).getAttributes().getNamedItem("value").getTextContent();

            game.setId(Long.parseLong(id));
            game.setName(name);
            game.setCover(cover);
            game.setPublished(published);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    public static Game getGameLong(String gameIdStats) {
        Game game = getGameShort(gameIdStats);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL("https://boardgamegeek.com/xmlapi2/thing?id=" + gameIdStats + "&stats=1").openStream());

            String minPlayers = doc.getElementsByTagName("minplayers").item(0).getAttributes().getNamedItem("value").getTextContent();
            String maxPlayers = doc.getElementsByTagName("maxplayers").item(0).getAttributes().getNamedItem("value").getTextContent();
            String minPlaytime = doc.getElementsByTagName("minplaytime").item(0).getAttributes().getNamedItem("value").getTextContent();
            String maxPlaytime = doc.getElementsByTagName("maxplaytime").item(0).getAttributes().getNamedItem("value").getTextContent();
            String rating = doc.getElementsByTagName("average").item(0).getAttributes().getNamedItem("value").getTextContent();
            String ranking = doc.getElementsByTagName("rank").item(0).getAttributes().getNamedItem("value").getTextContent();
            String weight = doc.getElementsByTagName("averageweight").item(0).getAttributes().getNamedItem("value").getTextContent();

            game.setMinPlayers(Integer.parseInt(minPlayers));
            game.setMaxPlayers(Integer.parseInt(maxPlayers));
            game.setMinPlaytime(Integer.parseInt(minPlaytime));
            game.setMaxPlaytime(Integer.parseInt(maxPlaytime));
            game.setRating(Math.round(Double.parseDouble(rating) * 100.0) / 100.0);
            game.setRanking(Integer.parseInt(ranking));
            game.setWeight(Math.round(Double.parseDouble(weight) * 100.0) / 100.0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }



}
